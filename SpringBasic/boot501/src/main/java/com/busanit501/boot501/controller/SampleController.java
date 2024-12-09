package com.busanit501.boot501.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller // 화면, 데이터 제공
@Log4j2
public class SampleController {
    @GetMapping("/hello")
    // 레거시에서 앞단은 jsp 사용
    // 부트에서는 화면을 타임리프 사용, 확장자 - .html 동일함
    public void hello(Model model) {
        model.addAttribute("msg", "hello world!");
    }

    @GetMapping("/ex/ex1")
    public void ex1(Model model) {
        List<String> list = Arrays.asList("a", "b", "c");
        model.addAttribute("list", list);
    }
}
