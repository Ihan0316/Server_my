package com.busanit501.boot501.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private  Long bno;
    @NotEmpty
    @Size(min = 3, max = 100)
    private  String title;

    @NotEmpty
    private  String content;

    @NotEmpty
    private  String writer;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    // 첨부 이미지 파일들 (파일 이름들)
    // uuid_파일명
    // 예시
    // 496b4eb8-1d1a-4c9d-bfd9-47d0a30c54bb_coding.jpg
    private List<String> fileNames;
}
