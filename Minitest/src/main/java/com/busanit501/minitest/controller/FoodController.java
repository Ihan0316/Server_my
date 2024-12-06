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

@Controller
@RequestMapping("/food")
@Log4j2
public class FoodController {
    @Autowired
    private FoodService foodService;

    // localhost:8080/food/list
    @RequestMapping("/list")
    public void list(@Valid PageRequestDTO pageRequestDTO ,
                     BindingResult bindingResult,
                     Model model) {
        log.info("FoodController list : 화면제공은 해당 메서드 명으로 제공함.");
        if(bindingResult.hasErrors()) {
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        PageResponseDTO<FoodDTO> pageResponseDTO = foodService.getListWithPage(pageRequestDTO);
        log.info("FoodController list 데이터 유무 확인 :" + pageResponseDTO);
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
        foodService.register(foodDTO);
        return "redirect:/food/list";
    }


    @RequestMapping("/read")
    public void read(Long fno, @Valid PageRequestDTO pageRequestDTO, Model model) {
        log.info("FoodController read");
        FoodDTO foodDTO = foodService.getOne(fno);
        log.info("FoodController read 데이터 유무 확인 :" + foodDTO);
        model.addAttribute("foodDTO", foodDTO);
    }

    @RequestMapping("/update")
    public void update(Long fno, @Valid PageRequestDTO pageRequestDTO, Model model) {
        log.info("FoodController update");
        FoodDTO foodDTO = foodService.getOne(fno);
        log.info("FoodController update 데이터 유무 확인 :" + foodDTO);
        model.addAttribute("foodDTO", foodDTO);
    }

    @PostMapping("/update")
    public String updateLogic(@Valid FoodDTO foodDTO, BindingResult bindingResult, PageRequestDTO pageRequestDTO,
                              RedirectAttributes redirectAttributes) {

        // 유효성 체크 -> 유효성 검증시, 통과 안된 원인이 있다면,
        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");
            // 1회용으로, 웹 브라우저에서, errors , 키로 조회 가능함. -> 뷰 ${errors}
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            //redirectAttributes 이용해서, 쿼리 스트링으로 전달.
            redirectAttributes.addAttribute("fno",foodDTO.getFno());
            redirectAttributes.addAttribute("page",pageRequestDTO.getPage());
            redirectAttributes.addAttribute("size",pageRequestDTO.getSize());
            return "redirect:/food/update";
        }

        log.info("foodDTO확인 finished의 변환 여부 확인. : " + foodDTO);

        foodService.update(foodDTO);
        redirectAttributes.addAttribute("page",pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size",pageRequestDTO.getSize());
        return "redirect:/food/list";
    }

    @PostMapping("/delete")
    public String delete(Long fno, PageRequestDTO pageRequestDTO,
                         RedirectAttributes redirectAttributes) {
        foodService.delete(fno);
        redirectAttributes.addAttribute("page",pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size",pageRequestDTO.getSize());
        return "redirect:/food/list";
    }
}
