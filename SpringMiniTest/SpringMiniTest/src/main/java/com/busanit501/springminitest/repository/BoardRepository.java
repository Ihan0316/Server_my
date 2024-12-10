package com.busanit501.springminitest.repository;

import com.busanit501.springminitest.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Food, Long> {
}