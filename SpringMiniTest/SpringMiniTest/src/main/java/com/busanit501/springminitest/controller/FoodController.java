package com.busanit501.springminitest.controller;

import com.busanit501.springminitest.dto.FoodDTO;
import com.busanit501.springminitest.dto.FoodListReplyCountDTO;
import com.busanit501.springminitest.dto.PageResponseDTO;
import com.busanit501.springminitest.service.FoodService;
import com.busanit501.springminitest.dto.PageRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@Log4j2
@RequestMapping("/food")
@RequiredArgsConstructor

public class FoodController {
    private final FoodService foodService;
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model ) {
        PageResponseDTO<FoodListReplyCountDTO> responseDTO = foodService.listWithReplyCount(pageRequestDTO);
        log.info("pageRequestDTO 의 getLink 조사 : " + pageRequestDTO.getLink());
        model.addAttribute("responseDTO", responseDTO);
    }

    @GetMapping("/register")
    public void register() {

    }
    @PostMapping("/register")
    public String registerPost(@Valid FoodDTO foodDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        log.info("FoodController register post 로직처리: ");
        log.info("FoodController register post  foodDTO : " + foodDTO);

        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/food/register";
        }
        Long fno = foodService.register(foodDTO);

        redirectAttributes.addFlashAttribute("result", fno);
        redirectAttributes.addFlashAttribute("resultType", "register");

        return "redirect:/food/list";

    }

    @GetMapping("/read")
    public void read(Long fno, PageRequestDTO pageRequestDTO,
                     Model model) {
        FoodDTO foodDTO = foodService.readOne(fno);
        model.addAttribute("dto", foodDTO);
    }

    @GetMapping("/update")
    public void update(Long fno, PageRequestDTO pageRequestDTO,
                       Model model) {
        FoodDTO foodDTO = foodService.readOne(fno);
        model.addAttribute("dto", foodDTO);
    }

    @PostMapping("/update")
    public String updatePost(@Valid FoodDTO foodDTO,
                             BindingResult bindingResult,
                             PageRequestDTO pageRequestDTO,
                             String keyword2,String page2, String type2,
                             RedirectAttributes redirectAttributes) {
        log.info("FoodController updatePost post 로직처리: ");
        log.info("FoodController updatePost post  foodDTO : " + foodDTO);

        log.info("FoodController updatePost post  pageRequestDTO : " + pageRequestDTO);

        String encodedKeyword = URLEncoder.encode(keyword2, StandardCharsets.UTF_8);

        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");
            // 1회용으로, 웹 브라우저에서, errors , 키로 조회 가능함. -> 뷰 ${errors}
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/food/update?fno="+foodDTO.getFno()+"&keyword="+encodedKeyword+"&page="+page2+"&type="+type2;
        }
        foodService.update(foodDTO);

        redirectAttributes.addFlashAttribute("result", foodDTO.getFno());
        redirectAttributes.addFlashAttribute("resultType", "update");

        return "redirect:/food/read?fno="+foodDTO.getFno()+"&keyword="+encodedKeyword+"&page="+page2+"&type="+type2;

    }

    @PostMapping("/delete")
    public String delete(Long fno,
                         String keyword2,String page2, String type2,
                         RedirectAttributes redirectAttributes) {
        foodService.delete(fno);
        //키워드 한글 처리.
        String encodedKeyword = URLEncoder.encode(keyword2, StandardCharsets.UTF_8);

        redirectAttributes.addFlashAttribute("result", fno);
        redirectAttributes.addFlashAttribute("resultType", "delete");
        return "redirect:/food/list?"+"&keyword="+encodedKeyword+"&page="+page2+"&type="+type2;
    }

}
