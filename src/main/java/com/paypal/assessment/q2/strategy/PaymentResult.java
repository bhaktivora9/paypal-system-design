package com.paypal.assessment.q2.strategy;

/**
 * Result of payment processing operation
 */
public class PaymentResult {
    private final boolean success;
    private final String message;
    private final String details;
    
    public PaymentResult(boolean success, String message, String details) {
        this.success = success;
        this.message = message;
        this.details = details;
    }
    
    public static PaymentResult success(String message, String details) {
        return new PaymentResult(true, message, details);
    }
    
    public static PaymentResult failure(String message, String details) {
        return new PaymentResult(false, message, details);
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public String getMessage() {
        return message;
    }
    
    public String getDetails() {
        return details;
    }
    
    @Override
    public String toString() {
        return String.format("PaymentResult{success=%s, message='%s', details='%s'}", 
                           success, message, details);
    }
}