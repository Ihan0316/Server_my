package com.busanit501.boot501.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity // JPA 이용하여 엔티티 클래스, DB 테이블 만들기.
@Getter // 비지니스 모델, 디비에는 불변성 유지, 수정을 안한다, 메서드를 이용해여 멤버 교체식으로 진행
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString // 로그를 해당 인스턴스 찍을 때, 해당 멤버를 쉽게 확인 가능
public class Board extends BaseEntity{ // 전역으로 만든, 베이스 엔티티 클래스 적용
    // 제약 조건 각 멤버는 각 디비의 컬럼과 동일
    // 각각의 제약조건을 설정한다 @Column 이용

    @Id // PK, 기본키, 중복방지
    // 오라클, 시퀀스 객체 이용시, SEQUENCE 설정, 추가로 필요
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 마리아 디비의 auto increment 사용
    private Long bno;

    @Column(length = 500, nullable = false)// 길이 500자, notNull
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String writer;
    // 모든 컬럼에 공통으로 들어가는 등록시간, 수정시간 등을 베이스 엔티티에서 작업예정

    // 해당 엔티티 클래스는 각 인스턴스가 해당 디비의 각 행 데이터와 동일
    // 바로 수정 불가하고, 조회만 하게 되고
    // 만약 수정이 필요하면, 메서드를 사용해서 안전하게 내용만 변경함
    public void changeTitleContent(String title, String content) {
        this.title = title;
        this.content = content;
    }
}