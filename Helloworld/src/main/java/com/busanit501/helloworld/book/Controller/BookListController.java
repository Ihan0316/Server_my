package com.busanit501.helloworld.book.Controller;



import com.busanit501.helloworld.book.DTO.BookDTO;
import com.busanit501.helloworld.book.Service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookListController", urlPatterns = "/book/booklist")
public class BookListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<BookDTO> bookList = BookService.INSTANCE.getList();
        request.setAttribute("list", bookList);

        request.getRequestDispatcher("/WEB-INF/book/bookList.jsp").forward(request, response);
    } 
}