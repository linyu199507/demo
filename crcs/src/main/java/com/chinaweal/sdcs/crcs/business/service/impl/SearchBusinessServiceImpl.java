package com.chinaweal.sdcs.crcs.business.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.business.entity.SearchBusiness;
import com.chinaweal.sdcs.crcs.business.entity.Tasklist;
import com.chinaweal.sdcs.crcs.business.mapper.SearchBusinsessMapper;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * 业务记录查询结果列表
 *
 * @author ：Lin Yu
 * @version : 1.0
 * @since ： 2019/9/2
 */
@Service
public class SearchBusinessServiceImpl extends ServiceImpl<SearchBusinsessMapper,SearchBusiness> implements IService<SearchBusiness> {

    /**
     * @author ：Lin Yu
     * @version : 1.0
     * @since ： 2019/9/2
     */
    public void exportBusinessFile(List<SearchBusiness> list) throws IOException {
        Workbook workbook;
        ExportParams params = new ExportParams();
        workbook = ExcelExportUtil.exportExcel(params, SearchBusiness.class, list);
        FileOutputStream business = new FileOutputStream("12综合业务记录查询.xls");

        workbook.write(business);
        business.close();
    }

   /* *//**
           * 导出订单
           *//*
    @Override
     public void exportById(OutputStream os, Long uuid) {

        //根据订单编号获取订单
//        Orders orders = ordersDao.get(uuid);
        //订单明细
//                List<Tasklist> orderDetails = orders.getOrderDetails();
//                String sheetName = "";
//                if(Orders.TYPE_IN.equals(orders.getType())){
//                    sheetName = "采 购 单";
//                    }
//                if(Orders.TYPE_OUT.equals(orders.getType())){
//                    sheetName = "销 售 单";
//                    }
            //工作簿
        HSSFWorkbook wk = new HSSFWorkbook();
        //创建工作表
        String sheetName;
        Sheet sheet = wk.createSheet(sheetName);
        //创建字体
                HSSFFont font_content = wk.createFont();
                font_content.setFontName("宋体");
                font_content.setFontHeightInPoints((short)12);
                //创建样式
        CellStyle style_content = wk.createCellStyle();
        //水平居中
        style_content.setAlignment(HorizontalAlignment.CENTER);
        //重直居中
        style_content.setVerticalAlignment(VerticalAlignment.CENTER);
        //设置字体
        style_content.setFont(font_content);
        //标题的样式, 样式克隆
        CellStyle style_title = wk.createCellStyle();
        style_title.cloneStyleFrom(style_content);
        HSSFFont font_title = wk.createFont();
        font_title.setFontName("黑体");
        font_title.setFontHeightInPoints((short)20);
        style_title.setFont(font_title);
        //设置边框
        style_content.setBorderBottom(BorderStyle.THIN);
        style_content.setBorderLeft(BorderStyle.THIN);
        style_content.setBorderRight(BorderStyle.THIN);
        style_content.setBorderTop(BorderStyle.THIN);
        //日期样式
        HSSFCellStyle style_date = wk.createCellStyle();
        style_date.cloneStyleFrom(style_content);
        HSSFDataFormat dataFormat = wk.createDataFormat();
        style_date.setDataFormat(dataFormat.getFormat("yyyy-MM-dd hh:mm"));
        //创建一行,参数指的是： 行的索引=行号-1
        Row row = null;
        Cell cell = null;
        int rowCnt = 10 + orderDetails.size();
        for(int i = 2; i < rowCnt; i++){
            row = sheet.createRow(i);
            for(int j = 0; j < 4; j++){ cell = row.createCell(j);
                //设置单元格样式
                cell.setCellStyle(style_content);
            }
        }
        //设置日期格式
        sheet.getRow(3).getCell(1).setCellStyle(style_date);
        sheet.getRow(4).getCell(1).setCellStyle(style_date);
        sheet.getRow(5).getCell(1).setCellStyle(style_date);
        sheet.getRow(6).getCell(1).setCellStyle(style_date);
        //sheet.getRow(3).getCell(1).setCellValue(new Date());
        //合并单元格,订单名称
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
        //供应商名称
        sheet.addMergedRegion(new CellRangeAddress(2,2,1,3));
        //明细
        sheet.addMergedRegion(new CellRangeAddress(7,7,0,3));
        //设置header内容
        Cell title_cell = sheet.createRow(0).createCell(0);
        title_cell.setCellStyle(style_title);
        title_cell.setCellValue("采 购 单");
        sheet.getRow(2).getCell(0).setCellValue("供应商");
        //日期 与 人
        row = sheet.getRow(3);
        row.getCell(0).setCellValue("下单日期");
        row.getCell(2).setCellValue("经办人");
        row = sheet.getRow(4);
        row.getCell(0).setCellValue("审核日期");
        row.getCell(2).setCellValue("经办人");
        row = sheet.getRow(5);
        row.getCell(0).setCellValue("采购日期");
        row.getCell(2).setCellValue("经办人");
        row = sheet.getRow(6);
        row.getCell(0).setCellValue("入库日期");
        row.getCell(2).setCellValue("经办人");
        sheet.getRow(7).getCell(0).setCellValue("订单明细");
        row = sheet.getRow(8);
        row.getCell(0).setCellValue("商品名称");
        row.getCell(1).setCellValue("数量");
        row.getCell(2).setCellValue("价格");
        row.getCell(3).setCellValue("金额");
        //设置行高与列宽
        //调整行的高度
        sheet.getRow(0).setHeight((short)1000);
        for(int i = 2; i < rowCnt; i++){
            sheet.getRow(i).setHeight((short)500);

        }

        //调整列宽

        for(int i = 0; i < 4; i++){
            sheet.setColumnWidth(i, 5000);

            //写入订单详情
            // sheet.getRow(2).getCell(1).setCellValue(supplierDao.getName(orders.getSupplieruuid()));
            // if(null != orders.getCreatetime()){//下单日期
            // sheet.getRow(3).getCell(1).setCellValue(orders.getCreatetime());

            if(null != orders.getChecktime()){//审核日期
                // sheet.getRow(4).getCell(1).setCellValue(orders.getChecktime());
                // }
                // if(null != orders.getStarttime()){//采购日期
                // sheet.getRow(5).getCell(1).setCellValue(orders.getStarttime());
                // }
                // if(null != orders.getEndtime()){//入库日期
                // sheet.getRow(6).getCell(1).setCellValue(orders.getEndtime());
                // }
                // 经办人
                // sheet.getRow(3).getCell(3).setCellValue(empDao.getName(orders.getCreater()));
                // sheet.getRow(4).getCell(3).setCellValue(empDao.getName(orders.getChecker()));
                // sheet.getRow(5).getCell(3).setCellValue(empDao.getName(orders.getStarter()));

                sheet.getRow(6).getCell(3).setCellValue(empDao.getName(orders.getEnder()))

                //写入明细内容
                Orderdetail od = null;
                //rowCnt = 10+size - 9 = 1+size - 1=size
                for(int i = 9; i < rowCnt - 1; i++){
                    od = orderDetails.get(i-9);
                    row = sheet.getRow(i);
                    row.getCell(0).setCellValue(od.getGoodsname());
                    row.getCell(1).setCellValue(od.getNum());
                    row.getCell(2).setCellValue(od.getPrice());
                    row.getCell(3).setCellValue(od.getMoney());
                }
                //合计
                sheet.getRow(rowCnt - 1).getCell(0).setCellValue("合计");
                sheet.getRow(rowCnt - 1).getCell(3).setCellValue(orders.getTotalmoney());
                //输出到输出流中
                try {
                    wk.write(os);
                } catch (IOException e) {
                    e.printStackTrace()} finally{
                    try {
                        wk.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }*/

        }

