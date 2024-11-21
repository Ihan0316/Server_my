package com.busanit501.helloworld;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// 웹브라우저에서 http://localhost:8080/my를 요청하면 WebServlet 으로 받는다.
@WebServlet(name = "myServlet", urlPatterns = "/my")
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 현재, 이 자바 파일은 역할이 서블릿 역할.
        // 서블릿 -> 웹 브라우저로부터의 요청을 받고 응답 해주는 역할, 중간 역할
        // 컨트롤러가 이 역할을 대신 할 예정

        // 응답, 서버 -> 웹브라우저 응답. text/html
        resp.setContentType("text/html");

        //출력
        PrintWriter out = resp.getWriter();

        //응답할 내용 작성, 받는 쪽에서는 사용하는 언어 html
        out.println("<html><body>");
        out.println("<h1>My Servlet Test 조이한</h1>");
        out.println("</body></html>");
    }

}