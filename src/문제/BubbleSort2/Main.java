package 문제.BubbleSort2;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){

        // 첫째 줄에 정수 N, L, R을 입력받음
        int N = sc.nextInt(); // 배열 크기
        int L = sc.nextInt(); // 정렬 시작 인덱스 (1부터 시작)
        int R = sc.nextInt(); // 정렬 끝 인덱스 (1부터 시작)

        // N 입력 범위
        if (N < 1 || N > 1000) {
            System.out.println("1~1000사이의 값을 입력하세요");
            return;
        }
        // L과 R의 입력범위
        if (L < 1 || L > N || R < 1 || R > N || L > R) {
            System.out.println("L과 R은 1 <= L <= R <= N을 만족해야 합니다.");
            return;
        }

        // 둘째 줄에 N개의 정수를 공백으로 구분하여 입력받음
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = sc.nextInt();
        }

        // 부분 버블 정렬
        bubbleSort2(array, L - 1, R - 1);

        // 정렬된 결과 출력
        for (int i = 0; i < N; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }


    public static void bubbleSort2(int[] array, int start, int end) {
        // 배열 크기가 0이나 1이면 이미 정렬된 상태이므로 반환
        if (end - start + 1 <= 1) {
            return;
        }

        // 부분 버블 정렬 수행
        for (int i = 0; i < end - start; i++) {
            for (int j = start; j < end - i; j++) {

                if (array[j] > array[j + 1]) {

                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}

