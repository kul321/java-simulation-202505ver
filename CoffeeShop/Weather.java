package project.CoffeeShop;

public class Weather {
    public static final int SUNNY = 0;
    public static final int RAINY = 1;
    public static final int SNOWY = 2;
    public static final int CLOUDY = 3;
    public static final int HOT = 4;
    public static final int COLD = 5;

    public int type;
    public String name;

    //손님 수 배율
    public double customerMultiplier;

    //뜨거운 음료 선택률
    public double hotDrinkPreference;

    //차가운 음료 선택률
    public double coldDrinkPreference;

    //음식 선택률
    public double foodPreference;

    public Weather(int type){
        this.type = type;

        switch(type){
            case SUNNY:
                name = "맑음";
                customerMultiplier = 1.0;
                hotDrinkPreference = 0.3;
                coldDrinkPreference = 0.7;
                foodPreference = 0.3;
                break;

            case RAINY:
                name  = "비";
                customerMultiplier = 0.6;
                hotDrinkPreference = 0.5;
                coldDrinkPreference = 0.5;
                foodPreference = 0.4;
                break;

            case SNOWY:
                name = "눈";
                customerMultiplier = 0.5;
                hotDrinkPreference = 0.6;
                coldDrinkPreference = 0.4;
                foodPreference = 0.3;
                break;


            case CLOUDY:
                name = "흐림";
                customerMultiplier = 0.9;
                hotDrinkPreference = 0.3;
                coldDrinkPreference = 0.7;
                foodPreference = 0.3;
                break;


            case COLD:
                name = "추움";
                customerMultiplier = 0.8;
                hotDrinkPreference = 0.7;
                coldDrinkPreference = 0.3;
                foodPreference = 0.4;
                break;


            case HOT:
                name = "더움";
                customerMultiplier = 1.2;
                hotDrinkPreference = 0.1;
                coldDrinkPreference = 0.9;
                foodPreference = 0.4;
                break;

        }
    }




}
