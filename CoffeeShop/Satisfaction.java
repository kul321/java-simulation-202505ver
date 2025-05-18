package project.CoffeeShop;

import java.util.List;

public class Satisfaction {
    public int satisfaction;

    // 최소/최대 만족도 제한
    public static final int MIN_SATISFACTION = 0;
    public static final int MAX_SATISFACTION = 100;

    public Satisfaction(){
        satisfaction = 50; //초기 만족도
    }

    public int processSatisfaction(double time, List<MenuItem> order) {

        // 기준 시간 30초로 변경 (요구사항대로)
        double BASE_TIME = 30.0;

        // 주문 크기에 따른 기대 처리 시간 조정
        double expectedTime = Math.min(BASE_TIME, 5.0 * order.size()); // 메뉴당 5초 기본 기대치, 최대 30초

        // 처리 시간 비율 계산 (1.0이면 정확히 기대 시간과 일치, 낮을수록 빠름, 높을수록 느림)
        double timeRatio = time / expectedTime;

        int result;

        // 만족도 계산
        if (timeRatio <= 0.5) {
            // 매우 빠른 서비스 (예상의 절반 이하) - 최대 보너스
            result = 40;
            System.out.println("매우 빠른 서비스로 최대 만족도 보너스를 얻습니다.");
        } else if (timeRatio <= 1.0) {
            // 빠르거나 정확한 서비스 - 높은 보너스
            result = (int)(40 - (timeRatio - 0.5) * 20); // 40~30 사이 선형 감소
            System.out.println("빠른 서비스로 높은 만족도 보너스를 얻습니다.");
        } else if (timeRatio <= 2.0) {
            // 느린 서비스 - 약간의 보너스
            result = (int)(30 - (timeRatio - 1.0) * 30); // 30~0 사이 선형 감소
            System.out.println("평균 이상의 시간이 소요되었습니다. 낮은 만족도 보너스를 얻습니다.");
        } else {
            // 매우 느린 서비스 - 만족도 감소
            result = (int)(-10 * Math.min(3, (timeRatio - 2.0))); // -10~-30 사이 선형 감소, 최대 -30
            System.out.println("서비스가 너무 느려 손님이 불만족을 느낍니다.");
        }

        System.out.println("주문처리시간 " + time + "초 (기대: " +
                expectedTime + "초), 만족도 변화: " + result);

        return result;
    }

    // 만족도에 따른 손님 배율 반환
    public double getCustomerMultiplier(){
        // 만족도가 50(기본값)일 때 배율이 1.0
        return 0.5 + (satisfaction / 100.0);
    }

    // 만족도 증가 (최대 100)
    public void increaseSatisfaction(int amount) {
        satisfaction += amount;
        if (satisfaction > MAX_SATISFACTION) {
            satisfaction = MAX_SATISFACTION;
        }
        System.out.println("만족도가 " + amount + "만큼 증가했습니다. 현재 만족도: " + satisfaction);
    }

    // 만족도 감소 (최소 0)
    public void decreaseSatisfaction(int amount) {
        satisfaction -= amount;
        if (satisfaction < MIN_SATISFACTION) {
            satisfaction = MIN_SATISFACTION;
        }
        System.out.println("만족도가 " + amount + "만큼 감소했습니다. 현재 만족도: " + satisfaction);
    }

    // 만족도가 0인지 확인 (게임오버 조건)
    public boolean isZero() {
        return satisfaction <= 0;
    }
}