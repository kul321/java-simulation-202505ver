import java.util.Random;
import java.util.Scanner;

public class Project2 {
    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();
    // 카페 기본 설정
    static int day = 1;                     // 현재 날짜
    static int cafeMoney = 5000;            // 초기 소지금
    static int clean = 10;                  // 초기 청결도 (최대 10)
    static double averageSatisfaction;      // 평균 고객 만족도

    // 메뉴 관련 데이터
    static String[] menu;                   // 메뉴 이름 배열
    static int[] basePrice;                 // 메뉴 기본 가격
    static int[] price;                     // 메뉴 판매 가격
    static int[] stock;                     // 메뉴 재고
    static int[] menuQuality;               // 메뉴 품질 레벨 (1:저급, 2:중급, 3:고급)
    static int[] manufactureTime;           // 메뉴별 제조 시간
    static double priceMultiplier = 1.0;    // 전체 메뉴 가격 배율
    static double medium_quality_multiplier = 1.5;  // 중급 품질 가격 배율
    static double high_quality_multiplier = 2.0;    // 고급 품질 가격 배율

    // 운영 관련 데이터
    static int customerCount = 50;          // 기본 손님 수
    static int skippedCustomers = 0;        // 영업시간 초과로 받지 못한 손님 수
    static int max_work_hours = 8;          // 하루 최대 노동 시간
    static int maxWorkHours = 8;            // 기본 작업 가능 시간
    static int additionalWorkHours = 4;     // 추가 작업 가능 시간
    static int totalWorkHours = maxWorkHours + additionalWorkHours;
    static int remaining_hours = max_work_hours;

    // 직원 관련 데이터
    static int workerCount = 0;             // 현재 알바생 수
    static int max_workers = 2;             // 최대 알바생 수
    static int currentWorkerCount = 0;      // 현재 고용된 알바생 수
    static int[] workerLevels = new int[max_workers];  // 알바생 등급 배열

    // 시설 관련 데이터
    static int interiorLevel = 1;           // 인테리어 레벨
    static int coffeeMachineLevel = 1;      // 커피 머신 레벨
    static int blenderLevel = 1;            // 블렌더 레벨
    static int ovenLevel = 1;               // 오븐 레벨
    static int[] interiorItems = new int[5]; // 각 인테리어 아이템 보유 여부

    // 디저트 관련 데이터
    static String[] dessertTypes = {"쿠키", "빵", "케이크"};         // 디저트 종류
    static int[] dessertPrices = {200, 500, 1000};                // 디저트 판매가
    static int[] dessertCosts = {100, 250, 500};                  // 디저트 제조원가
    static int[] dessertStocks = {0, 0, 0};                       // 디저트 재고
    static int[] dessertSatisfactions = {2, 5, 10};               // 디저트별 만족도 효과

    public static void main(String[] args) {
        // 메뉴 개수 입력받기
        System.out.print("커피 메뉴의 개수를 입력하세요: ");
        int menuCount = sc.nextInt();
        sc.nextLine();

        // 배열 초기화
        menu = new String[menuCount];
        stock = new int[menuCount];
        price = new int[menuCount];
        //ingredientUsage = new int[menuCount];
        manufactureTime = new int[menuCount];

        // 메뉴 초기화 (입력값으로 설정)
        initializeMenu(menu, stock, price);


        //프로그램 연속 여부
        boolean continueProgram = true;

        while (continueProgram) {

            int dailyOperatingHours = manageDailySchedule(); //하루 영업시간
            int eventTime = getEventTime(); // 무료 이벤트 시간 입력
            int eventCapacity = getEventCapacity(); // 무료 이벤트 최대 인원 입력

            // 각 메뉴별 제조 시간을 랜덤으로 설정
            setRandomManufactureTimes(manufactureTime);
            System.out.println("=== 제조 시간 확인 ===");
            for (int i = 0; i < manufactureTime.length; i++) {
                System.out.printf("%s: %d분\n", menu[i], manufactureTime[i]);
            }

            // 하루 손님 수 계산
            int baseCustomerCount = calculateCustomerCount(dailyOperatingHours);

            // 손님들의 선호 메뉴 랜덤 생성
            String[] customerPreference = getCustomerPreferences(menu, baseCustomerCount);

            // 초기 데이터 설정
            int[] customerSatisfaction = initializeCustomerSatisfaction(
                    customerCount, calculateWorkerEffect(), calculateInteriorEffect()
            );
            int[] preferenceCount = new int[menuCount];
            int[] soldDesserts = new int[dessertTypes.length];

            // 손님별 주문 처리 및 영업 수행
            int[] results = handleCustomers(
                    menu, stock, price, manufactureTime, customerPreference,
                    customerSatisfaction, preferenceCount, eventTime, eventCapacity,
                    dailyOperatingHours, soldDesserts);

            //하루 결과 표시
            int totalSales = results[0];
            int totalFreeCoffees = results[1];
            int unusedTime = results[2];
            int skippedCustomers = results[3];
            System.out.printf("디버깅: 초과된 손님 수는 %d명입니다.\n", skippedCustomers);

            // 하루 평균 만족도 계산 (폐업 조건 포함)
            averageSatisfaction = calculateAverageSatisfaction(customerSatisfaction);

            // 무료 이벤트 성공률 계산 (인원참가수, 만족도 포함)
            double eventSuccessRate = calculateEventSuccessRate(totalFreeCoffees, eventCapacity, baseCustomerCount, averageSatisfaction);

            //손님 수 조정
            int adjustedCustomerCount = adjustCustomerCount(eventSuccessRate, baseCustomerCount);
            baseCustomerCount = adjustedCustomerCount;
            remaining_hours = unusedTime / 60;

            // 영업 결과 출력
            printDailyResults(
                    totalSales, totalFreeCoffees, averageSatisfaction, eventSuccessRate, menu, stock, customerPreference.length, unusedTime, soldDesserts, skippedCustomers);

            // 다음 날의 재고 추천
            recommendStock(preferenceCount, menu);

            //프로그램 계속
            continueProgram = checkContinue();


        }

        System.out.println("프로그램을 종료합니다.");
    }

