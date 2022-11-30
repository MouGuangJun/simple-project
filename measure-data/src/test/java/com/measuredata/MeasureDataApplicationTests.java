package com.measuredata;

import com.measuredata.service.MeasureRainfallService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MeasureDataApplicationTests {

    @Autowired
    private MeasureRainfallService measureRainfallService;

    @Test
    void rainfallWrite2DB() {
        measureRainfallService.write2DB();
    }

}
