package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaymentTest {
    private Map<String,String> paymentData;
    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<String,String>();
    }
    @Test
    void testCreatePaymentInvalidMethod() {
        paymentData.put("ewallet", "081212121212");
        assertThrows(IllegalArgumentException.class, () -> {
           Payment payment = new Payment(
                   "b94d0613-a74b-427e-8967-dc40abda13e7",
                   "by-ewallet",
                   "SUCCESS",
                   paymentData);
        });
    }

    @Test
    void testCreatePaymentValidStatus() {
        paymentData.put("voucherCode", "ESHOP80235gh021f");
        Payment payment = new Payment(
                "b94d0613-a74b-427e-8967-dc40abda13e7",
                "by-voucher",
                "SUCCESS",
                paymentData
        );
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        paymentData.put("voucherCode", "ESHOP80235gh021f");
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(
                    "b94d0613-a74b-427e-8967-dc40abda13e7",
                    "by-voucher",
                    "GAKMAU",
                    paymentData
            );
        });
    }

    @Test
    void testCreatePaymentValidMethod() {
        paymentData.put("voucherCode", "ESHOP80235gh021f");
        Payment payment = new Payment(
                "b94d0613-a74b-427e-8967-dc40abda13e7",
                "by-voucher",
                "SUCCESS",
                paymentData
        );
        assertEquals("by-voucher", payment.getMethod());
    }

    @Test
    void testSetStatusInvalid() {
        paymentData.put("voucherCode", "ESHOP80235gh021f");
        Payment payment = new Payment(
                "b94d0613-a74b-427e-8967-dc40abda13e7",
                "by-voucher",
                "SUCCESS",
                paymentData
        );
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("HAH?????"));
    }

    @Test
    void testSetStatusToRejected() {
        paymentData.put("voucherCode", "ESHOP80235gh021f");
        Payment payment = new Payment(
                "b94d0613-a74b-427e-8967-dc40abda13e7",
                "by-voucher",
                "SUCCESS",
                paymentData
        );
        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }
}
