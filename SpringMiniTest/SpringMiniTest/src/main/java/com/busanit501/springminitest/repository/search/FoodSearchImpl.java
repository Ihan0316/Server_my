package com.busanit501.springminitest.repository.search;

import com.busanit501.springminitest.domain.Food;
import com.busanit501.springminitest.domain.QFood;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class FoodSearchImpl extends QuerydslRepositorySupport implements FoodSearch {

    public FoodSearchImpl() {
        super(Food.class);
    }

    @Override
    public Page<Food> search(Pageable pageable) {
        // 예시,
        QFood qFood = QFood.food;
        JPQLQuery<Food> query = from(qFood);

        query.where(qFood.foodName.contains("3"));
        //----------------------------------- 조건
        List<Food> list = query.fetch();
        // 해당 조건의 데이터 갯수 조회
        Long total = query.fetchCount();
        // 해당 조건의 데이터 가져오기

        return null;
    }
}
