package project.CustomizingRPGCharacter;

public class HealerSkills {
    public static Skill Heal() {
        Skill heal = new Skill(
                "치유",
                "아군의 체력을 회복시킵니다.",
                1,
                8,
                3,
                true
        );
        heal.addRequiredStat("지능", 8);
        heal.addRequiredStat("힐", 5);

        return heal;
    }

    public static Skill GroupHeal() {
        Skill groupHeal = new Skill(
                "광역 치유",
                "주변 아군들의 체력을 회복시킵니다.",
                1,
                10,
                6,
                true
        );
        groupHeal.addRequiredStat("지능", 12);
        groupHeal.addRequiredStat("힐", 8);

        return groupHeal;
    }
}
