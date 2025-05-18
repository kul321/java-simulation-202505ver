package 문제.Restaurant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){
        List<Menu> menus = new ArrayList<>();
        List<Order> orders = new ArrayList<>();

        /*예시 데이터
        menus.add(new Menu("짜장면",7000, "2023-02-23T13:10:00"));
        menus.add(new Menu("불고기", 15000,"2023-02-23T12:30:00" ));
        menus.add(new Menu("파스타", 20000,"2023-02-23T12:45:00" ));
        */

        //초기 메뉴 입력
        initializeMenu(menus);







        while(true){

            //현재 메뉴판 출력
            showCurrentMenu(menus);

            System.out.println("===음식점===");
            System.out.println("1.주문하기");
            System.out.println("2.음식추가");
            System.out.println("3.주문조회");
            System.out.println("4.금일정산");
            System.out.println("5.종료");
            System.out.print("선택");

            int choice = sc.nextInt();

            switch(choice){
                case 1:
                    orderFood(menus,orders);
                    break;
                case 2:
                    addFood(menus);
                    break;
                case 3:
                    viewOrders(orders);
                    break;
                case 4:
                    settleToday(orders);
                    break;
                case 5:
                    System.out.println("프로그램을 종료합니다.");
                    return;
            }
        }





    }

    //초기 메뉴 입력
    public static void initializeMenu(List<Menu> menus){
        System.out.println("===메뉴등록===");
        System.out.println("메뉴 등록을 마치려면 이름에 '종료' 입력");
        while(true){

            System.out.print("메뉴 이름: ");
            String name = sc.next();
            if(name.equals("종료")) break;

            System.out.print("가격: ");
            int price = sc.nextInt();
            menus.add(new Menu(name, price));

            System.out.println("메뉴가 등록되었습니다.");

        }
    }

    //메뉴판 출력 메서드
    public static void showCurrentMenu(List<Menu> menus){
        System.out.println("---메뉴판---");
        for(int i = 0; i< menus.size(); i++){
            System.out.println((i+1) + ". " + menus.get(i));
        }
    }

    //1.주문하기
    public static void orderFood(List<Menu> menus, List<Order> orders){
        while(true) {
            System.out.print("주문할 메뉴 번호 선택(종료:0)");
            int menuNum = sc.nextInt();

            if (menuNum == 0) {
                break;
            }

            Menu selectedMenu = menus.get(menuNum-1);
            LocalDateTime currentTime = LocalDateTime.now();
            orders.add(new Order(selectedMenu.getName(), selectedMenu.getPrice(),currentTime));
        }
    }

    //2.음식추가
    public static void addFood(List<Menu> menus){
        while (true){
            System.out.print("추가할 음식의 이름('종료'입력시 종료)");
            String name = sc.next();

            if (name.equals("종료")){
                break;
            }
            System.out.print("추가할 음식의 가격");
            int price = sc.nextInt();
            menus.add(new Menu(name,price));
            System.out.println("음식이 추가되었습니다.");
        }
    }

    //3.주문조회
    public static void viewOrders(List<Order> orders){
        System.out.println("1. 가격기준정렬 보기");
        System.out.println("2. 시각기준정렬 보기");
        System.out.println("3. 종료");

       int choice = sc.nextInt();

       switch (choice){

        case 1:
         //가격 기준 정렬
        orders.sort(Comparator.comparing(Order::getPrice));
        System.out.println("===가격 기준 정렬===");
        for(int i =0; i< orders.size();i++){
            System.out.println(orders.get(i));
         }
        break;

        case 2:
        //시각 기준 정렬
        orders.sort(Comparator.comparing(Order::getOrderTime));
        System.out.println("===시각 기준 정렬===");
        for(int i=0; i< orders.size(); i++){
            System.out.println(orders.get(i));
        }
        break;

        case 3:
            break;
       }
    }

    //4.금일정산
    public static void settleToday(List<Order> orders){
        LocalDateTime today = LocalDateTime.now();
        int totalCount = 0;
        int totalSales = 0;

        System.out.println("===오늘의 정산===");

        for(int i = 0; i < orders.size(); i++) {

            if(orders.get(i).getOrderTime().toLocalDate().equals(today.toLocalDate())) {
                totalCount++;
                totalSales += orders.get(i).getPrice();

                System.out.println(orders.get(i).getName() + ": " +
                        orders.get(i).getPrice() + "원 (" +
                        orders.get(i).getOrderTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + ")");
            }
        }

        System.out.println("-------------------");
        System.out.println("총 주문 건수: " + totalCount + "건");
        System.out.println("총 매출액: " + totalSales + "원");
        System.out.println("===================");
    }
}
