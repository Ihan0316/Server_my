package com.busanit501.minitest.sample;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
@ToString
@RequiredArgsConstructor
public class SampleService {
    @Qualifier("event")
    private final SampleDAO sampleDAO;
}


