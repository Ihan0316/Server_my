package com.busanit501.helloworld.todo.Controller;

import com.busanit501.helloworld.todo.DTO.TodoDTO;
import com.busanit501.helloworld.todo.Service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TodoListController", urlPatterns = "/todo/list")
public class TodoListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 화면 전달

        // DB연결전 더미 데이터를 받아와서 확인
        System.out.println("doGet : TodoListController");
        List<TodoDTO> todoList = TodoService.INSTANCE.getList();
        request.setAttribute("list", todoList);

        // 방법1
/*       RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/todo/todoList.jsp")
        dispatcher.forward(request, response); */

        // 방법2
        request.getRequestDispatcher("/WEB-INF/todo/todoList.jsp").forward(request, response);
    }
}
