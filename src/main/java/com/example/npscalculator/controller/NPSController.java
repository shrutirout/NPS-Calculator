package com.example.npscalculator.controller;

import com.example.npscalculator.model.NPSCalculation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

@Controller
public class NPSController {

    @GetMapping("/")
    public String showForm() {
        return "calculator";
    }

    @PostMapping("/calculate")
    public String calculate(
            @RequestParam("monthlyInvestment") double monthlyInvestment,
            @RequestParam("expectedReturnRate") double expectedReturnRate,
            @RequestParam("age") int age,
            Model model) {

        NPSCalculation calculation = new NPSCalculation();
        calculation.setMonthlyInvestment(monthlyInvestment);
        calculation.setExpectedReturnRate(expectedReturnRate);
        calculation.setAge(age);
        calculation.calculate();

        // Format the numbers before passing them to the model
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setGroupingUsed(true);

        // Round off the calculated values to two decimal places
        double totalInvestment = roundToTwoDecimals(calculation.getTotalInvestment());
        double investmentEarned = roundToTwoDecimals(calculation.getInvestmentEarned());
        double maturityAmount = roundToTwoDecimals(calculation.getMaturityAmount());
        double minAnnuityInvestment = roundToTwoDecimals(calculation.getMinAnnuityInvestment());

        model.addAttribute("totalInvestment", numberFormat.format(totalInvestment));
        model.addAttribute("investmentEarned", numberFormat.format(investmentEarned));
        model.addAttribute("maturityAmount", numberFormat.format(maturityAmount));
        model.addAttribute("minAnnuityInvestment", numberFormat.format(minAnnuityInvestment));

        return "result";
    }

    // Helper method to round off to two decimal places
    private double roundToTwoDecimals(double value) {
        return BigDecimal.valueOf(value)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
