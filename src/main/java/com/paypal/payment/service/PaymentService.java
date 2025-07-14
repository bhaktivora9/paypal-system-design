package com.paypal.payment.service;

import com.paypal.payment.payment.PaymentMethod;
import com.paypal.payment.payment.PaymentMethodFactory;

public class PaymentService {

	private final PaymentMethodFactory paymentFactory;

	public PaymentService(PaymentMethodFactory paymentFactory) {
		this.paymentFactory = paymentFactory;
	}

	public void processPayment(String paymentMethod, double amount, String customerId) {

		try {
			// Validate business rules
			// throw exception if required

			// Process payment immediately. no delay
			PaymentMethod method = paymentFactory.createPaymentMethod(paymentMethod);
			method.pay(amount);

		} catch (Exception e) {
			// TODO: Implement proper error handling strategy for production
			// For now, we'll print error to stderr and continue processing
			System.err.println("Payment processing failed: " + e.getMessage());
		}
	}
}
