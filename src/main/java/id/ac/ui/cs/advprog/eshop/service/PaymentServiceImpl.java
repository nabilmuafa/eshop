package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData){
        String paymentId = order.getId();
        Payment savedPayment = paymentRepository.findById(paymentId);
        if (savedPayment == null || !savedPayment.getStatus().equals(PaymentStatus.SUCCESS.getValue())){
            Payment payment = new Payment(order.getId(), method, paymentData);
            paymentRepository.save(payment);
            return payment;
        }
        return null;
    }
    @Override
    public Payment setStatus(Payment payment, String status){
        Payment findPayment = paymentRepository.findById(payment.getId());
        String orderStatus = "SUCCESS";
        if (findPayment != null) {
            Order order = orderRepository.findById(payment.getId());
            Payment newPayment = new Payment(payment.getId(), payment.getMethod(), status, payment.getPaymentData());
            if (status.equals("REJECTED")){
                orderStatus = "FAILED";
            }
            Order newOrder = new Order(order.getId(), order.getProducts(), order.getOrderTime(), order.getAuthor(), orderStatus);
            paymentRepository.save(newPayment);
            orderRepository.save(newOrder);
            return newPayment;
        }
        else {
            throw new NoSuchElementException();
        }
    }
    @Override
    public Payment getPayment(String paymentId){
        return paymentRepository.findById(paymentId);
    }
    @Override
    public List<Payment> getAllPayments(){
        return paymentRepository.findAll();
    }
}
