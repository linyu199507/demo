package com.chinaweal.test;

import com.chinaweal.entity.BaseFormLevel;
import com.chinaweal.service.BaseFormLevelServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 根据表格导入n菜单例子
 *
 * @author ：Huang Xiao
 * @version : 1.0
 * @since ： 2019/9/30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-context.xml")
public class BaseFormAnalysis {

    @Resource
    private BaseFormLevelServiceImpl baseFormLevelService;
    //从第二行开始，忽略表头
    private int rowNum = 1;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private List<BaseFormLevel> baseFormLevels = new ArrayList<>();

    private int Level1 = 1;
    private int Level2 = 1;
    private int Level3 = 1;
    private int Level4 = 1;
    private int Level5 = 1;

    private String baseLevel1 = "";
    private String baseLevel2 = "";
    private String baseLevel3 = "";
    private String baseLevel4 = "";
    private String baseLevel5 = "";

    private String type = "A";

    /**
     * 封装，自身一对多查询
     */
    @Test
    public void selectById() {
        BaseFormLevel baseFormLevel = baseFormLevelService.getById("f9d22fa9e7d6480d9263cd73ac2a33dd");
        logger.info(baseFormLevel.toString());
    }

    @Test
    public void analysisLevel() throws IOException {
        String fileName = "e:/基础数据.xls";
        InputStream inputStream = new FileInputStream(fileName);
        Workbook workbook;
        Row row;
        //获取Excel文档
        if (fileName.endsWith(".xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (fileName.endsWith(".xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            logger.warn("文件格式不对！");
            return;
        }
        //获取Excel文档的第一个sheet页
//        Sheet sheet = workbook.getSheetAt(0);
        //循环Excel文档的sheet页
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Level1 = 1;
            Level2 = 1;
            Level3 = 1;
            Level4 = 1;
            Level5 = 1;
            baseLevel1 = "";
            baseLevel2 = "";
            baseLevel3 = "";
            baseLevel4 = "";
            baseLevel5 = "";
            rowNum = 1;
            if (i == 0) {
                type = "A";
            } else if (i == 1) {
                type = "B";
            } else if (i == 2) {
                type = "C";
            } else if (i == 3) {
                type = "D";
            } else if (i == 4) {
                type = "E";
            } else if (i == 5) {
                type = "F";
            } else if (i == 6) {
                type = "G";
            }
            Sheet sheet = workbook.getSheetAt(i);
            for (; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++) {
                row = sheet.getRow(rowNum);
                logger.debug("一级：-------行：{}，列：{}-----值：{}", (rowNum + 1), 1, row.getCell(0));
                BaseFormLevel baseFormLevel = new BaseFormLevel();
                String id = UUID.randomUUID().toString().replaceAll("-", "");
                baseFormLevel.setId(id);
                baseFormLevel.setUpdatedate(LocalDateTime.now());
                baseFormLevel.setComment(row.getCell(0).getStringCellValue());
                baseFormLevel.setOrglevel(String.valueOf(row.getCell(0).getColumnIndex() + 1));
                String codeid = getCodeid(String.valueOf(row.getCell(0).getColumnIndex() + 1));
                baseFormLevel.setCodeid(codeid);
                baseFormLevel.setType(type);
                getLowerLevel(sheet, row.getCell(0).getColumnIndex() + 1, id);
                baseFormLevels.add(baseFormLevel);
            }
            //进行插入
            boolean b = baseFormLevelService.saveBatch(baseFormLevels);
            if (b) {
                logger.info("导入成功，共{}条！", baseFormLevels.size());
            }
            baseFormLevels.clear();
        }
    }

    /**
     * 递归解析下级
     *
     * @param cellNumber 单前级列数
     */
    private void getLowerLevel(Sheet sheet, int cellNumber, String parentid) {
        rowNum++;
        for (; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            //判断当前行是否为空行
            if (row != null) {
                if (isBlankLine(row)) {
                    logger.warn("第{}行是空行，已忽略", rowNum + 1);
                    continue;
                }
            }
            if (row != null) {
                if (row.getCell(cellNumber) == null || "".equals(row.getCell(cellNumber).getStringCellValue())) {
                    rowNum--;
                    return;
                }
                logger.debug("{}级：-------行：{}，列：{}-----值：{}", (cellNumber + 1), rowNum, (cellNumber + 1), row.getCell(cellNumber));
                BaseFormLevel baseFormLevel = new BaseFormLevel();
                String id = UUID.randomUUID().toString().replaceAll("-", "");
                baseFormLevel.setId(id);
                baseFormLevel.setUpdatedate(LocalDateTime.now());
                baseFormLevel.setComment(row.getCell(cellNumber).getStringCellValue());
                baseFormLevel.setOrglevel(String.valueOf(cellNumber + 1));
                baseFormLevel.setParentid(parentid);
                String codeid = getCodeid(String.valueOf(cellNumber + 1));
                baseFormLevel.setCodeid(codeid);
                baseFormLevel.setType(type);
                baseFormLevels.add(baseFormLevel);
                getLowerLevel(sheet, cellNumber + 1, id);
            }

        }
    }

    /**
     * 判断是否空行
     *
     * @return 空行 返回true
     */
    private boolean isBlankLine(Row row) {
        for (Cell cell : row) {
            if (cell != null && !"".equals(cell.getStringCellValue())) {
                return false;
            }
        }
        return true;
    }

    /**
     * 根据层级跟类型生成codeid
     * @param orglevel
     * @return
     */
    private String getCodeid(String orglevel) {
        if ("1".equals(orglevel)) {
            //将层级数量重置为默认值
            Level2 = 1;
            Level3 = 1;
            Level4 = 1;
            Level5 = 1;
            if (Level1 < 10) {
                baseLevel1 = type + "0"+ Level1;
            } else {
                baseLevel1 = type + Level1;
            }
            Level1 ++ ;
            return baseLevel1;
        } else if ("2".equals(orglevel)) {
            Level3 = 1;
            Level4 = 1;
            Level5 = 1;
            if (Level2 < 10) {
                baseLevel2 = baseLevel1 + "0"+ Level2;
            } else {
                baseLevel2 = baseLevel1 + Level2;
            }
            Level2 ++ ;
            return baseLevel2;
        } else if ("3".equals(orglevel)) {
            Level4 = 1;
            Level5 = 1;
            if (Level3 < 10) {
                baseLevel3 = baseLevel2 + "0"+ Level3;
            } else {
                baseLevel3 = baseLevel2 + Level3;
            }
            Level3 ++ ;
            return baseLevel3;
        } else if ("4".equals(orglevel)) {
            Level5 = 1;
            if (Level4 < 10) {
                baseLevel4 = baseLevel3 + "0"+ Level4;
            } else {
                baseLevel4 = baseLevel3 + Level4;
            }
            Level4 ++ ;
            return baseLevel4;
        } else if ("5".equals(orglevel)) {
            if (Level5 < 10) {
                baseLevel5 = baseLevel4 + "0"+ Level5;
            } else {
                baseLevel5 = baseLevel4 + Level5;
            }
            Level5 ++ ;
            return baseLevel5;
        }
        return "";
    }

}
