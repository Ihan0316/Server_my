package com.busanit501.helloworld.jdbcex;

import com.busanit501.helloworld.JDBCex.dto.TodoDTO;
import com.busanit501.helloworld.JDBCex.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;

public class TodoServiceTest {
    private TodoService todoService;

    @BeforeEach
    public void ready() {
        todoService = TodoService.INSTANCE;
    }

    @Test
    public void testInsert() throws SQLException {
        // 더미 데이터 화면에서 전달 받은 TOdoDTO
        TodoDTO todoDTO = TodoDTO.builder()
                .title("샘플 작업 11/26")
                .dueDate(LocalDate.now())
                .build();

        todoService.register(todoDTO);
    }

}
