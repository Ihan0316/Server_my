package com.busanit501.boot501.service;

import com.busanit501.boot501.dto.BoardDTO;
import com.busanit501.boot501.dto.PageRequestDTO;
import com.busanit501.boot501.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@Log4j2
public class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegisterBoard () {
        BoardDTO boardDTO = BoardDTO.builder()
                .title("test title")
                .writer("test writer")
                .content("test content")
                .regDate(LocalDateTime.now())
                .build();
        Long bno = boardService.register(boardDTO);

        log.info("boardService bno : " + bno.toString());
    }

    @Test
    public void testReadOneBoard () {
        Long bno = 103L;
        BoardDTO boardDTO = boardService.readOne(bno);
        log.info("boardService boardDTO 하나 조회 : " + boardDTO);
    }

    @Test
    public void testUpdateBoard () {
        Long bno = 103L;

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(bno)
                .title("test edit title")
                .content("test edit content")
                .build();

        boardService.update(boardDTO);
    }

    @Test
    public void testDeleteBoard () {
        Long bno = 103L;

        boardService.delete(bno);
    }

    @Test
    public void testSelectAllBoard() {
        PageRequestDTO pageRequestDTO =
                PageRequestDTO.builder()
                        .page(1)
                        .type("tcw")
                        .keyword("샘플")
                        .size(10)
                        .build();

        PageResponseDTO<BoardDTO> list = boardService.list(pageRequestDTO);
        log.info("list: " + list.toString());
    }
}