    //메뉴 초기화 및 기본 설정
    public static void initializeMenu(String[] menu, int[] stock, int[] price) {
        menuQuality = new int[menu.length];
        basePrice = new int[menu.length];

        for (int i = 0; i < menu.length; i++) {
            System.out.println((i + 1) + "번째 커피 이름을 입력하세요:");
            menu[i] = sc.nextLine();

            System.out.println(menu[i] + "의 재고를 입력하세요:");
            stock[i] = sc.nextInt();
            sc.nextLine();

            System.out.println(menu[i] + "의 기본 가격을 입력하세요:");
            basePrice[i] = sc.nextInt();
            sc.nextLine();

            System.out.println(menu[i] + "의 품질을 선택하세요 (1: 저급, 2: 중급, 3: 고급):");
            menuQuality[i] = sc.nextInt();
            sc.nextLine();

            // 품질에 따른 최종 가격 계산
            price[i] = calculatePriceByQuality(basePrice[i], menuQuality[i]);
        }

    }

    //하루 일과 관리 및 작업 선택
    public static int manageDailySchedule() {
        int remainingWorkHours = totalWorkHours;
        System.out.println("\n=== 카페를 오픈을 위한 하루를 준비하세요 ===");
        int additionalTasks = remaining_hours + 1;

        while (additionalTasks > 0) {
            displayDailyMenu(additionalTasks);
            int choice = getValidMenuChoice();

            if (!processMenuChoice(choice, additionalTasks)) {
                return 0;
            }
            additionalTasks--;
        }
        return 0;
    }

    //손님 응대 및 주문 처리
    public static int[] handleCustomers(
            String[] menu, int[] stock, int[] price, int[] manufactureTime,
            String[] customerPreference, int[] customerSatisfaction,
            int[] preferenceCount, int eventTime, int eventCapacity,
            int operatingHours, int[] soldDesserts) {

        int currentTime = 0;
        int totalFreeCoffees = 0;
        int totalSales = 0;
        int unusedTime = 0;

        for (int i = 0; i < customerPreference.length; i++) {
            System.out.printf("현재 시간: %d분 (손님 %d)\n", currentTime, i + 1);

            // 영업시간 체크
            if (currentTime >= operatingHours * 60) {
                handleSkippedCustomer(i);
                continue;
            }

            // 커피 주문 처리
            int[] orderResult = processOrder(i, currentTime, customerPreference[i],
                    eventTime, eventCapacity, totalFreeCoffees, customerSatisfaction[i]);

            currentTime = orderResult[0];
            totalFreeCoffees = orderResult[1];
            totalSales = orderResult[2];
            customerSatisfaction[i] = orderResult[3];

            // 디저트 주문 처리
            processDessertOrder(i, customerSatisfaction, soldDesserts);
        }

        unusedTime = calculateUnusedTime(operatingHours, currentTime);
        return new int[]{totalSales, totalFreeCoffees, unusedTime, skippedCustomers};
    }

    //스킵되는 손님
    private static void handleSkippedCustomer(int customerIndex) {
        skippedCustomers++;
        System.out.printf("손님 %d: 영업시간 초과로 처리되지 못하였습니다.\n", customerIndex + 1);
    }

    //이벤트 시간 입력
    public static int getEventTime() {
        System.out.println("무료 이벤트 시간을 입력하세요 (분 단위):");
        return sc.nextInt();
    }

    //이벤트 인원 입력
    public static int getEventCapacity() {
        System.out.println("무료 이벤트 최대 인원을 입력하세요:");
        return sc.nextInt();
    }

    //각 메뉴의 제조 시간을 랜덤으로 설정 (3~15분)
    public static void setRandomManufactureTimes(int[] manufactureTime) {
        for (int i = 0; i < manufactureTime.length; i++) {
            ;
            manufactureTime[i] = random.nextInt(6) + 3;

        }
    }

    //하루 동안 방문할 손님 수 계산
    public static int calculateCustomerCount(int dailyOperatingHours) {
        int minCustomersPerHour = 3;
        int maxCustomersPerHour = 10;
        int totalCustomers = 0;


        // 가격 배율에 따른 손님 수 조정
        if (priceMultiplier > 1.0) {
            // 가격이 올라갈수록 손님 감소
            minCustomersPerHour = (int)(minCustomersPerHour / priceMultiplier);
            maxCustomersPerHour = (int)(maxCustomersPerHour / priceMultiplier);
        } else if (priceMultiplier < 1.0) {
            // 가격이 내려갈수록 손님 증가
            minCustomersPerHour = (int)(minCustomersPerHour * (2 - priceMultiplier));
            maxCustomersPerHour = (int)(maxCustomersPerHour * (2 - priceMultiplier));
        }

        for (int i = 0; i < dailyOperatingHours; i++) {
            totalCustomers += random.nextInt(maxCustomersPerHour - minCustomersPerHour + 1) + minCustomersPerHour;
        }
        return totalCustomers;
    }

    //손님 선호 메뉴를 랜덤으로 생성
    public static String[] getCustomerPreferences(String[] menu, int customerCount) {
        String[] preferences = new String[customerCount];
        for (int i = 0; i < customerCount; i++) {
            preferences[i] = menu[random.nextInt(menu.length)];
        }
        return preferences;
    }

    // 특정 메뉴 찾기
    public static int findCoffeeIndex(String[] menu, String preferredCoffee) {
        for (int i = 0; i < menu.length; i++) {
            if (menu[i].equals(preferredCoffee)) {
                return i;
            }
        }
        return -1;
    }

