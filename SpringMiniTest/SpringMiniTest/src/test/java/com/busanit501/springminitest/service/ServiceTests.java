package com.busanit501.springminitest.service;

import com.busanit501.springminitest.dto.FoodDTO;
import com.busanit501.springminitest.dto.PageRequestDTO;
import com.busanit501.springminitest.dto.PageResponseDTO;
import com.busanit501.springminitest.dto.FoodListReplyCountDTO;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@Log4j2
@SpringBootTest
public class ServiceTests {
    @Autowired
    private FoodService boardService;

    @Test
    public void testRegisterFood() {
        // 더미 데이터 필요, 임시 DTO 생성.
        FoodDTO boardDTO = FoodDTO.builder()
                .foodName("오늘 점심 뭐 먹지?")
                .content("라면 먹어야지")
                .chefName("이상용")
                .regDate(LocalDateTime.now())
                .build();

        Long fno = boardService.register(boardDTO);
        log.info("입력한 게시글 번호: " + fno.toString());
    }

    @Test
    public void testSelectOneFood() {
        // 더미 데이터 필요, 임시 DTO 생성.
        Long fno = 103L;
        FoodDTO boardDTO= boardService.readOne(fno);
        log.info("testSelectOneFood , 하나 조회 boardDTO: " + boardDTO.toString());
    }

    @Test
    public void testUpdateFood() {
        // 수정할 더미 데이터 필요,
        FoodDTO boardDTO = FoodDTO.builder()
                .fno(103L)
                .foodName("수정한 오늘 점심 뭐 먹지?")
                .content("칼국수 먹을까?")
                .build();
        boardService.update(boardDTO);

    }

    @Test
    public void testDeleteFood() {
        boardService.delete(103L);
    }

    @Test
    public void testSelectAllFood() {
        // 검색할 더미 데이터
        // 준비물 1) PageRequestDTO, 키워드, 페이지, 사이즈 정보가 다 있음.
        PageRequestDTO pageRequestDTO =
                PageRequestDTO.builder()
                        .page(1)
                        .type("tcw")
                        .keyword("샘플")
                        .size(10)
                        .build();

        PageResponseDTO<FoodDTO> list = boardService.list(pageRequestDTO);
        log.info("list: " + list.toString());
    }

    @Test
    @Transactional
    public void testSelectAllFoodWithReplyCount() {
        // 검색할 더미 데이터
        // 준비물 1) PageRequestDTO, 키워드, 페이지, 사이즈 정보가 다 있음.
        PageRequestDTO pageRequestDTO =
                PageRequestDTO.builder()
                        .page(1)
                        .type("tcw")
                        .keyword("샘플")
                        .size(10)
                        .build();

        PageResponseDTO<FoodListReplyCountDTO> list = boardService.listWithReplyCount(pageRequestDTO);
        log.info("list: " + list.toString());
    }
}
