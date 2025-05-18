package project.BookStore;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class BookMainTest {
    public final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    public final PrintStream originalOut = System.out;
    public final InputStream originalIn = System.in;

    @BeforeEach
    public void setUpStreams() {
        // 출력 리다이렉션
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        // 입출력 복원
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void testInitializeData() {
        // BookRepository 초기화
        Main.bookRepository = new BookRepository();

        // 초기 데이터 추가
        Main.initializeData();

        // 3권의 책이 추가되었는지 확인
        assertEquals(3, Main.bookRepository.books.size());

        // 도서명 확인
        assertEquals("파이썬을 배워라! 당신의 파이가 늘어난다", Main.bookRepository.books.get(0).title);
        assertEquals("취미로 한 프로그래밍, 나의 주수입원이 되다", Main.bookRepository.books.get(1).title);
        assertEquals("자바야 걱정마 내가 너를 자바줄게", Main.bookRepository.books.get(2).title);
    }

    @Test
    public void testDisplayMenu() {
        // 메뉴 표시
        Main.displayMenu();

        // 출력 내용 확인
        String output = outContent.toString();
        assertTrue(output.contains("===서점 메뉴==="));
        assertTrue(output.contains("1. 전체 장서 목록 보기"));
        assertTrue(output.contains("0. 종료"));
    }

    @Test
    public void testProcessMenuChoice_Exit() {
        // 종료 옵션 테스트
        boolean result = Main.processMenuChoice(0);

        // 프로그램 종료 확인
        assertFalse(result);

        // 출력 메시지 확인
        String output = outContent.toString();
        assertTrue(output.contains("프로그램을 종료합니다"));
    }

    @Test
    public void testProcessMenuChoice_InvalidOption() {
        // 유효하지 않은 옵션 테스트
        boolean result = Main.processMenuChoice(99);

        // 프로그램 계속 실행
        assertTrue(result);

        // 출력 메시지 확인
        String output = outContent.toString();
        assertTrue(output.contains("잘못된 선택입니다"));
    }

    @Test
    public void testShowAllBooks() {
        // BookRepository 초기화 및 데이터 추가
        Main.bookRepository = new BookRepository();
        Main.initializeData();

        // 모든 책 표시
        Main.showAllBooks();

        // 출력 확인
        String output = outContent.toString();
        assertTrue(output.contains("===도서목록==="));
        assertTrue(output.contains("파이썬을 배워라!"));
        assertTrue(output.contains("취미로 한 프로그래밍"));
        assertTrue(output.contains("자바야 걱정마"));
    }

    @Test
    public void testTeacherRequirements() {
        // BookRepository 초기화
        Main.bookRepository = new BookRepository();
        Main.initializeData();

        // (1) 새로운 책 추가
        Book newBook = new Book("소소한 프로그래밍", 4, "미나킴", "미미출판사", 2022, 10, 18000, 5);
        Main.bookRepository.newBook(newBook);

        // (2) 5명의 손님이 각각 1권씩 구매
        for (int i = 0; i < 5; i++) {
            Main.bookRepository.findBookByNum(4).sellBook(1);
        }

        // (3) 25권 추가 주문
        Main.bookRepository.findBookByNum(4).addBook(25);

        // (4) 프로그래밍 관련 책 검색
        Book foundBook = Main.bookRepository.findBookByTitle("프로그래밍");
        assertNotNull(foundBook);

        // (5) 전체 목록에 새 책 포함 확인
        boolean found = false;
        for (Book book : Main.bookRepository.books) {
            if (book.title.equals("소소한 프로그래밍")) {
                found = true;
                break;
            }
        }
        assertTrue(found);

        // 추가 검증: 재고가 30권(원래 10권 - 5권 판매 + 25권 추가)인지 확인
        assertEquals(30, Main.bookRepository.findBookByNum(4).stock);
    }
}