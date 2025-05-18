package project.CustomizingRPGCharacter;

public class GameCharacter {

    // 캐릭터 클래스 타입
    public String WARRIOR = "전사";
    public String FIGHTER = "격투가";
    public String SPEARMAN = "창술가";
    public String SPIRITUALIST = "정령사";
    public String HEALER = "힐러";
    public String WIZARD = "마법사";
    public String ARCHER = "궁수";
    public String SPELLSWORD = "마검사";
    public String SPELLSPEAR = "마창사";

    public int selectedStatCount;

    // 기본 정보
    public String accountId;
    public String characterName;
    public String characterClass;

    // 레벨 시스템
    public int level;
    public int currentExp;
    public int expToNextLevel;
    public int baseExpRequired = 100; // 레벨업에 필요한 기본 경험치
    public double expIncreaseRate = 1.5; // 레벨당 필요 경험치 증가율
    public int levelUpStatPoints = 3; // 레벨업당 획득하는 스탯 포인트
    public int levelUpSkillPoints = 2; // 레벨업당 획득하는 스킬 포인트


    // 능력치 변수들
    public int strength;      // 힘
    public int intelligence;  // 지능
    public int hp;           // HP
    public int mp;           // MP
    public int agility;      // 민첩
    public int luck;         // 운
    public int speed;        // 스피드
    public int sword;        // 검술
    public int bow;          // 궁술
    public int magicSword;   // 마검술
    public int magicSpear;   // 마창술
    public int magic;        // 마법
    public int heal;         // 힐
    public int spirit;       // 정령

    public int availablePoints; //능력치포인트

    public SkillSet skillSet;

    // 생성자
    public GameCharacter(String accountId, String characterName, String characterClass) {
        this.accountId = accountId;
        this.characterName = characterName;
        this.characterClass = characterClass;
        this.availablePoints = 10; // 초기 포인트
        this.selectedStatCount = 0;

        // 레벨 시스템 초기화
        this.level = 1;
        this.currentExp = 0;
        this.expToNextLevel = baseExpRequired;
        this.availablePoints = 10; // 초기 스탯 포인트
        this.skillSet = new SkillSet();

        initializeSkills();
        // 모든 능력치 1로 초기화
        initializeStats();
        // 필수 능력치 설정
        setRequiredStats();
    }

    // 경험치 획득
    public void gainExp(int exp) {
        this.currentExp += exp;
        checkLevelUp();
    }

    // 레벨업 체크
    public void checkLevelUp() {
        while (currentExp >= expToNextLevel) {
            levelUp();
        }
    }

    // 레벨업
    public void levelUp() {
        level++;
        currentExp -= expToNextLevel;
        // 다음 레벨업에 필요한 경험치 계산
        double nextExp = baseExpRequired;
        for(int i = 1; i < level; i++) {
            nextExp = nextExp * expIncreaseRate;
        }
        expToNextLevel = (int)nextExp;

        // 레벨업 보상 지급
        availablePoints += levelUpStatPoints;
        skillSet.addSkillPoints(levelUpSkillPoints);

        System.out.println("\n=== LEVEL UP! ===");
        System.out.println("현재 레벨: " + level);
        System.out.println("획득한 스탯 포인트: " + levelUpStatPoints);
        System.out.println("획득한 스킬 포인트: " + levelUpSkillPoints);
        System.out.println("다음 레벨까지 필요한 경험치: " + expToNextLevel);
    }

    // 모든 능력치 1로 초기화하는 메소드
    public void initializeStats() {
        strength = 1;
        intelligence = 1;
        hp = 1;
        mp = 1;
        agility = 1;
        luck = 1;
        speed = 1;
        sword = 1;
        bow = 1;
        magicSword = 1;
        magicSpear = 1;
        magic = 1;
        heal = 1;
        spirit = 1;
    }

