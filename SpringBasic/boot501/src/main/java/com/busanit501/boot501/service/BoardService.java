package com.busanit501.boot501.service;

import com.busanit501.boot501.dto.BoardDTO;

public interface BoardService {
    Long register(BoardDTO boardDTO);
    BoardDTO readOne(Long bno);
        void update(BoardDTO boardDTO);
        void delete(Long bno);
}
