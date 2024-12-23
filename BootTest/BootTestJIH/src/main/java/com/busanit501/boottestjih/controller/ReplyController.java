package com.busanit501.boottestjih.controller;


import com.busanit501.boottestjih.dto.PageRequestDTO;
import com.busanit501.boottestjih.dto.PageResponseDTO;
import com.busanit501.boottestjih.dto.ReplyBlogDTO;
import com.busanit501.boottestjih.service.ReplyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/replies")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @Tag(name = "댓글 등록 post 방식",
            description = "댓글 등록을 진행함, post 형식으로")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Map<String,Long>> register(
            @Valid @RequestBody ReplyBlogDTO replyBlogDTO,
            BindingResult bindingResult
    ) throws BindException {
        log.info(" ReplyController replyBlogDTO: ", replyBlogDTO);
        // 확인용, 더미 데이터 ,

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        Long rno = replyService.register(replyBlogDTO);

        Map<String,Long> map = Map.of("rno",rno);
        return ResponseEntity.ok(map);
    }

    // 댓글 목록 전체 조회
    @Tag(name = "댓글 목록 조회",description = "댓글 목록 조회 RESTful get방식")
    @GetMapping(value ="/list/{blogno}")
    public PageResponseDTO<ReplyBlogDTO> getList(@PathVariable("blogno") Long blogno, PageRequestDTO pageRequestDTO)
    {
        log.info(" ReplyController getList: blogno={}", blogno);
        PageResponseDTO<ReplyBlogDTO> responseDTO = replyService.listWithReplyBlog(blogno, pageRequestDTO);
        return responseDTO;
    }

    // 댓글 목록 하나 조회
    @Tag(name = "댓글 하나 조회",description = "댓글 하나 조회 RESTful get방식")
    @GetMapping(value ="/{rno}")
    public ReplyBlogDTO getOne(@PathVariable("rno") Long rno)
    {
        log.info(" ReplyController getOne: rno={}", rno);
        ReplyBlogDTO replyBlogDTO = replyService.readOne(rno);
        return replyBlogDTO;
    }

    // 수정 로직 처리
    @Tag(name = "댓글 하나 수정 로직처리",description = "댓글 하나 수정 로직처리 RESTful get방식")
    @PutMapping(value ="/{rno}")
    public Map<String, Long> updateOne(@Valid @RequestBody ReplyBlogDTO replyBlogDTO,
                                       BindingResult bindingResult,
                                       @PathVariable("rno") Long rno) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        log.info(" ReplyController getOne: replyBlogDTO={}", replyBlogDTO);
        log.info(" ReplyController getOne: rno={}", rno);
        replyService.update(replyBlogDTO);
        Map<String, Long> map = Map.of("rno",rno);
        return map;
    }

    // 삭제 로직 처리
    @Tag(name = "댓글 하나 삭제 로직처리",description = "댓글 하나 삭제 로직처리 RESTful get방식")
    @DeleteMapping(value ="/{rno}")
    public Map<String, Long> deleteOne(
                                       @PathVariable("rno") Long rno) {
        log.info(" ReplyController getOne: rno={}", rno);
        replyService.delete(rno);
        Map<String, Long> map = Map.of("rno",rno);
        return map;
    }
}





