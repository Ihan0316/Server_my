package com.busanit501.boottestjih.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {
    private int page;
    private int size; // 페이징당 보여줄 갯수
    private int total;

    private int start;
    private int end;
    private boolean prev;
    private boolean next;

    private List<E> dtoList;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(List<E> dtoList, int total,
                           PageRequestDTO pageRequestDTO) {
        if(total <= 0){
            return;
        }

        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();
        this.total = total;
        this.dtoList = dtoList;

        this.end = ((int) Math.ceil(page / 10.0)) * 10;
        this.start = this.end - 9;
        int last = (int)(Math.ceil(total/10.0));
        this.end = end > last ? last :end;

        this.prev = this.start > 1;
        this.next = total > this.end * this.size;

    }
}
