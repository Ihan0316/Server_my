package com.busanit501.helloworld.food.controller;

import com.busanit501.helloworld.food.dto.FMemberDTO;
import com.busanit501.helloworld.food.service.FMemberService;
import com.busanit501.helloworld.jdbcex.dto.MemberDTO;
import com.busanit501.helloworld.jdbcex.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

@Log4j2
@WebServlet(name = "FoodLoginController" , urlPatterns = "/flogin")
public class FoodLoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("FoodLoginController doGet ");
        request.getRequestDispatcher("/WEB-INF/flogin.jsp")
                .forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("FoodLoginController doPost");
        String mid = request.getParameter("mid");
        String mpw = request.getParameter("mpw");
        String auto = request.getParameter("auto");

        boolean rememberfMe = auto != null && auto.equals("on");

        try {
            FMemberDTO fmemberDTO = FMemberService.INSTANCE.login(mid, mpw);
            if (rememberfMe) {
                String uuid = UUID.randomUUID().toString();
                FMemberService.INSTANCE.updateUuid(mid, uuid);
                fmemberDTO.setUuid(uuid);

                Cookie rememberCookie = new Cookie("rememberfMe", uuid);
                rememberCookie.setPath("/");
                rememberCookie.setMaxAge(60*60*24*7);
                response.addCookie(rememberCookie);
            }

            HttpSession session = request.getSession();
            session.setAttribute("floginInfo", fmemberDTO);
            response.sendRedirect("/food/list");
        } catch (SQLException e) {
            response.sendRedirect("/flogin?result=error");
        }

    }
}
