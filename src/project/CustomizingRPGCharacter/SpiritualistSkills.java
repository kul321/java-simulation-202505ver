package project.CustomizingRPGCharacter;

public class SpiritualistSkills {
    public static Skill SummonSpirit() {
        Skill summon = new Skill(
                "정령 소환",
                "전투를 도와줄 정령을 소환합니다.",
                1,
                8,
                5,
                true
        );
        summon.addRequiredStat("지능", 10);
        summon.addRequiredStat("정령", 5);

        return summon;
    }
}
