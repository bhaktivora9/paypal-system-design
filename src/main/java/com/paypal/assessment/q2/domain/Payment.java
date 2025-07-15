package com.paypal.assessment.q2.domain;

public class Payment {
	private final double amount;
	private final String customerId;
	private final String method;

	public Payment(double amount, String customerId, String method) {
		this.amount = amount;
		this.customerId = customerId;
		this.method = method;
	}

	// DOMAIN METHODS - Core Business Logic

	public boolean canUseInstallments() {
		return amount > 0 && "PayPal".equals(method);
	}

	public boolean canEarnRewards() {
		return amount >= 1.0 && "CreditCard".equals(method);
	}

	public InstallmentResult calculateInstallments(int count, double interestRate) {
		if (!canUseInstallments()) {
			throw new IllegalStateException("Payment not eligible for installments");
		}

		double baseAmount = amount / count;
		double finalAmount = baseAmount * (1 + interestRate);

		return new InstallmentResult(baseAmount, finalAmount);
	}

	public RewardResult calculateRewards(double percentage, double maxReward) {
		if (!canEarnRewards()) {
			return new RewardResult(0.0, amount);
		}

		double rewardAmount = Math.min(amount * percentage, maxReward);
		double remainingAmount = amount - rewardAmount;

		return new RewardResult(rewardAmount, remainingAmount);
	}

	public boolean meetsMinimumAmount(double minimum) {
		return amount >= minimum;
	}

	public boolean exceedsDailyLimit(double currentSpending, double dailyLimit) {
		return (currentSpending + amount) > dailyLimit;
	}

	// Getters
	public double getAmount() {
		return amount;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getMethod() {
		return method;
	}

	// Helper method for formatting
	private static String formatCurrency(double amount) {
		return String.format("%.2f", amount);
	}

	public static class InstallmentResult {
		private final double baseAmount;
		private final double finalAmount;

		public InstallmentResult(double baseAmount, double finalAmount) {
			this.baseAmount = baseAmount;
			this.finalAmount = finalAmount;
		}

		public double getBaseAmount() {
			return baseAmount;
		}

		public double getFinalAmount() {
			return finalAmount;
		}

		public String getBaseAmountFormatted() {
			return formatCurrency(baseAmount);
		}

		public String getFinalAmountFormatted() {
			return formatCurrency(finalAmount);
		}
	}

	public static class RewardResult {
		private final double rewardAmount;
		private final double remainingAmount;

		public RewardResult(double rewardAmount, double remainingAmount) {
			this.rewardAmount = rewardAmount;
			this.remainingAmount = remainingAmount;
		}

		public double getRewardAmount() {
			return rewardAmount;
		}

		public double getRemainingAmount() {
			return remainingAmount;
		}

		public String getRewardAmountFormatted() {
			return formatCurrency(rewardAmount);
		}

		public String getRemainingAmountFormatted() {
			return formatCurrency(remainingAmount);
		}
	}
}