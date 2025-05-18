package project.Energy;

public class Building {
    public String name;
    public double energyProduction;
    public double energyConsumption;
    public int constructionCost;
    public int level;

    // 기본 발전소 관련 상수
    public static int basic_plant_cost = 10;
    public static int basic_plant_production = 5;

    // 고급 발전소 관련 상수
    public static int advanced_plant_cost = 50;
    public static int advanced_plant_production = 20;

    public Building(String name, double production, double consumption, int cost) {
        this.name = name;
        this.energyProduction = production;
        this.energyConsumption = consumption;
        this.constructionCost = cost;
        this.level = 1;
    }

    public void upgrade() {
        this.level = this.level + 1;
        // 레벨업당 20% 증가
        this.energyProduction = this.energyProduction + (this.energyProduction * 0.2);
    }
}
