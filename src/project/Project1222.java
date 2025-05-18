import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Project1222 {
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();
    static int shopMoney = 0;
    static int salesMoney = 0;
    static int customerNumber = 1;
    static int turn = 0;
    static int maxCustomers = 100;
    static int numCategories = 2;
    static int numWeapons = 2;
    static int turnCount = 0;
    static int selectedWeaponPrice = 0;
    static int originalWeaponPrice = 0;


    static ArrayList<ArrayList<String>> weaponCategories = new ArrayList<>(numCategories);
    static ArrayList<ArrayList<String>> weaponNames = new ArrayList<>(numCategories);
    static ArrayList<ArrayList<Integer>> weaponDamage = new ArrayList<>(numCategories);
    static ArrayList<ArrayList<Integer>> weaponSpeed = new ArrayList<>(numCategories);
    static ArrayList<ArrayList<Integer>> weaponWeight = new ArrayList<>(numCategories);
    static ArrayList<ArrayList<Integer>> weaponRange = new ArrayList<>(numCategories);
    static ArrayList<ArrayList<Integer>> weaponDurability = new ArrayList<>(numCategories);
    static ArrayList<ArrayList<Integer>> weaponPrice = new ArrayList<>(numCategories);
    static ArrayList<ArrayList<Integer>> weaponAmount = new ArrayList<>(numCategories);
    static ArrayList<ArrayList<int[]>> weaponPromotions = new ArrayList<>(numCategories); // 무기별 프로모션
    static ArrayList<ArrayList<ArrayList<Integer>>> itemSalesHistory = new ArrayList<>();


    static int customerStr[] = new int[maxCustomers];
    static int customerSpd[] = new int[maxCustomers];
    static int customerStamina[] = new int[maxCustomers];
    static int customerReach[] = new int[maxCustomers];
    static ArrayList<ArrayList<ArrayList<String>>> customerWeapon = new ArrayList<>(maxCustomers);

    static int totalItemsSold = 0; // 누적 판매 수량
    static ArrayList<ArrayList<Integer>> itemSales = new ArrayList<>(numCategories);// 각 무기별 판매 수량 기록
    static ArrayList<ArrayList<Date[]>> weaponPromotionDates = new ArrayList<>((numCategories));

    public static void main(String[] args) {
        startNewTurn();
        System.out.println("상점에서 팔 무기의 종류를 작성해주세요");
        shopWeaponSet();
        initializeItemSales();


        for (int i = 0; i < maxCustomers; i++) {
            customerWeapon.add(new ArrayList<>());
            for (int j = 0; j < numCategories; j++) {
                customerWeapon.get(i).add(new ArrayList<>());
            }
        }

        while (true) {
            turn++;
            if (turn % 3 == 0) {
                System.out.println("==== 하루가 지났습니다 ====");
                dailySettlement();
            }
            break;
        }

        while (true) {
            LocalDate today = LocalDate.now();
            System.out.println("현재 날짜: "+today);
            System.out.println("사용자 로그인 ID: ");
            String inputId = sc.next();
            System.out.println("사용자 패스워드 : ");
            String inputPw = sc.next();

            try {
                String role = checkLogin(inputId, inputPw);
                if (role.equals("seller")) {
                    System.out.println("판매자님 로그인하였습니다.");
                    sellerMenu();

                }
                if (role.equals("customer")) {
                    System.out.println("손님" + inputId + "님 로그인하였습니다.");
                    if (customerNumber < maxCustomers) {
                        customerStr[customerNumber - 1] = rand.nextInt(20) + 10;
                        customerSpd[customerNumber - 1] = rand.nextInt(20) + 10;
                        customerStamina[customerNumber - 1] = rand.nextInt(20) + 10;
                        customerReach[customerNumber - 1] = rand.nextInt(5)+3;

                    }
                    customerMenu();

                }
                if (role.equals("invalid")) {
                    System.out.println("로그인에 실패하였습니다. 다시 확인해주세요");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("오류 발생: " + e.getMessage());
            }
        }
    }

    public static void shopWeaponSet() {
        for (int i = 0; i < numCategories; i++) {
            System.out.println((i + 1) + "번째 무기 카테고리: ");
            String categoryName = sc.next();
            weaponCategories.add(new ArrayList<>());
            weaponCategories.get(i).add(categoryName);

            weaponNames.add(new ArrayList<>());
            weaponDamage.add(new ArrayList<>());
            weaponSpeed.add(new ArrayList<>());
            weaponWeight.add(new ArrayList<>());
            weaponRange.add(new ArrayList<>());
            weaponDurability.add(new ArrayList<>());
            weaponPrice.add(new ArrayList<>());
            weaponAmount.add(new ArrayList<>());
            weaponPromotions.add(new ArrayList<>()); // 무기별 프로모션 초기화

            for (int j = 0; j < numWeapons; j++) {
                System.out.println(weaponCategories.get(i) + "카테고리의 " + (j + 1) + "번째 무기: ");
                weaponNames.get(i).add(sc.next());
                System.out.println(weaponNames.get(i).get(j) + "의 데미지 수치를 입력하세요.");
                weaponDamage.get(i).add(sc.nextInt());
                System.out.println(weaponNames.get(i).get(j) + "의 속도 수치를 입력하세요.");
                weaponSpeed.get(i).add(sc.nextInt());
                System.out.println(weaponNames.get(i).get(j) + "의 무게 수치를 입력하세요.");
                weaponWeight.get(i).add(sc.nextInt());
                System.out.println(weaponNames.get(i).get(j) + "의 범위 수치를 입력하세요.");
                weaponRange.get(i).add(sc.nextInt());
                System.out.println(weaponNames.get(i).get(j) + "의 내구성 수치를 입력하세요.");
                weaponDurability.get(i).add(sc.nextInt());

                int minPrice = 1000;
                int maxPrice = 10000;

                int baseprice = rand.nextInt(weaponDamage.get(i).get(j) * 300 +
                        weaponSpeed.get(i).get(j) * 200 - weaponWeight.get(i).get(j) * 100+
                        weaponRange.get(i).get(j)*100 + weaponDurability.get(i).get(j)*100)+minPrice;
                int price = Math.min(maxPrice,baseprice);


                weaponPrice.get(i).add(price);
                weaponAmount.get(i).add(2);
                weaponPromotions.get(i).add(new int[]{0, 0}); // 프로모션 초기값
            }
        }
    }


    public static String checkLogin(String id, String pw) {
        if (id.equals("seller") && pw.equals("seller123")) {
            return "seller";
        }
        for (int i = 1; i <= maxCustomers; i++) {
            String customerId = "ct" + i;
            String customerPw = "pw" + i;
            if (id.equals(customerId) && pw.equals(customerPw)) {
                customerNumber = i;
                return "customer";
            }
        }
        return "invalid";
    }

    //판매자 메뉴
    public static void sellerMenu() {
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
                    reportSales(); // 판매 보고서 확인
                    break;
                case 7:
                    System.out.println("판매자 로그아웃합니다.");
                    return;
                case 8:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0); // 프로그램 종료
                    break;
                default:
                    System.out.println("올바른 번호를 선택하세요.");
            }
        }
    }

    //고객 메뉴
    public static void customerMenu() {
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
                    handleCustomerLogout(); // 로그아웃 로직 호출
                    return; // 로그아웃 후 메뉴 종료
                default:
                    System.out.println("올바른 번호를 선택하세요.");
            }
        }
    }

    public static void viewInventory() {
        Date today = new Date();
        while (true) {
            for (int i = 0; i < weaponCategories.size(); i++) {
                System.out.println("\n카테고리: " + weaponCategories.get(i));
                for (int j = 0; j < weaponNames.get(i).size(); j++) {
                    String weaponName = weaponNames.get(i).get(j);
                    String discountInfo  ="";

                    int price = weaponPrice.get(i).get(j);
                    int finalPrice = price;
                    int amount = weaponAmount.get(i).get(j);
                    int[] promo = weaponPromotions.get(i).get(j);//// 개별 무기 프로모션
                    int discountRate = promo[0];

                    Date[] promoDate = weaponPromotionDates.get(i).get(j);
                    Date promoStartDate = promoDate[0];
                    Date promoEndDate = promoDate[1];

                    if(promoStartDate != null && promoEndDate != null &&
                            ((today.after(promoStartDate)||(today.equals(promoStartDate)) && ((today.before(promoEndDate)||(today.equals(promoEndDate)))))))
                    {
                        finalPrice = price * (100 - discountRate) /100;
                        discountInfo = "할인 중 ("+discountRate+"%)";
                    }
                    System.out.print(weaponName + " (" + finalPrice + "골드" + (discountInfo.isEmpty() ? "" : " [" +discountInfo+"]")+") - 재고: " +
                            (amount > 0 ? amount : "[판매완료]") +
                            " | 1+1: " + (promo[1] == 1 ? "적용" : "미적용") + " || ");
                }
                System.out.println("\n----------");
            }

            System.out.println("\n1. 돌아가기");
            int choice = sc.nextInt();
            if (choice == 1) {
                break;
            } else {
                System.out.println("올바른 번호를 입력하세요.");
            }
        }
    }

    public static void addWeaponToInventory() {
        System.out.println("새로운 무기의 카테고리를 입력하세요:");
        String category = sc.next();

        int categoryIndex = -1;
        for (int i = 0; i < weaponCategories.size(); i++) {
            if (weaponCategories.get(i).contains(category)) {
                categoryIndex = i;
                break;
            }
        }

        if (categoryIndex == -1) {
            weaponCategories.add(new ArrayList<>());
            weaponCategories.get(weaponCategories.size() - 1).add(category);
            categoryIndex = weaponCategories.size() - 1;

            weaponNames.add(new ArrayList<>());
            weaponDamage.add(new ArrayList<>());
            weaponSpeed.add(new ArrayList<>());
            weaponWeight.add(new ArrayList<>());
            weaponDurability.add(new ArrayList<>());
            weaponRange.add(new ArrayList<>());
            weaponPrice.add(new ArrayList<>());
            weaponAmount.add(new ArrayList<>());
            weaponPromotions.add(new ArrayList<>());// 무기별 프로모션 초기화
            weaponPromotionDates.add(new ArrayList<>());
        }

        System.out.println("무기 이름을 입력하세요:");
        String weaponName = sc.next();
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

        int minPrice = 1000;
        int maxPrice = 10000;
        int baseprice = rand.nextInt(damage * 300 + speed * 200 - weight * 100
                + range * 100 + durability * 100) +minPrice;

        int price = Math.min(maxPrice,baseprice);

        weaponNames.get(categoryIndex).add(weaponName);
        weaponDamage.get(categoryIndex).add(damage);
        weaponSpeed.get(categoryIndex).add(speed);
        weaponWeight.get(categoryIndex).add(weight);
        weaponRange.get(categoryIndex).add(range);
        weaponDurability.get(categoryIndex).add(durability);
        weaponPrice.get(categoryIndex).add(price);
        weaponAmount.get(categoryIndex).add(2);
        weaponPromotions.get(categoryIndex).add(new int[]{0, 0});// 프로모션 초기값
        weaponPromotionDates.get(categoryIndex).add(null);


        System.out.println("새로운 무기 " + weaponName + "이(가) 추가되었습니다.");
    }

    public static void removeWeaponFromInventory() {
        System.out.println("제거할 무기의 카테고리를 선택하세요:");
        for (int i = 0; i < weaponCategories.size(); i++) {
            System.out.println(i + ". " + weaponCategories.get(i));
        }
        int categoryChoice = sc.nextInt();

        System.out.println("제거할 무기의 번호를 선택하세요:");
        for (int j = 0; j < weaponNames.get(categoryChoice).size(); j++) {
            System.out.println(j + ". " + weaponNames.get(categoryChoice).get(j));
        }
        int weaponChoice = sc.nextInt();

        weaponNames.get(categoryChoice).remove(weaponChoice);
        weaponDamage.get(categoryChoice).remove(weaponChoice);
        weaponSpeed.get(categoryChoice).remove(weaponChoice);
        weaponWeight.get(categoryChoice).remove(weaponChoice);
        weaponRange.get(categoryChoice).remove(weaponChoice);
        weaponDurability.get(categoryChoice).remove(weaponChoice);
        weaponPrice.get(categoryChoice).remove(weaponChoice);
        weaponAmount.get(categoryChoice).remove(weaponChoice);
        weaponPromotions.get(categoryChoice).remove(weaponChoice); // 프로모션 정보도 제거

        System.out.println("무기가 제거되었습니다.");
    }

    public static void updateStock() {
        System.out.println("재고를 업데이트할 무기의 카테고리를 선택하세요:");
        for (int i = 0; i < weaponCategories.size(); i++) {
            System.out.println(i + ". " + weaponCategories.get(i));
        }
        int categoryChoice = sc.nextInt();

        System.out.println("재고를 업데이트할 무기를 선택하세요:");
        for (int j = 0; j < weaponNames.get(categoryChoice).size(); j++) {
            System.out.println(j + ". " + weaponNames.get(categoryChoice).get(j) + " - 현재 재고: " + weaponAmount.get(categoryChoice).get(j));
        }
        int weaponChoice = sc.nextInt();

        System.out.println("새로운 재고 수량을 입력하세요:");
        int newStock = sc.nextInt();
        weaponAmount.get(categoryChoice).set(weaponChoice, newStock);

        System.out.println("재고가 업데이트되었습니다.");
    }

    public static void setPromotion() {
        System.out.println("프로모션을 설정할 무기의 카테고리를 선택하세요:");
        for (int i = 0; i < weaponCategories.size(); i++) {
            System.out.println(i + ". " + weaponCategories.get(i));
        }
        int categoryChoice = sc.nextInt();

        System.out.println("프로모션을 설정할 무기를 선택하세요:");
        for (int j = 0; j < weaponNames.get(categoryChoice).size(); j++) {
            System.out.println(j + ". " + weaponNames.get(categoryChoice).get(j));
        }
        int weaponChoice = sc.nextInt();

        System.out.println("할인율을 입력하세요 (0% ~ 100%):");
        int discountRate = sc.nextInt();

        System.out.println("1+1 프로모션을 설정하시겠습니까? (1 = 예, 0 = 아니오):");
        int onePlusOne = sc.nextInt();


        //할인 기간 설정
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        System.out.println("할인 시작일을 입력하세요:");
        String startDateInput = sc.next();
        System.out.println("할인 종료일을 입력하세요:");
        String endDateInput = sc.next();

        try {
            Date startDate = dateFormat.parse(startDateInput);
            Date endDate = dateFormat.parse(endDateInput);
            Date[] promoDates = {startDate,endDate};

            int[] promo = weaponPromotions.get(categoryChoice).get(weaponChoice);
            promo[0] = discountRate;
            promo[1] = (onePlusOne == 1) ? 1 : 0;

            //할인 기간 저장
            weaponPromotionDates.get(categoryChoice).set(weaponChoice, promoDates);

            System.out.println("프로모션이 설정되었습니다. 할인율: " + discountRate + "%, 1+1: " + (onePlusOne == 1 ? "적용됨" : "적용되지 않음")+", 할인 기간: "+startDateInput+" ~ "+endDateInput);


        } catch ( ParseException e){
            System.out.println("날짜 형식이 잘못되었습니다.");
        }





    }

    public static void promotionMenu() {
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
                    return; // 프로모션 메뉴에서 돌아가기
                default:
                    System.out.println("올바른 번호를 선택하세요.");
            }
        }
    }

    public static void viewPromotion() {
        System.out.println("===현재 진행 중인 프로모션===");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        for (int i = 0; i < weaponCategories.size(); i++) {
            System.out.println("\n카테고리: " + weaponCategories.get(i));
            for (int j = 0; j < weaponNames.get(i).size(); j++) {
                String weaponName = weaponNames.get(i).get(j);
                int[] promo = weaponPromotions.get(i).get(j);
                int discountRate = promo[0];
                int onePlusOne = promo[1];

                Date[]promoDates = weaponPromotionDates.get(i).get(j);
                String promoStartDate = (promoDates[0] != null)? dateFormat.format(promoDates[0]): "미설정";
                String promoEndDate = (promoDates[1] != null)? dateFormat.format(promoDates[1]): "미설정";


                System.out.println(weaponName + " - 할인율: " + promo[0] + "%, 1+1: " + (promo[1] == 1 ? "적용됨" : "미적용"));
                System.out.println("할인 기간: "+promoStartDate+" ~ "+promoEndDate);
            }
            System.out.println("----------");
        }
    }

    public static void dailySettlement() {
        System.out.println("=== 하루 결산 ===");
        System.out.println("총 판매 금액: " + salesMoney);
        shopMoney += salesMoney;
        salesMoney = 0;
        System.out.println("상점 총 소지 금액: " + shopMoney);

        // 3턴마다 재고를 초기화하는 작업
        if (turn % 3 == 0) {
            for (int i = 0; i < weaponAmount.size(); i++) {
                for (int j = 0; j < weaponAmount.get(i).size(); j++) {
                    weaponAmount.get(i).set(j, 2); // 각 무기 재고 초기화
                }
            }
            System.out.println("재고가 모두 복구되었습니다.");
        }
    }

    // 무기 구매
    public static void buyWeapon() {
        System.out.println("어느 카테고리의 무기를 선택하시겠습니까? 번호를 입력해주세요.");
        for (int i = 0; i < weaponCategories.size(); i++) {
            System.out.print(i + ". " + weaponCategories.get(i) + "\n");
        }
        int categoryChoice = sc.nextInt();

        System.out.println("어느 무기를 고르시겠습니까? 번호를 입력해주세요.");
        for (int j = 0; j < weaponNames.get(categoryChoice).size(); j++) {
            System.out.print(j + ". " + weaponNames.get(categoryChoice).get(j) + "\n");
        }
        int weaponChoice = sc.nextInt();

        // 선택한 카테고리와 무기가 유효한 범위에 있는지 확인
        if (categoryChoice >= 0 && categoryChoice < weaponCategories.size() &&
                weaponChoice >= 0 && weaponChoice < weaponNames.get(categoryChoice).size()) {

            // 선택한 무기의 재고 확인
            if (weaponAmount.get(categoryChoice).get(weaponChoice) > 0) {
                int weaponDamageValue = weaponDamage.get(categoryChoice).get(weaponChoice);
                int weaponSpeedValue = weaponSpeed.get(categoryChoice).get(weaponChoice);
                int weaponWeightValue = weaponWeight.get(categoryChoice).get(weaponChoice);
                int weaponRangeValue = weaponRange.get(categoryChoice).get(weaponChoice);
                int weaponDurabilityValue = weaponDurability.get(categoryChoice).get(weaponChoice);

                //할인 및 1+1 프로모션 적용 확인
               originalWeaponPrice = weaponPrice.get(categoryChoice).get(weaponChoice);
               int promo[] = weaponPromotions.get(categoryChoice).get(weaponChoice);
               int discountRate = promo[0];
               int onePlusOne = promo[1];

               Date today = new Date();
               Date promoDates[] = weaponPromotionDates.get(categoryChoice).get(weaponChoice);
               Date promoStartDate = promoDates[0];
               Date promoEndDate = promoDates[1];

               if(promoStartDate != null && !today.before(promoStartDate) && !today.after(promoEndDate)){
                   selectedWeaponPrice = originalWeaponPrice -
                           (originalWeaponPrice * discountRate/100);
                   System.out.println("할인 적용 가격: "+selectedWeaponPrice+" " +
                           "(할인율: "+discountRate+"%)");
               }
               else System.out.println("할인중이 아닙니다.");
                // 고객 능력치 증가
                customerStr[customerNumber - 1] += weaponDamageValue;
                customerSpd[customerNumber - 1] += weaponSpeedValue;
                customerStamina[customerNumber - 1] += 10 - (weaponWeightValue / 10);
                customerReach[customerNumber - 1] += weaponRangeValue;

                // 구매 처리
                weaponAmount.get(categoryChoice).set(weaponChoice, weaponAmount.get(categoryChoice).get(weaponChoice) - 1);
                shopMoney += selectedWeaponPrice;
                salesMoney += selectedWeaponPrice;
                System.out.println();


                // 판매 기록 업데이트
                if (itemSalesHistory.size() <= turnCount) {
                    startNewTurn();
                }
                itemSalesHistory.get(turnCount).get(categoryChoice).set(
                        weaponChoice,
                        itemSalesHistory.get(turnCount).get(categoryChoice).get(weaponChoice) + 1
                );
                totalItemsSold++;


                // 무기 이름과 속성을 한꺼번에 저장
                String weaponInfo = weaponNames.get(categoryChoice).get(weaponChoice) +
                        " (데미지: " + weaponDamageValue + ", 속도: " + weaponSpeedValue +
                        ", 무게: " + weaponWeightValue + ")";
                customerWeapon.get(customerNumber - 1).get(categoryChoice).add(weaponInfo);

                System.out.println(weaponNames.get(categoryChoice).get(weaponChoice) + "를 구매했습니다.");
                System.out.println("능력치가 업데이트되었습니다: 힘 + " + (weaponDamageValue) + ", 민첩성 + " + (weaponSpeedValue) + ", 지구력 + " + (10 - (weaponWeightValue / 10)));
            } else {
                System.out.println("해당 무기는 판매 완료 상태입니다.");
            }
        } else {
            System.out.println("잘못된 선택입니다. 다시 시도하세요.");
        }
    }


    public static void viewPurchaseHistory() {
        System.out.println("ct" + customerNumber + "님의 인벤토리:");

        for (int i = 0; i < numCategories; i++) {
            System.out.println("카테고리: " + weaponCategories.get(i));
            for (String weaponInfo : customerWeapon.get(customerNumber - 1).get(i)) {
                System.out.println("무기: " + weaponInfo);
            }
            System.out.println();
        }
    }

    // 나의 정보 통계 확인
    public static void viewCustomerStats() {
        System.out.println("===나의 정보 통계===");
        System.out.println("힘: " + customerStr[customerNumber - 1]);
        System.out.println("민첩성: " + customerSpd[customerNumber - 1]);
        System.out.println("지구력: " + customerStamina[customerNumber - 1]);
        System.out.println("공격범위: " + customerReach[customerNumber - 1]);
    }

    public static void initializeItemSales() {
        for (int i = 0; i < numCategories; i++) {
            itemSales.add(new ArrayList<>());
            weaponPromotionDates.add(new ArrayList<>());
            for (int j = 0; j < numWeapons; j++) {
                weaponPromotionDates.get(i).add(new Date[]{null,null});
                itemSales.get(i).add(0); // 초기 판매 수량은 0으로 설정
            }
        }
    }

    // 판매 보고서 메소드
    public static void reportSales() {
        System.out.println("=== 판매 보고서 ===");

        // 1. 상점 누적 총 판매액
        System.out.println("누적 총 판매액: " + shopMoney + " 골드");

        // 2. 하루 동안 판매된 총 판매액 및 총 상품 수
        System.out.println("하루 동안 판매된 총 판매액: " + salesMoney + " 골드");
        System.out.println("하루 동안 판매된 총 상품 수: " + totalItemsSold + "개");

        // 3. 전체 기간 동안 가장 많이 팔린 품목
        String bestSellingWeapon = "";
        int maxSales = 0;

        for (int i = 0; i < itemSalesHistory.size(); i++) {
            for (int j = 0; j < itemSalesHistory.get(i).size(); j++) {
                for (int k = 0; k < itemSalesHistory.get(i).get(j).size(); k++) {
                    int sales = itemSalesHistory.get(i).get(j).get(k);
                    if (sales > maxSales) {
                        maxSales = sales;
                        bestSellingWeapon = weaponNames.get(j).get(k);
                    }
                }
            }
        }

        System.out.println("전체 기간 동안 가장 많이 팔린 품목: " + bestSellingWeapon + " (" + maxSales + "개 판매됨)");

        // 특정 기간 동안 가장 많이 팔린 품목
        System.out.println("특정 기간 동안 가장 많이 팔린 품목을 확인하시겠습니까? (1 = 예, 2 = 아니오): ");
        int choice = sc.nextInt();
        if (choice == 1) {
            System.out.print("시작 턴을 입력하세요: ");
            int startTurn = sc.nextInt();
            System.out.print("종료 턴을 입력하세요: ");
            int endTurn = sc.nextInt();

            String bestSellingPeriodWeapon = "";
            int maxPeriodSales = 0;

            for (int i = startTurn - 1; i < endTurn; i++) {
                for (int j = 0; j < itemSalesHistory.get(i).size(); j++) {
                    for (int k = 0; k < itemSalesHistory.get(i).get(j).size(); k++) {
                        int sales = itemSalesHistory.get(i).get(j).get(k);
                        if (sales > maxPeriodSales) {
                            maxPeriodSales = sales;
                            bestSellingPeriodWeapon = weaponNames.get(j).get(k);
                        }
                    }
                }
            }

            System.out.println("특정 기간 (" + startTurn + "턴 ~ " + endTurn + "턴) 동안 가장 많이 팔린 품목: " + bestSellingPeriodWeapon + " (" + maxPeriodSales + "개 판매됨)");
        }

        System.out.println("=================");
    }


    public static void handleCustomerLogout() {
        turnCount++; // 고객 로그아웃 시 turnCount 증가

        if (turnCount % 3 == 0) {
            System.out.println("==== 3명의 손님이 지나갔습니다. 하루가 끝났습니다 ====");
            dailySettlement(); // 하루 결산 및 초기화 작업 수행
            startNewTurn();
        }
    }

    // 새로운 턴을 시작할 때 호출하여 해당 턴의 판매량 리스트를 초기화
    public static void startNewTurn() {
        ArrayList<ArrayList<Integer>> turnSales = new ArrayList<>();
        for (int i = 0; i < numCategories; i++) {
            ArrayList<Integer> categorySales = new ArrayList<>();
            for (int j = 0; j < numWeapons; j++) {
                categorySales.add(0); // 초기 판매 수량은 0으로 설정
            }
            turnSales.add(categorySales);
        }
        itemSalesHistory.add(turnSales);
    }

    // 특정 기간 동안 가장 많이 팔린 품목을 조회하는 메소드
    public static void reportSalesForPeriod(int startTurn, int endTurn) {
        String bestSellingWeapon = "";
        int maxSales = 0;

        if (startTurn < 1 || endTurn > itemSalesHistory.size()) {
            System.out.println("유효한 턴 범위를 입력하세요.");
            return;
        }

        for (int i = 0; i < numCategories; i++) {
            for (int j = 0; j < numWeapons; j++) {
                int totalSalesForWeapon = 0;
                for (int turn = startTurn; turn <= endTurn && turn < itemSalesHistory.size(); turn++) {
                    totalSalesForWeapon += itemSalesHistory.get(turn).get(i).get(j);
                }
                if (totalSalesForWeapon > maxSales) {
                    maxSales = totalSalesForWeapon;
                    bestSellingWeapon = weaponNames.get(i).get(j);
                }
            }
        }

        // 결과 출력
        System.out.println("특정 기간 (" + startTurn + "턴 ~ " + endTurn + "턴) 동안 가장 많이 팔린 품목: "
                + bestSellingWeapon + " (" + maxSales + "개 판매됨)");
    }
}
