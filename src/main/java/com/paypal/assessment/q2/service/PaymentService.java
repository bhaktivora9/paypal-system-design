package com.paypal.assessment.q2.service;

import com.paypal.assessment.q2.config.PaymentConfig;
import com.paypal.assessment.q2.domain.Payment;
import com.paypal.assessment.q2.exception.InvalidPaymentDataException;
import com.paypal.assessment.q2.exception.UnsupportedPaymentMethodException;
import com.paypal.assessment.q2.strategy.PaymentProcessor;
import com.paypal.assessment.q2.strategy.PaymentResult;
import com.paypal.assessment.q2.strategy.impl.CreditCardProcessor;
import com.paypal.assessment.q2.strategy.impl.PayPalProcessor;

import java.util.ArrayList;
import java.util.List;

public class PaymentService {
    private final PaymentConfig config;
    private final List<PaymentProcessor> processors;
    
    public PaymentService(PaymentConfig config) {
        this.config = config;
        this.processors = new ArrayList<>();
        initializeProcessors();
    }
    
    private void initializeProcessors() {
        processors.add(new PayPalProcessor(config));
        processors.add(new CreditCardProcessor(config));
    }
    
    public void processPayment(String method, double amount, String customerId) {
        try {
            Payment payment = new Payment(amount, customerId, method);
            
            PaymentProcessor processor = findProcessor(method);
            if (processor == null) {
                throw new UnsupportedPaymentMethodException(method);
            }
            
            PaymentResult result = processor.processPayment(payment);
            
            if (result.isSuccess()) {
                System.out.println(result.getMessage());
                System.out.println(result.getDetails());
            } else {
                System.err.println("Payment failed: " + result.getMessage());
                System.err.println("Details: " + result.getDetails());
            }
            
        } catch (InvalidPaymentDataException e) {
            System.err.println("Invalid payment data: " + e.getMessage());
        } catch (UnsupportedPaymentMethodException e) {
            System.err.println("Unsupported payment method: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error during payment processing: " + e.getMessage());
        }
    }
    
    private PaymentProcessor findProcessor(String method) {
        return processors.stream()
                        .filter(processor -> processor.supports(method))
                        .findFirst()
                        .orElse(null);
    }
    
    /**
     * Get list of supported payment methods
     * @return list of supported methods
     */
    public List<String> getSupportedMethods() {
        List<String> methods = new ArrayList<>();
        for (PaymentProcessor processor : processors) {
            methods.add(processor.getProcessorName());
        }
        return methods;
    }
}