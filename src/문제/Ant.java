import java.util.Scanner;

public class Ant {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        /*int positions[] = {6, 12, 18};
        int directions[] = {1, -1, 1};
        int rightFallen = 24;//오른쪽으로 떨어지면
        int leftFallen = 0;//왼쪽으로 떨어지면
        boolean[] fallen = {false, false, false}; // 개미가 떨어졌는지 여부
        int time = 0;
        int rank = 1;

        while (rank <= 3) {
            time++;
            //개미들 이동
            for (int i = 0; i < positions.length; i++) {
                if (!fallen[i]) {
                    positions[i] += directions[i];
                }
            }//----------

            //충돌하면 방향 바꾸기
            for (int i = 0; i < positions.length - 1; i++) {
                for (int j = i + 1; j < positions.length; j++) {
                    if (positions[i] == positions[j] && !fallen[i] && !fallen[j]) {
                        directions[i] *= -1;
                        directions[j] *= -1;// 양쪽 다 방향 바꾸기?

                    }
                }
            }//------------

            //개미가 떨어지면 등수 표시
            for (int i = 0; i < positions.length; i++) {
                if (!fallen[i] && (positions[i] <= leftFallen || positions[i] >= rightFallen)) {
                    fallen[i] = true; // 개미 탈출 표시
                    System.out.println(rank + "등: 개미" + (i + 1));
                    rank++;
                }

            }//while문 종료

        }*/


        int ant1_position = 6;  // 개미1의 초기 위치
        int ant2_position = 12; // 개미2의 초기 위치
        int ant3_position = 18; // 개미3의 초기 위치
        int ant1_direction = 1; // 개미1의 초기 방향 (오른쪽: 1)
        int ant2_direction = -1; // 개미2의 초기 방향 (왼쪽: -1)
        int ant3_direction = 1; // 개미3의 초기 방향 (오른쪽: 1)
        int rank = 1; // 순위 초기화

        boolean ant1_fallen = false; // 개미1의 탈출 여부
        boolean ant2_fallen = false; // 개미2의 탈출 여부
        boolean ant3_fallen = false; // 개미3의 탈출 여부

        while(rank <= 3) {
            // 개미 이동
            if (!ant1_fallen) ant1_position += ant1_direction;
            if (!ant2_fallen) ant2_position += ant2_direction;
            if (!ant3_fallen) ant3_position += ant3_direction;

            // 충돌 체크 (개미1과 개미2 충돌)
            if (ant1_position == ant2_position && !ant1_fallen && !ant2_fallen) {
                ant1_direction *= -1;
                ant2_direction *= -1;
            }

            // 충돌 체크 (개미2와 개미3 충돌)
            if (ant2_position == ant3_position && !ant2_fallen && !ant3_fallen) {
                ant2_direction *= -1;
                ant3_direction *= -1;
            }

            // 개미1 탈출 체크
            if (!ant1_fallen && (ant1_position <= 0 || ant1_position >= 24)) {
                System.out.println(rank + "등: 개미1");
                rank++;
                ant1_fallen = true; // 개미1 탈출 처리
            }

            // 개미2 탈출 체크
            if (!ant2_fallen && (ant2_position <= 0 || ant2_position >= 24)) {
                System.out.println(rank + "등: 개미2");
                rank++;
                ant2_fallen = true; // 개미2 탈출 처리
            }

            // 개미3 탈출 체크
            if (!ant3_fallen && (ant3_position <= 0 || ant3_position >= 24)) {
                System.out.println(rank + "등: 개미3");
                rank++;
                ant3_fallen = true; // 개미3 탈출 처리
            }
        }
    }
}