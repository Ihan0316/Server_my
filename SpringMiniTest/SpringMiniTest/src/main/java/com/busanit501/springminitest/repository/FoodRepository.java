package com.busanit501.springminitest.repository;

import com.busanit501.springminitest.domain.Food;
import com.busanit501.springminitest.repository.search.FoodSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//public interface FoodRepository extends JpaRepository<Food, Long>, FoodSearch {
    public interface FoodRepository extends JpaRepository<Food, Long>{
    Page<Food> findByFoodNameContainingOrderByFnoDesc(String foodName, Pageable pageable);

    @Query("select f from Food f where f.foodName like concat('%',:keyword,'%')")
    Page<Food> findByKeyword(String keyword, Pageable pageable);
}