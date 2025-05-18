package project.CoffeeShop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Customer {

    public static final Random r = new Random();
    public Weather weather;

    public Customer(Weather weather) {
        this.weather = weather;

    }

    public List<MenuItem> order(List<MenuItem> availableMenu) {
        List<MenuItem> order = new ArrayList<>();

        if (availableMenu.isEmpty()) {
            return order;
        }


        // 음료와 음식 분리
        List<MenuItem> coldDrinks = new ArrayList<>();
        List<MenuItem> hotDrinks = new ArrayList<>();
        List<MenuItem> foods = new ArrayList<>();

        for (MenuItem item : availableMenu) {
            if (item.type == MenuItem.DRINK) {

                // 음료 이름에 따라 분류
                if(item.name.contains("아이스")|| item.name.contains("에이드")||item.name.contains("콜드")){
                    coldDrinks.add(item);
                } else{
                    hotDrinks.add(item);
                }


            } else if (item.type == MenuItem.FOOD) {
                foods.add(item);
            }
        }

       //날씨 기반 음료 선택
        double drinkChance = r.nextDouble();

        //뜨거운 음료 선택
        if(drinkChance< weather.hotDrinkPreference && !hotDrinks.isEmpty()){
            order.add(hotDrinks.get(r.nextInt(hotDrinks.size())));
        }
        //차가운 음료 선택
        if(drinkChance< weather.hotDrinkPreference+ weather.coldDrinkPreference && !coldDrinks.isEmpty()){
            order.add(coldDrinks.get(r.nextInt(coldDrinks.size())));
        }

        //음식 선택
        if(r.nextDouble()< weather.foodPreference && !foods.isEmpty()){
            order.add(foods.get(r.nextInt(foods.size())));
        }


        return order;
    }
}