    //대체 메뉴 찾기
    public static int findAlternativeMenu(int[] stock, int[] manufactureTime) {
        int minTime = -1; // 최소 제조 시간을 초기화
        int index = -1; // 대체 음료의 인덱스 (-1은 찾지 못했을 때의 값)

        // 재고가 있는 첫 번째 음료를 초기값으로 설정
        for (int i = 0; i < stock.length; i++) {
            if (stock[i] > 0) { // 재고가 있는 경우
                minTime = manufactureTime[i]; // 최소 제조 시간을 현재 음료로 초기화
                index = i;
                break;
            }
        }

        // 나머지 음료와 비교하여 최소값 갱신
        for (int i = 0; i < stock.length; i++) {
            if (stock[i] > 0 && manufactureTime[i] < minTime) {
                minTime = manufactureTime[i]; // 최소값 갱신
                index = i;
            }
        }

        return index;
    }

    //커피 주문 진행
    private static int[] processOrder(int customerIndex, int currentTime,
                                      String preferredCoffee, int eventTime, int eventCapacity,
                                      int totalFreeCoffees, int satisfaction) {

        int coffeeIndex = findCoffeeIndex(menu, preferredCoffee);
        if (coffeeIndex < 0) {
            return new int[]{currentTime, totalFreeCoffees, 0, satisfaction};
        }

        if (stock[coffeeIndex] <= 0) {
            return handleOutOfStock(customerIndex, currentTime, eventTime,
                    totalFreeCoffees, satisfaction, eventCapacity);
        }

        return handleInStock(customerIndex, currentTime, coffeeIndex,
                eventTime, eventCapacity, totalFreeCoffees, satisfaction);
    }

    //재고 부족 처리
    private static int[] handleOutOfStock(int customerIndex, int currentTime,
                                          int eventTime, int totalFreeCoffees, int satisfaction, int eventCapacity) {

        satisfaction -= 20;
        System.out.printf("재고 부족! [만족도 %d]\n", -20);

        boolean isEventTime = currentTime < eventTime;
        int alternativeIndex = findAlternativeMenu(stock, manufactureTime);

        if (isEventTime && alternativeIndex >= 0 && totalFreeCoffees < eventCapacity) {
            stock[alternativeIndex]--;
            int baseSatisfaction = random.nextInt(21) - 10;
            int satisfactionChange = 15 + baseSatisfaction + (price[alternativeIndex] / 1000);
            satisfaction += satisfactionChange;
            totalFreeCoffees++;
            currentTime += manufactureTime[alternativeIndex] * (1 - (calculateWorkerEffect() * 0.05));

            System.out.printf("%s이 대체 음료로 무료 제공되었습니다! (%d분 소요) [만족도 +%d]\n",
                    menu[alternativeIndex], manufactureTime[alternativeIndex], satisfactionChange);
        }

        return new int[]{currentTime, totalFreeCoffees, 0, satisfaction};
    }

    // 재고 존재 시의 처리
    private static int[] handleInStock(int customerIndex, int currentTime,
                                       int coffeeIndex, int eventTime, int eventCapacity,
                                       int totalFreeCoffees, int satisfaction) {

        stock[coffeeIndex]--;
        currentTime += manufactureTime[coffeeIndex] * (1 - (calculateWorkerEffect() * 0.05));  // 알바생 레벨당 5% 시간 감소
        boolean isEventTime = currentTime < eventTime;
        int baseSatisfaction = random.nextInt(21) - 10;
        int qualityBonus = calculateQualityBonus(menuQuality[coffeeIndex]);
        int totalSales = 0;

        if (isEventTime && totalFreeCoffees < eventCapacity) {
            satisfaction += 30 + baseSatisfaction + qualityBonus + (price[coffeeIndex] / 1000);
            totalFreeCoffees++;
            System.out.printf("손님 %d: %s를 무료로 받았습니다! (%d분 소요) [만족도 +%d]\n",
                    customerIndex + 1, menu[coffeeIndex], manufactureTime[coffeeIndex],
                    30 + baseSatisfaction + qualityBonus + (price[coffeeIndex] / 1000));
        } else {
            satisfaction += 20 + baseSatisfaction + qualityBonus + (price[coffeeIndex] / 1000);
            totalSales = price[coffeeIndex];
            cafeMoney += price[coffeeIndex];
            System.out.printf("손님 %d: %s를 유료로 사갔습니다! (%d분 소요, %d원) [만족도 +%d]\n",
                    customerIndex + 1, menu[coffeeIndex], manufactureTime[coffeeIndex],
                    price[coffeeIndex], 20 + baseSatisfaction + qualityBonus + (price[coffeeIndex] / 1000));
        }

        return new int[]{currentTime, totalFreeCoffees, totalSales, satisfaction};
    }

    //디저트 주문 진행
    private static void processDessertOrder(int customerIndex,
                                            int[] customerSatisfaction, int[] soldDesserts) {
        if (random.nextBoolean()) {
            int dessertIndex = random.nextInt(dessertTypes.length);
            if (dessertStocks[dessertIndex] > 0) {
                dessertStocks[dessertIndex]--;
                soldDesserts[dessertIndex]++;
                cafeMoney += dessertPrices[dessertIndex];
                customerSatisfaction[customerIndex] += dessertSatisfactions[dessertIndex];
                System.out.printf("%s을(를) 추가 구매했습니다! (가격: %d원) [만족도 +%d]\n",
                        dessertTypes[dessertIndex], dessertPrices[dessertIndex],
                        dessertSatisfactions[dessertIndex]);
            }
        }
    }


    //알바생 효과 계산
    private static int calculateWorkerEffect() {
        int effect = 0;
        for (int level : workerLevels) {
            effect += level * 2; // 등급당 +2 효과
        }
        return effect;
    }

    //인테리어 효과 계산
    private static int calculateInteriorEffect() {
        return interiorLevel * 3; // 인테리어 레벨당 +3 효과
    }

    //잉여시간 계산
    private static int calculateUnusedTime(int operatingHours, int currentTime) {
        return Math.max(0, operatingHours * 60 - currentTime);
    }



