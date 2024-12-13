package com.busanit501.springminitest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FoodDTO {
    private  Long fno;
    private  String foodName;
    private  String content;
    private  String chefName;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
