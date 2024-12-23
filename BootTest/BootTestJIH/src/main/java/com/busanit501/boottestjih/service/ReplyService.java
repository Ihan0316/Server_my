package com.busanit501.boottestjih.service;

import com.busanit501.boottestjih.dto.PageRequestDTO;
import com.busanit501.boottestjih.dto.PageResponseDTO;
import com.busanit501.boottestjih.dto.ReplyBlogDTO;

public interface ReplyService {
    Long register(ReplyBlogDTO replyBlogDTO);
    ReplyBlogDTO readOne(Long rno);
    void update(ReplyBlogDTO replyBlogDTO);
    void delete(Long rno);
    PageResponseDTO<ReplyBlogDTO> listWithReplyBlog(Long blogno, PageRequestDTO pageRequestDTO);
}
