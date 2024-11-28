package com.busanit501.helloworld.jdbcex;

import com.busanit501.helloworld.jdbcex.dto.MemberDTO;
import com.busanit501.helloworld.jdbcex.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.UUID;

@Log4j2
public class MemberServiceTest {
    private MemberService memberService;

    @BeforeEach
    public void ready() {
        memberService = MemberService.INSTANCE;
    }

    // 등록
    @Test
    public void testInsert() throws SQLException {
        MemberDTO memberDTO = memberService.login("lsy", "1234");
        log.info("MemberService loginTest" + memberDTO.toString());
    }

    //uuid 테스트
    @Test
    public void updateUuid() throws SQLException {
        String uuid = UUID.randomUUID().toString();
        memberService.updateUuid("lsy2", uuid);
    }
}
