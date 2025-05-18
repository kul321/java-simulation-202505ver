package project.Energy;


public class EnergyPool {
    public double storedEnergy;        // 저장된 에너지 (다음 턴까지 사용 불가)
    public double unstoredEnergy;       // 미저장 에너지 (현재 턴에 사용 가능)
    public double maxCapacity;         // 에너지 풀의 최대 저장 가능 용량

    public EnergyPool(double maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.storedEnergy = 0;
        this.unstoredEnergy = 0;
    }

    // 현재 구역의 에너지만을 미저장 에너지로 설정
    public void setUnstoredEnergy(Zone currentZone) {
        this.unstoredEnergy = currentZone.energy.amount;
        this.maxCapacity = currentZone.energy.maxCapacity;
    }

    // 에너지 저장
    public boolean storeEnergy(double amount) {
        if (amount <= 0 || storedEnergy + amount > maxCapacity || amount > unstoredEnergy) {
            return false;
        }
        storedEnergy += amount;
        unstoredEnergy -= amount;
        return true;
    }

    public double calculateUnstoredEnergy() {
        // 미저장 에너지는 저장된 에너지를 제외한 현재 가용 에너지
        return unstoredEnergy;
    }

    //턴 종료
    public void onTurnEnd() {
        unstoredEnergy = 0;  // 미저장 에너지 소멸
        unstoredEnergy = storedEnergy;  // 저장된 에너지를 미저장 상태로 변경
        storedEnergy = 0;
    }


    //저장된 에너지 반환
    public double getStoredEnergy() {
        return storedEnergy;
    }


    //미저장 에너지 반환
    public double getUnstoredEnergy() {
        return calculateUnstoredEnergy();
    }

    //최대 저장 가능 용량 반환
    public double getMaxCapacity() {
        return maxCapacity;
    }

    public void updateUnstoredEnergy(Zone[] zones) {
        unstoredEnergy = 0;
        for (Zone zone : zones) {
            if (zone != null) {
                unstoredEnergy += zone.energy.amount;
            }
        }
    }
}