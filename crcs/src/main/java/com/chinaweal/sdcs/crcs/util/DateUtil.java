package com.chinaweal.sdcs.crcs.util;


import com.chinaweal.sdcs.crcs.system.entity.TimesVo;
import org.apache.commons.lang.time.DateUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class DateUtil {

    //本月
    public static Map<String,Object> timeMounth()  {
        Map<String,Object> map = new HashMap<>();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:ss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        String first = format.format(c.getTime());
        map.put("start",first);

//        //获取当前月最后一天
//        Calendar ca = Calendar.getInstance();
//        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
//        ca.set(Calendar.HOUR_OF_DAY,23);
//        ca.set(Calendar.MINUTE,59);
//        ca.set(Calendar.SECOND,59);
//        String last = format.format(ca.getTime());
//        map.put("end",last);
//        System.out.println("===============本月last day:"+last);

        //获取当前日期
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:ss");
        String time = dateFormat.format(date);
        map.put("end",time);

        return map;
    }

    //上月
    public static Map<String,Object> lastMonth()  {
        Map<String,Object> map = new HashMap<>();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        String start = format.format(calendar.getTime());
        map.put("start",start);

//        Calendar c=Calendar.getInstance();
//        int month=c.get(Calendar.MONTH);
//        c.set(Calendar.MONTH, month-1);
//        c.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//        c.set(Calendar.HOUR_OF_DAY,23);
//        c.set(Calendar.MINUTE,59);
//        c.set(Calendar.SECOND,59);
//        String end = format.format(c.getTime());
//        map.put("end",end);
//        System.out.println("上个月最后一天："+format.format(c.getTime()));

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:ss");
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MONTH,c.get(Calendar.MONTH)-1);
        date = c.getTime();
        String end = dateFormat.format(date);
        map.put("end",end);

        return map;
    }
    //  1除2,只适用于同比
    public static BigDecimal toDivision(int number1, int number2){

        if( number1 == 0 && number2 == 0){
            return BigDecimal.valueOf(0);
        }else {
            double double1 = (double) number1;
            double double2 = (double) number2;
            BigDecimal bignum1 = new BigDecimal(Double.toString((double1 - double2)*100));
            BigDecimal bignum2 = new BigDecimal(Double.toString(double2));
            BigDecimal bignum3 = bignum1.divide(bignum2,2,BigDecimal.ROUND_HALF_UP);

            return bignum3;
        }

    }


    //上6月
    public static  List<TimesVo> getSixMonth()  {

        Map<String,Object> map = new HashMap<>();
        List<List<TimesVo>> mounths = new ArrayList<>();
        List<TimesVo> list = new ArrayList<>();

        for(int i=5 ; i>=0 ; i--){
            TimesVo timesVo = new TimesVo();
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Calendar calendar=Calendar.getInstance();
            calendar.add(Calendar.MONTH, -i);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY,0);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.SECOND,0);
            String start = format.format(calendar.getTime());
            timesVo.setTimestart(start);

            Calendar c=Calendar.getInstance();
            int month=c.get(Calendar.MONTH);
            c.set(Calendar.MONTH, month-i);
            c.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            c.set(Calendar.HOUR_OF_DAY,23);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            String end = format.format(c.getTime());
            timesVo.setTimeend(end);
            list.add(timesVo);
//            mounths.add(list);
        }
        return list;
    }


    //前12月
    public static  List<TimesVo> getTwelveMonth()  {

        Map<String,Object> map = new HashMap<>();
        List<List<TimesVo>> mounths = new ArrayList<>();
        List<TimesVo> list = new ArrayList<>();

        for(int i=11 ; i>=0 ; i--){
            TimesVo timesVo = new TimesVo();
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Calendar calendar=Calendar.getInstance();
            calendar.add(Calendar.MONTH, -i);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY,0);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.SECOND,0);
            String start = format.format(calendar.getTime());
            timesVo.setTimestart(start);

            Calendar c=Calendar.getInstance();
            int month=c.get(Calendar.MONTH);
            c.set(Calendar.MONTH, month-i);
            c.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            c.set(Calendar.HOUR_OF_DAY,23);
            c.set(Calendar.MINUTE,59);
            c.set(Calendar.SECOND,59);
            String end = format.format(c.getTime());
            timesVo.setTimeend(end);
            list.add(timesVo);
