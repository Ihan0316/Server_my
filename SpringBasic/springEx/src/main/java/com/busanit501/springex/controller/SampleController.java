package com.busanit501.springex.controller;

import com.busanit501.springex.dto.TodoDTO;
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

    // 확인은 http://localhost:8080/hello
    // 맵핑이 매서드명과 동일한 뷰 파일로 연결
    // /WEB-INF/views/
    // hello(메서드명)
    // .jsp 확장자
    @GetMapping("/hello")
    public void hello() {
        // 아직 화면이 없음, 임의로 설정
        log.info("hello");
    }

    // 리턴타입이 문자열이면, 해당 문자열의 이름이 뷰의 파일명으로 뷰를 맵핑, 할당
    @GetMapping("/hello2")
    public String hello2() {
        // 아직 화면이 없음, 임의로 설정
        log.info("hello2");
        return "helloTest";
    }

    // 파라미터 수집 여부 확인, 콘솔에서 확인
    // localhost:8080/ex1?name=ihan&age=26
    @GetMapping("/ex1")
    public void ex1(String name, int age) {
        log.info("ex1 name : " + name);
        log.info("ex1 age : " + age);
    }

    // 파라미터 수집 여부 확인, 콘솔에서 확인
    // localhost:8080/ex2?name=ihan&age=26
    @GetMapping("/ex2")
    public void ex2(@RequestParam(name = "name", defaultValue = "JIH") String name,
                    @RequestParam(name = "age", defaultValue = "30") int age) {
        log.info("ex2 name : " + name);
        log.info("ex2 age : " + age);
    }

    // 웹브라우저에서 넘어온 데이터 타입은 문자열
    // 받을때 타입 불일치 오류 확인
    // localhost:8080/ex3?dueDate=2024-12-03
    // 대책 -> 미리 형이 다른 문자열에 대해 localdate를 파싱,string타입으로
    // formatter 이용, 파일 분리, 시스템에 빈 등록
    // LocalDate가 문자열로 전송되어도, 시스템이 자동으로 LocalDate로 형변환 해줌
    @GetMapping("/ex3")
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

    // 웹브라우저에서 TodoDTO 멤버 타입 형식으로 받고
    // 다시 서버 -> 웹으로 전달
    // 파라미터, TodoDTO todoDTO 선언되어 있으면,
    // 화면에서 그대로 사용가능 ${todoDTO}
    // Model model 사용 안해도, 스프링 프레임워크에서 자동으로 화면에서 사용가능
    // localhost:8080/ex5?title=lsy&writer=이상용
    @GetMapping("/ex5")
    public void ex5(TodoDTO todoDTO, Model model) {
        log.info("ex5 : " + todoDTO);
    }

    // 리다이렉트시 데이터 전달
    // 1) 키:값으로 데이터 전달
    // 2) 일화용으로 데이터 전달 예시
    @GetMapping("/ex6")
    public String ex6(RedirectAttributes redirectAttributes) {
        log.info("ex6");
        // 키:값으로 데이터 전달
        // 서버 -> 웹으로 전달
        // 화면에서 데이터 탑재 후 전달
        // 주의사항 : 퀴리스트링으로 보냄(http://localhost:8080/ex7?msg=test+data) -> ex7에서 받는 변수가 필요함
        redirectAttributes.addAttribute("msg", "test data");
        // 화면에서 일회용으로 바로 사용 가능
        redirectAttributes.addFlashAttribute("msg2", "일회용으로 사용");

        return "redirect:/ex7";
    }

    @GetMapping("/ex7")
    public void ex7(String msg, Model model) {
        log.info("ex7");
        log.info("msg : " + msg);
        model.addAttribute("msg", msg);
    }

    // 예외발생 (타입불일치 예제)
    // localhost:8080/ex8?name=ihan&age=26
    @GetMapping("/ex8")
    public void ex8(String name, int age) {
        log.info("ex8 name : " + name);
        log.info("ex8 age : " + age);
    }
}
