package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
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
                   PaymentStatus.SUCCESS.getValue(),
                   paymentData);
        });
    }

    @Test
    void testCreatePaymentValidStatus() {
        paymentData.put("voucherCode", "ESHOP80235gh021f");
        Payment payment = new Payment(
                "b94d0613-a74b-427e-8967-dc40abda13e7",
                PaymentMethod.BY_VOUCHER.getValue(),
                PaymentStatus.SUCCESS.getValue(),
                paymentData
        );
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        paymentData.put("voucherCode", "ESHOP80235gh021f");
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(
                    "b94d0613-a74b-427e-8967-dc40abda13e7",
                    PaymentMethod.BY_VOUCHER.getValue(),
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
                PaymentMethod.BY_VOUCHER.getValue(),
                PaymentStatus.SUCCESS.getValue(),
                paymentData
        );
        assertEquals(PaymentMethod.BY_VOUCHER.getValue(), payment.getMethod());
    }

    @Test
    void testSetStatusInvalid() {
        paymentData.put("voucherCode", "ESHOP80235gh021f");
        Payment payment = new Payment(
                "b94d0613-a74b-427e-8967-dc40abda13e7",
                PaymentMethod.BY_VOUCHER.getValue(),
                PaymentStatus.SUCCESS.getValue(),
                paymentData
        );
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("HAH?????"));
    }

    @Test
    void testSetStatusToRejected() {
        paymentData.put("voucherCode", "ESHOP80235gh021f");
        Payment payment = new Payment(
                "b94d0613-a74b-427e-8967-dc40abda13e7",
                PaymentMethod.BY_VOUCHER.getValue(),
                PaymentStatus.SUCCESS.getValue(),
                paymentData
        );
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreateAllValidArguments() {
        paymentData.put("voucherCode", "ESHOP80235gh021f");
        Payment payment = new Payment(
                "b94d0613-a74b-427e-8967-dc40abda13e7",
                PaymentMethod.BY_VOUCHER.getValue(),
                PaymentStatus.SUCCESS.getValue(),
                paymentData
        );
        assertEquals("b94d0613-a74b-427e-8967-dc40abda13e7", payment.getId());
        assertEquals(PaymentMethod.BY_VOUCHER.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }
}
