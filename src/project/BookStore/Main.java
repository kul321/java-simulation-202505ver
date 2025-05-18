package project.BookStore;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    //(2) BookRepository 클래스 만들고 객체 선언
    public static BookRepository bookRepository = new BookRepository();

    public static void main(String[] args) {

        //초기 데이터
        initializeData();
        boolean open = true;

        while (open) {
            displayMenu();
            try {
                int choice = sc.nextInt();
                sc.nextLine();

                open = processMenuChoice(choice);
            }
        catch(InputMismatchException e){
            System.out.println("숫자를 입력해주세요.");
            sc.nextLine();
        }
        catch(Exception e){
            System.out.println("선택 중 오류가 발생했습니다.");
            sc.nextLine();

        }
        }


    }


    public static void initializeData() {
        //예시 세 가지 책
        Book book1 = new Book("파이썬을 배워라! 당신의 파이가 늘어난다", 1, "한용영", "한영출판사", 2012, 5, 13000, 0);
        Book book2 = new Book("취미로 한 프로그래밍, 나의 주수입원이 되다", 2, "김미래", "나노출판사", 2017, 12, 15000, 0);
        Book book3 = new Book("자바야 걱정마 내가 너를 자바줄게", 3, "오아람", "날아라출판사", 2011, 3, 8000, 0);

        //(3) 책 3권 추가
        bookRepository.newBook(book1);
        bookRepository.newBook(book2);
        bookRepository.newBook(book3);
    }

    public static void displayMenu() {
        System.out.println("===서점 메뉴===");
        System.out.println("1. 전체 장서 목록 보기");
        System.out.println("2. 도서 찾기");
        System.out.println("3. 도서 추가");
        System.out.println("4. 도서 판매하기");
        System.out.println("5. 도서 정보 수정하기");
        System.out.println("6. 출판사별 목록 보기");
        System.out.println("7. 재고별 목록 보기");
        System.out.println("8. 카테고리별 목록 보기");
        System.out.println("9. 도서 폐기");
        System.out.println("0. 종료");
        System.out.println("메뉴를 선택하세요.");
    }

    // 메뉴 선택 처리
    public static boolean processMenuChoice(int choice) {
        switch (choice) {
            case 1 -> showAllBooks();
            case 2 -> findBook();
            case 3 -> addNewBook();
            case 4 -> sellBook();
            case 5 -> updateBookInfo();
            case 6 -> showBooksByPublisher();
            case 7 -> showLowStockBooks();
            case 8 -> showBooksByCategory();
            case 9 -> removeBook();
            case 0 -> {
                System.out.println("프로그램을 종료합니다.");
                return false;
            }
            default -> System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
        }

        return true;
    }

    // 1. 전체 책 목록 보기
    public static void showAllBooks() {
        bookRepository.allBook();
    }

    // 2. 책 찾기
    public static void findBook() {
        try {
            System.out.println("어떤 방식으로 책을 찾으시겠습니까?");

            System.out.println("1. 책 번호로");
            System.out.println("2. 제목으로");
            System.out.print("번호 입력: ");
            int searchChoice = sc.nextInt();
            sc.nextLine();

            if (searchChoice == 1) {
                System.out.print("책번호: ");
                int bookNumber = sc.nextInt();
                sc.nextLine();

                Book foundBook = bookRepository.findBookByNum(bookNumber);
                foundBook.printInfo();
            } else if (searchChoice == 2) {
                System.out.print("책제목: ");
                String bookTitle = sc.nextLine();
                Book foundBook = bookRepository.findBookByTitle(bookTitle);
                foundBook.printInfo();
            } else {
                throw new IllegalArgumentException("1번 또는 2번만 선택 가능합니다.");
            }
        } catch(InputMismatchException e){
            System.out.println("잘못된 입력 형식입니다.");
        }
        catch(NoSuchElementException e){
            System.out.println("책을 찾을 수 없습니다.");
        }
        catch(Exception e){
            System.out.println("검색 중 오류가 발생했습니다. 다시 확인해주세요.");
        }

    }

    // 3. 새 책 추가
    public static void addNewBook() {
        boolean added = false;
        while (!added)
            try {
                System.out.println("===== 새 책 추가 =====");
                String noInput = "입력이 비어있습니다.";
                String newTitle = getStringInput("제목: ", noInput);
                int newBookNumber;
                while(true){
                    newBookNumber = getIntInput("책 번호", noInput, 0, 9999);
                    if(bookRepository.isBookNumberExists(newBookNumber)){
                        continue;
                    }
                    break;
                }

                String newAuthor = getStringInput("저자: ", noInput);
                String newPublisher = getStringInput("출판사: ", noInput);
                int newYear = getIntInput("출판년도: ", noInput,0, 9999);
                int newStock = getIntInput("초기물량: ", noInput, 0, 9999);
                int newPrice = getIntInput("가격: ", noInput, 0, 9999);


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
                int newCategoryCode = getIntInput("카테고리 코드(0-9): ", noInput, 0, 9);

                Book newBook = new Book(newTitle, newBookNumber, newAuthor, newPublisher, newYear, newStock, newPrice, newCategoryCode);
                bookRepository.newBook(newBook);
                added = true;
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력 형식입니다.");
            } catch (NoSuchElementException e) {
                System.out.println("입력 정보가 없습니다.");
            } catch (Exception e) {
                System.out.println("처리 중 오류가 발생했습니다. 다시 확인해주세요.");
            }
    }



    // 4. 책 판매하기
    public static void sellBook() {
        System.out.println("===== 책 판매 =====");
        System.out.print("판매할 책 번호: ");
       try {
           int sellBookNumber = sc.nextInt();
           sc.nextLine();

           Book bookToSell = bookRepository.findBookByNum(sellBookNumber);

           System.out.print("판매 수량: ");
           int sellQuantity = sc.nextInt();
           sc.nextLine();

           if (bookToSell.sellBook(sellQuantity)) {
               int totalPrice = bookToSell.calculateTotalPrice(sellQuantity);
               System.out.println("총 결제 금액: " + totalPrice + "원");
           }
       }
        catch (NoSuchElementException e){
               System.out.println("책을 찾을 수 없습니다.");
           }
       catch (Exception e){
           System.out.println("처리 중 오류가 발생하였습니다. 다시 확인해주세요.");
       }
       }



    // 5. 책 정보 수정하기
    public static void updateBookInfo() {
        System.out.println("===== 책 정보 수정 =====");
        try{
        System.out.print("수정할 책 번호: ");
        int updateBookNumber = sc.nextInt();
        sc.nextLine();

        if(updateBookNumber<=0){
            throw new IllegalArgumentException("책 번호는 양수여야 합니다.");
        }

        Book bookToUpdate = bookRepository.findBookByNum(updateBookNumber);

            System.out.println("현재 정보:");
            bookToUpdate.printInfo();

            System.out.println("수정할 항목을 선택하세요:");
            System.out.println("1. 제목");
            System.out.println("2. 저자");
            System.out.println("3. 가격");
            System.out.println("4. 카테고리");
            System.out.print("선택: ");
            int updateChoice = sc.nextInt();
            sc.nextLine();

            if(updateChoice <1 || updateChoice >4){
                throw new IllegalArgumentException("1~4 사이의 숫자를 입력해주세요.");
            }

            switch(updateChoice) {
                case 1 -> bookToUpdate.updateTitle();
                case 2 -> bookToUpdate.updateAuthor();
                case 3 -> bookToUpdate.updatePrice();
                case 4 -> bookToUpdate.updateCategory();
                default -> System.out.println("잘못된 선택입니다.");
            }
        }
        catch(InputMismatchException e){
            System.out.println("잘못된 입력 형식입니다.");
        }
        catch(NoSuchElementException e) {
            System.out.println("해당 번호의 책을 찾을 수 없습니다.");
        } catch(Exception e){
            System.out.println("처리 중 오류가 발생했습니다. 다시 확인해주세요.");
        }
    }


    // 6. 출판사별 도서 목록
    public static void showBooksByPublisher() {
        bookRepository.booksByPublisher();
    }

    // 7. 재고 부족 도서 확인
    public static void showLowStockBooks() {
        bookRepository.lowStockBooks();
    }

    // 8. 카테고리별 도서 목록
    public static void showBooksByCategory() {
        bookRepository.booksByCategory();
    }

    // 9. 책 삭제하기
    public static void removeBook() {
        try {
            System.out.println("===== 책 삭제 =====");
            System.out.print("삭제할 책 번호: ");
            int removeBookNumber = sc.nextInt();
            sc.nextLine(); // 버퍼 비우기

            bookRepository.removeBook(removeBookNumber);
        } catch(InputMismatchException e){
            System.out.println("잘못된 입력 형식입니다.");
        }
         catch(NoSuchElementException e) {
            System.out.println("해당 번호의 책을 찾을 수 없습니다.");
        } catch(Exception e){
            System.out.println("처리 중 오류가 발생했습니다. 다시 확인해주세요.");
        }
    }

    public static String getStringInput(String prompt, String errorMessage){
        while(true){
            System.out.println(prompt);
            String input = sc.nextLine();

            if(!input.isEmpty()){

                return input;

            }
            System.out.println(errorMessage);
        }
    }

    public static int getIntInput(String prompt, String errorMessage, int min, int max){
        while(true){
            System.out.println(prompt);
            String input = sc.nextLine();

            if(input.isEmpty()){
                System.out.println(errorMessage);
                continue;
            }
            try{
                int value = Integer.parseInt(input);

                if(value>=min && value<=max){
                    return value;
                }
            } catch (NumberFormatException e){
                System.out.println("올바른 숫자를 입력하세요");
            }
            catch (Exception e){
                System.out.println("처리 중 오류가 발생했습니다.");
            }
        }
    }
}



