package com.paypal.assessment.q2;


import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class SolutionTest {

    @Test
    void testProcessPayments() {
        List<String> methods = Arrays.asList("CreditCard", "Paypal");
        List<Double> amounts = Arrays.asList(100.0, 200.0);

        // This will print to console, so you can verify output manually or redirect output for assertions.
        Solution.processPayments(methods, amounts);
    }
}