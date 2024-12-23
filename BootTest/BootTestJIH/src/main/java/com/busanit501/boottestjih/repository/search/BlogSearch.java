package com.busanit501.boottestjih.repository.search;


import com.busanit501.boottestjih.domain.Blog;
import com.busanit501.boottestjih.dto.BlogListReplyCountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BlogSearch {
    Page<Blog> search(Pageable pageable);
    Page<Blog> searchAll(String[] types, String keyword, Pageable pageable);
    Page<BlogListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable);
}
