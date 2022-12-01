package com.measuredata.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 降水表
 * </p>
 *
 * @author gjmou
 * @since 2022-12-01
 */
@Data
@TableName("measure_sunshine")
@EqualsAndHashCode(exclude = {"sunshineHour", "sunshineHourCode"})
public class MeasureSunshine {

    /**
     * 站点
     */
    @TableField("site")
    private String site;

    /**
     * 维度
     */
    @TableField("latitude")
    private String latitude;

    /**
     * 经度
     */
    @TableField("longitude")
    private String longitude;

    /**
     * 观测场拔海高度
     */
    @TableField("altitude")
    private String altitude;

    /**
     * 年
     */
    @TableField("year")
    private String year;

    /**
     * 月
     */
    @TableField("month")
    private String month;

    /**
     * 日
     */
    @TableField("day")
    private String day;

    /**
     * 日照时数
     */
    @TableField("sunshine_hour")
    private String sunshineHour;

    /**
     * 日照时数质量控制码
     */
    @TableField("sunshine_hour_code")
    private String sunshineHourCode;


}
