import java.util.Scanner;

public class test7 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //2번 게임 데이터
        int uses_year =0;
        int goal_year = 0;
        String dream = "";

        //3번 게임 데이터
        boolean pvp = false;
        String userClass = "";


        A_game a = new A_game();
        B_game b = new B_game(uses_year,goal_year,dream);
        C_game c = new C_game(pvp,userClass);
        D_game d = new D_game();
        E_game e = new E_game();


        System.out.println("1. "+a.gameType);
        System.out.println("2. "+b.gameType);
        System.out.println("3. "+c.gameType);
        System.out.println("4. "+d.gameType);
        System.out.println("5. "+e.gameType);


        System.out.println("2번 게임에 사용될 사용자의 나이를 입력하세요:");
        uses_year =  sc.nextInt();
        System.out.println("2번 게임에 사용될 딸의 목표 나이를 입력하세요:");
        goal_year = sc.nextInt();
        System.out.println("2번 게임에 사용될 딸의 목표 직업을 입력하세요:");
        dream = sc.next();
        b = new B_game(uses_year,goal_year,dream);

        System.out.println("3번 게임 pvp를 가능하게 하려면 1을 누르세요:");
        int c_pvpchoice = sc.nextInt();
        pvp = (c_pvpchoice == 1);
        System.out.println("3번 게임 유저 클래스명을 입력하세요: ");
        userClass = sc.next();
        c = new C_game(pvp,userClass);

        System.out.println("자세한 정보를 확인할 게임을 선택하세요");
        int gameChoice = sc.nextInt();

        switch (gameChoice) {
            case 1:
                showGameInfo(
                        a.gameType,
                        a.dev,
                        a.desctiprion(),
                        "속도: " + a.speed,
                        "충돌 내구도: " + a.crash,
                        "점프력: " + a.jump,
                        "차량 브랜드: " + a.carModelBrand,
                        "레이서: " + a.racer
                );
                break;

            case 2:
                showGameInfo(
                        b.gameType,
                        b.dev,
                        b.desctiprion(),
                        "딸 나이: " + b.daughter_years,
                        "부모 나이: " + b.parents_years,
                        "엔딩 나이: " + b.ending_year,
                        "딸의 목표: " + b.duaghters_goal,
                        "서포터 이름: " + b.supporters_name
                );
                break;

            case 3:
                showGameInfo(
                        c.gameType,
                        c.dev,
                        c.desctiprion(),
                        "레벨: " + c.level,
                        "최대 레벨: " + c.max_level,
                        "PVP 가능 여부: " + c.pvp,
                        "캐릭터 클래스: " + c.charClass,
                        "메인 타이틀: " + c.mainTitle
                );
                break;

            case 4:
                showGameInfo(
                        d.gameType,
                        d.dev,
                        d.desctiprion(),
                        "스테이지: " + d.stage,
                        "점수: " + d.score,
                        "음악: " + d.music_title,
                        "난이도: " + d.difficulty,
                        "게임모드: " + d.gameMode
                );
                break;

            case 5:
                showGameInfo(
                        e.gameType,
                        e.dev,
                        e.desctiprion(),
                        "BPM: " + e.bpm,
                        "최대 콤보: " + e.max_combo,
                        "현재 콤보: " + e.combo,
                        "노래 제목: " + e.songTitle,
                        "난이도: " + e.difficulty
                );
                break;

            default:
                System.out.println("잘못된 선택입니다. 1-5 사이의 숫자를 입력해주세요.");
                break;
        }


    }

    static void showGameInfo(String gameType, String dev, String description, String... stats) {
        System.out.println("\n=== 게임 정보 ===");
        System.out.println("장르: " + gameType);
        System.out.println("개발자: " + dev);
        System.out.println("설명: " + description);
        System.out.println("능력치:");
        for (String stat : stats) {
            System.out.println("- " + stat);
        }
        System.out.println("================\n");
    }
}
