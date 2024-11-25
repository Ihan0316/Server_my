package com.busanit501.helloworld.book.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

// 롬복 사용하기

@Data
@Builder
public class BookVO {
    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}