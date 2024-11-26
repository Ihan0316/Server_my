package com.busanit501.helloworld.JDBCex.vo;

import lombok.*;

import java.time.LocalDate;

// 롬복 사용하기
//@Getter
//@Setter
//@ToString
// 직접적인 데이터 베이스에 반영하는 클래스
// 모델클래스
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class TodoVO {
    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}