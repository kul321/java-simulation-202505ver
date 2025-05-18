package project.RobotPath;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("맵의 크기를 입력하세요 (행 열): ");
        int n = sc.nextInt();
        int m = sc.nextInt();

        Robot robot = new Robot(n, m);

        System.out.println("로봇의 시작 위치를 입력하세요 (x y): ");
        robot.setRobot(sc.nextInt(), sc.nextInt());

        System.out.println("목적지 위치를 입력하세요 (x y): ");
        robot.setDestination(sc.nextInt(), sc.nextInt());

        System.out.println("장애물의 개수를 입력하세요: ");
        int obstacles = sc.nextInt();

        System.out.println("장애물의 위치를 입력하세요 (x y): ");
        for (int i = 0; i < obstacles; i++) {
            robot.setWall(sc.nextInt(), sc.nextInt());
        }

        System.out.println("===맵 상태===");
        robot.showMap();

        System.out.println("로봇이 움직이기 시작합니다...");
        robot.moveToDestination();


    }
}
