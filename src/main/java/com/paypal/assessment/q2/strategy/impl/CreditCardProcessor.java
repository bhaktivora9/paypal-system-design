package com.paypal.assessment.q2.strategy.impl;

import com.paypal.assessment.q2.config.PaymentConfig;
import com.paypal.assessment.q2.domain.Payment;
import com.paypal.assessment.q2.enums.PaymentMethod;
import com.paypal.assessment.q2.strategy.PaymentProcessor;
import com.paypal.assessment.q2.strategy.PaymentResult;
import com.paypal.assessment.q2.utils.Utils;

/**
 * Credit card payment processor implementing reward points strategy
 */
public class CreditCardProcessor implements PaymentProcessor {
    
    private final PaymentConfig config;
    
    public CreditCardProcessor(PaymentConfig config) {
        this.config = config;
    }
    
    @Override
    public PaymentResult processPayment(Payment payment) {
        try {
            Payment.RewardResult result = payment.calculateRewards(
                config.getRewardPercentage(), 
                config.getMaxReward()
            );
            
            String message = String.format("Paying $%s via %s using Reward Points Redemption Feature.", 
                                         Utils.roundDouble(payment.getAmount()), 
                                         payment.getMethod());
            
            String details = String.format("Redeemed $%s using reward points.%nPaying remaining amount of $%s via credit card.", 
                                         result.getRewardAmountFormatted(),
                                         result.getRemainingAmountFormatted());
            
            return PaymentResult.success(message, details);
            
        } catch (Exception e) {
            return PaymentResult.failure("Failed to process credit card payment", e.getMessage());
        }
    }
    
    @Override
    public boolean supports(String method) {
        return PaymentMethod.CREDIT_CARD.getMethodName().equalsIgnoreCase(method);
    }
    
    @Override
    public String getProcessorName() {
        return "Credit Card Reward Processor";
    }
}