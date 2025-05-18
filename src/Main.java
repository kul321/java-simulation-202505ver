import java.util.*;

/*

        int age;
        age = 27;

        int num = 21;
        age = 26;

        // age = 22.6; -> Test용도로 써놓은거니 무시하셔도 됩니다~

        age = num;
        age = age + 1; //자기자신에게 1을 더하라.

        System.out.print("Hello, I'm ");
        System.out.print(age);
        System.out.println(".");





        int people = 10;
        int people_sum = 100000;


        String word1 = "합치면";
        String word2 = "모자란";
        String word3 = "내야만";

        System.out.println("총 " + people + "명의 사람이 다 함께 음식점에 도착하였다");
        System.out.println("총 " + people + "명이 모은 금액은 " + people_sum + "원이다.");

        int price1 = 6470000;
        String food1 = "떡볶이";

        System.out.println(food1 + "는 " + price1 + "원 어치.");

        String food2 = "감자튀김";
        int price2 = 6988000;
        System.out.println(food2 + "는 " + price2 + "원 어치.");

        String food3 = "모듬 튀김";
        int price3 = 666777;
        System.out.println(food3 + "는 " + price3 + "원 어치.");

        String food4 = "김말이 튀김";
        int price4 = 8900000;
        System.out.println(food4 + "는 " + price4 + "원 어치.");

        String food5 = "계란 튀김";
        int price5 = 70000;
        System.out.println(food5 + "는 " + price5 + "원 어치.");

        String food6 = "순대";
        int price6 = 438000;
        System.out.println(food6 + "는 " + price6 + "원 어치.");

        String food7 = "우동";
        int price7 = 660000;
        System.out.println(food7 + "는 " + price7 + "원 어치.");

        String food8 = "카라멜 마끼아또";
        int price8 = 9900;
        System.out.println(food8 + "는 " + price8 + "원 어치.");

        String food9 = "믹스 커피";
        int price9 = 999999;
        System.out.println(food9 + "는 " + price9 + "원 어치.");

        String food10 = "볶음밥";
        int price10 = 690000;
        System.out.println(food10 + "는 " + price10 + "원 어치.");

        String food11 = "김치 볶음밥";
        int price11 = 5800080;
        System.out.println(food11 + "는 " + price11 + "원 어치.");

        String food12 = "계란 볶음밥";
        int price12 = 4385000;
        System.out.println(food12 + "는 " + price12 + "원 어치.");

        String food13 = "음료수";
        int price13 = 190000;
        System.out.println(food13 + "는 " + price13 + "원 어치.");

        String food14 = "과자";
        int price14 = 150000;
        System.out.println(food14 + "는 " + price14 + "원 어치.");

        int food_sum = price1 + price2 + price3 + price4 + price5 + price6 + price7 + price8 + price9 + price10 + price11 + price11 + price12 + price13 + price14;
        System.out.println("위 음식의 가격을 모두" + food_sum + "원이고,");
        System.out.println("현재 " + (food_sum - people_sum) + "원이 " + word2 + "상태다.");
        System.out.println("따라서," + people + "명은 " + ((food_sum - people_sum) / 10) + "원을 " + word3 + "한다.");

 Scanner scanner = new Scanner(System.in);
    System.out.println("사람 A의 이름은 무엇인가요?");
    String A_name = scanner.next();
    System.out.println("사람 B의 이름은 무엇인가요?");
    String B_name = scanner.next();

    System.out.println(A_name+"가 산 음식 세가지를 입력해주세요");
    String A_food_name1 = scanner.next();
    String A_food_name2 = scanner.next();
    String A_food_name3 = scanner.next();

    System.out.println(B_name+"가 산 음식 세가지를 입력해주세요");
    String B_food_name1 = scanner.next();
    String B_food_name2 = scanner.next();
    String B_food_name3 = scanner.next();

    System.out.println(A_food_name1 + ", " + A_food_name2 + ", " + A_food_name3 +"의 가격은 각각 얼마인가요?" );
    int A_food_price1 = scanner.nextInt();
    int A_food_price2 = scanner.nextInt();
    int A_food_price3 = scanner.nextInt();

        System.out.println(B_food_name1 + ", " + B_food_name2 + ", " + B_food_name3 +"의 가격은 각각 얼마인가요?" );
    int B_food_price1 = scanner.nextInt();
    int B_food_price2 = scanner.nextInt();
    int B_food_price3 = scanner.nextInt();


    String pay = "지불";

    int sum = A_food_price1 + A_food_price2 + A_food_price3 + B_food_price1 + B_food_price2 + B_food_price3;
    int A_sum = A_food_price1 + A_food_price2 + A_food_price3;
    int B_sum = B_food_price1 + B_food_price2 + B_food_price3;
    int minus = B_sum - A_sum;

    System.out.println(A_name + "와 "+ B_name +"은 함께 음식을 준비했습니다");
    System.out.println(A_name + "는 "+ A_food_name1 + ", " + A_food_name2 + ", " + A_food_name3 + "을/를 샀습니다");
    System.out.println(B_name + "는 "+ B_food_name1 + ", " + B_food_name2 + ", " + B_food_name3 + "을/를 샀습니다");
    System.out.println(A_food_name1 + "은/는 " + A_food_price1 + "원, "
            + A_food_name2 + "은/는 " + A_food_price2 + "원, " + A_food_name3 + "은/는 " + A_food_price3+"원입니다.");
    System.out.println(B_food_name1 + "은/는 " + B_food_price1 + "원, "
            + B_food_name2 + "은/는 " + B_food_price2 + "원, " + B_food_name3 + "은/는 " + B_food_price3+"원입니다.");

    System.out.println("총 합은 " + sum + "원이 나왔습니다.");

        System.out.println(A_name + "는 "+ A_sum +"원 을 " + pay +"하고");
        System.out.println(B_name + "는 "+ B_sum +"원 을 " + pay +"하였습니다.");
        System.out.println(B_name + "는 "+ A_name +"보다" + minus +"원을 더 "+ pay +"했습니다");

    System.out.println("첫 번째 숫자 입력 : ");
    int a = sc.nextInt();
    System.out.println("두 번째 숫자 입력 : ");
    int b = sc.nextInt();

    if(a < b){
        System.out.println("b가 a보다 큽니다");
    }
        if(a > b){
            System.out.println("a가 b보다 큽니다");
        }
        if(a == b){
            System.out.println("값이 같습니다");
        }
        if(a != b){
            System.out.println("값이 다릅니다");
        }
        if(a >= b){
            System.out.println("a가 b보다 크거나, 둘이 같습니다");
        }
        if(a <= b){
            System.out.println("b가 a보다 크거나 둘이 같습니다");
        }

        if(a == 1 && b ==1){
            System.out.println("a에 1이 들어있고, b에도 1이 들어있습니다.(and)");
        }

        if(a != 1 || b !=1){
            System.out.println("a에 1이 들어있지 않거나, 혹은 b에 1이 들어있지 않습니다(or)");
        }

    String str1 = "Hello";
    String str2 = "Bye";
    String str3 = "Hello";

    if(str1.equals(str2)){
        System.out.println("str1과 str2는 같습니다.");
    } else {
        System.out.println("str1과 str2는 다릅니다.");
    }

    if(str1.equals(str3)){
        System.out.println("str1과 str3은 같습니다");
    } else {
        System.out.println("str1과 str3은 다릅니다");
    }


        System.out.println("사람이름 셋을 입력해주세요");
        String People1 = sc.next();
        String People2 = sc.next();
        String People3 = sc.next();

        System.out.println("셋의 집은 각각 몇 km 마다 떨어져있는가");
        int km = sc.nextInt();

        System.out.println(People1 + ", " + People2 + ", " + People3+"이 있다.");
        System.out.println("셋의 집은 각각 " + km +" km 마다 떨어져 있는 거리에 존재한다.");
        System.out.println("세 명의 집을 차례대로 방문하려면 몇 km를 걸어가야 하는가");
        System.out.println("답 :");

        int answer = sc.nextInt();

        if (answer ==  km*3){
            System.out.println("정답입니다.");
        } else {
            System.out.println("오답입니다.");
            System.out.println("답은 30km 입니다");
        }

 */
