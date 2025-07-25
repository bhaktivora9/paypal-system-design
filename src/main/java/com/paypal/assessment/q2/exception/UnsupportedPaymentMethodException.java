package com.paypal.assessment.q2.exception;

/**
 * Exception thrown when payment method is not supported
 */
public class UnsupportedPaymentMethodException extends PaymentException {
    
    public UnsupportedPaymentMethodException(String paymentMethod) {
        super(String.format("Payment method '%s' is not supported", paymentMethod), "UNSUPPORTED_METHOD");
    }
    
    public UnsupportedPaymentMethodException(String message, Throwable cause) {
        super(message, "UNSUPPORTED_METHOD", cause);
    }
}