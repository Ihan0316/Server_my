package com.busanit501.helloworld.book.service;

import com.busanit501.helloworld.book.dto.BookDTO;

import java.time.LocalDate;

public enum BookService {
    INSTANCE;
    public BookDTO getOne(Long tno){
        // 실제로는 DB에서 받아와야함.
        // 더미 데이터 이용
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTno(5L);
        bookDTO.setTitle("하나 조회 더미 데이터");
        bookDTO.setDueDate(LocalDate.now());
        return bookDTO;
    }
}
