package com.busanit501.helloworld.book.Controller;


import com.busanit501.helloworld.book.DTO.BookDTO;
import com.busanit501.helloworld.book.Service.BookService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Log4j2 // log.info 형식으로 출력
@WebServlet(name = "BookList2Controller", urlPatterns = "/book/booklist2")
public class BookList2Controller extends HttpServlet {
    private BookService bookService = BookService.INSTANCE;
    // 외주 시키기 service
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("doGet BookList2Controller 확인");

        try {
            // 서비스에 외주 주고, 전체 목록 리스트 받아오기
            List<BookDTO> bookList = bookService.listAll();
            // 화면에 데이터 전달 + 화면에 데이터 탑재된 화면을 웹브라우저에 전달
            log.info(bookList.get(0).getTitle());
            request.setAttribute("list", bookList);
            request.getRequestDispatcher("/WEB-INF/book/bookList2.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}