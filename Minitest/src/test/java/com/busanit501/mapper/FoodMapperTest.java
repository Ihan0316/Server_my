package com.busanit501.mapper;

import com.busanit501.minitest.domain.FoodVO;
import com.busanit501.minitest.dto.PageRequestDTO;
import com.busanit501.minitest.mapper.FoodMapper;
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
public class FoodMapperTest {

    @Autowired(required = false)
    private FoodMapper foodMapper;

    @Test
    public void testGetTime() {
        log.info("getTime : " + foodMapper.getTime());
    }

    @Test
    public void testInsert() {
        // 더미 데이터 만들어서 TodoVO에 담아서 진행
        FoodVO foodVO = FoodVO.builder()
                .foodName("샘플데이터")
                .dueDate(LocalDate.now())
                .chefName("조이한")
                .build();

        foodMapper.insert(foodVO);
    }

    @Test
    public void testSelectAll() {
        List<FoodVO> lists = foodMapper.selectAll();
        for(FoodVO foodVO:lists) {
            log.info(foodVO);
        }
    }

    @Test
    public void testSelectOne() {
        FoodVO foodVO = foodMapper.selectOne(4L);
        log.info("foodVO : " + foodVO);
    }

    @Test
    public void testDelete() {
        foodMapper.delete(6L);
    }

    @Test
    public void testUpdate() {
        // 업데이트 할 더미 데이터 필요, TodoVO
        FoodVO foodVO = FoodVO.builder()
                .fno(6L)
                .foodName("수정 제목")
                .dueDate(LocalDate.now())
                .finished(true)
                .build();

        foodMapper.update(foodVO);
    }

    // 페이징 처리해서 전체 조회
    @Test
    public void testSelectAllWithPage() {
        // 페이징 준비물을 담은 PageRequestDTO 필요함,
        // 더미로 PageRequestDTO 만들고,
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(2)
                .size(10)
                .build();

        List<FoodVO> list = foodMapper.selectList(pageRequestDTO);
        list.forEach(vo -> log.info("vo : " + vo));
    }

    // 페이징 처리해서 전체 갯수 조회
    @Test
    public void testGetCount() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();
        int total = foodMapper.getCount(pageRequestDTO);
        log.info("total : " + total);
    }
}
