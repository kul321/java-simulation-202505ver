package test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import project.BookStore.Book;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
    public Book book;

    @BeforeEach
    public void setUp() {
        // Scanner 의존성을 테스트하기 위해 표준 입력 복원
        System.setIn(System.in);
        // 각 테스트 전에 Book 객체 초기화
        book = new Book("테스트 책", 100, "테스트 저자", "테스트 출판사", 2023, 10, 20000, 5);
    }


    @Test
    public void testGetCategoryName() {
        // 기술과학(5) 카테고리 테스트
        assertEquals("기술과학", book.getCategoryName());

        // 문학(8) 카테고리 테스트
        book.category = 8;
        assertEquals("문학", book.getCategoryName());

        // 총류(0) 카테고리 테스트
        book.category = 0;
        assertEquals("총류", book.getCategoryName());

        // 기타 값은 총류로 처리됨
        book.category = 15;
        assertEquals("총류", book.getCategoryName());
    }

    @Test
    public void testAddBook() {
        // 초기 재고 확인
        assertEquals(10, book.stock);

        // 책 추가
        book.addBook(5);

        // 재고 증가 확인
        assertEquals(15, book.stock);
    }

    @Test
    public void testSellBook_Success() {
        // 충분한 재고가 있는 경우
        boolean result = book.sellBook(3);

        // 판매 성공 및 재고 감소 확인
        assertTrue(result);
        assertEquals(7, book.stock);
    }

    @Test
    public void testSellBook_InsufficientStock() {
        // 재고보다 많은 수량 판매 시도
        boolean result = book.sellBook(15);

        // 판매 실패 및 재고 유지 확인
        assertFalse(result);
        assertEquals(10, book.stock);
    }

    @Test
    public void testSellBook_ZeroStock() {
        // 모든 재고 판매
        boolean result = book.sellBook(10);

        // 판매 성공 확인
        assertTrue(result);
        // 재고가 0이 되면 자동으로 10권 추가됨
        assertEquals(10, book.stock);
    }

    @Test
    public void testCalculateTotalPrice() {
        // 결제 금액 계산 테스트
        int payment = book.calculateTotalPrice(3);
        assertEquals(60000, payment); // 20000 * 3 = 60000
    }

    @Test
    public void testUpdateTitle() {
        // 입력 시뮬레이션
        String input = "새로운 제목\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // updateTitle 메소드 호출 (Scanner가 새 입력을 읽음)
        book.updateTitle();

        // 제목 변경 확인
        assertEquals("새로운 제목", book.title);
    }

    @Test
    public void testUpdateAuthor() {
        // 입력 시뮬레이션
        String input = "새로운 저자\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // updateAuthor 메소드 호출
        book.updateAuthor();

        // 저자 변경 확인
        assertEquals("새로운 저자", book.author);
    }

}

