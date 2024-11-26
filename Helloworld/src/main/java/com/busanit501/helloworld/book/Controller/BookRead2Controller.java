package com.busanit501.helloworld.book.Controller;

import com.busanit501.helloworld.book.DTO.BookDTO;
import com.busanit501.helloworld.book.Service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "BookRead2Controller", urlPatterns = "/book/read2")
public class BookRead2Controller extends HttpServlet {
    private BookService bookService = BookService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("doGet BookRead2Controller 하나 조회 예시");

        try {
            Long bno = Long.parseLong(request.getParameter("bno"));
            BookDTO bookDTO = bookService.get(bno);
            request.setAttribute("dto", bookDTO);
            request.getRequestDispatcher("/WEB-INF/book/bookRead2.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
