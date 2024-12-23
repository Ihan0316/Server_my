package com.busanit501.boottestjih.service;

import com.busanit501.boottestjih.dto.PageRequestDTO;
import com.busanit501.boottestjih.dto.PageResponseDTO;
import com.busanit501.boottestjih.dto.ReplyBlogDTO;

public interface ReplyService {
    Long register(ReplyBlogDTO replyBlogDTO);
    ReplyBlogDTO readOne(Long rno);
    void update(ReplyBlogDTO replyBlogDTO);
    void delete(Long rno);
    // 부모 게시글에 대한 댓글 목록 조회
    PageResponseDTO<ReplyBlogDTO> listWithReplyBlog(Long blogno, PageRequestDTO pageRequestDTO);
}
