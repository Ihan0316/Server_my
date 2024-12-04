package com.busanit501.minitest.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FoodVO {
    private Long fno;
    private String foodName;
    private LocalDate dueDate;
    private boolean finished;
    private String chefName;
}