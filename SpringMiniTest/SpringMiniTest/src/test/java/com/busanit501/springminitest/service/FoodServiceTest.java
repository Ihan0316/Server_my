package com.busanit501.springminitest.service;

import com.busanit501.springminitest.dto.FoodDTO;
import com.busanit501.springminitest.dto.PageRequestDTO;
import com.busanit501.springminitest.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@Log4j2
public class FoodServiceTest {

    @Autowired
    private FoodService foodService;

    @Test
    public void testRegisterFood () {
        FoodDTO foodDTO = FoodDTO.builder()
                .foodName("test foodName")
                .chefName("test chefName")
                .content("test content")
                .regDate(LocalDateTime.now())
                .build();
        Long fno = foodService.register(foodDTO);

        log.info("foodService fno : " + fno.toString());
    }

    @Test
    public void testReadOneFood () {
        Long fno = 200L;
        FoodDTO foodDTO = foodService.readOne(fno);
        log.info("foodService foodDTO 하나 조회 : " + foodDTO);
    }

    @Test
    public void testUpdateFood () {
        Long fno = 200L;

        FoodDTO foodDTO = FoodDTO.builder()
                .fno(fno)
                .foodName("test edit foodName")
                .content("test edit content")
                .build();

        foodService.update(foodDTO);
    }

    @Test
    public void testDeleteFood () {
        Long fno = 200L;

        foodService.delete(fno);
    }

    @Test
    public void testSelectAllFood() {
        PageRequestDTO pageRequestDTO =
                PageRequestDTO.builder()
                        .page(1)
                        .type("tcw")
                        .keyword("샘플")
                        .size(10)
                        .build();

        PageResponseDTO<FoodDTO> list = foodService.list(pageRequestDTO);
        log.info("list: " + list.toString());
    }
}