    //클래스별 초기 스킬 설정
    public void initializeSkills() {
        switch(characterClass) {
            case "전사":
                skillSet.addAvailableSkill(WarriorSkills.ShieldBash());
                break;
            case "격투가":
                skillSet.addAvailableSkill(FighterSkills.RapidPunch());
                skillSet.addAvailableSkill(FighterSkills.Roundhouse());
                break;
            case "창술가":
                skillSet.addAvailableSkill(SpearmanSkills.SpearThrust());
                skillSet.addAvailableSkill(SpearmanSkills.WhirlwindSpear());
                break;
            case "마법사":
                skillSet.addAvailableSkill(WizardSkills.Fireball());
                break;
            case "정령사":
                skillSet.addAvailableSkill(SpiritualistSkills.SummonSpirit());
                break;
            case "힐러":
                skillSet.addAvailableSkill(HealerSkills.Heal());
                skillSet.addAvailableSkill(HealerSkills.GroupHeal());
                break;
            case "궁수":
                skillSet.addAvailableSkill(ArcherSkills.PreciseShot());
                skillSet.addAvailableSkill(ArcherSkills.MultipleShot());
                break;
            case "마검사":
                skillSet.addAvailableSkill(SpellswordSkills.MagicSlash());
                skillSet.addAvailableSkill(SpellswordSkills.SpellBlade());
                break;
            case "마창사":
                skillSet.addAvailableSkill(SpellspearSkills.MagicThrust());
                skillSet.addAvailableSkill(SpellspearSkills.SpellSpear());
                break;
        }
    }

    public int getStatValue(String statName){
        switch(statName){
            case "힘": return strength;
            case "지능": return intelligence;
            case "HP": return hp;
            case "MP": return mp;
            case "민첩": return agility;
            case "운": return luck;
            case "스피드": return speed;
            case "검술": return sword;
            case "궁술": return bow;
            case "마검술": return magicSword;
            case "마창술": return magicSpear;
            case "마법": return magic;
            case "힐": return heal;
            case "정령": return spirit;
            case "격투": return strength;
            case "창술": return strength;
            default: return 0;
        }

    }

    //스킬 학습
    public boolean learnSkill(String skillName){
        return skillSet.learnSkill(skillName, this);
    }

    //스킬 업그레이드
    public boolean upgradeSkill(String skillName){
        return skillSet.upgradeSkill(skillName);
    }

    //스킬 포인트 추가
    public void addSkillPoints(int points){
        skillSet.addSkillPoints(points);
    }

    // 필수 능력치 설정 메소드
    public void setRequiredStats() {
        if (characterClass.equals(WARRIOR) ||
                characterClass.equals(FIGHTER) ||
                characterClass.equals(SPEARMAN)) {
            strength += 1;
            agility += 1;
        }
        else if (characterClass.equals(WIZARD) ||
                characterClass.equals(ARCHER) ||
                characterClass.equals(HEALER) ||
                characterClass.equals(SPIRITUALIST)) {
            intelligence += 1;
        }
        else if (characterClass.equals(SPELLSWORD) ||
                characterClass.equals(SPELLSPEAR)) {
            intelligence += 1;
            mp += 1;
            speed += 1;
        }
    }

    // 능력치 증가 메소드
    public boolean increaseStat(String statName) {
        if (availablePoints <= 0) {
            return false;
        }

        // 이미 최대 선택 개수에 도달했는지 확인
        if (selectedStatCount >= getMaxSelectableStats()) {
            System.out.println("더 이상 능력치를 선택할 수 없습니다.");
            return false;
        }

        int pointCost = calculatePointCost(statName);
        if (availablePoints >= pointCost) {
            if (updateStat(statName)) {
                availablePoints -= pointCost;
                selectedStatCount++;
                return true;
            }
        }
        return false;
    }

