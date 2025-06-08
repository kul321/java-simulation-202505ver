import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;




public class ShopManager {
    public Scanner sc;

    // 기존 변수들
    public int MAX_WEAPONS = 20;  // 최대 무기 개수
    public Weapon[] weapons;  //
    public int weaponCount;   // 현재 무기 개수
    public Customer[] customers;
    public Promotion[] promotions;
    public int promotionCount;
    public int shopMoney;
    public int dailySales;
    public int turn;
    public int customerNumber;
    public static int MAX_CUSTOMERS = 100;

    public ReorderRequest[] reorderRequests;
    public int reorderRequestCount;

    // 생성자 수정
    public ShopManager() {
        weapons = new Weapon[MAX_WEAPONS];
        customers = new Customer[MAX_CUSTOMERS];
        promotions = new Promotion[MAX_WEAPONS];
        weaponCount = 0;
        promotionCount = 0;
        this.shopMoney = 0;
        this.dailySales = 0;
        this.sc = new Scanner(System.in);
        this.turn = 0;
        this.customerNumber = 1;
        this.reorderRequests = new ReorderRequest[MAX_CUSTOMERS];
        this.reorderRequestCount = 0;
    }

    // 로그인 관련 메소드
    public String checkLogin(String id, String pw) {
        if (id.equals("seller") && pw.equals("seller123")) {
            return "seller";
        }
        for (int i = 1; i <= MAX_CUSTOMERS; i++) {
            String customerId = "ct" + i;
            String customerPw = "pw" + i;
            if (id.equals(customerId) && pw.equals(customerPw)) {
                customerNumber = i;
                return "customer";
            }
        }
        return "invalid";
    }

