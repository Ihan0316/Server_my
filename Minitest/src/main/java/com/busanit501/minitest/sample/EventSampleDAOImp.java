package com.busanit501.minitest.sample;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;


@Repository
@Qualifier("event")
public class EventSampleDAOImp implements SampleDAO{
}
