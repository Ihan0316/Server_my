package com.busanit501.helloworld.JDBCex.controller;

import com.busanit501.helloworld.JDBCex.dto.TodoDTO;
import com.busanit501.helloworld.JDBCex.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Log4j2 // log.info 형식으로 출력
@WebServlet(name = "TodoList2Controller", urlPatterns = "/todo/list2")
public class TodoList2Controller extends HttpServlet {
    private TodoService todoService = TodoService.INSTANCE;
        // 외주 시키기 service
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("doGet TodoList2Controller 확인");

//        List<TodoDTO> todoList = TodoService.INSTANCE.getList();
//        request.setAttribute("list", todoList);
        request.getRequestDispatcher("/WEB-INF/todo/todoList2.jsp").forward(request, response);
    }
}
