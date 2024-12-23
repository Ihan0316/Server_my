package com.busanit501.boottestjih.repository;

import com.busanit501.boottestjih.domain.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    // 기본적인 crud , 쿼리 스트링으로 가능함.

    // 댓글목록 조회 추가해보기.
    @Query("select r from Reply r where r.blog.blogno = :blogno")
    Page<Reply> listOfBlog(Long blogno, Pageable pageable);
}
