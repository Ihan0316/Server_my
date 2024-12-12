package com.busanit501.ihan0316test.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BlogVO {
    private Long rno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
    private String writer;
}