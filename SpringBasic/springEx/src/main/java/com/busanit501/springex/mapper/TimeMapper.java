package com.busanit501.springex.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
    // 방법1, 맵퍼 인터페이스에서 어노테이션 이용해서 디비 호출
    @Select("select now()")
    String getNow(); // 추상메서드

    // 방법2, sql문을 xml파일로 분리해서 이용

}
