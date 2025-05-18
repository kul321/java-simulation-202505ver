package 문제.BubbleSort3;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // 첫째 줄에 정수 N을 입력받음
        int N = sc.nextInt();

        // 입력 범위 검증
        if (N < 1 || N > 1000) {
            System.out.println("N은 1에서 1000 사이의 값이어야 합니다.");
            return;
        }

        // 둘째 줄에 N개의 정수를 공백으로 구분하여 입력받음
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = sc.nextInt();
        }

        // 셋째 줄에 정렬 조건을 입력받음 (EVEN 또는 ODD)
        String condition = sc.next().toUpperCase();

        if (!condition.equals("EVEN") && !condition.equals("ODD")) {
            System.out.println("'EVEN' 또는 'ODD'를 입력하세요.");
            return;
        }

        // 조건부 정렬 수행
        conditionalSort(array, condition);

        // 정렬된 결과 출력
        for (int i = 0; i < N; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

   //조건부 정렬
    public static void conditionalSort(int[] array, String condition) {
        // 조건에 맞는 원소들을 저장할 ArrayList
        ArrayList<Integer> targetElements = new ArrayList<>();

        // 조건에 맞는 위치를 저장할 ArrayList
        ArrayList<Integer> targetPositions = new ArrayList<>();

        // 조건에 맞는 원소들을 추출하고 위치 기록
        for (int i = 0; i < array.length; i++) {
            boolean isEven = array[i] % 2 == 0;
            boolean isTarget = (condition.equals("EVEN") && isEven) ||
                    (condition.equals("ODD") && !isEven);

            if (isTarget) {
                targetElements.add(array[i]);
                targetPositions.add(i);
            }
        }

        // 조건에 맞는 원소들만 정렬 (버블 정렬 사용)
        bubbleSort(targetElements);

        // 정렬된 값들을 원래 위치에 다시 넣기
        for (int i = 0; i < targetElements.size(); i++) {
            array[targetPositions.get(i)] = targetElements.get(i);
        }
    }


    public static void bubbleSort(ArrayList<Integer> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    // 원소 교환
                    int temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }
}