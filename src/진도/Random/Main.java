package 진도.Random;

import 진도.Person;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Random r = new Random();



        while(true) {
            System.out.println("******랜덤 룰렛******");
            System.out.println("당신의 행운에 도전하세요!");
            System.out.println("룰렛을 돌리겠습니까?(yes/no)");

            String choice = sc.next();

            if (choice.equals("yes")) {
                int result = r.nextInt(10000);
                if (result < 3000) {
                    System.out.println("'꽝'입니다. 다시 한 번 도전하세요!");
                } else if (result < 9390) {
                    System.out.println("축하드립니다. '100원'입니다!");
                } else if (result < 9850) {
                    System.out.println("축하드려요, '1000원'에 당첨되셨습니다!");
                } else if (result < 9950) {
                    System.out.println("와! '10000원'에 당첨되셨어요!");
                } else {
                    System.out.println("당신이 진정한 승리자입니다. '50000원'에 당첨되셨어요.");
                }


            } else {
                System.out.println("프로그램 종료");
                break;
            }
        }
    }
}
