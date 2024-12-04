package com.busanit501.service;

import com.busanit501.minitest.dto.FoodDTO;
import com.busanit501.minitest.service.FoodService;
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
public class FoodServiceTest {
    @Autowired
    private FoodService foodService;

    @Test
    public void testRegister() {
        FoodDTO foodDTO = FoodDTO.builder()
                .foodName("샘플 데이터 서비스 입력")
                .dueDate(LocalDate.now())
                .chefName("조이한")
                .build();

        foodService.register(foodDTO);
    }

    @Test
    public void testGetAll() {
        List<FoodDTO> list = foodService.getAll();
        for (FoodDTO foodDTO : list) {
            log.info("foodDTO : " + foodDTO);
        }
    }

    @Test
    public void testGetOne() {
        FoodDTO foodDTO = foodService.getOne(4L);
        log.info(foodDTO);
    }

    @Test
    public void testDelete() {
        foodService.delete(4L);
    }
}
