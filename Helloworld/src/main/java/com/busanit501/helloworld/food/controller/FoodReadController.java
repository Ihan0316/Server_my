package com.busanit501.helloworld.food.controller;

import com.busanit501.helloworld.food.dto.FoodDTO;
import com.busanit501.helloworld.food.service.FoodService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2
@WebServlet(name = "FoodReadController", urlPatterns = "/food/read")
public class FoodReadController extends HttpServlet {
    private FoodService foodService = FoodService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("doGet TodoReadController 하나 조회 예제");

        try {
            Long fno = Long.parseLong(request.getParameter("fno"));
            FoodDTO foodDTO = foodService.get(fno);
            request.setAttribute("dto", foodDTO);

            Cookie findCookie = findCookie(request.getCookies(), "viewFoods");
            String cookieValue = findCookie.getValue();
            log.info("cookieValue : " + cookieValue);
            boolean exists = false;

            if(cookieValue != null && cookieValue.indexOf(fno+"-") >=0) {
                log.info("cookieValue.indexOf(fno+\"-\") " + cookieValue.indexOf(fno+"-"));
                exists = true;
            }

            if(!exists) {
                cookieValue += fno+"-";
                findCookie.setValue(cookieValue);
                findCookie.setMaxAge(60);
                findCookie.setPath("/");

                response.addCookie(findCookie);
            }

            request.getRequestDispatcher("/WEB-INF/food/foodRead.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    } // doGet

    private Cookie findCookie(Cookie[] cookies, String name) {
        Cookie findCookie = null;
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    findCookie = cookie;
                    break;
                } //if
            } // for
        } // if

        if (findCookie == null) {
            findCookie = new Cookie("viewFoods", "");
            findCookie.setPath("/");
            findCookie.setMaxAge(60*60*24);
        }

        return findCookie;
    } // method
}
