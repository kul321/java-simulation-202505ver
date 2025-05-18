package project.CustomizingRPGCharacter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Account account = null;

        while(true) {
            while (account == null) {
                if (Account.accountCount > 0) {  // 계정이 하나라도 있으면
                    System.out.println("1. 새 계정 만들기");
                    System.out.println("2. 로그인");
                    System.out.print("선택: ");
                    int choice = sc.nextInt();
                    sc.nextLine();

                    System.out.print("아이디: ");
                    String id = sc.nextLine();
                    System.out.print("비밀번호: ");
                    String password = sc.nextLine();

                    if (choice == 1) {
                        if (Account.register(id, password)) {
                            account = Account.login(id, password);
                        }
                    } else if (choice == 2) {
                        account = Account.login(id, password);
                    }
                } else {  // 계정이 하나도 없으면 자동으로 회원가입 진행
                    System.out.println("회원가입");
                    System.out.print("아이디와 비밀번호를 입력해주세요\n");

                    System.out.print("아이디: ");
                    String id = sc.nextLine();
                    System.out.print("비밀번호: ");
                    String password = sc.nextLine();

                    if (Account.register(id, password)) {
                        account = Account.login(id, password);
                    }
                }
            }

            //-----------로그인 후
            while (true) {
                System.out.println("\n1. 캐릭터 생성");
                System.out.println("2. 캐릭터 목록 보기");
                System.out.println("3. 능력치 찍기");
                System.out.println("4. 스킬 관리");
                System.out.println("5. 클래스 변경"); // 새로 추가
                System.out.println("6. 경험치 획득 (테스트용)");
                System.out.println("7. 로그아웃");
                System.out.print("선택: ");
                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    // 캐릭터 생성
                    System.out.print("캐릭터 이름: ");
                    String characterName = sc.nextLine();

                    System.out.println("\n사용 가능한 클래스:");
                    System.out.println("1. 전사   2. 격투가   3. 창술가");
                    System.out.println("4. 마법사  5. 궁수    6. 힐러");
                    System.out.println("7. 정령사  8. 마검사   9. 마창사");
                    System.out.print("클래스 선택(번호): ");
                    int classChoice = sc.nextInt();

                    if (classChoice < 1 || classChoice > 9) {
                        System.out.println("잘못된 클래스 선택입니다.");
                        continue;
                    }

                    String[] classes = {"전사", "격투가", "창술가", "마법사", "궁수", "힐러", "정령사", "마검사", "마창사"};
                    String characterClass = classes[classChoice - 1];

                    if (account.createCharacter(characterName, characterClass)) {
                        System.out.println("캐릭터가 생성되었습니다!");
                    } else {
                        System.out.println("더 이상 캐릭터를 생성할 수 없습니다.");
                    }
                } else if (choice == 2) {
                    // 캐릭터 목록 보기
                    if (account.characterCount == 0) {
                        System.out.println("생성된 캐릭터가 없습니다.");
                        continue;
                    }
                    account.displayAllCharacters();
                } else if (choice == 3) {
                    // 능력치 찍기
                    if (account.characterCount == 0) {
                        System.out.println("생성된 캐릭터가 없습니다.");
                        continue;
                    }

                    System.out.println("\n=== 능력치 찍기 ===");

                    // 캐릭터 선택
                    System.out.println("\n캐릭터 선택:");
                    for (int i = 0; i < account.characterCount; i++) {
                        System.out.println((i + 1) + ". " + account.characters[i].characterName
                                + " (" + account.characters[i].characterClass + ")");
                    }
                    System.out.print("선택: ");
                    int charChoice = sc.nextInt();
                    sc.nextLine();

                    if (charChoice < 1 || charChoice > account.characterCount) {
                        System.out.println("잘못된 선택입니다.");
                        continue;
                    }

                    GameCharacter selectedChar = account.characters[charChoice - 1];

                    System.out.println("\n=== 현재 캐릭터 상태 ===");
                    selectedChar.displayCharacterInfo();

                    if (selectedChar.getRemainingSelectableStats() <= 0) {
                        System.out.println("더 이상 능력치를 선택할 수 없습니다!");
                        continue;
                    }

                    // 추천 능력치 표시
                    selectedChar.displayRecommendedStats();

                    // 사용 가능한 포인트와 남은 선택 가능 개수 표시
                    System.out.println("\n현재 상태:");
                    System.out.println("- 사용 가능한 포인트: " + selectedChar.availablePoints);
                    System.out.println("- 남은 선택 가능 개수: " + selectedChar.getRemainingSelectableStats());
                    System.out.println("(일반 능력치: 1포인트, 비주력 특수 능력치: 2포인트 소모)");
                    System.out.println("===============================");

                    // 능력치 선택
                    System.out.println("\n능력치 선택:");
                    System.out.println("1. 힘     2. 지능    3. HP      4. MP");
                    System.out.println("5. 민첩   6. 운      7. 스피드");
                    System.out.println("8. 검술   9. 궁술    10. 마검술");
                    System.out.println("11. 마창술 12. 마법   13. 힐     14. 정령");
                    System.out.println("15. 격투  16. 창술");
                    System.out.println("0. 취소");
                    System.out.print("선택: ");
                    int statChoice = sc.nextInt();

                    if (statChoice == 0) {
                        System.out.println("능력치 선택을 취소했습니다.");
                        continue;
                    }

                    if (statChoice < 1 || statChoice > 14) {
                        System.out.println("잘못된 능력치 선택입니다.");
                        continue;
                    }

                    String statName = "";
                    switch (statChoice) {
                        case 1:
                            statName = "힘";
                            break;
                        case 2:
                            statName = "지능";
                            break;
                        case 3:
                            statName = "HP";
                            break;
                        case 4:
                            statName = "MP";
                            break;
                        case 5:
                            statName = "민첩";
                            break;
                        case 6:
                            statName = "운";
                            break;
                        case 7:
                            statName = "스피드";
                            break;
                        case 8:
                            statName = "검술";
                            break;
                        case 9:
                            statName = "궁술";
                            break;
                        case 10:
                            statName = "마검술";
                            break;
                        case 11:
                            statName = "마창술";
                            break;
                        case 12:
                            statName = "마법";
                            break;
                        case 13:
                            statName = "힐";
                            break;
                        case 14:
                            statName = "정령";
                            break;
                    }

                    if (selectedChar.increaseStat(statName)) {
                        System.out.println("\n능력치가 증가되었습니다!");
                        selectedChar.displayCharacterInfo();
                    } else {
                        System.out.println("\n포인트가 부족하거나 잘못된 선택입니다.");
                    }
                } else if (choice == 4) {
                    // 스킬 관리 메뉴
                    if (account.characterCount == 0) {
                        System.out.println("생성된 캐릭터가 없습니다.");
                        continue;
                    }

                    // 캐릭터 선택
                    System.out.println("\n캐릭터 선택:");
                    for (int i = 0; i < account.characterCount; i++) {
                        System.out.println((i + 1) + ". " + account.characters[i].characterName
                                + " (" + account.characters[i].characterClass + ")");
                    }
                    System.out.print("선택: ");
                    int charChoice = sc.nextInt();
                    sc.nextLine();

                    if (charChoice < 1 || charChoice > account.characterCount) {
                        System.out.println("잘못된 선택입니다.");
                        continue;
                    }

                    GameCharacter selectedChar = account.characters[charChoice - 1];

                    while (true) {
                        System.out.println("\n=== 스킬 관리 ===");
                        System.out.println("1. 새 스킬 학습");
                        System.out.println("2. 스킬 업그레이드");
                        System.out.println("3. 스킬 정보 보기");
                        System.out.println("4. 이전 메뉴로");
                        System.out.print("선택: ");
                        int skillChoice = sc.nextInt();
                        sc.nextLine();

                        if (skillChoice == 1) {
                            // 스킬 학습
                            selectedChar.displaySkillInfo();
                            System.out.print("\n학습할 스킬 번호 입력 (0: 취소): ");
                            int learnChoice = sc.nextInt();
                            sc.nextLine();

                            if (learnChoice == 0) {
                                System.out.println("스킬 학습을 취소했습니다.");
                                continue;
                            }

                            if (learnChoice > 0 && learnChoice <= selectedChar.skillSet.availableSkillCount) {
                                Skill skillToLearn = selectedChar.skillSet.availableSkills[learnChoice - 1];
                                if (selectedChar.learnSkill(skillToLearn.skillName)) {
                                    System.out.println(skillToLearn.skillName + " 스킬을 학습했습니다!");
                                }
                            }
                        } else if (skillChoice == 2) {
                            // 스킬 업그레이드
                            if (selectedChar.skillSet.learnedSkillCount == 0) {
                                System.out.println("업그레이드할 스킬이 없습니다.");
                                continue;
                            }

                            System.out.println("\n=== 학습한 스킬 목록 ===");
                            for (int i = 0; i < selectedChar.skillSet.learnedSkillCount; i++) {
                                Skill skill = selectedChar.skillSet.learnedSkills[i];
                                System.out.println((i + 1) + ". " + skill.skillName
                                        + " (레벨: " + skill.skillLevel + "/" + skill.maxLevel + ")");
                            }

                            System.out.print("\n업그레이드할 스킬 번호 입력 (0: 취소): ");
                            int upgradeChoice = sc.nextInt();
                            sc.nextLine();

                            if (upgradeChoice == 0) {
                                System.out.println("스킬 업그레이드를 취소했습니다.");
                                continue;
                            }

                            if (upgradeChoice > 0 && upgradeChoice <= selectedChar.skillSet.learnedSkillCount) {
                                Skill skillToUpgrade = selectedChar.skillSet.learnedSkills[upgradeChoice - 1];
                                if (selectedChar.upgradeSkill(skillToUpgrade.skillName)) {
                                    System.out.println(skillToUpgrade.skillName + " 스킬을 업그레이드했습니다!");
                                } else {
                                    System.out.println("스킬 포인트가 부족하거나 최대 레벨입니다.");
                                }
                            }
                        } else if (skillChoice == 3) {
                            selectedChar.displaySkillInfo();
                        } else if (skillChoice == 4) {
                            break;
                        }
                    }
                } else if (choice == 5) {
                    if (account.characterCount == 0) {
                        System.out.println("생성된 캐릭터가 없습니다.");
                        continue;
                    }

                    // 캐릭터 선택
                    System.out.println("\n클래스를 변경할 캐릭터 선택:");
                    for (int i = 0; i < account.characterCount; i++) {
                        System.out.println((i + 1) + ". " + account.characters[i].characterName
                                + " (" + account.characters[i].characterClass + ")");
                    }
                    System.out.print("선택: ");
                    int charChoice = sc.nextInt();
                    sc.nextLine();

                    if (charChoice < 1 || charChoice > account.characterCount) {
                        System.out.println("잘못된 선택입니다.");
                        continue;
                    }

                    GameCharacter selectedChar = account.characters[charChoice - 1];

                    // 클래스 선택 메뉴 표시
                    System.out.println("\n변경할 클래스 선택:");
                    System.out.println("1. 전사   2. 격투가   3. 창술가");
                    System.out.println("4. 마법사  5. 궁수    6. 힐러");
                    System.out.println("7. 정령사  8. 마검사   9. 마창사");
                    System.out.print("클래스 선택(번호): ");
                    int classChoice = sc.nextInt();

                    String[] classes = {"전사", "격투가", "창술가", "마법사", "궁수", "힐러", "정령사", "마검사", "마창사"};
                    String newClass = classes[classChoice - 1];


                    if (selectedChar.changeClass(newClass)) {
                        // 변경된 캐릭터 정보 출력
                        selectedChar.displayCharacterInfo();

                    }
                } else if (choice == 6) {
                    // 경험치 획득 테스트
                    if (account.characterCount == 0) {
                        System.out.println("생성된 캐릭터가 없습니다.");
                        continue;
                    }

                    // 캐릭터 선택
                    System.out.println("\n캐릭터 선택:");
                    for (int i = 0; i < account.characterCount; i++) {
                        System.out.println((i + 1) + ". " + account.characters[i].characterName
                                + " (" + account.characters[i].characterClass + ")");
                    }
                    System.out.print("선택: ");
                    int charChoice = sc.nextInt();
                    sc.nextLine();

                    if (charChoice < 1 || charChoice > account.characterCount) {
                        System.out.println("잘못된 선택입니다.");
                        continue;
                    }

                    GameCharacter selectedChar = account.characters[charChoice - 1];

                    System.out.print("획득할 경험치 입력: ");
                    int expGain = sc.nextInt();
                    sc.nextLine();

                    if (expGain > 0) {
                        selectedChar.gainExp(expGain);
                        selectedChar.displayCharacterInfo();
                    } else {
                        System.out.println("올바르지 않은 값입니다.");
                    }
                } else if (choice == 7) {
                    System.out.println("로그아웃 되었습니다.");
                    account = null;
                    break;
                } else {
                    System.out.println("잘못된 선택입니다.");
                }
            }
        }
    }
}