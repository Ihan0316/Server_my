package com.busanit501.boot501.repository;

import com.busanit501.boot501.domain.Member;
import com.busanit501.boot501.domain.MemberRole;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 회원 가입 테스트
    @Test
    public void testInsert() {
        IntStream.range(1, 101).forEach(i -> {
            Member member = Member.builder()
                    .mid("user" + i)
                    .mpw(passwordEncoder.encode("1111"))
                    .email("user" + i + "@test.com")
                    .build();
            // 각 유저별 권한 추가
            member.addRole(MemberRole.USER);

            if (i >= 90) {
                member.addRole(MemberRole.ADMIN);
            }
            memberRepository.save(member);
        });
    }

    @Test
    public void testSelectMember() {
        Optional<Member> result = memberRepository.getWithRoles("user90");
        Member member = result.orElseThrow();
        log.info(member);
        log.info(member.getRoleSet());
        member.getRoleSet().forEach(role -> log.info(role.name()));

    }
}
