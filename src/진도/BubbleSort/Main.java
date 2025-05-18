package 진도.BubbleSort;

public class Main {
    public static void bubbleSort(int[] arr){
        int n= arr.length;

        //외부 루프: 전체 배열에 대해 (n-1)회 반복 패스 수행
        for (int i=0; i<n-1; i++){

            //내부 루프: 0번 인덱스부터 (n-2-i)번까지 인접 원소 비교
            for(int j=0; j<n-1-i; j++){

                //앞의 원소가 뒤의 원소보다 크다면 교환
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    public static void bubbleSortOptimized(int[] arr){
        int n = arr.length;
        for(int i=0; i<n-1; i++){
            boolean swapped = false; //이번 순회에서 교환 발생 여부
            for (int j = 0; j<n-1-i; j++){
                if(arr[j]>arr[j+1]){
                    //앞의 값이 크면 교환
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    swapped = true; //교환 발생
                }
            }
            if(!swapped) break;
        }
    }
}
