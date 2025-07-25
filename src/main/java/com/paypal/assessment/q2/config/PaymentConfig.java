package com.paypal.assessment.q2.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PaymentConfig {
    private final Properties properties;
    
    public PaymentConfig() {
        properties = new Properties();
        loadProperties();
    }
    
    private void loadProperties() {
        try (InputStream input = PaymentConfig.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            // Fall back to default values if properties file cannot be loaded
            System.err.println("Warning: Could not load application.properties, using default values");
        }
    }
    
    public double getRewardPercentage() {
        return Double.parseDouble(properties.getProperty("payment.reward.percentage", "0.10"));
    }

    public double getMaxReward() {
        return Double.parseDouble(properties.getProperty("payment.reward.max", "10.0"));
    }

    public int getInstallmentCount() {
        return Integer.parseInt(properties.getProperty("payment.installment.count", "2"));
    }

    public double getInterestRate() {
        return Double.parseDouble(properties.getProperty("payment.installment.interestRate", "0.05"));
    }

    public double getMinimumAmount() {
        return Double.parseDouble(properties.getProperty("payment.minimum.amount", "0.01"));
    }

    public double getLimit() {
        return Double.parseDouble(properties.getProperty("payment.daily.limit", "10000.0"));
    }
    
    public boolean isValidationEnabled() {
        return Boolean.parseBoolean(properties.getProperty("payment.validation.enabled", "true"));
    }
    
    public boolean isAuditEnabled() {
        return Boolean.parseBoolean(properties.getProperty("payment.audit.enabled", "true"));
    }
}