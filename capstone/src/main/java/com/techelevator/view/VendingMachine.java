package com.techelevator.view;

import com.sun.jdi.InvalidTypeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.text.DateFormat;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;



public class VendingMachine {

    public static void main(String[] args) {
        File log = new File("log.txt");
       try( PrintWriter printWriter = new PrintWriter(new FileOutputStream (log, true))){


        Change change = new Change();
        File inventoryFile = new File("vendingmachine.csv");
        Map<String, Item> itemMap = new TreeMap<>();

        try (Scanner inventoryInput = new Scanner(inventoryFile)) {

            while (inventoryInput.hasNextLine()) {
                String currentLine = inventoryInput.nextLine();
                String[] currentArray = currentLine.split("\\|");
                BigDecimal priceToBigDecimal = new BigDecimal(currentArray[2]);
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


        // BEGIN VENDING MACHINE FUNCTION
        boolean onFirstMenu = true;
        BigDecimal moneyFed = new BigDecimal(0);
        System.out.println("Welcome to Vendo-matic 800! (Owned by Umbrella Corp)");
        while (onFirstMenu) {
            System.out.println();
            System.out.println("(1) Display Vending Machine Items\n(2) Purchase\n(3) Exit");
            Scanner scanner = new Scanner(System.in);
            String selection = scanner.nextLine();
            switch (selection) {
                case "3":
                    printWriter.flush();
                    printWriter.close();
                    System.exit(1);
                    break;
                case "1":
                    for (Map.Entry<String, Item> item : itemMap.entrySet()) {
                        System.out.println(item.getKey() + ": " + item.getValue().getName() + " " + item.getValue().getQuantity() + " remaining");
                    }
                    break;

                case "java-19":
                    System.out.println("***************** Welcome to the hidden menu *****************");
                    System.out.print("Are you ready to play?(y/n) ");
                    String readyToPlay = scanner.nextLine();
                    if(readyToPlay.equalsIgnoreCase("y")) {
                        System.out.println("When was Umbrella Corp. originally founded?");
                        String founded = scanner.nextLine();
                        if(founded.equalsIgnoreCase("1968")) {
                            System.out.println("Correct!");
                        } else {
                            System.out.println("Incorrect, Goodbye");
                            break;
                        }
                        System.out.println("Name the President and CEO of Umbrella Corp.");
                        String ceo = scanner.nextLine();
                        if (ceo.contains("Oswell")) {
                            System.out.println("Correct!");
                        } else {
                            System.out.println("Incorrect, Goodbye");
                            break;
                        }
                        System.out.println("What is the passcode? Hint: ");
                        String passcode = scanner.nextLine();
                        if(passcode.equalsIgnoreCase("8462")) {
                            System.out.println("Welcome to the Lab");
                            System.out.println("The Vending Machine has been adjusted");
                            moneyFed = new BigDecimal(999999999);
                            break;
                        }

                    } else if (readyToPlay.equalsIgnoreCase("n")) {
                        break;
                    }
                    System.out.println("Goodbye");
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
                                try {
                                    int moneyEntered = Integer.parseInt(scanner.nextLine());
                                    if (moneyEntered <= 0) {
                                        System.out.println("Invalid input. Money must be positive! ");
                                        System.out.println("Please try again. ");
                                        continue;
                                    }
                                    BigDecimal bd = new BigDecimal(moneyEntered);
                                    moneyFed = bd.add(moneyFed);
                                    DateFormat dateFormat = new SimpleDateFormat(" MM/dd/yyyy hh:mm:ss aa");
                                    String dateString = dateFormat.format(new Date()).toString();
                                    printWriter.println( dateString + " FEED MONEY $ " + moneyEntered  + " $ " + moneyFed);

                                    break;
                                } catch (Exception e) {
                                    System.out.println("Please enter a valid funds amount.");
                                    break;
                                }
                            case "2":

                                for (Map.Entry<String, Item> item : itemMap.entrySet()) {
                                    if (item.getValue().getQuantity() > 0) {
                                        System.out.println(item.getKey() + ": " + item.getValue().getName() + " $" + item.getValue().getPrice());
                                    }
                                }
                                System.out.println("Please enter which item you would like.");
                                String itemSelected = scanner.nextLine().toUpperCase();
                                if (!itemMap.containsKey(itemSelected)) {
                                    System.out.println("Invalid selection, please try again! ");
                                    System.out.println();
                                    continue;
                                } else if (itemMap.get(itemSelected).getQuantity() == 0) {
                                    System.out.println("Sorry, this item is SOLD OUT. Please try again!");
                                    System.out.println();
                                    continue;
                                } else if (moneyFed.compareTo(itemMap.get(itemSelected).getPrice()) == -1) {
                                    System.out.println("Funds insufficient. Please insert additional funds and try again! ");
                                    System.out.println();
                                    continue;

                                } else {
                                    System.out.println(itemMap.get(itemSelected).getName() + " $" + itemMap.get(itemSelected).getPrice() + " " + itemMap.get(itemSelected).getMessage());
                                    DateFormat dateFormat = new SimpleDateFormat(" MM/dd/yyyy hh:mm:ss aa");
                                    String dateString = dateFormat.format(new Date()).toString();
                                    printWriter.println( dateString + " " + itemMap.get(itemSelected).getName() +
                                            " " + itemSelected + " $" + moneyFed + " $" + moneyFed.subtract(itemMap.get(itemSelected).getPrice()) );

                                    moneyFed = moneyFed.subtract(itemMap.get(itemSelected).getPrice());
                                    itemMap.get(itemSelected).buyItem();

                                    break;
                                }
                                case "3":
                                    System.out.println(change.getChange(moneyFed));
                                    DateFormat dateFormat = new SimpleDateFormat(" MM/dd/yyyy hh:mm:ss aa");
                                    String dateString = dateFormat.format(new Date()).toString();
                                    printWriter.println( dateString + " GIVE CHANGE $ " + moneyFed + " $0.00");
                                    moneyFed = BigDecimal.ZERO;
                                    onSecondMenu = false;
                                    onFirstMenu = true;

                                    break;

                        }


                    }
            }
        }

    }
       catch (FileNotFoundException e){
        e.getMessage();

    }
    }

}
