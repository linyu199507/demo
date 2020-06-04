package com.chinaweal.easypoi;

import com.chinaweal.easypoi.service.ExcelService;

import com.chinaweal.easypoi.vo.ComprehensiveVo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：Huang Xiao
 * @version : 1.0
 * @since ： 2019/8/20
 */
public class ComprehensiveTest {

    /*public static void main(String[] args) throws IOException {
        ExcelService excelService = new ExcelService() {
            @Override
            public void exportComprehensiveFile(List<ComprehensiveVo> list) throws IOException {

            }
        };
        List<ComprehensiveVo> list = new ArrayList<>();
        *//*for (int i = 0; i < 100; i++) {
            ComprehensiveVo vo = new ComprehensiveVo();
            vo.setCaseguid("1234567890-9876543");
            vo.setFocus("普通");
            vo.setId("" + i);
            list.add(vo);
        }*//*
        excelService.exportComprehensiveFile(list);
    }*/
}
