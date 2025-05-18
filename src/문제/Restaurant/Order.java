package 문제.Restaurant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    public String name;
    public int price;
    public LocalDateTime orderTime;

    public Order(String name, int price, LocalDateTime orderTime){
        this.name = name;
        this.price = price;
        this.orderTime = LocalDateTime.now();
    }

    public String getName(){return name;}
    public int getPrice(){return price;}
    public LocalDateTime getOrderTime(){return orderTime;}

    @Override
    public String toString(){

        return String.format("%s:%d:%s", name, price
                ,orderTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
        );
    }

}



