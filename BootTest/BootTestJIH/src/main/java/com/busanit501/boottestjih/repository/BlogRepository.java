package com.busanit501.boottestjih.repository;

import com.busanit501.boottestjih.domain.Blog;
import com.busanit501.boottestjih.repository.search.BlogSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BlogRepository extends JpaRepository<Blog, Long> , BlogSearch {
//public interface BoardRepository extends JpaRepository<Board, Long>  {
    Page<Blog> findByTitleContainingOrderByBlognoDesc(String title, Pageable pageable);

    @Query("select b from Blog b where b.title like concat('%',:keyword,'%')")
    Page<Blog> findByKeyword(String keyword, Pageable pageable);

    @Query(value = "select now()" , nativeQuery = true)
    String now();



}
