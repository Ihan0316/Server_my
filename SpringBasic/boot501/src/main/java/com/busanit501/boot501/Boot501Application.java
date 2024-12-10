package com.busanit501.boot501;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // 베이스 엔티티 이용 가능하게 전역 설정
public class Boot501Application {

    public static void main(String[] args) {
        SpringApplication.run(Boot501Application.class, args);
    }

}
