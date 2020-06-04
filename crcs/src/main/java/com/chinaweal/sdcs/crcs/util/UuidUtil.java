package com.chinaweal.sdcs.crcs.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UuidUtil {

    private static Logger log = Logger.getLogger(UuidUtil.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static String getUuid() {
        return UUID.randomUUID().toString();
    }

    private String getTridmaker(String makerID) {
        String num = null;
        Integer curnum = -1;
        Integer nextNum = -1;
        //获取当前的时间格式 2013-06-04
        Calendar curCl = Calendar.getInstance();
        Map<String, Object> makerMap;
        try {
            makerMap = jdbcTemplate.queryForMap("select * from Tridmaker where makerID = ?", makerID);
            //比较当前时间和数据库存储的时间
            Calendar dataCl = Calendar.getInstance();
            Date _curDate = (Date) makerMap.get("curDate");
            if (_curDate != null) {
                dataCl.setTime(_curDate);
            }
            if (curCl.get(Calendar.YEAR) == dataCl.get(Calendar.YEAR)){
                curnum = (Integer) makerMap.get("curnum");
            } else {
                curnum = 0;
                makerMap.put("curDate", new Date(curCl.getTime().getTime()));
            }
            nextNum = curnum + 1;
            makerMap.put("curnum", nextNum);
            jdbcTemplate.update("update Tridmaker set curnum = ?, curDate = ? where makerID = ?", makerMap.get("curnum"), makerMap.get("curDate"), makerMap.get("makerID"));
        } catch (EmptyResultDataAccessException e) {
            log.info("不存在传入的注册号生成规则，现按照acceptNo规则生成。");
            curnum = 0;
            nextNum = curnum + 1;
            makerMap = new HashMap<String, Object>();
            makerMap.put("makerID", makerID);
            makerMap.put("curnum", nextNum);
            makerMap.put("curDate", curCl.getTime());
            jdbcTemplate.update("insert into Tridmaker(makerID, curnum, curDate) values(?, ?, ?)", makerMap.get("makerID"), makerMap.get("curnum"), makerMap.get("curDate"));
        }
        //根据规则生成受理号
        int year = curCl.get(Calendar.YEAR);
        num = "8" + String.valueOf(year).substring(2, 4) + beforeZeroFill(nextNum, 8);
        return num;
    }

    /**
     * 规则年月日+5位顺序号。例如：13060400001
     *
     * @return 新产生的流水号
     */
    public synchronized String getCurAcceptNo() {
        return getTridmaker("acceptNoForTserialnum");
    }

    /**
     * @param number 需要填充0的数字
     * @param length 需要多少位
     * @return
     */
    private String beforeZeroFill(int number, int length) {
        return beforeZeroFill(String.valueOf(number), length);
    }

    private String beforeZeroFill(String number, int length) {
        String tmp = number;
        for (int i = 0; i < length - number.length(); i++) {
            tmp = "0" + tmp;
        }
        return tmp;
    }

    private String afterZeroFill(String number, int length) {
        StringBuilder tmp = new StringBuilder(number);
        for (int i = 0; i < length - number.length(); i++) {
            tmp.append("0");
        }
        return tmp.toString();
    }
}
