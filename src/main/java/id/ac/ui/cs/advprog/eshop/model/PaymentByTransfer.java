package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import java.util.Map;

public class PaymentByTransfer extends Payment {
    public PaymentByTransfer(String id, String method, String status, Map<String, String> paymentData) {
        super(id, method, status, paymentData);
    }

    public PaymentByTransfer(String id, String method, Map<String, String> paymentData) {
        super(id, method, PaymentStatus.PENDING.getValue(), paymentData);
    }

    @Override
    public void setPaymentData(Map<String, String> paymentData) {
        if (paymentData.isEmpty() || paymentData.get("bankName").isEmpty() || paymentData.get("referenceCode").isEmpty()) {
            this.status = PaymentStatus.REJECTED.getValue();
        } else {
            this.paymentData = paymentData;
            this.status = PaymentStatus.SUCCESS.getValue();
        }
    }
}