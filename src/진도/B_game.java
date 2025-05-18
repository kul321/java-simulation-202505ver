public class B_game {
    //게임의 종류
    String gameType = "육성 시뮬레이션";

    //게임 설명
    String desctiprion(){
        return "딸 키우기 시리즈 Fake작입니다.";
    }

    //게임 개발자
    String dev = "Olive";

    //능력치
    int daughter_years = 10;
    int parents_years = 35;
    int ending_year = 18;
    String duaghters_goal = "공주";
    String supporters_name = "큐브";

    B_game(int user, int goal, String dream){
        this.parents_years = user;
        this.ending_year = goal;
        this.duaghters_goal = dream;
    }


}
