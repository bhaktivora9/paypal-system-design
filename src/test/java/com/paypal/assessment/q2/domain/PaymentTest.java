package com.paypal.assessment.q2.domain;

import com.paypal.assessment.q2.exception.InvalidPaymentDataException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @Test
    void testPaymentCreation_ValidInputs() throws InvalidPaymentDataException {
        Payment payment = new Payment(100.0, "customer123", "PayPal");
        
        assertEquals(100.0, payment.getAmount());
        assertEquals("customer123", payment.getCustomerId());
        assertEquals("PayPal", payment.getMethod());
    }

    @Test
    void testPaymentCreation_InvalidAmount() {
        assertThrows(InvalidPaymentDataException.class, () -> 
            new Payment(-50.0, "customer123", "PayPal"));
    }

    @Test
    void testPaymentCreation_InvalidCustomerId() {
        assertThrows(InvalidPaymentDataException.class, () -> 
            new Payment(100.0, "", "PayPal"));
    }

    @Test
    void testPaymentCreation_InvalidMethod() {
        assertThrows(InvalidPaymentDataException.class, () -> 
            new Payment(100.0, "customer123", "Bitcoin"));
    }

    @Test
    void testCanUseInstallments_PayPal() throws InvalidPaymentDataException {
        Payment payment = new Payment(100.0, "customer123", "PayPal");
        assertTrue(payment.canUseInstallments());
    }

    @Test
    void testCanUseInstallments_CreditCard() throws InvalidPaymentDataException {
        Payment payment = new Payment(100.0, "customer123", "CreditCard");
        assertFalse(payment.canUseInstallments());
    }

    @Test
    void testCanEarnRewards_CreditCard() throws InvalidPaymentDataException {
        Payment payment = new Payment(100.0, "customer123", "CreditCard");
        assertTrue(payment.canEarnRewards());
    }

    @Test
    void testCanEarnRewards_PayPal() throws InvalidPaymentDataException {
        Payment payment = new Payment(100.0, "customer123", "PayPal");
        assertFalse(payment.canEarnRewards());
    }

    @Test
    void testCalculateInstallments() throws InvalidPaymentDataException {
        Payment payment = new Payment(100.0, "customer123", "PayPal");
        Payment.InstallmentResult result = payment.calculateInstallments(2, 0.05);
        
        assertEquals(50.0, result.getBaseAmount());
        assertEquals(52.5, result.getFinalAmount());
    }

    @Test
    void testCalculateRewards() throws InvalidPaymentDataException {
        Payment payment = new Payment(100.0, "customer123", "CreditCard");
        Payment.RewardResult result = payment.calculateRewards(0.10, 10.0);
        
        assertEquals(10.0, result.getRewardAmount());
        assertEquals(90.0, result.getRemainingAmount());
    }
}