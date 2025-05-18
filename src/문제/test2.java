import java.util.Scanner;
public class test2 {
    public static void main(String[] args) {
        playGame();
    }

    public static void playGame() {
        Scanner scanner = new Scanner(System.in);

        // 첫번째 사람의 이름과 선택 입력
        System.out.println("첫번째 사람의 이름을 입력하세요.");
        String firstName = scanner.next();
        System.out.println(firstName + "이/가 낸 것을 입력하세요. (가위, 바위, 보)");
        String firstChoice = scanner.next();

        // 두번째 사람의 이름과 선택 입력
        System.out.println("두번째 사람의 이름을 입력하세요.");
        String secondName = scanner.next();
        System.out.println(secondName + "이/가 낸 것을 입력하세요. (가위, 바위, 보)");
        String secondChoice = scanner.next();

        // 세번째 사람의 이름과 선택 입력
        System.out.println("세번째 사람의 이름을 입력하세요.");
        String thirdName = scanner.next();
        System.out.println(thirdName + "이/가 낸 것을 입력하세요. (가위, 바위, 보)");
        String thirdChoice = scanner.next();

        // 네번째 사람의 이름과 선택 입력
        System.out.println("네번째 사람의 이름을 입력하세요.");
        String fourthName = scanner.next();
        System.out.println(fourthName + "이/가 낸 것을 입력하세요. (가위, 바위, 보)");
        String fourthChoice = scanner.next();

        // 4등을 결정하는 함수 호출
        determineFourthPlace(firstName, firstChoice, secondName, secondChoice, thirdName, thirdChoice, fourthName, fourthChoice);
    }

    public static void determineFourthPlace(String firstName, String firstChoice, String secondName, String secondChoice, String thirdName, String thirdChoice, String fourthName, String fourthChoice) {
        Scanner scanner = new Scanner(System.in);
        String top1, top2, top3;

        // 네 명 중 4등을 결정
        if (!firstChoice.equals(secondChoice) && !firstChoice.equals(thirdChoice) && !firstChoice.equals(fourthChoice)) {
            top1 = secondName;
            top2 = thirdName;
            top3 = fourthName;
            System.out.println("4등 : " + firstName);
        } else if (!secondChoice.equals(firstChoice) && !secondChoice.equals(thirdChoice) && !secondChoice.equals(fourthChoice)) {
            top1 = firstName;
            top2 = thirdName;
            top3 = fourthName;
            System.out.println("4등 : " + secondName);
        } else if (!thirdChoice.equals(firstChoice) && !thirdChoice.equals(secondChoice) && !thirdChoice.equals(fourthChoice)) {
            top1 = firstName;
            top2 = secondName;
            top3 = fourthName;
            System.out.println("4등 : " + thirdName);
        } else {
            top1 = firstName;
            top2 = secondName;
            top3 = thirdName;
            System.out.println("4등 : " + fourthName);
        }

        // 4등이 제외된 후 남은 세 명의 선택 입력
        System.out.println(top1 + "이/가 낸 것을 입력하세요. (가위, 바위, 보)");
        String choice1 = scanner.next();
        System.out.println(top2 + "이/가 낸 것을 입력하세요. (가위, 바위, 보)");
        String choice2 = scanner.next();
        System.out.println(top3 + "이/가 낸 것을 입력하세요. (가위, 바위, 보)");
        String choice3 = scanner.next();

        // 3등을 결정하는 함수 호출
        determineThirdPlace(top1, choice1, top2, choice2, top3, choice3);
    }

    public static void determineThirdPlace(String name1, String choice1, String name2, String choice2, String name3, String choice3) {
        Scanner scanner = new Scanner(System.in);
        String finalist1, finalist2;

        // 세 명 중 3등을 결정
        if (!choice1.equals(choice2) && !choice1.equals(choice3)) {
            finalist1 = name2;
            finalist2 = name3;
            System.out.println("3등 : " + name1);
        } else if (!choice2.equals(choice1) && !choice2.equals(choice3)) {
            finalist1 = name1;
            finalist2 = name3;
            System.out.println("3등 : " + name2);
        } else {
            finalist1 = name1;
            finalist2 = name2;
            System.out.println("3등 : " + name3);
        }

        // 3등이 제외된 후 남은 두 명의 선택 입력
        System.out.println(finalist1 + "이/가 낸 것을 입력하세요. (가위, 바위, 보)");
        String finalChoice1 = scanner.next();
        System.out.println(finalist2 + "이/가 낸 것을 입력하세요. (가위, 바위, 보)");
        String finalChoice2 = scanner.next();

        // 1등과 2등을 결정하는 함수 호출
        determineFinalWinner(finalist1, finalChoice1, finalist2, finalChoice2);
    }

    public static void determineFinalWinner(String name1, String choice1, String name2, String choice2) {
        // 두 명 중 1등과 2등을 결정
        if (choice1.equals("바위")) {
            if (choice2.equals("가위")) {
                System.out.println("2등 : " + name2);
                System.out.println("1등 : " + name1);
            } else if (choice2.equals("보")) {
                System.out.println("2등 : " + name1);
                System.out.println("1등 : " + name2);
            }
        } else if (choice1.equals("가위")) {
            if (choice2.equals("보")) {
                System.out.println("2등 : " + name2);
                System.out.println("1등 : " + name1);
            } else if (choice2.equals("바위")) {
                System.out.println("2등 : " + name1);
                System.out.println("1등 : " + name2);
            }
        } else if (choice1.equals("보")) {
            if (choice2.equals("바위")) {
                System.out.println("2등 : " + name2);
                System.out.println("1등 : " + name1);
            } else if (choice2.equals("가위")) {
                System.out.println("2등 : " + name1);
                System.out.println("1등 : " + name2);
            }
        }
    }
}
