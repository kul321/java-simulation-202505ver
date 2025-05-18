package project.Energy;

public class EnergyTransferManager {
    private EnergySystem energySystem;  // EnergySystem 참조 추가

    public EnergyTransferManager(EnergySystem energySystem) {
        this.energySystem = energySystem;
    }

    public boolean transferEnergy(Zone from, Zone to, double amount, EnergyPool energyPool) {
        EnergyTransferValidation validation = this.energySystem.validateEnergyTransfer(from, to, amount, energyPool);

        if (!validation.isValid) {
            System.out.println(validation.errorMessage);
            return false;
        }

        // 미저장 에너지에서만 차감
        energyPool.unstoredEnergy -= amount;
        from.energy.amount -= amount;
        to.energy.amount += amount;
        System.out.println("에너지 전송 완료: " + amount + " 단위");

        return true;
    }
}
