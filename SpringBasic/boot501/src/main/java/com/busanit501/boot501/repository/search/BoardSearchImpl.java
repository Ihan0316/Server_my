package com.busanit501.boot501.repository.search;

import com.busanit501.boot501.domain.Board;
import com.busanit501.boot501.domain.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

// 이름 작성시 : 인터페이스 이름 + Impl
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch{

    // 부모 클래스 초기화, 사용하는 엔티티 클래스 설정
    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    // 자바문법으로 sql 명령어 대체
    public Page<Board> search(Pageable pageable) {
        // 예시,
        QBoard qBoard = QBoard.board; // Q 도메인 객체
        JPQLQuery<Board> query = from(qBoard); // select * from board 랑 비슷함
        // select * from board 내용을 query 객체로 만듦
        // 다양한 쿼리 조건을 이용가능
        // where, groupBy, join, pagenation
        query.where(qBoard.title.contains("3"));
        //----------------------------------- 조건1
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.or(qBoard.title.contains("3"));
        booleanBuilder.or(qBoard.content.contains("7"));
        // query에 해당 조건을 적용
        query.where(booleanBuilder);
        // 방법2, 추가 조건을 bno가 0보다 초과하는 조건
        query.where(qBoard.bno.gt(0L));
        //------------------------------------ 조건2
        // 페이징 조건 추가하기. query에 페이징 조건을 추가한 상황
        this.getQuerydsl().applyPagination(pageable, query);
        // 제목, 작성자 검색 조건 추가
        //------------------------------------ 조건3
        List<Board> list = query.fetch();
        // 해당 조건의 데이터 갯수 조회
        // 해당 조건의 데이터 가져오기
        Long total = query.fetchCount();

        return null;
    }
}
