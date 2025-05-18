package project.CustomizingRPGCharacter;

public class SpellspearSkills {
    public static Skill MagicThrust() {
        Skill magicThrust = new Skill(
                "마창 찌르기",
                "마력이 깃든 창으로 적을 찌릅니다.",
                1,
                8,
                3,
                true
        );
        magicThrust.addRequiredStat("지능", 8);
        magicThrust.addRequiredStat("마창술", 5);

        return magicThrust;
    }

    public static Skill SpellSpear() {
        Skill spellSpear = new Skill(
                "마법창",
                "창에 마력을 깃들여 공격력을 높입니다.",
                1,
                10,
                5,
                true
        );
        spellSpear.addRequiredStat("지능", 10);
        spellSpear.addRequiredStat("마창술", 8);
        spellSpear.addRequiredStat("MP", 10);

        return spellSpear;
    }
}
