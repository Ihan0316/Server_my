package com.busanit501.boottestjih.controller;

import com.busanit501.boottestjih.dto.BlogDTO;
import com.busanit501.boottestjih.dto.BlogListReplyCountDTO;
import com.busanit501.boottestjih.dto.PageRequestDTO;
import com.busanit501.boottestjih.dto.PageResponseDTO;
import com.busanit501.boottestjih.service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@Log4j2
@RequestMapping("/blog")
@RequiredArgsConstructor

public class BlogController {
    private final BlogService blogService;
    // http://localhost:8080/blog/list
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model ) {

        PageResponseDTO<BlogListReplyCountDTO> responseDTO = blogService.listWithReplyCount(pageRequestDTO);
        log.info("pageRequestDTO 의 getLink 조사 : " + pageRequestDTO.getLink());
        model.addAttribute("responseDTO", responseDTO);
    }

    //등록 작업, 1) 등록화면 2) 로직처리
    @GetMapping("/register")
    public void register() {

    }
    @PostMapping("/register")
    public String registerPost(@Valid BlogDTO blogDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        log.info("BlogController register post 로직처리: ");
        log.info("BlogController register post  blogDTO : " + blogDTO);

        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/blog/register";
        }

        Long blogno = blogService.register(blogDTO);

        redirectAttributes.addFlashAttribute("result", blogno);
        redirectAttributes.addFlashAttribute("resultType", "register");

        return "redirect:/blog/list";
    }

    @GetMapping("/read")
    public void read(Long blogno, Model model) {
        BlogDTO blogDTO = blogService.readOne(blogno);
        model.addAttribute("dto", blogDTO);
    }

    @GetMapping("/update")
    public void update(Long blogno, Model model) {
        BlogDTO blogDTO = blogService.readOne(blogno);
        model.addAttribute("dto", blogDTO);
    }

    @PostMapping("/update")
    public String updatePost(@Valid BlogDTO blogDTO,
                               BindingResult bindingResult,
                               PageRequestDTO pageRequestDTO,
                               String keyword2,String page2, String type2,
                               RedirectAttributes redirectAttributes) {
        log.info("BlogController updatePost post 로직처리: ");
        log.info("BlogController updatePost post  blogDTO : " + blogDTO);
        log.info("BlogController updatePost post  pageRequestDTO : " + pageRequestDTO);

        String encodedKeyword = URLEncoder.encode(keyword2, StandardCharsets.UTF_8);

        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/blog/update?blogno="+blogDTO.getBlogno()+"&keyword="+encodedKeyword+"&page="+page2+"&type="+type2;
        }
        blogService.update(blogDTO);

        redirectAttributes.addFlashAttribute("result", blogDTO.getBlogno());
        redirectAttributes.addFlashAttribute("resultType", "update");

        return "redirect:/blog/read?blogno="+blogDTO.getBlogno()+"&keyword="+encodedKeyword+"&page="+page2+"&type="+type2;

    }

    @PostMapping("/delete")
    public String delete(Long blogno,
                         String keyword2,String page2, String type2,
                         RedirectAttributes redirectAttributes) {
        blogService.delete(blogno);
        String encodedKeyword = URLEncoder.encode(keyword2, StandardCharsets.UTF_8);

        redirectAttributes.addFlashAttribute("result", blogno);
        redirectAttributes.addFlashAttribute("resultType", "delete");
        return "redirect:/blog/list?"+"&keyword="+encodedKeyword+"&page="+page2+"&type="+type2;
    }

}
