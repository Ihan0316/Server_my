package com.busanit501.helloworld.jdbcex;

import com.busanit501.helloworld.JDBCex.dto.TodoVO;
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

    @Test
    public void insertTest() throws Exception{
        BookVO bookVO1 = BookVO.builder().title("책1").dueDate(LocalDate.of(2024, 12, 31)).build();
        bookDAO.insert(bookVO1);
    }

    @Test
    public void testList() throws SQLException {
        List<BookVO> list = bookDAO.selectAllBook();
        list.forEach(vo -> System.out.println(vo));
    }

    // 하나조회 테스트
    @Test
    public void getOneTest() throws SQLException {
        Long tno = 3L;
        BookVO bookVO = bookDAO.selectOne(tno);
        System.out.println(bookVO);
    }
}
