package project.CoffeeShop;

public class Ingredient {
    public String name;
    public int quantity;
    public double price;  // 1개당 가격

    public Ingredient(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public void addQuantity(int amount) {
        quantity += amount;
    }

    public void consumeQuantity(int amount) {
        quantity -= amount;
        if (quantity < 0) quantity = 0;
    }
}
