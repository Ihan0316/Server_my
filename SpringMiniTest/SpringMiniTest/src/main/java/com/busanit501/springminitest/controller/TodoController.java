package com.busanit501.springminitest.controller;

import com.busanit501.springminitest.dto.SampleDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
@Controller
public class TodoController {

    @GetMapping("/todo/ex1")
    public void ex1(Model model) {
        List<String> list = Arrays.asList("a", "b", "c");
        model.addAttribute("list", list);
    }

    @GetMapping("/ex/ex2")
    public void ex2(Model model) {
        List<String> strList = IntStream.range(1,10).mapToObj(i -> "임시 데이터" + i).collect(Collectors.toList());
        model.addAttribute("strList", strList);

        Map<String, String> map = new HashMap<>();
        map.put("a", "aaa");
        map.put("b", "bbb");
        model.addAttribute("map", map);

        SampleDTO sampleDTO = SampleDTO.builder()
                .p1("테스트 p1")
                .p2("테스트 p2")
                .p3("테스트 p3")
                .p4("테스트 p4")
                .build();
        model.addAttribute("sampleDTO", sampleDTO);
    }

    @GetMapping("/todo/list")
    public void list(Model model) {
        List<String> list = Arrays.asList("a", "b", "c");
        model.addAttribute("list", list);
    }

    @GetMapping("/todo/register")
    public void register() {
    }
}
