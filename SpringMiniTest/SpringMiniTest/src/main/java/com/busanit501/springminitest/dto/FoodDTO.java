package com.busanit501.springminitest.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
    @NotEmpty
    @Size(min = 3, max = 100)
    private  String foodName;

    @NotEmpty
    private  String content;

    @NotEmpty
    private  String chefName;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
