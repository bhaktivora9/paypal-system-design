package com.paypal.payment.config;

public interface PaymentConfiguration {

	double getCreditCardRewardPercentage();

	double getCreditCardMaxReward();

	double getPayPalInterestRate();

	int getPayPalInstallmentCount();
}
