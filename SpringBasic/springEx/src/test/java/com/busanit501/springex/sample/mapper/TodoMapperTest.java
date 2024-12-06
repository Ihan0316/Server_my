package com.busanit501.springex.sample.mapper;

import com.busanit501.springex.domain.TodoVO;
import com.busanit501.springex.dto.PageRequestDTO;
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

    @Test
    public void testUpdate() {
        // 업데이트 할 더미 데이터 필요, TodoVO
        TodoVO todoVO = TodoVO.builder()
                .tno(6L)
                .title("수정 제목")
                .dueDate(LocalDate.now())
                .finished(true)
                .build();

        todoMapper.update(todoVO);
    }

    // 페이징 처리해서 전체 조회
    @Test
    public void testSelectAllWithPage() {
        // 페이징 준비물을 담은 PageRequestDTO 필요함,
        // 더미로 PageRequestDTO 만들고,
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        List<TodoVO> list = todoMapper.selectList(pageRequestDTO);
        list.forEach(vo -> log.info("vo : " + vo));
    }

    @Test
    public void testGetCount() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();
        int total = todoMapper.getCount(pageRequestDTO);
        log.info("total : " + total);
    }
}
