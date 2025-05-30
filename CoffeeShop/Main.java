package project.CoffeeShop;

public class Main {
    public static void main(String[] args) {
        ShopManager shopManager = new ShopManager();

        // 자동모드 실행 (인자가 "auto"일 때)
        if (args.length > 0 && args[0].equals("auto")) {
            shopManager.runAutoMode(); //
        } else {
            shopManager.start(); // 기존 CLI 메뉴
        }
    }
}
