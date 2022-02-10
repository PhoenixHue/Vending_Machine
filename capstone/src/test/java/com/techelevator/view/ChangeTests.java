package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class ChangeTests {

    Change change = new Change();


    @Test
    public void getChange_for_69_dollars() {
        //Arrange
        BigDecimal test = new BigDecimal(69.00);
        //Act
        String result = change.getChange(test);
        //Assert
        Assert.assertEquals("Change : 276 Quarters, 0 Dimes, 0 Nickels.", result);

    }
    @Test
    public void getChange_for_2_dollars_sixty_five_cents() {
        //Arrange
        BigDecimal test = new BigDecimal(2.65);
        //Act
        String result = change.getChange(test);
        //Assert
        Assert.assertEquals("Change : 10 Quarters, 1 Dimes, 1 Nickels.", result);

    }

    @Test
    public void getChange_for_five_cents() {
        //Arrange
        BigDecimal test = new BigDecimal(0.05);
        //Act
        String result = change.getChange(test);
        //Assert
        Assert.assertEquals("Change : 0 Quarters, 0 Dimes, 1 Nickels.", result);

    }
    @Test
    public void getChange_for_fifteen_cents() {
        //Arrange
        BigDecimal test = new BigDecimal(0.15);
        //Act
        String result = change.getChange(test);
        //Assert
        Assert.assertEquals("Change : 0 Quarters, 1 Dimes, 1 Nickels.", result);

    }

    @Test
    public void getChange_for_2_dollars_fifty_five_cents() {
        //Arrange
        BigDecimal test = new BigDecimal(2.55);
        //Act
        String result = change.getChange(test);
        //Assert
        Assert.assertEquals("Change : 10 Quarters, 0 Dimes, 1 Nickels.", result);

    }







}
