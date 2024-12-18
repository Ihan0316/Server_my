package com.busanit501.boot501.service;

import com.busanit501.boot501.dto.ReplyDTO;

public interface ReplyService {
    Long register(ReplyDTO replyDTO);
    ReplyDTO readOne(Long rno);
    void update(ReplyDTO replyDTO);
    void delete(Long rno);
//    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
