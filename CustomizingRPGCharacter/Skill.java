package project.CustomizingRPGCharacter;

public class Skill {
    public String skillName;
    public String description;
    public int skillLevel;
    public int maxLevel;
    public int cooldown;
    public boolean isUsableInCombat;

    //필요 능력치
    public String[] requiredStatNames;
    public int[] requiredStatValues;
    public int requiredStatsCount;


    public Skill(String skillName, String description, int skillLevel, int maxLevel, int cooldown, boolean isUsableInCombat){
        this.skillName = skillName;
        this.description = description;
        this.skillLevel = skillLevel;
        this.maxLevel = maxLevel;
        this.cooldown = cooldown;
        this.isUsableInCombat = isUsableInCombat;

        this.requiredStatNames = new String[3];
        this.requiredStatValues = new int [3];
        this.requiredStatsCount = 0;

    }

    //스킬 업그레이드
    public boolean upgrade(int availablePoints){
        if(skillLevel < maxLevel && availablePoints >=getUpgradeCost()){
            skillLevel++;
            return true;
        }
        return false;
    }

    //업그레이드에 필요한 포인트 계산
    public int getUpgradeCost(){

        if (skillLevel >= 3 && skillLevel < 5) return 2;
        if (skillLevel >= 5) return 3;
        return 1;
    }

    //필요 능력치 추가
    public void addRequiredStat(String statName, int value){
        if(requiredStatsCount<3){
            requiredStatNames[requiredStatsCount] = statName;
            requiredStatValues[requiredStatsCount] = value;
            requiredStatsCount++;
        }
    }

    //캐릭터에 따른 스킬 사용 여부 확인
    public boolean canUseSkill(GameCharacter character) {
     for(int i=0; i< requiredStatsCount; i++){
         String statname = requiredStatNames[i];
         int requiredValue = requiredStatValues[i];
         int characterValue = character.getStatValue(statname);

         if(characterValue < requiredValue){
             return false;
         }
     }
     return true;
    }

}
