package com.busanit501.springminitest.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FoodListReplyCountDTO {
    private  Long fno;
    private  String foodName;
    private  String content;
    private  String chefName;
    private LocalDateTime regDate;

    // 댓글의 갯수
    private Long replyCount;
}
