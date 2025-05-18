package project.CoffeeShop;

public class MenuItem {

    public static final int DRINK = 0;
    public static final int FOOD = 1;

    public String name;
    public int price;
    public int type;  // 0: 음료, 1: 음식
    public String typeName;
    public Recipe recipe;
    public String quality; // 나쁨, 보통, 좋음, 매우 좋음
    public boolean unlocked; // 메뉴 해금 여부

    public MenuItem(String name, int price, int type, String typeName, Recipe recipe, String quality, boolean unlocked) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.typeName = typeName;
        this.recipe = recipe;
        this.quality = quality;
        this.unlocked = unlocked;
    }


}
