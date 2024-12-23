package com.busanit501.boottestjih.service;


import com.busanit501.boottestjih.domain.Blog;
import com.busanit501.boottestjih.domain.Reply;
import com.busanit501.boottestjih.dto.PageRequestDTO;
import com.busanit501.boottestjih.dto.PageResponseDTO;
import com.busanit501.boottestjih.dto.ReplyBlogDTO;
import com.busanit501.boottestjih.repository.BlogRepository;
import com.busanit501.boottestjih.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    private final BlogRepository blogRepository;

    private final ModelMapper modelMapper;

    @Override
    public Long register(ReplyBlogDTO replyBlogDTO) {
        // 화면에서 받은 데이터 DTO 타입 -> 엔티티 타입으로 변경,
        // replyBlogDTO, bno 값이 존재.
        log.info("Registering new replyBlogDTO: " + replyBlogDTO);
        Reply reply = modelMapper.map(replyBlogDTO, Reply.class);
        Optional<Blog> result = blogRepository.findById(replyBlogDTO.getBno());
        Blog blog = result.orElseThrow();
        reply.changeBlog(blog);
        log.info("Registering new reply: " + reply);
        Long rno = replyRepository.save(reply).getRno();
        return rno;
    }

    @Override
    public ReplyBlogDTO readOne(Long rno) {
        Optional<Reply> result = replyRepository.findById(rno);
        Reply reply = result.orElseThrow();
        ReplyBlogDTO replyBlogDTO = modelMapper.map(reply, ReplyBlogDTO.class);
        replyBlogDTO.setBno(reply.getBlog().getBno());
        return replyBlogDTO;
    }

    @Override
    public void update(ReplyBlogDTO replyBlogDTO) {
        Optional<Reply> result = replyRepository.findById(replyBlogDTO.getRno());
        Reply reply = result.orElseThrow();
        reply.changeReplyTextReplyer(replyBlogDTO.getReplyText(), replyBlogDTO.getReplyer());
        replyRepository.save(reply);
    }

    @Override
    public void delete(Long rno) {
        replyRepository.deleteById(rno);
    }

    @Override
    public PageResponseDTO<ReplyBlogDTO> listWithReplyBlog(Long bno, PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage()-1 <= 0 ? 0 : pageRequestDTO.getPage()-1, pageRequestDTO.getSize(),
                Sort.by("rno").ascending());

        Page<Reply> result = replyRepository.listOfBlog(bno, pageable);

        List<ReplyBlogDTO> dtoList = result.getContent().stream().map(reply -> {
            ReplyBlogDTO replyBlogDTO = modelMapper.map(reply, ReplyBlogDTO.class);
            replyBlogDTO.setBno(reply.getBlog().getBno());
            return replyBlogDTO;
        }).collect(Collectors.toList());

        PageResponseDTO<ReplyBlogDTO> pageResponseDTO = PageResponseDTO.<ReplyBlogDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();

        return pageResponseDTO;
    }
}
