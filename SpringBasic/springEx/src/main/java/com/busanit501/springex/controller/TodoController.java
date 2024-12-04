package com.busanit501.springex.controller;

import com.busanit501.springex.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
    //@Valid TodoDTO todoDTO -> 검사 대상 클래스
    // BindingResult bindingResult -> 검사 결과의 오류를 모아두는 임시 저장소
    // RedirectAttributes redirectAttributes -> 서버-> 웹, 데이터를 전달하는 도구
    public String registerPost(@Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("TodoController register post 로직처리: ");
        log.info("TodoController register post  todoDTO : " + todoDTO);

        // 유효성 체크 -> 유효성 검증시, 통과 안된 원인이 있다면,
        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");
            // 1회용으로, 웹 브라우저에서, errors , 키로 조회 가능함. -> 뷰 ${errors}
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/todo/register";
        }
        //검사가 통과가 되고, 정상 입력
        return "redirect:/todo/list";
    }
}
