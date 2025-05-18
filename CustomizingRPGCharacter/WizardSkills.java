package project.CustomizingRPGCharacter;

public class WizardSkills {
    public static Skill Fireball(){
        Skill fireball = new Skill(
                "파이어볼",
                "화염구를 발사하여 데미지를 입힙니다.",
                1,
                8,
                3,
                true
        );
        fireball.addRequiredStat("지능",10);
        fireball.addRequiredStat("MP", 5);

        return  fireball;
    }
}
