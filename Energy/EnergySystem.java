package project.Energy;

public class EnergySystem {
    public Zone[] zones;
    public EnergyTransferManager transferManager;
    public EnergyPool energyPool;

    public EnergySystem(int zoneCount) {
        zones = new Zone[zoneCount];
        energyPool = new EnergyPool(0);
        transferManager = new EnergyTransferManager(this);
    }



    // 공통 검증 메서드
    public EnergyTransferValidation validateEnergyTransfer(Zone from, Zone to, double amount, EnergyPool energyPool) {
        // 같은 구역일 경우 (에너지 저장의 경우) 동맹 체크를 건너뜀
        if(from != to) {  // 다른 구역으로 전송할 때만 동맹 체크
            if(from.alliance == null || !from.isAlliedWith(to)) {
                return new EnergyTransferValidation(false, "동맹 관계가 아니어서 에너지를 전송할 수 없습니다.");
            }
        }

        // 미저장 에너지 검증
        if(amount > energyPool.getUnstoredEnergy()) {
            return new EnergyTransferValidation(false, "미저장 에너지가 부족합니다.");
        }


        // 사용 가능한 총 에너지 계산 (구역 에너지 + 미저장 에너지)
        double availableEnergy = from.energy.amount + energyPool.unstoredEnergy;

        // 전송량 검증
        if(amount > from.maxTransfer) {
            return new EnergyTransferValidation(false, "최대 전송 가능량을 초과했습니다.");
        }

        // 사용 가능한 에너지 검증
        if(amount > availableEnergy) {
            return new EnergyTransferValidation(false, "사용 가능한 에너지가 부족합니다.");
        }

        // 수신 구역 용량 검증
        if(to.energy.amount + amount > to.energy.maxCapacity) {
            return new EnergyTransferValidation(false, "대상 구역의 최대 용량을 초과합니다.");
        }

        return new EnergyTransferValidation(true, "");
    }



    public boolean storeEnergy(Zone zone, double amount) {
        // 현재 구역의 에너지만 미저장 에너지로 설정
        energyPool.setUnstoredEnergy(zone);
        if (amount > zone.energy.amount) {
            System.out.println("보유한 에너지보다 많은 양을 저장할 수 없습니다.");
            return false;
        }

        if (!energyPool.storeEnergy(amount)) {
            System.out.println("저장소의 최대 용량을 초과했습니다.");
            return false;
        }

        zone.energy.amount -= amount;

        return true;
    }


    // 저장된 에너지 사용 메서드
    public boolean useStoredEnergy(Zone zone, double amount) {
        if(energyPool.getStoredEnergy() < amount) {
            return false;
        }

        // 구역의 최대 용량 체크
        if(zone.energy.amount + amount > zone.energy.maxCapacity) {
            return false;
        }

        zone.energy.amount += amount;
        energyPool.storedEnergy -= amount;
        return true;
    }

    // 턴 종료시 호출되는 메서드
    public void onTurnEnd() {
        energyPool.onTurnEnd();
    }
}