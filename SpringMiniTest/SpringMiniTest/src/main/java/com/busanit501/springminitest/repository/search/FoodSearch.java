package com.busanit501.springminitest.repository.search;

import com.busanit501.springminitest.domain.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FoodSearch {
    Page<Food> search(Pageable pageable);
}
