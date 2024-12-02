package com.busanit501.springex.sample;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

// 문제점, SampleDAO, 인터페이스를 구현하는 클래스가 2개
// 시스템에 2개를 알려줌, 어느 클래스를 구현해야 하는지, 고민이 생김.
@Repository
@Primary // 이 클래스를 주 클래스로 이용하겠다고 시스템에 알림
public class EventSampleDAOImp implements SampleDAO{
}
