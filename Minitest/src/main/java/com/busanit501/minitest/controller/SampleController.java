package com.busanit501.minitest.controller;

import com.busanit501.minitest.dto.FoodDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@Log4j2
public class SampleController {

    @GetMapping("/hello")
    public void hello() {
        // 아직 화면이 없음, 임의로 설정
        log.info("hello");
    }

    @GetMapping("/hello2")
    public String hello2() {
        // 아직 화면이 없음, 임의로 설정
        log.info("hello2");
        return "helloTest";
    }

    @GetMapping("/ex1")
    public void ex1(String name, int age) {
        log.info("ex1 name : " + name);
        log.info("ex1 age : " + age);
    }

    @GetMapping("/ex2")
    public void ex2(@RequestParam(name = "name", defaultValue = "JIH") String name,
                    @RequestParam(name = "age", defaultValue = "30") int age) {
        log.info("ex2 name : " + name);
        log.info("ex2 age : " + age);
    }

    @GetMapping("/ex3")
    public void ex3(LocalDate dueDate) {
        log.info("ex3 dueDate : " + dueDate);
    }

    @GetMapping("/ex4")
    public void ex4(Model model) {
        log.info("ex4 Model 서버에서 -> 데이터 전달하기. :");
        model.addAttribute("msg"," <script>\n" +
                "                    alert('이것은 JavaScript alert 테스트입니다!, 만약, 공격자가 악성 코드를 이런식으로 문자열에 포함하면 안 좋은일이 생김');\n" +
                "                </script>");
    }

    @GetMapping("/ex5")
    public void ex5(FoodDTO foodDTO) {
        log.info("ex5 : " + foodDTO);
    }

    @GetMapping("/ex6")
    public String ex6(RedirectAttributes redirectAttributes) {
        log.info("ex6");
        redirectAttributes.addAttribute("msg", "test data");
        redirectAttributes.addFlashAttribute("msg2", "일회용으로 사용");

        return "redirect:/ex7";
    }

    @GetMapping("/ex7")
    public void ex7(String msg, Model model) {
        log.info("ex7");
        log.info("msg : " + msg);
        model.addAttribute("msg", msg);
    }
}
