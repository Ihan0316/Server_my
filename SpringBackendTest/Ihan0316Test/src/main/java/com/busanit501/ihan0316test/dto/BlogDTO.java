package com.busanit501.ihan0316test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogDTO {
    private Long rno;
    @NotEmpty
    private String title;
    @Future
    private LocalDate dueDate;
    private boolean finished;
    @NotEmpty
    private String writer;
}
