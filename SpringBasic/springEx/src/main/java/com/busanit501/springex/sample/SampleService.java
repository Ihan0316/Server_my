package com.busanit501.springex.sample;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
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

    // 방법1, 필드 주입방식
//     @Autowired
//     private SampleDAO sampleDAO;

    // 방법2, 생성자 주입 방식 -> 1) 생성자로 주입, 2) 롬복 애너테이션 이용
    // 1) 생성자로 주입
//     public SampleService(SampleDAO sampleDAO) {
//     this.sampleDAO = sampleDAO;

    // 2) 롬복 애너테이션 이용
    // SampleDAO -> 타입 : 인터페이스 sampleDAO
    // 구현한 클래스 2개가 시스템에 등록되었다.
     private final SampleDAO sampleDAO;
     }


