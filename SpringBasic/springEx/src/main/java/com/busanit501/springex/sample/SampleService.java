package com.busanit501.springex.sample;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// 컨트롤러 역할 @Controller
// 서비스 역할 @Service
// DAO 역할 @Repository
@Component
@Service
@ToString
@RequiredArgsConstructor
public class SampleService {
    // 이용하는 클래스, SampleDAO에 의존하는 클래스,

    // 방법1, 필드 주입 방식
    //    @Autowired
    //   private  SampleDAO sampleDAO;

    // 방법2, 생성자 주입 방식.
    // -> 1) 생성자로 주입 2) 롬복 애너테이션 이용

//    private final SampleDAO sampleDAO;
//1) 생성자로 주입
//    public SampleService(SampleDAO sampleDAO) {
//        this.sampleDAO = sampleDAO;
//    }

    //2) 롬복 애너테이션 이용
    // 클래스 상단에 , 애너테이션 @RequiredArgsConstructor 붙이면 됨.
    // SampleDAO -> 타입: 인터페이스, sampleDAO,
    // 구현한 클래스 2개가 시스템 상에 등록이 되었다.
//    @Qualifier("normal") // normal을 이용하라고 지정함
    @Qualifier("event") // event을 이용하라고 지정함
    private final SampleDAO sampleDAO;
}


