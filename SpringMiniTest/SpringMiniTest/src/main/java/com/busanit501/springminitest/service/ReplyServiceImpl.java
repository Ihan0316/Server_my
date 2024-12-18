package com.busanit501.springminitest.service;

import com.busanit501.springminitest.domain.Reply;
import com.busanit501.springminitest.dto.ReplyDTO;
import com.busanit501.springminitest.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    private final ModelMapper modelMapper;

    @Override
    public Long register(ReplyDTO replyDTO) {
        // 화면에서 받은 데이터 dto -> 엔티티 변경
        log.info("Registering new replyDTO: " + replyDTO);
        Reply reply = modelMapper.map(replyDTO, Reply.class);
        log.info("Registering new reply: " + replyDTO);
        Long rno = replyRepository.save(reply).getRno();
        return rno;
    }

    @Override
    public ReplyDTO readOne(Long rno) {
        Optional<Reply> result = replyRepository.findById(rno);
        Reply reply = result.orElseThrow();
        ReplyDTO replyDTO = modelMapper.map(reply, ReplyDTO.class);
        return replyDTO;
    }

    @Override
    public void update(ReplyDTO replyDTO) {
        Optional<Reply> result = replyRepository.findById(replyDTO.getRno());
        Reply reply = result.orElseThrow();
        reply.changeReplyText(replyDTO.getReplyText());
        replyRepository.save(reply);
    }

    @Override
    public void delete(Long rno) {
        replyRepository.deleteById(rno);
    }
}
