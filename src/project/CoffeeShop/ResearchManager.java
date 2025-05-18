
package project.CoffeeShop;

import java.util.*;

public class ResearchManager {
    public List<Research> availableRecipe;
    public List<Research> activeRecipe;
    public List<Research> completedRecipe;
    public int researchPoints;
    public static final Random r = new Random();

    // 메뉴 타입
    public static final String[] DRINK_TYPES = {"커피", "논커피", "차"};
    public static final String[] FOOD_TYPES = {"샌드위치", "케이크", "핫도그", "베이글", "디저트"};

    // 품질 확률 (나쁨:20%, 보통:50%, 좋음:25%, 매우 좋음:5%)
    public static final double[] QUALITY_CHANCES = {0.2, 0.5, 0.25, 0.05};
    public static final String[] QUALITIES = {MenuQuality.BAD, MenuQuality.NORMAL, MenuQuality.GOOD, MenuQuality.EXCELLENT};


    //재료 카테고리
    public static final Map<String, List<String>> INGREDIENT_CATEGORIES = new HashMap<>();

    public ResearchManager() {
        availableRecipe = new ArrayList<>();
        activeRecipe = new ArrayList<>();
        completedRecipe = new ArrayList<>();
        researchPoints = 0;

        // 재료 카테고리 초기화
        initializeIngredientCategories();

        // 초기 연구 설정
        initializeResearch();
    }
    // 매일 수익에 기반한 연구 포인트 추가
    public void addDailyResearchPoints(int dailyRevenue) {
        // 일일 수익의 5%를 연구 포인트로 변환 (최소 5포인트 보장)
        int newPoints = Math.max(5, (int)(dailyRevenue * 0.05));
        researchPoints += newPoints;
        System.out.println("일일 수익 기반 연구 포인트 " + newPoints + " 획득! (현재: " + researchPoints + ")");
    }

    // 재료 카테고리 초기화 메서드
    public void initializeIngredientCategories() {
        // 커피 관련 재료
        List<String> coffeeIngredients = new ArrayList<>();
        coffeeIngredients.add("커피콩");
        coffeeIngredients.add("에스프레소원두");
        coffeeIngredients.add("바닐라시럽");
        INGREDIENT_CATEGORIES.put("커피", coffeeIngredients);

        // 차 관련 재료
        List<String> teaIngredients = new ArrayList<>();
        teaIngredients.add("차");
        teaIngredients.add("녹차가루");
        INGREDIENT_CATEGORIES.put("차", teaIngredients);

        // 논커피 음료 관련 재료
        List<String> nonCoffeeIngredients = new ArrayList<>();
        nonCoffeeIngredients.add("레몬");
        nonCoffeeIngredients.add("초콜릿");
        INGREDIENT_CATEGORIES.put("논커피", nonCoffeeIngredients);

        // 샌드위치 관련 재료
        List<String> sandwichIngredients = new ArrayList<>();
        sandwichIngredients.add("빵");
        sandwichIngredients.add("치즈");
        sandwichIngredients.add("버터");
        INGREDIENT_CATEGORIES.put("샌드위치", sandwichIngredients);

        // 핫도그 관련 재료
        List<String> hotdogIngredients = new ArrayList<>();
        hotdogIngredients.add("소시지");
        hotdogIngredients.add("빵");
        INGREDIENT_CATEGORIES.put("핫도그", hotdogIngredients);

        // 케이크 관련 재료
        List<String> cakeIngredients = new ArrayList<>();
        cakeIngredients.add("밀가루");
        cakeIngredients.add("설탕");
        cakeIngredients.add("계란");
        INGREDIENT_CATEGORIES.put("케이크", cakeIngredients);

        // 공통 재료 (모든 카테고리에 추가)
        List<String> commonIngredients = new ArrayList<>();
        commonIngredients.add("물");
        commonIngredients.add("설탕");
        commonIngredients.add("우유");

        // 모든 카테고리에 공통 재료 추가
        for (String category : INGREDIENT_CATEGORIES.keySet()) {
            INGREDIENT_CATEGORIES.get(category).addAll(commonIngredients);
        }
    }

