import java.util.Scanner;

public class test1 {

    public static void main(String[] args) {
        int num = foodSelect();
        System.out.println("num = " + num);
    }

    public static int foodSelect() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1.감자 2.옥수수 3.수박 4.호박");
        int select = sc.nextInt();

        if (select == 1) {
            return foodPriceSelect("감자", 1000, 2000, 3000);
        }
        if (select == 2) {
            return foodPriceSelect("옥수수", 1, 2, 3);
        }
        if (select == 3) {
            return foodPriceSelect("수박", 5, 2, 3);
        }
        if (select == 4) {
            return foodPriceSelect("호박", 15, 12, 13);
        }
        return 0;
    }

    public static int foodPriceSelect(String food, int price1, int price2, int price3) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1." + price1 + " 2." + price2 + " 3." + price3);
        int select = sc.nextInt();
        if (select == 1) {
            return foodNumberSelect(food, price1);
        }
        if (select == 2) {
            return foodNumberSelect(food, price2);
        }
        if (select == 3) {
            return foodNumberSelect(food, price3);
        }
        return 0;
    }

    public static int foodNumberSelect(String food, int price) {
        Scanner sc = new Scanner(System.in);
        System.out.println("개수 : ");
        int number = sc.nextInt();
        return number * price;

    }
}