package com.busanit501.boot501.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Log4j2
@Configuration
@RequiredArgsConstructor
// 시큐리티 설정 on 추가
@EnableWebSecurity
public class CustomSecurityConfig {

    //순서1,
    // 인증, 인가 관련 구체적인 설정은 여기 메서드에서 작성
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.info("===========config=================");

        // 순서3, 로그인 방식을 폼 로그인으로 설정.
        // 옛날 문법,
        // http.formLogin();
        http.formLogin(
                formLogin ->
                        formLogin.loginPage("/member/login")
        );

        //로그인 후, 성공시 리다이렉트 될 페이지 지정, 간단한 버전.
        http.formLogin(formLogin ->
                formLogin.defaultSuccessUrl("/board/list",true)
        );

        // 기본은 csrf 설정이 on, 작업시에는 끄고 작업하기.
        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());

        http.authorizeHttpRequests(
                authorizeRequests -> {
                    authorizeRequests.requestMatchers("/css/**", "/js/**", "/images/**", "/images2/**","/member/login").permitAll();
                    authorizeRequests.requestMatchers("/board/list","/board/register").authenticated();
                    authorizeRequests.requestMatchers("/admin/**").hasRole("ADMIN");
                    authorizeRequests.anyRequest().authenticated();
                }

        );


        return http.build();
    }

    // 순서2,
    // css, js, 등 정적 자원은 시큐리티 필터에서 제외하기
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        log.info("시큐리티 동작 확인 ====webSecurityCustomizer======================");
        return (web) ->
                web.ignoring()
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }



}
