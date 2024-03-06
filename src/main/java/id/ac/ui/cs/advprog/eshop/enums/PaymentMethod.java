package id.ac.ui.cs.advprog.eshop.enums;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    BY_VOUCHER("by-voucher"),
    BY_TRANSFER("by-transfer");

    private final String value;

    private PaymentMethod(String value) {
        this.value = value;
    }

    public static boolean contains(String param) {
        for (PaymentStatus paymentStatus : PaymentStatus.values()) {
            if (paymentStatus.name().equals(param)){
                return true;
            }
        }
        return false;
    }
}
