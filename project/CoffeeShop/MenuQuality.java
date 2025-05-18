package project.CoffeeShop;

public class MenuQuality {
    public static final String BAD = "나쁨";
    public static final String NORMAL = "보통";
    public static final String GOOD = "좋음";
    public static final String EXCELLENT = "매우 좋음";


//가격 배율
public static double resultPriceMultiplier(String quality) {
    return switch (quality) {
        case BAD -> 0.7;
        case NORMAL -> 1.0;
        case GOOD -> 1.5;
        case EXCELLENT -> 2.0;
        default -> 1.0;
    };

}
}