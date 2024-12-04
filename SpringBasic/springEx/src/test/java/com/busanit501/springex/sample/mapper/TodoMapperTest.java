package com.busanit501.springex.sample.mapper;

import com.busanit501.springex.domain.TodoVO;
import com.busanit501.springex.mapper.TodoMapper;
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
public class TodoMapperTest {

    @Autowired(required = false)
    private TodoMapper todoMapper;

    @Test
    public void testGetTime() {
        log.info("getTime : " + todoMapper.getTime());
    }

    @Test
    public void testInsert() {
        // 더미 데이터 만들어서 TodoVO에 담아서 진행
        TodoVO todoVO = TodoVO.builder()
                .title("샘플데이터")
                .dueDate(LocalDate.now())
                .writer("조이한")
                .build();

        todoMapper.insert(todoVO);
    }

    @Test
    public void testSelectAll() {
        List<TodoVO> lists = todoMapper.selectAll();
        for(TodoVO todoVO:lists) {
            log.info(todoVO);
        }
    }

    @Test
    public void testSelectOne() {
        TodoVO todoVO = todoMapper.selectOne(4L);
        log.info("todoVO : " + todoVO);
    }

    @Test
    public void testDelete() {
        todoMapper.delete(6L);
    }
}
