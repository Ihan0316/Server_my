package com.busanit501.springminitest.controller;

import com.busanit501.springminitest.dto.PageRequestDTO;
import com.busanit501.springminitest.service.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/food")
public class FoodController {
    private final FoodService foodService;

    @GetMapping("/list2")
    public void list(PageRequestDTO pageRequestDTO, Model model) {

    }
}
