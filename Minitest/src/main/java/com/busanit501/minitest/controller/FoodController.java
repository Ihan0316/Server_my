package com.busanit501.minitest.controller;

import com.busanit501.minitest.dto.FoodDTO;
import com.busanit501.minitest.dto.PageRequestDTO;
import com.busanit501.minitest.dto.PageResponseDTO;
import com.busanit501.minitest.service.FoodService;
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

@Controller
@RequestMapping("/food") // 경로지정, /food 경로로 오는 모든 url은 이 컨트롤러가 받아서 작업함
@Log4j2
public class FoodController {
    @Autowired
    private FoodService foodService;

    // localhost:8080/food/list
    @RequestMapping("/list")
    // 기존 전체 페이지 출력 -> 페이징 처리된 목록 요소만 출력.
//    public void list(Model model) {
    public void list(@Valid PageRequestDTO pageRequestDTO ,
                     BindingResult bindingResult,
                     Model model) {
        log.info("FoodController list : 화면제공은 해당 메서드 명으로 제공함.");
        if(bindingResult.hasErrors()) {
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        PageResponseDTO<FoodDTO> pageResponseDTO = foodService.getListWithPage(pageRequestDTO);
        log.info("FoodController list 데이터 유무 확인 :" + pageResponseDTO);
        //데이터 탑재. 서버 -> 웹
        model.addAttribute("pageResponseDTO", pageResponseDTO);

    }
    // 1) 글작성 폼 -> get
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void register() {
        log.info("FoodController register : 화면제공은 해당 메서드 명으로 제공함");
    }

    // 2) 글작성 로직 처리 -> post
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(@Valid FoodDTO foodDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("FoodController register post 로직처리: ");
        log.info("FoodController register post foodDTO : " + foodDTO);

        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/food/register";
        }
        //검사가 통과가 되고, 정상 입력
        foodService.register(foodDTO);
        return "redirect:/food/list";
    }


    @RequestMapping("/read")
    public void read(Long fno, Model model) {
        log.info("FoodController read");
        FoodDTO foodDTO = foodService.getOne(fno);
        log.info("FoodController read 데이터 유무 확인 :" + foodDTO);
        model.addAttribute("foodDTO", foodDTO);
    }

    // 수정, 수정폼, 로직
    @RequestMapping("/update")
    public void update(Long fno, Model model) {
        log.info("FoodController update");
        FoodDTO foodDTO = foodService.getOne(fno);
        log.info("FoodController update 데이터 유무 확인 :" + foodDTO);
        model.addAttribute("foodDTO", foodDTO);
    }

    //수정 로직 처리
    @PostMapping("/update")
    // 수정할 항목을 모두 받아서, FoodDTO 담습니다. 여기에 tno 도 포함 시키기
    public String updateLogic(@Valid FoodDTO foodDTO, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        // 유효성 체크 -> 유효성 검증시, 통과 안된 원인이 있다면,
        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");
            // 1회용으로, 웹 브라우저에서, errors , 키로 조회 가능함. -> 뷰 ${errors}
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/food/update";
        }

        // 수정하는 로직 필요함.
        // 주의사항, 체크박스의 값의 문자열 on 전달 받습니다.
        log.info("foodDTO확인 finished의 변환 여부 확인. : " + foodDTO);

        foodService.update(foodDTO);
        return "redirect:/food/list";
    }

    // 삭제
    @PostMapping("/delete")
    public String delete(Long fno) {
        foodService.delete(fno);
        return "redirect:/food/list";
    }
}
