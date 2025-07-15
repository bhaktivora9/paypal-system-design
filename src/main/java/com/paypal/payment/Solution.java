package com.paypal.payment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.paypal.payment.config.DefaultPaymentConfiguration;
import com.paypal.payment.config.PaymentConfiguration;
import com.paypal.payment.service.PaymentService;
import com.paypal.payment.strategy.PaymentMethodFactory;

public class Solution {

	public static void processPayments(List<String> paymentMethods, List<Double> amounts, PaymentConfiguration config) {

		// Loading config to make sure we are keeping values configurable.
		if (config == null) {
			config = new DefaultPaymentConfiguration();
		}

		PaymentMethodFactory factory = new PaymentMethodFactory(config);
		// service responsible for executing payment service
		// TODO: Add metrics collector.
		PaymentService paymentService = new PaymentService(factory);

		// Process each payment
		for (int i = 0; i < paymentMethods.size(); i++) {
			String method = paymentMethods.get(i);
			double amount = amounts.get(i);
			String customerId = "customer_" + i; // Simple customer ID for demo

			paymentService.processPayment(method, amount, customerId);

			// Add spacing between payments as required by HackerRank format
			if (i < paymentMethods.size() - 1) {
				System.out.println();
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(reader.readLine().trim());

		List<String> methods = new ArrayList<>();
		List<Double> amounts = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			String[] line = reader.readLine().trim().split(" ");
			methods.add(line[0]);
			amounts.add(Double.parseDouble(line[1]));
		}

		processPayments(methods, amounts,null);
		reader.close();
	}
}
