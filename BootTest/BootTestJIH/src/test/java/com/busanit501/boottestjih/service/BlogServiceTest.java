package com.busanit501.boottestjih.service;

import com.busanit501.boottestjih.dto.BlogDTO;
import com.busanit501.boottestjih.dto.PageRequestDTO;
import com.busanit501.boottestjih.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@Log4j2
public class BlogServiceTest {

    @Autowired
    private BlogService blogService;

    @Test
    public void testRegisterBlog () {
        BlogDTO blogDTO = BlogDTO.builder()
                .title("test title")
                .writer("test writer")
                .content("test content")
                .regDate(LocalDateTime.now())
                .build();
        Long bno = blogService.register(blogDTO);

        log.info("blogService bno : " + bno.toString());
    }

    @Test
    public void testReadOneBlog () {
        Long bno = 102L;
        BlogDTO blogDTO = blogService.readOne(bno);
        log.info("blogService blogDTO 하나 조회 : " + blogDTO);
    }

    @Test
    public void testUpdateBlog () {
        Long bno = 95L;

        BlogDTO blogDTO = BlogDTO.builder()
                .bno(bno)
                .title("test edit title")
                .content("test edit content")
                .build();

        blogService.update(blogDTO);
    }

    @Test
    public void testDeleteBlog () {
        Long bno = 103L;

        blogService.delete(bno);
    }

    @Test
    public void testSelectAllBlog() {
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
}
