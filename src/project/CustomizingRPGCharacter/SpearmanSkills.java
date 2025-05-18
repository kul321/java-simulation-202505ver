package project.CustomizingRPGCharacter;

public class SpearmanSkills {
    public static Skill SpearThrust() {
        Skill spearThrust = new Skill(
                "창 찌르기",
                "창으로 적을 강하게 찌릅니다.",
                1,
                8,
                2,
                true
        );
        spearThrust.addRequiredStat("힘", 8);
        spearThrust.addRequiredStat("민첩", 5);

        return spearThrust;
    }

    public static Skill WhirlwindSpear() {
        Skill whirlwind = new Skill(
                "창 휘두르기",
                "창을 회전시켜 주변 적들을 공격합니다.",
                1,
                10,
                4,
                true
        );
        whirlwind.addRequiredStat("힘", 10);
        whirlwind.addRequiredStat("민첩", 7);

        return whirlwind;
    }
}