//            mounths.add(list);
        }
        return list;
    }

    public static  Map<String,Object> getThisYear()  {
        Map<String,Object> map = new HashMap<>();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.MONTH,0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        String start = format.format(calendar.getTime());
        map.put("start",start);

        Calendar c=Calendar.getInstance();
        c.set(Calendar.MONTH,11);
        c.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY,23);
        c.set(Calendar.MINUTE,59);
        c.set(Calendar.SECOND,59);
        String end = format.format(c.getTime());
        map.put("end",end);


        return map ;
    }

    public static  Map<String,Object> getLastYear()  {
        Map<String,Object> map = new HashMap<>();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.YEAR,-1);
        calendar.set(Calendar.MONTH,0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        String start = format.format(calendar.getTime());
        map.put("start",start);

        Calendar c=Calendar.getInstance();
        c.add(Calendar.YEAR,-1);
        c.set(Calendar.MONTH,11);
        c.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY,23);
        c.set(Calendar.MINUTE,59);
        c.set(Calendar.SECOND,59);
        String end = format.format(c.getTime());
        map.put("end",end);

        return map ;
    }

    public static  Map<String,Object> getLastYearOfNow()  {
        Map<String,Object> map = new HashMap<>();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.YEAR,-1);
        calendar.set(Calendar.MONTH,0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        String start = format.format(calendar.getTime());
        map.put("start",start);

        Calendar c=Calendar.getInstance();
        System.out.println(c.getTime());
        c.add(Calendar.YEAR,-1);
        String end = format.format(c.getTime());
        map.put("end",end);

        return  map;
    }

    public static  Map<String,Object> getThisYearOfNow()  {
        Map<String,Object> map = new HashMap<>();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.MONTH,0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        String start = format.format(calendar.getTime());
        map.put("start",start);

        Calendar c=Calendar.getInstance();
        String end = format.format(c.getTime());
        map.put("end",end);
        return map;
    }
    public static  Map<String,Object> getTbDate(String timeDate) {
        Map<String, Object> map = new HashMap<>();
        if (timeDate==null && "".equals(timeDate)) {
            return null;
        }else{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            String monthFormat;
            try {
                date = sdf.parse(timeDate + "-" + "01");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.YEAR, -1);
            int month = c.get(Calendar.MONTH) + 1;
            if (month <= 9) {
                monthFormat = "0" + month;
            } else {
                monthFormat = String.valueOf(month);
            }
            String tbDate = c.get(Calendar.YEAR) + "-"
                    + monthFormat + "-01";
            map.put("firstdate", timeDate);
            map.put("lastdate", tbDate);
            return map;
        }
    }

    public static  Map<String,Object> getDateAfterOneMonth()  {
        Map<String,Object> map = new HashMap<>();
        Date now = new Date();
        Date date = DateUtils.addDays(now, -30);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateOfEnd = simpleDateFormat.format(now);
        String dateOfStart = simpleDateFormat.format(date);
        map.put("begindate",dateOfStart);
        map.put("enddate",dateOfEnd);

        return  map;
    }


    public static  Map<String,Object> getDateOfToday()  {
        Map<String,Object> map = new HashMap<>();
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateOfStart = simpleDateFormat.format(todayStart.getTime());
        map.put("begindate",dateOfStart);

        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        String dateOfEnd = simpleDateFormat.format(todayEnd.getTime());
        System.out.println(dateOfEnd);
        map.put("enddate",dateOfEnd);

        return  map;
    }

}
