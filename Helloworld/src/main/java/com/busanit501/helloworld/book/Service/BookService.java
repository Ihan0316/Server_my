package com.busanit501.helloworld.book.Service;

import com.busanit501.helloworld.book.DTO.BookDTO;
import com.busanit501.helloworld.todo.DTO.TodoDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum BookService {
    INSTANCE;
    public BookDTO getOne(Long tno){
        // 실제로는 DB에서 받아와야함.
        // 더미 데이터 이용
        BookDTO bookDTO = new BookDTO();
        bookDTO.setFno(5L);
        bookDTO.setTitle("하나 조회 더미 데이터");
        bookDTO.setDueDate(LocalDate.now());
        return bookDTO;
    }
    public List<BookDTO> getList() {
        List<BookDTO> bookList = IntStream.range(0,10).mapToObj(
                i -> {
                    // 10 반복 해서, 더미 인스턴스 10개 생성,
                    BookDTO bookDTO = new BookDTO();
                    bookDTO.setTitle("테스트AA " + i);
                    bookDTO.setFno((long) i);
                    bookDTO.setDueDate(LocalDate.now());
                    return  bookDTO;
                }).collect(Collectors.toList());
        return bookList;
    }


}
