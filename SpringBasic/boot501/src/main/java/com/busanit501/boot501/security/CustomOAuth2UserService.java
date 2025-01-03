package com.busanit501.boot501.security;

import com.busanit501.boot501.domain.Member;
import com.busanit501.boot501.domain.MemberRole;
import com.busanit501.boot501.repository.MemberRepository;
import com.busanit501.boot501.security.dto.MemberSecurityDTO;
import io.swagger.v3.oas.models.links.Link;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    // 멤버와 연동할 준비
    // 준비1)
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 소셜 로그인시 반환 타입 OAuth2User
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("kakao CustomOAuth2UserService loadUser");
        log.info("userRequest : " + userRequest);

        // 이메일 추출
        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        String clientName = clientRegistration.getClientName();
        log.info("clientName : " +clientName);

        OAuth2User user = super.loadUser(userRequest);
        Map<String, Object> paramMap = user.getAttributes();

        // 소셜 정보 출력 확인용
        paramMap.forEach((key, value) -> log.info(key + " : " + value));

        String email = null;
        switch (clientName) {
            case "kakao":
                email = getKakaoEmail(paramMap);
                break;
        }

        // 반환 타입을 MemberSecurityDTO로 반환
        // generateDTO(email, paramMap) 정의

        return generateDTO(email, paramMap);
    }

    private String getKakaoEmail(Map<String, Object> params) {
        Object value = params.get("kakao_account");
        LinkedHashMap accountMap = (LinkedHashMap) value;
        String email = (String) accountMap.get("email");
        log.info("email : " + email);
        return email;
    }
    private MemberSecurityDTO generateDTO(String email, Map<String, Object> params) {
        Optional<Member> result = memberRepository.findByEmail(email);
        // 사용자가 db에 없으면 새로 생성
        if (result.isEmpty()) {
            Member member = Member.builder()
                    .mid(email)
                    .mpw(passwordEncoder.encode("1111"))
                    .email(email)
                    .social(true)
                    .build();
            member.addRole(MemberRole.USER);
            memberRepository.save(member);

            // 화면 출력 용도로 사용위해 DTO 변환
            MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(
                    email,
                    "1111",
                    email,
                    false,
                    true,
                    Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))
            );
            // 소셜 로그인 정보 담기
            memberSecurityDTO.setProps(params);
            return memberSecurityDTO;
        }
        // 유저 있는 경우
        else {
            Member member = result.get();
            MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(
                    member.getMid(),
                    member.getMpw(),
                    member.getEmail(),
                    member.isDel(),
                    member.isSocial(),
                    member.getRoleSet().stream().map(memberRole ->
                            new SimpleGrantedAuthority("ROLE_" + memberRole.name())).collect(Collectors.toList())
            );
            return memberSecurityDTO;
        }
    }
}
