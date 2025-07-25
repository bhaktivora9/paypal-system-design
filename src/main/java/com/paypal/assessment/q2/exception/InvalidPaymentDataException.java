package com.paypal.assessment.q2.exception;

/**
 * Exception thrown when input validation fails
 */
public class InvalidPaymentDataException extends PaymentException {
    
    public InvalidPaymentDataException(String message) {
        super(message, "INVALID_INPUT");
    }
    
    public InvalidPaymentDataException(String message, Throwable cause) {
        super(message, "INVALID_INPUT", cause);
    }
}