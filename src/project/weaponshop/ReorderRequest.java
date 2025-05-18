public class ReorderRequest {
    public Weapon weapon;
    public String customerId;
    public int requestTurn;

    public ReorderRequest(Weapon weapon, String customerId, int requestTurn) {
        this.weapon = weapon;
        this.customerId = customerId;
        this.requestTurn = requestTurn;
    }
}
