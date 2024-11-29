package com.busanit501.helloworld.jdbcex.filter;

import com.busanit501.helloworld.jdbcex.dto.MemberDTO;
import com.busanit501.helloworld.jdbcex.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter(urlPatterns = {"/todo/*"})
@Log4j2
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("doFilter ,/todo 하위로 들어오는 모든 url에 대해서 " +
                "로그인 필터링 동작중.");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 세션정보 호출 및 가져오기
        HttpSession session = request.getSession();

        // 만약 서버에서 최초로 접근을 했다면
        // 서버에서 JSESSIONID를 발급
        if(session.isNew()){
            log.info("최초로 서버에 요청을 함");
            // 로그인 컨트롤러가 아직 없음.
            response.sendRedirect("/login");
            return;
        }
        // 2번째 이후의 방문, 세션이라는 저장공간
        // 키 : loginInfo, 값 : 로그인한 유저의 아이디를 기록
        if(session.getAttribute("loginInfo") != null){
            log.info("2번째 이후로 서버에 요청을 했고, 로그인 정보는 없는 경우");
            // 로그인 컨트롤러가 아직 없음.
//            response.sendRedirect("/login");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        // 자동 로그인 프로세스
        // PageScope, HttpServletRequest, HttpSession, Application
        // HttpServletRequest에서 모든 쿠키를 검색해서, rememberMe 이름을 찾는다.
        // 만약 해당 쿠키가 있고, 값도 있다면, DB에 조회해서, 쿠키의 값, DB값의 일치를 판단
        // loginInfo MemberDTO의 값 설정

        // 해당 유저의 uuid가 findCookie에 들어가 있음
        Cookie findCookie = findCookie(request.getCookies(), "rememberMe");
        // rememberMe가 체크가 안되어서 null일 경우, 다시 로그인 직접 하기
        if(findCookie != null){
            response.sendRedirect("/login");
            return;
        }
        String getUuid = findCookie.getValue();

        try {
            MemberDTO memberDTO = MemberService.INSTANCE.getMemberWithUuidService(getUuid);
            log.info("memberDTO : ", memberDTO);
            if(memberDTO == null){
                throw new Exception("쿠키값이 유효하지 않습니다");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/login");
        }


        // 임시로, 최초도 아니고, 로그인 처리가 되었다면
        // 정상적으로 접근하는 페이지로 이동 시켜 줄게
        if(session.getAttribute("loginInfo") != null) {
//            String result = (String) session.getAttribute("loginInfo");
            MemberDTO memberDTO = (MemberDTO) session.getAttribute("loginInfo");
            log.info("session.getAttribute(\"loginInfo\") memberDTO : " + memberDTO);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private Cookie findCookie(Cookie[] cookies, String name) {
        Cookie findCookie = null;
        // 쿠키가 있는 경우
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                // cookie.getName(): 전체 쿠키 목록 요소의 이름
                // name : 찾고자하는 쿠키 이름.
                if (cookie.getName().equals(name)) {
                    findCookie = cookie;
                    break;
                } //if
            } // for
        } // if
        return findCookie;
    } // method
}
