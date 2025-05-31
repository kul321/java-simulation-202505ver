package project.CoffeeShop;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Args: " + Arrays.toString(args)); // 확인 로그

        ShopManager shopManager = new ShopManager();

        if (args.length > 0 && args[0].equals("auto")) {
            System.out.println("자동모드 진입");
            shopManager.runAutoMode();
        } else {
            System.out.println("CLI 모드 진입");
            shopManager.start();
        }
    }
}

