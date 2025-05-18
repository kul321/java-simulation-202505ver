package 문제.Restaurant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Menu {
    public String name;
    public int price;


    public Menu(String name, int price){
        this.name = name;
        this.price = price;

    }

    public String getName(){return name;}
    public int getPrice(){return price;}


    @Override
    public String toString(){

        return String.format("%s:%d", name, price
               );
    }



}