/*

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        //첫번째 문제
        System.out.println("a와 b를 입력해주세요");
        System.out.print("a = ");
        int a = sc.nextInt();
        System.out.print("b = ");
        int b = sc.nextInt();
        System.out.print(a + " + " + b + " = ");
        int answer = sc.nextInt();

        if (answer == a + b) {
            System.out.println("정답입니다.");
            System.out.println("다음문제: ");

            if (a == b) {
                System.out.println("서로 같은 값을 입력하셨습니다.");
            }
            if (a != b) {
                System.out.println(a + "와 " + b + "중 어느 값이 더 큽니까?");
                int answer2 = sc.nextInt();
                if (answer2 == 1) {
                    if(a > b){
                        System.out.println("정답");
                    }
                    if(a < b){
                        System.out.println("오답입니다 답은 2번입니다.");
                    }
                }
                if (answer2 == 2) {
                    if(a < b){
                        System.out.println("정답");
                    }
                    if(a > b){
                        System.out.println("오답입니다 답은 1번입니다.");
                    }
                }
            }
        }
        if(answer != a+b){
            System.out.println("오답입니다 답은 " +(a+b)+"입니다.");
        }
    }

     System.out.println("a를 입력하세요");
        int a = sc.nextInt();
        System.out.println("b를 입력하세요");
        int b = sc.nextInt();
        System.out.println("c를 입력하세요");
        int c = sc.nextInt();
        System.out.println("d를 입력하세요");
        int d = sc.nextInt();
        System.out.println("e를 입력하세요");
        int e = sc.nextInt();
        System.out.println("f를 입력하세요");
        int f = sc.nextInt();
        System.out.println("g를 입력하세요");
        int g = sc.nextInt();

    if((a>=5000 && a<=10000) & 4000<=b &&
            (a>b && (c == a + b -300)) || (a<b && (c == a + b))
            && (d == a+b+c) && (e < (a+b+c)*3) && (f > a+b+c+d+e + 5000) && (g == 2 * f)){
            System.out.println("모두 잘되었습니다.");
        }

    if(a<5000){
            System.out.println("a가 잘못되었습니다.(a가 50미만");
        }

    if(4000>b || b>10000){
        System.out.println("b가 잘못되었습니다.(b가 4000미만이거나 10000초과)");
        }

    if( (a>b && (c != a + b -300))){
        System.out.println("c가 잘못되었습니다.(b가 a보다 작을 때 c가 a+b-300이 아님) ");
        }

    if( (a<b && (c != a + b))){
            System.out.println("c가 잘못되었습니다.(b가 a보다 작을 때 c가 a+b 아님) ");
        }

    if(d != a+b+c){
        System.out.println("d가 잘못되었습니다.(d가 abc의 합이 아님)");
    }

    if(e >= (a+b+c)*3){
            System.out.println("e가 잘못되었습니다.(e가 abc의 세 배보다 같거나 큼)");
        }
    if(f <= (a+b+c+d+e)+5000){
            System.out.println("f가 잘못되었습니다.(f가 abcde+5000보다 같거나 작음)");
        }
    if(g != f * 2){
            System.out.println("g가 잘못되었습니다.(g가 f의 두 배가 아님)");
        }

    if((a<5000 & (4000>b || b>10000) &&
            ((a>b && (c != a + b -300) || (a<b && (c != a + b)))
                && (d != a+b+c) && (e >= (a+b+c)*3) && (f <= a+b+c+d+e + 5000) && (g != 2 * f)))){
            System.out.println("모두 잘못되었습니다.");
        }
Scanner sc = new Scanner(System.in);

        System.out.println("다음 세 가지 중 고르시오. 1. 감자 2. 옥수수 3. 수박");

        int select1 = sc.nextInt();
        int price = 0;


        if (select1 == 1) {
            System.out.println("1번 감자를 선택하셨습니다.");
            System.out.println("크기를 골라주세요 1. 1000원어치 2. 2000원어치 3. 3000원어치");
            int select2 = sc.nextInt();

            if (select2 == 1) {
                System.out.println("감자 1000원어치를 선택하셨습니다.");
                price= 1000;
            }

            if (select2 == 2) {
                System.out.println("감자 2000원어치를 선택하셨습니다.");
                price= 2000;
            }

            if (select2 == 3) {
                System.out.println("감자 3000원어치를 선택하셨습니다.");
                price= 3000;
            }




        }// 1 감자케이스

        if (select1 == 2) {
            System.out.println("2번 옥수수를 선택하셨습니다.");
            System.out.println("크기를 골라주세요 1. 4000원어치 2. 5000원어치 3. 6000원어치");
            int select2 = sc.nextInt();

            if (select2 == 1) {
                System.out.println("옥수수 4000원어치를 선택하셨습니다.");
                price= 4000;
            }

            if (select2 == 2) {
                System.out.println("옥수수 5000원어치를 선택하셨습니다.");
                price= 5000;
            }

            if (select2 == 3) {
                System.out.println("옥수수 6000원어치를 선택하셨습니다.");
                price= 6000;
            }




        }// 2 옥수수케이스

        if (select1 == 3) {
            System.out.println("3번 수박을 선택하셨습니다.");
            System.out.println("크기를 골라주세요 1. 10000원어치 2. 20000원어치 3. 30000원어치");
            int select2 = sc.nextInt();

            if (select2 == 1) {
                System.out.println("수박 10000원어치를 선택하셨습니다.");
                price= 10000;
            }

            if (select2 == 2) {
                System.out.println("수박 20000원어치를 선택하셨습니다.");
                price= 20000;
            }

            if (select2 == 3) {
                System.out.println("수박 30000원어치를 선택하셨습니다.");
                price= 30000;
            }




        }// 3 수박
        System.out.println("몇 개를 주문하실 건가요?");
        int count = sc.nextInt();
        System.out.println(count * price + "원입니다");

        }


    함수표현식
    
    1. 중복의 최소화
    2. 단일체계원칙 -> 하나의 함수는 하나의 역할만을 해ㅐ야한다.












    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("다음 세 가지 중 고르시오. 1. 감자 2. 옥수수 3. 수박 4. 호박");

        int fruitNumber = sc.nextInt();
        int fruit = choiceFruit(fruitNumber);

        System.out.println("크기를 골라주세요.");
        int price = choicePrice(fruit);

        System.out.println("몇 개를 주문하시겠습니까?");
        int count = sc.nextInt();

        int total = cal(count, price);

        System.out.println("총금액은 " + total + "원입니다.");

        }


    //1)과일 선택
    public static int choiceFruit(int fruitNumber){

        if (fruitNumber == 1) {
            System.out.println("1번 감자를 선택하셨습니다.");}
        if (fruitNumber == 2) {
            System.out.println("2번 옥수수를 선택하셨습니다.");}
        if (fruitNumber == 3) {
            System.out.println("3번 수박을 선택하셨습니다.");}

            return fruitNumber;
        }

    //2) 과일 사이즈 선택
    public static int choicePrice(int fruit){
            Scanner sc = new Scanner(System.in);
            int price = 0;

            if (fruit == 1) {
                System.out.println("1. 1000원어치 2. 2000원어치 3. 3000원어치");
                int sizeSelect = sc.nextInt();
                if (sizeSelect == 1) {
                    price = 1000;
                }
                if (sizeSelect == 2) {
                    price = 2000;
                }
                if (sizeSelect == 3) {
                    price = 3000;
                }
                System.out.println("감자 " + price +"원어치 선택하셨습니다.");
            }
            if (fruit == 2) {
                System.out.println("1. 4000원어치 2. 5000원어치 3. 6000원어치");
                int sizeSelect = sc.nextInt();
                if (sizeSelect == 1) {
                    price = 4000;
                }
                if (sizeSelect == 2) {
                    price = 5000;
                }
                if (sizeSelect == 3) {
                    price = 6000;
                }
                System.out.println("옥수수 " + price +"원어치 선택하셨습니다.");
            }
            if (fruit == 3){
                System.out.println("1. 4000원어치 2. 5000원어치 3. 6000원어치");
                int sizeSelect = sc.nextInt();
                if (sizeSelect ==1){
                price = 10000;
                }
                if (sizeSelect ==2){
                price = 20000;
                }
                if (sizeSelect ==3){
                price = 30000;
                }
                System.out.println("수박 " + price +"원어치 선택하셨습니다.");
            }
            return price;
            }



    //과일 개수 * 가격 총금액 계산
    public static int cal(int count, int price){
                return count * price;
            }



    1. 중복의 최소화
    2. 단일 체계 원칙 -> 하나의 함수는 하나의 역할만을 해야 한다.
    3. 의존성 최소화. -> 하나의 함수를 건드린다고 해서, 다른 함수를 건드려야하면 의존성이 성립된다.

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
        System.out.println("1."+price1+" 2."+price2+" 3."+price3);
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

    public class Main {
    public static void main(String[] args) {
    public static int foodNumberSelect(String food, int price) {
        Scanner sc = new Scanner(System.in);
        System.out.println("개수 : ");
        int number = sc.nextInt();
        return number * price;

        System.out.println("점수는?");
        int score = sc.nextInt();

        System.out.println("학점은?");
        String grade = sc.next();


        if (isGradeValid(score,grade)){
            System.out.println("올바른 학점입니다.");
        }

        if (!isGradeValid(score,grade)){
            System.out.println("올바르지 않은 학점입니다.");
        }



    }

    public static boolean isGradeValid(int score, String grade){
        String enteredGrade = scoreGrade(score);
        return enteredGrade.equals(grade);
    }


    public static String scoreGrade(int score) {
        if (score >= 100) {
            return "A";
        }

        if (score >= 90) {
            return "B";
        }

        if (score >= 80) {
            return "C";
        }

        if (score >= 70) {
            return "D";
        }

        if (score >= 60) {
            return "E";
        }

        if (score >= 50) {
            return "F";
        }
        return "잘못된 값입니다";

        int select = 0;
       int customer = 1;
       int sum = 0;

       while(true){
           System.out.println("1. 타코야끼 : 2000원\n2. 해물찜 : 7000원\n 3.튀김요리 : 5000원\n 4.자동차기름: 12000원\n5. 종료");
           select = sc.nextInt();

           if(select == 1)
           {
               sum += 2000;
               System.out.println(customer + "번째 손님이 타코야끼를 주문하셔서 현재 누적 금액 " + sum + "원입니다.");
           }
           if(select == 2){
               sum += 7000;
               System.out.println(customer + "번째 손님이 해물찜을 주문하셔서 현재 누적 금액 " + sum + "원입니다.");
           }

           if(select == 3){
               sum += 5000;
               System.out.println(customer + "번째 손님이 튀김요리를 주문하셔서 현재 누적 금액 " + sum + "원입니다.");
           }

           if(select == 4){
               sum += 12000;
               System.out.println(customer + "번째 손님이 자동차기름을 주문하셔서 현재 누적 금액 " + sum + "원입니다.");
           }

           if(select == 5){
               System.out.println(customer + "번째 손님이 종료를 누르셔서 프로그램을 종료합니다");
               System.out.println("오늘의 매출은 "+sum+"입니다.");
               break;
           }

           customer++;


       } //while문 종료


        System.out.println("몇 개의 숫자를 입력받으시겠습니까? ");
        int numerOfRepetitions = sc.nextInt();
        int runTime = 0;
        System.out.print("처음에 입력 받을 숫자: ");
        int Number = sc.nextInt();

        System.out.print("\n두번째 숫자: ");
        int inputNumber = sc.nextInt();

        System.out.print("[본인이 생각한 숫자] "+ Number + " + [본인이 생각한 숫자] " + inputNumber + " = ");
        Number += inputNumber;
        runTime++;
        System.out.println(Number);

        while(runTime < numerOfRepetitions - 1 && numerOfRepetitions > 2){
            System.out.print("\n그 다음 생각하는 숫자: ");
            inputNumber = sc.nextInt();
            System.out.print("[결과값] "+ Number + " + [본인이 생각한 숫자] " + inputNumber);
            Number += inputNumber;
            System.out.println(" = "+Number);
            runTime++;



        }
        System.out.print("\n결과 값: " + Number);
    }

        int food1_price = 0;
        int food2_price = 0;
        int food3_1_price = 0;
        int food3_2_price = 0;
        int food3_3_price = 0;
        int sum = 0;
        int result = 0;
        int number1 = 2;
        int number2 = 1;

        boolean priceDecide = false;

        while (true) {
            System.out.println("[1.구구단 2.음식점 3.계산기 4. 종료]");

            int quizSelect = sc.nextInt();
            //1. 2단만 나오는 구구단
            if (quizSelect == 1) {
                while (number2 <= 9) {
                    System.out.println(number1 + " * " + number2 + " = " + number1 * number2);
                    number2++;
                }
                number2 = 1;//개발자가 보기에도 명확.
            }
            //2. 음식점
            if (quizSelect == 2) {

                if (!priceDecide) {
                    //priceDecide가 true로 전환
                    priceDecide = true;

                    System.out.print("볶음밥의 금액: ");
                    food1_price = sc.nextInt();
                    System.out.print("자장면의 금액: ");
                    food2_price = sc.nextInt();
                    System.out.print("탕수육 (대) 금액: ");
                    food3_1_price = sc.nextInt();
                    System.out.print("탕수육 (중) 금액: ");
                    food3_2_price = sc.nextInt();
                    System.out.print("탕수육 (소) 금액: ");
                    food3_3_price = sc.nextInt();
                }


                while (true) {
                    System.out.println("[1.볶음밥 2.자장면 3.탕수육[소`중`대 따로] 4.종료]");
                    int menuSelect = sc.nextInt();

                    //어떠어떠한 함수를 분리함
                    sum = menuPlus(menuSelect, sum, food1_price, food2_price, food3_1_price, food3_2_price, food3_3_price);

                    //종료 선택
                    if (menuSelect == 4) {
                        System.out.println("총 누적 금액은 " + sum + "입니다");
                        break;
                    }
                }
            }
            //3.계산기
            if (quizSelect == 3) {
                System.out.println("1. 더하기 2. 빼기 3. 종료 ");
                int selectOperator = sc.nextInt();
                int firstNumber = 0;
                int secondNumber = 0;
                while (true) {
                    if (result == 0) {
                        System.out.print("첫번째 숫자: ");
                        firstNumber = sc.nextInt();
                        System.out.print("두번째 숫자: ");
                        secondNumber = sc.nextInt();
                        result = runOperator(selectOperator, result, firstNumber, secondNumber);
                        System.out.println("현재 결과 " + result + ", 계속 계산을 하시겠습니까? 1. 더하기 2. 빼기 3. 종료");
                        selectOperator = sc.nextInt();

                    } else {

                        System.out.print("두번째 숫자: ");
                        secondNumber = sc.nextInt();
                        result = runOperator(selectOperator, result, firstNumber, secondNumber);
                        System.out.println("현재 결과 " + result + ", 계속 계산을 하시겠습니까? 1. 더하기 2. 빼기 3. 종료");
                        selectOperator = sc.nextInt();
                    }
                    if (selectOperator == 3) {
                        break;
                    }
                }

            }// Q3  종료

            if (quizSelect == 4) {
                System.out.println("종료합니다.");
                System.out.println("음식점 누적금액[" + sum + "원] + 계산기 결과[" + result + "] = " + sum + result);
                break;
            }

        }


    }//전체 시스템


    public static int menuPlus(int menuSelect, int sum, int food1_price, int food2_price, int food3_1_price, int food3_2_price, int food3_3_price) {
        Scanner sc = new Scanner(System.in);

        if (menuSelect == 1) {
            sum += food1_price;
            System.out.println("볶음밥 " + food1_price + "원이 추가되어 현재 " + sum + "[누적금액]원입니다.");
        } else if (menuSelect == 2) {
            sum += food2_price;
            System.out.println("자장면 " + food2_price + "원이 추가되어 현재 " + sum + "[누적금액]원입니다.");
        } else if (menuSelect == 3) {
            System.out.println("1.대[" + food3_1_price + "원] 2.중[" + food3_2_price + "원] 3.소[" + food3_3_price + "원] 4.이전 메뉴");
            int food3Select = sc.nextInt();

            if (food3Select == 1) {
                sum += food3_1_price;
                System.out.println("탕수육 대자 " + food3_1_price + "원이 추가되어 현재 " + sum + "[누적금액]원입니다.");
            }

            if (food3Select == 2) {
                sum += food3_2_price;
                System.out.println("탕수육 중자 " + food3_2_price + "원이 추가되어 현재 " + sum + "[누적금액]원입니다.");
            }

            if (food3Select == 3) {
                sum += food3_3_price;
                System.out.println("탕수육 소자 " + food3_3_price + "원이 추가되어 현재 " + sum + "[누적금액]원입니다.");
            }

            if (food3Select == 4) {
                System.out.println("이전 메뉴로 돌아갑니다.");
            }


        }

        return sum;
    }

    public static int runOperator(int selectOperator, int result, int firstNumber, int secondNumber) {
        if (selectOperator == 1) {
            result = plusProcess(firstNumber, secondNumber, result);
        }
        if (selectOperator == 2) {
            result = minusProcess(firstNumber, secondNumber, result);
        }
        return result;

    public static int plusProcess(int firstNumber, int secondNumber, int result) {
        result = firstNumber + secondNumber;
        System.out.println(firstNumber + " + " + secondNumber + " = " + result);
        return result;
    }

    public static int minusProcess(int firstNumber, int secondNumber, int result) {
        if ((result == 0 && firstNumber >= secondNumber)) {
            result = firstNumber - secondNumber;
            System.out.println(firstNumber + " - " + secondNumber + " = " + result);

        } else if (result != 0 && result >= secondNumber) {
            int result2 = result - secondNumber;
            System.out.println(result + " - " + secondNumber + " = " + result2);
            result = result2;
        } else {
            System.out.println("두 번째 숫자가 더 클 수 없습니다.");
        }
        return result;
    }




int total = 0;
        int customer = 0;

        for(customer=1;;customer++){
            //메뉴 선택
            total = menu(total);


            System.out.println("계속 구매하시겠다면 1번, 아니면 2번을 눌러주세요.");
            System.out.println("현재 손님 수 : "+ customer);
            System.out.println("현재 누적 금액 : " +total+"원");
            int continueChoice = sc.nextInt();
            if(continueChoice == 2){
                System.out.println("종료합니다. 총 손님 수 : "+customer+", 누적금액 : "+total+"원");
                break;
            }
        }



    }

    public static int menu(int total){
        Scanner sc = new Scanner(System.in);
        System.out.println(" 메뉴를 선택해주세요. 1. 장어덮밥(5000원) 2. 옥수수콘(10000원) 3.감자튀김(3000원) 4. 총가격");
        int choice = sc.nextInt();

        if ( choice == 1){
            total += 5000;
            System.out.println("1번 장어덮밥을 선택하셨습니다. 총 가격은 " + total + "입니다.");
        }
        if ( choice == 2){
            total += 10000;
            System.out.println("2번 옥수수콘을 선택하셨습니다. 총 가격은 " + total + "입니다.");
        }
        if ( choice == 3){
            total += 3000;
            System.out.println("3번 감자튀김을 선택하셨습니다. 총 가격은 " + total + "입니다.");
        }

        return total;


        Scanner sc = new Scanner(System.in);
        int i = 0;
        int j = 0;

        for(i=2; i<=41; i++){
            int displayDan = (i == 18) ? 118 : (i == 20) ? 220: i;

            if ( displayDan % 2 != 0 && displayDan % 10 !=1) continue;

            //문제 2 (4, 8, 14, 10 배수 출력 ㄴㄴ)
            if ( displayDan == 4 || displayDan == 8 || displayDan ==14  || (displayDan % 10 == 0 && displayDan !=220)) continue;

            //문제 1 구구단 출력
            System.out.println("======구구단 "+displayDan +"단=====");
            for(j=1; j<=9; j++){
                System.out.println(displayDan + " x " + j + " = " + displayDan * j);
            }

            //문제 4
            if ( displayDan % 10 == 9 ){
                i +=2;
                System.out.println("======구구단 "+displayDan +"단=====");
                for(j=1; j<=9; j++){
                    System.out.println(i + " x " + j + " = " + i * j);
                }
            }
            if ( i == 41) break;
             int j = 1;
        for (int i = 2; i <= 40; i = i + 2) {
            if (i != 4 && i != 8 && i != 14) {
                if (i == 18) {
                    googoo(i,j);
                    i = 118;
                }
                if (i == 20) {
                    googoo(i,j);
                    i = 220;
                    googoo(i,j);
                }
                if (i % 10 == 0) {
                    i = i + 1;
                }
                googoo(i,j);
                if(i % 2 != 0){//홀수단에서 1을 감소시켜 다시 짝수단으로
                    i = i - 1;

                }
                if(i == 118){
                    i = 18;
                }
                if(i == 220){
                    i = 20;
                }
            }
        }
    }
    public static int googoo (int i, int j){
        System.out.println("===구구단" + i + "단===");
        for (j = 1; j <= 9; j++) {
            System.out.println(i + " x " + j + " = " + (i * j));
        }
        return i;
    }

      //누적금액
        int price_sum = 0;

        //음식이름
        String foodname1 = "껌";
        String foodname2 = "과자";
        String foodname3 = "복숭아";

        //음식가격
        int price_gum = 5000;
        int price_snack = 7000;
        int price_peach = 4000;

        //음식판매개수
        int gum_total = 0;
        int snack_total = 0;
        int peach_total = 0;

        //몇번째 고객
        int customer = 0;



        Scanner sc = new Scanner(System.in);

        while(true){
            showMenu();
            int menuChoice = sc.nextInt();


            //----1~3. 음식 구매----

            //껌
            if(menuChoice == 1){
                if (gum_total<5) {
                    customer++;
                    gum_total++;
                    price_sum = buyFood(foodname1, price_gum, customer, price_sum, gum_total);

                }
                else{
                    System.out.println("껌은 더이상 구매할 수 없습니다.(5개를 초과할 수 없음)");
                }
            }

            //과자
            if(menuChoice == 2){
                customer++;
                snack_total++;
                price_sum = buyFood(foodname2,price_snack, customer, price_sum, snack_total);
            }

            //복숭아
            if(menuChoice == 3){
                customer++;
                peach_total++;
                price_sum = buyFood(foodname3,price_peach, customer, price_sum, peach_total);
            }


            //4.환불
            if(menuChoice == 4){
                System.out.println("[1. 전체환불 2. 개별환불 ]");
                int refundChoice = sc.nextInt();
                //전체환불
                if (refundChoice == 1){
                    System.out.println("전체 환불합니다.");
                    price_sum = 0;
                }
                //개별환불
                if (refundChoice ==2){
                    System.out.println("어떤 것을 환불하시겠습니까?");
                    System.out.println("[1. 껌 2. 과자 3. 복숭아 ]");
                    int refundChoiceFood = sc.nextInt();
                    if(refundChoiceFood == 1){
                        price_sum = refundFood(foodname1,price_gum, price_sum);
                    }

                    if(refundChoiceFood == 2){
                        price_sum = refundFood(foodname2,price_snack, price_sum);
                    }

                    if(refundChoiceFood == 3){
                        price_sum = refundFood(foodname3,price_peach, price_sum);
                    }

                }
            }

            //5.종료
            if (menuChoice == 5){
                if (snack_total % 10 != 0){
                    System.out.println("과자를 더 구매해주세요");
                    System.out.println("(현재 과자 "+snack_total+"개 앞으로 "+ (10 -(snack_total % 10)) + "개 필요)");
                    continue;
                }

                if (peach_total < snack_total){
                    System.out.println("과자보다 복숭아가 적습니다. 복숭아를 더 구매해주세요");
                    System.out.println("(현재 과자 "+snack_total+"개이므로 복숭아 "+(snack_total - peach_total)+"개 추가 구매 필요");
                    continue;
                }

                System.out.println("===매출표===");
                System.out.println();
                System.out.println("오늘의 매출 : " + price_sum);
                if (gum_total > 0){
                    System.out.println("껌 x "+gum_total+"개" + (gum_total * 5000));
                }
                if (snack_total > 0){
                    System.out.println("과자 x "+snack_total+"개" + (snack_total * 5000));
                }
                if (peach_total > 0){
                    System.out.println("복숭아 X "+peach_total+"개" + (peach_total * 5000));
                }
                break;
            }
        }//메뉴 while문

    }//main

    public static void showMenu(){
        System.out.println("안내판 [1.껌5000원 2.과자7000원 3.복숭아4000원 4.환불 5.종료] ");
        System.out.println("원하는 번호를 입력하세요");
    }

    public static int buyFood(String foodname, int foodprice, int customer, int price_sum, int food_total){
        price_sum += foodprice;
        System.out.println(customer+"번째 손님은 " + foodname + "을 샀습니다.(현재 "+foodname+"은 "+food_total+"번째 구매입니다.)");
        System.out.println("현재 누적 금액 "+price_sum + "원입니다.");

        System.out.println();

        return price_sum;
    }

    public static int refundFood(String foodname, int foodprice, int price_sum){
        price_sum -= foodprice;
        System.out.println(foodname + "을 1개 환불했습니다.");
        return price_sum;
    }
            System.out.println("지금부터 게임을 진행합니다.");
        System.out.println("1번 플레이어의 이름을 입력해주세요.");
        String player1 = sc.next();
        System.out.println("2번 플레이어의 이름을 입력해주세요.");
        String player2 = sc.next();
        System.out.println(player1 + "님, " + player2 + "님 반갑습니다.");
        System.out.println(player1 + "님은 정답 번호를 각각 두번씩 입력해주세요.");
        System.out.print("첫번째 번호 입력: ");
        int inputNumber1 = sc.nextInt();
        System.out.print("두번째 번호 입력: ");
        int inputNumber2 = sc.nextInt();


        System.out.println(player2 + "님은 정답 번호를 각각 두번씩 입력해주세요.");
        System.out.print("세번째 번호 입력: ");
        int inputNumber3 = sc.nextInt();
        System.out.print("네번째 번호 입력: ");
        int inputNumber4 = sc.nextInt();

        System.out.println("번호 저장 완료");
        System.out.println("입력된 숫자가 모두 더해졌습니다.");
        int correctAnswer = inputNumber1 + inputNumber2 + inputNumber3 + inputNumber4;
        System.out.println("테스트용 (초기값) " + correctAnswer);//테스트용

        System.out.println("우리는 그 답을 맞춰보도록 하겠습니다. 최대한 빨리 맞추는 사람이 승리입니다.");

        //턴 정의
        int turn = 1;

        // 최근 5개의 숫자를 저장할 변수들
        int lastNumber1 = 0, lastNumber2 = 0, lastNumber3 = 0, lastNumber4 = 0, lastNumber5 = 0;

        // 힌트1 받음
        int hint1count = 0;
        // 힌트2 받음
        int hint2count = 0;

        // 페널티 턴(초기값 3)
        int penaltyTurn = 3;

        while (true) {
            boolean player1missed = false;
            boolean player2missed = false;

            //-------------1번 플레이어 차례------------------
            System.out.print(player1 + "님의 차례입니다: ");
            int player1number = sc.nextInt();
            System.out.println("테스트용 (현재 정답) " + correctAnswer);
            if (player1number > correctAnswer) System.out.println("정답이 " + player1 + "님이 입력하신 숫자보다 작습니다.");
            if (player1number < correctAnswer) System.out.println("정답이 " + player1 + "님이 입력하신 숫자보다 큽니다.");

            //최근 5턴간 넣은 숫자(테스트용)
            lastNumber5 = lastNumber4;
            lastNumber4 = lastNumber3;
            lastNumber3 = lastNumber2;
            lastNumber2 = lastNumber1;
            lastNumber1 = player1number;
            System.out.println(lastNumber1);
            System.out.println(lastNumber2);
            System.out.println(lastNumber3);
            System.out.println(lastNumber4);
            System.out.println(lastNumber5);

            // 힌트를 제공할 때 사용할 원래의 정답 값을 저장
            int originalCorrectAnswer = correctAnswer;


            //---정답 넣었으나 아깝게 오답처리된 경우--(힌트 받을수있는 경우)

            //규칙1 5의 배수 번째 턴에 정답을 입력한 경우 오답으로 표출 되며, 정답이 + 1200이 된다.
            if (turn % 5 == 0 && (player1number == correctAnswer)) {
                System.out.println("오답입니다.");
                player1missed = true;
                correctAnswer += 1200;
                System.out.println("테스트용 (규칙 1. 정답에 1200 더해짐) " + correctAnswer);//테스트용
            }

            //규칙1 7의 배수 번째 턴에 정답을 입력한 경우, 오답으로 표출 되며, 정답이 - 560이 된다.
            if (turn % 7 == 0 && (player1number == correctAnswer)) {
                System.out.println("오답입니다.");
                player1missed = true;
                correctAnswer -= 560;
                System.out.println("테스트용 (규칙 2. 정답에 560 감속됨) " + correctAnswer);//테스트용
            }


            // 플레이어1 힌트받기
            if (player1missed && penaltyTurn <= 0) {
                hint1count = provideHint1(originalCorrectAnswer); //힌트1 약수 제공
                hint2count = provideHint2(originalCorrectAnswer, lastNumber1, lastNumber2, lastNumber3, lastNumber4, lastNumber5); //힌트2 최근 5턴간 확인
                player1missed = false;
                penaltyTurn =3;
            }


            //-------------2번 플레이어 차례------------------
            System.out.print(player2 + "님의 차례입니다: ");
            int player2number = sc.nextInt();
            if (player2number > correctAnswer) System.out.println("정답이 " + player2 + "님이 입력하신 숫자보다 작습니다.");
            if (player2number < correctAnswer) System.out.println("정답이 " + player2 + "님이 입력하신 숫자보다 큽니다.");

            lastNumber5 = lastNumber4;
            lastNumber4 = lastNumber3;
            lastNumber3 = lastNumber2;
            lastNumber2 = lastNumber1;
            lastNumber1 = player2number;

            // 힌트를 제공할 때 사용할 원래의 정답 값을 저장
            originalCorrectAnswer = correctAnswer;

            //규칙1
            if (turn % 5 == 0 && (player2number == correctAnswer)) {
                System.out.println("오답입니다.");
                player2missed = true;
                correctAnswer += 1200;
                System.out.println("테스트용 (규칙 1. 정답에 1200 더해짐) " + correctAnswer);//테스트용
            }
            if (turn % 7 == 0 && (player2number == correctAnswer)) {
                System.out.println("오답입니다.");
                player2missed = true;
                correctAnswer -= 560;
                System.out.println("테스트용 (규칙 2. 정답에 560 감속됨) " + correctAnswer);//테스트용
            }

            // 플레이어2 힌트받기
            if (player2missed && penaltyTurn <= 0) {
                hint1count = provideHint1(originalCorrectAnswer); //힌트1 약수 제공
                hint2count = provideHint2(originalCorrectAnswer, lastNumber1, lastNumber2, lastNumber3, lastNumber4, lastNumber5);//힌트2 최근 5턴간 확인
                player2missed = false;
                penaltyTurn =3;
            }

            System.out.println("현재까지 " + turn + "턴");


            //---------------------------------------------
            // 힌트3 hint1count 값에 따른 추가 규칙 적용
            if (hint1count == 1) {
                correctAnswer += 100;
                System.out.println("힌트1에서 1개의 값이 나와 정답에 +100");
                System.out.println("테스트용(힌트 3. 힌트1에서 나온 값으로 인함) " + correctAnswer);//테스트용

            } else if (hint1count == 3) {
                correctAnswer += 360;
                System.out.println("힌트1에서 3개의 값이 나와 정답에 +360");
                System.out.println("테스트용(힌트 3. 힌트1에서 나온 값으로 인함) " + correctAnswer);//테스트용

            } else if (hint1count == 5) {
                correctAnswer += 1700;
                System.out.println("힌트1에서 5개의 값이 나와 정답에 +1700.");
                System.out.println("테스트용(힌트 3. 힌트1에서 나온 값으로 인함) " + correctAnswer);//테스트용


            }


            // 힌트 4 페널티턴
            if (0 < hint1count && hint1count < 3) {//힌트1에 따름
                penaltyTurn++;
                hint1count = 0; //힌트1 시행 초기화
            }
            if (hint2count == 1) {//힌트2에 따름
                penaltyTurn += 3;
                hint2count = 0; //힌트2 시행 초기화
            }
            if (0 < hint1count && correctAnswer >= 2000) {//힌트3에 따름
                penaltyTurn += 7;
                hint1count = 0; //힌트1 시행 초기화
            }
            if (0 < hint1count && correctAnswer < 2000) {//힌트3에 따름
                penaltyTurn -= 9;
                hint1count = 0; //힌트1 시행 초기화
            }


            // 규칙 3 8의 배수 턴 이후 자리수 합 힌트
            if (turn % 8 == 0) {
                int digitSum = sumOfDigits(correctAnswer);
                System.out.println("힌트: 정답의 각 자리수 합은 " + digitSum + "입니다.");
            }

            // 규칙 4 10의 배수 턴 이후 정답의 자리수 힌트
            if (turn % 10 == 0) {
                int digitCount = countOfDigits(correctAnswer);
                        System.out.println("힌트: 정답은 " + digitCount + "자리 수입니다.");
            }


            // 플레이어1 승리
            if (player1number == correctAnswer) {
                System.out.println(player1 + "님의 승리입니다.");
                break;
            }
            // 플레이어2 승리
            if (player2number == correctAnswer) {
                System.out.println(player1 + "님의 승리입니다.");
                break;
            }

            turn++;//턴증가
            if (penaltyTurn > 0) penaltyTurn--; //페널티턴은 감소
            if (penaltyTurn < 0) penaltyTurn =0; //페널티턴이 마이너스가 된다면 0으로 초기화
            System.out.println("현재 페널티턴 " + penaltyTurn);


        }//while문 종료


    }//main


    //==========================================================
    //힌트1
    public static int provideHint1(int correctAnswer) {
        System.out.println("힌트1. 정답의 약수는 ");
        int hint1count = 0;

        //20이상 약수, 최대 5개까지
        for (int i = 20; i <= correctAnswer; i++) {
            if (correctAnswer % i == 0) {
                if (hint1count == 5) break;
                hint1count++;
                System.out.println("정답이 " + i + "의 배수입니다.");

            }
        }
        System.out.println("더이상 일치하는 배수가 없습니다.");
        return hint1count;
    }

    //힌트2
    public static int provideHint2(int correctAnswer, int last1, int last2, int last3, int last4, int last5) {
        boolean found = false;
        int hint2count = 0;
        if (last1 == correctAnswer || last2 == correctAnswer || last3 == correctAnswer || last4 == correctAnswer || last5 == correctAnswer) {
            found = true;
        }

        //5턴간 있음
        if (found) {
            System.out.println("최근 5턴 중에서 정답이 존재합니다.");
            //힌트2 받음
            hint2count++;
        }
        //5턴간 없음
        else {
            System.out.println("최근 5턴 중에서 정답이 존재하지 않습니다.");
        }


        return hint2count;
    }

    //규칙 3 자릿수 합 표현
    public static int sumOfDigits(int correctAnswer) {
        int sum = 0;
        while (correctAnswer > 0) {
            sum += correctAnswer % 10;
            correctAnswer /= 10;
        }
        return sum;
    }

    //규칙 4 자릿수 계산
    public static int countOfDigits(int correctAnswer) {
        int count=0; //연산 횟수

        // 매개변수로 전달된 정수가 0이 될때까지 정수를 계속 10으로 나누는 식
        while(correctAnswer > 0) {

            correctAnswer = correctAnswer / 10;

            count++;
            System.out.println("자리수 확인 " + count);
        }
        return count;
    }

 */

