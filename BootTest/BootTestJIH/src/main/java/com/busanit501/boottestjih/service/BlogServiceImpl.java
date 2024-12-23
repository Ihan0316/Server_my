package com.busanit501.boottestjih.service;

import com.busanit501.boottestjih.domain.Blog;
import com.busanit501.boottestjih.dto.BlogDTO;
import com.busanit501.boottestjih.dto.BlogListReplyCountDTO;
import com.busanit501.boottestjih.dto.PageRequestDTO;
import com.busanit501.boottestjih.dto.PageResponseDTO;
import com.busanit501.boottestjih.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BlogServiceImpl implements BlogService {

    //맵퍼에게 의존 해야함.
    // 디비 작업 도구,
    private final BlogRepository blogRepository;
    // DTO <-> Entity class
    private final ModelMapper modelMapper;

    @Override
    public Long register(BlogDTO blogDTO) {
        Blog blog = modelMapper.map(blogDTO, Blog.class);
        Long blogno = blogRepository.save(blog).getBlogno();
        return blogno;
    }

    @Override
    public BlogDTO readOne(Long blogno) {
        Optional<Blog> result = blogRepository.findById(blogno);
        Blog blog= result.orElseThrow();
        BlogDTO dto = modelMapper.map(blog, BlogDTO.class);
        return dto;
    }

    @Override
    public void update(BlogDTO blogDTO) {
        Optional<Blog> result = blogRepository.findById(blogDTO.getBlogno());
        Blog blog = result.orElseThrow();
        blog.changeTitleConent(blogDTO.getTitle(),blogDTO.getContent());
        blogRepository.save(blog);
    }

    @Override
    public void delete(Long blogno) {
        blogRepository.deleteById(blogno);
    }

    @Override
    public PageResponseDTO<BlogDTO> list(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("blogno");

        Page<Blog> result = blogRepository.searchAll(types,keyword,pageable);
        // list -> PageResponseDTO 타입으로 변경 필요.

        // result.getContent() -> 페이징된 엔티티 클래스 목록
        List<BlogDTO> dtoList = result.getContent().stream()
                .map(blog ->modelMapper.map(blog, BlogDTO.class))
                .collect(Collectors.toList());


        return PageResponseDTO.<BlogDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();

    } // list

    @Override
    public PageResponseDTO<BlogListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO) {

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("blogno");

        // 수정1
        Page<BlogListReplyCountDTO> result = blogRepository.searchWithReplyCount(types,keyword,pageable);
        // list -> PageResponseDTO 타입으로 변경 필요.

        // result.getContent() -> 페이징된 엔티티 클래스 목록
        // Projection.bean 이용해서, 데이터 조회시 , 바로 dto 변환을 다했음.
        // 변환 작업이 필요가 없음.
//        List<BlogDTO> dtoList = result.getContent().stream()
//                .map(blog ->modelMapper.map(blog, BlogDTO.class))
//                .collect(Collectors.toList());


        return PageResponseDTO.<BlogListReplyCountDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(result.getContent())
                .total((int) result.getTotalElements())
                .build();
    }
}
