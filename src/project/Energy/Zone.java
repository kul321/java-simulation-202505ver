package project.Energy;

public class Zone {
    // 구역의 기본 정보
    public String name;
    public double maxTransfer;
    public double minEnergy;
    public Energy energy;
    public Penalty penalty;
    public int population;
    public double balance;

    // 건물 관련 변수
    public Building[] buildings;
    public int buildingCount;    // 현재 건물 수
    public static int max_buildings= 20;  // 최대 건물 수

    // 동맹 관련 변수
    public Alliance alliance;


    public double totalPenalty = 0;  // 누적 페널티

    public Zone(String name, double initialEnergy, double maxCapacity, double minEnergy, double maxTransfer, int population){
        this.name = name;
        if (initialEnergy > maxCapacity) {
            initialEnergy = maxCapacity;
            System.out.println("초기 에너지가 최대 용량을 초과하여 " + maxCapacity + "로 조정되었습니다.");
        }
        this.energy = new Energy(initialEnergy,maxCapacity,minEnergy);
        this.maxTransfer = maxTransfer;
        this.minEnergy = minEnergy;
        this.population = population;
        this.buildings = new Building[max_buildings];
        this.buildingCount = 0;
        this.balance = 3;
    }

    // 새로운 건물 추가
    public void addBuilding(Building building) {
        if(buildingCount < max_buildings) {
            buildings[buildingCount++] = building;
        }
    }



    // 인구 변동 처리
    public void addPopulation(int newPopulation) {
        this.population += newPopulation;
        if(this.population < 0) {
            this.population = 0;
        }
       // updateEnergyConsumption();
    }

    // 소비량 계산만 하고 실제 차감은 하지 않음
    public double calculateEnergyConsumption() {
        return population * balance;
    }

    // 현재 보유 중인 건물 목록 반환
    public Building[] getBuildings() {
        Building[] result = new Building[buildingCount];
        System.arraycopy(buildings, 0, result, 0, buildingCount);
        return result;
    }

    // 인구에 따른 에너지 소비량 업데이트
    public void updateEnergyConsumption() {
        double consumption = population * balance;
        energy.amount -= consumption;
    }


    // 다른 구역과의 동맹 관계 확인
    public boolean isAlliedWith(Zone other) {
    return this.alliance != null && this.alliance.areAllies(this, other);
    }


    // 페널티 설정
    public void setPenalty(double amount){
        this.penalty = new Penalty(this,amount);
    }
}