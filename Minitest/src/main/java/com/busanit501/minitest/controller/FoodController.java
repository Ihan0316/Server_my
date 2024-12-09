package com.busanit501.minitest.controller;

import com.busanit501.minitest.service.FoodService;
import com.busanit501.minitest.dto.PageRequestDTO;
import com.busanit501.minitest.dto.PageResponseDTO;
import com.busanit501.minitest.dto.FoodDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller// 1)화면 2)데이터 제공.
@RequestMapping("/food")
@Log4j2
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;


    @RequestMapping("/list")

    public String list(@Valid PageRequestDTO pageRequestDTO,
                     BindingResult bindingResult,
                     RedirectAttributes redirectAttributes,
                     Model model) {
        log.info("FoodController list : 화면제공은 해당 메서드 명으로 제공함.");
        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/food/list";
        }
        PageResponseDTO<FoodDTO> pageResponseDTO = foodService.selectList(pageRequestDTO);
        log.info("FoodController list 데이터 유무 확인 :" + pageResponseDTO);
        //데이터 탑재. 서버 -> 웹
        model.addAttribute("pageResponseDTO", pageResponseDTO);
        redirectAttributes.addAttribute("page",pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size",pageRequestDTO.getSize());
        return "/food/list";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void register() {
        log.info("FoodController register : 화면제공은 해당 메서드 명으로 제공함.");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(@Valid FoodDTO foodDTO, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        log.info("FoodController register post 로직처리: ");
        log.info("FoodController register post  foodDTO : " + foodDTO);

        // 유효성 체크 -> 유효성 검증시, 통과 안된 원인이 있다면,
        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");
            // 1회용으로, 웹 브라우저에서, errors , 키로 조회 가능함. -> 뷰 ${errors}
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/food/register";
        }
        //검사가 통과가 되고, 정상 입력
        foodService.register(foodDTO);

        return "redirect:/food/list";
    }

    @RequestMapping("/read")
    public String read(Long fno, @Valid PageRequestDTO pageRequestDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       Model model) {
        log.info("FoodController read :");
        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");
            // 1회용으로, 웹 브라우저에서, errors , 키로 조회 가능함. -> 뷰 ${errors}
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("fno", fno);
            return "redirect:/food/read";
        }
        FoodDTO foodDTO = foodService.getOne(fno);
        log.info("FoodController read 데이터 유무 확인 :" + foodDTO);
        log.info("FoodController read 데이터 유무 확인 pageRequestDTO :" + pageRequestDTO);
        //데이터 탑재. 서버 -> 웹
        model.addAttribute("foodDTO", foodDTO);
        redirectAttributes.addAttribute("page",pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size",pageRequestDTO.getSize());
        return "/food/read";

    }


    // 수정 1) 폼 2) 로직 처리
    @RequestMapping("/update")
    public String update(Long fno, @Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, Model model) {
        log.info("FoodController update :");
        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("fno", fno);
            return "redirect:/food/update";
        }
        FoodDTO foodDTO = foodService.getOne(fno);
        log.info("FoodController update 데이터 유무 확인 :" + foodDTO);
        //데이터 탑재. 서버 -> 웹
        model.addAttribute("foodDTO", foodDTO);
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "/food/update";

    }

    @PostMapping("/update")
    public String updateLogic(@Valid FoodDTO foodDTO, BindingResult bindingResult,
                              @Valid PageRequestDTO pageRequestDTO,
                              BindingResult pageBindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함. 검사 대상 :FoodDTO ");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("fno", foodDTO.getFno());
            redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
            redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
            return "redirect:/food/update?"+pageRequestDTO.getLink();
        }

        if (pageBindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함. 검사 대상 :PageRequestDTO ");
            redirectAttributes.addFlashAttribute("errors2", pageBindingResult.getAllErrors());
            //redirectAttributes 이용해서, 쿼리 스트링으로 전달.
            redirectAttributes.addAttribute("fno", foodDTO.getFno());
            redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
            redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
            return "redirect:/food/update?"+pageRequestDTO.getLink();
        }

        log.info("foodDTO확인 finished2의 변환 여부 확인. : " + foodDTO);
        log.info("FoodController update pageRequestDTO : "+ pageRequestDTO);

        foodService.update(foodDTO);
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "redirect:/food/list?"+pageRequestDTO.getLink();
    }

    // 삭제
    @PostMapping("/delete")
    public String delete(Long fno, PageRequestDTO pageRequestDTO,
                         RedirectAttributes redirectAttributes
    ) {
        foodService.delete(fno);
        log.info("FoodController delete : pageRequestDTO " + pageRequestDTO);

        return "redirect:/food/list?"+pageRequestDTO.getLink();
    }

    // 페이징,

    // 검색,

    // 필터

}








