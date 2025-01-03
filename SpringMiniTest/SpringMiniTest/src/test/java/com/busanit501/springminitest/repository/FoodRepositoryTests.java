package com.busanit501.springminitest.repository;

import com.busanit501.springminitest.domain.Food;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class FoodRepositoryTests {

    @Autowired
    // 아무 메소드가 없지만, 기본 탑재된 쿼리 메소드 이용해서, crud  해보기.
    private FoodRepository foodRepository;

    @Test
    public void testInsert() {
        // 더미 데이터, 앞에서, 병렬 처리 기능 사용하기.
        // stream 클래스 이용하기.
        // 1 ~ 99번까지 생성해요.
        IntStream.range(1, 101).forEach(i -> {
            Food food = Food.builder()
                    .foodName("샘플 음식 : " + i)
                    .content("샘플 내용 : " + i)
                    .chefName("샘플 요리사 : jih " + i)
                    .build();

            Food result = foodRepository.save(food);
            log.info("추가된 fno 번호 : " + result);
        });
    }

    @Test
    public void testSelectOne() {
        Long fno = 99L;
        //Optional , 있으면, 해당 인스턴스 가져오기, 없으면, null 입니다.
        Optional<Food> result = foodRepository.findById(fno);
        // result 있으면, Food 타입으로 받고, 없으면, 예외 발생시킴.
        Food food = result.orElseThrow();
        log.info("하나 조회 : " + food);

    }

    @Test
    public void testSelectAll() {

        List<Food> result = foodRepository.findAll();
        // result 있으면, Food 타입으로 받고, 없으면, 예외 발생시킴.
        for (Food food : result) {
            log.info(food);
        }


    }

    @Test
    public void testUpdate() {
        Long fno = 98L;
        // 수정 할 데이터가 해당 테이블에 있는지 조회 부터 하기.
        Optional<Food> result = foodRepository.findById(fno);
        // result 있으면, Food 타입으로 받고, 없으면, 예외 발생시킴.
        // food, 엔티티 클래스 인스턴스가, 하나의 데이터베이스의 내용임.
        Food food = result.orElseThrow();
        food.changeTitleContent("변경 음식 ", "변경 내용");
        // 실제 디비 테이블 반영.
        // 순서 -> 1차 영속성 컨텍스트(임시 테이블) 적용 -> 실제 테이블 반영.
        // save -> 해당 실제 테이블 없다면, -> insert
        // save -> 해당 실제 테이블 있다면, -> update

        foodRepository.save(food);

    }

    @Test
    public void testDelete() {
        Long fno = 99L;
        foodRepository.deleteById(fno);
    }

    @Test
    public void testPaging() {

        // 0 -> 1페이지, 1 -> 2페이지
        Pageable pageable = PageRequest.of(1, 10,
                Sort.by("fno").descending());
        Page<Food> result = foodRepository.findAll(pageable);
        log.info("result.getTotalElements()전체 갯수 :" + result.getTotalElements());
        log.info("result.getTotalPages()총페이지등 :" + result.getTotalPages());
        log.info("result.getContent() 페이징된 결과물 10개 :" + result.getContent());
        log.info("result.getNumber() 현재 페이지 번호 :" + result.getNumber());
        log.info("result.getSize() 크기  :" + result.getSize());
    }

    // 방법1 , 쿼리스트링
    @Test
    public void testQueryString() {
        Pageable pageable = PageRequest.of(1, 10,
                Sort.by("fno").descending());
        Page<Food> result = foodRepository.findByFoodNameContainingOrderByFnoDesc(
                "3", pageable
        );
        log.info("result.getTotalElements()전체 갯수 :" + result.getTotalElements());
        log.info("result.getTotalPages()총페이지등 :" + result.getTotalPages());
        log.info("result.getContent() 페이징된 결과물 10개 :" + result.getContent());
        log.info("result.getNumber() 현재 페이지 번호 :" + result.getNumber());
        log.info("result.getSize() 크기  :" + result.getSize());
    }

    // 방법2 , @Query
    @Test
    public void testQueryAnotation() {
        Pageable pageable = PageRequest.of(1, 10,
                Sort.by("fno").descending());
        Page<Food> result = foodRepository.findByKeyword("3", pageable);

        log.info("result.getTotalElements()전체 갯수 :" + result.getTotalElements());
        log.info("result.getTotalPages()총페이지등 :" + result.getTotalPages());
        log.info("result.getContent() 페이징된 결과물 10개 :" + result.getContent());
        log.info("result.getNumber() 현재 페이지 번호 :" + result.getNumber());
        log.info("result.getSize() 크기  :" + result.getSize());
    }

    // 방버3 Querydsl
    // 단계적으로, sql 문장만 일단 확인중. , 아직 메서드 완성 안됨.
    // 연습용
    @Test
    public void testQuerydsl() {
        Pageable pageable = PageRequest.of(1, 10,
                Sort.by("fno").descending());
//        Page<Food> result = foodRepository.search(pageable);
          foodRepository.search(pageable);

//        log.info("result.getTotalElements()전체 갯수 :" +result.getTotalElements());
//        log.info("result.getTotalPages()총페이지등 :" +result.getTotalPages());
//        log.info("result.getContent() 페이징된 결과물 10개 :" +result.getContent());
//        log.info("result.getNumber() 현재 페이지 번호 :" +result.getNumber());
//        log.info("result.getSize() 크기  :" +result.getSize());
    }

    @Test
    public void testQuerydsl2() {
        Pageable pageable = PageRequest.of(1, 10,
                Sort.by("fno").descending());

        // 전달할 준비물
        // 1) 검색어, 2) 검색 유형
        String keyword = "샘플";
        String[] types = {"f","ct","ch"};

        Page<Food> result = foodRepository.searchAll(types,keyword,pageable);

        log.info("result.getTotalElements()전체 갯수 :" +result.getTotalElements());
        log.info("result.getTotalPages()총페이지등 :" +result.getTotalPages());
        log.info("result.getContent() 페이징된 결과물 10개 :" +result.getContent());
        log.info("result.getNumber() 현재 페이지 번호 :" +result.getNumber());
        log.info("result.getSize() 크기  :" +result.getSize());
        log.info("result.hasNext() 다음  :" +result.hasNext());
        log.info("result.hasPrevious() 이전  :" +result.hasPrevious());
    }


}
