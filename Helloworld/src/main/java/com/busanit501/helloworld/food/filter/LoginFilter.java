package com.busanit501.helloworld.food.filter;

import com.busanit501.helloworld.food.dto.FMemberDTO;
import com.busanit501.helloworld.jdbcex.dto.MemberDTO;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/food/*"})
@Log4j2
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("doFilter ,/food 하위로 들어오는 모든 url에 대해서 " +
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
            response.sendRedirect("/flogin");
            return;
        }
        // 2번째 이후의 방문, 세션이라는 저장공간
        // 키 : loginInfo, 값 : 로그인한 유저의 아이디를 기록
        if(session.getAttribute("loginInfo") == null){
            log.info("2번째 이후로 서버에 요청을 했고, 로그인 정보는 없는 경우");
            // 로그인 컨트롤러가 아직 없음.
            response.sendRedirect("/flogin");
            return;
        }
        // 임시로, 최초도 아니고, 로그인 처리가 되었다면
        // 정상적으로 접근하는 페이지로 이동 시켜 줄게
        if(session.getAttribute("loginInfo") != null) {
//            String result = (String) session.getAttribute("loginInfo");
            FMemberDTO fmemberDTO = (FMemberDTO) session.getAttribute("loginInfo");
            log.info("session.getAttribute(\"loginInfo\") fmemberDTO : " + fmemberDTO);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
