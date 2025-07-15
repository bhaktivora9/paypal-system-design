package com.paypal.payment.strategy;

import com.paypal.payment.config.PaymentConfiguration;
import com.paypal.payment.util.Utils;

public class CreditCardPaymentMethod extends AbstractPaymentMethod {
    
    public CreditCardPaymentMethod(PaymentConfiguration config) {
        super("Credit Card", config);
    }
    
    @Override
    protected void executePayment(double amount) {
        
        double rewardPercentage = config.getCreditCardRewardPercentage();
        double maxReward = config.getCreditCardMaxReward();
        double redeemableAmount = Math.min(amount * rewardPercentage, maxReward);
        double remainingAmount = amount - redeemableAmount;
        
        System.out.println("Redeemed $" + Utils.roundDouble(redeemableAmount) + " using reward points.");
        System.out.println("Paying remaining amount of $" + Utils.roundDouble(remainingAmount) + " via credit card.");
    }
}