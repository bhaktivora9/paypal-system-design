### SYSTEM-DESIGN: PAYMENT-SERVICE

### File Overview
- `Payment.java` – Handles business logic for payments, installments, and rewards.
- `PaymentConfig.java` – Sets rules for rewards, limits, and interest.
- `PaymentService.java` – Processes each payment.
- `Solution.java` – Runs the program from the command line.
- `Utils.java` – Helper formatting method.
### Prerequisites

- Java 8 or above.

### Running the Program

### How to RUN this : 

```bash
> mvn install
> mvn clean compile
> mvn exec:java -Dexec.mainClass="com.paypal.assessment.q2.Solution"
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
