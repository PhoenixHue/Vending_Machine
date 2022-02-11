package com.techelevator.view;

import java.math.BigDecimal;

public class Change {
    private int quarters;
    private int dimes;
    private int nickels;

    public String getChange(BigDecimal moneyFed) {
        double moneyFedInt = Math.ceil(moneyFed.doubleValue() * 100.00);
        this.quarters = (int)(moneyFedInt / 25.00);
        double remainingChange = (moneyFedInt % 25.00);
        this.dimes = (int)(remainingChange / 10.00);
        remainingChange = remainingChange % 10.00;
        this.nickels = (int)(remainingChange / 5.00);

        // no pennies allowed welcome to sweden

        return "Change : " + quarters + " Quarters, " + dimes + " Dimes, " + nickels + " Nickels.";
    }



}
