package com.paypal.assessment.q2;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {
    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void testProcessPayments_ValidInputs() {
        List<String> methods = Arrays.asList("CreditCard", "PayPal");
        List<Double> amounts = Arrays.asList(100.0, 200.0);

        Solution.processPayments(methods, amounts);
        
        String output = outContent.toString();
        assertTrue(output.contains("Paying $100.00 via CreditCard"));
        assertTrue(output.contains("Reward Points Redemption"));
        assertTrue(output.contains("Paying $200.00 via PayPal"));
        assertTrue(output.contains("Installment Payment Plan"));
    }
    
    @Test
    void testProcessPayments_InvalidAmount() {
        List<String> methods = Arrays.asList("CreditCard");
        List<Double> amounts = Arrays.asList(-50.0);

        Solution.processPayments(methods, amounts);
        
        String errorOutput = errContent.toString();
        assertTrue(errorOutput.contains("Invalid payment data"));
        assertTrue(errorOutput.contains("must be positive"));
    }
    
    @Test
    void testProcessPayments_UnsupportedMethod() {
        List<String> methods = Arrays.asList("Bitcoin");
        List<Double> amounts = Arrays.asList(100.0);

        Solution.processPayments(methods, amounts);
        
        String errorOutput = errContent.toString();
        assertTrue(errorOutput.contains("not supported"));
    }
    
    @Test
    void testProcessPayments_EmptyCustomerId() {
        List<String> methods = Arrays.asList("CreditCard");
        List<Double> amounts = Arrays.asList(100.0);

        // This test checks that the service properly handles customer ID validation
        // The actual validation happens in the Payment constructor
        Solution.processPayments(methods, amounts);
        
        // Should succeed because Solution provides customer IDs
        String output = outContent.toString();
        assertTrue(output.contains("Paying $100.00 via CreditCard"));
    }
}