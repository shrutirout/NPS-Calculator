// src/main/java/com/example/npscalculator/model/NPSCalculation.java
package com.example.npscalculator.model;

public class NPSCalculation {
    private double monthlyInvestment;
    private double expectedReturnRate;
    private int age;
    private double totalInvestment;
    private double maturityAmount;
    private double investmentEarned;
    private double minAnnuityInvestment;

    // Getters and setters for all fields

    public double getMonthlyInvestment() {
        return monthlyInvestment;
    }

    public void setMonthlyInvestment(double monthlyInvestment) {
        this.monthlyInvestment = monthlyInvestment;
    }

    public double getExpectedReturnRate() {
        return expectedReturnRate;
    }

    public void setExpectedReturnRate(double expectedReturnRate) {
        this.expectedReturnRate = expectedReturnRate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getTotalInvestment() {
        return totalInvestment;
    }

    public double getMaturityAmount() {
        return maturityAmount;
    }

    public double getInvestmentEarned() {
        return investmentEarned;
    }

    public double getMinAnnuityInvestment() {
        return minAnnuityInvestment;
    }

    // Method to perform calculations
    public void calculate() {
        int tenure = 60 - age; // Assuming retirement age is 60
        totalInvestment = monthlyInvestment * 12 * tenure;

        double r = expectedReturnRate / 100;
        double n = 12; // Compounded monthly
        double t = tenure;

        // Compound interest formula applied to each monthly contribution
        maturityAmount = monthlyInvestment * ((Math.pow(1 + r / n, n * t) - 1) / (r / n));

        investmentEarned = maturityAmount - totalInvestment;
        minAnnuityInvestment = maturityAmount * 0.4;
    }
}

