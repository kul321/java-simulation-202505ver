import java.util.Date;

public class Promotion {
    // 데이터만 저장
    public Weapon weapon;
    public Date startDate;
    public Date endDate;
    public int discountRate;
    public boolean isOnePlusOne;

    // 생성자로 데이터 초기화만 담당
    public Promotion(Weapon weapon, Date startDate, Date endDate, int discountRate, boolean isOnePlusOne) {
        this.weapon = weapon;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountRate = discountRate;
        this.isOnePlusOne = isOnePlusOne;
    }
}





