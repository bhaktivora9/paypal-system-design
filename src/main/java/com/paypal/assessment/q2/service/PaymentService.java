package com.paypal.assessment.q2.service;

import com.paypal.assessment.q2.config.PaymentConfig;
import com.paypal.assessment.q2.domain.Payment;
import com.paypal.assessment.q2.utils.Utils;

public class PaymentService {
    private final PaymentConfig config;
    
    public PaymentService(PaymentConfig config) {
        this.config = config;
    }
    
    public void processPayment(String method, double amount, String customerId) {
        Payment payment = new Payment(amount, customerId, method);
        
        if (payment.canUseInstallments()) {
            processInstallmentPayment(payment);
        } else if (payment.canEarnRewards()) {
            processRewardPayment(payment);
        }
    }
    
    private void processInstallmentPayment(Payment payment) {
        Payment.InstallmentResult result = payment.calculateInstallments(
            config.getInstallmentCount(), 
            config.getInterestRate()
        );
        
        // ✅ Using Utils.roundDouble() through domain methods
        System.out.println("Paying $" + Utils.roundDouble(payment.getAmount()) + 
                          " via " + payment.getMethod() + " using Installment Payment Plan.");
        System.out.println("Paid $" + result.getBaseAmountFormatted() + " in first installment.");
        System.out.println("Paid $" + result.getFinalAmountFormatted() + 
                          " in second installment with " + (int)(config.getInterestRate() * 100) + "% interest.");
    }
    
    private void processRewardPayment(Payment payment) {
        Payment.RewardResult result = payment.calculateRewards(
            config.getRewardPercentage(), 
            config.getMaxReward()
        );
        
        // ✅ Using Utils.roundDouble() through domain methods
        System.out.println("Paying $" + Utils.roundDouble(payment.getAmount()) + 
                          " via " + payment.getMethod() + " using Reward Points Redemption Feature.");
        System.out.println("Redeemed $" + result.getRewardAmountFormatted() + " using reward points.");
        System.out.println("Paying remaining amount of $" + result.getRemainingAmountFormatted() + 
                          " via credit card.");
    }
}