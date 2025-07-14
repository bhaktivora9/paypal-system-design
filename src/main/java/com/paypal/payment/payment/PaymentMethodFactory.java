package com.paypal.payment.payment;

import com.paypal.payment.config.PaymentConfiguration;

/**
 * Factory for creating payment method instances
 */
public class PaymentMethodFactory {

	private final PaymentConfiguration config;

	public PaymentMethodFactory(PaymentConfiguration config) {
		this.config = config;
	}

	public PaymentMethod createPaymentMethod(String methodType) {
		switch (methodType) {
		case "CreditCard":
			return new CreditCardPaymentMethod(config);
		case "PayPal":
			return new PayPalPaymentMethod(config);
		default:
			throw new IllegalArgumentException("Unsupported payment method: " + methodType);
		}
	}
}