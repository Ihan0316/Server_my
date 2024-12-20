package com.busanit501.springminitest.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController// 데이터만 제공.
@Log4j2
public class SampleRestController {

    @PostMapping("/hiRest")
    public String[] hiRest() {
        return new String[]{"aaa", "bbb", "ccc"};
    }
}