/*public class Main {
    static Scanner sc = new Scanner(System.in);

    // 문제1 음식점용 변수---------------------------------
    static String foodname1 = "장어덮밥";
    static String foodname2 = "옥수수콘";
    static String foodname3 = "감자튀김";

    static int foodprice1 = 5000;
    static int foodprice2 = 10000;
    static int foodprice3 = 3000;

    static int foodcount1 = 0;
    static int foodcount2 = 0;
    static int foodcount3 = 0;

    static int totalprice = 0;
    static int customer = 0;

    // 문제 2 은행용 변수---------------------------------
    static int age = 0;
    static String gender = "";
    static int phonenumber = 0;
    static int reidentnumber = 0;
    static int cardpd = 0;
    static int resiAttempts = 0;
    static int resiMaxattempts = 3;
    static int residigit = 0;
    static int cardAttempts = 0;
    static int cardMaxattempts = 3;
    static int carddigit = 0;
    static int account = 100000;
    static boolean programQuit = false;

    public static void main(String[] args) {

    // 문제1 음식점
    while(true) {
        showMenu();
        int choice = sc.nextInt();
        if (processMenu(choice)) {
            break;
        }

    }//while문 종료 여기까지 음식점

    //문제 2 은행

        //개인정보 입력받기
        infoput();
        //주민번호, 카드 입력받기
        validNumber();

        if(!programQuit){
        //계좌 조회 및 입출금
        bankMenu();
        }

    }// main


    //===========================================================================
    // 음식점 메뉴 보여주기
    public static void showMenu(){
        System.out.println("===메뉴판===");
        System.out.println("1.장어덮밥(5000원) 2.옥수수콘(10000원) 3.감자튀김(3000원) 4.총 가격");
        System.out.println("메뉴를 선택하세요.");
    }

    // 음식점 메뉴 선택
    public static boolean processMenu(int choice) {
        switch (choice) {
            case 1:
                customer++;
                foodcount1++;
                totalprice = updateOrder(foodname1, foodprice1, customer, totalprice);
                return false;
            case 2:
                customer++;
                foodcount2++;
                totalprice = updateOrder(foodname2, foodprice2, customer, totalprice);
                return false;
            case 3:
                customer++;
                foodcount3++;
                totalprice = updateOrder(foodname3, foodprice3, customer, totalprice);
                return false;
            case 4:
                provideService();
                calculateTotal();
                System.out.println("계속 구매하시겠다면 1번, 아니면 2번을 눌러주세요.");
                int continuechoice = sc.nextInt();
                switch (continuechoice){
                    case 1:
                    return false;
                    case 2:
                        System.out.println("종료합니다.");
                    return true;
                }

            default:
                System.out.println("잘못된 입력입니다.");
                return false;
        }

    }


    //음식점 주문 처리
    public static int updateOrder(String foodname, int foodprice, int customer, int totalprice){
        totalprice += foodprice;
        System.out.println(customer + "번째 손님이 " + foodname + "을 선택하셨습니다.");
        System.out.println("현재 누적금액은 " + totalprice + "입니다.");
        return totalprice;
    }

    //음식점 총 가격 계산
    public static void calculateTotal(){
        System.out.println("총 가격은 "+totalprice+"입니다");
        System.out.println("장어덮팝 : "+foodcount1+"개");
        System.out.println("옥수수콘 : "+foodcount2+"개");
        System.out.println("감자튀김 : "+foodcount3+"개");
    }

    //음식점 추가 서비스
    public static void provideService(){
        if (foodcount1 > foodcount2 && foodcount1 > foodcount3){
            System.out.println(foodname1 + "을 가장 많이 구매하셔서 "+foodname2+"과 "+foodname3+"을 1개씩 무료로 드립니다.");
            foodcount2++;
            foodcount3++;
        }

        if(foodcount2 > foodcount1 && foodcount2 > foodcount3){
            System.out.println(foodname2 + "을 가장 많이 구매하셔서 "+foodname3+"을 1개씩 무료로 드립니다.");
            foodcount3++;
        }

        if(foodcount3 > foodcount1 && foodcount3 > foodcount2){
            System.out.println(foodname3 + "을 가장 많이 구매하셔서 서비스는 아무것도 없습니다.");
        }
    }

    //===========================================문제1

    // 개인정보 입력받기
    public static void infoput() {
        System.out.println("개인 정보를 입력해주세요.");
        System.out.print("나이: ");
        age = sc.nextInt();
        System.out.print("성별: ");
        gender = sc.next();
        System.out.print("전화번호: ");
        phonenumber = sc.nextInt();
    }

    //번호 확인
    public static void validNumber() {
        while(true) {
            System.out.print("주민번호: ");
            resiAttempts++;
            reidentnumber = sc.nextInt();

            if (!isValidResiLenghth(reidentnumber)) {
                System.out.println("잘못된 입력입니다. 주민번호는 6자리여야 합니다.");
                if (resiAttempts < resiMaxattempts) {
                    if (residigit > 6) {
                        resiMaxattempts++;
                    }
                    if (residigit < 6) {
                        resiMaxattempts += 2;
                    }
                    System.out.println("현재 시도 : " + resiAttempts + "회, 남은 기회: " + (resiMaxattempts - resiAttempts) + "번");
                    residigit = 0;
                }
            }

            else if(resiAttempts>=resiMaxattempts) {
                System.out.println("시도할 수 있는 횟수를 초과하였습니다.");
                programQuit = true;
                break;

            }
            else {
                System.out.println("주민번호가 정상적으로 입력되었습니다.");

                while(true) {
                    System.out.print("카드 비밀전호 4자리: ");
                    cardAttempts++;
                    cardpd = sc.nextInt();

                    if (!isValidCardLenghth(cardpd)) {
                        System.out.println("잘못된 입력입니다. 카드비밀번호는 4자리여야 합니다.");
                        System.out.println("현재 시도 : " + cardAttempts + "회, 남은 기회: " + (cardMaxattempts - cardAttempts) + "번");
                        carddigit = 0;


                    } else if (cardAttempts >= cardMaxattempts) {
                        System.out.println("시도할 수 있는 횟수를 초과하였습니다.");
                        programQuit = true;
                        break;
                    } else {
                        System.out.println("카드번호가 정상적으로 입력되었습니다.");
                        break;
                    }
                }
                break;
        }//카드까지 끝




        }//while문 종료
    }





    //주민번호 입력길이 검증
    public static boolean isValidResiLenghth(int reidentnumber){
        while(reidentnumber>0){
            reidentnumber = reidentnumber / 10;
            residigit++;
        }
        if (residigit == 6){
            return true;
        }
        else return false;
    }

    //카드 입력길이 검증
    public static boolean isValidCardLenghth(int cardpd){
        while(cardpd>0){
            cardpd = cardpd / 10;
            carddigit++;
        }
        if (carddigit == 4){
            return true;
        }
        else return false;
    }

    public static void bankMenu(){
        boolean viewAccount = false; ////먼저 조회했는지? 초기값

        for (;;) {
            System.out.println(" [은행메뉴] 1. 조회 2. 입금 3. 출금");
            int bankChoice = sc.nextInt();

            switch (bankChoice){
                case 1:
                    if (!viewAccount){
                        viewAccount = true;
                        checkingCount();
                        continue;
                    }
                    if (viewAccount){
                        System.out.println("[계좌 조회는 1회만 가능합니다]");
                    }
                    break;
                case 2:
                    if (!viewAccount){
                        System.out.println("[처음에는 계좌 조회만 가능합니다.]");
                    }
                    if (viewAccount){
                        account = plusAccount(account);

                    }
                    break;
                case 3:
                    if (!viewAccount){
                        System.out.println("[처음에는 계좌 조회만 가능합니다.]");
                    }
                    if (viewAccount){
                        account = minusAccount(account);

                    }
                    break;

            }
        }//for문 종료
    }//은행 메뉴

    // 계좌 조회
    public static void checkingCount (){
        System.out.println("현재 계좌에는 "+account+"원 있습니다.");
    }

    // 계좌 입금
    public static int plusAccount(int account){
        System.out.println("얼마를 입금하시겠습니까?");
        int plusMoney = sc.nextInt();
        account += plusMoney;
        return account;
    }

    // 계좌 출금
    public static int minusAccount(int account){
        System.out.println("얼마를 출금하시겠습니까?");
        int minusMoney = sc.nextInt();
        if (account<minusMoney){
            System.out.println("[잔액이 부족합니다.]");
            return account;
        }
        else {
            account -= minusMoney;
        }
        return account;
    }*/
    /* System.out.print("몇개까지 있다고 할까요 :");
        int n = sc.nextInt();
        int total_number []= new int[n]; //배열 크기 설정
        total_number[0]= 0;
        int current_number = 0;
        int turn = 1;

        while (true){
            System.out.println("현재 "+turn + "번째 값에 "+current_number+"을 넣었습니다.");
            System.out.println("1.더하기");
            System.out.println("2.빼기");
            System.out.println("3.특정 값 불러오기");
            System.out.print("선택: ");
            int choice = sc.nextInt();

            if (choice == 1){
                System.out.print("숫자 입력: ");
                current_number += sc.nextInt();
                total_number[turn] = current_number; //배열에 값 저장
                turn++;
            }
            if (choice == 2){
                System.out.println("숫자 입력: ");
                current_number -= sc.nextInt();
                total_number[turn] = current_number;
                turn++;
            }
            if (choice == 3){
                System.out.print("몇번째 값을 불러올까요: ");
                int index = sc.nextInt();
                total_number[turn] = total_number[index-1];
                current_number = total_number[turn];
                turn++;
            }

            for(int i=0; i<turn; i++){
                System.out.println((i+1)+"번째 값: "+total_number[i]);
            }
            System.out.println(" ");
        }

         static int n = 0;

        System.out.print("총 몇 개의 값을 받으시겠습니까?");
        n = sc.nextInt();
        int sum = 0;
        int count7 = 0;
        int arr[] = new int [n];

        //입력
        System.out.println(n +"개의 숫자를 입력하세요.");
        for(int i = 0; i<n; i++) {
            arr[i] = sc.nextInt();
            sum += arr[i];
        }
        //정출력
        normalView(arr);

        //역출력
        reverseView(arr);

        //합계
        sum = calculateSum(arr);

        //7이 몇 번이나 나왔는지
        for(int i=0; i<n; i++){
            if(arr[i] == 7){
                    count7++;
            }
        }

        //7이 나온 경우
        if(count7>0) {
            System.out.println("7은 총 " + count7 + "번 나왔습니다.");
            System.out.println("7이 들어있는 ");

        }

        if(count7<=3){
            System.out.println("7을 더 입력 받아야합니다.");
            int arr2[] = new int[n+5];
            //기존 배열 복사
            for(int i=0; i<n; i++){
                arr2[i] = arr[i];
            }
            //새로운 5개 입력받기
            for(int i =n; i<n+5; i++){
                arr2[i] = sc.nextInt();
                sum+=arr2[i];
            }

        }

    }

    public static void normalView(int arr[]){
        //정출력
        System.out.print("입력: ");
        for(int i = 0; i<n; i++){
            System.out.print(arr[i]+" ");
        }
    }

    public static void reverseView(int arr[]){
        System.out.print("\n"+"[거꾸로] ");
        for(int i = n-1; i>=0; i--){
            System.out.print(arr[i]+" ");
        }
    }

    public static int calculateSum(int arr[]){
        int sum = 0;
        for(int i=0; i<arr.length; i++){
            sum+=arr[i];
        }
        System.out.println("\n"+"총 합은 "+sum+"입니다.");
        return sum;
    }

         //1. 손님 설정
        System.out.println("몇 명의 돈을 추가하겠습니까?");
        int numCustomers = sc.nextInt();
        int customersMoney[] = new int[numCustomers];
        int sum = 0;


        for(int i=0; i<numCustomers; i++){
            System.out.print((i+1)+"번째 손님 추가할 금액 : ");
            customersMoney[i] = sc.nextInt();
        }
        for(int i=0; i<numCustomers; i++){
            System.out.println((i+1)+"번째 손님이 가진 금액 : "+ customersMoney[i]);
        }

        for (int i=0; i<numCustomers; i++) sum+= customersMoney[i];
        int originalSum = sum;



        //2. 돈빼기
        while(true) {

            System.out.println("몇 번째 손님의 돈을 빼내겠습니까?(종료하려면 0입력)");
            int choice = sc.nextInt();

            System.out.println("금액을 입력하세요(종료하려면 0입력)");
            int amount = sc.nextInt();


            //종료 조건
            if (choice == 0 && amount == 0){
                sum = 0;
                int remainingCustomers = 0;  // 남은 손님 수
                for (int i = 0; i < customersMoney.length; i++) {
                    sum += customersMoney[i];
                    if (customersMoney[i] > 0) {
                        remainingCustomers++;  // 남은 손님 수 계산
                    }
                }
                System.out.println("현재 손님들이 가진 돈의 총금액은 "+sum+"원입니다.");
                System.out.println("현재 손님 수는 "+remainingCustomers+"명입니다.");
                System.out.println("처음에 손님들이 가지고 있던 돈은 총 "+originalSum+"원이었고, 현재 금액과의 차액은 "+(originalSum-sum)+"원입니다.");
                break;
            }

            //선택된 손님의 금액 차감
            customersMoney[choice-1] -=amount;

            // 손님의 금액이 0 이하가 되는 경우, 배열에서 손님을 제거하고 앞으로 이동
            if (customersMoney[choice - 1] <= 0) {
                for (int i = choice - 1; i < customersMoney.length - 1; i++) {
                    customersMoney[i] = customersMoney[i + 1]; // 앞으로 이동
                }
                customersMoney[customersMoney.length - 1] = 0; // 마지막은 0으로 처리
            }

            int custoemrIndex = 1;
            for(int i=0; i<customersMoney.length; i++){
                if(customersMoney[i]==0) continue;
                System.out.println(custoemrIndex+"번째 손님이 가진 금액 : "+customersMoney[i]);
                custoemrIndex++;

            }


        }

    }

    static Scanner sc = new Scanner(System.in);
    static final int MAX_LIMIT = 100000; // 최대 금액 한도 설정

    static boolean[] isPreviousDayCustomer;
    public static void main(String[] args) {
     int people = 0;

        // 첫날 인원 수 입력받기
        while (true) {
            System.out.println("첫날 온 인원수? (10명 이상 가능)");
            int firstCome = sc.nextInt();

            if (firstCome >= 10) {
                people = firstCome;
                break; // 10명 이상이면 입력을 받고 종료
            } else {
                System.out.println("첫날 인원수는 10명 이상이어야 합니다. 다시 입력해주세요.");
            }
        }

        int plusCome = 0; //추가인원
        int date = 1; //현재날짜
        int carryOver = 0; //이전되는 사람수

        if(people>=10){
            carryOver = people - 10;
            people -= carryOver;
        }



        System.out.println("현재인원" + people);
        System.out.println("현재날짜" + date);
        System.out.println("남은인원(다음날로)" + carryOver);
        //-------------------------------

        // 각 사람의 초기 금액 입력받기
        int[] initialMoney = new int[people]; // 각자의 금액
        for (int personNumber = 0; personNumber < people; personNumber++) {
            System.out.println((personNumber + 1)+"번째 사람의 금액을 입력하세요 (최대 100000원): ");
            initialMoney[personNumber] = sc.nextInt();
            if (initialMoney[personNumber] > MAX_LIMIT) {
                System.out.println("금액이 100,000원을 넘을 수 없습니다. 다시 입력하세요.");
                personNumber--; // 금액을 다시 입력받기 위해 인덱스를 감소
            }
        }

        // 첫날에도 isPreviousDayCustomer 배열을 초기화
        isPreviousDayCustomer = new boolean[people];

        // 모든 손님은 첫날이므로 false로 설정
        for (int p = 0; p < people; p++) {
            isPreviousDayCustomer[p] = false;
        }



        //각 사람 주문 내역
        int totalPrices[] = new int[people]; //총 금액
        int totalPoints[] = new int[people]; //총 포인트
        int totalAmount[] = new int[people]; //총 구매량
        int choiceNumber = 0;

        //각 사람 별 음식 수량 기록(조건처리에 필요)
        int icecreamCount[] = new int[people]; //아이스크림
        int potatoCount[] = new int[people]; //감자

        // 음식 목록
        String foodNames[] = {"베이컨", "아이스크림", "감자콩"};
        int foodPrices[] = {5000, 3000, 1000};
        int foodCounts[][] = new int[people][foodNames.length]; // 음식 별 수량 기록




            //각 사람의 주문 처리
            for (int personNumber = 0; personNumber < people; personNumber++) {
            // 날짜에 따라 출력할 번호 계산
            int displayPersonNumber = personNumber  + 1; // 매일 손님 번호를 1부터 시작하도록 계산


                while (true) {
                    //메뉴 출력
                    System.out.println(
                            "[1번 베이컨 5000원, 2번 아이스크림 3000원, 3번 감자콩 1000원 " +
                                    "4.다음사람 5.종료 6. 포인트 적립 7.다음 날 8.영수증 조회 ]");
                    System.out.println(displayPersonNumber  + "번째 사람 주문 중");
                    choiceNumber = sc.nextInt();

                    // 음식 번호 선택
                    if (choiceNumber == 1 || choiceNumber == 2 || choiceNumber == 3) {
                        orderFood(choiceNumber, personNumber, totalPrices, totalAmount, icecreamCount, potatoCount, foodCounts, initialMoney);


                        // 주문 후 계속할지 물어보기
                        System.out.println("추가로 주문하시겠습니까? (1: 계속 주문, 4: 다음사람으로 넘어가기, 5: 종료)");
                        int continueOrder = sc.nextInt();

                        // 다음 사람으로 넘어가기
                        if (continueOrder == 4) {
                            // 2와 3 조건 검사(아이스크림, 감자콩)
                            boolean conditionsSatisfied = checkOrderConditions(personNumber, icecreamCount, potatoCount);
                                   if (!conditionsSatisfied) {
                                continue; // 조건을 만족하지 않으면 계속 주문!!
                            }
                            // 10명 처리 후 남은 사람이 있으면 다음 날로 넘김
                            if (personNumber == 9 && carryOver > 0) {
                                System.out.println("10명의 주문이 끝났습니다. 남은 " + carryOver + "명은 다음 날로 넘어갑니다.");
                                continue;
                            }

                            System.out.println("다음 사람으로 넘어갑니다.");
                            break;
                        }

                        // 종료하기
                        if (continueOrder == 5) {
                            System.out.println("종료합니다.");
                            return; // 프로그램 종료
                        }

                        // 계속 주문이면 반복
                        continue;
                    }




                    // 메뉴 상태일때, 문제 2와 문제 3 검사
                    if (choiceNumber == 4 || choiceNumber == 5) {
                        if(checkOrderConditions(personNumber, icecreamCount, potatoCount) ){
                            if (choiceNumber == 4) {
                                System.out.println("다음 사람으로 넘어갑니다.");
                                break;
                            }
                            if (choiceNumber == 5) {
                                System.out.println("종료합니다.");
                                return;
                            }
                        }
                    }



                        if (choiceNumber == 6) {
                            System.out.println("포인트 적립");
                            totalPoints[personNumber] = calculatePoints(totalPrices[personNumber]);
                        }



                    // 다음 날 처리
                    if (choiceNumber == 7) {
                        System.out.println("다음 날로 넘어갑니다.");

                        // 현재 손님들의 주문 초기화
                        nextDayProcess(people, totalPrices, totalPoints, totalAmount);

                        // 추가로 오는 손님 입력받기
                        while (true) {
                            System.out.println("오늘은 몇 명이 왔나요?");
                            plusCome = sc.nextInt();

                            if (plusCome >= 10) {
                                break; // 10명 이상이면 입력을 받고 종료
                            } else {
                                System.out.println("인원 수는 10명 이상이어야 합니다. 다시 입력해주세요.");
                            }
                        }

                        // 새로운 손님들의 소지금 입력받기
                        int newInitialMoney[] = new int[10]; // 새로운 손님들의 금액 배열 크기를 10으로 고정
                        for (int p = 0; p < 10; p++) {
                            System.out.println((p+1) + "번째 새로운 손님의 금액을 입력하세요 (최대 100000원): ");
                            newInitialMoney[p] = sc.nextInt();
                            if (newInitialMoney[p] > MAX_LIMIT) {
                                System.out.println("금액이 100000원을 넘을 수 없습니다. 다시 입력하세요.");
                                p--; // 금액을 다시 입력받기 위해 인덱스를 감소
                            }
                        }

                        // 기존 손님의 소지금을 제대로 업데이트
                        initialMoney = newInitialMoney;  // 새로운 손님들의 소지금을 업데이트

                        // 새로운 인원 수 계산: 기존 인원(carryOver) + 추가된 인원(plusCome)
                        int newPeopleCount = carryOver + plusCome;

                        // 새로운 인원이 10명을 넘지 않도록 처리
                        if (newPeopleCount > 10) {
                            newPeopleCount = 10;
                        }

                        // 기존 배열에 새로운 배열 크기 맞춰서 확장
                        int updatedInitialMoney[] = new int[10];
                        int updatedTotalPrices[] = new int[10];
                        int updatedTotalPoints[] = new int[10];
                        int updatedTotalAmount[] = new int[10];
                        int updatedIcecreamCount[] = new int[10];
                        int updatedPotatoCount[] = new int[10];
                        int updatedFoodCounts[][] = new int[10][foodNames.length];

                        // 기존 손님들의 금액 복사 (carryOver 만큼만)
                        for (int p = 0; p < carryOver; p++) {
                            updatedInitialMoney[p] = initialMoney[p]; // 기존 손님의 금액 복사
                            updatedTotalPrices[p] = totalPrices[p];
                            updatedTotalPoints[p] = totalPoints[p];
                            updatedTotalAmount[p] = totalAmount[p];
                            updatedIcecreamCount[p] = icecreamCount[p];
                            updatedPotatoCount[p] = potatoCount[p];
                            for (int f = 0; f < foodNames.length; f++) {
                                updatedFoodCounts[p][f] = foodCounts[p][f];
                            }
                        }

                        // 새로운 손님들의 금액 추가
                        for (int p = 0; p < plusCome; p++) {
                            if (carryOver + p < 10) {  // 인원이 10명을 넘지 않도록 처리
                                updatedInitialMoney[carryOver + p] = newInitialMoney[p]; // 새로운 손님의 금액 추가
                            }
                        }

                        // 인원 정보 업데이트
                        initialMoney = updatedInitialMoney;
                        totalPrices = updatedTotalPrices;
                        totalPoints = updatedTotalPoints;
                        totalAmount = updatedTotalAmount;
                        icecreamCount = updatedIcecreamCount;
                        potatoCount = updatedPotatoCount;
                        foodCounts = updatedFoodCounts;
                        people = newPeopleCount;


                        // 손님이 이전 날에서 넘어왔는지 확인하는 배열
                        isPreviousDayCustomer = new boolean[people];

                        // 기존 손님들의 경우는 true로 설정 (이전 날에서 넘어온 경우)
                        for (int p = 0; p < carryOver; p++) {
                            isPreviousDayCustomer[p] = true;
                        }

                        // 새로운 손님들의 경우는 false로 설정
                        for (int p = carryOver; p < people; p++) {
                            isPreviousDayCustomer[p] = false;
                        }

                        // 날짜 처리 및 남은 인원 처리
                        int peopleResult[] = numberPeople(people, date, plusCome, carryOver);
                        people = 10; // 새로운 인원은 10명으로 고정
                        date = peopleResult[1];
                        carryOver = (carryOver + plusCome) - 10;

                        System.out.println("현재인원: " + people);
                        System.out.println("현재날짜: " + date);
                        System.out.println("남은인원(다음날로): " + carryOver);

                        // 첫 번째 손님부터 다시 시작
                        personNumber = 0; // 루프가 첫 번째 손님부터 다시 시작
                        continue;
}




                        if (choiceNumber == 8) {
                            System.out.println("영수증 조회");

                            System.out.println("[1월 " + date + "일]");
                            System.out.println("===주문표===");

                            // 모든 손님들이 구매한 음식 합산하여 출력
                            System.out.println("<<모든 손님들이 총 구매한 음식>>");
                            int[] totalFoodCounts = new int[foodNames.length]; // 각 음식의 총 구매량을 저장할 배열
                            for (int j = 0; j < people; j++) {
                                for (int f = 0; f < foodNames.length; f++) {
                                    totalFoodCounts[f] += foodCounts[j][f]; // 각 손님의 음식 구매량을 합산
                                }
                            }

                            // 모든 손님들이 구매한 음식 출력
                            for (int f = 0; f < foodNames.length; f++) {
                                if (totalFoodCounts[f] > 0) {
                                    System.out.println(foodNames[f] + " x " + totalFoodCounts[f] + " = " + (totalFoodCounts[f] * foodPrices[f]) + "원");
                                }
                            }

                            // 개별 손님들의 구매 내역 출력
                            for (int j = 0; j < personNumber; j++) {
                                printReceipt(j, date, totalPrices[j], initialMoney[j], totalPoints[j] > 0, totalPoints[j], isPreviousDayCustomer[j], foodCounts, foodNames, foodPrices);
                            }
                            }



                    }



            } //for문 끝



                }//하루의 while문 종료






            //인원 수 처리
            public static int[] numberPeople ( int people, int date, int plusCome, int carryOver){
                //현재인원+추가인원
                people += plusCome;

                // 10명 단위로 처리 (carryOver 업데이트)
                carryOver = people % 10; // 10명으로 나누고 남은 인원을 carryOver에 저장
                people -= (10-carryOver); // 실제 주문 가능한 인원은 10명 단위

                // 추가로 날짜 증가 (10명 이상일 경우 날짜 증가)
                if (plusCome >= 10) {
                    date++;
                }

                return new int[]{people, date, plusCome, carryOver};
            }// 메서드 끝

            //음식 주문 메서드
            public static void orderFood(int choice, int personIndex, int[] totalPrices, int[] totalAmount,
                                         int[] icecreamCount, int[] potatoCount, int[][] foodCounts, int[] initialMoney) {
                int itemPrices[] = {5000, 3000, 1000};
                String itemNames[] = {"베이컨", "아이스크림", "감자콩"};

                // 남은 금액 계산
                int remainingMoney = initialMoney[personIndex] - totalPrices[personIndex];

                // 주문하려는 음식 가격이 남은 소지금보다 많으면 주문 차단
                if (itemPrices[choice - 1] > remainingMoney) {
                    System.out.println("잔액이 부족하여 " + itemNames[choice - 1] + "을(를) 주문할 수 없습니다. 남은 금액: " + remainingMoney + "원");
                    return; // 주문 취소
                }

                // 주문이 가능하면 금액을 추가
                totalPrices[personIndex] += itemPrices[choice - 1];
                foodCounts[personIndex][choice - 1]++;  // 선택한 음식 수량 증가

                // 각 음식에 따라 아이스크림 또는 감자콩 개수 업데이트
                if (choice == 2) {
                    icecreamCount[personIndex]++; // 아이스크림 개수 증가
                } else if (choice == 3) {
                    potatoCount[personIndex]++; // 감자콩 개수 증가
                }

                System.out.println(itemNames[choice - 1] + " 구매됨. 현재 가격: " + itemPrices[choice - 1] + "원");
            }//orderfood 끝


        //2, 3 조건 따지기
        public static boolean checkOrderConditions(int personIndex, int[] icecreamCount, int[] potatoCount) {
        // 문제 2: 아이스크림이 10의 배수가 아니면 추가 주문 요구
        if (icecreamCount[personIndex] % 10 != 0) {
            int neededIceCream = 10 - (icecreamCount[personIndex] % 10);
            System.out.println("아이스크림이 10의 배수가 아닙니다. 현재 아이스크림 개수: " + icecreamCount[personIndex] + "개. 추가로 " + neededIceCream + "개 더 구매하세요.");
            return false; // 조건을 만족하지 않음
        }

        // 문제 3: 감자콩이 아이스크림보다 적으면 추가 주문 요구
        if (potatoCount[personIndex] < icecreamCount[personIndex]) {
            int neededPotato = icecreamCount[personIndex] - potatoCount[personIndex];
            System.out.println("감자콩 수량이 부족합니다. 현재 감자콩: " + potatoCount[personIndex] + "개, 아이스크림: " + icecreamCount[personIndex] + "개. 추가로 " + neededPotato + "개 더 구매하세요.");
            return false; // 조건을 만족하지 않음
        }

        return true; // 모든 조건을 만족함
    }


            // 다음 날로 넘어갈 때 초기화하는 메서드
            public static void nextDayProcess ( int people, int[] totalPrices, int[] totalPoints, int[] totalAmount){
                // 각 사람의 주문 내역을 초기화
                for (int i = 0; i < people; i++) {
                    totalPrices[i] = 0;
                    totalPoints[i] = 0;
                    totalAmount[i] = 0;
                }
                System.out.println("모든 사람의 주문 내역이 초기화되었습니다. 새로운 날이 시작됩니다.");
            }

    // 할인된 가격 계산
    public static int applyDiscount(int price, boolean isPreviousDayCustomer) {
        if (isPreviousDayCustomer) {
            return (int) (price * 0.95); // 5% 할인 적용
        }
        return price;
    }

    // 포인트 적립
    public static int calculatePoints(int totalPrice) {
        return totalPrice / 100; // 구매 금액의 1% 포인트 적립
    }


    // 영수증 출력
    public static void printReceipt(int personIndex, int date, int totalPrice, int initialMoney,
                                    boolean hasPoints, int points, boolean isPreviousDayCustomer,
                                    int[][] foodCounts, String[] foodNames, int[] foodPrices)  {




        // 총 구매 금액 계산 (이전 날 고객일 경우 5% 할인 적용)
        int finalPrice = isPreviousDayCustomer ? applyDiscount(totalPrice, true) : totalPrice;

        System.out.println("=== " + (personIndex + 1) + "번째 손님 ===");
        System.out.println("원래 가지고 있던 금액: " + initialMoney + "원");
        System.out.println("총 구매 금액: " + totalPrice + "원");

        // 주문한 음식 출력
        for (int i = 0; i < foodNames.length; i++) {
            if (foodCounts[personIndex][i] > 0) {  // 해당 음식을 주문했을 경우에만 출력
                System.out.println(foodNames[i] + " x " + foodCounts[personIndex][i] + " = "
                        + (foodCounts[personIndex][i] * foodPrices[i]) + "원");
            }
        }

        // 5% 할인 적용 여부 출력
        if (isPreviousDayCustomer) {
            System.out.println("5% 할인 적용: " + (int) (totalPrice * 0.05) + "원 할인");
            System.out.println("할인된 금액: " + finalPrice + "원");
        }

        System.out.println("현재 가지고 있는 금액: " + (initialMoney - totalPrice) + "원");

        if (hasPoints) {
            System.out.println("적립된 포인트: " + points + "점");
        }
    }
        }


        */
