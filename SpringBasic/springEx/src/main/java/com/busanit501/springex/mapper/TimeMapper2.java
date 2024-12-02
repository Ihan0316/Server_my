package com.busanit501.springex.mapper;

public interface TimeMapper2 {
    // 맵퍼 인터페이스, xml 파일 이름 동일
    // 메서드로 사용하는 이름과, xml 파일에서의 id이름 동일
    // Main/resource/mappers 폴더 만들기
    // TimeMapper2.xml 파일 생성
    String getNow();
}
