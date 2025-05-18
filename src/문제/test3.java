import java.util.Scanner;

public class test3 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n;//카드 개수
        int answerCount = 1;
        do{
            System.out.print("카드 개수 : ");

            n = sc.nextInt();
            if (n <50 || n>100) {
                System.out.println("카드 개수는 50에서 100장 사이입니다.");
            }
        }while (n <50 || n>100);

        int cards[][] = new int[n][2];

        for(int i=0; i<n; i++){
            int cardNumber;
            while (true){
                System.out.print("카드의 숫자 입력:");
                cardNumber = sc.nextInt();
                //1~10000사이의 숫자
                if(cardNumber < 1|| cardNumber >10000 ) {
                    System.out.println("숫자는 1~10000 사이여야 합니다.");
                } else break;

            }
            cards[i][0] = cardNumber;
            cards[i][1] = 0; //버려지는 카드
        }// 카드 전부 입력받음

        int removedCards = (2 / 3) * n;


        //짝수카드 제거
        for (int i=0; i<removedCards; i+=2){
            cards[i][1] = 1;
        }

        //힌트 1. 4배수이거나 5배수이거나 6배수가 아니면 제거
        for (int i=0; i<n; i++){
            if(cards[i][1] == 0 && !(cards[i][0] % 6 ==0||cards[i][0] % 5 ==0 || cards[i][0] % 4 ==0 || cards[i][0] % 3 ==0)){
                cards[i][1] = 1; //버림
            }
        }

        //힌트 2. 100~8000 사이의 숫자만 남기기
        for (int i = 0; i<n; i++){
            if (cards[i][1] == 0 && (cards[i][0]<100 || cards[i][0]>8000)){
                cards[i][1] = 1; //버림
            }
        }

        //힌트 5. 3의 배수이지만 720은 들어가지 않기
        for (int i = 0; i<n; i++){
            if(cards[i][1] == 0 && cards[i][0] == 720){
                cards[i][1] = 1; //버림
            }
        }

        /* 디버깅용 출력: 카드 상태 출력
        for (int i = 0; i < n; i++) {
            System.out.println("카드 " + (i + 1) + "의 숫자: " + cards[i][0] + ", 상태: " + (cards[i][1] == 0 ? "남음" : "제거됨"));
        } */

        System.out.println("카드 중 2/3이 제거되었습니다.");
        System.out.println("카드 중 하나가 선택되었습니다.");

        int chosenCards = 0; //초기값

        // 선택된 카드 찾기
        for (int i = 0; i < n; i++) {
            if (cards[i][1] == 0) {  // 제거되지 않은 카드 중 하나를 선택
                chosenCards = cards[i][0];  // 카드의 숫자를 선택
                break;  // 첫 번째로 남은 카드를 찾으면 루프 종료
            }
        }
        // 선택 가능한 카드가 하나도 안 남았을 때
        if (chosenCards == 0) {
            System.out.println("선택할 수 있는 카드가 없습니다. 모든 카드가 제거되었습니다.");
        } else {
            // 디버깅: 선택된 카드가 정상적으로 출력되는지 확인
            //System.out.println("chosenCards: " + chosenCards);
        }

        // 힌트에 들어갈 숫자 정하기
        int questionmark1 = 0;
        int questionmark2 = 0;

        //약수 구하기
        for (int i =1; i<=chosenCards/2; i++){
            if (chosenCards % i ==0){
                questionmark1 = i; //마지막 약수를 힌트로
            }
        }
        //자릿수 구하기
        int temp = chosenCards;
        int digitNumber = 0;
        while( temp > 0){
            temp = temp / 10;
            digitNumber++;
        }
        questionmark2 = digitNumber;

        //디버깅
        System.out.println("questionmark1: "+questionmark1);
        System.out.println("questionmark2: "+questionmark2);

        for (answerCount=1; answerCount<9; answerCount++) {
            //추측값 받기
            System.out.print("카드의 숫자를 추측하세요: ");
            int guess = sc.nextInt();
            System.out.println("현재 추측값보다 위인지 아래인지 확인하려면 -1을 입력하세요 넘어가려면 0입력");
            int updown = sc.nextInt();
            //위인지 아래인지 확인
            if (updown == -1){
                if (guess > chosenCards ) System.out.println("정답보다 위의 숫자입니다.");
                if (guess < chosenCards ) System.out.println("정답보다 아래 숫자입니다.");
                if (guess == chosenCards)
                {System.out.println("정답입니다!");
                    break;}

            }

            //정답의 범위 -5~+5
            int minAnswer = chosenCards - 5;
            int maxAnswer = chosenCards + 5;

            //정답 확인
            if (guess >= minAnswer && guess <= maxAnswer) {
                System.out.println("정답입니다!");
                break;
            } else {
                System.out.println("오답입니다.");

                if (answerCount >= 5&&answerCount !=8) System.out.println("숫자는 "+questionmark1+"의 배수입니다");
                if (answerCount >= 6&&answerCount !=8) System.out.println("숫자는 정확히 "+questionmark2+"자리입니다.");
                if (answerCount >= 7&&answerCount !=8) System.out.println("숫자는 3의 배수이지만 720은 들어가지 않습니다.");

            }
        }//추측타임

        if (answerCount>=9) {
            System.out.println("더이상 추측할 수 없습니다.");
            System.out.println("game over");
        }


    }
}