package 문제.ArrayList;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner sc = new java.util.Scanner(System.in);

    //초기 잔액
    static int money = 0;

    //입출금 내역을 저장할 ArrayList
    static ArrayList<Integer> transactions = new ArrayList<>();
    public static void main(String[] args){
        bankSystem();

    }

    public static void bankSystem(){
        System.out.println("=== 은행 창구 프로그램(기초) ===");
        System.out.println("10개의 입출금 내역을 입력해주세요.");
        System.out.println("입금은 양수로, 출금은 음수로 입력하세요.");

        //10개 입력받기
        int count = 0;
        while(count < 10){
            System.out.println((count+1)+"번째 거래: ");
            int amount = sc.nextInt();
            if(amount < 0 && Math.abs(amount)>money){
                System.out.println("잔액이 부족합니다.");
                System.out.println("다시 입력해주세요");
                continue;
            }
            transactions.add(amount);
            money += amount;

            count++;
        }

        printTransactions();

        printResult();




    }

    public static void printTransactions(){
        //내역 출력
        System.out.println("<입출금 내역>");

        for (int transaction : transactions){
            if(transaction>0){
                System.out.println("입금: " + transaction + "원");
            }
            if(transaction<0){
                System.out.println("출금: " + Math.abs(transaction) + "원");
            }
        }
    }


    public static void printResult(){
        System.out.print("잔액: " + money + "원");

    }


}
