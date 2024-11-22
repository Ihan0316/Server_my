package com.busanit501.helloworld.jdbcex;

import com.busanit501.helloworld.JDBCex.dao.BookDAO;
import com.busanit501.helloworld.JDBCex.dto.BookVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;

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
}
