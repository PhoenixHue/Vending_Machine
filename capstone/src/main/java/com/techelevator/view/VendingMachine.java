package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {

    public static void main(String[] args) {
        File inventoryFile = new File("vendingmachine.csv");
        Map<String, Item> itemMap = new HashMap<>();


        try(Scanner inventoryInput = new Scanner(inventoryFile)) {

            while(inventoryInput.hasNextLine()) {
                String currentLine = inventoryInput.nextLine();
                String[] currentArray = currentLine.split("\\|");
                BigDecimal priceToBigDecimal =  new BigDecimal(currentArray[2]);
                itemMap.put(currentArray[0], new Item(currentArray[1], priceToBigDecimal, currentArray[3]));
            }

        } catch (FileNotFoundException e) {
            System.out.println("No inventory found");
            System.exit(1);
        }




    }

}
