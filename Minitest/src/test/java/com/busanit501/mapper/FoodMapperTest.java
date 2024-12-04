package com.busanit501.mapper;

import com.busanit501.minitest.domain.FoodVO;
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
}
