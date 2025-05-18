import java.util.Scanner;
public class test6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] name = new String[100];
        int[] age = new int[100];

        System.out.print("총 층 수를 입력하세요 : ");
        int level = sc.nextInt();

        System.out.print("총 호 수를 입력하세요 : ");
        int room = sc.nextInt();

        System.out.println("<아파트 이사 입주 프로그램>");
        System.out.println("입주민들 정보 입력");

        for (int n = 1; n <= level; n++) {
            for (int m = 1; m <= room; m++) {
                System.out.println(n + "층 " + m + "번째 방 정보 입력");
                System.out.print("사람의 이름 : ");
                name[room * (level - n) + m] = "입주민" + (n + 1) + (m + 1);
                System.out.print("사람의 나이 : ");
                age[room * (level - n) + m] = (n + 1) + (m + 1);
            }
        }

        System.out.println("모두 입력하셨습니다.");
        printCurrentStatus(name, age, level, room);

        int finish = 0;
        while (finish == 0) {
            System.out.println("[1. 이사 2. 입주 3. 종료]");
            System.out.print("무엇을 선택하시겠습니까? : ");
            int sel = sc.nextInt();

            if (sel == 1) {
                System.out.print("이사 할 공간의 층수 : ");
                int selLevel = sc.nextInt();
                System.out.print("이사 할 공간의 호수 : ");
                int selRoom = sc.nextInt();

                int index = room * (level - selLevel) + selRoom;
                name[index] = "빈 방";
                age[index] = 0;

                System.out.println("<이사 완료>");
                printCurrentStatus(name, age, level, room);
            } else if (sel == 2) {
                System.out.print("입주 할 공간의 층수 : ");
                int selLevel = sc.nextInt();
                System.out.print("입주 할 공간의 호수 : ");
                int selRoom = sc.nextInt();

                int index = room * (level - selLevel) + selRoom;

                System.out.print("입주 할 사람의 이름 : ");
                String newName = sc.next();
                System.out.print("입주 할 사람의 나이 : ");
                int newAge = sc.nextInt();

                name[index] = newName;
                age[index] = newAge;

                System.out.println("<입주 완료>");
                printCurrentStatus(name, age, level, room);
            } else if (sel == 3) {
                finish = 1;
            }
        }
        sc.close();
    }

    public static void printCurrentStatus(String[] name, int[] age, int level, int room) {
        for (int n = level; n >= 1; n--) {
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");
            for (int m = 1; m <= room; m++) {
                int index = room * (level - n) + m;
                System.out.printf("| 이름 : %s 나이 : %d ",
                        name[index] != null ? name[index] : "빈 방", age[index]);
            }
            System.out.println("|");
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
    }

}
