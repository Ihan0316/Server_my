package com.busanit501.helloworld.food.service;

import com.busanit501.helloworld.food.dao.FMemberDAO;
import com.busanit501.helloworld.food.dto.FMemberDTO;
import com.busanit501.helloworld.food.vo.FMemberVO;
import com.busanit501.helloworld.jdbcex.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;

// 설정1
@Log4j2
public enum FMemberService {
    INSTANCE;
    private FMemberDAO fmemberDAO;
    private ModelMapper modelMapper;

    FMemberService() {
        fmemberDAO = new FMemberDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    // 로그인 확인용
    public FMemberDTO login(String mid, String mpw) throws SQLException {
        FMemberVO fmemberVO = fmemberDAO.getMemberWithMpw(mid, mpw);
        FMemberDTO fmemberDTO = modelMapper.map(fmemberVO, FMemberDTO.class);
        return fmemberDTO;
    }

    public void updateUuid(String mid, String uuid) throws SQLException {
        fmemberDAO.updateUuid(mid,uuid);
    }

    public FMemberDTO getMemberWithUuidService(String uuid) throws SQLException {
        FMemberVO fmemberVO= fmemberDAO.getMemberWithUuid(uuid);
        FMemberDTO fmemberDTO = modelMapper.map(fmemberVO, FMemberDTO.class);
        return fmemberDTO;
    }

}






