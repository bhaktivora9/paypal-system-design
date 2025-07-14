package com.paypal.payment.payment;

import com.paypal.payment.config.PaymentConfiguration;
import com.paypal.payment.util.Utils;

public abstract class AbstractPaymentMethod implements PaymentMethod {

	protected final String paymentMethodName;
	protected final PaymentConfiguration config;

	protected AbstractPaymentMethod(String methodName, PaymentConfiguration config) {
		this.paymentMethodName = methodName;
		this.config = config;
	}

	@Override
	public final void pay(double amount) {
		// TODO validate amount if amt is valid, String, .. Can be done on FE.
		executePayment(amount);
	}

	protected abstract void executePayment(double amount);

	protected void printPaymentStmt(double amount, String feature) {
		System.out.println(
				"Paying $" + Utils.roundDouble(amount) + " via " + paymentMethodName + " using " + feature + ".");
		
	}
}