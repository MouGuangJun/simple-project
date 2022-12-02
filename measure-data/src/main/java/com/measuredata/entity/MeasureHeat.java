package com.measuredata.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 温度表
 * </p>
 *
 * @author gjmou
 * @since 2022-12-02
 */
@Data
@TableName("measure_heat")
@EqualsAndHashCode(exclude = {"averageHeat", "maxHeat", "minHeat", "averageHeatCode", "maxHeatCode", "minHeatCode"})
public class MeasureHeat {

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
     * 平均地表气温
     */
    @TableField("average_heat")
    private String averageHeat;

    /**
     * 日最高地表气温
     */
    @TableField("max_heat")
    private String maxHeat;

    /**
     * 日最低地表气温
     */
    @TableField("min_heat")
    private String minHeat;

    /**
     * 平均地表气温质量控制码
     */
    @TableField("average_heat_code")
    private String averageHeatCode;

    /**
     * 日最高地表气温质量控制码
     */
    @TableField("max_heat_code")
    private String maxHeatCode;

    /**
     * 日最低地表气温质量控制码
     */
    @TableField("min_heat_code")
    private String minHeatCode;


}
