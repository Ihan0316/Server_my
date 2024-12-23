package com.busanit501.boottestjih.repository;

import com.busanit501.boottestjih.domain.Blog;
import com.busanit501.boottestjih.domain.Reply;
import com.busanit501.boottestjih.dto.BlogListReplyCountDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private BlogRepository blogRepository;

    // insert
    @Test
    public void testInsert() {
        Long blogno = 99L;

        Blog blog = Blog.builder().blogno(blogno).build();

        Reply reply = Reply.builder()
                .blog(blog)
                .replyText("샘플 댓글")
                .replyer("샘플 작성자")
                .build();

        replyRepository.save(reply);
    }

    // 반복문을 이용한 insert
    @Test
    public void testInsert2() {
        Long blogno = 100L;

        IntStream.range(1, 101).forEach(i -> {
            Blog blog = Blog.builder().blogno(blogno).build();
            Reply reply = Reply.builder()
                    .blog(blog)
                    .replyText("샘플 댓글"+i)
                    .replyer("샘플 작성자"+i)
                    .build();

            replyRepository.save(reply);
        });
    }

    // 게시글 표시에 댓글 갯수 추가해서 조회하기.
    @Test
    public void testSelectWithReplyCount() {
        Pageable pageable = PageRequest.of(0, 10,
                Sort.by("blogno").descending());

        // 1) 검색어, 2) 검색 유형
        String keyword = "오늘";
        String[] types = {"t","w","c"};

        Page<BlogListReplyCountDTO> result = blogRepository.searchWithReplyCount(types,keyword,pageable);

        log.info("result.getTotalElements()전체 갯수 :" +result.getTotalElements());
        log.info("result.getTotalPages()총페이지등 :" +result.getTotalPages());
        log.info("result.getContent() 페이징된 결과물 10개 :" +result.getContent());
        log.info("result.getNumber() 현재 페이지 번호 :" +result.getNumber());
        log.info("result.getSize() 크기  :" +result.getSize());
        log.info("result.hasNext() 다음  :" +result.hasNext());
        log.info("result.hasPrevious() 이전  :" +result.hasPrevious());
    }

    // 댓글 전체 조회
    @Test
    @Transactional
    public void testSelectReply() {
        Pageable pageable = PageRequest.of(0, 10,
                Sort.by("rno").descending());

        Page<Reply> result = replyRepository.listOfBlog(100L, pageable);
        log.info("result.getTotalElements()전체 갯수 :" +result.getTotalElements());
        log.info("result.getTotalPages()총페이지등 :" +result.getTotalPages());
        log.info("result.getContent() 페이징된 결과물 10개 :" +result.getContent());
        log.info("result.getNumber() 현재 페이지 번호 :" +result.getNumber());
        log.info("result.getSize() 크기  :" +result.getSize());
        log.info("result.hasNext() 다음  :" +result.hasNext());
        log.info("result.hasPrevious() 이전  :" +result.hasPrevious());
    }

    //댓글 하나 조회
    @Test
    @Transactional
    public void testSelectOneReply() {
        // rno 번호 찾기
        Optional<Reply> result = replyRepository.findById(100L);
        Reply reply = result.orElseThrow();
        log.info("댓글 하나 조회 결과" + reply);
    }

}






