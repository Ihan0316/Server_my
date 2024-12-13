package com.busanit501.boot501.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO {
    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;

    private String type;
    private String keyword;

    public String[] getTypes() {
        if (type == null || type.isEmpty()) {
            return null;
        } else {
            return type.split("");
        }
    }

    // 페이징 처리
    public Pageable getPageable(String ...props) {
        Pageable pageable = PageRequest.of(this.page-1, this.size, Sort.by(props).descending());
        return pageable;
    }
}




