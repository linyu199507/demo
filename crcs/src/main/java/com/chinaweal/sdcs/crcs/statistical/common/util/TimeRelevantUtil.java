package com.chinaweal.sdcs.crcs.statistical.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author: NaJim
 * @Date: 2019/9/24
 */
public class TimeRelevantUtil {

    /**
     * 根据传入参数i返回2019-i-01 00:00:00的Date对象
     *
     * @return Date
     */
    public static Date dateWrapper(int years,int i){
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String totle = "";
        if (i<10){
            totle = years+"-0"+i+"-01 00:00:00";
        }else{
            totle = years+"-"+i+"-01 00:00:00";
        }
        try {
            return dateTimeFormat.parse(totle);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }


    /**
     * 根据传入yyyy-mm-dd的String返回Date类型
     */
    public static Date strToDate(String str){
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateTimeFormat.parse(str);
        } catch (ParseException e) {
            return new Date();
        }

    }

    /**
     * 传入yyyy-mm-dd的String返回对应星期几
     */
    public static String dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = { "sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday" };
        Calendar cal = Calendar.getInstance();
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 传入start时间和end时间获得中间时间的遍历
     */
    public static List<String> getBetweenDate(String begindate, String enddate){
        List<String> list = new ArrayList<>();
        LocalDate startDate = LocalDate.parse(begindate);
        LocalDate endDate = LocalDate.parse(enddate);

        long distance = ChronoUnit.DAYS.between(startDate, endDate);
        if (distance < 1) {
            return list;
        }
        Stream.iterate(startDate, d -> {
            return d.plusDays(1);
        }).limit(distance + 1).forEach(f -> {
            list.add(f.toString());
        });
        return list;
    }

    /**
     * 根据传入Date 返回LocalDateTime
     */
    public static String dateToDate(Date date){
        String string = date.toString();
        String str = string+" 00:00:00";
        return str;
    }

}
