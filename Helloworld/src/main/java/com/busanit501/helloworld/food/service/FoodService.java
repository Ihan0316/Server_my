package com.busanit501.helloworld.food.service;

import com.busanit501.helloworld.food.dao.FoodDAO;
import com.busanit501.helloworld.food.dto.FoodDTO;
import com.busanit501.helloworld.food.vo.FoodVO;
import com.busanit501.helloworld.jdbcex.dto.TodoDTO;
import com.busanit501.helloworld.jdbcex.util.MapperUtil;
import com.busanit501.helloworld.jdbcex.vo.TodoVO;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;


//열거형 상수들,
//상수들의 집합, 모음집
@Log4j2
public enum FoodService {
    INSTANCE;

    private FoodDAO foodDAO;
    private ModelMapper modelMapper;

    FoodService() {
        foodDAO = new FoodDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    //1
    // register
    public void register(FoodDTO foodDTO) throws SQLException {
        FoodVO foodVO = modelMapper.map(foodDTO, FoodVO.class);
        log.info("foodVO : " + foodVO);
        foodDAO.insert(foodVO);
    } // register

    //2
    // 전체 조회
    public List<FoodDTO> listAll() throws SQLException {
        List<FoodVO> voList = foodDAO.selectAll();
        log.info("voList : " + voList);
        List<FoodDTO> dtoList = voList.stream().map(vo -> modelMapper.map(vo, FoodDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }

    //3
    // 하나 조회, 상세보기.
    public FoodDTO get(Long fno) throws SQLException {
        log.info("fno : " + fno);
        ///  디비에서 하나 조회 결과 받았음.
        FoodVO foodVO = foodDAO.selectOne(fno);
        // VO -> DTO 변환 작업.
        FoodDTO foodDTO = modelMapper.map(foodVO,FoodDTO.class);
        return foodDTO;

    }

    //4 수정 기능
    public void update(FoodDTO foodDTO) throws SQLException {
        // 화면에서 넘겨 받은 데이터는 TodoDTO 타입 박스에 담겨서 오고,
        // DAO 계층에서 박스의 타입 TodoVO 사용하니, 변환 작업 필요함.
        // 항상 데이터가 전달 유무 확인.
        log.info("todoDTO : " + foodDTO);
        FoodVO foodVO = modelMapper.map(foodDTO, FoodVO.class);
        foodDAO.updateOne(foodVO);

    }

    //5 삭제 기능.
    public void delete(Long fno) throws SQLException {
        foodDAO.deleteFood(fno);
    }

}


// 예시, TodoService.INSTANCe
// private final String str = "test";
