package com.busanit501.springminitest.service;

import com.busanit501.springminitest.dto.PageRequestDTO;
import com.busanit501.springminitest.dto.PageResponseDTO;
import com.busanit501.springminitest.dto.ReplyDTO;

public interface ReplyService {
    Long register(ReplyDTO replyDTO);
    ReplyDTO readOne(Long rno);
    void update(ReplyDTO replyDTO);
    void delete(Long rno);
    // 부모 게시글에 대한 댓글 목록 조회
    PageResponseDTO<ReplyDTO> listWithReply(Long fno, PageRequestDTO pageRequestDTO);
}