    // 포인트 비용 계산 메소드
    public int calculatePointCost(String statName) {
        // 특수 계열 스킬인 경우
        if (isSpecialSkill(statName)) {
            // 해당 클래스의 주력 스킬인지 확인
            if (isMainSkill(statName)) {
                return 1;
            } else {
                return 2; // 비주력 스킬은 2배의 포인트 필요
            }
        }
        return 1; // 일반 스탯은 1포인트
    }

    public void displayRecommendedStats() {
        System.out.println("\n=== 추천 능력치 가이드 ===");
        switch(characterClass) {
            case "전사":
                System.out.println("- 필수: 힘, 민첩");
                System.out.println("- 추천: HP, 검술");
                break;
            case "격투가":
                System.out.println("- 필수: 힘, 민첩");
                System.out.println("- 추천: HP, 격투");
                break;
            case "창술가":
                System.out.println("- 필수: 힘, 민첩");
                System.out.println("- 추천: HP, 창술");
                break;
            case "마법사":
                System.out.println("- 필수: 지능");
                System.out.println("- 추천: MP, 마법");
                break;
            case "궁수":
                System.out.println("- 필수: 지능");
                System.out.println("- 추천: 민첩, 궁술");
                break;
            case "힐러":
                System.out.println("- 필수: 지능");
                System.out.println("- 추천: MP, 힐");
                break;
            case "정령사":
                System.out.println("- 필수: 지능");
                System.out.println("- 추천: MP, 정령");
                break;
            case "마검사":
                System.out.println("- 필수: 지능, MP, 스피드");
                System.out.println("- 추천: 마검술");
                break;
            case "마창사":
                System.out.println("- 필수: 지능, MP, 스피드");
                System.out.println("- 추천: 마창술");
                break;
        }
        System.out.println("===============================");
    }

    // 특수 계열 스킬 확인 메소드 수정
    public boolean isSpecialSkill(String statName) {
        return statName.equals("검술") ||
                statName.equals("격투") ||
                statName.equals("창술") ||
                statName.equals("마검술") ||
                statName.equals("마창술") ||
                statName.equals("마법") ||
                statName.equals("힐") ||
                statName.equals("정령");
    }

    public int getMaxSelectableStats() {
        if (characterClass.equals(WARRIOR) ||
                characterClass.equals(FIGHTER) ||
                characterClass.equals(SPEARMAN)) {
            return 4;  // 필수 2개 + 선택 4개
        }
        else if (characterClass.equals(WIZARD) ||
                characterClass.equals(ARCHER) ||
                characterClass.equals(HEALER) ||
                characterClass.equals(SPIRITUALIST)) {
            return 5;  // 필수 1개 + 선택 5개
        }
        else {  // 마검사, 마창사
            return 2;  // 필수 3개 + 선택 2개 (총 5개)
        }
    }

    // 선택 가능한 남은 능력치 개수 반환
    public int getRemainingSelectableStats() {
        return getMaxSelectableStats() - selectedStatCount;
    }

    public boolean isMainSkill(String statName) {
        switch(characterClass) {
            case "전사":
                return statName.equals("검술");
            case "격투가":
                return statName.equals("격투");
            case "창술가":
                return statName.equals("창술");
            case "궁수":
                return statName.equals("궁술");
            case "마검사":
                return statName.equals("마검술");
            case "마창사":
                return statName.equals("마창술");
            case "마법사":
                return statName.equals("마법");
            case "힐러":
                return statName.equals("힐");
            case "정령사":
                return statName.equals("정령");
            default:
                return false;
        }
    }

    // 실제 스탯 증가를 처리하는 메소드
    public boolean updateStat(String statName) {
        switch(statName) {
            case "힘": strength++; break;
            case "지능": intelligence++; break;
            case "HP": hp++; break;
            case "MP": mp++; break;
            case "민첩": agility++; break;
            case "운": luck++; break;
            case "스피드": speed++; break;
            case "검술": sword++; break;
            case "궁술": bow++; break;
            case "마검술": magicSword++; break;
            case "마창술": magicSpear++; break;
            case "마법": magic++; break;
            case "힐": heal++; break;
            case "정령": spirit++; break;
            case "격투": strength++; break;
            case "창술": strength++; break;
            default: return false;
        }
        return true;
    }

