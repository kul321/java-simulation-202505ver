package project.CustomizingRPGCharacter;

public class WarriorSkills {
    public static Skill ShieldBash(){
        Skill shieldBash = new Skill(
          "방패치기",
          "방패로 적을 강타하여 스턴을 걸고 데미지를 줍니다.",
                1,
                10,
                3,
                true
        );
        shieldBash.addRequiredStat("힘",10);
        shieldBash.addRequiredStat("민첩",5);

        return shieldBash;
    }
}
