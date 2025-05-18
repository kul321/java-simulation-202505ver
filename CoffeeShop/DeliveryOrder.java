package project.CoffeeShop;

class DeliveryOrder {
    public int deliveryDay;      // 배달 예정일
    public String ingredientName; // 재료 이름
    public int quantity;         // 수량

    public DeliveryOrder(int deliveryDay, String ingredientName, int quantity) {
        this.deliveryDay = deliveryDay;
        this.ingredientName = ingredientName;
        this.quantity = quantity;
    }
}