    //타입에 따른 레시피 생성 메서드 수정
    public Recipe createRecipeForType(String typeName, List<Ingredient> availableIngredients) {
        Recipe recipe = new Recipe();

        // 해당 타입에 맞는 필수 재료가 있는지 확인
        List<String> categoryIngredients = INGREDIENT_CATEGORIES.getOrDefault(typeName, new ArrayList<>());

        if (categoryIngredients.isEmpty()) {
            // 알 수 없는 타입일 경우 기본 레시피 반환
            recipe.addIngredient("물", 1);
            recipe.addIngredient("설탕", 1);
            return recipe;
        }

        // 해당 카테고리에서 필수 재료 선택
        String primaryIngredient = getPrimaryIngredientForType(typeName, categoryIngredients);
        recipe.addIngredient(primaryIngredient, 1 + r.nextInt(2));

        // 타입에 따른 기본 레시피 구성
        switch (typeName) {
            case "커피":
                recipe.addIngredient("물", 1);
                if (r.nextBoolean()) recipe.addIngredient("우유", 1);
                if (r.nextBoolean()) recipe.addIngredient("설탕", 1);
                break;

            case "논커피":
                if (categoryIngredients.contains("레몬") && r.nextBoolean())
                    recipe.addIngredient("레몬", 1);
                if (r.nextBoolean()) recipe.addIngredient("설탕", 1);
                recipe.addIngredient("물", 1);
                if (r.nextBoolean()) recipe.addIngredient("얼음", 1);
                break;

            case "차":
                recipe.addIngredient("물", 1);
                if (r.nextBoolean()) recipe.addIngredient("설탕", 1);
                if (categoryIngredients.contains("레몬") && r.nextBoolean())
                    recipe.addIngredient("레몬", 1);
                break;

            case "샌드위치":
                recipe.addIngredient("빵", 2);
                if (categoryIngredients.contains("버터") && r.nextBoolean())
                    recipe.addIngredient("버터", 1);
                if (categoryIngredients.contains("치즈") && r.nextBoolean())
                    recipe.addIngredient("치즈", 1);
                if (categoryIngredients.contains("양파") && r.nextBoolean())
                    recipe.addIngredient("양파", 1);
                break;

            case "케이크":
                recipe.addIngredient("밀가루", 2);
                recipe.addIngredient("설탕", 1);
                recipe.addIngredient("계란", 2);
                if (categoryIngredients.contains("버터"))
                    recipe.addIngredient("버터", 1);
                break;

            case "핫도그":
                recipe.addIngredient("빵", 1);
                recipe.addIngredient("소시지", 1);
                if (categoryIngredients.contains("케찹") && r.nextBoolean())
                    recipe.addIngredient("케찹", 1);
                if (categoryIngredients.contains("마요네즈") && r.nextBoolean())
                    recipe.addIngredient("마요네즈", 1);
                break;

            case "베이글":
                recipe.addIngredient("베이글반죽", 1);
                if (categoryIngredients.contains("크림치즈") && r.nextBoolean())
                    recipe.addIngredient("크림치즈", 1);
                break;

            case "디저트":
                if (categoryIngredients.contains("밀가루") && r.nextBoolean())
                    recipe.addIngredient("밀가루", 1);
                if (r.nextBoolean()) recipe.addIngredient("설탕", 1);
                if (categoryIngredients.contains("계란") && r.nextBoolean())
                    recipe.addIngredient("계란", 1);
                if (categoryIngredients.contains("버터") && r.nextBoolean())
                    recipe.addIngredient("버터", 1);
                if (categoryIngredients.contains("초콜릿") && r.nextBoolean())
                    recipe.addIngredient("초콜릿", 1);
                break;
        }

        // 추가 랜덤 재료 (품질 향상 가능성)
        addRandomIngredientsFromCategory(recipe, typeName, categoryIngredients, availableIngredients);

        return recipe;
    }

