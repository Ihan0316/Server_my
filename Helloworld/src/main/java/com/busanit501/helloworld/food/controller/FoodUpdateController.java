package com.busanit501.helloworld.food.controller;

import com.busanit501.helloworld.food.dto.FoodDTO;
import com.busanit501.helloworld.food.service.FoodService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Log4j2
@WebServlet(name = "FoodUpdateController",urlPatterns = "/food/update")
public class FoodUpdateController extends HttpServlet {
    // 서비스를 포함 하기. 의존하기.
    private FoodService foodService = FoodService.INSTANCE;
    // 날짜 포맷팅
    private final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long fno = Long.parseLong(request.getParameter("fno"));
            FoodDTO foodDTO = foodService.get(fno);
            request.setAttribute("dto", foodDTO);
            request.getRequestDispatcher("/WEB-INF/food/foodUpd.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 글 작성 로직 처리.
    // 화면에서 데이터 전달받고, -> DTO 담아서, -> 서비스로 전달.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String finished = request.getParameter("finished");
        log.info("finished : " + finished);

        boolean checkFinished = false;
        if (finished.equals("on")) {
            checkFinished = true;
        }

        FoodDTO foodDTO = FoodDTO.builder()
                .fno(Long.valueOf(request.getParameter("fno")))
                .title(request.getParameter("title"))
                .dueDate(LocalDate.parse(request.getParameter("dueDate"), DATE_TIME_FORMATTER))
                .finished(checkFinished)
                .build();
        log.info("foodDTO 수정된 내용: " + foodDTO);

        try {
            foodService.update(foodDTO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("doPost : 글쓰기 처리하는 로직, 디비 연결 전, 리스트로 이동함");
        response.sendRedirect("/food/list");
    }
}
