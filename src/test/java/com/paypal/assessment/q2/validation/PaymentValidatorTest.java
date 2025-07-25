package com.paypal.assessment.q2.validation;

import com.paypal.assessment.q2.exception.InvalidPaymentDataException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentValidatorTest {

    @Test
    void testValidateAmount_ValidAmount() {
        assertDoesNotThrow(() -> PaymentValidator.validateAmount(100.0));
    }

    @Test
    void testValidateAmount_NegativeAmount() {
        InvalidPaymentDataException exception = assertThrows(
            InvalidPaymentDataException.class,
            () -> PaymentValidator.validateAmount(-50.0)
        );
        assertTrue(exception.getMessage().contains("must be positive"));
    }

    @Test
    void testValidateAmount_ZeroAmount() {
        InvalidPaymentDataException exception = assertThrows(
            InvalidPaymentDataException.class,
            () -> PaymentValidator.validateAmount(0.0)
        );
        assertTrue(exception.getMessage().contains("must be positive"));
    }

    @Test
    void testValidateAmount_TooSmallAmount() {
        InvalidPaymentDataException exception = assertThrows(
            InvalidPaymentDataException.class,
            () -> PaymentValidator.validateAmount(0.001)
        );
        assertTrue(exception.getMessage().contains("must be at least"));
    }

    @Test
    void testValidateAmount_TooLargeAmount() {
        InvalidPaymentDataException exception = assertThrows(
            InvalidPaymentDataException.class,
            () -> PaymentValidator.validateAmount(200000.0)
        );
        assertTrue(exception.getMessage().contains("cannot exceed"));
    }

    @Test
    void testValidateCustomerId_ValidId() {
        assertDoesNotThrow(() -> PaymentValidator.validateCustomerId("customer123"));
    }

    @Test
    void testValidateCustomerId_NullId() {
        InvalidPaymentDataException exception = assertThrows(
            InvalidPaymentDataException.class,
            () -> PaymentValidator.validateCustomerId(null)
        );
        assertTrue(exception.getMessage().contains("cannot be null"));
    }

    @Test
    void testValidateCustomerId_EmptyId() {
        InvalidPaymentDataException exception = assertThrows(
            InvalidPaymentDataException.class,
            () -> PaymentValidator.validateCustomerId("")
        );
        assertTrue(exception.getMessage().contains("cannot be null or empty"));
    }

    @Test
    void testValidateCustomerId_SqlInjection() {
        InvalidPaymentDataException exception = assertThrows(
            InvalidPaymentDataException.class,
            () -> PaymentValidator.validateCustomerId("customer'; DROP TABLE users; --")
        );
        assertTrue(exception.getMessage().contains("invalid characters"));
    }

    @Test
    void testValidatePaymentMethod_ValidMethod() {
        assertDoesNotThrow(() -> PaymentValidator.validatePaymentMethod("PayPal"));
        assertDoesNotThrow(() -> PaymentValidator.validatePaymentMethod("CreditCard"));
    }

    @Test
    void testValidatePaymentMethod_InvalidMethod() {
        InvalidPaymentDataException exception = assertThrows(
            InvalidPaymentDataException.class,
            () -> PaymentValidator.validatePaymentMethod("Bitcoin")
        );
        assertTrue(exception.getMessage().contains("not supported"));
    }

    @Test
    void testValidatePaymentMethod_NullMethod() {
        InvalidPaymentDataException exception = assertThrows(
            InvalidPaymentDataException.class,
            () -> PaymentValidator.validatePaymentMethod(null)
        );
        assertTrue(exception.getMessage().contains("cannot be null"));
    }
}