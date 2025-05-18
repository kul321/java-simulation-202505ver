package project.Energy;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("게임 모드를 선택하세요:");
        System.out.println("1. 짧은 게임 (10턴)");
        System.out.println("2. 일반 게임 (20턴)");
        System.out.println("3. 긴 게임 (30턴)");
        System.out.println("4. 커스텀 (턴 수 직접 입력)");
        int gameModeChoice = sc.nextInt();
        int maxTurns;

        switch (gameModeChoice) {
            case 1:
                maxTurns = 10;
                break;
            case 2:
                maxTurns = 20;
                break;
            case 3:
                maxTurns = 30;
                break;
            case 4:
                System.out.println("턴 수를 입력하세요:");
                maxTurns = sc.nextInt();
                break;
            default:
                maxTurns = 20;
        }


        //플레이어 수 입력 받기
        System.out.println("플레이어 수를 입력하세요:");
        int playerCount = sc.nextInt();


        //전체 시스템 초기화
        EnergySystem system = new EnergySystem(playerCount);
        Player[] players = new Player[playerCount];

        // 초기 총 에너지량을 계산
        double totalInitialEnergy = 0;

        for (int i = 0; i < playerCount; i++) {
            System.out.println("플레이어 이름을 입력하세요:");
            String playerName = sc.next();
            System.out.println((i + 1) + "번 구역 설정");
            System.out.println("초기에너지량을 입력하세요:");
            int initialEnergy = sc.nextInt();
            System.out.println("최대용량을 입력하세요.");
            int maxEnergyCapacity = sc.nextInt();
            System.out.println("최소에너지량을 입력하세요.");
            int minEnergy = sc.nextInt();
            System.out.println("최대전송량을 입력하세요.");
            int maxEnergyTrnasfer = sc.nextInt();
            System.out.println("인구 수를 입력하세요:");
            int population = sc.nextInt();

            Zone zone = new Zone("Zone" + (i + 1), initialEnergy, maxEnergyCapacity, minEnergy, maxEnergyTrnasfer, population);
            system.zones[i] = zone;
            totalInitialEnergy += initialEnergy;

            players[i] = new Player(playerName, zone);

            system.energyPool.updateUnstoredEnergy(system.zones);

        }



        //턴 매니저
        TurnManager turnManager = new TurnManager(maxTurns);

        //게임 진행
        while (!turnManager.isGameOver()) {
            System.out.println("\n=== " + turnManager.currentTurn + "턴 시작 ===");
            System.out.println("남은 턴 수: " + turnManager.getRemainingTurns());

            // 모든 플레이어의 행동을 먼저 처리
            for (int i = 0; i < players.length; i++) {
                System.out.println("=== " + players[i].name + "의 차례 ===");
                printZoneStatus(system);

                playerTurn(system, players[i], players, turnManager);
            }

            // 모든 플레이어의 행동이 끝난 후 턴 종료 처리
            system.onTurnEnd();  // 에너지 풀 처리
            turnManager.nextTurn(system.zones);
        }

        // 게임 종료 후 결과 출력
        System.out.println("\n=== 게임 종료 ===");

        // 승자 찾기 (가장 낮은 누적 페널티를 가진 플레이어)
        Player winner = players[0];

        // 모든 플레이어들을 차례대로 확인
        for(int i = 0; i < players.length; i++) {
            Player player = players[i];
            // 현재 플레이어의 페널티가 승자의 페널티보다 작으면 현재 플레이어를 새로운 승자로 설정
            if(player.zone.totalPenalty < winner.zone.totalPenalty) {
                winner = player;
            }
        }

        // 모든 플레이어들의 최종 상태를 출력
        for(int i = 0; i < players.length; i++) {
            // 현재 확인할 플레이어
            Player player = players[i];

            // 플레이어의 기본 정보 출력
            System.out.println("\n" + player.name + "의 최종 상태:");
            System.out.println("구역: " + player.zone.name);
            System.out.println("최종 에너지량: " + player.zone.energy.amount);
            System.out.println("누적 페널티: " + player.zone.totalPenalty);

            // 보유 건물 정보 출력
            System.out.println("보유 건물:");

            // 건물이 없는 경우
            if(player.zone.buildingCount == 0) {
                System.out.println("- 없음");
            }

            // 건물이 있는 경우 각 건물의 정보를 출력
            else {
                for(int j = 0; j < player.zone.buildingCount; j++) {
                    // 현재 출력할 건물
                    Building building = player.zone.buildings[j];

                    // 건물의 상세 정보 출력
                    System.out.println("- " + building.name +
                            " (레벨: " + building.level +
                            ", 생산량: " + building.energyProduction + ")");
                }
            }
        }

        // 승자 발표
        System.out.println("\n=== 승자 발표 ===");
        System.out.println("승자: " + winner.name);
        System.out.println("최종 누적 페널티: " + winner.zone.totalPenalty);


    }


    public static void playerTurn(EnergySystem system, Player currentPlayer, Player[] players, TurnManager turnManager) {
        while (true) {
            System.out.println("\n=== " + currentPlayer.name + "의 행동 선택 ===");
            System.out.println("1. 건물 건설");
            System.out.println("2. 에너지 전송");
            System.out.println("3. 동맹 관리");
            System.out.println("4. 에너지 저장");
            System.out.println("5. 페널티 현황 보기"); // 새로 추가
            System.out.println("6. 턴 종료");
            System.out.println("7. 게임 종료");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    constructBuilding(currentPlayer, system);
                    break;
                case 2:
                    transferEnergy(system, currentPlayer, players);
                    break;
                case 3:
                    manageAlliance(currentPlayer, players, turnManager);
                    break;
                case 4:
                    manageEnergyStorage(currentPlayer, system);
                    break;
                case 5:
                    turnManager.printPenaltyStatus(system.zones);
                    break;
                case 6:
                    /*system.onTurnEnd();  // 턴 종료 시 에너지 풀 처리
                    turnManager.nextTurn(system.zones);*/
                    return;
                case 7:
                    System.out.println("게임을 종료합니다.");
                    System.exit(0);
                default:
                    System.out.println("잘못된 선택입니다.");
                    break;
            }
        }
    }

    // 에너지 저장 관리 메서드 추가
    public static void manageEnergyStorage(Player currentPlayer, EnergySystem system) {
        while (true) {

            // 상태 표시 전에 현재 구역의 에너지 상태로 업데이트
            system.energyPool.setUnstoredEnergy(currentPlayer.zone);

            System.out.println("\n=== 에너지 저장소 관리 ===");
            System.out.println("현재 구역 에너지량: " + currentPlayer.zone.energy.amount);
            System.out.println("저장소 상태:");
            System.out.println("- 저장된 에너지: " + system.energyPool.getStoredEnergy());
            System.out.println("- 미저장 에너지: " + system.energyPool.getUnstoredEnergy());
            System.out.println("- 저장소 최대 용량: " + system.energyPool.getMaxCapacity());

            System.out.println("\n1. 에너지 저장하기");
            System.out.println("2. 저장된 에너지 사용하기");
            System.out.println("3. 돌아가기");

            int choice = sc.nextInt();

            switch(choice) {
                case 1:
                    System.out.println("\n저장할 에너지량을 입력하세요 (0: 취소):");
                    double storeAmount = sc.nextDouble();

                    if (storeAmount > 0) {
                        if (system.storeEnergy(currentPlayer.zone, storeAmount)) {
                            System.out.println(storeAmount + " 만큼의 에너지를 저장했습니다.");
                        } else {
                            System.out.println("에너지 저장에 실패했습니다.");
                        }
                    }
                    break;

                case 2:
                    System.out.println("\n사용할 에너지량을 입력하세요 (0: 취소):");
                    double useAmount = sc.nextDouble();

                    if (useAmount > 0) {
                        if (system.useStoredEnergy(currentPlayer.zone, useAmount)) {
                            System.out.println(useAmount + " 만큼의 저장된 에너지를 사용했습니다.");
                        } else {
                            System.out.println("저장된 에너지 사용에 실패했습니다.");
                        }
                    }
                    break;

                case 3:
                    return;

                default:
                    System.out.println("잘못된 선택입니다.");
                    break;
            }
        }
    }


    public static void manageAlliance(Player currentPlayer, Player[] players, TurnManager turnManager) {
        System.out.println("\n=== 동맹 관리 ===");
        System.out.println("1. 동맹 현황 보기");
        System.out.println("2. 동맹 요청하기");
        System.out.println("3. 동맹 탈퇴하기");
        System.out.println("4. 돌아가기");

        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                showAllianceStatus(currentPlayer, players);
                break;
            case 2:
                requestAlliance(currentPlayer, players, turnManager);
                break;
            case 3:
                leaveAlliance(currentPlayer, turnManager);
                break;
            case 4:
                return;
            default:
                System.out.println("잘못된 선택입니다.");
        }
    }


    private static void showAllianceStatus(Player currentPlayer, Player[] players) {
        System.out.println("\n=== 동맹 현황 ===");
        if(currentPlayer.zone.alliance == null) {
            System.out.println("현재 동맹이 가입되어 있지 않습니다.");
            return;
        }

        System.out.println("현재 동맹 멤버:");
        Zone[] members = currentPlayer.zone.alliance.getMembers();
        for(int i = 0; i < currentPlayer.zone.alliance.getMemberCount(); i++) {
            System.out.println("- " + members[i].name);
        }


    }


    private static void requestAlliance(Player currentPlayer, Player[] players, TurnManager turnManager) {
        System.out.println("\n=== 동맹 요청 ===");

        // 동맹 가능한 플레이어 목록 표시
        Player[] availablePlayers = new Player[players.length];
        int availableCount = 0;

        for (Player other : players) {
            if (other != currentPlayer && !Alliance.areAllies(currentPlayer.zone, other.zone)) {
                availablePlayers[availableCount] = other;
                String allianceStatus = other.zone.alliance != null ? " (다른 동맹 소속)" : "";
                System.out.println((availableCount + 1) + ". " + other.name + allianceStatus);
                availableCount++;
            }
        }

        if (availableCount == 0) {
            System.out.println("동맹 요청 가능한 플레이어가 없습니다.");
            return;
        }

        System.out.println("동맹을 요청할 플레이어 번호를 선택하세요 (0: 취소): ");
        int choice = sc.nextInt();

        if (choice > 0 && choice <= availableCount) {
            Player targetPlayer = availablePlayers[choice - 1];

            // 5턴 제한 체크
            if (!targetPlayer.zone.alliance.canRequestAlliance(currentPlayer.zone, targetPlayer.zone, turnManager.currentTurn)) {
                System.out.println("해당 플레이어와는 아직 동맹 요청이 불가능합니다. (5턴 제한)");
                return;
            }

            System.out.println("\n" + targetPlayer.name + "에게 동맹을 요청합니다.");
            if (targetPlayer.zone.alliance != null) {
                System.out.println("(주의: 상대방이 이미 다른 동맹에 속해 있습니다. 수락 시 기존 동맹에서 탈퇴됩니다)");
            }
            System.out.println(targetPlayer.name + ", 동맹 요청을 수락하시겠습니까? (1: 수락, 2: 거절)");
            int response = sc.nextInt();

            if (response == 1) {
                Alliance.createAlliance(currentPlayer.zone, targetPlayer.zone);
                System.out.println("동맹이 체결되었습니다!");
            } else {
                if (currentPlayer.zone.alliance != null) {
                    currentPlayer.zone.alliance.rejectRequest(currentPlayer.zone, targetPlayer.zone, turnManager.currentTurn);
                }
                System.out.println("동맹 요청이 거절되었습니다.");
            }
        }
    }


    private static void leaveAlliance(Player currentPlayer, TurnManager turnManager) {
        if (currentPlayer.zone.alliance == null) {
            System.out.println("현재 동맹에 가입되어 있지 않습니다.");
            return;
        }

        System.out.println("정말로 동맹에서 탈퇴하시겠습니까? (1: 예, 2: 아니오)");
        int choice = sc.nextInt();

        if (choice == 1) {
            currentPlayer.zone.alliance = null;
            System.out.println("동맹에서 탈퇴했습니다.");
        }
    }



    public static void constructBuilding(Player player, EnergySystem system) {
        System.out.println("\n=== 건물 건설 ===");
        System.out.println("현재 보유 에너지: " + player.zone.energy.amount);

        // 건설 가능한 건물 목록 표시
        System.out.println("1. 기초 발전소");
        System.out.println("   비용: " + Building.basic_plant_cost);
        System.out.println("   생산량: " + Building.basic_plant_production);

        System.out.println("2. 고급 발전소");
        System.out.println("   비용: " + Building.advanced_plant_cost);
        System.out.println("   생산량: " + Building.advanced_plant_production);

        System.out.println("3. 건물 목록 보기");
        System.out.println("4. 건물 업그레이드");
        System.out.println("5. 돌아가기");

        int choice = sc.nextInt();
        Building newBuilding = null;

        if (choice == 1) {
            // 기초 발전소 건설
            if (player.zone.energy.amount >= Building.basic_plant_cost) {
                newBuilding = new Building("기초 발전소", Building.basic_plant_production, 1, Building.basic_plant_cost);
                player.zone.energy.amount = player.zone.energy.amount - Building.basic_plant_cost;
                player.zone.addBuilding(newBuilding);
                System.out.println("기초 발전소를 건설했습니다.");
            } else {
                System.out.println("에너지가 부족합니다.");
            }
        }
        else if (choice == 2) {
            // 고급 발전소 건설
            if (player.zone.energy.amount >= Building.advanced_plant_cost) {
                newBuilding = new Building("고급 발전소", Building.advanced_plant_production, 2, Building.advanced_plant_cost);
                player.zone.energy.amount = player.zone.energy.amount - Building.advanced_plant_cost;
                player.zone.addBuilding(newBuilding);
                System.out.println("고급 발전소를 건설했습니다.");
            } else {
                System.out.println("에너지가 부족합니다.");
            }
        }
        else if (choice == 3) {
            showBuildings(player);
        }
        else if (choice == 4) {
            upgradeBuilding(player);
        }
        else if (choice == 5) {
            return;
        }
    }


    // 건물 목록 보기
    public static void showBuildings(Player player) {
        System.out.println("\n=== 보유 중인 건물 목록 ===");

        if (player.zone.buildingCount == 0) {
            System.out.println("보유 중인 건물이 없습니다.");
            return;
        }

        for (int i = 0; i < player.zone.buildingCount; i++) {
            Building b = player.zone.buildings[i];
            System.out.println((i + 1) + ". " + b.name);
            System.out.println("   레벨: " + b.level);
            System.out.println("   생산량: " + b.energyProduction);
            System.out.println("   소비량: " + b.energyConsumption);
        }
    }



    // 건물 업그레이드 메서드 추가
    private static void upgradeBuilding(Player player) {
        showBuildings(player);

        if(player.zone.buildingCount == 0) {
            return;
        }

        System.out.println("\n업그레이드할 건물 번호를 선택하세요 (0: 취소):");
        int buildingNum = sc.nextInt() - 1;

        if (buildingNum >= 0 && buildingNum < player.zone.buildingCount) {
            Building building = player.zone.buildings[buildingNum];
            int upgradeCost = building.constructionCost * building.level;

            System.out.println("업그레이드 비용: " + upgradeCost + " 에너지");
            System.out.println("진행하시겠습니까? (1: 예, 2: 아니오)");

            if (sc.nextInt() == 1) {
                if (player.zone.energy.amount >= upgradeCost) {
                    player.zone.energy.amount = player.zone.energy.amount - upgradeCost;
                    building.upgrade();
                    System.out.println("건물을 업그레이드했습니다.");
                    System.out.println("새로운 생산량: " + building.energyProduction);
                } else {
                    System.out.println("에너지가 부족합니다.");
                }
            }
        }
    }

    //모든 구역 상태 출력
    public static void printZoneStatus(EnergySystem system) {
        for (int i = 0; i < system.zones.length; i++) {
            Zone zone = system.zones[i];
            System.out.println(zone.name + ":");
            System.out.println("현재 에너지량: " + zone.energy.amount);
            System.out.println("최대 용량: " + zone.energy.maxCapacity);
            System.out.println("최소 에너지: " + zone.minEnergy);
            System.out.println("최대 전송량: " + zone.maxTransfer);

            if (zone.penalty != null) {
                System.out.println("페널티 상태: " + (zone.penalty.isActive ? "활성" : "비활성"));
                System.out.println("페널티 양: " + zone.penalty.penaltyAmount);
            }

            System.out.println("인구 수: " + zone.population);

            // 건물 목록 출력
            System.out.println("보유 건물:");
            Building[] buildings = zone.getBuildings();
            if (buildings.length == 0) {
                System.out.println("- 없음");
            } else {
                for (Building b : buildings) {
                    System.out.println("- " + b.name + " (생산량: " + b.energyProduction +
                            ", 소비량: " + b.energyConsumption + ")");
                }
            }

            // 동맹 정보 출력
            if (zone.alliance != null) {
                System.out.println("동맹 상태: 있음");
                Zone[] allies = zone.alliance.getMembers();
                for (int j = 0; j < zone.alliance.getMemberCount(); j++) {
                    if (allies[j] != zone) {
                        System.out.println("- 동맹국: " + allies[j].name);
                    }
                }
            } else {
                System.out.println("동맹 상태: 없음");
            }
            System.out.println();
        }
    }


    public static void transferEnergy(EnergySystem system, Player currentPlayer, Player[] players) {
        System.out.println("\n=== 에너지 전송 ===");

        // 동맹 구성원 찾기
        Player[] alliancePlayers = new Player[players.length];
        int allianceCount = 0;

        for (int i = 0; i < players.length; i++) {
            if  (players[i] != currentPlayer && currentPlayer.zone.alliance != null &&
                    currentPlayer.zone.alliance.areAllies(currentPlayer.zone, players[i].zone)) {
                alliancePlayers[allianceCount] = players[i];
                System.out.println((allianceCount + 1) + ". " + players[i].name +
                        " (현재 에너지: " + players[i].zone.energy.amount + ")");
                allianceCount++;
            }
        }

        if (allianceCount == 0) {
            System.out.println("에너지를 전송할 수 있는 동맹국이 없습니다.");
            return;
        }

        System.out.println("\n현재 보유 에너지: " + currentPlayer.zone.energy.amount);
        System.out.println("최대 전송 가능량: " + currentPlayer.zone.maxTransfer);

        System.out.println("\n에너지를 전송할 플레이어 번호를 선택하세요 (0: 취소):");
        int choice = sc.nextInt();

        if (choice > 0 && choice <= allianceCount) {
            Player targetPlayer = alliancePlayers[choice - 1];
            System.out.println("전송할 에너지량을 입력하세요:");
            double amount = sc.nextDouble();

            boolean result = system.transferManager.transferEnergy(
                    currentPlayer.zone,
                    targetPlayer.zone,
                    amount,
                    system.energyPool
            );

            if (!result) {
                System.out.println("에너지 전송에 실패했습니다.");
            }
        }
    }
}


