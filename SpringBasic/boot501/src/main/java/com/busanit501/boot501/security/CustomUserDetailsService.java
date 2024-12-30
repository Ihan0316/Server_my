package com.busanit501.boot501.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    // 시큐리티에서 로그인 작업 처리시, 동작하는 메서드 여기
    // 시큐리티에서, 타입을 UserDetails로 반환해야 확인 가능
    // 결론 -> 로그인 했을 경우 입력한 username, password 값을 여기 매서드로 가지고 온다
    // 가지고 오는 키는 고정 username, password 주의사항
    // input태그에서 name이름 작성시 주의
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("시큐리티 loadUserByUsername 확인 : " + username);
        // 데이터 베이스 저장된 유저와 비교 작업 후, 처리

        return null;
    }
}