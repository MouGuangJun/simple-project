package com.measuredata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.measuredata.mapper"})
public class MeasureDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeasureDataApplication.class, args);
    }

}
