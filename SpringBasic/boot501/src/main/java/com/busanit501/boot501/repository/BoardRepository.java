package com.busanit501.boot501.repository;

import com.busanit501.boot501.domain.Board;
import com.busanit501.boot501.repository.search.BoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;

//extends JpaRepository<Board, Long> -> 기본 쿼리 메소드 이용해서,
// 간단한 crud 디비 작업은, 메서드를 이용해서 처리가 가능함.

// Querydsl 이용시, 만들었던 인터페이스 추가 구현해야됨
public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
    // 아무 메서드가 없다.
    // 하지만 우리는 기본 탑재된 쿼리 메서드를 활용할 예정
}
