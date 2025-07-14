## PayPal System Design
### Q3. Payment Service 
This repository demonstrates a simple payment processing system in Java, inspired by PayPal features. It supports:

- **Installment Payments**: Split PayPal payments into two installments with interest.
- **Reward Points**: Earn and redeem reward points for eligible credit card payments.
- **Configurable Rules**: Easily adjust reward rates, interest, and limits in the code.


### File Overview

- `Payment.java` – Handles business logic for payments, installments, and rewards.
- `PaymentConfig.java` – Sets rules for rewards, limits, and interest.
- `PaymentService.java` – Processes each payment.
- `Solution.java` – Runs the program from the command line.
- `Utils.java` – Helper formatting method.
### Prerequisites

- Java 8 or above.

### Running the Program

1. Compile the Java source files:
   ```sh
   javac src/com/paypal/assessment/q2/**/*.java
   ```
2. Run the main class:
   ```sh
   java com.paypal.assessment.q2.Solution
   ```

3. Follow the CLI prompts:
   - Enter the number of payments to process.
   - For each payment, enter the payment method and amount, e.g.:
     ```
     PayPal 120.00
     CreditCard 50.00
     ```

### Example Output

```
Paying $120.00 via PayPal using Installment Payment Plan.
Paid $60.00 in first installment.
Paid $63.00 in second installment with 5% interest.

Paying $50.00 via CreditCard using Reward Points Redemption Feature.
Redeemed $5.00 using reward points.
Paying remaining amount of $45.00 via credit card.
```
