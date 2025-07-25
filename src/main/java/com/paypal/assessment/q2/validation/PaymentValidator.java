package com.paypal.assessment.q2.validation;

import com.paypal.assessment.q2.enums.PaymentMethod;
import com.paypal.assessment.q2.exception.InvalidPaymentDataException;

/**
 * Utility class for validating payment data
 */
public class PaymentValidator {
    
    private static final double MIN_AMOUNT = 0.01;
    private static final double MAX_AMOUNT = 100000.00;
    private static final int MAX_CUSTOMER_ID_LENGTH = 50;
    
    /**
     * Validate payment amount
     * @param amount the amount to validate
     * @throws InvalidPaymentDataException if amount is invalid
     */
    public static void validateAmount(double amount) throws InvalidPaymentDataException {
        if (amount <= 0) {
            throw new InvalidPaymentDataException("Payment amount must be positive");
        }
        if (amount < MIN_AMOUNT) {
            throw new InvalidPaymentDataException(
                String.format("Payment amount must be at least $%.2f", MIN_AMOUNT));
        }
        if (amount > MAX_AMOUNT) {
            throw new InvalidPaymentDataException(
                String.format("Payment amount cannot exceed $%.2f", MAX_AMOUNT));
        }
        if (Double.isNaN(amount) || Double.isInfinite(amount)) {
            throw new InvalidPaymentDataException("Payment amount must be a valid number");
        }
    }
    
    /**
     * Validate customer ID
     * @param customerId the customer ID to validate
     * @throws InvalidPaymentDataException if customer ID is invalid
     */
    public static void validateCustomerId(String customerId) throws InvalidPaymentDataException {
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new InvalidPaymentDataException("Customer ID cannot be null or empty");
        }
        if (customerId.length() > MAX_CUSTOMER_ID_LENGTH) {
            throw new InvalidPaymentDataException(
                String.format("Customer ID cannot exceed %d characters", MAX_CUSTOMER_ID_LENGTH));
        }
        // Basic sanitization - check for potentially harmful characters
        if (containsSqlInjectionPatterns(customerId) || containsScriptInjectionPatterns(customerId)) {
            throw new InvalidPaymentDataException("Customer ID contains invalid characters");
        }
    }
    
    /**
     * Validate payment method
     * @param method the payment method to validate
     * @throws InvalidPaymentDataException if payment method is invalid
     */
    public static void validatePaymentMethod(String method) throws InvalidPaymentDataException {
        if (method == null || method.trim().isEmpty()) {
            throw new InvalidPaymentDataException("Payment method cannot be null or empty");
        }
        if (!PaymentMethod.isValid(method)) {
            throw new InvalidPaymentDataException(
                String.format("Payment method '%s' is not supported. Supported methods: %s", 
                            method, getSupportedMethods()));
        }
    }
    
    /**
     * Get list of supported payment methods as string
     * @return comma-separated list of supported methods
     */
    private static String getSupportedMethods() {
        StringBuilder sb = new StringBuilder();
        for (PaymentMethod method : PaymentMethod.values()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(method.getMethodName());
        }
        return sb.toString();
    }
    
    /**
     * Check for basic SQL injection patterns
     * @param input the input to check
     * @return true if suspicious patterns found
     */
    private static boolean containsSqlInjectionPatterns(String input) {
        String lowerInput = input.toLowerCase();
        return lowerInput.contains("'") || 
               lowerInput.contains("--") || 
               lowerInput.contains("/*") || 
               lowerInput.contains("*/") ||
               lowerInput.contains("select") ||
               lowerInput.contains("insert") ||
               lowerInput.contains("update") ||
               lowerInput.contains("delete") ||
               lowerInput.contains("drop");
    }
    
    /**
     * Check for basic script injection patterns
     * @param input the input to check
     * @return true if suspicious patterns found
     */
    private static boolean containsScriptInjectionPatterns(String input) {
        String lowerInput = input.toLowerCase();
        return lowerInput.contains("<script") || 
               lowerInput.contains("javascript:") || 
               lowerInput.contains("onload=") ||
               lowerInput.contains("onerror=");
    }
}