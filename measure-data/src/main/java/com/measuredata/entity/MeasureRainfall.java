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
 * @since 2022-11-30
 */
@Data
@TableName("measure_rainfall")
@EqualsAndHashCode(exclude = {"rainfall208", "rainfall820", "rainfall2020", "rainfallCode208", "rainfallCode820", "rainfallCode2020"})
public class MeasureRainfall {

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
     * 20-8时降水量
     */
    @TableField("rainfall_20_8")
    private String rainfall208;

    /**
     * 8-20时降水量
     */
    @TableField("rainfall_8_20")
    private String rainfall820;

    /**
     * 20-20时累计降水量
     */
    @TableField("rainfall_20_20")
    private String rainfall2020;

    /**
     * 20-8时降水量质量控制码
     */
    @TableField("rainfall_code_20_8")
    private String rainfallCode208;

    /**
     * 8-20时累计降水量质量控制码
     */
    @TableField("rainfall_code_8_20")
    private String rainfallCode820;

    /**
     * 20-20时降水量质量控制码
     */
    @TableField("rainfall_code_20_20")
    private String rainfallCode2020;


}
