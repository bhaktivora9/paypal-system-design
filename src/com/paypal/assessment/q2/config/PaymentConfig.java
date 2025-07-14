package com.paypal.assessment.q2.config;


public class PaymentConfig {
// Can be read from application.properties.
	public double getRewardPercentage() {
		return 0.10; // 10%
	}

	public double getMaxReward() {
		return 10.0;
	}

	public int getInstallmentCount() {
		return 2;
	}

	public double getInterestRate() {
		return 0.05;
	}

	public double getMinimumAmount() {
		return 0.01;
	}

	public double getLimit() {
		return 10000.0;
	}
}