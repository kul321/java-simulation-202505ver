package project.CustomizingRPGCharacter;

public class Account {
    public String id;
    public String password;
    public GameCharacter[] characters;
    public int characterCount;
    public static Account[] accounts = new Account[100];  // 최대 100개 계정 저장
    public static int accountCount = 0;

    public Account(String id, String password) {
        this.id = id;
        this.password = password;
        this.characters = new GameCharacter[5]; // 최대 5개의 캐릭터 저장 가능
        this.characterCount = 0;
    }

    // 회원가입
    public static boolean register(String id, String password) {
        // ID 중복 체크
        for(int i = 0; i < accountCount; i++) {
            if(accounts[i].id.equals(id)) {
                System.out.println("이미 존재하는 아이디입니다.");
                return false;
            }
        }

        // 새 계정 생성
        accounts[accountCount] = new Account(id, password);
        accountCount++;
        System.out.println("회원가입이 완료되었습니다.");
        return true;
    }

    // 로그인
    public static Account login(String id, String password) {
        for(int i = 0; i < accountCount; i++) {
            if(accounts[i].id.equals(id) && accounts[i].password.equals(password)) {
                System.out.println("로그인 성공!");
                return accounts[i];
            }
        }
        System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
        return null;
    }


    // 캐릭터 생성 메소드
    public boolean createCharacter(String characterName, String characterClass) {
        if (characterCount >= 5) {
            return false;
        }

        GameCharacter newCharacter = new GameCharacter(this.id, characterName, characterClass);
        characters[characterCount] = newCharacter;
        characterCount++;
        return true;
    }

    // 캐릭터 정보 출력 메소드
    public void displayAllCharacters() {
        for (int i = 0; i < characterCount; i++) {
            System.out.println("\n캐릭터 " + (i + 1) + " 정보:");
            characters[i].displayCharacterInfo();
        }
    }

}
