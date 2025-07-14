package com.paypal.assessment.q2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.paypal.assessment.q2.config.PaymentConfig;
import com.paypal.assessment.q2.service.PaymentService;


public class Solution {
    
    /**
     * Process multiple payments using domain-driven approach
     */
    public static void processPayments(List<String> methods, List<Double> amounts) {
        PaymentService service = new PaymentService(new PaymentConfig());
        
        for (int i = 0; i < methods.size(); i++) {
            service.processPayment(methods.get(i), amounts.get(i), "customer_" + i);
            
            // Add spacing between payments
            if (i < methods.size() - 1) {
                System.out.println();
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(reader.readLine().trim());
        List<String> methods = new ArrayList<>();
        List<Double> amounts = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            String[] line = reader.readLine().trim().split(" ");
            methods.add(line[0]);
            amounts.add(Double.parseDouble(line[1]));
        }
        
        processPayments(methods, amounts);
        reader.close();
    }
}