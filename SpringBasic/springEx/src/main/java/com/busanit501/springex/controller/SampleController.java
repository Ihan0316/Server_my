package com.busanit501.springex.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@Log4j2
public class SampleController {

    @GetMapping("/hello")
    // 확인은 http://localhost:8080/hello
    // 맵핑이 매서드명과 동일한 뷰 파일로 연결
    // /WEB-INF/views/
    // hello(메서드명)
    // .jsp 확장자
    public void hello() {
        // 아직 화면이 없음, 임의로 설정
        log.info("hello");
    }

    @GetMapping("/hello2")
    // 리턴타입이 문자열이면, 해당 문자열의 이름이 뷰의 파일명으로 뷰를 맵핑, 할당
    public String hello2() {
        // 아직 화면이 없음, 임의로 설정
        log.info("hello2");
        return "helloTest";
    }

    @GetMapping("/ex1")
    // 파라미터 수집 여부 확인, 콘솔에서 확인
    // localhost:8080/ex1?name=ihan&age=26
    public void ex1(String name, int age) {
        log.info("ex1 name : " + name);
        log.info("ex1 age : " + age);
    }

    @GetMapping("/ex2")
    // 파라미터 수집 여부 확인, 콘솔에서 확인
    // localhost:8080/ex2?name=ihan&age=26
    public void ex2(@RequestParam(name = "name", defaultValue = "JIH") String name,
                    @RequestParam(name = "age", defaultValue = "30") int age) {
        log.info("ex2 name : " + name);
        log.info("ex2 age : " + age);
    }

    @GetMapping("/ex3")
    // 웹브라우저에서 넘어온 데이터 타입은 문자열
    // 받을때 타입 불일치 오류 확인
    // localhost:8080/ex3?dueDate=2024-12-03
    // 대책 -> 미리 형이 다른 문자열에 대해 localdate를 파싱,string타입으로
    // formatter 이용, 파일 분리, 시스템에 빈 등록
    // LocalDate가 문자열로 전송되어도, 시스템이 자동으로 LocalDate로 형변환 해줌
    public void ex3(LocalDate dueDate) {
        log.info("ex3 dueDate : " + dueDate);
    }

    // 앞의 예제는 웹 -> 서버로 전달, 서버로 확인
    // 이번에는 방향이 반대, 서버 -> 웹으로, 화면에 데이터 탑재 전달
    @GetMapping("/ex4")
    public void ex4(Model model) {
        log.info("ex4 Model 서버에서 -> 데이터 전달하기. :");
        model.addAttribute("msg"," <script>\n" +
                "                    alert('이것은 JavaScript alert 테스트입니다!, 만약, 공격자가 악성 코드를 이런식으로 문자열에 포함하면 안 좋은일이 생김');\n" +
                "                </script>");
    }
}
