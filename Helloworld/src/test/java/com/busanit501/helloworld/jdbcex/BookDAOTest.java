package com.busanit501.helloworld.jdbcex;

import com.busanit501.helloworld.book.DAO.BookDAO;
import com.busanit501.helloworld.book.VO.BookVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class BookDAOTest {
    private BookDAO bookDAO;

    // 아래에 각 단위 테스트가 실행되기전에, 먼저 실행을 함.
    @BeforeEach
    public void ready() {
        bookDAO = new BookDAO();
    }

    // DB에 데이터 추가
    @Test
    public void insertTest() throws Exception{
        Long bno = 2L;
        BookVO bookVO1 = BookVO.builder().title("책1").dueDate(LocalDate.now()).bno(bno).build();
        bookDAO.insertBook(bookVO1);
    }

    // 전체 조회 테스트
    @Test
    public void testList() throws SQLException {
        List<BookVO> list = bookDAO.selectAllBook();
        list.forEach(vo -> System.out.println(vo));
    }

    // 하나 조회 테스트
    @Test
    public void getOneTest() throws SQLException {
        Long bno = 3L;
        BookVO bookVO = bookDAO.selectOne(bno);
        System.out.println(bookVO);
    }

    // 하나 삭제 테스트
    @Test
    public void deleteTest() throws SQLException {
        Long bno = 2L;
        bookDAO.deleteBook(bno);
    }

    // 수정 테스트
    @Test
    public void updateTest() throws SQLException {
        Long bno = 3L;
        BookVO bookVO = BookVO.builder()
                .bno(bno)
                .title("수정 테스트 중")
                .finished(true)
                .dueDate(LocalDate.of(2024, 11, 25))
                .build();
        bookDAO.updateBook(bookVO);
    }
}
