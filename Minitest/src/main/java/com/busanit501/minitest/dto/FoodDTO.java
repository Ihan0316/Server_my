package com.busanit501.minitest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodDTO {
    private Long fno;
    @NotEmpty
    private String foodName;
    @Future
    private LocalDate dueDate;
    private boolean finished;
    @NotEmpty
    private String chefName;
}
