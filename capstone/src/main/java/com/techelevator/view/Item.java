package com.techelevator.view;

import java.math.BigDecimal;

public class Item {


    private String name;
    private BigDecimal price;
    private int quantity  ;
    private String type;


    public Item(String name, BigDecimal price, String type){
        this.name = name;
        this.price = price;
        this.quantity= 5;
        this.type =type;


    }
    public void buyItem (){

        this.quantity-= 1;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }


    public int getQuantity() {
        return quantity;
    }
    public String getMessage(){
        if( type.equals("Chip")){
            return " Crunch Crunch, Yum!";
        }
        else if( type.equals("Candy")){
            return "Munch Munch, Yum!";
        }
        else if ( type. equals( "Drink")){
            return "Glug Glug, Yum!";


        }
        return "Chew Chew, Yum!";
        

    }


}
