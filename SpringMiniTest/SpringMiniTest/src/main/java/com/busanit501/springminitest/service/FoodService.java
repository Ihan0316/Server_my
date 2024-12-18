package com.busanit501.springminitest.service;

import com.busanit501.springminitest.dto.FoodDTO;
import com.busanit501.springminitest.dto.FoodListReplyCountDTO;
import com.busanit501.springminitest.dto.PageRequestDTO;
import com.busanit501.springminitest.dto.PageResponseDTO;


public interface FoodService {
    Long register(FoodDTO foodDTO);
    FoodDTO readOne(Long fno);
    void update(FoodDTO boardDTO);
    void delete(Long fno);
    PageResponseDTO<FoodDTO> list(PageRequestDTO pageRequestDTO);
    //게시글에 댓글 갯수 포함한 메서드
    PageResponseDTO<FoodListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);
}
