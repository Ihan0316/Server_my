package com.busanit501.helloworld.jdbcex;

import com.busanit501.helloworld.book.DTO.BookDTO;
import com.busanit501.helloworld.book.Service.BookService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

@Log4j2
public class BookServiceTest {
    private BookService bookService;

    @BeforeEach
    public void ready() {

        bookService = BookService.INSTANCE;
    }

    @Test
    public void testInsert() {
        try {
            BookDTO bookDTO = BookDTO.builder()
                    .title("샘플 작업 11/26")
                    .dueDate(LocalDate.now())
                    .build();

            bookService.register(bookDTO);
        } catch (SQLException e) {
            log.error("SQLException occurred while registering the book: ", e);
            fail("SQLException should not occur");
        }
    }

    @Test
    public void testSelectAll() throws SQLException {
        List<BookDTO> dtoList = bookService.listAll();
        for(BookDTO bookDTO:dtoList) {
            log.info(bookDTO);
        }
    }
}
