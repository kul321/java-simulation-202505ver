package project.Energy;

public class Penalty {
    public double penaltyAmount;
    public boolean isActive;
    public Zone affectedZone;

    public Penalty(Zone zone, double amount){
        this.affectedZone = zone;
        this.penaltyAmount = amount;
        this.isActive = false;
    }

    public void applyPenalty(){
        if(!isActive){
            affectedZone.energy.amount -=penaltyAmount;
            affectedZone.totalPenalty += penaltyAmount;
            isActive = true;
        }
    }

    public void removePenalty(){
        if(isActive){
            affectedZone.energy.amount += penaltyAmount;
            isActive = false;
        }
    }

    public void tryAutoPayback() {
        if (isActive) {
            if (affectedZone.energy.amount >= affectedZone.minEnergy + penaltyAmount) {
                affectedZone.energy.amount -= penaltyAmount;
                isActive = false;
            }
        }
    }
}
