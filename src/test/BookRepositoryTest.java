package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.BookStore.Book;
import project.BookStore.BookRepository;

import static org.junit.jupiter.api.Assertions.*;

public class BookRepositoryTest {
    public BookRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new BookRepository();

        // 테스트용 책 데이터 추가
        Book book1 = new Book("자바 프로그래밍", 1, "김자바", "코딩출판사", 2020, 5, 25000, 5);
        Book book2 = new Book("파이썬 기초", 2, "박파이썬", "프로그래밍출판사", 2021, 3, 18000, 5);
        Book book3 = new Book("소설 모음집", 3, "이소설", "문학출판사", 2019, 10, 15000, 8);

        repository.newBook(book1);
        repository.newBook(book2);
        repository.newBook(book3);
    }

    @Test
    public void testNewBook() {
        // 초기 크기 확인
        assertEquals(3, repository.books.size());

        // 새 책 추가
        Book newBook = new Book("새 책", 4, "새 저자", "새 출판사", 2023, 5, 20000, 1);
        repository.newBook(newBook);

        // 크기 증가 확인
        assertEquals(4, repository.books.size());
        // 추가된 책 확인
        assertEquals("새 책", repository.books.get(3).title);
    }

    @Test
    public void testFindBookByNum() {
        // 책 번호로 찾기 테스트
        Book foundBook = repository.findBookByNum(2);
        assertNotNull(foundBook);
        assertEquals("파이썬 기초", foundBook.title);

        // 존재하지 않는 번호로 찾기
        foundBook = repository.findBookByNum(99);
        assertNull(foundBook);
    }

    @Test
    public void testFindBookByTitle() {
        // 책 제목으로 찾기 테스트
        Book foundBook = repository.findBookByTitle("자바 프로그래밍");
        assertNotNull(foundBook);
        assertEquals(1, foundBook.bookNumber);

        // 존재하지 않는 제목으로 찾기
        foundBook = repository.findBookByTitle("존재하지 않는 책");
        assertNull(foundBook);
    }

    @Test
    public void testRemoveBook() {
        // 초기 크기 확인
        assertEquals(3, repository.books.size());

        // 책 삭제 테스트
        boolean result = repository.removeBook(2);
        assertTrue(result);

        // 크기 감소 확인
        assertEquals(2, repository.books.size());

        // 삭제 후 해당 책 찾기 시도
        Book foundBook = repository.findBookByNum(2);
        assertNull(foundBook);

        // 존재하지 않는 책 삭제 시도
        result = repository.removeBook(99);
        assertFalse(result);
        assertEquals(2, repository.books.size());
    }

    @Test
    public void testLowStockBooks() {
        // 재고가 1권 미만인 책이 없는 상태
        repository = new BookRepository();
        Book book1 = new Book("정상재고 책", 1, "저자", "출판사", 2023, 5, 15000, 0);
        repository.newBook(book1);

        // 재고 부족 책 추가
        Book book2 = new Book("품절 책", 2, "저자", "출판사", 2023, 0, 15000, 0);
        repository.newBook(book2);

        // 테스트 실행 (출력 확인은 어렵지만 최소한 오류가 없어야 함)
        repository.lowStockBooks();

        // 간접적으로 테스트: 품절 책의 재고가 0인지 확인
        assertEquals(0, repository.findBookByNum(2).stock);
    }

    @Test
    public void testBooksByCategory() {
        repository = new BookRepository();

        // 다양한 카테고리의 책 추가
        Book book1 = new Book("철학 책", 1, "저자1", "출판사", 2023, 5, 15000, 1); // 철학
        Book book2 = new Book("문학 책", 2, "저자2", "출판사", 2023, 5, 15000, 8); // 문학
        Book book3 = new Book("역사 책", 3, "저자3", "출판사", 2023, 5, 15000, 9); // 역사
        Book book4 = new Book("과학 책", 4, "저자4", "출판사", 2023, 5, 15000, 4); // 자연과학

        repository.newBook(book1);
        repository.newBook(book2);
        repository.newBook(book3);
        repository.newBook(book4);

        // 테스트 실행 (출력 확인은 어렵지만 최소한 오류가 없어야 함)
        repository.booksByCategory();

        // 간접적으로 테스트: 모든 책이 예상 카테고리를 가지고 있는지 확인
        assertEquals(1, repository.findBookByNum(1).category);
        assertEquals(8, repository.findBookByNum(2).category);
        assertEquals(9, repository.findBookByNum(3).category);
        assertEquals(4, repository.findBookByNum(4).category);
    }
}
