package 문제.BubbleSort4;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        //첫째 줄에 정수 N과 K 입력
        int N = sc.nextInt(); //배열 크기
        int K = sc.nextInt(); //패스 횟수

        //입력 범위
        if(N <1 || N> 1000){
            System.out.println("N은 1~1000 사이의 값으로 입력해주세요");
        return;
        }

        if(K<0 || K> N-1){
            System.out.println("K는 0~N-1 사이의 값이어야 합니다.");
            return;
        }

        //둘째 줄에 N개 정수 공백으로 구분하여 입력
        int[ ] array = new int[N];
        for(int i = 0; i<N; i++){
            array[i] = sc.nextInt();
        }

        //제한 패스 버블 정렬
        bubbleSort4(array, N, K);

        //정렬된 결과 출력
        for(int i=0; i<N; i++){
            System.out.print(array[i] + " ");
        }

        System.out.println();
    }

    public static void bubbleSort4(int[] array, int N, int K){
        //K=0인 경우 정렬하지 않고 그대로 반환
        if( K==0 || N <= 1){
            return;
        }

        //K번 패스 수행
        for(int i = 0; i<K; i++){
            boolean swapped = false;

            for(int j=0; j< N-1; j++) {
                if (array[j] > array[j + 1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    swapped = true;
                }
            }
            if(!swapped){
                break;
            }
        }
    }
}
