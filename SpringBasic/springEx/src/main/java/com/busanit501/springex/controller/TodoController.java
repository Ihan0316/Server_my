package com.busanit501.springex.controller;

import com.busanit501.springex.dto.TodoDTO;
import com.busanit501.springex.service.TodoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller // 화면, 데이터 제공
@RequestMapping("/todo") // 경로지정, /todo 경로로 오는 모든 url은 이 컨트롤ㄹ가 받아서 작업함
@Log4j2
public class TodoController {
    @Autowired
    private TodoService todoService;

    // localhost:8080/todo/list
    @RequestMapping("/list")
    public void list(Model model) {
        log.info("TodoController list : 화면제공은 해당 메서드 명으로 제공함");
        List<TodoDTO> list = todoService.getAll();
        log.info("TodoController list 데이터 유무 확인 :" + list);
        // 서버는 웹에 응답을 해야 됨
        // 데이터 탑재 -> 서버 -> 웹
        model.addAttribute("list", list);
    }

    // localhost:8080/todo/register
    // 1) 글작성 폼 -> get
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void register() {
        log.info("TodoController register : 화면제공은 해당 메서드 명으로 제공함");
    }

    // 2) 글작성 로직 처리 -> post
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    // @Valid TodoDTO todoDTO -> 검사 대상 클래스
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
        todoService.register(todoDTO);
        return "redirect:/todo/list";
    }

    // 상세조회 -> controller 서비스 연결부분
    // localhost:8080/todo/read?tno=9
    // 파라미터 자동 수집 많이 활용
    // tno 서버에서 바로 사용가능
    // 파라미터로 (TodoDTO todoDTO),
    // model.addAttribute("TodoDTO" todoDTO); 없이도
    // 뷰에서 -> EL표기법으로 ${todoDTO}
    @RequestMapping("/read")
    public void read(Long tno, Model model) {
        log.info("TodoController read");
       TodoDTO todoDTO = todoService.getOne(tno);
        log.info("TodoController read 데이터 유무 확인 :" + todoDTO);
        // 서버는 웹에 응답을 해야 됨
        // 데이터 탑재 -> 서버 -> 웹
        model.addAttribute("todoDTO", todoDTO);
    }

    // 수정, 수정폼, 로직
    @RequestMapping("/update")
    public void update(Long tno, Model model) {
        log.info("TodoController update");
        TodoDTO todoDTO = todoService.getOne(tno);
        log.info("TodoController update 데이터 유무 확인 :" + todoDTO);
        model.addAttribute("todoDTO", todoDTO);
    }

    // 삭제
    @PostMapping("/delete")
    public String delete(Long tno) {
        todoService.delete(tno);
        return "redirect:/todo/list";
    }

    // 페이징

    // 검색

    // 필터

}
