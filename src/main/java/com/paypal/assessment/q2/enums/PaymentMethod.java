package com.paypal.assessment.q2.enums;

/**
 * Enumeration of supported payment methods
 */
public enum PaymentMethod {
    PAYPAL("PayPal", "PayPal account payment"),
    CREDIT_CARD("CreditCard", "Credit card payment"),
    DEBIT_CARD("DebitCard", "Debit card payment"),
    BANK_TRANSFER("BankTransfer", "Bank transfer payment");
    
    private final String methodName;
    private final String description;
    
    PaymentMethod(String methodName, String description) {
        this.methodName = methodName;
        this.description = description;
    }
    
    public String getMethodName() {
        return methodName;
    }
    
    public String getDescription() {
        return description;
    }
    
    /**
     * Parse payment method from string
     * @param methodName the method name string
     * @return PaymentMethod enum or null if not found
     */
    public static PaymentMethod fromString(String methodName) {
        if (methodName == null || methodName.trim().isEmpty()) {
            return null;
        }
        
        for (PaymentMethod method : PaymentMethod.values()) {
            if (method.methodName.equalsIgnoreCase(methodName.trim())) {
                return method;
            }
        }
        return null;
    }
    
    /**
     * Check if a payment method string is valid
     * @param methodName the method name to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValid(String methodName) {
        return fromString(methodName) != null;
    }
}