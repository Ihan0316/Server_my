package com.busanit501.helloworld.food;

import com.busanit501.helloworld.food.dto.FMemberDTO;
import com.busanit501.helloworld.food.service.FMemberService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.UUID;

@Log4j2
public class FMemberServiceTest {
    private FMemberService fmemberService;

    @BeforeEach
    public void ready() {
        fmemberService = FMemberService.INSTANCE;
    }

    // 등록
    @Test
    public void testInsert() throws SQLException {
        FMemberDTO fmemberDTO = fmemberService.login("ihan", "0316");
        log.info("FMemberService loginTest" + fmemberDTO.toString());
    }

    @Test
    public void updateUuid() throws SQLException {
        String uuid = UUID.randomUUID().toString();
        fmemberService.updateUuid("ihan2", uuid);
    }

    @Test
    public void getMemberWithUuidSearch() throws SQLException {
        // 각자 테이블의 유저의uuid를 직접 복사해서 붙여넣기.
        // 각각 전부 다 달라요.
        fmemberService.getMemberWithUuidService("081ad4d0-dbd1-42d3-9734-19fbadb6efbb");
    }
}
