package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

public class VendingMachine {

    public static void main(String[] args) {
        File inventoryFile = new File("vendingmachine.csv");
        Map<String, Item> itemMap = new TreeMap<>();

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




       /* if (selection.equals("3")) {
            System.exit(1);
        }
        else if (selection.equals("1")) {
            for (Map.Entry<String, Item> item : itemMap.entrySet()) {
                System.out.println(item.getKey() + ": " + item.getValue().getName() + " " + item.getValue().getQuantity());
            }
        } */

        boolean onFirstMenu = true;
        BigDecimal moneyFed = new BigDecimal(0);
        System.out.println("Welcome to Vendomatic 800! (Owned by Umbrella Corp)");
        while (onFirstMenu) {
            System.out.println();
        System.out.println("(1) Display Vending Machine Items\n(2) Purchase\n(3) Exit");
        Scanner scanner = new Scanner(System.in);
        String selection = scanner.nextLine();
        switch (selection) {
            case "3":
                System.exit(1);
                break;
            case "1":
                for (Map.Entry<String, Item> item : itemMap.entrySet()) {
                    System.out.println(item.getKey() + ": " + item.getValue().getName() + " " + item.getValue().getQuantity() + " remaining");
                }
                break;
            case "2":
                onFirstMenu = false;
                boolean onSecondMenu = true;
                while (onSecondMenu) {
                System.out.println("(1) Feed Money\n(2) Select Product\n(3) Finish Transaction\n\n Current Money Provided: $" + moneyFed);
                String selection2 = scanner.nextLine();
                switch (selection2) {
                    case "1":
                        System.out.println("How much money would you like to enter?");
                        int moneyEntered = Integer.parseInt(scanner.nextLine());
                        BigDecimal bd = new BigDecimal(moneyEntered);
                        moneyFed = bd.add(moneyFed);
                        break;
                    case "2":

                        for (Map.Entry<String, Item> item : itemMap.entrySet()) {
                            if (item.getValue().getQuantity() > 0) {
                                System.out.println(item.getKey() + ": " + item.getValue().getName() + " $" + item.getValue().getPrice());
                            }
                        }
                        System.out.println("Please enter which item you would like.");
                        String itemSelected = scanner.nextLine();
                        System.out.println(itemMap.get(itemSelected).getName() + " $" + itemMap.get(itemSelected).getPrice() + " " + itemMap.get(itemSelected).getMessage());
                        moneyFed = moneyFed.subtract(itemMap.get(itemSelected).getPrice());
                        itemMap.get(itemSelected).buyItem();
                }

                }
        }
        }







    }

}
