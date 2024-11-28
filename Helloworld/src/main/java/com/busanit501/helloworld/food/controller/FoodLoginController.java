package com.busanit501.helloworld.food.controller;

import com.busanit501.helloworld.food.dto.FMemberDTO;
import com.busanit501.helloworld.food.service.FMemberService;
import com.busanit501.helloworld.jdbcex.dto.MemberDTO;
import com.busanit501.helloworld.jdbcex.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2
@WebServlet(name = "FoodLoginController" , urlPatterns = "/flogin")
public class FoodLoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("FoodLoginController doGet ");
        request.getRequestDispatcher("/WEB-INF/flogin.jsp")
                .forward(request,response);
    }
    // 로직처리
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("FoodLoginController doPost");
        String mid = request.getParameter("mid");
        String mpw = request.getParameter("mpw");

        // 디비에 가서 해당 유저가 있으면 임시로 세션에 저장
        // 임의로 세션 동작 여부만 확인 중
        try {
            FMemberDTO fMemberDTO = FMemberService.INSTANCE.login(mid, mpw);
            // 세션에, 위의 로그인 정보를 저장
            HttpSession session = request.getSession();
            session.setAttribute("floginInfo", fMemberDTO);
            response.sendRedirect("/food/list");
        } catch (SQLException e) {
            response.sendRedirect("flogin?result=error");
        }

    }
}