/*
System.out.println("배열 arr의 크기를 입력해주세요");
            int n = sc.nextInt();
            int[] arr = new int[n];
        System.out.println("각 원소를 입력해주세요.");
            for(int i=0; i<n; i++){
                arr[i] = sc.nextInt();
            }

            int maxLength = 1;
            int currentLength = 1;

            for(int i = 1; i<arr.length; i++){
                if(arr[i]>arr[i-1]){
                    currentLength++;
                    if(currentLength>maxLength){
                        maxLength = currentLength;
                }

                }else currentLength = 1;
            }

        System.out.println(maxLength);

        Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = sdf.format(now);
        System.out.println(dateString);

        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();

        System.out.printf("%d년 %d월 %d일 %d분 %d초%n", year, month, day, hour, minute, second);

        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul" ));
        System.out.println("현재 시간:" + now);
        int year = now.getYear();
        System.out.println("현재 년도: "+year);
        int month = now.getMonthValue();
        System.out.println("현재 월: "+month);
        int day = now.getDayOfMonth();
        System.out.println("현재 일: "+day);
        int hour = now.getHour();
        System.out.println("현재 시간: "+hour);
        int minute = now.getMinute();
        System.out.println("현재 분: "+minute);
        int second = now.getSecond();
        System.out.println("현재 초: "+second);

        Date currentDate = new Date();
        System.out.println("현재 날짜: "+currentDate);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,1);
        Date tomorrow = calendar.getTime();
        System.out.println("내일 날짜: "+tomorrow);

        Calendar calendar = Calendar.getInstance();
        System.out.println("현재 시간: "+calendar.getTime());

        LocalDate yesterday = LocalDate.now().minusDays(1);
        System.out.println("어제 날짜 "+yesterday);

         LocalDate tomorrow = LocalDate.now().plusDays(1);
        System.out.println("내일 날짜 "+tomorrow);

        LocalDate specificDate = LocalDate.of(2024,5,22);
        System.out.println("특정 날짜: "+specificDate);


        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("현재 날짜와 시간: "+currentDateTime);

         Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date firstDayOfWeek = calendar.getTime();
        System.out.println("이번 주 첫째 날 (월요일): "+firstDayOfWeek);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,1);
        Date firstDayOfMonth = calendar.getTime();
        System.out.println("이번 달 첫째 날 : "+firstDayOfMonth);

        //현재 날짜와 시간
        LocalDateTime now = LocalDateTime.now();
        System.out.println("현재 날짜와 시간: "+now);

        //어제 날짜 구하기
        LocalDate yesterday = LocalDate.now().minusDays(1);
        System.out.println("어제 날짜: "+yesterday);

        //내일 날짜 구하기
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        System.out.println("내일 날짜: "+tomorrow);

        //이번 주 첫째 날 (월요일) 구하기
        LocalDate firstDayOfWeek = LocalDate.now().with(java.time.DayOfWeek.MONDAY);
        System.out.println("이번 주 첫째 날 (월요일) :" +firstDayOfWeek);

        //이번 달 첫째 날 구하기
        LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
        System.out.println("이번 달 첫째 날: "+firstDayOfMonth);

        //날짜 포맷팅
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);
        System.out.println("포맷팅된 날짜와 시간: "+formattedDate);

        //다른 형식으로 포맷팅
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");
        String formattedDate2 = now.format(formatter2);
        System.out.println("다른 형식으로 포맷팅된 날짜: "+formattedDate2);

        System.out.println("입력: 년도, 월, 일(예: 2023 02 28)");
        int year = sc.nextInt();
        int month = sc.nextInt();
        int day = sc.nextInt();

        LocalDateTime dateTime = LocalDateTime.of(year,month,day,0,0,0);
        ZoneId zone = ZoneId.of("Asia/Seoul");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(dateTime, zone);

        System.out.println("출력: "+zonedDateTime);

        ZonedDateTime tomorrow = zonedDateTime.plusDays(1);
        ZonedDateTime yesterday = zonedDateTime.minusDays(1);

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일  H시 m분");


        System.out.println("다음날: "+tomorrow.format(formatter1));
        System.out.println("전날: "+yesterday.format(formatter1));
        System.out.println("현재 시간: "+zonedDateTime.format(formatter2));

 System.out.print("현재 시간을 입력해주세요\n" +
                    "형식은 ISO 8601을 따릅니다. (예: \"2023-02-23T12:30:00+09:00\")\n: ");
            String inputTime = sc.next();

            // 현재 시간을 분 단위로 계산
            LocalDateTime now = LocalDateTime.parse(inputTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            int currentMinutes = now.getHour() * 60 + now.getMinute();


            int[] arr = new int[1440];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = i + 1;
            }

            // 현재 시간 이후의 시간들만 출력
            for (int i = 0; i < arr.length; i++) {
                int minutes = arr[i];
                if (minutes > currentMinutes) {
                    int hour = minutes / 60;
                    int minute = minutes % 60;
                    System.out.printf("%02d:%02d, ", hour, minute);
                }
            }

            int x  = 10;
            int y  = 20;

            int result = add(x,y);
            System.out.println(result);

        }

        private int add(int x, int y){
            return x + y;

             Book b;
            b = new Book();
            b.title = "자바";
            b.price = 15000;
            b.company = "청어람";
            b.page = 700;

        System.out.println(b.title);
        System.out.println(b.price);
        System.out.println(b.company);
        System.out.println(b.page);

        Person p = new Person();
        p.name = "홍길동";
        p.age = 15;
        p.weight = 68.4f;
        p.height = 175.7f;

        System.out.println(p.name);
        System.out.println(p.age);
        System.out.println(p.weight);
        System.out.println(p.height);

        int x = 10;
           int y = 20;

           //int result = add(x,y);
        Main main = new Main();
        System.out.println(main.add(10,20));
        }

        private int add(int x, int y){
        return x + y;

          int age = 34;
        String pets_name = "레미,파미";
        Minseo m1 = new Minseo();
        Minseo m = new Minseo(34,pets_name);
        LocalDate today = LocalDate.now();
        int thisYear = today.getYear();

        System.out.println("나 "+m.born());

        System.out.println("현재 "+m.age()+"살이다");
        m.jobStatus();

        System.out.println(m.name+"는 현재 "+ thisYear+"년 "
                +m.address+"에 살고 있고,"+m.pet_type+pets_name+"를 키우고 있다.");

        System.out.println(m.name+"은 "+m.goal()+"라고 말했다.");

        int a = 10;
        int b = 20;
        int c = 30;
        int minValue = Math.min(a,Math.min(b,c));
        int maxValue = Math.max(a,Math.max(b,c));

        System.out.println("minValue = " + minValue);
        System.out.println("maxValue = " + maxValue);

         System.out.println("배열의 크기 입력: ");
        int n = sc.nextInt();
        int[] arr  = new int[n];

        System.out.println("각각의 숫자 입력: ");
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }


        int smallest = Arrays.stream(arr).min().getAsInt();
        int secondmin=0;
        for(int i=0; i<n; i++){
            secondmin = Math.max(secondmin,arr[i]);
        }

        for(int i=0; i<n; i++){
            if(arr[i]>smallest) {
                secondmin = Math.min(secondmin, arr[i]);
            }
        }
        System.out.println(secondmin);
 */
