package com.busanit501.helloworld.book.VO;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class BookVO {
    private Long bno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}