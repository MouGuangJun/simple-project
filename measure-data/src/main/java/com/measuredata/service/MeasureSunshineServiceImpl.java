package com.measuredata.service;

import com.measuredata.entity.MeasureSunshine;
import com.measuredata.mapper.MeasureSunshineMapper;
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
public class MeasureSunshineServiceImpl implements MeasureSunshineService {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @SneakyThrows
    @Override
    public void write2DB() {
        log.error("==========================日照开始==========================");
        log.error("==========================日照开始==========================");

        // 文件夹
        File dir = new File("D:\\测试工程\\日照");
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file)); SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false)) {
                String str;
                HashSet<MeasureSunshine> measureSunshines = new HashSet<>();
                while ((str = reader.readLine()) != null) {
                    String formatStr = str.replaceAll(" +", " ");
                    String[] datas = formatStr.split(" ");
                    if (datas.length != 9) {
                        log.error("文件【{}】中的【{}】数据不合规！", file.getName(), str);
                        continue;
                    }
                    // 执行插入操作
                    MeasureSunshine measureSunshine = new MeasureSunshine();
                    measureSunshine.setSite(datas[0]);
                    measureSunshine.setLatitude(datas[1]);
                    measureSunshine.setLongitude(datas[2]);
                    measureSunshine.setAltitude(datas[3]);
                    measureSunshine.setYear(datas[4]);
                    measureSunshine.setMonth(datas[5]);
                    measureSunshine.setDay(datas[6]);
                    measureSunshine.setSunshineHour(datas[7]);
                    measureSunshine.setSunshineHourCode(datas[8]);
                    boolean added = measureSunshines.add(measureSunshine);
                    if (!added) {
                        log.error("文件【{}】中的【{}】数据存在重复！", file.getName(), str);
                    }
                }

                // 执行数据库操作
                int index = 0;
                MeasureSunshineMapper measureSunshineMapper = sqlSession.getMapper(MeasureSunshineMapper.class);
                for (MeasureSunshine measureSunshine : measureSunshines) {
                    measureSunshineMapper.insert(measureSunshine);
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