    // 해당 타입에 맞는 주요 재료 선택
    public String getPrimaryIngredientForType(String typeName, List<String> categoryIngredients) {

        // 타입에 맞는 주요 재료 선택
        List<String> primaryCandidates = new ArrayList<>();

        switch (typeName) {
            case "커피":
                if (categoryIngredients.contains("커피콩")) primaryCandidates.add("커피콩");
                if (categoryIngredients.contains("에스프레소원두")) primaryCandidates.add("에스프레소원두");
                break;

            case "차":
                if (categoryIngredients.contains("차")) primaryCandidates.add("차");
                if (categoryIngredients.contains("녹차가루")) primaryCandidates.add("녹차가루");
                break;

            case "논커피":
                if (categoryIngredients.contains("레몬")) primaryCandidates.add("레몬");
                if (categoryIngredients.contains("초콜릿")) primaryCandidates.add("초콜릿");
                break;

            case "샌드위치":
                if (categoryIngredients.contains("빵")) primaryCandidates.add("빵");
                break;

            case "핫도그":
                if (categoryIngredients.contains("소시지")) primaryCandidates.add("소시지");
                break;

            case "케이크":
                if (categoryIngredients.contains("밀가루")) primaryCandidates.add("밀가루");
                break;

            default:
                // 카테고리에 있는 첫 번째 재료 사용
                if (!categoryIngredients.isEmpty()) {
                    return categoryIngredients.get(0);
                }
                return "물"; // 기본값
        }

        // 후보가 있으면 랜덤 선택, 없으면 기본값
        if (!primaryCandidates.isEmpty()) {
            return primaryCandidates.get(r.nextInt(primaryCandidates.size()));
        }

        // 카테고리에 있는 아무 재료나 사용
        if (!categoryIngredients.isEmpty()) {
            return categoryIngredients.get(r.nextInt(categoryIngredients.size()));
        }

        return "물"; // 최종 기본값
    }

    // 카테고리 내에서만 랜덤 재료 추가
    public void addRandomIngredientsFromCategory(Recipe recipe, String typeName,
                                                  List<String> categoryIngredients,
                                                  List<Ingredient> availableIngredients) {
        // 30% 확률로 추가 재료 선택
        if (r.nextDouble() < 0.3 && !categoryIngredients.isEmpty()) {
            // 해당 카테고리 내의 재료만 필터링
            List<Ingredient> categoryAvailableIngredients = new ArrayList<>();

            for (Ingredient ing : availableIngredients) {
                if (categoryIngredients.contains(ing.name)) {
                    // 이미 레시피에 있는 재료는 제외
                    boolean alreadyInRecipe = false;
                    for (RecipeItem item : recipe.recipeItems) {
                        if (item.ingredientName.equals(ing.name)) {
                            alreadyInRecipe = true;
                            break;
                        }
                    }

                    if (!alreadyInRecipe) {
                        categoryAvailableIngredients.add(ing);
                    }
                }
            }

            // 카테고리 내 사용 가능한 재료가 있으면 추가
            if (!categoryAvailableIngredients.isEmpty()) {
                Ingredient randomIng = categoryAvailableIngredients.get(r.nextInt(categoryAvailableIngredients.size()));
                recipe.addIngredient(randomIng.name, 1);
                System.out.println(typeName + " 카테고리 내에서 추가 재료 " + randomIng.name + " 선택됨");
            }
        }
    }

