-- 降水表
--DROP TABLE MEASURE_RAINFALL;
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
