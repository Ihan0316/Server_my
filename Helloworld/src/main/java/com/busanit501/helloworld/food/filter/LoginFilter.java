package com.busanit501.helloworld.food.filter;

import com.busanit501.helloworld.food.dto.FMemberDTO;
import com.busanit501.helloworld.food.service.FMemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/food/*"})
@Log4j2
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("doFilter ,/food/* 하위로 들어오는 모든 url 에 대해서 로그인 체크함");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        if(session.isNew()) {
            log.info("최초로 서버에 요청을함.");
            // 로그인 컨트롤러가 아직 없음, 곧 만들 예정.
            response.sendRedirect("/flogin");
            return;
        }
        if(session.getAttribute("floginInfo") != null) {
            log.info("2번째 이후로 서버에 요청을했고, 하지만, 로그인 정보는 없는 경우.");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        Cookie findCookie = findCookie(request.getCookies(), "rememberfMe");
        if(findCookie == null) {
            response.sendRedirect("/flogin");
            return;
        }

        String getUuid = findCookie.getValue();

        try {
            FMemberDTO fmemberDTO = FMemberService.INSTANCE.getMemberWithUuidService(getUuid);
            log.info("fmemberDTO : ", fmemberDTO);

            if(fmemberDTO == null) {
                throw new Exception("쿠키값이 유효하지 않습니다.");
            }
            session.setAttribute("floginInfo", fmemberDTO);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/flogin");

        }

        if(session.getAttribute("floginInfo") != null) {
            FMemberDTO fmemberDTO  = (FMemberDTO) session.getAttribute("floginInfo");
            log.info("session.getAttribute(\"floginInfo\") fmemberDTO : " + fmemberDTO);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private Cookie findCookie(Cookie[] cookies, String name) {
        Cookie findCookie = null;
        // 쿠키가 있는 경우
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    findCookie = cookie;
                    break;
                } //if
            } // for
        } // if
        return findCookie;
    } // method
}