    // 연구 초기화
    public void initializeResearch() {

        // 기본 연구 타입 추가
        availableRecipe.add(new Research("커피 연구", 3000, 100, MenuItem.DRINK, "커피"));
        availableRecipe.add(new Research("논커피 연구", 3000, 100, MenuItem.DRINK, "논커피"));
        availableRecipe.add(new Research("차(Tea) 연구", 3000, 100, MenuItem.DRINK, "차"));
        availableRecipe.add(new Research("샌드위치 연구", 3000, 100, MenuItem.FOOD, "샌드위치"));
        availableRecipe.add(new Research("케이크 연구", 3000, 100, MenuItem.FOOD, "케이크"));
        availableRecipe.add(new Research("핫도그 연구", 3000, 100, MenuItem.FOOD, "핫도그"));
    }

    // 연구 시작
    public boolean startResearch(int index, int investPoints) {
        if (index < 0 || index >= availableRecipe.size()) {
            System.out.println("잘못된 연구 번호입니다.");
            return false;
        }

        if (researchPoints < investPoints) {
            System.out.println("연구 포인트가 부족합니다.");
            return false;
        }

        Research selected = availableRecipe.get(index);

        // 연구 포인트 사용
        researchPoints -= investPoints;

        // 연구 진행
        boolean completed = selected.processResearch_points(investPoints);

        // 아직 active 리스트에 없다면 추가
        if (!activeRecipe.contains(selected)) {
            activeRecipe.add(selected);
            availableRecipe.remove(index);
        }

        // 연구 완료시 처리
        if (completed) {
            System.out.println(selected.name + " 연구가 완료되었습니다!");
            activeRecipe.remove(selected);
            completedRecipe.add(selected);
            return true;
        } else {
            System.out.println(selected.name + " 연구가 진행 중입니다. (진행률: " +
                    selected.researchPercentage() + "%)");
            return false;
        }
    }

    // 연구 완료 후 새 메뉴 생성
    public MenuItem createNewMenuItem(Research research, List<Ingredient> availableIngredients) {
        // 연구 타입에 따른 메뉴 생성
        int menuType = research.type; // 음료 or 음식
        String typeName = research.typeName; // 세부 타입

        // 메뉴 이름 생성
        String menuName = generateMenuName(typeName);

        // 레시피 생성
        Recipe recipe = createRecipeForType(typeName, availableIngredients);

        // 품질 결정 (랜덤)
        String quality = determineQuality();

        // 기본 가격 결정 (재료 비용 * 1.5 ~ 2.5)
        int basePrice = calculateBasePrice(recipe, availableIngredients);

        // 품질에 따른 가격 조정
        int finalPrice = (int)(basePrice * MenuQuality.resultPriceMultiplier(quality));

        // 새 메뉴 아이템 생성
        MenuItem newItem = new MenuItem(menuName, finalPrice, menuType, typeName, recipe, quality, true);

        return newItem;
    }

