package project.Energy;

public class Energy {
    //기본 에너지 객체
    public double amount; //현재 에너지양
    public double maxCapacity; //최대 에너지 저장 가능량
    public double minCapacity; //최소 에너지 저장량

    public Energy(double amount, double maxCapacity, double minCapacity){
        this.amount = amount; //초기 에너지양 설정
        this.maxCapacity = maxCapacity; //최대 용량 설정
        this.minCapacity = minCapacity; //최소 용량 설정
    }
}
