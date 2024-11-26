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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "BookReg2Controller", urlPatterns = "/book/register2")
public class BookReg2Controller extends HttpServlet {
    private BookService bookService = BookService.INSTANCE;
    // date 포멧팅
    private final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/book/bookReg2.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BookDTO bookDTO = BookDTO.builder()
                .title(request.getParameter("title"))
                .dueDate(LocalDate.parse(request.getParameter("dueDate"), DATE_TIME_FORMATTER))
                .build();
        try {
            bookService.register(bookDTO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("doPost : 글쓰기 로직 처리");
        response.sendRedirect("/book/list2");
    }
}
