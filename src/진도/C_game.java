public class C_game {
    //게임의 종류
    String gameType = "RPG";

    //게임 설명
    String desctiprion(){
        return "중세 판타지 오픈월드 RPG게임입니다.";
    }

    //게임 개발자
    String dev = "Hwang";

    //능력치
    int level = 1;
    int max_level = 99;
    boolean pvp = true;
    String charClass = "마법사";
    String mainTitle = "마법사의 돌";

    C_game(boolean pvp, String userClass){
    this.pvp = pvp;
    this.charClass = userClass;
    }
}
