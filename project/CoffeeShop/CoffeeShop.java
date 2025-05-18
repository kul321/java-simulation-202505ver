package project.CoffeeShop;

import java.util.ArrayList;
import java.util.List;

public class CoffeeShop {
    public int money;
    public List<MenuItem> menuItems;
    public List<Ingredient> ingredients;
    public int dailyRevenue;
    public int totalRevenue;

    public CoffeeShop(){
        money = 50000; //초기 자금: 50000
        menuItems = new ArrayList<>();
        ingredients = new ArrayList<>();
        dailyRevenue = 0;
        totalRevenue = 0;
    }


    //일수입초기화
    public void resetDailyRevenue() {
        dailyRevenue = 0;
    }

    //메뉴 삭제
    public void removeMenuItem(int index) {
        if (index >= 1 && index <= menuItems.size()) {
            MenuItem removed = menuItems.remove(index - 1);
            System.out.println(removed.name + " 메뉴가 삭제되었습니다.");
        } else {
            System.out.println("잘못된 번호입니다.");
        }
    }

    //메뉴 추가
    public void addMenuItem(MenuItem item){
        menuItems.add(item);
    }

    //재료 추가
    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
    }

    //메뉴 출력
    public void showMenu() {
        System.out.println("=== 메뉴 목록 ===");
        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem item = menuItems.get(i);

            // 해금된 메뉴만 상세 정보 표시 / 해금되지 않은 메뉴는 ??? 로 표시
            if (item.unlocked) {
                System.out.println((i + 1) + ". " + item.name + " " + item.price + "원" + " [" + item.quality + "]");

                // 레시피 출력
                System.out.println("   필요 원재료: ");
                for (RecipeItem recipeItem : item.recipe.recipeItems) {
                    System.out.println("   - " + recipeItem.ingredientName + ": " + recipeItem.quantity + "개");
                }
            } else {
                System.out.println((i + 1) + ". ??? (연구를 통해 해금 필요)");
            }
        }
    }

    //재료 출력
    public void showIngredients(){
        System.out.println("=== 재료 목록 ===");
        for (int i = 0; i < ingredients.size(); i++) {
            Ingredient ing = ingredients.get(i);
            System.out.println((i + 1) + ". " + ing.name + " - 재고: " + ing.quantity + "개, 가격: " + ing.price + "원/개");
        }

    }

    //주문 진행
    public boolean processOrder(List<MenuItem> order) {
        // 재료가 충분한지 확인
        if (!checkIngredients(order)) {
            return false;
        }

        // 재료 소비 및 판매
        for (MenuItem item : order) {
            consumeIngredients(item);

            // 판매 금액 추가
            money += item.price;
            dailyRevenue += item.price;
            totalRevenue += item.price;
        }

        return true;
    }


    //재료 확인
    public boolean checkIngredients(List<MenuItem> order) {
        for (MenuItem item : order) {
            for (RecipeItem recipeItem : item.recipe.recipeItems) {
                boolean found = false;
                for (Ingredient ing : ingredients) {
                    if (ing.name.equals(recipeItem.ingredientName)) {
                        if (ing.quantity < recipeItem.quantity) {
                            return false;  // 재료 부족
                        }
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    return false;  // 필요한 재료가 없음
                }
            }
        }
        return true;
    }

    //재료 소비
   public void consumeIngredients(MenuItem item) {
        for (RecipeItem recipeItem : item.recipe.recipeItems) {
            for (Ingredient ing : ingredients) {
                if (ing.name.equals(recipeItem.ingredientName)) {
                    ing.consumeQuantity(recipeItem.quantity);
                    break;
                }
            }
        }
    }
}
