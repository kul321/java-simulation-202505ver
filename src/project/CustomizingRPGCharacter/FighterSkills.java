package project.CustomizingRPGCharacter;

public class FighterSkills {
    public static Skill RapidPunch() {
        Skill rapidPunch = new Skill(
                "연속 펀치",
                "빠른 속도로 연속 타격을 가합니다.",
                1,
                8,
                2,
                true
        );
        rapidPunch.addRequiredStat("힘", 8);
        rapidPunch.addRequiredStat("민첩", 8);

        return rapidPunch;
    }

    public static Skill Roundhouse() {
        Skill roundhouse = new Skill(
                "회전 킥",
                "강력한 회전 킥으로 주변 적들을 공격합니다.",
                1,
                10,
                4,
                true
        );
        roundhouse.addRequiredStat("힘", 10);
        roundhouse.addRequiredStat("민첩", 6);

        return roundhouse;
    }
}