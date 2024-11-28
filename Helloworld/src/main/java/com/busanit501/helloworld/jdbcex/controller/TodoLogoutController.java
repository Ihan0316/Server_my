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
@WebServlet(name = "TodoLogoutController" , urlPatterns = "/logout")
public class TodoLogoutController extends HttpServlet {

    // 로그인시 임시로 세션에 loginInfo 키이름으로, 값 : 아이디+패스워드
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("TodoLoginController doPost");

        HttpSession session = request.getSession();
        session.removeAttribute("loginInfo");
        session.invalidate();

        response.sendRedirect("/login");
    }
}
