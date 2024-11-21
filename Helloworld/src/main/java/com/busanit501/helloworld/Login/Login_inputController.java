package com.busanit501.helloworld.Login;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "login_inputController", urlPatterns = "/login/input")
public class Login_inputController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet 호출");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Login/login_input.jsp");
        dispatcher.forward(request, response);
    }
}