    // 메뉴 이름 생성
    public String generateMenuName(String typeName) {
        String[] prefixes = {"특별한 ", "새로운 ", "프리미엄 ", "시그니처 ", "클래식 ", "홈메이드 "};
        String[] suffixes = {"", " 스페셜", " 디럭스", " 오리지널", " 프리미엄"};

        // 타입 기반 메뉴 이름 변형
        String baseItemName = "";

        switch (typeName) {
            case "커피":
                String[] coffeeItems = {"아메리카노", "카페라떼", "카푸치노", "에스프레소", "모카"};
                baseItemName = coffeeItems[r.nextInt(coffeeItems.length)];
                break;

            case "차":
                String[] teaItems = {"녹차", "홍차", "허브티", "레몬티", "캐모마일"};
                baseItemName = teaItems[r.nextInt(teaItems.length)];
                break;

            case "논커피":
                String[] nonCoffeeItems = {"레모네이드", "핫초코", "과일주스", "스무디", "밀크티"};
                baseItemName = nonCoffeeItems[r.nextInt(nonCoffeeItems.length)];
                break;

            case "샌드위치":
                String[] sandwichItems = {"치즈 샌드위치", "햄 샌드위치", "야채 샌드위치", "클럽 샌드위치"};
                baseItemName = sandwichItems[r.nextInt(sandwichItems.length)];
                break;

            case "핫도그":
                String[] hotdogItems = {"클래식 핫도그", "치즈 핫도그", "소시지 롤", "브리또 핫도그"};
                baseItemName = hotdogItems[r.nextInt(hotdogItems.length)];
                break;

            case "케이크":
                String[] cakeItems = {"초코 케이크", "치즈 케이크", "카라멜 케이크", "과일 케이크"};
                baseItemName = cakeItems[r.nextInt(cakeItems.length)];
                break;

            case "베이글":
                String[] bagelItems = {"플레인 베이글", "크림치즈 베이글", "블루베리 베이글"};
                baseItemName = bagelItems[r.nextInt(bagelItems.length)];
                break;

            case "디저트":
                String[] dessertItems = {"쿠키", "머핀", "도넛", "크로와상", "타르트"};
                baseItemName = dessertItems[r.nextInt(dessertItems.length)];
                break;

            default:
                baseItemName = typeName; // 기본값은 타입명 그대로 사용
        }

        // 50% 확률로 접두사 추가
        String prefix = r.nextBoolean() ? prefixes[r.nextInt(prefixes.length)] : "";

        // 30% 확률로 접미사 추가
        String suffix = r.nextDouble() < 0.3 ? suffixes[r.nextInt(suffixes.length)] : "";

        return prefix + baseItemName + suffix;
    }

    // 품질 랜덤 결정
    private String determineQuality() {
        double rand = r.nextDouble();
        double cumulative = 0.0;

        for (int i = 0; i < QUALITY_CHANCES.length; i++) {
            cumulative += QUALITY_CHANCES[i];
            if (rand <= cumulative) {
                return QUALITIES[i];
            }
        }

        return MenuQuality.NORMAL; // 기본값
    }



    // 기본 가격 계산
    public int calculateBasePrice(Recipe recipe, List<Ingredient> availableIngredients) {
        int costSum = 0;

        // 레시피에 있는 모든 재료의 비용 합산
        for (RecipeItem recipeItem : recipe.recipeItems) {
            for (Ingredient ing : availableIngredients) {
                if (ing.name.equals(recipeItem.ingredientName)) {
                    costSum += ing.price * recipeItem.quantity;
                    break;
                }
            }
        }

        // 마진 추가 (1.5배 ~ 2.5배)
        double margin = 1.5 + (r.nextDouble() * 1.0);
        return (int)(costSum * margin);
    }

    // 새로운 연구 생성 (이미 완료된 연구 기반)
    public void generateNewResearchTopics() {
        // 완료된 연구가 있을 때, 20% 확률로 새로운 연구 주제 생성
        if (!completedRecipe.isEmpty() && r.nextDouble() < 0.2) {
            // 랜덤하게 기존 연구 선택
            Research baseResearch = completedRecipe.get(r.nextInt(completedRecipe.size()));

            // 고급 연구 생성
            String newName;
            String newType;
            int newItemType = baseResearch.type;

            if (newItemType == MenuItem.DRINK) {
                newType = DRINK_TYPES[r.nextInt(DRINK_TYPES.length)];
                newName = "고급 " + newType + " 연구";
            } else {
                newType = FOOD_TYPES[r.nextInt(FOOD_TYPES.length)];
                newName = "고급 " + newType + " 연구";
            }

            // 이미 있는 연구인지 확인
            for (Research existing : availableRecipe) {
                if (existing.name.equals(newName)) {
                    return; // 이미 존재하면 취소
                }
            }

            // 새 연구 생성 (비용과 필요 포인트는 기존보다 20% 증가)
            int newCost = (int)(baseResearch.cost * 1.2);
            int newPoints = (int)(baseResearch.researchPoints * 1.2);

            Research newResearch = new Research(newName, newCost, newPoints, newItemType, newType);
            availableRecipe.add(newResearch);

            System.out.println("새로운 연구가 발견되었습니다: " + newName);
        }
    }
}