package com.busanit501.helloworld.JDBCex.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

// 톰켓 서버에서 한글로 입력된 내용을 utf-8로 변환해서 보내기.
// 필터 -> 서버에서 작업을 실행하기 전에 먼저 검사한다.
@WebFilter(filterName = "UTF8Filter", urlPatterns = {"/*"})
@Log4j2
public class UTF8Filter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("doFilter : /하위로 들어오는 모든 url에 대해서" + "한글 변환 필터링 동작 중");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.setCharacterEncoding("UTF-8");
        filterChain.doFilter(request, servletResponse);
    }
}