    // 판매자 메뉴
    public void sellerMenu() {
        while (true) {
            System.out.println("===판매자메뉴===");
            System.out.println("1. 모든 항목의 인벤토리를 봅니다.");
            System.out.println("2. 인벤토리에 새 항목을 추가합니다.");
            System.out.println("3. 인벤토리에서 항목을 제거합니다.");
            System.out.println("4. 항목의 재고를 업데이트합니다.");
            System.out.println("5. 프로모션 관리");
            System.out.println("6. 판매 보고서 확인");
            System.out.println("7. 로그아웃");
            System.out.println("8. 프로그램 종료");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewInventory();
                    break;
                case 2:
                    addWeaponToInventory();
                    break;
                case 3:
                    removeWeaponFromInventory();
                    break;
                case 4:
                    updateStock();
                    break;
                case 5:
                    promotionMenu();
                    break;
                case 6:
                    reportSales();
                    break;
                case 7:
                    System.out.println("판매자 로그아웃합니다.");
                    return;
                case 8:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                default:
                    System.out.println("올바른 번호를 선택하세요.");
            }
        }
    }

    // 고객 메뉴
    public void customerMenu() {
        while (true) {
            System.out.println(customerNumber + "번째 손님 방문중");
            System.out.println("===무기상점===");
            System.out.println("1. 현재 진행중인 프로모션 확인");
            System.out.println("2. 모든 항목의 인벤토리를 봅니다.");
            System.out.println("3. 무기 구매");
            System.out.println("4. 구매 내역 및 구매한 무기의 속성 확인");
            System.out.println("5. 나의 정보 통계 확인");
            System.out.println("6. 로그아웃");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewPromotion();
                    break;
                case 2:
                    viewInventory();
                    break;
                case 3:
                    buyWeapon();
                    break;
                case 4:
                    viewPurchaseHistory();
                    break;
                case 5:
                    viewCustomerStats();
                    break;
                case 6:
                    System.out.println("로그아웃합니다.");
                    handleCustomerLogout();
                    return;
                default:
                    System.out.println("올바른 번호를 선택하세요.");
            }
        }
    }

    // 로그인 처리 메소드
    public void handleLogin(String id, String pw) {
        String role = checkLogin(id, pw);
        if (role.equals("seller")) {
            System.out.println("판매자님 로그인하였습니다.");
            sellerMenu();
        } else if (role.equals("customer")) {
            System.out.println("손님" + id + "님 로그인하였습니다.");
            if (customerNumber <= MAX_CUSTOMERS) {
                createNewCustomer(id);
                customerMenu();
            }
        } else {
            System.out.println("로그인에 실패하였습니다. 다시 확인해주세요");
        }
    }

    // 새 고객 생성
    public void createNewCustomer(String id) {
        Customer newCustomer = new Customer(id);
        customers[customerNumber - 1] = newCustomer;
    }

    // 고객 로그아웃 처리
    public void handleCustomerLogout() {
        turn++;
        if (turn % 3 == 0) {
            System.out.println("==== 3명의 손님이 지나갔습니다. 하루가 끝났습니다 ====");
            dailySettlement();
        }
    }

    public void viewInventory() {
        Date today = new Date();

        // 현재 보여진 카테고리를 기록하기
        ArrayList<String> shownCategories = new ArrayList<>();

        // 모든 무기를 순회하면서 카테고리별로 출력
        for (Weapon currentWeapon : weapons) {
            // 이미 출력한 카테고리는 건너뛰기
            if (!shownCategories.contains(currentWeapon.category)) {
                System.out.println("\n카테고리: " + currentWeapon.category);

                // 같은 카테고리의 모든 무기 출력
                for (Weapon weapon : weapons) {
                    if (weapon.category.equals(currentWeapon.category)) {
                        String discountInfo = "";

                        // 해당 무기의 프로모션 찾기
                        Promotion currentPromo = getPromotion(weapon);

                        int finalPrice = weapon.price;
                        if (currentPromo != null) {
                            finalPrice = weapon.price * (100 - currentPromo.discountRate) / 100;
                            discountInfo = "할인 중 (" + currentPromo.discountRate + "%)";
                        }

                        System.out.println(weapon.name + " (" + finalPrice + "골드" +
                                (discountInfo.isEmpty() ? "" : " [" + discountInfo + "]") + ") - 재고: " +
                                (weapon.stock > 0 ? weapon.stock : "[판매완료]") +
                                " | 1+1: " + (currentPromo != null && currentPromo.isOnePlusOne ? "적용" : "미적용"));
                    }
                }
                shownCategories.add(currentWeapon.category);
            }
        }
        System.out.println("----------");
    }

    // 무기 추가
    public void addWeaponToInventory() {
        System.out.println("새로운 무기의 카테고리를 입력하세요:");
        String category = sc.next();

        System.out.println("무기 이름을 입력하세요:");
        String name = sc.next();

        System.out.println("무기의 데미지 수치를 입력하세요:");
        int damage = sc.nextInt();

        System.out.println("무기의 속도 수치를 입력하세요:");
        int speed = sc.nextInt();

        System.out.println("무기의 무게 수치를 입력하세요:");
        int weight = sc.nextInt();

        System.out.println("무기의 범위 수치를 입력하세요:");
        int range = sc.nextInt();

        System.out.println("무기의 내구성 수치를 입력하세요:");
        int durability = sc.nextInt();

        Weapon newWeapon = new Weapon(name, category, damage, speed, weight, range, durability);
        newWeapon.stock = 2; // 기본 재고 설정
        weapons[weaponCount] = newWeapon;
        weaponCount++;

        System.out.println("새로운 무기 " + name + "이(가) 추가되었습니다.");
    }

    // 무기 제거
    public void removeWeaponFromInventory() {
        if (weaponCount == 0) {
            System.out.println("제거할 무기가 없습니다.");
            return;
        }

        for (int i = 0; i < weaponCount; i++) {
            System.out.println(i + ". " + weapons[i].category);
        }

        int choice = sc.nextInt();
        if (choice >= 0 && choice < weaponCount) {
            String weaponName = weapons[choice].name;
            for(int i = choice; i < weaponCount - 1; i++) {
                weapons[i] = weapons[i + 1];
            }
            weaponCount--;
            System.out.println(weaponName + " 무기가 제거되었습니다.");
        } else {
            System.out.println("잘못된 선택입니다.");
        }
    }

    // 재고 업데이트
    public void updateStock() {
        if (weaponCount == 0) {
            System.out.println("업데이트할 무기가 없습니다.");
            return;
        }

        System.out.println("재고를 업데이트할 무기를 선택하세요:");
        for (int i = 0; i < weaponCount; i++) {
            System.out.println(i + ". " + weapons[i].name +
                    " - 현재 재고: " + weapons[i].stock);
        }

        int choice = sc.nextInt();
        if (choice >= 0 && choice < weaponCount) {
            System.out.println("새로운 재고 수량을 입력하세요:");
            int newStock = sc.nextInt();
            weapons[choice].stock = newStock;
            System.out.println("재고가 업데이트되었습니다.");
        } else {
            System.out.println("잘못된 선택입니다.");
        }
    }

    // 프로모션 메뉴
    public void promotionMenu() {
        while (true) {
            System.out.println("===프로모션 관리===");
            System.out.println("1. 현재 프로모션 보기");
            System.out.println("2. 프로모션 설정");
            System.out.println("3. 돌아가기");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewPromotion();
                    break;
                case 2:
                    setPromotion();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("올바른 번호를 선택하세요.");
            }
        }
    }

    // 프로모션 보기
    public void viewPromotion() {
        System.out.println("===현재 진행 중인 프로모션===");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for(int i = 0; i < weaponCount; i++) {
            Weapon weapon = weapons[i];
            System.out.println("\n카테고리: " + weapon.category);

            // 해당 무기의 프로모션만 찾아서 표시
            Promotion weaponPromo = getPromotion(weapon);
            if (weaponPromo != null) {
                String promoStartDate = dateFormat.format(weaponPromo.startDate);
                String promoEndDate = dateFormat.format(weaponPromo.endDate);

                System.out.println(weapon.name + " - 할인율: " + weaponPromo.discountRate +
                        "%, 1+1: " + (weaponPromo.isOnePlusOne ? "적용됨" : "미적용"));
                System.out.println("할인 기간: " + promoStartDate + " ~ " + promoEndDate);
            }
        }
        System.out.println("----------");
    }

    // 프로모션 설정
    public void setPromotion() {
        if (weaponCount == 0) {
            System.out.println("프로모션을 설정할 무기가 없습니다.");
            return;
        }

        // 무기 선택
        System.out.println("프로모션을 설정할 무기를 선택하세요:");
        for (int i = 0; i < weaponCount; i++) {
            System.out.println(i + ". " + weapons[i].name);
        }
        int weaponChoice = sc.nextInt();

        if (weaponChoice < 0 || weaponChoice >= weaponCount) {
            System.out.println("잘못된 선택입니다.");
            return;
        }

        // 프로모션 정보 입력
        System.out.println("할인율을 입력하세요 (0% ~ 100%):");
        int discountRate = sc.nextInt();

        System.out.println("1+1 프로모션을 설정하시겠습니까? (1 = 예, 0 = 아니오):");
        boolean isOnePlusOne = sc.nextInt() == 1;

        // 할인 기간 설정
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            System.out.println("할인 시작일을 입력하세요 (yyyy-MM-dd):");
            String startDateInput = sc.next();
            Date startDate = dateFormat.parse(startDateInput);

            System.out.println("할인 종료일을 입력하세요 (yyyy-MM-dd):");
            String endDateInput = sc.next();
            Date endDate = dateFormat.parse(endDateInput);

            // 새 프로모션 생성 및 추가
            Weapon selectedWeapon = weapons[weaponChoice];
            Promotion newPromo = new Promotion(selectedWeapon, startDate, endDate, discountRate, isOnePlusOne);
            promotions[promotionCount++] = newPromo;

            System.out.println("프로모션이 설정되었습니다.");
            System.out.println("할인율: " + discountRate + "%");
            System.out.println("1+1: " + (isOnePlusOne ? "적용됨" : "적용되지 않음"));
            System.out.println("할인 기간: " + startDateInput + " ~ " + endDateInput);

        } catch (ParseException e) {
            System.out.println("날짜 형식이 잘못되었습니다. yyyy-MM-dd 형식으로 입력해주세요.");
        }
    }

    // 무기 구매
    public void buyWeapon() {
        if (weaponCount == 0) {
            System.out.println("구매 가능한 무기가 없습니다.");
            return;
        }

        // 무기 목록 표시
        System.out.println("어떤 무기를 구매하시겠습니까?");
        for (int i = 0; i < weaponCount; i++) {
            Weapon weapon = weapons[i];
            System.out.println(i + ". " + weapon.name +
                    " (" + weapon.price + "골드) - 재고: " +
                    (weapon.stock > 0 ? weapon.stock : "[판매완료]"));
        }

        int choice = sc.nextInt();
        if (choice < 0 || choice >= weaponCount) {
            System.out.println("잘못된 선택입니다.");
            return;
        }

        Weapon selectedWeapon = weapons[choice];

        // 재고 확인 부분
        if (selectedWeapon.stock <= 0) {
            System.out.println("해당 무기는 품절되었습니다.");
            System.out.println("재발주를 신청하시겠습니까? (1: 예, 2: 아니오)");
            int reorderChoice = sc.nextInt();

            if (reorderChoice == 1) {
                requestReorder(selectedWeapon);
            }
            return;
        }

        // 프로모션 적용
        int finalPrice = selectedWeapon.price;
        Promotion activePromo = getPromotion(selectedWeapon);
        if (activePromo != null) {
            finalPrice = finalPrice * (100 - activePromo.discountRate) / 100;
            System.out.println("할인 적용 가격: " + finalPrice + " (할인율: " + activePromo.discountRate + "%)");
        }



        // 구매 처리
        Customer customer = customers[customerNumber - 1];
        selectedWeapon.stock--;
        customer.inventory.add(selectedWeapon);

        if (selectedWeapon.price == 1000) { // 최저가 무기 구매시
            System.out.println("최저가 무기 구매 이벤트! 동일 무기 1개를 추가로 드립니다!");
            customer.inventory.add(selectedWeapon); // 한개 더 추가
        }

        // 능력치 증가
        customer.strength += selectedWeapon.damage;
        customer.speed += selectedWeapon.speed;
        customer.stamina += 10 - (selectedWeapon.weight / 10);
        customer.reach += selectedWeapon.range;

        // 판매 금액 처리
        dailySales += finalPrice;
        System.out.println(selectedWeapon.name + "를 구매했습니다.");
        System.out.println("능력치가 업데이트되었습니다.");
    }

    // 특정 무기의 프로모션 찾기
    private Promotion getPromotion(Weapon weapon) {
        Date today = new Date();
        for (int i = 0; i < promotionCount; i++) {
            if (promotions[i].weapon == weapon) {
                return promotions[i];
            }
        }
        return null;
    }

    // 구매 내역 보기
    public void viewPurchaseHistory() {
        if (customerNumber <= 0 || customerNumber > MAX_CUSTOMERS) {
            System.out.println("유효하지 않은 고객 번호입니다.");
            return;
        }

        Customer customer = customers[customerNumber - 1];
        System.out.println("=== " + customer.id + "님의 구매 내역 ===");

        if (customer.inventory.isEmpty()) {
            System.out.println("구매 내역이 없습니다.");
            return;
        }

        for (Weapon weapon : customer.inventory) {
            System.out.println("무기: " + weapon.name);
            System.out.println("카테고리: " + weapon.category);
            System.out.println("데미지: " + weapon.damage);
            System.out.println("속도: " + weapon.speed);
            System.out.println("무게: " + weapon.weight);
            System.out.println("범위: " + weapon.range);
            System.out.println("내구성: " + weapon.durability);
            System.out.println("---------------");
        }
    }

    // 고객 통계 보기
    public void viewCustomerStats() {
        if (customerNumber <= 0 || customerNumber > MAX_CUSTOMERS) {
            System.out.println("유효하지 않은 고객 번호입니다.");
            return;
        }

        Customer customer = customers[customerNumber - 1];
        System.out.println("=== " + customer.id + "님의 정보 통계 ===");
        System.out.println("힘: " + customer.strength);
        System.out.println("민첩성: " + customer.speed);
        System.out.println("지구력: " + customer.stamina);
        System.out.println("공격범위: " + customer.reach);
    }

    // 판매 보고서
    public void reportSales() {
        System.out.println("=== 판매 보고서 ===");
        System.out.println("누적 총 판매액: " + shopMoney + " 골드");
        System.out.println("금일 판매액: " + dailySales + " 골드");

        // 가장 많이 팔린 무기 찾기
        Weapon bestSeller = null;
        int maxSales = 0;
        for(int i = 0; i < weaponCount; i++) {
            Weapon weapon = weapons[i];
            // 판매량 = 초기재고 - 현재재고
            int sales = 2 - weapon.stock;  // 초기재고를 2로 가정
            if (sales > maxSales) {
                maxSales = sales;
                bestSeller = weapon;
            }
        }

        if (bestSeller != null) {
            System.out.println("최다 판매 무기: " + bestSeller.name + " (" + maxSales + "개)");
        }

        System.out.println("=================");
    }

    public class ReorderRequest {
        Weapon weapon;
        String customerId;
        int requestTurn;

        public ReorderRequest(Weapon weapon, String customerId, int requestTurn) {
            this.weapon = weapon;
            this.customerId = customerId;
            this.requestTurn = requestTurn;
        }
    }


    public static int REORDER_THRESHOLD = 3; // 3명이 예약하면 재발주



    // 재발주 요청
    private void requestReorder(Weapon weapon) {
        // 이미 신청했는지 확인
        for (ReorderRequest request : reorderRequests) {
            if (request.weapon == weapon && request.customerId.equals("ct" + customerNumber)) {
                System.out.println("이미 재발주를 신청하셨습니다.");
                return;
            }
        }

        // 새 재발주 요청 추가
        if (reorderRequestCount >= reorderRequests.length) {
            System.out.println("재발주 요청을 더 이상 추가할 수 없습니다.");
            return;
        }

        reorderRequests[reorderRequestCount++] = new ReorderRequest(weapon, "ct" + customerNumber, turn);

        // 같은 무기에 대한 재발주 요청 수 확인
        int requestCount = 0;
        for (ReorderRequest request : reorderRequests) {
            if (request.weapon == weapon) {
                requestCount++;
            }
        }

        System.out.println("재발주 신청이 완료되었습니다. (현재 " + requestCount + "명 신청)");

        // 임계값 도달 시 재발주 처리
        if (requestCount >= REORDER_THRESHOLD) {
            processReorder(weapon);
        }
    }

    // 재발주 처리
    private void processReorder(Weapon weapon) {
        System.out.println(weapon.name + "의 재발주가 시작됩니다.");
        weapon.stock = 5; // 재고 보충

        // 신청한 고객들에게 알림
        for (ReorderRequest request : reorderRequests) {
            if (request.weapon == weapon) {
                System.out.println("고객 " + request.customerId + "님, 신청하신 " +
                        weapon.name + "의 재고가 보충되었습니다.");
            }
        }

        // 처리된 재발주 요청 제거
        int newCount = 0;
        for (int i = 0; i < reorderRequestCount; i++) {
            if (reorderRequests[i].weapon != weapon) {
                reorderRequests[newCount++] = reorderRequests[i];
            }
        }
        reorderRequestCount = newCount;
    }

    // 일일 정산 메소드 수정
    public void dailySettlement() {
        System.out.println("=== 하루 결산 ===");
        System.out.println("총 판매 금액: " + dailySales);
        shopMoney += dailySales;
        dailySales = 0;
        System.out.println("상점 총 소지 금액: " + shopMoney);

        // 3턴마다 재고를 초기화하는 작업
        if (turn % 3 == 0) {
            for (Weapon weapon : weapons) {
                weapon.stock = 2;
            }
            System.out.println("재고가 모두 복구되었습니다.");
        }

        int newCount = 0;
        for (int i = 0; i < reorderRequestCount; i++) {
            if (turn - reorderRequests[i].requestTurn < 3) {
                reorderRequests[newCount++] = reorderRequests[i];
            }
        }
        reorderRequestCount = newCount;
    }

    private void checkExpiredRequests() {
        ReorderRequest[] newRequests = new ReorderRequest[reorderRequests.length];
        int newCount = 0;

        for (int i = 0; i < reorderRequestCount; i++) {
            if (turn - reorderRequests[i].requestTurn < 3) {
                newRequests[newCount] = reorderRequests[i];
                newCount++;
            }
        }
        reorderRequests = newRequests;
        reorderRequestCount = newCount;
    }

}



