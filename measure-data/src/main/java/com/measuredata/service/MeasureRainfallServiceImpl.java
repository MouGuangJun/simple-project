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
        File dir = new File("D:\\BaiduNetdiskDownload\\测试数据\\降水");
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file));
                 SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false)) {
                String str;
                MeasureRainfallMapper measureRainfallMapper = sqlSession.getMapper(MeasureRainfallMapper.class);
                int index = 0;
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
                    measureRainfallMapper.insert(measureRainfall);
                    index++;

                    if (index % 10000 == 0) {
                        sqlSession.commit();
                        log.error("同步数据到数据库成功，此次同步{}条数据！", 10000);
                    }
                }
                // 最后再进行一次提交操作
                sqlSession.commit();
                log.error("最后一次同步数据到数据库成功，此次同步{}条数据！", index % 10000);
            }
        }
    }
}
