package com.busanit501.helloworld.jdbcex.controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
@WebServlet(name = "TodoLoginController" , urlPatterns = "/login")
public class TodoLoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("TodoLoginController doGet ");
        request.getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request,response);
    }
    // 로직처리
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("TodoLoginController doPost");
        String mid = request.getParameter("mid");
        String mpw = request.getParameter("mpw");

        // 디비에 가서 해당 유저가 있으면 임시로 세션에 저장
        // 임의로 세션 동작 여부만 확인 중
        String tempInfo = mid + mpw;
        // 위의 로그인 정보를 세션에 저장

        HttpSession session = request.getSession();
        session.setAttribute("loginInfo", tempInfo);
        response.sendRedirect("/todo/list2");
    }
}
