package project.CoffeeShop;

public class Employee {
    public static final int AMATEUR = 0; //평균보다 낮은 능력치
    public static final int BARISTA = 1; //음료전문
    public static final int CASHIER = 2; //기준치
    public static final int COOK = 3; //음식전문
    public static final int ALLROUNDER = 4; //평균보다 높은 능력치

    public int type;
    public String name;
    public int speedSkill;
    public int salary;
    public boolean isWorking;

    public Employee(String name, int type, int speedSkill){
        this.name = name;
        this.type = type;
        this.speedSkill = speedSkill;


        calculateSalary();
    }

    //타입과 스킬에 따라 급여 계산
    public void calculateSalary(){
        //기본 급여(스피드스킬 1당 100원)
        salary = speedSkill * 100;

        //수습이 아니면 추가 급여
        if(type != AMATEUR){
            salary += 100;
        }

        //숙달된 경력 직원에게는 추가 급여
        if(type == ALLROUNDER){
            salary += 150;
        }
    }

    //처리속도 계산
    public double processingTime(MenuItem item){
        double time = 2.0 - (speedSkill * 0.1);

        //일반 알바 수습생일 경우 시간이 더 걸림
        if(type == AMATEUR){
            time *= 1.2; // 20퍼 지연
        }

        //전문 분야에 따른 추가 단축
        if((type == BARISTA && item.type ==MenuItem.DRINK) || (type == COOK && item.type ==MenuItem.FOOD) || (type == ALLROUNDER)){
            time *= 0.7; // 30퍼 단축 올라운더는 모든 주문에 속도 보너스
        }
        return time;
    }

    //근무 시작/종료
    public void setWorking(boolean working){
        this.isWorking = working;
    }

    //직원 타입 이름 반환
    public String getTypeName(){
        switch (type){
            case AMATEUR: return "수습알바생";
            case BARISTA : return "바리스타";
            case CASHIER : return "캐셔";
            case COOK: return "요리사";
            case ALLROUNDER: return "올라운더";
            default: return "-";
        }
    }

}
