package com.busanit501.minitest.sample;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("normal")
public class SampleDAOImp implements SampleDAO {
}
