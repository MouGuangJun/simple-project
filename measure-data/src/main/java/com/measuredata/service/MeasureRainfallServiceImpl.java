package com.measuredata.service;

import com.measuredata.entity.MeasureRainfall;
import com.measuredata.mapper.MeasureRainfallMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Objects;

@Service
@Slf4j
public class MeasureRainfallServiceImpl implements MeasureRainfallService {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @SneakyThrows
    @Override
    public void write2DB() {
        // 文件夹
        File dir = new File("D:\\测试工程\\降水\\第二次");
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file));
                 SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false)) {
                String str;
                HashSet<MeasureRainfall> measureRainfalls = new HashSet<>();
                while ((str = reader.readLine()) != null) {
                    String formatStr = str.replaceAll(" +", " ");
                    String[] datas = formatStr.split(" ");
                    if (datas.length != 13) {
                        log.error("文件【{}】中的【{}】数据不合规！", file.getName(), str);
                        continue;
                    }
                    // 执行插入操作
                    MeasureRainfall measureRainfall = new MeasureRainfall();
                    measureRainfall.setSite(datas[0]);
                    measureRainfall.setLatitude(datas[1]);
                    measureRainfall.setLongitude(datas[2]);
                    measureRainfall.setAltitude(datas[3]);
                    measureRainfall.setYear(datas[4]);
                    measureRainfall.setMonth(datas[5]);
                    measureRainfall.setDay(datas[6]);
                    measureRainfall.setRainfall208(datas[7]);
                    measureRainfall.setRainfall820(datas[8]);
                    measureRainfall.setRainfall2020(datas[9]);
                    measureRainfall.setRainfallCode208(datas[10]);
                    measureRainfall.setRainfallCode820(datas[11]);
                    measureRainfall.setRainfallCode2020(datas[12]);
                    boolean added = measureRainfalls.add(measureRainfall);
                    if (!added) {
                        log.error("文件【{}】中的【{}】数据存在重复！", file.getName(), str);
                    }
                }

                // 执行数据库操作
                int index = 0;
                MeasureRainfallMapper measureRainfallMapper = sqlSession.getMapper(MeasureRainfallMapper.class);
                for (MeasureRainfall measureRainfall : measureRainfalls) {
                    measureRainfallMapper.insert(measureRainfall);
                    index++;

                    // 每10000条提交一次
                    if (index % 10000 == 0) {
                        sqlSession.commit();
                        log.error("文件【{}】同步数据到数据库成功，此次同步{}条数据！", file.getName(), 10000);
                    }
                }

                // 最后再进行一次提交操作
                sqlSession.commit();
                log.error("文件【{}】最后一次同步数据到数据库成功，此次同步{}条数据！", file.getName(), index % 10000);
            }
        }
    }
}