    //평균 만족도 계산
    public static double calculateAverageSatisfaction(int[] customerSatisfaction) {
        int sum = 0;
        for (int value : customerSatisfaction) {
            sum += value;
        }
        double average = (double) sum / customerSatisfaction.length;

        // 청결도가 낮을 경우 패널티
        if (clean > 0 && clean < 5) {
            System.out.println("청결도가 다소 낮아 만족도가 떨어집니다.");
            average -= 5;
        }
        if (clean == 0) {
            System.out.println("카페가 너무 더러워져서 만족도가 크게 하락합니다.");
            average -= 10;
        }

        // 만족도가 0 이하인 경우 프로그램 종료
        if (average <= 0) {
            System.out.println("\n=== 만족도가 0이 되었습니다. 카페를 폐업합니다. ===");
            System.exit(0); // 프로그램 종료
        }

        return average;
    }

    //이벤트 성공률 계산
    public static double calculateEventSuccessRate(int totalFreeCoffees, int eventCapacity, int customerCount, double averageSatisfaction) {
        int totalEventCustomers = Math.min(eventCapacity, customerCount);
        double baseSuccessRate = (totalFreeCoffees / (double) totalEventCustomers) * 100;

        // 성공률에 만족도 영향을 추가 (만족도가 높을수록 성공률 증가)
        double satisfactionBonus = (averageSatisfaction - 50) / 2; // 만족도 50 기준으로 ± 영향
        double adjustedSuccessRate = Math.max(0, Math.min(baseSuccessRate + satisfactionBonus, 100)); // 0~100 사이로 제한

        return adjustedSuccessRate;


    }

    // 품질에 따른 가격 계산을 위한 메소드
    public static int calculatePriceByQuality(int basePrice, int quality) {
        switch (quality) {
            case 2:  // 중급
                return (int) (basePrice * medium_quality_multiplier);
            case 3:  // 고급
                return basePrice * (int) high_quality_multiplier;
            default: // 저급
                return basePrice;
        }
    }
    // 품질에 따른 만족도 보너스 계산 메소드
    public static int calculateQualityBonus(int quality) {
        switch (quality) {
            case 2:  // 중급
                return 10;
            case 3:  // 고급
                return 20;
            default: // 저급
                return 0;
        }
    }

    //업그레이드 효과: 고객 만족도 증가
    public static int[] initializeCustomerSatisfaction(int customerCount, int workerEffect, int interiorEffect) {
        int[] satisfaction = new int[customerCount];
        int baseSatisfaction = 50 + workerEffect + interiorEffect; // 기본 만족도: 50 + 효과

        for (int i = 0; i < satisfaction.length; i++) {
            satisfaction[i] = Math.min(100, baseSatisfaction); // 최대 100 제한
        }

        return satisfaction;
    }

    // 성공률에 따른 다음 날 손님 수 조정
    public static int adjustCustomerCount(double eventSuccessRate, int currentCustomerCount) {

        int adjustment = (int) ((eventSuccessRate - 50) / 10); // 성공률 50 기준으로 ± 손님 수 변화

        return Math.max(1, currentCustomerCount + adjustment); // 최소 손님 수 1명 보장
    }

    //통계표
    public static void printDailyResults(
            int totalSales, int totalFreeCoffees, double averageSatisfaction,
            double eventSuccessRate, String[] menu, int[] stock, int customerCount, int unusedTime, int[] soldDesserts, int skippedCustomers) {
        System.out.println("\n=== 오늘의 영업 종료 ===");
        System.out.println("총 매출: " + totalSales + "원");
        System.out.println("무료로 제공된 커피 수: " + totalFreeCoffees + "잔");
        System.out.println("평균 만족도: " + averageSatisfaction);
        System.out.printf("무료 이벤트 성공률: %.2f%%\n", eventSuccessRate);
        System.out.println("방문한 총 손님 수: " + customerCount + "명");
        System.out.printf("잉여 영업시간: %d분\n", unusedTime);

        System.out.println("\n남은 커피 재고:");
        for (int i = 0; i < menu.length; i++) {
            System.out.println(menu[i] + ": " + stock[i] + "잔");
        }

        // 디저트 판매 결과 출력 (판매된 디저트만 표시)

        for (int i = 0; i < dessertTypes.length; i++) {
            if (soldDesserts[i] > 0) {
                System.out.println("\n디저트 판매 결과:");
                System.out.printf("%s: %d개 판매 (남은 재고: %d개)\n",
                        dessertTypes[i], soldDesserts[i], dessertStocks[i]);
            }

        }

        System.out.println("영업시간 초과로 처리되지 못한 손님 수: " + skippedCustomers);
    }

    //추천시스템
    public static void recommendStock(int[] preferenceCount, String[] menu) {
        System.out.println("\n=== 컴퓨터 추천 시스템 ===");
        for (int i = 0; i < preferenceCount.length; i++) {
            int recommendedQuantity = preferenceCount[i] > 0 ? preferenceCount[i] * 2 : 5;
            System.out.println(menu[i] + " - 추천 수량: " + recommendedQuantity + "잔");
        }
    }

    //프로그램 종료 확인
    public static boolean checkContinue() {
        System.out.println("\n다음 날로 넘어가시겠습니까? (1: 예, 0: 아니오)");
        int choice = sc.nextInt();
        return choice == 1;
    }



    //하루 일과 표시
    private static void displayDailyMenu(int remainingTasks) {
        System.out.printf("\n남은 추가 작업 가능 횟수: %d\n", remainingTasks);
        System.out.println("[작업 목록]");
        System.out.println("1. 청소");
        System.out.println("2. 재고 보충");
        System.out.println("3. 커피 품질 변경");
        System.out.println("4. 커피 가격 변경");
        System.out.println("5. 조리기기 업그레이드");
        System.out.println("6. 알바생 추가/변경");
        System.out.println("7. 인테리어 업그레이드");
        System.out.println("8. 디저트 추가/변경");
        System.out.println("9. 하루를 시작합니다.");
        System.out.print("\n원하는 작업을 선택하세요 : ");
    }

    //일과 메뉴 선택 입력
    private static int getValidMenuChoice() {
        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
    }

