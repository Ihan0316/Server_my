package com.busanit501.boottestjih.service;

import com.busanit501.boottestjih.dto.BlogDTO;
import com.busanit501.boottestjih.dto.BlogListReplyCountDTO;
import com.busanit501.boottestjih.dto.PageRequestDTO;
import com.busanit501.boottestjih.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Log4j2
@SpringBootTest
public class ServiceTests {
    @Autowired
    private BlogService blogService;

    @Test
    public void testRegisterBlog() {
        // 더미 데이터 필요, 임시 DTO 생성.
        BlogDTO blogDTO = BlogDTO.builder()
                .title("오늘 점심 뭐 먹지?")
                .content("라면 먹어야지")
                .writer("이상용")
                .regDate(LocalDateTime.now())
                .build();

        Long bno = blogService.register(blogDTO);
        log.info("입력한 게시글 번호: " + bno.toString());
    }

    @Test
    public void testSelectOneBlog() {
        // 더미 데이터 필요, 임시 DTO 생성.
        Long bno = 103L;
        BlogDTO blogDTO= blogService.readOne(bno);
        log.info("testSelectOneBlog , 하나 조회 blogDTO: " + blogDTO.toString());
    }

    @Test
    public void testUpdateBlog() {
        // 수정할 더미 데이터 필요,
        BlogDTO blogDTO = BlogDTO.builder()
                .bno(103L)
                .title("수정한 오늘 점심 뭐 먹지?")
                .content("칼국수 먹을까?")
                .build();
        blogService.update(blogDTO);

    }

    @Test
    public void testDeleteBlog() {
        blogService.delete(103L);
    }

    @Test
    public void testSelectAllBlog() {
        // 검색할 더미 데이터
        // 준비물 1) PageRequestDTO, 키워드, 페이지, 사이즈 정보가 다 있음.
        PageRequestDTO pageRequestDTO =
                PageRequestDTO.builder()
                        .page(1)
                        .type("tcw")
                        .keyword("샘플")
                        .size(10)
                        .build();

        PageResponseDTO<BlogDTO> list = blogService.list(pageRequestDTO);
        log.info("list: " + list.toString());
    }

    @Test
    @Transactional
    public void testSelectAllBlogWithReplyCount() {
        // 검색할 더미 데이터
        // 준비물 1) PageRequestDTO, 키워드, 페이지, 사이즈 정보가 다 있음.
        PageRequestDTO pageRequestDTO =
                PageRequestDTO.builder()
                        .page(1)
                        .type("tcw")
                        .keyword("샘플")
                        .size(10)
                        .build();

        PageResponseDTO<BlogListReplyCountDTO> list = blogService.listWithReplyCount(pageRequestDTO);
        log.info("list: " + list.toString());
    }
}
