package com.busanit501.minitest.controller;

import com.busanit501.minitest.dto.FoodDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/food") // 경로지정, /todo 경로로 오는 모든 url은 이 컨트롤ㄹ가 받아서 작업함
@Log4j2
public class FoodController {
    @RequestMapping("/list")
    public void list() {
        log.info("FoodController list : 화면제공은 해당 메서드 명으로 제공함");
    }

    // 1) 글작성 폼 -> get
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void register() {
        log.info("FoodController register : 화면제공은 해당 메서드 명으로 제공함");
    }

    // 2) 글작성 로직 처리 -> post
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void registerPost(FoodDTO foodDTO) {
        log.info("FoodController register post 로직처리");
        log.info("FoodController register post foodDTO : " + foodDTO);
    }
}
