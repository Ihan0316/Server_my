package com.busanit501.helloworld.book.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    // 게터/세터/toString, 재정의.
    // 자바, 소스 -> 반자동으로 -> 롬복 라이브러리 사용법,

}
