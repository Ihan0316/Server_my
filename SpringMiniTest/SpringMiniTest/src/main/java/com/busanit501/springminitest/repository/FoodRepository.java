package com.busanit501.springminitest.repository;

import com.busanit501.springminitest.domain.Food;
import com.busanit501.springminitest.repository.search.FoodSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long>, FoodSearch {
}