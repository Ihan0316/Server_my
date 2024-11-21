package com.busanit501.helloworld.book.Service;

import com.busanit501.helloworld.todo.DTO.TodoDTO;

import java.time.LocalDate;

public class BookService {
    public TodoDTO getOne(Long tno){
        // 실제로는 DB에서 받아와야함.
        // 더미 데이터 이용
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setTno(5L);
        todoDTO.setTitle("하나 조회 더미 데이터");
        todoDTO.setDueDate(LocalDate.now());
        return todoDTO;
    }
}
