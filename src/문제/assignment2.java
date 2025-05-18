import java.util.Scanner;
public class assignment2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("첫 번째 사람 이름 : ");
        String person1 = sc.next();
        System.out.print(person1 + "이 받은 점수 : ");
        int person1_score = sc.nextInt();

        System.out.print("두 번째 사람 이름 : ");
        String person2 = sc.next();
        System.out.print(person2 + "이 받은 점수 : ");
        int person2_score = sc.nextInt();

        System.out.print("세 번째 사람 이름 : ");
        String person3 = sc.next();
        System.out.print(person3 + "이 받은 점수 : ");
        int person3_score = sc.nextInt();

        System.out.print("네 번째 사람 이름 : ");
        String person4 = sc.next();
        System.out.print(person4 + "이 받은 점수 : ");
        int person4_score = sc.nextInt();

        //점수 비교 함수 호출
        compareScores(person1, person1_score, person2, person2_score, person3, person3_score, person4, person4_score);


        }

        public static void compareScores(String person1, int person1_score, String person2, int person2_score, String person3, int person3_score, String person4, int person4_score){
            Scanner sc = new Scanner(System.in);



            while(true){
                System.out.println("\n1. " + person1 + "\n2. " + person2 + "\n3. " + person3 + "\n4. " + person4 + "\n5. 종료");
                System.out.println("누구와 누구를 대조하시겠습니까? 종료하시기를 원하시면 5번을 두 번 입력하세요.(한 번만 입력시 에러)");
                System.out.print("첫번째 입력: ");
                int choice1 = sc.nextInt();
                System.out.print("두번째 입력: ");
                int choice2 = sc.nextInt();
                String choice1_person = "";
                String choice2_person = "";

                if ( choice1 == 1 ){
                    choice1 = person1_score;
                    choice1_person = person1;
                }
                if ( choice1 == 2 ){
                    choice1 = person2_score;
                    choice1_person = person2;
                }
                if ( choice1 == 3 ){
                    choice1 = person3_score;
                    choice1_person = person3;
                }
                if ( choice1 == 4 ){
                    choice1 = person4_score;
                    choice1_person = person4;
                }

                if (choice2 == 1){
                    choice2 = person1_score;
                    choice2_person = person1;
                }
                if (choice2 == 2){
                    choice2 = person2_score;
                    choice2_person = person2;
                }
                if (choice2 == 3){
                    choice2 = person3_score;
                    choice2_person = person3;
                }
                if (choice2 == 4){
                    choice2 = person4_score;
                    choice2_person = person4;
                }

                if (choice1 > choice2 && choice1 !=5 && choice2 !=5){
                    System.out.println(choice1_person + "의 점수(" + choice1 + ")가 " + choice2_person + "의 점수(" + choice2 + ")보다 높습니다.");
                }

                if (choice2 > choice1 && choice1 !=5 && choice2 !=5){
                    System.out.println(choice2_person + "의 점수(" + choice2 + ")가 " + choice1_person + "의 점수(" + choice1 + ")보다 높습니다.");
                }

                if (choice1 == 5 && choice2 == 5){
                    System.out.println("종료합니다.");
                    break;
                }

                if ((choice1 == 5 && choice2 != 5) || (choice2 == 5 && choice1 != 5)) {
                    System.out.println("잘못된 선택입니다.");
                }

            }

        }
}
