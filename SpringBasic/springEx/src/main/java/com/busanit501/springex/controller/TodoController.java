package com.busanit501.springex.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // 화면, 데이터 제공
@RequestMapping("/todo") // 경로지정, /todo 경로로 오는 모든 url은 이 컨트롤ㄹ가 받아서 작업함
@Log4j2
public class TodoController {
    @RequestMapping("/list")
    public void list() {
        log.info("TodoController list : 화면제공은 해당 메서드 명으로 제공함");
    }
}
