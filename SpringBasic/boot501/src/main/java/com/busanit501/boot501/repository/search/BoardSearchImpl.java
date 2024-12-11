package com.busanit501.boot501.repository.search;

import com.busanit501.boot501.domain.Board;
import com.busanit501.boot501.domain.QBoard;
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
    public Page<Board> search(Pageable pageable) {
        // 예시,
        QBoard qBoard = QBoard.board; // Q 도메인 객체
        JPQLQuery<Board> query = from(qBoard); // select * from board 랑 비슷함
        // select * from board 내용을 query 객체로 만듦
        // 다양한 쿼리 조건을 이용가능
        // where, groupBy, join, pagenation
        query.where(qBoard.title.contains("3"));
        //----------------------------------- 조건
        List<Board> list = query.fetch();
        // 해당 조건의 데이터 갯수 조회
        Long total = query.fetchCount();
        // 해당 조건의 데이터 가져오기

        return null;
    }
}
