package com.busanit501.minitest.mapper;

import com.busanit501.minitest.domain.FoodVO;

import java.util.List;

// 맵퍼, sql 파일을 따로 관리
public interface FoodMapper {
    String getTime();

    void insert(FoodVO foodVO);

    List<FoodVO> selectAll();
}
