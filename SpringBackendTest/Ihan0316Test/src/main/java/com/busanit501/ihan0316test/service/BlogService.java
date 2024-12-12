package com.busanit501.ihan0316test.service;

import com.busanit501.ihan0316test.dto.PageRequestDTO;
import com.busanit501.ihan0316test.dto.PageResponseDTO;
import com.busanit501.ihan0316test.dto.BlogDTO;

import java.util.List;

public interface BlogService {
    void register(BlogDTO blogDTO);

//    List<BlogDTO> getAll();

    PageResponseDTO<BlogDTO> selectList(PageRequestDTO pageRequestDTO);

    BlogDTO getOne(Long tno);

    void delete(Long tno);

    void update(BlogDTO blogDTO);
}
