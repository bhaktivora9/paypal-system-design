package com.paypal.assessment.q2.strategy.impl;

import com.paypal.assessment.q2.config.PaymentConfig;
import com.paypal.assessment.q2.domain.Payment;
import com.paypal.assessment.q2.enums.PaymentMethod;
import com.paypal.assessment.q2.strategy.PaymentProcessor;
import com.paypal.assessment.q2.strategy.PaymentResult;
import com.paypal.assessment.q2.utils.Utils;

/**
 * PayPal payment processor implementing installment payment strategy
 */
public class PayPalProcessor implements PaymentProcessor {
    
    private final PaymentConfig config;
    
    public PayPalProcessor(PaymentConfig config) {
        this.config = config;
    }
    
    @Override
    public PaymentResult processPayment(Payment payment) {
        if (!payment.canUseInstallments()) {
            return PaymentResult.failure("Payment not eligible for installments", 
                                       "PayPal installments require minimum criteria");
        }
        
        try {
            Payment.InstallmentResult result = payment.calculateInstallments(
                config.getInstallmentCount(), 
                config.getInterestRate()
            );
            
            String message = String.format("Paying $%s via %s using Installment Payment Plan.", 
                                         Utils.roundDouble(payment.getAmount()), 
                                         payment.getMethod());
            
            String details = String.format("Paid $%s in first installment.%nPaid $%s in second installment with %d%% interest.", 
                                         result.getBaseAmountFormatted(),
                                         result.getFinalAmountFormatted(),
                                         (int)(config.getInterestRate() * 100));
            
            return PaymentResult.success(message, details);
            
        } catch (Exception e) {
            return PaymentResult.failure("Failed to process PayPal payment", e.getMessage());
        }
    }
    
    @Override
    public boolean supports(String method) {
        return PaymentMethod.PAYPAL.getMethodName().equalsIgnoreCase(method);
    }
    
    @Override
    public String getProcessorName() {
        return "PayPal Installment Processor";
    }
}