    // 캐릭터 정보 출력 메소드
    public void displayCharacterInfo() {
        System.out.println("캐릭터 이름: " + characterName);
        System.out.println("클래스: " + characterClass);
        System.out.println("레벨: " + level);
        System.out.println("경험치: " + currentExp + " / " + expToNextLevel);
        System.out.println("남은 스탯 포인트: " + availablePoints);
        System.out.println("남은 스킬 포인트: " + skillSet.skillPoints);
        System.out.println("\n===== 능력치 =====");
        System.out.println("힘: " + strength);
        System.out.println("지능: " + intelligence);
        System.out.println("HP: " + hp);
        System.out.println("MP: " + mp);
        System.out.println("민첩: " + agility);
        System.out.println("운: " + luck);
        System.out.println("스피드: " + speed);
        System.out.println("검술: " + sword);
        System.out.println("궁술: " + bow);
        System.out.println("마검술: " + magicSword);
        System.out.println("마창술: " + magicSpear);
        System.out.println("마법: " + magic);
        System.out.println("힐: " + heal);
        System.out.println("정령: " + spirit);
    }

    //스킬 정보 출력
    public void displaySkillInfo(){
        System.out.println("\n=== 스킬 정보 ===");
        System.out.println("사용 가능한 스킬 포인트: " + skillSet.skillPoints);

        System.out.println("\n학습 가능한 스킬:");
        for(int i = 0; i < skillSet.availableSkillCount; i++){
            Skill skill = skillSet.availableSkills[i];
            System.out.println("\n스킬 " + (i+1) + ":");
            System.out.println("- 이름: " + skill.skillName);
            System.out.println("- 설명: " + skill.description);
            System.out.println("- 현재 레벨: " + skill.skillLevel + "/" + skill.maxLevel);
            System.out.println("- 쿨다운: " + skill.cooldown + "초");

            System.out.println("- 필요 능력치:");
            for(int j = 0; j < skill.requiredStatsCount; j++) {
                System.out.println("  * " + skill.requiredStatNames[j] + ": "
                        + skill.requiredStatValues[j]);
            }
        }

        if(skillSet.learnedSkillCount > 0) {
            System.out.println("\n학습한 스킬:");
            for(int i = 0; i < skillSet.learnedSkillCount; i++){
                Skill skill = skillSet.learnedSkills[i];
                System.out.println("\n스킬 " + (i+1) + ":");
                System.out.println("- 이름: " + skill.skillName);
                System.out.println("- 설명: " + skill.description);
                System.out.println("- 현재 레벨: " + skill.skillLevel + "/" + skill.maxLevel);
                System.out.println("- 쿨다운: " + skill.cooldown + "초");

                if(skill.skillLevel < skill.maxLevel) {
                    System.out.println("- 다음 레벨 필요 포인트: " + skill.getUpgradeCost());
                }
            }
        } else {
            System.out.println("\n아직 학습한 스킬이 없습니다.");
        }
    }

    public boolean changeClass(String newClass) {
        // 현재 클래스와 같은 클래스로는 변경 불가
        if(characterClass.equals(newClass)) {
            System.out.println("이미 해당 클래스입니다.");
            return false;
        }

        // 기존 스탯 초기화
        initializeStats();

        // 스킬셋 초기화
        skillSet = new SkillSet();

        // 클래스 변경
        characterClass = newClass;

        // 필수 스탯 재설정
        setRequiredStats();

        // 새 클래스의 스킬 초기화
        initializeSkills();

        System.out.println("\n클래스가 " + newClass + "로 변경되었습니다!");
        System.out.println("모든 스탯과 스킬이 초기화되었습니다.");
        displayRecommendedStats();

        return true;
    }


}



