package com.busanit501.helloworld.book.Controller;

import com.busanit501.helloworld.book.DTO.BookDTO;
import com.busanit501.helloworld.book.Service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BookReadController", urlPatterns = "/book/read")
public class BookReadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("doGet TodoReadController 하나 조회 예시");
        // 클릭한 게시글 번호를 가지고 와야함
        Long fno = Long.parseLong(request.getParameter("fno"));

        // 서비스에서 todo 더미 데이터를 조회 후,
        BookDTO bookDTO = BookService.INSTANCE.getOne(fno);

        // 화면에 전달하기
        request.setAttribute("dto", bookDTO);
        request.getRequestDispatcher("/WEB-INF/book/bookRead.jsp").forward(request, response);
    }
}
