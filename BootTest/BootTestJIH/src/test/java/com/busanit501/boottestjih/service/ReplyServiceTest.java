package com.busanit501.boottestjih.service;

import com.busanit501.boottestjih.dto.PageRequestDTO;
import com.busanit501.boottestjih.dto.PageResponseDTO;
import com.busanit501.boottestjih.dto.ReplyBlogDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@Log4j2
public class ReplyServiceTest {
    @Autowired
    private ReplyService replyService;

    @Test
//    @Transactional
    public void testRegisterReplyBlog() {
        // 더미 데이터 필요, 임시 DTO 생성.
        ReplyBlogDTO replyDTO = ReplyBlogDTO.builder()
                .replyText("오늘 점심 뭐 먹지?")
                .replyer("조이한")
                .blogno(106L)
                .regDate(LocalDateTime.now())
                .build();

        log.info("replyDTO, register 함수 호출 전 " + replyDTO);
        Long blogno = replyService.register(replyDTO);
        log.info("입력한 댓글 번호: " + blogno.toString());
    }

    @Test
    public void testReadReplyBlog() {
        ReplyBlogDTO replyDTO = replyService.readOne(9L);
        log.info("조회한 댓글 내용 "+replyDTO);

    }

    @Test
    public void testUpdateReplyBlog() {
        // 각자 디비에 따라 다름

        ReplyBlogDTO replyDTO = ReplyBlogDTO.builder()
                .replyText("수정 테스트 내용 변경?")
                .replyer("조이한")
                .rno(23L)
                .blogno(106L)
                .regDate(LocalDateTime.now())
                .build();

        log.info("replyDTO, register 함수 호출 전 " + replyDTO);
        replyService.update(replyDTO);
    }

    @Test
    public void testDeleteReplyBlog() {
        replyService.delete(9L);
    }

    @Test
    public void testReadAllReplyBlog() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();
        PageResponseDTO<ReplyBlogDTO> result = replyService.listWithReplyBlog(106L,pageRequestDTO);
        log.info("result : " + result);
    }
}
