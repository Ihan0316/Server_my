package com.busanit501.minitest.service;

import com.busanit501.minitest.dto.FoodDTO;
import com.busanit501.minitest.dto.PageRequestDTO;
import com.busanit501.minitest.dto.PageResponseDTO;

import java.util.List;

public interface FoodService {
    void register(FoodDTO foodDTO);
    List<FoodDTO> getAll();
    FoodDTO getOne(Long fno);
    void delete(Long fno);
    void update(FoodDTO foodDTO);
    PageResponseDTO<FoodDTO> getListWithPage(PageRequestDTO pageRequestDTO);
}
