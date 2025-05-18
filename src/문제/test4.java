import java.util.Scanner;

public class test4 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("지금부터 게임을 시작합니다.");
        int table[][] = new int[3][4];
        int attempts = 0;
        int maxAttempts = 10;
        boolean answered [][] = new boolean[3][4];
        boolean gameWin = false;

        //배열에 숫자 입력받기
        for(int i =0; i<3; i++){
            for (int j=0; j<4; j++){
                System.out.print("["+i+"]"+"["+j+"] 에 숫자를 입력해주세요 : ");
                table[i][j] = sc.nextInt();
            }
        }

        System.out.println("숫자가 저장되었습니다. 답변자는 지금부터 문제에 대한 답변을 해주세요.");


        while(!(gameWin)&& attempts<maxAttempts+1) {
            System.out.println("답변을 맞추고 싶으시다면 열과 행을 입력해주세요.");
            printBoard(answered, table);
            int row = sc.nextInt();
            int col = sc.nextInt();


            System.out.print(row + "행과 " + col + "열을 선택하셨습니다. 정답은 무엇입니까?");
            int guess = sc.nextInt();

            if (table[row][col] == guess) {
                System.out.println("정답입니다!");
                answered[row][col] = true;
            }
            if (table[row][col] != guess) {
                System.out.println("오답입니다.");
                System.out.println(attempts+"번째 기회입니다. 10회가 넘어갈 경우 답변자님 패배입니다.");
                attempts++;
            }

            gameWin = checkGameWin(answered);
        }//while문 종료
        if(gameWin){
            System.out.println("축하합니다. 모든 숫자를 맞췄습니다. 게임을 종료합니다.");
        }
        if (!gameWin){
            System.out.println("기회 10번을 넘어 게임을 종료합니다.");
        }

    }
    public static void printBoard(boolean answered[][], int table[][]){
        for(int i = 0; i<3; i++){
            for(int j=0; j<4; j++){
                if(answered[i][j]){
                    System.out.print("["+table[i][j]+"]");
                }
                if(!answered[i][j]){
                    System.out.print("[?]");
                }

            } System.out.println();
        }
    }

    public static boolean checkGameWin(boolean answered[][]) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (!answered[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}