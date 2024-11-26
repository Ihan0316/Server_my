package com.busanit501.helloworld.JDBCex.controller;

import com.busanit501.helloworld.JDBCex.dto.TodoDTO;
import com.busanit501.helloworld.JDBCex.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "TodoRead2Controller", urlPatterns = "/todo/read2")
public class TodoRead2Controller extends HttpServlet {
    private TodoService todoService = TodoService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("doGet TodoRead2Controller 하나 조회 예시");

        try {
            Long tno = Long.parseLong(request.getParameter("tno"));
            TodoDTO todoDTO = todoService.get(tno);
            // 화면에 전달하기
            request.setAttribute("dto", todoDTO);
            request.getRequestDispatcher("/WEB-INF/todo/todoRead2.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}