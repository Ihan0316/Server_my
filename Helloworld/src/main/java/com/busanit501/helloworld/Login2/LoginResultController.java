package com.busanit501.helloworld.Login2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Login_resultController2", urlPatterns = "/login/result2")
public class LoginResultController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost,LoginResultController 서블릿을 경유해서 로직 처리");
        String username2 = request.getParameter("username2");
        String password2 = request.getParameter("password2");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1> username : " + username2 + "</h1>");
        out.println("<h1> password : " + password2 + "</h1>");
        out.println("</body></html>");
    }
}
