package com.busanit501.boot501.repository.search;

import com.busanit501.boot501.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

// 이름 작성시 : 인터페이스 이름 + Impl
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch{

    // 부모 클래스 초기화, 사용하는 엔티티 클래스 설정
    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> search(Pageable pageable) {
        return null;
    }
}
