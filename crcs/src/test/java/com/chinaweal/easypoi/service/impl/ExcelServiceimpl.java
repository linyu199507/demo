package com.chinaweal.easypoi.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.chinaweal.easypoi.service.ExcelService;
import com.chinaweal.easypoi.vo.ComprehensiveVo;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.Collection;
import java.util.Date;
import java.util.List;


/**
 * @author ：Huang Xiao
 * @version : 1.0
 * @since ： 2019/8/20
 */
class ExcelServiceImpl implements ExcelService {

    /*public void exportComprehensiveFile(List<ComprehensiveVo> list) throws IOException {
        Workbook workbook;
        ExportParams params = new ExportParams();
        workbook = ExcelExportUtil.exportExcel(params, ComprehensiveVo.class, list);
        FileOutputStream fos = new FileOutputStream("C:/Users/lin99/Desktop/SDCS/crcs/综合查询123.xls");
        workbook.write(fos);
        fos.close();

    }*/
}
