package com.busanit501.boot501.security;

import com.busanit501.boot501.domain.Member;
import com.busanit501.boot501.repository.MemberRepository;
import com.busanit501.boot501.security.dto.MemberSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    // 시스템에 등록된 암호화 도구 가져오기
    private final MemberRepository memberRepository;
    private PasswordEncoder passwordEncoder;

    // 시큐리티에서 로그인 작업 처리시, 동작하는 메서드 여기
    // 시큐리티에서, 타입을 UserDetails로 반환해야 확인 가능
    // 결론 -> 로그인 했을 경우 입력한 username, password 값을 여기 매서드로 가지고 온다
    // 가지고 오는 키는 고정 username, password 주의사항
    // input태그 에서 name 이름 작성시 주의

//    public CustomUserDetailsService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//        this.passwordEncoder = new BCryptPasswordEncoder();
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("시큐리티 loadUserByUsername 확인 : " + username);
        // 데이터 베이스 저장된 유저와 비교 작업 후, 처리
        // 테스트 더미데이터 작업
        // User : 스프링 시큐리티에서 제공하는 클래스, 이름 주의!!
//        log.info("passwordEncoder.encode() : " + passwordEncoder.encode("1234"));
//        UserDetails userDetials = User.builder()
//                .username("jih")
//                .password(passwordEncoder.encode("1234"))
//                // 인증된 유저
//                .authorities("ROLE_USER", "ROLE_ADMIN")
//                .authorities("ROLE_USER")
//                .build();

        // 더미 데이터에 이어서, 우리가 만든 Member 엔티티 이용하고,
        //  로그인 처리시에 사용하는 DTO 이용함
        // 주의사항, 반드시 시큐리티에서 제공하는  User 클래스 상속 받은 클래스 이용해야함.
        log.info("로그인한 유저 확인 : "+ username);
        Optional<Member> result = memberRepository.getWithRoles(username);
        // 없으면 예외 발생시키기
        if (result.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        Member member = result.get();
        // 중요한 부분, 반환 타입 UserDetails 로 반환해야해서,
        // member -> MemberSecurityDTO 변환해서, 반환하기.
        MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(
                member.getMid(),
                member.getMpw(),
                member.getEmail(),
                member.isDel(),
                false,
                // 권한 지정 해주기.
                member.getRoleSet().stream().map(
                        memberRole -> new SimpleGrantedAuthority("ROLE_"+memberRole.name())
                ).collect(Collectors.toList())
        );
        log.info("memberSecurityDTO : " + memberSecurityDTO);

        return memberSecurityDTO;
    }
}