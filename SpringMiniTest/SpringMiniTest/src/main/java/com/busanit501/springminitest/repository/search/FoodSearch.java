package com.busanit501.springminitest.repository.search;

import com.busanit501.springminitest.domain.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FoodSearch {
    // 테스트용
    Page<Food> search(Pageable pageable);

    Page<Food> searchAll(String[] types, String keyword, Pageable pageable);
}
