package project.CoffeeShop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class ShopManager {

    public static Scanner sc = new Scanner(System.in);
    public static Random r = new Random();

    public CoffeeShop coffeeShop;
    public int currentDay;
    public final int MAX_DAYS = 30;
    public boolean isGameOver;
    public List<DeliveryOrder> pendingDeliveries;
    public Weather todayWeather;
    public List<Employee> totalEmployees;
    public List<Employee> workingEmployees;
    public Satisfaction satisfaction;
    public ResearchManager researchManager;

    public int ingredientShortageCount = 0; // 재료 부족 발생 횟수
    public static final int MAX_SHORTAGE_COUNT = 3; // 최대 허용 부족 횟수

    public ShopManager() {
        coffeeShop = new CoffeeShop();
        currentDay = 1;
        isGameOver = false;
        pendingDeliveries = new ArrayList<>();
        totalEmployees = new ArrayList<>();
        workingEmployees = new ArrayList<>();
        satisfaction = new Satisfaction();
        researchManager = new ResearchManager();
    }

    public void start() {
        System.out.println("=== 당신은 커피숍 주인입니다.===");
        System.out.println("=== 게임은 30일간 진행됩니다.===");
        System.out.println("=== 주의: 고객 만족도가 0이 되면 게임이 종료됩니다! ===");
        System.out.println("=== 재료 부족이나 자금 부족은 연속 3회까지만 허용됩니다! ===");


        // 초기 메뉴 및 원재료 설정
        initializeMenu();
        initializeIngredients();

        // 게임 루프
        while (!isGameOver && currentDay <= MAX_DAYS) {
            System.out.println("=== " + currentDay + "일차 ===");
            System.out.println("현재 자금: " + coffeeShop.money + "원");
            selectWeather(); //오늘의 날씨


            // 배달 예정인 원재료 처리
            processPendingDeliveries();

            // 오너 차례
            ownerTurn();

            if (isGameOver) break;

            // 손님 차례
            customerTurn();

            // 만족도가 0이면 게임오버
            if (satisfaction.isZero()) {
                System.out.println("=== 고객 만족도가 0이 되었습니다! ===");
                System.out.println("가게를 폐업합니다. 종료");

                isGameOver = true;
                break;
            }

            // 하루 마감
            endDay();

            currentDay++;
        }

        if (!isGameOver) {
            // 게임 결과 표시
            showGameResult();
        }

        askForRestart();
    }

    public void processPendingDeliveries() {
        // 삭제해야 할 배달 항목을 저장할 리스트
        List<DeliveryOrder> completedDeliveries = new ArrayList<>();
        boolean hasDelivery = false;

        for (DeliveryOrder delivery : pendingDeliveries) {
            if (delivery.deliveryDay == currentDay) {
                if (!hasDelivery) {
                    System.out.println("=== 오늘 배달된 원재료 ===");
                    hasDelivery = true;
                }

                // 재료 찾기 및 재고 추가
                for (Ingredient ing : coffeeShop.ingredients) {
                    if (ing.name.equals(delivery.ingredientName)) {
                        ing.addQuantity(delivery.quantity);
                        System.out.println(ing.name + " " + delivery.quantity + "개가 배달되었습니다.");
                        break;
                    }
                }

                // 처리 완료된 배달은 나중에 제거할 목록에 추가
                completedDeliveries.add(delivery);
            }
        }

        // 처리된 배달 제거
        pendingDeliveries.removeAll(completedDeliveries);
    }

    public void initializeMenu() {
        // 기본 메뉴 추가 (0: 음료, 1: 음식)
        // 음료 메뉴
        Recipe coldAmericanoRecipe = new Recipe();
        coldAmericanoRecipe.addIngredient("커피콩", 1);
        coldAmericanoRecipe.addIngredient("물", 1);
        coldAmericanoRecipe.addIngredient("얼음", 1);
        coffeeShop.addMenuItem(new MenuItem("아이스 아메리카노", 3500, 0, "커피", coldAmericanoRecipe, "보통", true));

        Recipe hotAmericanoRecipe = new Recipe();
        hotAmericanoRecipe.addIngredient("커피콩", 1);
        hotAmericanoRecipe.addIngredient("물", 1);
        coffeeShop.addMenuItem(new MenuItem("따뜻한 아메리카노", 3000, 0, "커피", hotAmericanoRecipe, "보통", true));


        Recipe adeRecipe = new Recipe();
        adeRecipe.addIngredient("레몬", 1);
        adeRecipe.addIngredient("설탕", 1);
        adeRecipe.addIngredient("물", 1);
        coffeeShop.addMenuItem(new MenuItem("에이드", 4000, 0, "논커피", adeRecipe, "보통", true));


        Recipe iceTeaRecipe = new Recipe();
        iceTeaRecipe.addIngredient("차", 1);
        iceTeaRecipe.addIngredient("얼음", 1);
        coffeeShop.addMenuItem(new MenuItem("아이스티", 3500, 0, "차", iceTeaRecipe, "보통", true));

        // 음식 메뉴

        Recipe sandwichRecipe = new Recipe();
        sandwichRecipe.addIngredient("빵", 2);
        sandwichRecipe.addIngredient("치즈", 1);
        sandwichRecipe.addIngredient("버터", 1);
        coffeeShop.addMenuItem(new MenuItem("기본 샌드위치", 5500, 1, "샌드위치", sandwichRecipe, "보통", true));

        Recipe hotdogRecipe = new Recipe();
        hotdogRecipe.addIngredient("빵", 1);
        hotdogRecipe.addIngredient("소시지", 1);
        hotdogRecipe.addIngredient("케찹", 1);
        hotdogRecipe.addIngredient("마요네즈", 1);
        coffeeShop.addMenuItem(new MenuItem("기본 핫도그", 4000, 1, "핫도그", hotdogRecipe, "보통", true));
    }


    public void initializeIngredients() {
        // 초기 재료 추가
        coffeeShop.addIngredient(new Ingredient("커피콩", 50, 1500));
        coffeeShop.addIngredient(new Ingredient("물", 100, 500));
        coffeeShop.addIngredient(new Ingredient("우유", 50, 1500));
        coffeeShop.addIngredient(new Ingredient("차", 30, 1000));
        coffeeShop.addIngredient(new Ingredient("얼음", 50, 500));
        coffeeShop.addIngredient(new Ingredient("베이글반죽", 30, 1000));
        coffeeShop.addIngredient(new Ingredient("크림치즈", 30, 1500));
        coffeeShop.addIngredient(new Ingredient("소시지", 30, 1000));
        coffeeShop.addIngredient(new Ingredient("빵", 50, 500));
        coffeeShop.addIngredient(new Ingredient("케찹", 30, 500));
        coffeeShop.addIngredient(new Ingredient("마요네즈", 30, 500));
        coffeeShop.addIngredient(new Ingredient("양파", 30, 500));
        coffeeShop.addIngredient(new Ingredient("크로와상반죽", 20, 1500));
        coffeeShop.addIngredient(new Ingredient("딸기잼", 20, 2000));
        coffeeShop.addIngredient(new Ingredient("치즈", 30, 1500));
        coffeeShop.addIngredient(new Ingredient("버터", 30, 1000));
        coffeeShop.addIngredient(new Ingredient("슈크림", 20, 2000));
        coffeeShop.addIngredient(new Ingredient("고구마", 20, 1000));
        coffeeShop.addIngredient(new Ingredient("밀가루", 30, 1000));
        coffeeShop.addIngredient(new Ingredient("설탕", 30, 500));
        coffeeShop.addIngredient(new Ingredient("계란", 50, 500));
        coffeeShop.addIngredient(new Ingredient("파마산치즈", 20, 2000));
        coffeeShop.addIngredient(new Ingredient("스파게티면", 20, 1500));
        coffeeShop.addIngredient(new Ingredient("레몬", 30, 1500));
        coffeeShop.addIngredient(new Ingredient("초콜릿", 30, 2000));
        coffeeShop.addIngredient(new Ingredient("바닐라시럽", 30, 1000));
        coffeeShop.addIngredient(new Ingredient("에스프레소원두", 30, 2000));
        coffeeShop.addIngredient(new Ingredient("녹차가루", 30, 2000));
    }

    public void ownerTurn() {
        boolean turnEnd = false;

        while (!turnEnd) {
            System.out.println("\n=== 주인장 메뉴 ===");
            System.out.println("1. 메뉴 보기");
            System.out.println("2. 메뉴 추가");
            System.out.println("3. 메뉴 삭제");
            System.out.println("4. 재고 확인");
            System.out.println("5. 원재료 주문");
            System.out.println("6. 부족 재료 자동 주문");
            System.out.println("7. 직원 관리");
            System.out.println("8. 메뉴 개발 (연구하기)");
            System.out.println("9. 일과 시작 (손님 맞이하기)");
            System.out.println("10. 게임 종료");
            System.out.print("선택: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    coffeeShop.showMenu();
                    break;
                case 2:
                    addMenu();
                    break;
                case 3:
                    removeMenu();
                    break;
                case 4:
                    coffeeShop.showIngredients();
                    break;
                case 5:
                    orderIngredients();
                    break;
                case 6:
                    autoOrderEmergencyIngredients();
                    break;
                case 7:
                    employeeManagement();
                    break;
                case 8:
                    researchMenu();
                    break;
                case 9:
                    turnEnd = true;
                    break;
                case 10:
                    System.out.println("게임을 종료합니다.");
                    isGameOver = true;
                    return;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    public void employeeManagement() {
        boolean back = false;

        while (!back) {
            System.out.println("=== 직원 관리 메뉴 ===");
            System.out.println("1. 직원 목록 보기");
            System.out.println("2. 직원 고용하기");
            System.out.println("3. 직원 해고하기");
            System.out.println("4. 오늘의 근무 직원 배치");
            System.out.println("5. 돌아가기");
            System.out.print("선택: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    showEmployees();
                    break;
                case 2:
                    hireEmployee();
                    break;
                case 3:
                    fireEmployee();
                    break;
                case 4:
                    setWorkers();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    public void addMenu() {
        System.out.print("메뉴 이름: ");
        String name = sc.nextLine();
        System.out.print("가격: ");
        int price = sc.nextInt();
        System.out.print("종류 (0: 음료, 1: 음식): ");
        int type = sc.nextInt();
        System.out.println("타입: ");
        String typeName = sc.nextLine();
        String quality = "보통";
        boolean unlocked = true;
        sc.nextLine();

        // 레시피 생성
        Recipe recipe = new Recipe();

        System.out.println("필요한 원재료를 입력하세요 (완료 시 '완료' 입력)");
        while (true) {
            System.out.print("원재료 이름 (완료 시 '완료' 입력): ");
            String ingredientName = sc.nextLine();

            if (ingredientName.equals("완료")) {
                break;
            }

            System.out.print("필요 수량: ");
            int quantity = sc.nextInt();
            sc.nextLine();

            recipe.addIngredient(ingredientName, quantity);
        }

        MenuItem newItem = new MenuItem(name, price, type, typeName, recipe, quality, unlocked);
        coffeeShop.addMenuItem(newItem);

        System.out.println(name + " 메뉴가 추가되었습니다.");
    }

    public void removeMenu() {
        coffeeShop.showMenu();
        System.out.print("삭제할 메뉴 번호: ");
        int index = sc.nextInt();
        sc.nextLine();

        coffeeShop.removeMenuItem(index);
    }

    public void orderIngredients() {
        coffeeShop.showIngredients();
        System.out.print("주문할 재료 번호: ");
        int index = sc.nextInt();
        System.out.print("주문할 수량: ");
        int quantity = sc.nextInt();
        sc.nextLine();

        if (index >= 1 && index <= coffeeShop.ingredients.size()) {
            Ingredient ing = coffeeShop.ingredients.get(index - 1);
            double cost = ing.price * quantity;

            if (coffeeShop.money >= cost) {
                // 다음날 배달 예약
                int deliveryDay = currentDay + 1;
                DeliveryOrder newDelivery = new DeliveryOrder(deliveryDay, ing.name, quantity);
                pendingDeliveries.add(newDelivery);

                coffeeShop.money -= cost;
                System.out.println(ing.name + " " + quantity + "개를 주문했습니다. 비용: " + cost + "원");
                System.out.println("다음날 배달됩니다.");
                System.out.println("남은 자금: " + coffeeShop.money + "원");

                // 자금 확인
                if (coffeeShop.money <= 0) {
                    System.out.println("자금이 부족합니다!");
                    isGameOver = true;
                }
            }
            if (coffeeShop.money < cost) {
                System.out.println("자금이 부족합니다!");
            }
        } else {
            System.out.println("잘못된 번호입니다.");
        }

    }


    public void customerTurn() {
        // 손님 수 계산
        int customerCount = calculateDailyCustomers();
        displayDailyInfo(customerCount);

        // 각 손님 처리
        for (int i = 1; i <= customerCount; i++) {
            if (isGameOver) break;

            System.out.println("=== 손님 " + i + " ===");
            processCustomer(i);
        }
    }

    // 일일 손님 수 계산
    public int calculateDailyCustomers() {
        int baseCustomerCount = r.nextInt(16) + 5;  // 기본 손님수 5~20
        double weatherMultiplier = todayWeather.customerMultiplier;
        double satisfactionMultiplier = satisfaction.getCustomerMultiplier();
        return (int) (baseCustomerCount * weatherMultiplier * satisfactionMultiplier);
    }

    // 일일 정보 표시
    public void displayDailyInfo(int customerCount) {
        System.out.println("오늘 방문한 손님 수: " + customerCount);
        System.out.println("오늘의 날씨: " + todayWeather.name +
                " (손님 배율: " + String.format("%.2f", todayWeather.customerMultiplier) + ")");
        System.out.println("가게 만족도: " + satisfaction.satisfaction +
                " (손님 배율: " + String.format("%.2f", satisfaction.getCustomerMultiplier()) + ")");
    }


    public void displayOrder(List<MenuItem> order) {
        System.out.println("손님 주문 내역:");
        int totalPrice = 0;
        for (MenuItem item : order) {
            System.out.println("- " + item.name + " (" + item.price + "원)");
            totalPrice += item.price;
        }
        System.out.println("총 금액: " + totalPrice + "원");
    }

    // 개별 손님 처리
    public void processCustomer(int customerNumber) {
        Customer customer = new Customer(todayWeather);

        System.out.println("손님 " + customerNumber + "님의 주문을 처리합니다.");
        // 손님 주문 및 선호도 정보 생성
        List<MenuItem> customerOrder = getCustomerOrder(customer);
        List<String> customerPreferences = generateCustomerPreferences();
        System.out.println("손님 선호 메뉴(추정): " + customerPreferences);

        // 주문이 비어있으면 처리
        if (customerOrder.isEmpty()) {
            List<MenuItem> possibleOrders = coffeeShop.menuItems; // 모든 메뉴를 선택 가능한 메뉴로 사용
            customerOrder = handleEmptyOrder(customer, customerPreferences, possibleOrders);

            // 여전히 비어있다면 이 손님은 건너뜀
            if (customerOrder.isEmpty()) {
                return;
            }
        }

        // 주문 내역 표시 및 처리
        displayOrder(customerOrder);

        // 주문 취소 처리
        if (handleOrderCancellation(customerOrder)) {
            return;
        }

        // 재료 부족 처리
        if (handleIngredientShortage(customerOrder)) {
            return;
        }

        // 주문 처리 시간 계산
        double processingTime = calculateProcessingTime(customerOrder);

        // 만족도 처리
        handleSatisfactionChange(processingTime, customerOrder);

        // 주문 완료 및 선호도 처리
        completeOrder(customerOrder, customerPreferences);
    }

    // 손님 주문 가져오기
    public List<MenuItem> getCustomerOrder(Customer customer) {
        return customer.order(coffeeShop.menuItems);
    }

    // 빈 주문 처리
    public List<MenuItem> handleEmptyOrder(Customer customer, List<String> customerPreferences, List<MenuItem> possibleOrders) {
        System.out.println("손님이 아무것도 주문하지 않았습니다.");
        List<MenuItem> selectedOrder;

        if (workingEmployees.isEmpty()) {
            // 주인이 직접 메뉴 선택
            selectedOrder = selectMenuForCustomer(possibleOrders, customerPreferences);
        } else {
            // 직원이 자동으로 랜덤 메뉴 선택
            selectedOrder = employesSelectForCustomer(possibleOrders, customerPreferences);
        }

        return selectedOrder;
    }

    // 주문 취소 처리
    public boolean handleOrderCancellation(List<MenuItem> customerOrder) {
        boolean cancelOrder = processCustomerMenu(customerOrder);

        if (cancelOrder) {
            System.out.println("주문이 취소되었습니다.");
            satisfaction.decreaseSatisfaction(1);
            return true;
        }
        return false;
    }

    public boolean processCustomerMenu(List<MenuItem> order) {
        System.out.println("== 손님 메뉴 ===");
        System.out.println("1. 주문 확인하기");
        System.out.println("2. 주문 취소하기");
        System.out.print("선택: ");

        int choice = sc.nextInt();
        sc.nextLine();

        return choice == 2;
    }

    // 재료 부족 처리
    public boolean handleIngredientShortage(List<MenuItem> customerOrder) {
        if (!coffeeShop.checkIngredients(customerOrder)) {
            System.out.println("재료가 부족하여 주문을 처리할 수 없습니다!");

            // 재료 부족 카운트 증가
            ingredientShortageCount++;

            // 만족도 감소
            satisfaction.decreaseSatisfaction(5);

            // 재료 부족 경고
            System.out.println("재료 부족 발생 (" + ingredientShortageCount + "/" + MAX_SHORTAGE_COUNT + "회)");

            // 최대 허용 횟수 초과 시 게임오버
            if (ingredientShortageCount >= MAX_SHORTAGE_COUNT) {
                System.out.println("연속 " + MAX_SHORTAGE_COUNT + "회 동안 재료 부족으로 주문을 거절했습니다!");
                System.out.println("가게 평판이 나빠져 폐업합니다.");
                isGameOver = true;
            }

            return true;
        } else {
            // 재료가 충분하면 카운트 리셋
            ingredientShortageCount = 0;
            return false;
        }
    }

    // 처리 시간 계산
    public double calculateProcessingTime(List<MenuItem> customerOrder) {
        if (!workingEmployees.isEmpty()) {
            // 가장 빠른 직원 찾기
            Employee fastestEmployee = findFastestEmployee(customerOrder);
            double processingTime = calculateEmployeeProcessingTime(fastestEmployee, customerOrder);

            System.out.println(fastestEmployee.name + "(" + fastestEmployee.getTypeName() +
                    ")이(가) 주문을 처리합니다. 소요 시간: " + String.format("%.1f", processingTime) + "분");

            return processingTime;
        } else {
            // 직원이 없으면 주인이 직접 처리 (기본 처리 시간 적용)
            double processingTime = customerOrder.size() * 2.0;  // 메뉴 당 2분 소요
            System.out.println("주인이 직접 주문을 처리합니다. 소요 시간: " + String.format("%.1f", processingTime) + "분");
            return processingTime;
        }
    }

    // 가장 빠른 직원 찾기
    public Employee findFastestEmployee(List<MenuItem> customerOrder) {
        Employee fastestEmployee = null;
        double bestTime = Double.MAX_VALUE;

        for (Employee emp : workingEmployees) {
            double employeeTime = calculateEmployeeProcessingTime(emp, customerOrder);

            if (employeeTime < bestTime) {
                bestTime = employeeTime;
                fastestEmployee = emp;
            }
        }

        return fastestEmployee;
    }

    // 직원의 처리 시간 계산
    public double calculateEmployeeProcessingTime(Employee employee, List<MenuItem> customerOrder) {
        double employeeTime = 0;
        for (MenuItem item : customerOrder) {
            employeeTime += employee.processingTime(item);
        }
        return employeeTime;
    }

    // 주문 완료 및 선호도 보너스 처리
    public void completeOrder(List<MenuItem> customerOrder, List<String> customerPreferences) {
        // 재료 소비 및 판매
        coffeeShop.processOrder(customerOrder);
        System.out.println("주문이 완료되었습니다.");

        // 선호도에 따른 만족도 처리
        boolean anyPreferredItem = false;
        int totalSatisfactionBonus = 0;

        // 선호도 및 품질에 따른 추가 만족도 처리
        for (MenuItem item : customerOrder) {
            boolean isPreferred = checkItemPreference(item, customerPreferences);
            int qualityBonus = getQualityBonus(item.quality);

            if (isPreferred) {
                // 선호 메뉴 제공 시 만족도 증가 (기본 3 + 품질 보너스)
                int bonus = 3 + qualityBonus;
                totalSatisfactionBonus += bonus;
                System.out.println("손님이 선호하는 메뉴 '" + item.name + "' (" + item.quality +
                        ")를 제공하여 만족도가 " + bonus + "만큼 상승했습니다.");
                anyPreferredItem = true;
            } else {
                // 비선호 메뉴지만 품질이 좋으면 약간의 만족도 보너스
                if (qualityBonus > 0) {
                    totalSatisfactionBonus += qualityBonus;
                    System.out.println("비선호 메뉴지만 품질이 좋은 '" + item.name + "' (" + item.quality +
                            ")를 제공하여 만족도가 " + qualityBonus + "만큼 상승했습니다.");
                }
            }
        }

        // 메뉴 품질에 따른 추가 매출 계산 및 적용
        applyQualityRevenueBonuses(customerOrder);

        // 선호 메뉴가 하나도 없을 경우 만족도 감소
        if (!anyPreferredItem && !customerOrder.isEmpty() && totalSatisfactionBonus <= 0) {
            satisfaction.decreaseSatisfaction(2);
            System.out.println("손님이 선호하는 메뉴가 없어 만족도가 감소했습니다.");
        } else if (totalSatisfactionBonus > 0) {
            satisfaction.increaseSatisfaction(totalSatisfactionBonus);
        }
    }

    // 품질에 따른 만족도 보너스 계산
    public int getQualityBonus(String quality) {
        switch (quality) {
            case MenuQuality.BAD:
                return -1; // 나쁜 품질은 만족도 감소
            case MenuQuality.NORMAL:
                return 0; // 보통 품질은 보너스 없음
            case MenuQuality.GOOD:
                return 2; // 좋은 품질은 +2 보너스
            case MenuQuality.EXCELLENT:
                return 5; // 매우 좋은 품질은 +5 보너스
            default:
                return 0;
        }
    }

    // 품질에 따른 추가 매출 적용
    public void applyQualityRevenueBonuses(List<MenuItem> customerOrder) {
        int qualityRevenueBonus = 0;

        for (MenuItem item : customerOrder) {
            double bonusMultiplier = 0;

            switch (item.quality) {
                case MenuQuality.BAD:
                    bonusMultiplier = 0; // 나쁜 품질은 보너스 없음
                    break;
                case MenuQuality.NORMAL:
                    bonusMultiplier = 0.05; // 보통 품질은 5% 보너스
                    break;
                case MenuQuality.GOOD:
                    bonusMultiplier = 0.15; // 좋은 품질은 15% 보너스
                    break;
                case MenuQuality.EXCELLENT:
                    bonusMultiplier = 0.30; // 매우 좋은 품질은 30% 보너스
                    break;
            }

            if (bonusMultiplier > 0) {
                int itemBonus = (int) (item.price * bonusMultiplier);
                qualityRevenueBonus += itemBonus;
                coffeeShop.money += itemBonus;
                coffeeShop.dailyRevenue += itemBonus;
                coffeeShop.totalRevenue += itemBonus;

                System.out.println(item.quality + " 품질의 " + item.name + "에서 추가 수익 " +
                        itemBonus + "원이 발생했습니다. (팁/추가주문)");
            }
        }

        if (qualityRevenueBonus > 0) {
            System.out.println("품질 보너스로 총 " + qualityRevenueBonus + "원의 추가 수익이 발생했습니다.");
        }
    }

    // 아이템 선호도 체크
    public boolean checkItemPreference(MenuItem item, List<String> customerPreferences) {
        for (String pref : customerPreferences) {
            if (item.name.contains(pref)) {
                return true;
            }
        }
        return false;
    }

    // 만족도 변화 처리
    public void handleSatisfactionChange(double processingTime, List<MenuItem> customerOrder) {
        int satisfactionChange = satisfaction.processSatisfaction(processingTime, customerOrder);
        System.out.println("만족도 변화: " + satisfactionChange);

        if (satisfactionChange > 0) {
            satisfaction.increaseSatisfaction(satisfactionChange);
        } else if (satisfactionChange < 0) {
            satisfaction.decreaseSatisfaction(Math.abs(satisfactionChange));
        }
    }

    public void endDay() {
        System.out.println("\n=== " + currentDay + "일차 영업 종료 ===");
        System.out.println("오늘의 매출: " + coffeeShop.dailyRevenue + "원");

        // 연구 포인트 획득
        researchManager.addDailyResearchPoints(coffeeShop.dailyRevenue);

        // 직원 급여 지급
        if (!workingEmployees.isEmpty()) {
            System.out.println("\n직원 급여를 지급합니다.");
            payEmployeeSalaries();
        }

        System.out.println("현재 자금: " + coffeeShop.money + "원");
        System.out.println("현재 만족도: " + satisfaction.satisfaction);

        // 일일 매출 초기화
        coffeeShop.resetDailyRevenue();

        System.out.println("계속하려면 엔터를 누르세요.");
        sc.nextLine();
    }

    // 자동 재료 주문 기능 추가 (단순화된 버전)
    public void autoOrderEmergencyIngredients() {
        System.out.println("=== 재료 부족 방지 시스템 ===");

        boolean orderedSomething = false;
        double totalCost = 0;

        // 재고가 적은 재료 표시
        System.out.println("현재 재고가 10개 이하인 재료들:");
        for (Ingredient ing : coffeeShop.ingredients) {
            if (ing.quantity <= 10) {
                System.out.println("- " + ing.name + " (현재: " + ing.quantity + "개)");
            }
        }

        // 재고가 2개 이하인 모든 재료 자동 주문
        for (Ingredient ing : coffeeShop.ingredients) {
            if (ing.quantity <= 10) {
                int orderQuantity = 20; // 기본 20개씩 주문
                double cost = ing.price * orderQuantity;

                // 자금 확인
                if (coffeeShop.money >= cost) {
                    // 다음날 배달 예약
                    int deliveryDay = currentDay + 1;
                    DeliveryOrder newDelivery = new DeliveryOrder(deliveryDay, ing.name, orderQuantity);
                    pendingDeliveries.add(newDelivery);

                    coffeeShop.money -= cost;
                    totalCost += cost;

                    System.out.println(ing.name + " " + orderQuantity + "개 주문 완료 (비용: " + cost + "원)");
                    orderedSomething = true;
                } else {
                    System.out.println(ing.name + "를 주문하기에 자금이 부족합니다.");
                }
            }
        }

        if (orderedSomething) {
            System.out.println("총 " + totalCost + "원 어치 재료를 주문했습니다.");
            System.out.println("남은 자금: " + coffeeShop.money + "원");
        } else {
            System.out.println("재고가 부족한 재료가 없거나 자금이 부족합니다.");
        }
    }

    public void showGameResult() {
        System.out.println("=== 게임 결과 ===");
        System.out.println("30일 동안 커피숍을 성공적으로 운영했습니다!");
        System.out.println("최종 자금: " + coffeeShop.money + "원");
        System.out.println("최종 만족도: " + satisfaction.satisfaction);
        System.out.println("총 매출: " + coffeeShop.totalRevenue + "원");
    }

    public void askForRestart() {
        System.out.print("게임을 다시 시작하시겠습니까? (y/n): ");
        String answer = sc.nextLine();

        if (answer.equals("y")) {
            currentDay = 1;
            isGameOver = false;
            coffeeShop = new CoffeeShop();
            pendingDeliveries = new ArrayList<>();
            start();
        } else {
            System.out.println("게임을 종료합니다. 이용해주셔서 감사합니다!");
        }
    }

    public void selectWeather() {
        int weatherType = r.nextInt(6);
        todayWeather = new Weather(weatherType);
        System.out.println("오늘의 날씨: " + todayWeather.name);
    }

    public void showEmployees() {
        if (totalEmployees.isEmpty()) {
            System.out.println("고용된 직원이 없습니다.");
            return;
        }

        for (int i = 0; i < totalEmployees.size(); i++) {
            Employee emp = totalEmployees.get(i);
            System.out.println((i + 1) + ". " + emp.name + (emp.isWorking ? "[근무중]" : "[비번]"));
        }
    }

    public void hireEmployee() {
        System.out.println("새 직원 고용");

        // 지원자 생성
        List<Employee> applicants = new ArrayList<>();
        String[] names = {"가나", "다라", "마바", "사아", "자차", "카타", "파하"};

        for (int i = 0; i < 4; i++) {
            String name = names[r.nextInt(names.length)] + (i + 1);  // 이름 중복 방지
            int type = r.nextInt(5);  // 직원 타입 (0-4)
            int speedSkill = r.nextInt(5) + 1;  // 속도 스킬 (1-5)
            applicants.add(new Employee(name, type, speedSkill));  // 수정된 부분
        }

        System.out.println("상기 지원자 중에서 선택하세요");
        for (int i = 0; i < applicants.size(); i++) {
            Employee emp = applicants.get(i);
            System.out.println((i + 1) + ". " + emp.name + " - 유형: " + emp.getTypeName() +
                    ", 속도 스킬: " + emp.speedSkill + ", 급여: " + emp.salary + "원");
        }

        System.out.print("고용할 직원 번호 (취소: 0): ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice > 0 && choice <= applicants.size()) {
            Employee hired = applicants.get(choice - 1);

            // 고용 비용 확인
            if (coffeeShop.money >= hired.salary) {
                totalEmployees.add(hired);
                coffeeShop.money -= hired.salary;  // 고용 비용 지불
                System.out.println(hired.name + "(" + hired.getTypeName() + ")을(를) 고용했습니다.");
                System.out.println("남은 자금: " + coffeeShop.money + "원");
            } else {
                System.out.println("자금이 부족하여 직원을 고용할 수 없습니다.");
            }
        } else if (choice != 0) {
            System.out.println("잘못된 번호입니다.");
        } else {
            System.out.println("고용을 취소했습니다.");
        }
    }

    public void fireEmployee() {
        if (totalEmployees.isEmpty()) {
            System.out.println("고용된 직원이 없습니다.");
            return;
        }

        System.out.println("=== 직원 해고 ===");
        showEmployees();

        System.out.print("해고할 직원 번호 (취소: 0): ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice > 0 && choice <= totalEmployees.size()) {
            Employee fired = totalEmployees.get(choice - 1);

            // 근무 중인 직원이라면 근무 목록에서도 제거
            if (fired.isWorking) {
                workingEmployees.remove(fired);
            }

            totalEmployees.remove(choice - 1);
            System.out.println(fired.name + "(" + fired.getTypeName() + ")을(를) 해고했습니다.");

            // 해고 시 만족도 감소 (직원들의 사기가 떨어짐)
            satisfaction.decreaseSatisfaction(5);
            System.out.println("직원 해고로 가게 만족도가 감소했습니다. 현재 만족도: " + satisfaction.satisfaction);
        } else if (choice != 0) {
            System.out.println("잘못된 번호입니다.");
        } else {
            System.out.println("해고를 취소했습니다.");
        }
    }


    public void setWorkers() {
        System.out.println("=== 오늘의 근무 직원 배치 ===");

        // 현재 근무 중인 직원 모두 해제
        for (Employee emp : totalEmployees) {
            emp.setWorking(false);
        }
        workingEmployees.clear();

        // 고용된 직원 목록 표시
        System.out.println("고용된 직원 목록:");
        showEmployees();

        if (totalEmployees.isEmpty()) {
            System.out.println("고용된 직원이 없습니다.");
            return;
        }

        System.out.println("근무할 직원 번호를 입력하세요 (완료 시 0 입력)");
        while (true) {
            System.out.print("직원 번호 (완료: 0): ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 0) {
                break;
            }

            if (choice > 0 && choice <= totalEmployees.size()) {
                Employee selected = totalEmployees.get(choice - 1);

                if (selected.isWorking) {
                    System.out.println(selected.name + "은(는) 이미 근무 중입니다.");
                } else {
                    selected.setWorking(true);
                    workingEmployees.add(selected);
                    System.out.println(selected.name + "을(를) 근무 배치했습니다.");
                }
            } else {
                System.out.println("잘못된 번호입니다.");
            }
        }

        System.out.println("오늘 근무 직원 (" + workingEmployees.size() + "명):");
        for (Employee emp : workingEmployees) {
            System.out.println("- " + emp.name + " (" + emp.getTypeName() + ")");
        }
    }

    public void payEmployeeSalaries() {
        if (workingEmployees.isEmpty()) {
            System.out.println("오늘 근무한 직원이 없으므로 급여를 지급하지 않습니다.");
            return;
        }

        int totalSalary = 0;
        System.out.println("=== 일일 급여 지급 ===");

        for (Employee emp : workingEmployees) {
            totalSalary += emp.salary;
            System.out.println(emp.name + " (" + emp.getTypeName() + "): " + emp.salary + "원");
        }

        if (coffeeShop.money >= totalSalary) {
            coffeeShop.money -= totalSalary;
            System.out.println("총 급여: " + totalSalary + "원 지급 완료");
            System.out.println("남은 자금: " + coffeeShop.money + "원");

            // 급여 지급으로 만족도 소폭 상승
            satisfaction.increaseSatisfaction(2);
        } else {
            System.out.println("자금 부족으로 급여를 지급할 수 없습니다!");
            System.out.println("필요 금액: " + totalSalary + "원, 보유 자금: " + coffeeShop.money + "원");

            // 급여 미지급으로 만족도 대폭 하락
            satisfaction.decreaseSatisfaction(15);
            System.out.println("급여 미지급으로 가게 만족도가 하락했습니다. 현재 만족도: " + satisfaction.satisfaction);

        }
    }

    public List<String> generateCustomerPreferences() {
        List<String> preferences = new ArrayList<>();

        //날씨 기반 선호도
        if (todayWeather.type == Weather.HOT || todayWeather.type == Weather.SUNNY) {
            preferences.add("아이스");
            preferences.add("에이드");
        } else if (todayWeather.type == Weather.COLD || todayWeather.type == Weather.RAINY || todayWeather.type == Weather.SNOWY) {
            preferences.add("따뜻한");
            preferences.add("핫");
        }

        //랜덤
        String[] drinkTypes = {"아메리카노", "라떼", "카푸치노", "에스프레소", "녹차", "초코"};
        String[] foodTypes = {"베이글", "샌드위치", "핫도그"};

        //음료 1-2개 선택
        int drinkPrefs = r.nextInt(2) + 1;
        for (int i = 0; i < drinkPrefs; i++) {
            preferences.add(drinkTypes[r.nextInt(drinkTypes.length)]);
        }

        //음식 좋아할 확률
        if (r.nextDouble() < 0.4) {
            preferences.add(foodTypes[r.nextInt(foodTypes.length)]);
        }
        return preferences;
    }

    //손님에게 제공할 메뉴 선택
    public List<MenuItem> selectMenuForCustomer(List<MenuItem> possibleOrders, List<String> customerPreferences) {
        System.out.println("===메뉴===");
        for (int i = 0; i < possibleOrders.size(); i++) {
            MenuItem item = possibleOrders.get(i);
            boolean isPreferred = false;

            //선호 메뉴
            for (String pref : customerPreferences) {
                if (item.name.contains(pref)) {
                    isPreferred = true;
                    break;
                }
            }
            System.out.println((i + 1) + ". " + item.name + " (" + item.price + "원)" + (isPreferred ? " [선호]" : ""));
        }

        List<MenuItem> selectedItems = new ArrayList<>();
        boolean selecting = true;

        System.out.println("손님에게 어떤 메뉴를 제공하시겠습니까? (완료 시 0 입력)");


        while (selecting) {
            System.out.println("메뉴 번호 선택(0: 완료): ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 0) {
                selecting = false;
            }
            if (choice >= 1 && choice <= possibleOrders.size()) {
                MenuItem selected = possibleOrders.get(choice - 1);
                selectedItems.add(selected);
                // 선택한 메뉴의 선호도 확인 및 피드백
                boolean isPreferred = checkItemPreference(selected, customerPreferences);
                if (isPreferred) {
                    System.out.println(selected.name + "을(를) 선택했습니다. 손님이 선호하는 메뉴입니다.");
                } else {
                    System.out.println(selected.name + "을(를) 선택했습니다. 손님의 선호와는 맞지 않을 수 있습니다.");
                }
            }
        }
        return selectedItems;

    }

    public List<MenuItem> employesSelectForCustomer(List<MenuItem> possibleOrders, List<String> customerPreferences) {
        // 직원이 없으면 빈 리스트 반환
        if (workingEmployees.isEmpty()) {
            return new ArrayList<>();
        }

        // 적절한 직원 선택
        Employee selectedEmployee = selectBestEmployee(workingEmployees);
        System.out.println(selectedEmployee.name + "(" + selectedEmployee.getTypeName() + ")이(가) 주문을 처리합니다.");

        // 직원 능력에 따른 정확도 계산
        double selectionAccuracy = getSelectionAccuracy(selectedEmployee);
        double serviceQuality = getServiceQuality(selectedEmployee);
        System.out.println("직원의 서비스 정확도: " + String.format("%.1f", selectionAccuracy * 100) + "%");

        // 메뉴 선택 결정
        if (r.nextDouble() < selectionAccuracy) {
            // 정확한 선택 로직
            return selectAccurateMenu(selectedEmployee, possibleOrders, customerPreferences, serviceQuality);
        } else {
            // 부정확한 (랜덤) 선택 로직
            return selectRandomMenu(selectedEmployee, possibleOrders, customerPreferences);
        }
    }

    // 정확한 메뉴 선택 로직
    public List<MenuItem> selectAccurateMenu(Employee employee, List<MenuItem> possibleOrders,
                                             List<String> customerPreferences, double serviceQuality) {
        List<MenuItem> selectedItems = new ArrayList<>();

        // 선호도와 직원 전문성에 맞는 메뉴 분류
        List<MenuItem> preferredItems = findPreferredItems(possibleOrders, customerPreferences);
        List<MenuItem> typeMatchedItems = findTypeMatchedItems(possibleOrders, employee);

        // 최고 품질의 선택 시도 (타입+선호도+품질 일치)
        MenuItem premiumChoice = trySelectPremiumItem(preferredItems, typeMatchedItems, employee, serviceQuality);
        if (premiumChoice != null) {
            selectedItems.add(premiumChoice);
            // 고급 선택에 성공하면 추가 메뉴 제안 가능성 증가
            if (r.nextDouble() < 0.3 * serviceQuality) {
                suggestAdditionalItem(selectedItems, possibleOrders, customerPreferences, employee);
            }
            return selectedItems;
        }

        // 선호도+타입 모두 일치하는 메뉴 찾기
        List<MenuItem> bestItems = findBestItems(preferredItems, typeMatchedItems);
        if (!bestItems.isEmpty()) {
            MenuItem selected = bestItems.get(r.nextInt(bestItems.size()));
            selectedItems.add(selected);
            System.out.println(employee.name + "이(가) 손님 선호와 전문 분야에 맞는 " +
                    selected.name + "(" + selected.quality + ")을(를) 추천했습니다.");

            // 추가 메뉴 제안 (20% 확률)
            if (r.nextDouble() < 0.2 * serviceQuality) {
                suggestAdditionalItem(selectedItems, possibleOrders, customerPreferences, employee);
            }
            return selectedItems;
        }

        // 선호도만 일치하는 메뉴 선택
        if (!preferredItems.isEmpty()) {
            MenuItem selected = preferredItems.get(r.nextInt(preferredItems.size()));
            selectedItems.add(selected);
            System.out.println(employee.name + "이(가) 손님 선호에 맞는 " +
                    selected.name + "(" + selected.quality + ")을(를) 선택했습니다.");
            return selectedItems;
        }

        // 직원 타입만 일치하는 메뉴 선택
        if (!typeMatchedItems.isEmpty()) {
            MenuItem selected = typeMatchedItems.get(r.nextInt(typeMatchedItems.size()));
            selectedItems.add(selected);
            System.out.println(employee.name + "이(가) 자신의 전문 분야인 " +
                    selected.name + "(" + selected.quality + ")을(를) 선택했습니다.");
            return selectedItems;
        }

        // 그 외 - 랜덤 선택
        MenuItem randomChoice = possibleOrders.get(r.nextInt(possibleOrders.size()));
        selectedItems.add(randomChoice);
        System.out.println(employee.name + "이(가) " + randomChoice.name +
                "(" + randomChoice.quality + ")을(를) 선택했습니다.");

        return selectedItems;
    }

    // 부정확한 랜덤 메뉴 선택
    public List<MenuItem> selectRandomMenu(Employee employee, List<MenuItem> possibleOrders,
                                           List<String> customerPreferences) {
        List<MenuItem> selectedItems = new ArrayList<>();
        MenuItem randomItem = possibleOrders.get(r.nextInt(possibleOrders.size()));
        selectedItems.add(randomItem);

        System.out.println(employee.name + "이(가) 아무 생각 없이 " + randomItem.name +
                "(" + randomItem.quality + ")을(를) 선택했습니다.");

        // 선택한 메뉴가 선호 메뉴인지 확인
        boolean isPreferred = checkItemPreference(randomItem, customerPreferences);
        if (isPreferred) {
            System.out.println("우연히도 손님이 좋아할 만한 메뉴를 선택했습니다!");
        } else {
            System.out.println("손님의 만족도가 떨어질 수 있습니다.");
        }

        return selectedItems;
    }

    // 선호하는 메뉴 목록 찾기
    public List<MenuItem> findPreferredItems(List<MenuItem> possibleOrders, List<String> customerPreferences) {
        List<MenuItem> preferredItems = new ArrayList<>();
        for (MenuItem item : possibleOrders) {
            if (checkItemPreference(item, customerPreferences)) {
                preferredItems.add(item);
            }
        }
        return preferredItems;
    }

    // 직원 타입에 맞는 메뉴 목록 찾기
    public List<MenuItem> findTypeMatchedItems(List<MenuItem> possibleOrders, Employee employee) {
        List<MenuItem> typeMatchedItems = new ArrayList<>();
        for (MenuItem item : possibleOrders) {
            if ((employee.type == Employee.BARISTA && item.type == MenuItem.DRINK) ||
                    (employee.type == Employee.COOK && item.type == MenuItem.FOOD) ||
                    (employee.type == Employee.ALLROUNDER)) {
                typeMatchedItems.add(item);
            }
        }
        return typeMatchedItems;
    }

    // 고품질 아이템 선택 시도
    public MenuItem trySelectPremiumItem(List<MenuItem> preferredItems, List<MenuItem> typeMatchedItems,
                                         Employee employee, double serviceQuality) {
        // 선호도와 타입이 모두 일치하는 아이템 중 고품질 아이템 찾기
        for (MenuItem item : preferredItems) {
            if (typeMatchedItems.contains(item) && serviceQuality > 0.6) {
                if ((item.quality.equals(MenuQuality.GOOD) || item.quality.equals(MenuQuality.EXCELLENT)) &&
                        r.nextDouble() < serviceQuality) {

                    System.out.println(employee.name + "이(가) 손님의 취향과 고급 메뉴 " + item.name +
                            "(" + item.quality + ")을(를) 완벽하게 추천했습니다!");
                    System.out.println("손님이 매우 만족할 것 같습니다.");

                    return item;
                }
            }
        }
        return null;
    }

    // 최고의 메뉴 아이템 찾기 (선호도와 타입 모두 일치)
    public List<MenuItem> findBestItems(List<MenuItem> preferredItems, List<MenuItem> typeMatchedItems) {
        List<MenuItem> bestItems = new ArrayList<>();
        for (MenuItem item : preferredItems) {
            if (typeMatchedItems.contains(item)) {
                bestItems.add(item);
            }
        }
        return bestItems;
    }

    // 가장 적합한 직원 선택 (타입과 스킬)
    public Employee selectBestEmployee(List<Employee> employees) {
        if (employees.size() == 1) {
            return employees.get(0);
        }

        // 랜덤 요소 있게 직원 선택 (단, 능력이 좋은 직원이 선택될 확률 높게)
        List<Employee> candidatePool = new ArrayList<>();

        // 기본: 모든 직원을 1번씩 후보 풀에 추가
        candidatePool.addAll(employees);

        // 추가: 능력치가 높은 직원은 능력치에 비례해 더 많이 추가
        for (Employee emp : employees) {
            // 스킬 레벨에 따라 추가 - 최대 5번까지
            for (int i = 0; i < emp.speedSkill; i++) {
                candidatePool.add(emp);
            }

            // 올라운더는 추가 보너스로 더 추가
            if (emp.type == Employee.ALLROUNDER) {
                candidatePool.add(emp);
                candidatePool.add(emp);
            }
        }

        // 풀에서 랜덤 선택
        return candidatePool.get(r.nextInt(candidatePool.size()));
    }

    // 직원의 서비스 품질 계산 (추가 메서드)
    public double getServiceQuality(Employee employee) {
        double baseQuality = 0.3 + (employee.speedSkill * 0.1); // 기본 품질 (0.4~0.8)

        // 직원 타입에 따른 보정
        switch (employee.type) {
            case Employee.AMATEUR:
                baseQuality *= 0.7; // 수습은 30% 감소
                break;
            case Employee.BARISTA:
            case Employee.COOK:
                baseQuality *= 1.1; // 전문가는 10% 증가
                break;
            case Employee.CASHIER:
                baseQuality *= 1.0; // 캐셔는 기본
                break;
            case Employee.ALLROUNDER:
                baseQuality *= 1.3; // 올라운더는 30% 증가
                break;
        }

        // 품질은 0.2~1.0 사이로 제한
        return Math.max(0.2, Math.min(1.0, baseQuality));
    }

    // 추가 메뉴 제안
    public void suggestAdditionalItem(List<MenuItem> selectedItems, List<MenuItem> possibleOrders,
                                      List<String> customerPreferences, Employee employee) {
        // 이미 선택된 메뉴의 타입 확인
        int selectedType = selectedItems.get(0).type;

        // 반대 타입의 메뉴만 추출
        List<MenuItem> complementaryItems = new ArrayList<>();
        for (MenuItem item : possibleOrders) {
            if (item.type != selectedType) {
                complementaryItems.add(item);
            }
        }

        if (!complementaryItems.isEmpty()) {
            // 선호도에 맞는 메뉴 우선 추천
            List<MenuItem> preferredComplements = new ArrayList<>();
            for (MenuItem item : complementaryItems) {
                if (checkItemPreference(item, customerPreferences)) {
                    preferredComplements.add(item);
                }
            }

            MenuItem additionalItem;
            if (!preferredComplements.isEmpty() && r.nextDouble() < getServiceQuality(employee)) {
                additionalItem = preferredComplements.get(r.nextInt(preferredComplements.size()));
                System.out.println(employee.name + "이(가) 손님 선호에 맞는 추가 메뉴 " +
                        additionalItem.name + "을(를) 제안했습니다.");
            } else {
                additionalItem = complementaryItems.get(r.nextInt(complementaryItems.size()));
                System.out.println(employee.name + "이(가) 추가 메뉴 " +
                        additionalItem.name + "을(를) 제안했습니다.");
            }

            selectedItems.add(additionalItem);
            System.out.println("세트 메뉴가 구성되었습니다.");
        }
    }

    public static double getSelectionAccuracy(Employee employee) {
        double selectionAccuracy = 0.3; // 기본 정확도

        // 직원 타입에 따른 보정
        switch (employee.type) {
            case Employee.AMATEUR:
                selectionAccuracy *= 0.5; // 수습은 정확도 50% 감소
                break;
            case Employee.BARISTA:
                selectionAccuracy *= 1.2; // 바리스타는 정확도 20% 증가
                break;
            case Employee.CASHIER:
                selectionAccuracy *= 1.0; // 캐셔는 기본 정확도
                break;
            case Employee.COOK:
                selectionAccuracy *= 1.2; // 요리사는 정확도 20% 증가
                break;
            case Employee.ALLROUNDER:
                selectionAccuracy *= 1.5; // 올라운더는 정확도 50% 증가
                break;
        }

        // 스피드 스킬에 따른 추가 보정 (스킬 1당 5% 증가)
        selectionAccuracy += (employee.speedSkill * 0.05);
        return selectionAccuracy;
    }

    public void researchMenu() {
        boolean cancel = false;
        while (!cancel) {
            System.out.println("=== 연구 시스템 ===");
            System.out.println("현재 보유 연구 포인트: " + researchManager.researchPoints);
            System.out.println("1. 연구 목록 보기");
            System.out.println("2. 연구 시작하기");
            System.out.println("3. 진행 중인 연구 상태 확인");
            System.out.println("4. 완료된 연구로 메뉴 개발하기");
            System.out.println("5. 돌아가기");
            System.out.print("선택: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    showResearchList();
                    break;
                case 2:
                    startNewResearch();
                    break;
                case 3:
                    checkActiveResearch();
                    break;
                case 4:
                    developNewMenu();
                    break;
                case 5:
                    cancel = true;
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }


    public void showResearchList() {
        System.out.println("=== 가능한 연구 목록 ===");
        if (researchManager.availableRecipe.isEmpty()) {
            System.out.println("현재 시작 가능한 연구가 없습니다.");
        } else {
            for (int i = 0; i < researchManager.availableRecipe.size(); i++) {
                Research research = researchManager.availableRecipe.get(i);
                System.out.println((i + 1) + ". " + research.name
                        + " (필요 연구 포인트: " + research.researchPoints + ")");
            }
        }
    }

    public void startNewResearch() {
        if (researchManager.availableRecipe.isEmpty()) {
            System.out.println("현재 시작 가능한 연구가 없습니다.");
            return;
        }

        showResearchList();

        System.out.print("시작할 연구 번호 (취소: 0): ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice <= 0 || choice > researchManager.availableRecipe.size()) {
            System.out.println("연구 시작을 취소했습니다.");
            return;
        }

        int index = choice - 1;
        Research selected = researchManager.availableRecipe.get(index);

        System.out.println("현재 보유 연구 포인트: " + researchManager.researchPoints);
        System.out.println(selected.name + "에 투자할 연구 포인트를 입력하세요.");
        System.out.print("투자 포인트 (필요 총 포인트: " + selected.researchPoints + "): ");

        int investPoints = sc.nextInt();
        sc.nextLine();

        if (investPoints <= 0) {
            System.out.println("투자를 취소했습니다.");
            return;
        }

        // 연구 시작
        researchManager.startResearch(index, investPoints);
    }

    public void checkActiveResearch() {
        System.out.println("=== 진행 중인 연구 ===");

        if (researchManager.activeRecipe.isEmpty()) {
            System.out.println("현재 진행 중인 연구가 없습니다.");
            return;
        }

        for (int i = 0; i < researchManager.activeRecipe.size(); i++) {
            Research research = researchManager.activeRecipe.get(i);
            System.out.println((i + 1) + ". " + research.name
                    + " - 진행률: " + String.format("%.1f", research.researchPercentage()) + "%"
                    + " (" + research.completionPoints + "/" + research.researchPoints + ")");
        }

        System.out.println("추가 연구 포인트를 투자하시겠습니까? (1: 예, 0: 아니오)");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            System.out.print("연구 번호 선택: ");
            int researchIndex = sc.nextInt();
            sc.nextLine();

            if (researchIndex <= 0 || researchIndex > researchManager.activeRecipe.size()) {
                System.out.println("잘못된 번호입니다.");
                return;
            }

            Research selected = researchManager.activeRecipe.get(researchIndex - 1);

            System.out.println("현재 보유 연구 포인트: " + researchManager.researchPoints);
            System.out.print("투자할 포인트: ");
            int investPoints = sc.nextInt();
            sc.nextLine();

            if (investPoints <= 0) {
                System.out.println("투자를 취소했습니다.");
                return;
            }

            if (researchManager.researchPoints < investPoints) {
                System.out.println("보유한 연구 포인트가 부족합니다.");
                return;
            }

            // 연구 포인트 투자
            researchManager.researchPoints -= investPoints;
            boolean completed = selected.processResearch_points(investPoints);

            System.out.println(selected.name + "에 " + investPoints + " 포인트를 투자했습니다.");
            System.out.println("현재 진행률: " + String.format("%.1f", selected.researchPercentage()) + "%");

            // 연구 완료 처리
            if (completed) {
                System.out.println(selected.name + " 연구가 완료되었습니다!");
                researchManager.activeRecipe.remove(selected);
                researchManager.completedRecipe.add(selected);

                // 새로운 연구 주제 생성 기회
                researchManager.generateNewResearchTopics();
            }
        }
    }

    public void developNewMenu() {
        System.out.println("=== 새 메뉴 개발 ===");

        if (researchManager.completedRecipe.isEmpty()) {
            System.out.println("완료된 연구가 없습니다. 먼저 연구를 완료해주세요.");
            return;
        }

        System.out.println("완료된 연구 목록:");
        for (int i = 0; i < researchManager.completedRecipe.size(); i++) {
            Research research = researchManager.completedRecipe.get(i);
            System.out.println((i + 1) + ". " + research.name + " (" +
                    (research.type == MenuItem.DRINK ? "음료" : "음식") + " - " + research.typeName + ")");
        }

        System.out.print("개발할 메뉴의 연구 번호 (취소: 0): ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice <= 0 || choice > researchManager.completedRecipe.size()) {
            System.out.println("메뉴 개발을 취소했습니다.");
            return;
        }

        Research selectedResearch = researchManager.completedRecipe.get(choice - 1);

        // 새 메뉴 생성
        MenuItem newMenuItem = researchManager.createNewMenuItem(selectedResearch, coffeeShop.ingredients);

        // 메뉴 정보 표시
        System.out.println("=== 새로운 메뉴가 개발되었습니다! ===");
        System.out.println("이름: " + newMenuItem.name);
        System.out.println("종류: " + (newMenuItem.type == MenuItem.DRINK ? "음료" : "음식") + " (" + newMenuItem.typeName + ")");
        System.out.println("품질: " + newMenuItem.quality);
        System.out.println("가격: " + newMenuItem.price + "원");

        System.out.println("필요 재료:");
        for (RecipeItem recipeItem : newMenuItem.recipe.recipeItems) {
            System.out.println("- " + recipeItem.ingredientName + ": " + recipeItem.quantity + "개");
        }

        System.out.println("이 메뉴를 추가하시겠습니까? (1: 예, 0: 아니오)");
        int addChoice = sc.nextInt();
        sc.nextLine();

        if (addChoice == 1) {
            coffeeShop.addMenuItem(newMenuItem);
            System.out.println(newMenuItem.name + " 메뉴가 추가되었습니다!");
        } else {
            System.out.println("메뉴 추가를 취소했습니다.");
        }
    }


    public void runAutoMode() {
        System.out.println("=== 자동 모드로 커피 하나 팔기 ===");
        todayWeather = new Weather(Weather.SUNNY);
        List<MenuItem> menu = coffeeShop.menuItems;
        if (!menu.isEmpty()) {
            List<MenuItem> testOrder = List.of(menu.get(0));
            coffeeShop.processOrder(testOrder);
            System.out.println("잔액: " + coffeeShop.money);
        } else {
            System.out.println("메뉴가 비어 있어서 주문을 처리할 수 없습니다.");
        }
    }

    public void handleOrder() {
        selectWeather();  // 날씨 선택
        processPendingDeliveries();  // 배달 처리
        customerTurn();  // 손님 처리
        endDay();  // 하루 마감
    }
}