    //일과 메뉴 선택
    private static boolean processMenuChoice(int choice, int additionalTasks) {
        switch (choice) {
            case 1, 2, 3, 4, 5, 6, 7, 8 -> {
                executeTask(choice);
                return true;
            }
            case 9 -> {
                return startNewDay();
            }
            case 0 -> {
                System.out.println("추가 작업을 취소하고 다음으로 넘어갑니다.");
                return false;
            }
            default -> {
                System.out.println("잘못된 선택입니다. 다시 입력하세요.");
                return true;
            }
        }
    }

    //메뉴별 시행 메소드 분류
    private static void executeTask(int choice) {
        switch (choice) {
            case 1 -> cleanCafe();
            case 2 -> restockMenu();
            case 3 -> changeCoffeeQuality();
            case 4 -> changeCoffeePrice(averageSatisfaction);
            case 5 -> upgradeAppliance();
            case 6 -> manageWorker();
            case 7 -> upgradeInterior();
            case 8 -> manageDesserts();
        }
    }

    //새로운 하루 시작
    private static boolean startNewDay() {
        System.out.println("하루를 시작합니다.");
        updateDailyStatus();

        System.out.println("\n=== 새로운 하루가 시작됩니다 ===");
        int operatingHours = random.nextInt(12) + 1;
        System.out.println("오늘의 영업 시간: " + operatingHours + "시간");

        updateWorkHours(operatingHours);
        return false;
    }

    //일자 상태 업데이트
    private static void updateDailyStatus() {
        day++;
        clean--;
        if (clean <= 0) {
            System.out.println("\n=== 청결도가 0이 되었습니다. 손님 만족도가 크게 하락합니다. ===");
        }
        System.out.printf("날짜: %d일차, 청결도: %d\n", day, clean);
    }

    //작업 시간 업데이트(페널티 적용)
    private static void updateWorkHours(int operatingHours) {
        if (operatingHours > maxWorkHours) {
            int penalty = operatingHours - maxWorkHours;
            System.out.println("경고: 기본 작업시간 초과! 다음날 작업 가능 시간이 " + penalty + "시간 감소됩니다.");
            totalWorkHours = Math.max(0, maxWorkHours + additionalWorkHours - penalty);
        } else {
            totalWorkHours = maxWorkHours + additionalWorkHours;
        }
    }

    //메뉴 1. 카페 청소
    public static void cleanCafe() {
        System.out.println("\n===청소를 하시겠습니까? (1: 예, 0: 아니오)===");
        int choice = sc.nextInt();
        if (choice == 1) {
            clean = 10;
            System.out.println("청소가 완료되었습니다. 완벽하게 깨끗한 상태입니다.");
        } else System.out.println("청소를 하지 않습니다.");
    }

    //메뉴2. 재고 보충
    public static void restockMenu() {
        System.out.println("\n=== 재고 보충 ===");
        System.out.println("1. 전체 메뉴 재고 보충");
        System.out.println("2. 특정 메뉴 재고 보충");
        int choice = sc.nextInt();
        sc.nextLine(); // 입력 버퍼 처리

        switch (choice) {
            case 1 -> { //전체 메뉴 재고
                System.out.println("전체 메뉴의 재고를 추가합니다.");
                int totalCost = 0;
                System.out.println("각 메뉴당 몇 잔을 추가하시겠습니까?");
                int addCount = sc.nextInt();

                // 품질에 따른 비용 계산
                for (int i = 0; i < menu.length; i++) {
                    int baseCost = price[i];  // 기본 원가
                    // 품질에 따른 원가 계산
                    if (menuQuality[i] == 2) {  // 중급
                        baseCost = (int)(baseCost * 1.5);
                    } else if (menuQuality[i] == 3) {  // 고급
                        baseCost = baseCost * 2;
                    }
                    totalCost += (baseCost * addCount);
                }

                // 비용 확인 및 처리
                if (cafeMoney >= totalCost) {
                    for (int i = 0; i < stock.length; i++) {
                        stock[i] += addCount;
                    }
                    cafeMoney -= totalCost;
                    System.out.printf("전체 재고가 %d잔씩 보충되었습니다.\n", addCount);
                    System.out.printf("총 비용: %d원 (남은 소지금: %d원)\n", totalCost, cafeMoney);
                } else {
                    System.out.println("소지금이 부족합니다.");
                    System.out.printf("필요 금액: %d원, 보유 금액: %d원\n", totalCost, cafeMoney);
                }
            }
            case 2 -> { //개별 메뉴 재고
                System.out.println("재고를 보충할 메뉴를 선택하세요:");
                for (int i = 0; i < menu.length; i++) {
                    System.out.printf("%d. %s (현재 재고: %d잔)\n", i + 1, menu[i], stock[i]);
                }
                int menuChoice = sc.nextInt() - 1;
                if (menuChoice >= 0 && menuChoice < menu.length) {
                    System.out.print("추가할 재고 수를 입력하세요: ");
                    int additionalStock = sc.nextInt();

                    // 비용 계산
                    for (int i = 0; i < menu.length; i++) {
                        int cost = price[i] * additionalStock;

                        if (cafeMoney >= cost) {
                            stock[menuChoice] += additionalStock;
                            cafeMoney -= cost;
                            System.out.printf("%s의 재고가 %d잔 추가되었습니다.\n", menu[menuChoice], additionalStock);
                            System.out.printf("비용: %d원 (남은 소지금: %d원)\n", cost, cafeMoney);
                        } else {
                            System.out.println("소지금이 부족합니다.");
                            System.out.printf("필요 금액: %d원, 보유 금액: %d원\n", cost, cafeMoney);
                        }
                    }
                } else{
                    System.out.println("잘못된 메뉴 선택입니다.");
                }

            }
            default -> System.out.println("잘못된 선택입니다.");
        }
    }

