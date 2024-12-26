package com.busanit501.boot501.service;

import com.busanit501.boot501.domain.Board;
import com.busanit501.boot501.domain.Reply;
import com.busanit501.boot501.dto.*;
import com.busanit501.boot501.repository.BoardRepository;
import com.busanit501.boot501.repository.ReplyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {

    //맵퍼에게 의존 해야함.
    // 디비 작업 도구,
    private final BoardRepository boardRepository;

    // 지원 받기, 댓글의 디비 작업이 가능한,
    private final ReplyRepository replyRepository;
    // DTO <-> Entity class
    private final ModelMapper modelMapper;

    // 게시글만 첨부된 내용
//    @Override
//    public Long register(BoardDTO boardDTO) {
//        Board board = modelMapper.map(boardDTO, Board.class);
//        Long bno = boardRepository.save(board).getBno();
//        return bno;
//    }

    // 게시글만 첨부된 내용+첨부 이미지 추가
    @Override
    public Long register(BoardDTO boardDTO) {
        // 교체 작업
//        Board board = modelMapper.map(boardDTO, Board.class);
        Board board = dtoToEntity(boardDTO);
        Long bno = boardRepository.save(board).getBno();
        return bno;
    }

    @Override
    public BoardDTO readOne(Long bno) {
        Optional<Board> result = boardRepository.findById(bno);
        Board board= result.orElseThrow();
        // 첨부 이미지 추가 버전
//        BoardDTO dto = modelMapper.map(board, BoardDTO.class);
        BoardDTO dto = entityToDto(board);
        return dto;
    }

    @Override
    // 첨부 이미지 추가 버전으로 수정
    public void update(BoardDTO boardDTO) {
        Optional<Board> result = boardRepository.findById(boardDTO.getBno());
        Board board = result.orElseThrow();
        board.changeTitleContent(boardDTO.getTitle(),boardDTO.getContent());

        // 첨부 이미지 처리 로직
        // 기존 내용 다 삭제 후 첨부 내용 새로 업데이트하는 방식
        board.clearImages();

        // 게시글 수정시, 첨부된 이미지 존재 -> 교체
        if (boardDTO.getFileNames() != null) {
            for (String fileName : boardDTO.getFileNames()) {
                String[] arr = fileName.split("_");
                board.addImages(arr[0], arr[1]);
            }
        }

        boardRepository.save(board);
    }

    @Override
    public void delete(Long bno) {
        // 첨부 이미지 추가된 삭제 버전
        // 댓글 존재 여부 확인 후, 있다면, 삭제
        // 없다면, 기존 게시글 삭제 -> 자동 이미지 삭제
        // replyRepository의 지원을 받아야 한다
        List<Reply> result = replyRepository.findByBoardBno(bno);
        boolean checkReply = result.isEmpty() ? false : true;
        if(checkReply) {
            replyRepository.deleteByBoard_Bno(bno);
        }

        // 게시글만 삭제
        boardRepository.deleteById(bno);
    }



    @Override
    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("bno");

        Page<Board> result = boardRepository.searchAll(types,keyword,pageable);
        // list -> PageResponseDTO 타입으로 변경 필요.

        // result.getContent() -> 페이징된 엔티티 클래스 목록
        List<BoardDTO> dtoList = result.getContent().stream()
                .map(board ->modelMapper.map(board, BoardDTO.class))
                .collect(Collectors.toList());


        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();

    } // list

    @Override
    public PageResponseDTO<BoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO) {

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("bno");

        // 수정1
        Page<BoardListReplyCountDTO> result = boardRepository.searchWithReplyCount(types,keyword,pageable);
        // list -> PageResponseDTO 타입으로 변경 필요.

        // result.getContent() -> 페이징된 엔티티 클래스 목록
        // Projection.bean 이용해서, 데이터 조회시 , 바로 dto 변환을 다했음.
        // 변환 작업이 필요가 없음.
//        List<BoardDTO> dtoList = result.getContent().stream()
//                .map(board ->modelMapper.map(board, BoardDTO.class))
//                .collect(Collectors.toList());


        return PageResponseDTO.<BoardListReplyCountDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(result.getContent())
                .total((int) result.getTotalElements())
                .build();
    }

    @Override
    public PageResponseDTO<BoardListAllDTO> listWithAll(PageRequestDTO pageRequestDTO) {

        return null;
    }
}
