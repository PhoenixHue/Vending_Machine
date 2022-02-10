package com.techelevator.view;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class ItemTest {
    private Item testItem;
    @Before
    public void setup() {
        this.testItem = new Item("CandyBar", new BigDecimal(500.50), "Candy");
    }

    @Test
    public void buyItem_reduces_quantity_by_one() {
        //Arrange

        //Act
        testItem.buyItem();
        int result = testItem.getQuantity();
        //Assert
        Assert.assertEquals(4, result);
    }

    @Test
    public void buyItem_constructor_works(){
        //Arrange
        //Act
        String name = testItem.getName();
        String message = testItem.getMessage();
        BigDecimal price = testItem.getPrice();
        BigDecimal expectedPrice = new BigDecimal(500.50);
        //Assert
        Assert.assertEquals("Name input is incorrect","CandyBar", name);
    Assert.assertEquals("Message is incorrect", "Munch Munch, Yum!", message );
    Assert.assertEquals("Price is wrong", expectedPrice, price );
    }
}