    // 메뉴 3. 커피 품질 변경을 위한 메소드
    public static void changeCoffeeQuality() {
        System.out.println("\n=== 커피 품질 변경 ===");
        System.out.println("품질을 변경할 메뉴를 선택하세요:");

        for (int i = 0; i < menu.length; i++) {
            String qualityString = "";
            if (menuQuality[i] == 1) qualityString = "저급";
            else if (menuQuality[i] == 2) qualityString = "중급";
            else qualityString = "고급";

            System.out.printf("%d. %s (현재 품질: %s, 가격: %d원)\n",
                    i + 1, menu[i], qualityString, price[i]);
        }

        int menuChoice = sc.nextInt() - 1;
        if (menuChoice >= 0 && menuChoice < menu.length) {
            System.out.println("1. 품질 업그레이드 (비용: 5000원)");
            System.out.println("2. 품질 다운그레이드");
            System.out.println("0. 취소");

            int choice = sc.nextInt();
            switch (choice) {
                case 1: //품질 업그레이드
                    if (menuQuality[menuChoice] < 3 && cafeMoney >= 5000) {
                        menuQuality[menuChoice]++;
                        cafeMoney -= 5000;
                        // 새로운 가격 계산
                        price[menuChoice] = calculatePriceByQuality(basePrice[menuChoice], menuQuality[menuChoice]);
                        System.out.println("품질이 업그레이드되었습니다.");
                    } else {
                        if (menuQuality[menuChoice] >= 3) {
                            System.out.println("이미 최고 품질입니다.");
                        } else {
                            System.out.println("소지금이 부족합니다.");
                        }
                    }
                    break;
                case 2: //품질 다운그레이드
                    if (menuQuality[menuChoice] > 1) {
                        menuQuality[menuChoice]--;
                        // 새로운 가격 계산
                        if (menuQuality[menuChoice] == 1) {
                            price[menuChoice] = basePrice[menuChoice];
                        } else if (menuQuality[menuChoice] == 2) {
                            price[menuChoice] = (int)(basePrice[menuChoice] * 1.5);
                        }
                        System.out.println("품질이 다운그레이드되었습니다.");
                    } else {
                        System.out.println("이미 가장 낮은 품질입니다.");
                    }
                    break;
                default:
                    System.out.println("품질 변경을 취소합니다.");
            }
        }
    }

    //메뉴 4. 커피 가격 변경
    public static void changeCoffeePrice(double averageSatisfaction) {
        System.out.println("\n=== 커피 가격 변경 ===");
        System.out.println("1. 전체 메뉴 가격 조정");
        System.out.println("2. 개별 메뉴 가격 조정");
        System.out.println("0. 취소");

        int choice = sc.nextInt();
        sc.nextLine(); // 버퍼 처리

        switch (choice) {
            case 1: //전체 가격 조정
                System.out.println("가격 조정 비율을 입력하세요 (예: 1.2는 20% 인상, 0.8은 20% 인하):");
                double newMultiplier = sc.nextDouble();
                sc.nextLine(); // 버퍼 처리

                if (newMultiplier > 0) {
                    // 이전 가격 저장
                    int[] oldPrices = new int[price.length];
                    for (int i = 0; i < price.length; i++) {
                        oldPrices[i] = price[i];
                    }

                    // 새 가격 적용
                    priceMultiplier = newMultiplier;
                    for (int i = 0; i < price.length; i++) {
                        price[i] = (int)(basePrice[i] * priceMultiplier);
                    }

                    // 가격 변동에 따른 만족도 조정
                    if (newMultiplier > 1.0) {
                        System.out.println("가격이 올라 손님들의 만족도가 감소합니다.");
                        averageSatisfaction = Math.max(0, averageSatisfaction - 3);
                    } else if (newMultiplier < 1.0) {
                        System.out.println("가격이 내려 손님들의 만족도가 증가합니다.");
                        averageSatisfaction = Math.min(100, averageSatisfaction + 5);
                    }

                    System.out.println("전체 메뉴 가격이 조정되었습니다.");
                    System.out.println("가격 변동으로 인해 손님 수와 만족도가 변동됩니다.");
                }
                break;

            case 2: //개별 가격 조정
                System.out.println("가격을 변경할 메뉴를 선택하세요:");
                for (int i = 0; i < menu.length; i++) {
                    System.out.printf("%d. %s (현재 가격: %d원)\n", i + 1, menu[i], price[i]);
                }

                int menuIndex = sc.nextInt() - 1;
                if (menuIndex >= 0 && menuIndex < menu.length) {
                    System.out.printf("현재 %s의 가격은 %d원입니다. 새로운 가격을 입력하세요: ",
                            menu[menuIndex], price[menuIndex]);
                    int oldPrice = price[menuIndex];
                    int newPrice = sc.nextInt();

                    if (newPrice > 0) {
                        price[menuIndex] = newPrice;
                        System.out.printf("%s의 가격이 %d원으로 변경되었습니다.\n",
                                menu[menuIndex], newPrice);

                        if (newPrice > oldPrice) {
                            System.out.println("가격이 올라 손님들의 만족도가 약간 감소합니다.");
                            averageSatisfaction = Math.max(0, averageSatisfaction - 3);
                        } else if (newPrice < oldPrice) {
                            System.out.println("가격이 내려 손님들의 만족도가 증가합니다.");
                            averageSatisfaction = Math.min(100, averageSatisfaction + 5);
                        } else {
                            System.out.println("가격이 동일하므로 손님들의 만족도에 변화가 없습니다.");
                        }
                    } else {
                        System.out.println("가격은 0원보다 커야 합니다. 가격 변경이 취소되었습니다.");
                    }
                } else {
                    System.out.println("잘못된 메뉴 선택입니다.");
                }
                break;

            case 0:
                System.out.println("가격 변경을 취소합니다.");
                break;

            default:
                System.out.println("잘못된 선택입니다.");
                break;
        }
    }

