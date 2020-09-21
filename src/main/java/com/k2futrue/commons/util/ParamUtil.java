package com.k2futrue.commons.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ParamUtil {

    public static final DateTimeFormatter STANDARD_DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter STANDARD_DATE= DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter ORDER_CODE_FORMATTER = DateTimeFormatter.ofPattern("yyMMddHHmmssSS");//SSS overflow


    public static LocalDateTime string2DateTime(String time) {
        try {
            return LocalDateTime.parse(time, STANDARD_DATE_TIME);
        } catch (Exception e) {
            throw new IllegalArgumentException("date str shuld be yyyy-MM-dd HH:mm:ss ");
        }
    }

    public static LocalDate string2Date(String time) {
        return string2DateTime(time).toLocalDate();
    }

    /**
     * date相差天数, 始终返回正数
     * @param date1  第一个日期
     * @param date2 第二个日期
     * @return  相差天数
     */
    public static int periodBetween(LocalDate date1, LocalDate date2) {
        int days = Period.between(date1, date2).getDays();
        return Math.abs(days);
    }

    /**
     * 某个日期和今天相差天数
     * @param date  日期
     * @return 天数
     */
    public static int periodBetween(LocalDate date) {
        int days = Period.between(date, LocalDate.now()).getDays();
        return Math.abs(days);
    }


    /**
     * comma sp, 无有效输入返回 empty list
     * @param ids id字符串以","分割
     * @return list id long 类型列表
     */
    public static List<Long> idsStr2List(String ids) {

        List<Long> l = new ArrayList<>();
        if (!StringUtils.isEmpty(ids)) {
            for (String s : ids.split(",")) {
                Long id;
                try {
                    id = Long.valueOf(s);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("wrong id style : " + s);
                }
                l.add(id);
            }
        }

        return l;
    }

    public static void idNN(Long id) {
        Assert.notNull(id, "id null?");
    }

    public static void entityNN(Object o) {
        Assert.notNull(o, "Invalid id");

    }

}
