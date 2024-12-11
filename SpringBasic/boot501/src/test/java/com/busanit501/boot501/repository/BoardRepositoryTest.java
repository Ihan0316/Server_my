package com.busanit501.boot501.repository;

import com.busanit501.boot501.domain.Board;
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
public class BoardRepositoryTest {
    @Autowired
    // 아무 메서드가 없지만 기본 탑재된 쿼리 메서드를 이용해서 crud 해보기
    private BoardRepository boardRepository;

    @Test
    public void testInsert() {
        // 더미 데이터 넣기, 병렬 처리
        // stream 클래스 이용
        IntStream.range(1,2).forEach(i -> {
            Board board = Board.builder()
                    .title("샘플 제목 : "+i)
                    .content("샘플 내용 : "+i)
                    .writer("샘플 작성자 : "+i)
                    .build();
            // crud의 insert 랑 같다, 1차 임시 테이블에 저장을 하고 실제 테이블에 저장을 한다
            // save -> 예시
            // insert
            //    into
            //        board
            //        (content, mod_date, reg_date, title, writer)
            //    values
            //        (?, ?, ?, ?, ?)
            Board result = boardRepository.save(board);
            log.info("추가된 bno 번호 : " + result);
        });
    }

    @Test
    public void testSelectOne() {
        Long bno = 99L;
        // Optional 있으면 가져오기, 없으면 null
        // findByID 예시
        // select
        //        b1_0.bno,
        //        b1_0.content,
        //        b1_0.mod_date,
        //        b1_0.reg_date,
        //        b1_0.title,
        //        b1_0.writer
        //    from
        //        board b1_0
        //    where
        //        b1_0.bno=?
        Optional<Board> result = boardRepository.findById(bno);
        // result 있으면, Board 타입으로 받고, 없으면 예외발생
        Board board = result.orElseThrow();
        log.info("하나조회 : "+board);
    }

    @Test
    public void testSelectAll() {
        List<Board> result = boardRepository.findAll();
        for (Board board : result) {
            log.info(board);
        }
    }

    @Test
    public void testUpdate() {
        Long bno = 90L;
        // 수정할 데이터가 해당 테이블에 있는지 조회 먼저 함
        Optional<Board> result = boardRepository.findById(bno);
        // result 있으면, Board 타입으로 받고, 없으면 예외발생
        // board 엔티티 클래스 인스턴스가 하나의 데이터 베이스 내용
        Board board = result.orElseThrow();
        board.changeTitleContent("변경 제목", "변경 내용");
        // 실제 디비 테이블 반영
        // 순서 -> 1차 영속성 컨텍스트(임시 테이블) 적용 후 -> 실제 디비에 반영
        // save -> 해당 실제 테이블에 없다면 insert, 있다면, update 실행
        boardRepository.save(board);

    }

    @Test
    public void testDelete() {
        Long bno = 99L;
        boardRepository.deleteById(bno);
    }

    // 페이지는 0부터 시작
    @Test
    public void testPaging() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.findAll(pageable);
        log.info("result.getTotalElements()전체개수 : " +result.getTotalElements());
        log.info("result.getTotalPages()총페이지수 : " +result.getTotalPages());
        log.info("result.getContent() 페이징된 결과 10개 : "+result.getContent());
        log.info("result.getNumber() 현재 페이지 번호 : "+result.getNumber());
        log.info("result.getSize() 크기 : "+result.getSize());
    }

    // 테스트
    // 방법1. 쿼리스트링
    @Test
    public void testQueryString () {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.findByTitleContainingOrderByBnoDesc("3", pageable);

        log.info("result.getTotalElements()전체개수 : " +result.getTotalElements());
        log.info("result.getTotalPages()총페이지수 : " +result.getTotalPages());
        log.info("result.getContent() 페이징된 결과 10개 : "+result.getContent());
        log.info("result.getNumber() 현재 페이지 번호 : "+result.getNumber());
        log.info("result.getSize() 크기 : "+result.getSize());
    }

    // 방법2. @Query
    @Test
    public void testQueryAnnotation () {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.findByKeyword("3", pageable);

        log.info("result.getTotalElements()전체개수 : " +result.getTotalElements());
        log.info("result.getTotalPages()총페이지수 : " +result.getTotalPages());
        log.info("result.getContent() 페이징된 결과 10개 : "+result.getContent());
        log.info("result.getNumber() 현재 페이지 번호 : "+result.getNumber());
        log.info("result.getSize() 크기 : "+result.getSize());
    }

    // 방법3. querydsl
    @Test
    public void testQuerydsl () {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
//        Page<Board> result = boardRepository.search(pageable);
        boardRepository.search(pageable);

//        log.info("result.getTotalElements()전체개수 : " +result.getTotalElements());
//        log.info("result.getTotalPages()총페이지수 : " +result.getTotalPages());
//        log.info("result.getContent() 페이징된 결과 10개 : " +result.getContent());
//        log.info("result.getNumber() 현재 페이지 번호 : " +result.getNumber());
//        log.info("result.getSize() 크기 : " +result.getSize());
    }

    @Test
    public void testQuerydsl2 () {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        // 전달할 준비물
        // 1) 검색어
        String keyword = "3";
        // 2) 검색 유형
        String[] types = {"t", "w", "c"};
        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);

        log.info("result.getTotalElements()전체개수 : " +result.getTotalElements());
        log.info("result.getTotalPages()총페이지수 : " +result.getTotalPages());
        log.info("result.getContent() 페이징된 결과 10개 : " +result.getContent());
        log.info("result.getNumber() 현재 페이지 번호 : " +result.getNumber());
        log.info("result.getSize() 크기 : " +result.getSize());
        log.info("result.hasNext 다음 : " +result.hasNext());
        log.info("result.hasPrevious() 이전 : " +result.hasPrevious());
    }


}