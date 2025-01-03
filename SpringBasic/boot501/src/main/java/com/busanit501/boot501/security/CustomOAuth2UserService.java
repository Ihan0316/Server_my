package com.busanit501.boot501.security;

import io.swagger.v3.oas.models.links.Link;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
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

        return user;
    }

    private String getKakaoEmail(Map<String, Object> params) {
        Object value = params.get("kakao_account");
        LinkedHashMap accountMap = (LinkedHashMap) value;
        String email = (String) accountMap.get("email");
        log.info("email : " + email);
        return email;
    }
}
