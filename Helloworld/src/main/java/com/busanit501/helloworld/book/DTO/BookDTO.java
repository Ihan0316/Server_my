package com.busanit501.helloworld.book.DTO;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class BookDTO {
    private Long bno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}
