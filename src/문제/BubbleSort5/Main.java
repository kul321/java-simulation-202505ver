package 문제.BubbleSort5;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //첫째줄
        int N = sc.nextInt();

        //입력값 검증
        if (!isValidInput(N)) return;

        //둘째줄 - 배열에 수 넣기
        int[] array = inputArray(sc, N);

        //셋째줄
        int T = sc.nextInt(); //목표 합 값

        //버블 배열 정렬
        bubbleSort(array, N);

        //합이 T인 쌍 찾기
        findPair(array, N, T);


    }

    public static boolean isValidInput(int N) {
        //입력 범위
        if (N < 1 || N > 1000) {
            System.out.println("N은 1~1000 사이의 값으로 입력해주세요");
            return false;
        }
        return true;
    }

    public static int[] inputArray(Scanner sc, int N) {
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = sc.nextInt();
        }
        return array;
    }

    public static void bubbleSort(int[] array, int N) {
        if (N <= 1) return;

        for (int i = 0; i < N - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < N - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;


        }

    }

    public static void findPair(int[] array, int N, int T){
    int left = 0;
    int right = N -1;
    boolean found = false;

    while(left<right){
        int currentSum = array[left] + array[right];

        //쌍을 찾았을 때
        if (currentSum == T){
            System.out.println(array[left] + " " + array[right]);
            found = true;
            break;
        }

        //포인터 이동
        //합이 목표값보다 작을 때
        else if(currentSum<T){
            left++;
        }
        //합이 목표값보다 클 때
        else {
            right--;
        }
    }

    // 쌍이 없는 경우
        if(! found){
            System.out.println("NO PAIR");
        }


    }
}

