/**
 * Copyright (C), 2015-2023, 东北证券股份有限公司
 * FileName: DateTimeUtil
 * Author:   summer
 * Date:     2023/5/8 09:50
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.toolkit.common.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @ClassName DateTimeUtil
 * @Description 日期、时间工具类
 * @Author summer
 * @Date 2023/5/8 09:50
 **/
public class DateTimeUtil
{
    /**
     * @return java.util.Date
     * @Author summer
     * @Description 根据年、月、日生成一个Date
     * @Date 09:59 2023/5/8
     * @Param [year]
     * @Param [month range 1-12]
     * @Param [dayOfMonth]
     **/
    public static Date createDate(int year, int month, int dayOfMonth)
    {
        LocalDate localDate = LocalDate.of(year, month, dayOfMonth);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return date;
    }

    /**
     * @Author summer
     * @Description 根据年、月、日生成一个LocalDate
     * @Date 10:04 2023/5/8
     * @Param [year]
     * @Param [month : range 1-12]
     * @Param [dayOfMonth]
     * @return java.time.LocalDate
     **/
    public static LocalDate createLocalDate(int year, int month, int dayOfMonth)
    {
        LocalDate localDate = LocalDate.of(year, month, dayOfMonth);
        return localDate;
    }

    /**
     * @Author summer
     * @Description LocalDate转化Date
     * @Date 10:11 2023/5/8
     * @Param [localDate]
     * @return java.util.Date
     **/
    public static Date convertDate(LocalDate localDate)
    {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
