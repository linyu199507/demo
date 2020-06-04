package com.chinaweal.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chinaweal.entity.testLiny;
import com.chinaweal.mapper.testLinyMapper;
import com.chinaweal.sdcs.crcs.system.entity.BasecodeContrast;
import com.chinaweal.sdcs.crcs.system.entity.Basecodeform;
import com.chinaweal.sdcs.crcs.system.service.IBasecodeContrastService;
import com.chinaweal.sdcs.crcs.system.service.IBasecodeformService;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.xml.transform.Source;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-context.xml")
@WebAppConfiguration
public class ExportBaseDataTest {

    @Resource
    private IBasecodeformService iBasecodeformService;
    @Resource
    private testLinyMapper testLinyMappe1r;
    @Resource
    private IBasecodeContrastService iBasecodeContrastService;
    private String sheetName;

    @Test
    public void test1() throws IOException {
        iBasecodeformService.list();
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("orglevel", "1");
        queryWrapper.like("type", "%12345%");
        queryWrapper.orderByAsc("type");
        queryWrapper.orderByAsc("codeid");
        Collection<Basecodeform> collection = iBasecodeformService.list(queryWrapper);
        int y = 0;
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet sheet = null;
        XSSFCellStyle cellStyle = xssfWorkbook.createCellStyle();
        cellStyle.setDataFormat(xssfWorkbook.createDataFormat().getFormat("@"));
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        for (Basecodeform basecodeform : collection) {
            if (basecodeform.getType().length() < 2) {
                continue;//不要本地的数据
            }

            if (!basecodeform.getType().equals(sheetName)) {
                //新的sheet
                sheetName = basecodeform.getType();
                y = 0;
                sheet = xssfWorkbook.createSheet(sheetName);
            }
            assert sheet != null;
            sheet.setDefaultColumnWidth(15);
            sheet.setDefaultRowHeight((short) 400);
            y = export(sheet, cellStyle, basecodeform, y);
        }
        xssfWorkbook.write(new FileOutputStream("D:/Desktop/basecodeform.xlsx"));
    }


    private int export(XSSFSheet xssfSheet, XSSFCellStyle cellStyle, Basecodeform basecodeform, int y) {
        XSSFRow row;
        row = xssfSheet.createRow(y++);
        row.setRowStyle(cellStyle);
        row.createCell(0).setCellValue(basecodeform.getOrglevel());
        XSSFCell commentCell = row.createCell(Integer.parseInt(basecodeform.getOrglevel()));
        commentCell.setCellValue(basecodeform.getComment());
        XSSFCell codeidCell = row.createCell(Integer.parseInt(basecodeform.getOrglevel()) + 1);
        codeidCell.setCellStyle(cellStyle);
        codeidCell.setCellValue(basecodeform.getCodeid());
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parentid", basecodeform.getCodeid());
        queryWrapper.eq("type", basecodeform.getType());
        queryWrapper.orderByAsc("codeid");
        Collection<Basecodeform> basecodeforms = iBasecodeformService.list(queryWrapper);
        for (Basecodeform entity : basecodeforms) {
            y = export(xssfSheet, cellStyle, entity, y);
        }
        return y;
    }

    /**
     * 导入数据库
     */
    @Test
    public void test3() throws IOException {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook("C:/chinaweal-project/文档/顺德市场监督管理局项目/12345已和12315对应的基础数据20191228.xlsx");
        XSSFSheet objectSheet = xssfWorkbook.getSheet("行分类业");
        List<BasecodeContrast> list = new ArrayList<>();
        for (Row row : objectSheet) {
            System.out.println(row.getCell(3));
            if (StringUtils.isBlank(row.getCell(3).getStringCellValue()))
                continue;
            BasecodeContrast basecodeContrast = new BasecodeContrast();
            basecodeContrast.setId(UUID.randomUUID().toString());
            basecodeContrast.setType("industry");
            basecodeContrast.setFsCode(row.getCell(1).getStringCellValue());
            basecodeContrast.setCountryCode(row.getCell(3).getStringCellValue());

            if (row.getRowNum() != 0)
                list.add(basecodeContrast);
        }
        iBasecodeContrastService.saveBatch(list);
        System.out.println(list.size());

    }
   @Test
    public void test9() throws IOException {

       InputStream inputS=new FileInputStream("C:\\Users\\lin99\\Desktop\\消保系统历史Excel数据\\2020来人来访明细.xls");
       HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputS);
       HSSFSheet sheet = hssfWorkbook.getSheet("1-6月1");
//       List
       for(int i=1;i<sheet.getLastRowNum();i++){
           System.out.println(sheet.getRow(i).getCell(1));
           System.out.println("第"+i+"行");
//           String One = sheet.getRow(i).getCell(1).getStringCellValue();
//           String Two = sheet.getRow(i).getCell(2).getStringCellValue();


                    testLiny data = new testLiny();
                    UUID uuid=UUID.randomUUID();
                    String uuidStr=uuid.toString();
                    data.setId(uuidStr);
                    UUID uuidone=UUID.randomUUID();
                    String uuidStrone=uuidone.toString();
                    data.setCaseguid(uuidStrone);
//                    data.setOne(sheet.getRow(i).getCell(1).getStringCellValue());
//                    data.setOne(sheet.getRow(i).getCell(1).getStringCellValue());
//                    data.setTwo(sheet.getRow(i).getCell(2).getStringCellValue());
////                    data.setThree(sheet.getRow(i).getCell(3).getStringCellValue());
//                    data.setThree(sheet.getRow(i).getCell(3).getDateCellValue());
//                    data.setFour(sheet.getRow(i).getCell(4).getStringCellValue());
//
//                    data.setFive(sheet.getRow(i).getCell(5).getNumericCellValue());
//                    data.setSix(sheet.getRow(i).getCell(6).getStringCellValue());
//           System.out.println("123");
//           System.out.println(sheet.getRow(i).getCell(7));
//
//           if(sheet.getRow(i).getCell(7) == null){
//               data.setServer("");
//           }else {
//               data.setServer(sheet.getRow(i).getCell(7).getStringCellValue());
//
//           }
//
//
//           data.setEight(sheet.getRow(i).getCell(8).getStringCellValue());
//                    data.setNine(sheet.getRow(i).getCell(9).getStringCellValue());
                    System.out.println(data);
                    testLinyMappe1r.insert(data);

       }



    }
}
