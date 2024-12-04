package com.busanit501.springex.mapper;

import com.busanit501.springex.domain.TodoVO;

import java.util.List;

// 맵퍼, sql 파일을 따로 관리
public interface TodoMapper {
    String getTime();

    void insert(TodoVO todoVO);

    List<TodoVO> selectAll();
}
