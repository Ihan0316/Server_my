package com.busanit501.springex.sample.service;

import com.busanit501.springex.dto.TodoDTO;
import com.busanit501.springex.service.TodoService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class) //JUnit5 테스트 설정.
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
//@RequiredArgsConstructor
public class TodoServiceTest {
    // 방법1
    @Autowired
    private TodoService todoService;

    // 방법2
//    private final TodoService todoService;

    @Test
    public void testRegister() {
        TodoDTO todoDTO = TodoDTO.builder()
                .title("샘플 데이터 서비스 입력")
                .dueDate(LocalDate.now())
                .writer("조이한")
                .build();

    todoService.register(todoDTO);
    }

    @Test
    public void testGetAll() {
        List<TodoDTO> list = todoService.getAll();
        for (TodoDTO todoDTO : list) {
            log.info("todoDTO : " + todoDTO);
        }
    }
}
