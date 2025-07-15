# PayPal System Design
## Q3. Payment Service 
This repository demonstrates a simple payment processing system in Java, inspired by PayPal features. It supports:
## How to Run

1. **Clone the repo and switch to the strategy branch:**
   ```bash
   git clone https://github.com/bhaktivora9/paypal-system-design.git
   cd paypal-system-design
   git checkout strategy
   ```
2. **Build:**
   ```bash
   ./mvnw clean install
   ```
3. **Compile:**
   ```bash
./mvnw clean compile
   ```
   
   
4. **Run:**
   ```bash
./mvnw exec:java -Dexec.mainClass="com.paypal.payment.Solution"
   ```

## Extending Payment Methods

To add a new payment method:

1. Implement a new class in the `strategy/` folder following the Strategy pattern.
