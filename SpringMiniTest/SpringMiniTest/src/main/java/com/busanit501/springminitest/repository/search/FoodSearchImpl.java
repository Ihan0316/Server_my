package com.busanit501.springminitest.repository.search;

import com.busanit501.springminitest.domain.Food;
import com.busanit501.springminitest.domain.QFood;
import com.busanit501.springminitest.domain.QReply;
import com.busanit501.springminitest.dto.FoodListReplyCountDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
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
                        break;
                    case "ct":
                        booleanBuilder.or(qFood.content.contains(keyword));
                        break;
                    case "ch":
                        booleanBuilder.or(qFood.chefName.contains(keyword));
                        break;
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

    @Override
    public Page<FoodListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable) {
        QFood food = QFood.food;
        QReply reply = QReply.reply;
        JPQLQuery<Food> query = from(food);// select * from food
        query.leftJoin(reply).on(reply.food.fno.eq(food.fno));
        query.groupBy(food);

        if (types != null && types.length > 0 && keyword != null) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for (String type : types) {
                switch (type) {
                    case "t":
                        booleanBuilder.or(food.foodName.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(food.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(food.chefName.contains(keyword));
                        break;
                } // switch
            }// end for
            // where 조건을 적용해보기.
            query.where(booleanBuilder);
        } //end if
        // fno >0
        query.where(food.fno.gt(0L));

        JPQLQuery<FoodListReplyCountDTO> dtoQuery =
                query.select(Projections.bean(FoodListReplyCountDTO.class,
                        food.fno,
                        food.foodName,
                        food.content,
                        food.chefName,
                        food.regDate,
                        reply.count().as("replyCount")));

        this.getQuerydsl().applyPagination(pageable, dtoQuery);

        List<FoodListReplyCountDTO> list = dtoQuery.fetch();

        long total = dtoQuery.fetchCount();

        Page<FoodListReplyCountDTO> result = new PageImpl<FoodListReplyCountDTO>(list, pageable, total);

        return result;
    }
}
