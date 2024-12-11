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
public class FoodRepositoryTest {
    @Autowired
    private FoodRepository foodRepository;

    @Test
    public void testInsert() {

        IntStream.range(1,100).forEach(i -> {
            Food board = Food.builder()
                    .foodName("샘플 음식 : "+i)
                    .content("샘플 내용 : "+i)
                    .chefName("샘플 요리사 : "+i)
                    .build();
            Food result = foodRepository.save(board);
            log.info("추가된 fno 번호 : " + result);
        });
    }

    @Test
    public void testSelectOne() {
        Long fno = 90L;
        Optional<Food> result = foodRepository.findById(fno);
        Food food = result.orElseThrow();
        log.info("하나조회 : "+food);
    }

    @Test
    public void testSelectAll() {
        List<Food> result = foodRepository.findAll();
        for (Food food : result) {
            log.info(food);
        }
    }

    @Test
    public void testUpdate() {
        Long fno = 90L;
        Optional<Food> result = foodRepository.findById(fno);

        Food food = result.orElseThrow();
        food.changeTitleContent("변경 제목", "변경 내용");
        foodRepository.save(food);

    }

    @Test
    public void testDelete() {
        Long fno = 99L;
        foodRepository.deleteById(fno);
    }

    // 페이지는 0부터 시작
    @Test
    public void testPaging() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("fno").descending());
        Page<Food> result = foodRepository.findAll(pageable);
        log.info("result.getTotalElements()전체개수 : " +result.getTotalElements());
        log.info("result.getTotalPages()총페이지수 : " +result.getTotalPages());
        log.info("result.getContent() 페이징된 결과 10개 : "+result.getContent());
        log.info("result.getNumber() 현재 페이지 번호 : "+result.getNumber());
        log.info("result.getSize() 크기 : "+result.getSize());
    }

    // 테스트 쿼리스트링
    @Test
    public void testQueryString () {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("fno").descending());
        Page<Food> result = foodRepository.findByFoodNameContainingOrderByFnoDesc("3", pageable);

        log.info("result.getTotalElements()전체개수 : " +result.getTotalElements());
        log.info("result.getTotalPages()총페이지수 : " +result.getTotalPages());
        log.info("result.getContent() 페이징된 결과 10개 : "+result.getContent());
        log.info("result.getNumber() 현재 페이지 번호 : "+result.getNumber());
        log.info("result.getSize() 크기 : "+result.getSize());
    }

    @Test
    public void testQueryAnnotation () {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("fno").descending());
        Page<Food> result = foodRepository.findByKeyword("3", pageable);

        log.info("result.getTotalElements()전체개수 : " +result.getTotalElements());
        log.info("result.getTotalPages()총페이지수 : " +result.getTotalPages());
        log.info("result.getContent() 페이징된 결과 10개 : "+result.getContent());
        log.info("result.getNumber() 현재 페이지 번호 : "+result.getNumber());
        log.info("result.getSize() 크기 : "+result.getSize());
    }

    @Test
    public void testQuerydsl () {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("fno").descending());
        Page<Food> result = foodRepository.search(pageable);
        foodRepository.search(pageable);
    }

    @Test
    public void testQuerydsl2 () {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("fno").descending());

        String keyword = "샘플";
        String[] types = {"f", "ch", "c"};
        Page<Food> result = foodRepository.searchAll(types, keyword, pageable);

        log.info("result.getTotalElements()전체개수 : " +result.getTotalElements());
        log.info("result.getTotalPages()총페이지수 : " +result.getTotalPages());
        log.info("result.getContent() 페이징된 결과 10개 : " +result.getContent());
        log.info("result.getNumber() 현재 페이지 번호 : " +result.getNumber());
        log.info("result.getSize() 크기 : " +result.getSize());
        log.info("result.hasNext 다음 : " +result.hasNext());
        log.info("result.hasPrevious() 이전 : " +result.hasPrevious());
    }
}