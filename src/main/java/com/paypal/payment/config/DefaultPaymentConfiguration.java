package com.paypal.payment.config;

// TODO read from application.properties
public class DefaultPaymentConfiguration implements PaymentConfiguration {
	int payPalInstallmentCount = 2;

	@Override
	public double getCreditCardRewardPercentage() {
		return 0.10; // 10%
	}

	@Override
	public double getCreditCardMaxReward() {
		return 10.0; // $10 maximum
	}

	@Override
	public double getPayPalInterestRate() {
		return 0.05; // 5%
	}

	@Override
	public int getPayPalInstallmentCount() {
		return payPalInstallmentCount;
	}

	// Setter method to allow configuration changes
	public void setPayPalInstallmentCount(int installments) {
		if (installments > 2) {
			this.payPalInstallmentCount = installments;
		}

	}

}
