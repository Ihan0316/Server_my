package com.busanit501.springminitest.service;

import com.busanit501.springminitest.dto.ReplyDTO;

public interface ReplyService {
    Long register(ReplyDTO replyDTO);
    ReplyDTO readOne(Long rno);
    void update(ReplyDTO replyDTO);
    void delete(Long rno);
}
