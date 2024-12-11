package com.busanit501.boot501.repository;

import com.busanit501.boot501.domain.Board;
import com.busanit501.boot501.repository.search.BoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//extends JpaRepository<Board, Long> -> 기본 쿼리 메소드 이용해서,
// 간단한 crud 디비 작업은, 메서드를 이용해서 처리가 가능함.

// Querydsl 이용시, 만들었던 인터페이스 추가 구현해야됨
public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
//public interface BoardRepository extends JpaRepository<Board, Long> {
    // 아무 메서드가 없다.
    // 하지만 우리는 기본 탑재된 쿼리 메서드를 활용할 예정

    // 쿼리스트링 방법1) 전달
    Page<Board> findByTitleContainingOrderByBnoDesc(String title, Pageable pageable);

    // @Query 방법2) 전달, JPQL 문법, 모든 관계형 디비에 적용 됨
    @Query("select b from Board b where b.title like concat('%',:keyword,'%')")
    Page<Board> findByKeyword(String keyword, Pageable pageable);

    // Querydsl 도구 이용해서, 방법3)
    // BoardSearch 인터페이스에 구현, 이 인터페이스에서 구현한 클래스에서 문법 사용
    // BoardSearchImpl 구현한 클래스의 이름, 구현체
}
