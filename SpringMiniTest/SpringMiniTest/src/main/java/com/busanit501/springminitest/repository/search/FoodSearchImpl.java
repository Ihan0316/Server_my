package com.busanit501.springminitest.repository.search;

import com.busanit501.springminitest.domain.Food;
import com.busanit501.springminitest.domain.QFood;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class FoodSearchImpl extends QuerydslRepositorySupport implements FoodSearch {

    public FoodSearchImpl() {
        super(Food.class);
    }

    @Override

    public Page<Food> search(Pageable pageable) {

        QFood qFood = QFood.food;
        JPQLQuery<Food> query = from(qFood);

        query.where(qFood.foodName.contains("3"));

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.or(qFood.foodName.contains("3"));
        booleanBuilder.or(qFood.content.contains("7"));

        query.where(booleanBuilder);

        query.where(qFood.fno.gt(0L));

        this.getQuerydsl().applyPagination(pageable, query);

        List<Food> list = query.fetch();

        Long total = query.fetchCount();

        return null;
    }

    @Override
    public Page<Food> searchAll(String[] types, String keyword, Pageable pageable) {
        QFood qFood = QFood.food;
        JPQLQuery<Food> query = from(qFood);
        if (types != null && types.length > 0 && keyword != null) {

            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for (String type : types) {
                switch (type) {
                    case "f":
                        booleanBuilder.or(qFood.foodName.contains(keyword));
                    case "ct":
                        booleanBuilder.or(qFood.content.contains(keyword));
                    case "ch":
                        booleanBuilder.or(qFood.chefName.contains(keyword));
                } // switch
            }// end for
            query.where(booleanBuilder);
        } //end if

        query.where(qFood.fno.gt(0L));

        this.getQuerydsl().applyPagination(pageable, query);

        List<Food> list = query.fetch();

        long total = query.fetchCount();

        Page<Food> result = new PageImpl<Food>(list, pageable, total);

        return result;
    }
}
