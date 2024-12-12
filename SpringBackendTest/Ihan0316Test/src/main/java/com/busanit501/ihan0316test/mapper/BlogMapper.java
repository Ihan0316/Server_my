package com.busanit501.ihan0316test.mapper;

import com.busanit501.ihan0316test.domain.BlogVO;
import com.busanit501.ihan0316test.dto.PageRequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogMapper {

    void insert(BlogVO todoVO);

    List<BlogVO> selectAll();

    BlogVO selectOne(Long tno);

    void delete(Long tno);

    void update(BlogVO todoVO);

    List<BlogVO> selectList(PageRequestDTO pageRequestDTO);

    int getCount(PageRequestDTO pageRequestDTO);
}





