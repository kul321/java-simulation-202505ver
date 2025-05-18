package project.Energy;

public class TurnManager {
    public int currentTurn;
    public int maxTurns;


    public TurnManager(int maxTurns) {
        this.currentTurn = 1;
        this.maxTurns = maxTurns;
    }

    public void nextTurn(Zone[] zones) {
        currentTurn++;

        // 턴 시작시 인구 이동 처리
        handlePopulationMovement(zones);

        // 각 구역별 처리
        for(int i = 0; i < zones.length; i++) {
            Zone zone = zones[i];  // 현재 처리할 구역

            // 1단계 건물들의 에너지 생산/소비를 처리
            buildingProcess(zone);

            // 2단계 구역 인구의 에너지 소비를 처리
            populationProcess(zone);

            // 3단계 구역의 최종 에너지 상태를 확인하고 페널티 적용
            calculateBalance(zone);
        }

        for (int i = 0; i < zones.length; i++) {
            if (zones[i].penalty != null) {
                zones[i].penalty.tryAutoPayback();  // 페널티 자동 상환 시도
            }
        }
    }

    // 인구 이동 처리 메서드 추가
    public void handlePopulationMovement(Zone[] zones) {
        System.out.println("\n=== 인구 이동 단계 ===");
        for (int i = 0; i < zones.length; i++) {
            Zone zone = zones[i];  // 현재 처리할 구역
            System.out.println("\n" + zone.name + "의 현재 인구: " + zone.population);

            // 인구 변동 입력 받기
            System.out.println("이주할 인구 수를 입력하세요 (+: 유입, -: 유출):");
            int populationChange = Main.sc.nextInt();

            zone.addPopulation(populationChange);

            System.out.println("변경 후 인구: " + zone.population);
        }
    }

    // 건물의 에너지 생산/소비를 처리
    public void buildingProcess(Zone zone) {
        double productionSum = 0; // 총 에너지 생산량
        double consumptionSum = 0; // 총 에너지 소비량

        for(int i = 0; i < zone.buildingCount; i++) {
            Building building = zone.buildings[i];
            productionSum = productionSum + building.energyProduction;
            consumptionSum = consumptionSum + building.energyConsumption;
        }

        // 순수 생산량을 zone의 에너지에 적용
        double netProduction = productionSum - consumptionSum;
        zone.energy.amount += netProduction;
    }


    // 인구에 따른 에너지 소비를 처리
    public void populationProcess(Zone zone) {
        double consumption = zone.calculateEnergyConsumption();
        zone.energy.amount -= consumption;  // 실제 차감은 여기서만 수행

        System.out.println(zone.name + "의 인구 관련 정보:");
        System.out.println("현재 인구: " + zone.population);
        System.out.println("인구당 에너지 소비량: " + zone.balance);
        System.out.println("총 소비량: " + consumption);
    }


    public void calculateBalance(Zone zone) {
        // 최소 에너지 요구량 체크
        if(zone.energy.amount < zone.minEnergy) {
            double deficit = zone.minEnergy - zone.energy.amount;

            // 페널티가 없다면 새로 생성
            if(zone.penalty == null) {
                zone.setPenalty(deficit);
            }

            // 페널티 적용
            zone.penalty.applyPenalty();
        }
        // 에너지가 충분하고 활성화된 페널티가 있다면 제거
        else if(zone.penalty != null && zone.penalty.isActive) {
            zone.penalty.removePenalty();
        }

        // 최대 용량 제한
        if(zone.energy.amount > zone.energy.maxCapacity) {
            zone.energy.amount = zone.energy.maxCapacity;
        }
    }


    public boolean isGameOver() {
        return currentTurn >= maxTurns;
    }

    // 남은 턴 수를 반환
    public int getRemainingTurns() {
        return maxTurns - currentTurn;
    }

    //페널티 상태 표시
    public void printPenaltyStatus(Zone[] zones) {
        System.out.println("\n=== 현재 페널티 현황 ===");
        for (int i = 0; i < zones.length; i++) {
            Zone zone = zones[i];
            System.out.println(zone.name + ":");
            System.out.println("- 누적 페널티: " + zone.totalPenalty);
            if (zone.penalty != null && zone.penalty.isActive) {
                System.out.println("- 현재 페널티: " + zone.penalty.penaltyAmount);
            }
        }
    }
}
