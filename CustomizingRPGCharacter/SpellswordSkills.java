package project.CustomizingRPGCharacter;

public class SpellswordSkills {
    public static Skill MagicSlash() {
        Skill magicSlash = new Skill(
                "마검 베기",
                "마력이 깃든 검으로 적을 베어냅니다.",
                1,
                8,
                3,
                true
        );
        magicSlash.addRequiredStat("지능", 8);
        magicSlash.addRequiredStat("마검술", 5);

        return magicSlash;
    }

    public static Skill SpellBlade() {
        Skill spellBlade = new Skill(
                "마법검",
                "검에 마력을 깃들여 공격력을 높입니다.",
                1,
                10,
                5,
                true
        );
        spellBlade.addRequiredStat("지능", 10);
        spellBlade.addRequiredStat("마검술", 8);
        spellBlade.addRequiredStat("MP", 10);

        return spellBlade;
    }
}
