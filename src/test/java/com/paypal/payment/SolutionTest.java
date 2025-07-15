package com.paypal.payment;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.paypal.payment.config.DefaultPaymentConfiguration;

public class SolutionTest {

    @Test
    public void testProcessPaymentsWithPayPal() {
        List<String> methods = Arrays.asList("PayPal");
        List<Double> amounts = Arrays.asList(100.0);
        Solution.processPayments(methods, amounts,null);
        // You can capture console output and assert here if using System Lambda or similar
    }

    @Test
    public void testProcessPaymentsWithCreditCard() {
        List<String> methods = Arrays.asList("CreditCard");
        List<Double> amounts = Arrays.asList(120.0);
        Solution.processPayments(methods, amounts,null);
        // You can capture console output and assert here if using System Lambda or similar
    }

    @Test
    public void testProcessPaymentsWithPaypalAndThreeInstallments() {
    	List<String> methods = Arrays.asList("PayPal");
    	List<Double> amounts = Arrays.asList(120.0);
    	DefaultPaymentConfiguration defaultPaymentConfiguration = new DefaultPaymentConfiguration();
    	defaultPaymentConfiguration.setPayPalInstallmentCount(3);
    	Solution.processPayments(methods, amounts, defaultPaymentConfiguration);
    	// You can capture console output and assert here if using System Lambda or similar
    }
    
    @Test
    public void testProcessPaymentsWithMultipleMethods() {
        List<String> methods = Arrays.asList("PayPal", "CreditCard");
        List<Double> amounts = Arrays.asList(80.0, 150.0);
        Solution.processPayments(methods, amounts,null);
        // You can capture console output and assert here if using System Lambda or similar
    }
}