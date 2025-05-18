package project.CustomizingRPGCharacter;

public class SkillSet {
    public Skill[] availableSkills; //학습 가능 스킬들
    public Skill[] learnedSkills; // 학습한 스킬들
    public int availableSkillCount; // 학습 가능한 스킬 개수
    public int learnedSkillCount; // 학습한 스킬 개수
    public int skillPoints; // 보유 스킬 포인트

    public SkillSet(){
        this.availableSkills = new Skill[10];
        this.learnedSkills = new Skill[10];
        this.availableSkillCount = 0;
        this.learnedSkillCount = 0;
        this.skillPoints = 0;

    }

    //사용 가능한 스킬 추가
    public void addAvailableSkill(Skill skill){
        if(availableSkillCount < 10){
            availableSkills[availableSkillCount] = skill;
            availableSkillCount++;
        }
    }

    //스킬 학습
    public boolean learnSkill(String skillName, GameCharacter character){
        // 이미 배운 스킬인지 확인
        for(int i = 0; i < learnedSkillCount; i++){
            if(learnedSkills[i].skillName.equals(skillName)){
                System.out.println("이미 학습한 스킬입니다.");
                return false;
            }
        }

        // 학습 가능한 스킬인지 찾기
        Skill skillToLearn = null;
        for(int i = 0; i < availableSkillCount; i++){
            if(availableSkills[i].skillName.equals(skillName)){
                skillToLearn = availableSkills[i];
                break;
            }
        }

        if(skillToLearn == null){
            System.out.println("학습할 수 없는 스킬입니다.");
            return false;
        }

        // 필요한 능력치 체크
        if(!skillToLearn.canUseSkill(character)){
            System.out.println("필요한 능력치가 부족합니다.");
            return false;
        }

        // 스킬 학습 진행
        learnedSkills[learnedSkillCount] = skillToLearn;
        learnedSkillCount++;
        System.out.println(skillName + " 스킬을 학습했습니다!");
        return true;
    }

    //스킬 포인트 추가
    public void addSkillPoints(int points){
        this.skillPoints += points;
    }

    //스킬 업그레이드
    public boolean upgradeSkill(String skillName){
        for( int i = 0; i<learnedSkillCount; i++){
            Skill skill = learnedSkills[i];
            if(skill.skillName.equals(skillName)){
                if(skill.upgrade(skillPoints)){
                    skillPoints -= skill.getUpgradeCost();
                    return true;
                }
            }
        }
        System.out.println("스킬을 업그레이드 할 수 없습니다.");
        return false;
    }

}
