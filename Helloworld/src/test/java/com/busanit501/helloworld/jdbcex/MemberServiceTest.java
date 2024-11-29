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

    @Test
    public void getMemberWithUuidSearch() throws SQLException {
        // 각자 테이블의 유저의uuid를 직접 복사해서 붙여넣기.
        // 각각 전부 다 달라요.
        memberService.getMemberWithUuidService("7115a855-55bb-4dc2-8268-ab45b32bb383");
    }
}
