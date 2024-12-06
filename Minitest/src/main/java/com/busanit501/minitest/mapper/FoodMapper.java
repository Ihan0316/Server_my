package com.busanit501.minitest.mapper;

import com.busanit501.minitest.domain.FoodVO;
import com.busanit501.minitest.dto.PageRequestDTO;

import java.util.List;

// 맵퍼, sql 파일을 따로 관리
public interface FoodMapper {
    String getTime();

    void insert(FoodVO foodVO);

    List<FoodVO> selectAll();

    FoodVO selectOne(Long fno);

    void delete(Long fno);

    void update(FoodVO foodVO);

    List<FoodVO> selectList(PageRequestDTO pageRequestDTO);

    int getCount(PageRequestDTO pageRequestDTO);
}
