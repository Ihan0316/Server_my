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

    //페이징한 전체 목록
    List<FoodVO> selectList(PageRequestDTO pageRequestDTO);

    // 페이징 위해서, 전체갯수,
    int getCount(PageRequestDTO pageRequestDTO);
}
