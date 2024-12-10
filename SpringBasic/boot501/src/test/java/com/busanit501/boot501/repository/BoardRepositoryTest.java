package com.busanit501.boot501.repository;

import com.busanit501.boot501.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
            Board result = boardRepository.save(board);
            log.info("추가된 bno 번호 : " + result);
        });
    }
}
