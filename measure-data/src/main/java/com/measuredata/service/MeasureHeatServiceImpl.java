package com.measuredata.service;

import com.measuredata.entity.MeasureHeat;
import com.measuredata.mapper.MeasureHeatMapper;
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
public class MeasureHeatServiceImpl implements MeasureHeatService {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @SneakyThrows
    @Override
    public void write2DB() {
// 文件夹
        File dir = new File("D:\\测试工程\\温度\\第二次");
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file));
                 SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false)) {
                String str;
                HashSet<MeasureHeat> measureHeats = new HashSet<>();
                while ((str = reader.readLine()) != null) {
                    String formatStr = str.replaceAll(" +", " ");
                    String[] datas = formatStr.split(" ");
                    if (datas.length != 13) {
                        log.error("文件【{}】中的【{}】数据不合规！", file.getName(), str);
                        continue;
                    }
                    // 执行插入操作
                    MeasureHeat measureHeat = new MeasureHeat();
                    measureHeat.setSite(datas[0]);
                    measureHeat.setLatitude(datas[1]);
                    measureHeat.setLongitude(datas[2]);
                    measureHeat.setAltitude(datas[3]);
                    measureHeat.setYear(datas[4]);
                    measureHeat.setMonth(datas[5]);
                    measureHeat.setDay(datas[6]);
                    measureHeat.setAverageHeat(datas[7]);
                    measureHeat.setMaxHeat(datas[8]);
                    measureHeat.setMinHeat(datas[9]);
                    measureHeat.setAverageHeatCode(datas[10]);
                    measureHeat.setMaxHeatCode(datas[11]);
                    measureHeat.setMinHeatCode(datas[12]);
                    boolean added = measureHeats.add(measureHeat);
                    if (!added) {
                        log.error("文件【{}】中的【{}】数据存在重复！", file.getName(), str);
                    }
                }

                // 执行数据库操作
                int index = 0;
                MeasureHeatMapper measureHeatMapper = sqlSession.getMapper(MeasureHeatMapper.class);
                for (MeasureHeat measureHeat : measureHeats) {
                    measureHeatMapper.insert(measureHeat);
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