    //메뉴 5. 조리 기기 업그레이드
    public static void upgradeAppliance() {
        System.out.println("\n=== 조리 기기 업그레이드 ===");
        System.out.println("1. 커피 머신 업그레이드");
        System.out.println("2. 블렌더 업그레이드");
        System.out.println("3. 오븐 업그레이드");
        System.out.println("4. 에스프레소 머신 업그레이드");
        System.out.println("0. 취소");

        int choice = sc.nextInt();
        sc.nextLine(); // 버퍼 처리

        switch (choice) {
            case 1 -> upgradeCoffeeMachine();
            case 2 -> upgradeBlender();
            case 3 -> upgradeOven();
            case 0 -> System.out.println("조리 기기 업그레이드를 취소합니다.");
            default -> System.out.println("잘못된 선택입니다.");
        }
    }

    //5-1 커피 머신 업그레이드
    public static void upgradeCoffeeMachine() {
        if (cafeMoney >= 5000) {
            cafeMoney -= 5000;
            coffeeMachineLevel++;

            // 제조 시간 단축 효과
            double speedMultiplier = 1 - (0.1 * coffeeMachineLevel);
            for (int i = 0; i < manufactureTime.length; i++) {
                manufactureTime[i] = Math.max(1, (int)(manufactureTime[i] * speedMultiplier));
            }

            // 재고 증가 효과 추가
            int bonusStock = coffeeMachineLevel * 3; // 레벨당 3개씩 추가
            for (int i = 0; i < stock.length; i++) {
                stock[i] += bonusStock;
            }

            System.out.printf("커피 머신이 업그레이드되었습니다! 현재 커피 머신 레벨: %d\n", coffeeMachineLevel);
            System.out.printf("제조 시간이 단축되었으며, 각 메뉴당 %d개의 추가 재고가 생성되었습니다.\n", bonusStock);
        } else {
            System.out.println("소지금이 부족합니다. 업그레이드를 할 수 없습니다.");
        }
    }

    //5-2 블렌더 업그레이드
    public static void upgradeBlender() {
        if (cafeMoney >= 3000) {
            cafeMoney -= 3000;
            blenderLevel++;

            // 제조 시간 단축 및 재고 증가
            double speedMultiplier = 1 - (0.1 * blenderLevel);
            int bonusStock = blenderLevel * 2; // 레벨당 2개씩 추가

            for (int i = 0; i < stock.length; i++) {
                manufactureTime[i] = Math.max(1, (int)(manufactureTime[i] * speedMultiplier));
                stock[i] += bonusStock;
            }

            System.out.printf("블렌더가 업그레이드되었습니다! 현재 블렌더 레벨: %d\n", blenderLevel);
            System.out.printf("제조 시간이 단축되었으며, 각 메뉴당 %d개의 추가 재고가 생성되었습니다.\n", bonusStock);
        } else {
            System.out.println("소지금이 부족합니다. 업그레이드를 할 수 없습니다.");
        }
    }

    //5-3 오븐 업그레이드
    public static void upgradeOven() {
        if (cafeMoney >= 7000) { // 업그레이드 비용
            cafeMoney -= 7000;
            ovenLevel++;
            System.out.printf("오븐이 업그레이드되었습니다! 현재 오븐 레벨: %d\n", ovenLevel);

            // 디저트 재고 생산 증가 효과
            int bonusDesserts = ovenLevel * 5; // 레벨당 +5 재고
            System.out.printf("디저트 제조 효율이 증가합니다. 추가 재고 보너스: +%d\n", bonusDesserts);
        } else {
            System.out.println("소지금이 부족합니다. 업그레이드를 할 수 없습니다.");
        }
    }



    //메뉴 6. 알바생 관리
    public static void manageWorker() {
        System.out.println("\n=== 알바생 관리 ===");
        System.out.println("1. 알바생 추가");
        System.out.println("2. 알바생 해고");
        System.out.println("3. 알바생 현황 보기");
        System.out.println("0. 취소");

        int choice = sc.nextInt();
        sc.nextLine(); // 버퍼 처리

        switch (choice) {
            case 1 -> hireWorker();
            case 2 -> fireWorker();
            case 3 -> viewWorkers();
            case 0 -> System.out.println("알바생 관리 작업을 취소합니다.");
            default -> System.out.println("잘못된 선택입니다. 다시 입력하세요.");
        }
    }

    //6-1 알바생 고용
    public static void hireWorker() {
        if (currentWorkerCount >= max_workers) {
            System.out.println("최대 고용 가능한 알바생은 2명입니다. 더 이상 추가할 수 없습니다.");
            return;
        }

        System.out.println("\n=== 알바생 등급 선택 ===");
        System.out.println("1. C등급 (비용: 2000원, 기본 효율)");
        System.out.println("2. B등급 (비용: 4000원, 높은 효율)");
        System.out.println("3. A등급 (비용: 6000원, 최고 효율)");
        System.out.println("0. 취소");

        int choice = sc.nextInt();
        sc.nextLine(); // 버퍼 처리
        int workerCost = 0;
        int workerLevel = 0;

        switch (choice) {
            case 1 -> {
                workerCost = 2000;
                workerLevel = 1; // C등급
            }
            case 2 -> {
                workerCost = 4000;
                workerLevel = 2; // B등급
            }
            case 3 -> {
                workerCost = 6000;
                workerLevel = 3; // A등급
            }
            case 0 -> {
                System.out.println("알바생 추가를 취소합니다.");
                return;
            }
            default -> {
                System.out.println("잘못된 선택입니다.");
                return;
            }
        }

        if (cafeMoney >= workerCost) {
            cafeMoney -= workerCost;

            // 배열의 빈 슬롯(0) 찾기
            for (int i = 0; i < workerLevels.length; i++) {
                if (workerLevels[i] == 0) {
                    workerLevels[i] = workerLevel; // 레벨 저장
                    currentWorkerCount++;
                    break;
                }
            }

            System.out.printf("%d등급 알바생을 추가했습니다! 현재 알바생 수: %d명\n", workerLevel, currentWorkerCount);
            System.out.printf("현재 남은 소지금: %d원\n", cafeMoney);
        } else {
            System.out.println("소지금이 부족합니다. 알바생을 추가할 수 없습니다.");
        }
    }

