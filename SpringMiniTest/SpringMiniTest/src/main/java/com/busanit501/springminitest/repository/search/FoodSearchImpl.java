package com.busanit501.springminitest.repository.search;

import com.busanit501.springminitest.domain.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class FoodSearchImpl extends QuerydslRepositorySupport implements FoodSearch {

    public FoodSearchImpl() {
        super(Food.class);
    }

    @Override
    public Page<Food> search(Pageable pageable) {
        return null;
    }
}
