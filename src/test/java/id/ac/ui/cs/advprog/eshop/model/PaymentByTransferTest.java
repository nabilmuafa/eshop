package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentByTransferTest {
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();
    }

    @Test
    void testValidPaymentData() {
        this.paymentData.put("bankName", "Bank Jago");
        this.paymentData.put("referenceCode", "cabefaefcecaaebaefcbaebfeab");
        PaymentByTransfer payment = new PaymentByTransfer("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.BY_TRANSFER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testInvalidEmptyBankNamePaymentData() {
        this.paymentData.put("bankName", "");
        this.paymentData.put("referenceCode", "cabefaefcecaaebaefcbaebfeab");
        PaymentByTransfer payment = new PaymentByTransfer("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.BY_TRANSFER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testInvalidEmptyCodePaymentData() {
        this.paymentData.put("bankName", "BCA");
        this.paymentData.put("referenceCode", "");
        PaymentByTransfer payment = new PaymentByTransfer("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.BY_TRANSFER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testInvalidEmptyPaymentData() {
        PaymentByTransfer payment = new PaymentByTransfer("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.BY_TRANSFER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}