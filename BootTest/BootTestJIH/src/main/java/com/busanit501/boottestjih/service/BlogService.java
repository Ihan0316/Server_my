package com.busanit501.boottestjih.service;

import com.busanit501.boottestjih.dto.BlogDTO;
import com.busanit501.boottestjih.dto.BlogListReplyCountDTO;
import com.busanit501.boottestjih.dto.PageRequestDTO;
import com.busanit501.boottestjih.dto.PageResponseDTO;

public interface BlogService {
    Long register(BlogDTO blogDTO);
    BlogDTO readOne(Long blogno);
    void update(BlogDTO blogDTO);
    void delete(Long blogno);
    PageResponseDTO<BlogDTO> list(PageRequestDTO pageRequestDTO);
    PageResponseDTO<BlogListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);

}
