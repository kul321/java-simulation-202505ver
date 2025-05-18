package project.Energy;

public class EnergyTransferValidation {
    public String errorMessage;
    public boolean isValid;

    public EnergyTransferValidation(boolean isValid, String errorMessage) {
        this.isValid = isValid;
        this.errorMessage = errorMessage;
    }
}