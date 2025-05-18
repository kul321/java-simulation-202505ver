package 문제;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayCopy {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n;
        int m;
        int o;
        do{
            //길이 제한 체크
            System.out.print("첫번째 배열의 길이 n 입력:");
            n = sc.nextInt();

            System.out.print("두번째 배열의 길이 m 입력: ");
            m = sc.nextInt();

            System.out.print("세번째 배열의 길이 o 입력: ");
            o = sc.nextInt();

            if( n > 100000 || m > 100000 || o > 100000){
                System.out.println("배열의 길이는 100,000을 초과할 수 없음");
            }

        } while( n> 100000 && m > 100000 && o > 100000);

        int[] arr1 = new int[n];
        int[] arr2 = new int[m];
        int[] arr3 = new int[o];
        int minLength = Math.min(Math.min(n,m),o);

        System.out.print("첫번째 배열 요소 입력:");
        for(int i=0; i<n; i++) arr1[i] = sc.nextInt();
        System.out.print("두번째 배열 요소 입력:");
        for(int i=0; i<n; i++) arr2[i] = sc.nextInt();
        System.out.print("세번째 배열 요소 입력:");
        for(int i=0; i<n; i++) arr3[i] = sc.nextInt();

        int[] copy = new int[minLength];
        int count = 0;

        //공통 요소 찾기
        for(int i=0; i<n; i++){
            boolean found = false;
            for(int j = 0; j < m; j++){
               if(arr1[i] == arr2[j]){
                   for(int k = 0; k<o; k++){
                       if(arr1[i] == arr3[k]){
                           copy[count++] = arr1[i];
                           found = true;
                           break;
                       }
                   }
               }
               if(found) break;
            }

        }
        int [] result = Arrays.copyOf(copy, count);
        System.out.print("공통 요소: ");
        for(int i=0; i< count; i++){
            System.out.print(result[i]+" ");
        }
    }


}
