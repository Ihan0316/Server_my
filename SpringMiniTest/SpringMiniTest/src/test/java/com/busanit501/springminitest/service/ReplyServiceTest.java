package com.busanit501.springminitest.service;

import com.busanit501.springminitest.dto.ReplyDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Log4j2
public class ReplyServiceTest {
    @Autowired
    private ReplyService replyService;

    @Test
    @Transactional
    public void testRegisterFood() {
        // 더미 데이터 필요, 임시 DTO 생성.
        ReplyDTO replyDTO = ReplyDTO.builder()
                .replyText("오늘 점심 뭐 먹지?")
                .replyer("조이한")
                .fno(200L)
                .regDate(LocalDateTime.now())
                .build();

        log.info("replyDTO, register 함수 호출 전 " + replyDTO);
        Long fno = replyService.register(replyDTO);
        log.info("입력한 댓글 번호: " + fno.toString());
    }

    @Test
    public void testReadReply() {
        ReplyDTO replyDTO = replyService.readOne(10L);
        log.info("조회한 댓글 내용 "+replyDTO);

    }

    @Test
    public void testUpdateReply() {
        // 각자 디비에 따라 다름

        ReplyDTO replyDTO = ReplyDTO.builder()
                .replyText("수정 테스트 내용 변경 중")
                .replyer("조이한")
                .rno(8L)
                .fno(200L)
                .regDate(LocalDateTime.now())
                .build();

        log.info("replyDTO, register 함수 호출 전 " + replyDTO);
        replyService.update(replyDTO);
    }

    @Test
    public void testDeleteReply() {
        replyService.delete(9L);
    }
}