/*int[] arr = {3,1,4,2,5};
        Arrays.sort(arr);

        for(int i : arr){
        System.out.println("i = " + i);
        }
           Integer[] arr  = {3,1,4,2,5};
      Arrays.sort(arr, Comparator.reverseOrder());
        System.out.println(Arrays.toString(arr));
         int arr[] = {3,1,4,2,5};
        Arrays.sort(arr,1,4);

        for(int i : arr){
            System.out.println("i = " + i);
        }

          String[] arr = {"apple","banana","cherry","date","elderberry"};
      Arrays.sort(arr, Collections.reverseOrder());
        System.out.println(Arrays.toString(arr));


    StudyPerson[] people = {new StudyPerson("Alice", 22)
                , new StudyPerson("Bob",19),
                new StudyPerson("Charlie", 28)};
        Arrays.sort(people);
        System.out.println(Arrays.toString(people));
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
        Integer[] boxedArr  = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        Arrays.sort(boxedArr, Comparator.reverseOrder()); //배열 boxedArr 역순정렬
        arr = Arrays.stream(boxedArr).mapToInt(Integer::intValue).toArray();
        System.out.println(Arrays.toString(arr));

         Student[] students = new Student[3];
        students[0] = new Student("Alice",20);
        students[1] = new Student("Bob",22);
        students[2] = new Student("Charlie",21);

        Arrays.sort(students);

        for(Student student : students){
            System.out.println(student.getName() + " : " + student.getAge());
        }
        */



public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int[] originalArray = {1,2,3,4,5};
        int[] newArray = Arrays.copyOf(originalArray,5);

        String[] originalStringArray = {"a","b","c"};
        String[] newStringArray = Arrays.copyOf(originalStringArray,5);

        for(int i : newArray){
            System.out.println("i= " + i);
        }

        for(String i: newStringArray){
            System.out.println("i = " + i);
        }

    }
 }

