package com.paypal.assessment.q2.strategy;

import com.paypal.assessment.q2.domain.Payment;

/**
 * Strategy interface for payment processing
 */
public interface PaymentProcessor {
    
    /**
     * Process a payment using the specific payment method
     * @param payment The payment to process
     * @return PaymentResult containing the processing outcome
     */
    PaymentResult processPayment(Payment payment);
    
    /**
     * Check if this processor supports the given payment method
     * @param method The payment method
     * @return true if supported, false otherwise
     */
    boolean supports(String method);
    
    /**
     * Get the name of this payment processor
     * @return processor name
     */
    String getProcessorName();
}