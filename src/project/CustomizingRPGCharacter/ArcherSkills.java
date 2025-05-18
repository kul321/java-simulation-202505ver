package project.CustomizingRPGCharacter;

public class ArcherSkills {
    public static Skill PreciseShot() {
        Skill precise = new Skill(
                "정밀 사격",
                "높은 정확도로 적을 공격합니다.",
                1,
                8,
                2,
                true
        );
        precise.addRequiredStat("지능", 8);
        precise.addRequiredStat("궁술", 5);

        return precise;
    }

    public static Skill MultipleShot() {
        Skill multiple = new Skill(
                "다중 사격",
                "여러 발의 화살을 동시에 발사합니다.",
                1,
                10,
                4,
                true
        );
        multiple.addRequiredStat("지능", 10);
        multiple.addRequiredStat("궁술", 8);

        return multiple;
    }
}