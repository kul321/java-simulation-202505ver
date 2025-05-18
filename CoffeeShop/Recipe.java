package project.CoffeeShop;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    public List<RecipeItem> recipeItems;

    public Recipe() {
        recipeItems = new ArrayList<>();
    }

    public void addIngredient(String name, int quantity) {
        // 같은 재료가 있는지 확인
        for (RecipeItem item : recipeItems) {
            if (item.ingredientName.equals(name)) {
                // 이미 있는 재료면 수량 증가
                item.quantity += quantity;
                return;
            }
        }
        // 새 재료면 추가
        recipeItems.add(new RecipeItem(name, quantity));
    }

}
