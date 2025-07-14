package com.paypal.payment.util;

/**
 * Utility class for common formatting operations
 * 
 * Single Responsibility Principle:
 * - Only responsible for currency formatting
 * 
 * ASSUMPTION: All amounts are in USD with 2 decimal places
 */
public class Utils {
    
    /**
     * Formats currency amounts for display while preserving calculation precision
     * 
     * @param amount The amount to format
     * @return Formatted currency string with 2 decimal places
     */
    public static String roundDouble(double amount) {
        return String.format("%.2f", amount);
    }
}
