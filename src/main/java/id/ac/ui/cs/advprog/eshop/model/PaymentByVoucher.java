package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import java.util.Map;

public class PaymentByVoucher extends Payment{
    public PaymentByVoucher(String id, String method, String status, Map<String, String> paymentData) {
        super(id, method, status, paymentData);
    }

    public PaymentByVoucher(String id, String method, Map<String, String> paymentData) {
        super(id, method, PaymentStatus.PENDING.getValue(), paymentData);
    }

    @Override
    public void setPaymentData(Map<String, String> paymentData) {
        if (paymentData.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            int nums = 0;
            for (char c : paymentData.get("voucherCode").toCharArray()) {
                if (Character.isDigit(c)) {
                    nums++;
                }
            }
            this.paymentData = paymentData;
            if (paymentData.get("voucherCode").length() == 16 && paymentData.get("voucherCode").startsWith("ESHOP") && nums == 8) {
                this.status = PaymentStatus.SUCCESS.getValue();
            } else {
                this.status = PaymentStatus.REJECTED.getValue();
            }
        }
    }
}
