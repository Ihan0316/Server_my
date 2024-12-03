package com.busanit501.springex.controller;

import com.busanit501.springex.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller // 화면, 데이터 제공
@RequestMapping("/todo") // 경로지정, /todo 경로로 오는 모든 url은 이 컨트롤ㄹ가 받아서 작업함
@Log4j2
public class TodoController {
    // localhost:8080/todo/list
    @RequestMapping("/list")
    public void list() {
        log.info("TodoController list : 화면제공은 해당 메서드 명으로 제공함");
    }

    // localhost:8080/todo/register
    // 1) 글작성 폼 -> get
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void register() {
        log.info("TodoController register : 화면제공은 해당 메서드 명으로 제공함");
    }

    // 2) 글작성 로직 처리 -> post
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void registerPost(TodoDTO todoDTO) {
        log.info("TodoController register post 로직처리");
        log.info("TodoController register post todoDTO : " + todoDTO);
    }
}
