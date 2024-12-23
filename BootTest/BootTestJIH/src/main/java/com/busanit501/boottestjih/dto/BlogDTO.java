package com.busanit501.boottestjih.dto;

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
public class BlogDTO {
    private  Long blogno;
    @NotEmpty
    @Size(min = 3, max = 100)
    private  String title;

    @NotEmpty
    private  String content;

    @NotEmpty
    private  String writer;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
