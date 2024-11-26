package com.busanit501.helloworld.JDBCex.service;

import com.busanit501.helloworld.JDBCex.dao.TodoDAO;
import com.busanit501.helloworld.JDBCex.dto.TodoDTO;
import com.busanit501.helloworld.JDBCex.util.MapperUtil;
import com.busanit501.helloworld.JDBCex.vo.TodoVO;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// 설정1
@Log4j2
public enum TodoService {
    INSTANCE;
    // 2가지를 다른 클래스에 의존
    // ModelMapper, DAO기능
    private TodoDAO todoDAO;
    private ModelMapper modelMapper;

    // 생성자 이용해서 초기화하기
    TodoService(){
        todoDAO = new TodoDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    // crud 기본 테스트
    // 직접적인 비지니스 로직은 dao에 다 있다
    // 기능 명세서, 기능 모음집
    // dao에 의존해서 이용하기

    // 1
    // register
    // 화면에 등록된 내용이 dto에 담겨서 서비스 계층에 전달
    public void register (TodoDTO todoDTO) throws SQLException {
        // DAO에서 작업시, DB에 직접적인 영향을 주는 객체
        // VO 실제 비지니스 로직에서만 사용함
        // 서블릿 -> DTO -> DAO한테 전달할 때 다시 VO로 변환해야됨
        // 변환하는 도구, ModelMapper
        // 도구 사용안하면
//        TodoVO todoVO = new TodoVO();
//        todoVO.setTno(todoDTO.getTno());
//        todoVO.setTitle(todoDTO.getTitle());
//        todoVO.setDueDate(todoDTO.getDueDate());
//        todoVO.setFinished(todoDTO.isFinished());

        // 도구 사용시
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);

        // 기존 로깅 기록 출력
//        System.out.println("todoVO" + todoVO);
        log.info("todoVO" + todoVO);

        // DAO에 외주 맡기기
        todoDAO.insert(todoVO);
    }

    // 2
    // 전체 조회
    public List<TodoDTO> listAll() throws SQLException {
        List<TodoVO> voList = todoDAO.selectALL();
        log.info("voList" + voList);
        // stream 안하는 코드
//        List<TodoDTO> dtoList2 = new ArrayList<>();
//        for (TodoVO todoVO:voList){
//            TodoDTO todoDTO = new TodoDTO();
//            todoDTO.setTno(todoVO.getTno());
//            todoDTO.setTitle(todoVO.getTitle());
//            todoDTO.setDueDate(todoVO.getDueDate());
//            todoDTO.setFinished(todoVO.isFinished());
//            dtoList2.add(todoDTO);
//        }
        // 화면에 전달시 vo -> dto로 변환
        // stream 병렬처리 하면 간결해짐
        List<TodoDTO> dtoList = voList.stream().map(vo ->
                modelMapper.map(vo, TodoDTO.class)).collect(Collectors.toList());
        return dtoList;
    }

    // 3
    // 하나 조회
    public TodoDTO get(Long tno) throws SQLException {
        log.info("tno" + tno);
        // db에서 하나 조회 결과 받기
        TodoVO todoVO = todoDAO.selectOne(tno);
        // vo -> dto
        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);

        return todoDTO;
    }
}
