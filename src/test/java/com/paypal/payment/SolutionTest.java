package com.paypal.payment;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class SolutionTest {

    @Test
    public void testProcessPaymentsWithPayPal() {
        List<String> methods = Arrays.asList("PayPal");
        List<Double> amounts = Arrays.asList(100.0);
        Solution.processPayments(methods, amounts);
        // You can capture console output and assert here if using System Lambda or similar
    }

    @Test
    public void testProcessPaymentsWithCreditCard() {
        List<String> methods = Arrays.asList("CreditCard");
        List<Double> amounts = Arrays.asList(120.0);
        Solution.processPayments(methods, amounts);
        // You can capture console output and assert here if using System Lambda or similar
    }

    @Test
    public void testProcessPaymentsWithMultipleMethods() {
        List<String> methods = Arrays.asList("PayPal", "CreditCard");
        List<Double> amounts = Arrays.asList(80.0, 150.0);
        Solution.processPayments(methods, amounts);
        // You can capture console output and assert here if using System Lambda or similar
    }
}