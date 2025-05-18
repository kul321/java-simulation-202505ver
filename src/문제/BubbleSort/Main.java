package 문제.BubbleSort;

import 진도.Person;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args){

        /*System.out.println("몇개의 수를 배열에 넣겠습니까?(1~1000)");
        //배열의 크기(1<=N<=1000)*/
        int N = sc.nextInt();
        if(N<1 || N>1000){
            System.out.println("범위를 초과합니다. 다시 입력하세요.");
            N = sc.nextInt();
        }
        //System.out.println(N+"개의 수로 이루어진 배열");

        //둘째 줄에 N개의 정수가 공백으로 구분되어 표시
        int [] array = new int[N];
        for(int i=0; i<N; i++){
            //System.out.print("숫자를 입력하세요: ");
            array[i] = sc.nextInt();
        }

        int passes = bubbleSort(array, N);

        for(int i=0; i<N; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
        System.out.println("Passes: "+passes);
    }

    public static int bubbleSort(int[] array, int N){
        if(N<=1){
            return 0;
        }

        int passes = 0;

        //버블 정렬
        for(int i=0; i<N-1; i++){
            boolean swapped = false;
            for(int j=0; j<N-i-1; j++){
                if(array[j]>array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    swapped = true;
                }
            }
            passes++;
            if(!swapped) break;
        }


        return passes;

    }
}
