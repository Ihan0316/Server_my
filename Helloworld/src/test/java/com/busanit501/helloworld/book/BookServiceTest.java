package com.busanit501.helloworld.book;

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
    public void testInsert() throws SQLException {
        // 더미 데이터 화면에서 전달 받은 TOdoDTO
        BookDTO bookDTO = BookDTO.builder()
                .title("샘플 작업 11/26")
                .dueDate(LocalDate.now())
                .build();

        bookService.register(bookDTO);
    }

    @Test
    public void testSelectAll() throws SQLException {
        List<BookDTO> dtoList = bookService.listAll();
        for(BookDTO bookDTO:dtoList) {
            log.info(bookDTO);
        }
    }

    @Test
    public void testSelectOne() throws SQLException {
        BookDTO bookDTO = bookService.get(3L);
        log.info(bookDTO);
    }
}
