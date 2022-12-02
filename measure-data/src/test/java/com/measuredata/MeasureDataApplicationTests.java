package com.measuredata;

import com.measuredata.service.MeasureHeatService;
import com.measuredata.service.MeasureRainfallService;
import com.measuredata.service.MeasureSunshineService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MeasureDataApplicationTests {

    @Autowired
    private MeasureRainfallService measureRainfallService;

    @Autowired
    private MeasureSunshineService measureSunshineService;

    @Autowired
    private MeasureHeatService measureHeatService;

    @Test
    void rainfallWrite2DB() {
        measureRainfallService.write2DB();
    }


    @Test
    void sunShineWrite2DB() {
        measureSunshineService.write2DB();
    }

    @Test
    void heatWrite2DB() {
        measureHeatService.write2DB();
    }

}
