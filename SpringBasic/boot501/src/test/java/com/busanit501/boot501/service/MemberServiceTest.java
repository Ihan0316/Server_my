package com.busanit501.boot501.service;

import com.busanit501.boot501.dto.BoardDTO;
import com.busanit501.boot501.dto.MemberJoinDTO;
import com.busanit501.boot501.dto.PageRequestDTO;
import com.busanit501.boot501.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@Log4j2
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void testRegisterMember() throws MemberService.MidExistException {
        MemberJoinDTO memberJoinDTO = new MemberJoinDTO();
        memberJoinDTO.setMid("jih");
        memberJoinDTO.setMpw("1234");
        memberJoinDTO.setEmail("test@test.com");
        memberService.join(memberJoinDTO);
    }
}
