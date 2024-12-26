package com.busanit501.boot501.service;

import com.busanit501.boot501.domain.Board;
import com.busanit501.boot501.dto.*;

public interface BoardService {
    Long register(BoardDTO boardDTO);
    BoardDTO readOne(Long bno);
    void update(BoardDTO boardDTO);
    void delete(Long bno);
    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
    // 게시글에 댓글 갯수 포함한 메서드
    PageResponseDTO<BoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);
    // 게시글, 댓글, 이미지 첨부
    PageResponseDTO<BoardListAllDTO> listWithAll(PageRequestDTO pageRequestDTO);

    // 화면 (DTO)-> 디비(엔티티),
    default Board dtoToEntity(BoardDTO dto) {
        // 박스에서 꺼내서, 디비 타입(Entity) 변경.
        Board board = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();

        // 첨부 이미지들이 존재한다면, 꺼내서 담기.
        if(dto.getFileNames() != null) {
            dto.getFileNames().forEach(fileName -> {
                // 파일이름 형식 = {UUID}_{파일명}
                String[] arr = fileName.split("_");
                board.addImages(arr[0], arr[1]);
            });

        }
        return board;
    }

}
