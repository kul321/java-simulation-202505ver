package 진도.Random;

import java.util.Random;
import java.util.Scanner;

public class Main2 {
    // 입력 범위 상수
    static final int MIN_PLAYERS = 1;
    static final int MAX_PLAYERS = 8;
    static final int MIN_CHANCES = 1;
    static final int MAX_CHANCES = 10;

    // 확률 관련 상수
    // 최고 당첨은 0.1% (9990-10000)
    static final int TOTAL_CASES = 10000;
    static final int FAIL_BOUNDARY = 3000;         // 30%
    static final int SMALL_WIN_BOUNDARY = 9390;    // 63.9%
    static final int MEDIUM_WIN_BOUNDARY = 9890;   // 5%
    static final int LARGE_WIN_BOUNDARY = 9990;    // 1%


    // 점수 관련 상수
    static final int FAIL_SCORE = 0;
    static final int SMALL_WIN_SCORE = 1;
    static final int MEDIUM_WIN_SCORE = 10;
    static final int LARGE_WIN_SCORE = 100;
    static final int JACKPOT_SCORE = 500;


    // 보너스 라운드 관련 상수
    static final int SCORE_GAP_UNIT = 700;

    static Scanner sc = new Scanner(System.in);
    static Random r = new Random();

    public static void main(String[] args) {
        try {
            showGameIntro();

            // 게임 설정 초기화
            int humanCount = getValidHumanCount();
            int aiCount = getValidAICount();
            int totalPlayers = humanCount + aiCount;

            // 플레이어 정보 초기화
            boolean[] isAI = new boolean[totalPlayers];
            String[] playerNames = initializePlayers(humanCount, aiCount, isAI);

            // 게임 기회 설정
            int chancesPerPlayer = getValidChancesPerPlayer();
            int[] scores = new int[totalPlayers];
            int[] remainingChances = initializeChances(totalPlayers, chancesPerPlayer);

            // 게임 진행
            playGame(totalPlayers, isAI, playerNames, scores, remainingChances);

            // 결과 출력
            announceWinner(totalPlayers, playerNames, scores);

        } catch (IllegalArgumentException e) {
            System.out.println("오류 발생: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("예상치 못한 오류가 발생했습니다: " + e.getMessage());
        }
    }

    public static void showGameIntro() {
        System.out.println("******랜덤 룰렛******");
        System.out.println("당신의 행운에 도전하세요!");
        System.out.println("\n=== 당첨 구간 및 배점 안내 ===");
        System.out.printf("꽝 (30%%): %d점\n", FAIL_SCORE);
        System.out.printf("100원 (63.9%%): %d점\n", SMALL_WIN_SCORE);
        System.out.printf("1,000원 (5%%): %d점\n", MEDIUM_WIN_SCORE);
        System.out.printf("10,000원 (1%%): %d점\n", LARGE_WIN_SCORE);
        System.out.printf("50,000원 (0.1%%): %d점\n", JACKPOT_SCORE);
        System.out.println("==========================\n");
    }

    public static int getValidHumanCount() {
        System.out.println("실제 사람 플레이어는 몇 명이 게임을 진행하나요?");
        System.out.printf("(%d-%d명): ", MIN_PLAYERS, MAX_PLAYERS);
        int count = sc.nextInt();
        sc.nextLine();

        if (count < MIN_PLAYERS || count > MAX_PLAYERS) {
            throw new IllegalArgumentException("플레이어 수는 " + MIN_PLAYERS + "명에서 " + MAX_PLAYERS + "명 사이여야 합니다.");
        }
        return count;
    }

    public static int getValidAICount() {
        System.out.println("AI 플레이어는 몇 명으로 할까요?");
        System.out.printf("(%d-%d명): ", MIN_PLAYERS, MAX_PLAYERS);
        int count = sc.nextInt();
        sc.nextLine();

        if (count < MIN_PLAYERS || count > MAX_PLAYERS) {
            throw new IllegalArgumentException("AI 수는 " + MIN_PLAYERS + "명에서 " + MAX_PLAYERS + "명 사이여야 합니다.");
        }
        return count;
    }

    public static String[] initializePlayers(int humanCount, int aiCount, boolean[] isAI) {
        int totalPlayers = humanCount + aiCount;
        String[] playerNames = new String[totalPlayers];

        // 실제 플레이어 이름 입력
        for(int i=0; i<humanCount; i++){
            System.out.printf("플레이어 %d의 이름을 입력하세요: ", i+1);
            String name = sc.nextLine().trim();
            if(name.isEmpty()) {
                throw new IllegalArgumentException("플레이어 이름은 비어있을 수 없습니다.");
            }
            playerNames[i] = name;
            isAI[i] = false;
        }

        // AI 플레이어 이름 자동 생성
        for(int i = humanCount; i<totalPlayers; i++){
            playerNames[i] = "AI_" + (i-humanCount+1);
            isAI[i] = true;
        }

        return playerNames;
    }

    public static int getValidChancesPerPlayer() throws Exception {
        System.out.println("각 플레이어당 몇 번의 기회를 드릴까요?");
        System.out.printf("(%d-%d번): ", MIN_CHANCES, MAX_CHANCES);
        int chances = sc.nextInt();
        sc.nextLine();

        if (chances < MIN_CHANCES || chances > MAX_CHANCES) {
            throw new Exception("기회는 " + MIN_CHANCES + "번에서 " + MAX_CHANCES + "번 사이여야 합니다.");
        }
        return chances;
    }

    public static int[] initializeChances(int totalPlayers, int chancesPerPlayer) {
        int[] chances = new int[totalPlayers];
        for (int i = 0; i < totalPlayers; i++) {
            chances[i] = chancesPerPlayer;
        }
        return chances;
    }

    public static void playGame(int totalPlayers, boolean[] isAI,
                                String[] playerNames, int[] scores, int[] remainingChances) {
        int currentPlayer = 0;
        int roundCount = 1;
        boolean gameEnded = false;

        while (!gameEnded) {
            if (remainingChances[currentPlayer] > 0) {
                System.out.printf("\n=== %d 라운드 ===\n", roundCount);
                handlePlayerTurn(currentPlayer, isAI, playerNames, scores, remainingChances);

                if(totalPlayers >= 4) {
                    checkBonusRoundWithFourthPlace(totalPlayers, playerNames, scores, remainingChances);
                }

                // 턴 종료 후 상황 요약
                showRoundSummary(totalPlayers, playerNames, scores, remainingChances);
            }

            currentPlayer = (currentPlayer + 1) % totalPlayers;
            if(currentPlayer == 0) roundCount++;

            gameEnded = isGameEnded(remainingChances);
        }
    }

    public static void handlePlayerTurn(int currentPlayer, boolean[] isAI,
                                        String[] playerNames, int[] scores, int[] remainingChances) {
        showPlayerTurnInfo(isAI[currentPlayer], playerNames[currentPlayer],
                remainingChances[currentPlayer]);

        int[] result = spinRoulette();
        int points = result[0];

        scores[currentPlayer] += points;
        System.out.printf("%s님의 현재 총점: %d점\n", playerNames[currentPlayer],
                scores[currentPlayer]);
        remainingChances[currentPlayer]--;
    }

    public static void showPlayerTurnInfo(boolean isAI, String playerName, int chances) {
        if(isAI){
            System.out.printf("\n%s님의 차례입니다. (남은 기회: %d번)\n", playerName, chances);
            System.out.println("AI가 자동으로 룰렛을 돌립니다...");
        } else {
            System.out.printf("\n%s님의 차례입니다. (남은 기회: %d번)\n", playerName, chances);
            System.out.println("돌리세요![enter를 누르면 룰렛이 돌아갑니다.]");
            sc.nextLine();
        }
    }

    public static int[] spinRoulette() {
        int randomResult = r.nextInt(TOTAL_CASES);
        int points;


        if (randomResult < FAIL_BOUNDARY) {
            System.out.println("'꽝'입니다. 다시 한 번 도전하세요!(0점 추가)");
            points = FAIL_SCORE;

        } else if (randomResult < SMALL_WIN_BOUNDARY) {
            System.out.println("축하드립니다. '100원'입니다!(1점 추가)");
            points = SMALL_WIN_SCORE;

        } else if (randomResult < MEDIUM_WIN_BOUNDARY) {
            System.out.println("축하드려요, '1000원'에 당첨되셨습니다!(10점 추가)");
            points = MEDIUM_WIN_SCORE;

        } else if (randomResult < LARGE_WIN_BOUNDARY) {
            System.out.println("와! '10000원'에 당첨되셨어요!(100점 추가)");
            points = LARGE_WIN_SCORE;

        } else {
            System.out.println("당신이 진정한 승리자입니다. '50000원'에 당첨되셨어요.(500점 추가)");
            points = JACKPOT_SCORE;

        }

        return new int[]{points};
    }

    public static void checkBonusRoundWithFourthPlace(int totalPlayers, String[] playerNames,
                                                      int[] scores, int[] remainingChances) {
        int[] sortedIndexes = getSortedPlayerIndexes(scores);
        int firstScore = scores[sortedIndexes[0]];
        int fourthScore = scores[sortedIndexes[3]];
        int scoreDiff = firstScore - fourthScore;

        if(scoreDiff > 0 && scoreDiff % SCORE_GAP_UNIT == 0) {
            System.out.println("\n=== 보너스 라운드! ===");
            System.out.printf("1등과 4등의 점수차가 %d점의 배수가 되어 보너스 라운드를 시작합니다\n",
                    SCORE_GAP_UNIT);

            // 4등 이하 모든 플레이어에게 추가 기회 부여
            for(int i = 3; i < totalPlayers; i++) {
                int playerIndex = sortedIndexes[i];
                System.out.printf("%s님께 추가 기회를 드립니다.\n", playerNames[playerIndex]);
                remainingChances[playerIndex]++;
            }
        }
    }

    public static int[] getSortedPlayerIndexes(int[] scores) {
        int n = scores.length;
        int[] indexes = new int[n];
        for(int i = 0; i < n; i++) indexes[i] = i;

        // 버블 정렬로 인덱스 정렬 (점수 내림차순)
        for(int i = 0; i < n-1; i++) {
            for(int j = 0; j < n-i-1; j++) {
                if(scores[indexes[j]] < scores[indexes[j+1]]) {
                    int temp = indexes[j];
                    indexes[j] = indexes[j+1];
                    indexes[j+1] = temp;
                }
            }
        }
        return indexes;
    }

    public static void showRoundSummary(int totalPlayers, String[] playerNames,
                                        int[] scores, int[] remainingChances) {
        System.out.println("\n=== 현재 게임 상황 ===");
        int[] sortedIndexes = getSortedPlayerIndexes(scores);

        for(int i = 0; i < totalPlayers; i++) {
            int playerIndex = sortedIndexes[i];
            System.out.printf("%d등: %s (%d점, 남은 기회: %d번)\n",
                    i+1, playerNames[playerIndex], scores[playerIndex],
                    remainingChances[playerIndex]);
        }
        System.out.println("==================");
    }

    public static boolean isGameEnded(int[] remainingChances) {
        for (int chance : remainingChances) {
            if (chance > 0) return false;
        }
        return true;
    }

    public static void announceWinner(int totalPlayers, String[] playerNames, int[] scores) {
        System.out.println("\n======= 최종 게임 결과 =======");
        int[] sortedIndexes = getSortedPlayerIndexes(scores);

        for(int i = 0; i < totalPlayers; i++) {
            int playerIndex = sortedIndexes[i];
            System.out.printf("%d등: %s (%d점)\n",
                    i+1, playerNames[playerIndex], scores[playerIndex]);
        }

        System.out.printf("\n우승자는 %s님입니다! (최종 점수: %d점)\n",
                playerNames[sortedIndexes[0]], scores[sortedIndexes[0]]);
    }
}