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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "TodoReg2Controller", urlPatterns = "/todo/register2")
public class TodoReg2Controller extends HttpServlet {
    // 서비스를 포함, 의존하기
    private TodoService todoService = TodoService.INSTANCE;
    // date 포멧팅
    private final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/todo/todoReg2.jsp").forward(request, response);
    }

    // 글 작성 로직 처리
    // 화면에서 데이터 전달받고, dto담아서 서비스로 전달
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // PRG 패턴
        // Post 처리 후 Redirect, Get 호출
        // 무한 Post 방지, 화면 전환 효과
        // 임시로 담을 DTO인스턴스 필요
        TodoDTO todoDTO = TodoDTO.builder()
                .title(request.getParameter("title"))
                .dueDate(LocalDate.parse(request.getParameter("dueDate"), DATE_TIME_FORMATTER))
                .build();
        // Controller -> Service
        try {
            todoService.register(todoDTO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("doPost : 글쓰기 로직 처리");
        response.sendRedirect("/todo/list2");
    }
}
