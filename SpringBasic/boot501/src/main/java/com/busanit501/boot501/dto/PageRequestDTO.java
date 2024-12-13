package com.busanit501.boot501.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


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

    private String link;

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

    public String getLink() {
        if (link == null || link.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            builder.append("page=" + this.page);
            builder.append("&size=" + this.size);

            if (type != null && type.length() > 0) {
                builder.append("&types="+this.type);
            }

            // 검색 및 필터 조건 추가 하기.
            if(keyword != null) {
                try {
                    builder.append("&keyword="+ URLEncoder.encode(keyword, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                link = builder.toString();
            }
        }
        return link;
    }
}




