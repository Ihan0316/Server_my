package com.busanit501.helloworld.food;

import com.busanit501.helloworld.food.dao.FMemberDAO;
import com.busanit501.helloworld.food.vo.FMemberVO;
import com.busanit501.helloworld.jdbcex.dao.MemberDAO;
import com.busanit501.helloworld.jdbcex.vo.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.UUID;

@Log4j2
public class FMemberDAOTest {
    private FMemberDAO fmemberDAO;

    // 아래에 각 단위 테스트가 실행되기전에, 먼저 실행을 함.
    @BeforeEach
    public void ready() {
        fmemberDAO = new FMemberDAO();
    }

    @Test
    public void getMemberWithMpwTest() throws SQLException {
        String mid = "ihan";
        String mpw = "0316";
        FMemberVO fmemberVO = fmemberDAO.getMemberWithMpw(mid, mpw);
        log.info("memberVO 조회 확인" + fmemberVO);
    }
    @Test
    public void updateUUIDTest() throws SQLException {
        String uuid = UUID.randomUUID().toString();
        fmemberDAO.updateUuid("ihan", uuid);
    }

    @Test
    public void getMemberWithUuidTest() throws SQLException {
        // 각자 테이블의 유저의 uuid를 직접 복붙
        FMemberVO fmemberVO = fmemberDAO.getMemberWithUuid("b6a6e51a-3ccb-4b96-99d6-26aa3fa55797");
        log.info("fmemberVO : " + fmemberVO);
    }
}
