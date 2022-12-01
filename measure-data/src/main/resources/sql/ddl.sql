-- 降水表
-- DROP TABLE MEASURE_RAINFALL;
CREATE TABLE `MEASURE_RAINFALL`
(
    `site`                VARCHAR(32) NOT NULL COMMENT '站点',
    `latitude`            VARCHAR(32) NOT NULL COMMENT '维度',
    `longitude`           VARCHAR(32) NOT NULL COMMENT '经度',
    `altitude`            VARCHAR(32) NOT NULL COMMENT '观测场拔海高度',
    `year`                VARCHAR(18) NOT NULL COMMENT '年',
    `month`               VARCHAR(18) NOT NULL COMMENT '月',
    `day`                 VARCHAR(18) NOT NULL COMMENT '日',
    `rainfall_20_8`       VARCHAR(32) DEFAULT NULL COMMENT '20-8时降水量',
    `rainfall_8_20`       VARCHAR(32) DEFAULT NULL COMMENT '8-20时降水量',
    `rainfall_20_20`      VARCHAR(32) DEFAULT NULL COMMENT '20-20时累计降水量',
    `rainfall_code_20_8`  VARCHAR(32) DEFAULT NULL COMMENT '20-8时降水量质量控制码',
    `rainfall_code_8_20`  VARCHAR(32) DEFAULT NULL COMMENT '8-20时累计降水量质量控制码',
    `rainfall_code_20_20` VARCHAR(32) DEFAULT NULL COMMENT '20-20时降水量质量控制码',
    PRIMARY KEY (`site`, `latitude`, `longitude`, `altitude`, `year`, `month`, `day`)
) ENGINE=INNODB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='降水表';

-- 日照表
-- DROP TABLE MEASURE_SUNSHINE;
CREATE TABLE `MEASURE_SUNSHINE`
(
    `site`                VARCHAR(32) NOT NULL COMMENT '站点',
    `latitude`            VARCHAR(32) NOT NULL COMMENT '维度',
    `longitude`           VARCHAR(32) NOT NULL COMMENT '经度',
    `altitude`            VARCHAR(32) NOT NULL COMMENT '观测场拔海高度',
    `year`                VARCHAR(18) NOT NULL COMMENT '年',
    `month`               VARCHAR(18) NOT NULL COMMENT '月',
    `day`                 VARCHAR(18) NOT NULL COMMENT '日',
    `sunshine_hour`       VARCHAR(32) DEFAULT NULL COMMENT '日照时数',
    `sunshine_hour_code`  VARCHAR(32) DEFAULT NULL COMMENT '日照时数质量控制码',
    PRIMARY KEY (`site`, `latitude`, `longitude`, `altitude`, `year`, `month`, `day`)
) ENGINE=INNODB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='日照表';

-- 温度表
-- DROP TABLE MEASURE_HEAT;
CREATE TABLE `MEASURE_HEAT`
(
    `site`                VARCHAR(32) NOT NULL COMMENT '站点',
    `latitude`            VARCHAR(32) NOT NULL COMMENT '维度',
    `longitude`           VARCHAR(32) NOT NULL COMMENT '经度',
    `altitude`            VARCHAR(32) NOT NULL COMMENT '观测场拔海高度',
    `year`                VARCHAR(18) NOT NULL COMMENT '年',
    `month`               VARCHAR(18) NOT NULL COMMENT '月',
    `day`                 VARCHAR(18) NOT NULL COMMENT '日',
    `average_heat`        VARCHAR(32) DEFAULT NULL COMMENT '平均地表气温',
    `max_heat`            VARCHAR(32) DEFAULT NULL COMMENT '日最高地表气温',
    `min_heat`            VARCHAR(32) DEFAULT NULL COMMENT '日最低地表气温',
    `average_heat_code`   VARCHAR(32) DEFAULT NULL COMMENT '平均地表气温质量控制码',
    `max_heat_code`       VARCHAR(32) DEFAULT NULL COMMENT '日最高地表气温质量控制码',
    `min_heat_code`       VARCHAR(32) DEFAULT NULL COMMENT '日最低地表气温质量控制码',
    PRIMARY KEY (`site`, `latitude`, `longitude`, `altitude`, `year`, `month`, `day`)
) ENGINE=INNODB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='温度表';

