package com.busanit501.helloworld.food;

import com.busanit501.helloworld.food.dto.FMemberDTO;
import com.busanit501.helloworld.food.service.FMemberService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

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
}
