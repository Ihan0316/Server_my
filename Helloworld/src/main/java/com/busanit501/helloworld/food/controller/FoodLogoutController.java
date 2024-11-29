package com.busanit501.helloworld.food.controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
@WebServlet(name = "FoodLogoutController" , urlPatterns = "/flogout")
public class FoodLogoutController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("FoodLogoutController doPost");

        HttpSession session = request.getSession();
        session.removeAttribute("floginInfo");
        session.invalidate();

        response.sendRedirect("/flogin");
    }
}
