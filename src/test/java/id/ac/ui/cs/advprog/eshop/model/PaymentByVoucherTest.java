package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaymentByVoucherTest {
    Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<String, String>();
    }

    @Test
    void testEmptyPaymentData() {
        PaymentByVoucher payment = new PaymentByVoucher("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.BY_VOUCHER.getValue(), this.paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setPaymentData(this.paymentData));
    }

    @Test
    void testSetValidPaymentData() {
        this.paymentData.put("voucherCode", "ESHOP6743FHD8521");
        PaymentByVoucher payment = new PaymentByVoucher("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.BY_VOUCHER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testSetShortInvalidPaymentData() {
        this.paymentData.put("voucherCode", "ESHOP12357234FB");
        PaymentByVoucher payment = new PaymentByVoucher("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.BY_VOUCHER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testNoPrefixInvalidPaymentData() {
        this.paymentData.put("voucherCode", "31692FHS3");
        PaymentByVoucher payment = new PaymentByVoucher("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.BY_VOUCHER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testNoEightNumberPaymentData() {
        this.paymentData.put("voucherCode", "ESHOPASGHGHSGHSU");
        PaymentByVoucher payment = new PaymentByVoucher("13652556-012a-4c07-b546-54eb1396d79b", PaymentMethod.BY_VOUCHER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}
