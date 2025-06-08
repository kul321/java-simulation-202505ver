import java.time.LocalDate;
import java.util.Scanner;

public class Project1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ShopManager shop = new ShopManager();

        while (true) {
            LocalDate today = LocalDate.now();
            System.out.println("현재 날짜: " + today);
            System.out.println("사용자 로그인 ID: ");
            String inputId = sc.next();
            System.out.println("사용자 패스워드 : ");
            String inputPw = sc.next();


                shop.handleLogin(inputId, inputPw);

        }
    }
}
