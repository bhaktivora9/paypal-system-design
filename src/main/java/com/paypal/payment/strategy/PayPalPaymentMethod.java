package com.paypal.payment.strategy;

import com.paypal.payment.config.PaymentConfiguration;
import com.paypal.payment.util.Utils;

public class PayPalPaymentMethod extends AbstractPaymentMethod {

	public PayPalPaymentMethod(PaymentConfiguration config) {
		super("PayPal", config);
	}

	// Assumption : Scheduled Installment to be handled, messaging, 
	@Override
	protected void executePayment(double amount) {
		printPaymentStmt(amount, "Installment Payment Plan");

		// Business logic: Calculate installments with configuration
		int installmentCount = config.getPayPalInstallmentCount();
		double interestRate = config.getPayPalInterestRate();
		double baseInstallment = amount / installmentCount;
		
		  for (int i = 1; i <= installmentCount; i++) {
	            double installmentAmount;
	            String installmentDescription;
	            
	            if (i == installmentCount) {
	                // Final installment: Apply interest rate
	                installmentAmount = baseInstallment * (1 + interestRate);
	                installmentDescription = (i) + " installment with " + 
	                                       (int)(interestRate * 100) + "% interest";
	            } else {
	                // Regular installment: No interest
	                installmentAmount = baseInstallment;
	                installmentDescription = (i) + " installment";
	            }
	            
	            // Output installment processing
	            System.out.println("Paid $" + Utils.roundDouble(installmentAmount) + 
	                             " in " + installmentDescription + ".");
	        }
		
	}
	
	
}