    //6-2 알바생 해고
    public static void fireWorker() {
        if (currentWorkerCount == 0) {
            System.out.println("현재 해고할 수 있는 알바생이 없습니다.");
            return;
        }

        System.out.println("\n=== 해고할 알바생 선택 ===");
        for (int i = 0; i < workerLevels.length; i++) {
            if (workerLevels[i] > 0) {
                System.out.printf("%d. %d등급 알바생\n", i + 1, workerLevels[i]);
            }
        }
        System.out.println("0. 취소");

        int choice = sc.nextInt() - 1;
        sc.nextLine(); // 버퍼 처리

        if (choice >= 0 && choice < workerLevels.length && workerLevels[choice] > 0) {
            System.out.printf("%d등급 알바생이 해고되었습니다.\n", workerLevels[choice]);
            workerLevels[choice] = 0; // 슬롯 비우기
            currentWorkerCount--;
        } else if (choice == -1) {
            System.out.println("해고를 취소합니다.");
        } else {
            System.out.println("잘못된 선택입니다.");
        }
    }

    //6-3 알바생 현황
    public static void viewWorkers() {
        System.out.println("\n=== 알바생 현황 ===");
        System.out.printf("현재 고용된 알바생 수: %d명\n", currentWorkerCount);
        if (currentWorkerCount > 0) {
            System.out.println("고용된 알바생 레벨:");
            for (int i = 0; i < workerLevels.length; i++) {
                if (workerLevels[i] > 0) {
                    System.out.printf("- %d등급 알바생\n", workerLevels[i]);
                }
            }
        } else {
            System.out.println("현재 고용된 알바생이 없습니다.");
        }
        System.out.printf("현재 남은 소지금: %d원\n", cafeMoney);
    }

    //메뉴 7. 인테리어 업그레이드
    public static void upgradeInterior() {
        System.out.println("\n=== 인테리어 업그레이드 ===");
        System.out.println("아래에서 추가할 인테리어를 선택하세요:");
        System.out.println("1. 화분 추가 (비용: 5000원, 고객 만족도 +5)");
        System.out.println("2. 그림 액자 추가 (비용: 8000원, 고객 만족도 +8)");
        System.out.println("3. 조각품 추가 (비용: 12000원, 고객 만족도 +12)");
        System.out.println("4. 거울 추가 (비용: 10000원, 고객 만족도 +10)");
        System.out.println("5. 마스코트 인형 추가 (비용: 15000원, 고객 만족도 +15)");
        System.out.println("0. 취소");

        int choice = sc.nextInt();
        sc.nextLine(); // 버퍼 처리

        switch (choice) {
            case 1 -> purchaseItem("화분", 5000, 5);
            case 2 -> purchaseItem("그림 액자", 8000, 8);
            case 3 -> purchaseItem("조각품", 12000, 12);
            case 4 -> purchaseItem("거울", 10000, 10);
            case 5 -> purchaseItem("마스코트 인형", 15000, 15);
            case 0 -> System.out.println("인테리어 업그레이드를 취소합니다.");
            default -> System.out.println("잘못된 선택입니다. 다시 입력하세요.");
        }
    }

    //7-1 인테리어 아이템 구매
    public static void purchaseItem(String itemName, int cost, int satisfactionBoost) {
        if (cafeMoney >= cost) {
            cafeMoney -= cost;

            // 아이템별 효과 저장
            switch (itemName) {
                case "화분" -> interiorItems[0]++;
                case "그림 액자" -> interiorItems[1]++;
                case "조각품" -> interiorItems[2]++;
                case "거울" -> interiorItems[3]++;
                case "마스코트 인형" -> interiorItems[4]++;
            }

            interiorLevel += satisfactionBoost;

            // 전체 인테리어 시너지 효과
            int totalItems = 0;
            for (int item : interiorItems) {
                totalItems += item;
            }

            if (totalItems >= 3) {
                interiorLevel += 5; // 3개 이상 보유시 추가 보너스
            }

            System.out.printf("%s을(를) 구매하였습니다! 비용: %d원, 고객 만족도 +%d\n",
                    itemName, cost, satisfactionBoost);
        } else {
            System.out.println("소지금이 부족합니다. 해당 인테리어를 구매할 수 없습니다.");
        }
    }

    //8. 디저트 관리
    public static void manageDesserts() {
        System.out.println("\n=== 디저트 관리 ===");
        System.out.println("현재 제공 중인 디저트 재고:");

        // 현재 디저트 상태 출력
        for (int i = 0; i < dessertTypes.length; i++) {
            System.out.printf("%d. %s (재고: %d개, 판매가: %d원, 제조원가: %d원, 만족도: +%d)\n",
                    i + 1, dessertTypes[i], dessertStocks[i], dessertPrices[i],
                    dessertCosts[i], dessertSatisfactions[i]);
        }
        System.out.println("0. 취소");

        System.out.print("재고를 추가할 디저트를 선택하세요: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 0) {
            System.out.println("디저트 관리를 취소합니다.");
            return;
        }

        int dessertIndex = choice - 1;
        if (dessertIndex >= 0 && dessertIndex < dessertTypes.length) {
            System.out.printf("%s의 추가할 재고 수를 입력하세요: ", dessertTypes[dessertIndex]);
            int additionalStock = sc.nextInt();
            sc.nextLine();

            // 제조 비용 계산
            int totalCost = dessertCosts[dessertIndex] * additionalStock;


            if (cafeMoney >= totalCost) {
                dessertStocks[dessertIndex] += additionalStock;
                cafeMoney -= totalCost;
                System.out.printf("%s의 재고가 %d개 추가되었습니다.\n",
                        dessertTypes[dessertIndex], additionalStock);
                System.out.printf("제조 비용: %d원 (남은 소지금: %d원)\n", totalCost, cafeMoney);
            } else {
                System.out.println("소지금이 부족합니다.");
                System.out.printf("필요 금액: %d원, 보유 금액: %d원\n", totalCost, cafeMoney);
            }
        } else {
            System.out.println("잘못된 선택입니다.");
        }
    }


}