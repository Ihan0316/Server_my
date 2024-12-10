package com.busanit501.boot501.repository;

import com.busanit501.boot501.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        IntStream.range(1,100).forEach(i -> {
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
        Long bno = 99L;
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
}
