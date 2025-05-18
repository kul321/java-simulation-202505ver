public class Weapon {
    public String name;
    public String category;
    public int damage;
    public int speed;
    public int weight;
    public int range;
    public int durability;
    public int price;
    public int stock;

    // 생성자
    public Weapon(String name, String category, int damage, int speed, int weight, int range, int durability) {
        this.name = name;
        this.category = category;
        this.damage = damage;
        this.speed = speed;
        this.weight = weight;
        this.range = range;
        this.durability = durability;
        calculatePrice(); // 가격 자동 계산
    }

    // 가격 계산 메소드
    public void calculatePrice() {
        int basePrice = (damage * 300 + speed * 200 - weight * 100 + range * 150 + durability * 100);
        if (speed < 30) {
            this.price = 1000; // 최소가격
            this.stock = 5;    // 저가 무기는 재고 많이
        } else if (damage > 90 && speed > 80) {
            this.price = 10000; // 최대가격
            this.stock = 1;     // 최고가 무기는 재고 제한
        } else {
            this.price = Math.min(Math.max(basePrice, 1000), 10000);
            this.stock = 2;     // 일반 무기는 기본 재고
        }
    }
}
