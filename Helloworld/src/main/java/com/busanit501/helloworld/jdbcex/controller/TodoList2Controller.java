package com.busanit501.helloworld.jdbcex.controller;

import com.busanit501.helloworld.jdbcex.service.TodoService;
import com.busanit501.helloworld.jdbcex.dto.TodoDTO;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Log4j2 // log.info("이런 형식으로 출력 한다.")
@WebServlet(name = "TodoList2Controller", urlPatterns = "/todo/list2")
public class TodoList2Controller extends HttpServlet {
    // 외주 일 시키기, 누구? 서비스 한테, 선언만,
    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 세션정보 호출 및 가져오기
        HttpSession session = request.getSession();

        // 만약 서버에서 최초로 접근을 했다면
        // 서버에서 JSESSIONID를 발급
        if(session.isNew()){
            log.info("최초로 서버에 요청을 함");
            // 로그인 컨트롤러가 아직 없음.
            response.sendRedirect("/login");
            return;
        }
        // 2번째 이후의 방문, 세션이라는 저장공간
        // 키 : loginInfo, 값 : 로그인한 유저의 아이디를 기록
        if(session.getAttribute("loginInfo") == null){
            log.info("2번째 이후로 서버에 요청을 했고, 로그인 정보는 없는 경우");
            // 로그인 컨트롤러가 아직 없음.
            response.sendRedirect("/login");
            return;
        }
        // 임시로, 최초도 아니고, 로그인 처리가 되었다면
        // 정상적으로 접근하는 페이지로 이동 시켜 줄게

        log.info("doGet TodoList2Controller 확인");
        try {
            // 서비스에 외주 주고, 전체 목록 리스트 받아오기.
            List<TodoDTO> todoList = todoService.listAll();
            // 화면에 데이터 전달. + 화면에 데이터 탑재된 화면을 -> 웹브라우저에게 전달.
            request.setAttribute("list", todoList);
            request.getRequestDispatcher("/WEB-INF/todo/todoList2.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
