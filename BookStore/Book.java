package project.BookStore;


/*
그 외에도, 본인이 서점 관리 시스템에서 더 추가하고 싶은 부분이 있다면 추가해보세요.
(ex. 결제하기, 책 정보 수정하기, 출판사 목록 출력하기 등등)
*/

public class Book {
    public static Scanner sc = new Scanner(System.in);

    public String title; //제목
    public int bookNumber; //책번호
    public String author; //작가
    public String publisher; //출판사
    public int year; //출판연도
    public int stock; //재고
    public int price; //가격
    public int category; //책 분류 카테고리 0~9





public Book(String title, int bookNumber, String author, String publisher, int year, int stock, int price, int category){
    this.title = title;
    this.bookNumber = bookNumber;
    this.author = author;
    this.publisher = publisher;
    this.year = year;
    this.stock = stock;
    this.price = price;
    this.category = category;

}

public String getCategoryName(){
    return switch(this.category){
        case 0 -> "총류";
        case 1 -> "철학";
        case 2 -> "종교";
        case 3 -> "사회과학";
        case 4 -> "자연과학";
        case 5 -> "기술과학";
        case 6 -> "예술";
        case 7 -> "언어";
        case 8 -> "문학";
        case 9 -> "역사";
        default -> "총류";
    };
}

public void addBook(int addbook){
    this.stock += addbook;
    System.out.println(title + " " + addbook + "권이 입고되었습니다.");
    System.out.println("현재 재고: " + this.stock + "권");
    System.out.println("------");
}

public boolean sellBook(int sellbook) {
     if(sellbook > this.stock) {
       System.out.println("재고가 부족합니다. 현재 재고: " + this.stock);
            return false;
        }

     this.stock -= sellbook;
       System.out.println(title + " " + sellbook + "권이 판매되었습니다.");
       System.out.println("현재 재고: " + stock);

       if(this.stock == 0) {
            System.out.println("재고가 모두 소진되었습니다.");
            addBook(10);
        }
        System.out.println("-------");
        return true;
    }

public void printInfo(){
    System.out.println("번호:" + bookNumber);
    System.out.println("제목: " + title);
    System.out.println("작가: " + author);
    System.out.println("출판사: " + publisher);
    System.out.println("출판년도: " + year);
    System.out.println("카테고리 : " + getCategoryName()+"["+category+"]");
    System.out.println("재고수량: " + stock);
    System.out.println("가격" + price);
    System.out.println("------------");

}


public void updateTitle(){
    try{
    System.out.print("제목변경: ");
    String newTitle = sc.nextLine();
    this.title = newTitle;
    System.out.println("제목이 ["+newTitle+"]로 변경되었습니다.");
    } catch(Exception e){
        System.out.println("제목 변경 중 오류가 발생했습니다.");
    }
}

public void updateAuthor(){
    try{

    System.out.print("저자변경: ");
    String newAuthor = sc.nextLine();
    this.author = newAuthor;
    System.out.println("저자명이 ["+newAuthor+"]로 변경되었습니다.");
    } catch(Exception e){
        System.out.println("작가 변경 중 오류가 발생했습니다.");
    }
}

public void updatePrice() {
    try{
    System.out.print("가격변경: ");
    int newPrice = sc.nextInt();
    sc.nextLine(); // 버퍼 비우기
    System.out.println("가격 변경이 [" + newPrice + "]원으로 변경되었습니다.");
    this.price = newPrice;}
    catch(Exception e){
            System.out.println("가격 변경 중 오류가 발생했습니다.");
        }
    }
public void updateCategory() {
    try {
        System.out.println("카테고리 목록:");
        System.out.println("0: 총류");
        System.out.println("1: 철학");
        System.out.println("2: 종교");
        System.out.println("3: 사회과학");
        System.out.println("4: 자연과학");
        System.out.println("5: 기술과학");
        System.out.println("6: 예술");
        System.out.println("7: 언어");
        System.out.println("8: 문학");
        System.out.println("9: 역사");

        System.out.print("카테고리 변경(0-9): ");
        int newCategory = sc.nextInt();
        sc.nextLine();

        if (newCategory >= 0 && newCategory <= 9) {
            String oldCategoryName = getCategoryName();
            this.category = newCategory;
            System.out.println("카테고리 변경: " + oldCategoryName + " -> " + getCategoryName());
        } else {
            System.out.println("잘못된 카테고리 코드입니다. 0-9 사이의 값을 입력하세요.");
        }
    } catch(InputMismatchException e){
        System.out.println("카테고리는 숫자로 입력해야 합니다.");
    }

    catch (Exception e) {
        System.out.println("카테고리 변경 중 오류가 발생했습니다. 다시 확인해주세요.");
    }
}

public int calculateTotalPrice(int quantity){
    return this.price * quantity;
}


}