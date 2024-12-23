package com.busanit501.helloworld.jdbcex;

import com.busanit501.helloworld.jdbcex.dao.MemberDAO;
import com.busanit501.helloworld.jdbcex.vo.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.UUID;

@Log4j2
public class MemberDAOTest {
    private MemberDAO memberDAO;

    // 아래에 각 단위 테스트가 실행되기전에, 먼저 실행을 함.
    @BeforeEach
    public void ready() {
        memberDAO = new MemberDAO();
    }

    @Test
    public void getMemberWithMpwTest() throws SQLException {
        String mid = "lsy";
        String mpw = "1234";
        MemberVO memberVO = memberDAO.getMemberWithMpw(mid, mpw);
        log.info("memberVO 조회 확인" + memberVO);
    }

    @Test
    public void updateUUIDTest() throws SQLException {
        String uuid = UUID.randomUUID().toString();
        memberDAO.updateUuid("lsy", uuid);
    }

    @Test
    public void getMemberWithUuidTest() throws SQLException {
        // 각자 테이블의 유저의 uuid를 직접 복붙
        MemberVO memberVO = memberDAO.getMemberWithUuid("bbe10151-3121-4013-917e-edaf3b248ff4");
        log.info("memberVO : " + memberVO);
    }
    
}
