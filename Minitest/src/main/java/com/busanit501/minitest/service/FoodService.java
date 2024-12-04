package com.busanit501.minitest.service;

import com.busanit501.minitest.dto.FoodDTO;

import java.util.List;

public interface FoodService {
    void register(FoodDTO foodDTO);
    List<FoodDTO> getAll();
    FoodDTO getOne(Long fno);
    void delete(Long fno);
}
