package com.chinaweal.test;

import com.chinaweal.sdcs.crcs.analyse.service.IHotWordService;
import com.chinaweal.sdcs.crcs.analyse.service.impl.HotWordServiceImpl;
import org.ansj.library.DicLibrary;
import org.ansj.splitWord.analysis.DicAnalysis;
import org.ansj.util.MyStaticValue;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

public class HotWordTest {

    @Test
    public void test1() {
        String text = "上海虹桥机场双十一的12341234";
        System.out.println(DicAnalysis.parse(text));
        MyStaticValue.putLibrary(DicLibrary.DEFAULT, "D:\\chinaweal\\project-code\\SDCS\\trunk\\crcs\\src\\main\\resources\\library\\default.dic");
        System.out.println(DicAnalysis.parse(text));
    }

    @Test
    public void test2() {
        IHotWordService hotWordService = new HotWordServiceImpl();
        hotWordService.test("`~88上海虹桥机场双十一的12341234m破壁机");
    }

    @Test
    public void test3() {
//        String[] lables = "虚假宣传 ".split(" ");
//        for (String lable : lables) {
//            lable = lable.trim();
//            System.out.println(lable + "-");
//        }

        int lastMonth = 0;
        int currDay = 0;
        BigDecimal yearOnYear = BigDecimal.valueOf((lastMonth - currDay) / (double) lastMonth);
        System.out.println(yearOnYear.setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    @Test
    public void test3a() {
        String str = "冰箱 不按约定发货  商家不处理  尽快发货 赔偿 售货车外观侵权，请部门介入处理，勒令整改，停止、经,营";
        System.out.println(Arrays.toString(str.split("[\\s|，|,|、]")));
    }
}
