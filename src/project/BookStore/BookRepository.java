package project.BookStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class BookRepository {
    public ArrayList<Book> books;

    public BookRepository(){
        this.books = new ArrayList<>();
    }

    public void newBook(Book book){
        if(isBookNumberExists(book.bookNumber)){
            throw new IllegalArgumentException("이미 존재하는 책 번호입니다.(중복도서일 수 있습니다.) 다시 확인해주세요.");
        }
        books.add(book);
        System.out.println("["+book.title + "] 책이 추가되었습니다.");
    }

    public void allBook(){
        System.out.println("===도서목록===");

        if(books.isEmpty()){
            System.out.println("보유 중인 도서가 없습니다.");
            return;
        }

        for(Book book : books){
            book.printInfo();
        }
    }

    public Book findBookByNum(int num){
        if(num<= 0){
            throw new IllegalArgumentException("책 번호는 양수여야 합니다.");
        }
        for(Book book : books){
            if(book.bookNumber == num){
                return book;
            }

        }

        throw new NoSuchElementException("책 번호 "+num+"에 해당하는 책을 찾을 수 없습니다.");
    }

    public Book findBookByTitle(String title){
        if(title == null ){
            throw new IllegalArgumentException("책 제목을 입력해주세요.");
        }

        for(Book book : books){
            if(book.title.toLowerCase().contains(title.toLowerCase())){
                return book;
            }
        }
        throw new NoSuchElementException("제목 "+title+"에 해당하는 책을 찾을 수 없습니다.");
    }

    // 추가 기능 - 출판사별 도서 목록 출력
    public void booksByPublisher() {
        System.out.println("===== 출판사별 도서 목록 =====");
        if(books.isEmpty()) {
            System.out.println("보유 중인 도서가 없습니다.");
            return;
        }

        // 분류
        HashMap<String, ArrayList<Book>> publisherMap = new HashMap<>();

        for(Book book : books) {
            if(!publisherMap.containsKey(book.publisher)) {
                publisherMap.put(book.publisher, new ArrayList<>());
            }
            publisherMap.get(book.publisher).add(book);
        }

        // 출력
        for(String publisher : publisherMap.keySet()) {
            System.out.println("[" + publisher + "] 출판 도서:");

            ArrayList<Book> publisherBooks = publisherMap.get(publisher);
            for(Book book : publisherBooks) {
                System.out.println("  - " + book.title + " (저자: " + book.author + ")");
            }
        }
    }

    // 추가 기능 - 재고 부족한 책 목록 (1권 미만)
    public void lowStockBooks() {
        System.out.println("===== 재고 부족 도서 (1권 미만) =====");
        boolean found = false;

        for(Book book : books) {
            if(book.stock < 1) {
                System.out.println("  - " + book.title + " (현재 재고: " + book.stock + "권)");
                found = true;
            }
        }

        if(!found) {
            System.out.println("재고가 부족한 도서가 없습니다.");
        }
    }

    // 추가 기능 - 카테고리별 도서 목록
    public void booksByCategory() {
        System.out.println("===== 카테고리별 도서 목록 =====");
        if(books.isEmpty()) {
            System.out.println("보유 중인 도서가 없습니다.");
            return;
        }

        // 카테고리별로 책 분류
        HashMap<Integer, ArrayList<Book>> categoryMap = new HashMap<>();

        for(Book book : books) {
            if(!categoryMap.containsKey(book.category)) {
                categoryMap.put(book.category, new ArrayList<>());
            }
            categoryMap.get(book.category).add(book);
        }

        // 출력 (순서대로)
        for(int i = 0; i <= 9; i++) {
            if(categoryMap.containsKey(i)) {
                Book tempBook = new Book("", 0, "", "", 0, 0,0,0);
                tempBook.category = i;
                System.out.println("\n[" + tempBook.getCategoryName() + " (" + i + ")] 카테고리 도서:");

                ArrayList<Book> categoryBooks = categoryMap.get(i);
                for(Book book : categoryBooks) {
                    System.out.println("  - " + book.title + " (저자: " + book.author + ")");
                }
            }
        }
    }

    // 추가 기능 - 책 폐기하기
    public boolean removeBook(int bookNumber) {
        Book bookToRemove = findBookByNum(bookNumber);
        if(bookToRemove != null) {
            books.remove(bookToRemove);
            System.out.println(bookToRemove.title + " 책이 목록에서 삭제되었습니다.");
            return true;
        }
        System.out.println("해당 번호의 책을 찾을 수 없습니다.");
        return false;
    }


    //중복 방지
    public boolean isBookNumberExists(int bookNumber){
        for(Book book : books){
            if(book.bookNumber == bookNumber){
                return true;
            }
        }
        return false;
    }

}
