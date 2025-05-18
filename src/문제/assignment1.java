import java.util.Scanner;

public class assignment1 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int initialMoney = 10000;
        System.out.println("철수와 영희가 10000원을 갖고 있습니다. 용돈을 추가로 받았습니다");
        System.out.println("추가 용돈 금액은?");
        System.out.print("추가 금액: ");
        int plusMoney = sc.nextInt();
        int currentMoney = initialMoney + plusMoney;
        System.out.println("현재 철수와 영희는 " + currentMoney + "원을 가지고 있습니다.");
        ;
        int TteokPrice = getPrice("떡볶이");
        int UdonPrice = getPrice( "우동");
        int SnackPrice = getPrice("과자");
        int drinkPrice = getPrice("음료수");

        currentMoney = orderFood("떡볶이", TteokPrice, currentMoney, true);
        currentMoney = orderFood("우동", UdonPrice, currentMoney, false);
        currentMoney = orderFood("과자", SnackPrice, currentMoney, false);
        currentMoney = orderFood("음료수", drinkPrice, currentMoney, false);


    }

    //추가되는 음식들의 가격 결정
    public static int getPrice(String foodName){
        Scanner sc = new Scanner(System.in);
        System.out.println(foodName + "의 금액을 입력하세요: ");
        return sc.nextInt();
    }

    //주문
    public static int orderFood(String foodName, int foodPrice, int currentMoney, boolean isTteok){
        Scanner sc = new Scanner(System.in);
        System.out.println(foodName + " 몇 개를 주문하시겠습니까?");
        int count = sc.nextInt();
        int totalCost = foodPrice * count;

        if(totalCost > currentMoney && isTteok){
            System.out.println("현재 " + (totalCost - currentMoney) + "원이 부족합니다.");
            System.out.print("돈이 부족하여 용돈을 더 받았습니다. 추가 용돈 금액을 입력하세요:");
            int plusMoney = sc.nextInt();
            currentMoney = currentMoney + plusMoney;
            System.out.println("현재 " + currentMoney + "원이 있습니다.");
        }

        if(totalCost <= currentMoney) {
            currentMoney = currentMoney - totalCost;
            System.out.println(foodName + "은/는 총 " + totalCost + "원이었습니다. " +
                    "현재 " + currentMoney + "원 남았습니다.");
        } else {
            System.out.println("돈이 부족하여 가게에서 쫓겨났습니다.");
        }


        return currentMoney;
    }

}














