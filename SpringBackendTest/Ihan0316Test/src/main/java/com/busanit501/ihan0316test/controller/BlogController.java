package com.busanit501.ihan0316test.controller;

import com.busanit501.ihan0316test.dto.BlogDTO;
import com.busanit501.ihan0316test.dto.PageRequestDTO;
import com.busanit501.ihan0316test.dto.PageResponseDTO;
import com.busanit501.ihan0316test.service.BlogService;
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
@RequestMapping("/blog")

@Log4j2
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @RequestMapping("/list")
    public String list(@Valid PageRequestDTO pageRequestDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       Model model) {
        log.info("BlogController list : 화면제공은 해당 메서드 명으로 제공함.");
        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/blog/list";
        }
        PageResponseDTO<BlogDTO> pageResponseDTO = blogService.selectList(pageRequestDTO);
        log.info("BlogController list 데이터 유무 확인 :" + pageResponseDTO);
        //데이터 탑재. 서버 -> 웹
        model.addAttribute("pageResponseDTO", pageResponseDTO);
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "/blog/list";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void register() {
        log.info("BlogController register : 화면제공은 해당 메서드 명으로 제공함.");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(@Valid BlogDTO blogDTO, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        log.info("BlogController register post  blogDTO : " + blogDTO);


        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/blog/register";
        }

        blogService.register(blogDTO);

        return "redirect:/blog/list";
    }

    @RequestMapping("/read")
    public String read(Long rno, @Valid PageRequestDTO pageRequestDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       Model model) {
        log.info("BlogController read :");
        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("rno", rno);
            return "redirect:/blog/read";
        }
        BlogDTO blogDTO = blogService.getOne(rno);
        log.info("BlogController read 데이터 유무 확인 :" + blogDTO);
        log.info("BlogController read 데이터 유무 확인 pageRequestDTO :" + pageRequestDTO);
        //데이터 탑재. 서버 -> 웹
        model.addAttribute("blogDTO", blogDTO);
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "/blog/read";

    }

    @RequestMapping("/update")
    public String update(Long rno, @Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, Model model) {
        log.info("BlogController update :");
        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("rno", rno);

            return "redirect:/blog/update";
        }
        BlogDTO blogDTO = blogService.getOne(rno);
        log.info("BlogController update 데이터 유무 확인 :" + blogDTO);

        model.addAttribute("blogDTO", blogDTO);
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "/blog/update";
    }

    @PostMapping("/update")
    public String updateLogic(@Valid BlogDTO blogDTO, BindingResult bindingResult,
                              @Valid PageRequestDTO pageRequestDTO,
                              BindingResult pageBindingResult,
                              RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함. 검사 대상 :BlogDTO ");

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            redirectAttributes.addAttribute("rno", blogDTO.getRno());
            redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
            redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
            return "redirect:/blog/update" + pageRequestDTO.getLink();
        }

        if (pageBindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함. 검사 대상 :PageRequestDTO ");

            redirectAttributes.addFlashAttribute("errors2", pageBindingResult.getAllErrors());

            redirectAttributes.addAttribute("rno", blogDTO.getRno());
            redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
            redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
            return "redirect:/blog/update" + pageRequestDTO.getLink();
        }

        log.info("blogDTO확인 finished의 변환 여부 확인2. : " + blogDTO);
        log.info("BlogController update pageRequestDTO : " + pageRequestDTO);

        blogService.update(blogDTO);

        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "redirect:/blog/list?" + pageRequestDTO.getLink();
    }

    @PostMapping("/delete")
    public String delete(Long rno, PageRequestDTO pageRequestDTO,
                         RedirectAttributes redirectAttributes
    ) {
        blogService.delete(rno);
        log.info("BlogController delete : pageRequestDTO " + pageRequestDTO);

        return "redirect:/blog/list?" + pageRequestDTO.getLink();
    }
}








