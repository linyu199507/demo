package Excel;

import com.chinaweal.sdcs.crcs.system.entity.OverallSituation;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ExcelTest {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // 创建excel
        try (HSSFWorkbook wk = new HSSFWorkbook()) {
            // 创建一张工作表
            HSSFSheet sheet = wk.createSheet();
            // 设置列宽
            sheet.setColumnWidth(0, 2000);
            sheet.setColumnWidth(1, 4000);
            sheet.setColumnWidth(2, 6000);
            sheet.setColumnWidth(3, 8000);
            sheet.setColumnWidth(4, 5000);
            sheet.setColumnWidth(5, 5000);
            sheet.setColumnWidth(6, 5000);
            // 创建第一行
            HSSFRow row = sheet.createRow(0);
            // 创建第一行的第一个单元格
            HSSFCell cell1 = row.createCell(0);
            HSSFCell cell2 = row.createCell(1);
            HSSFCell cell3 = row.createCell(2);
            HSSFCell cell4 = row.createCell(3);
            HSSFCell cell5 = row.createCell(4);
            HSSFCell cell6 = row.createCell(5);
            HSSFCell cell7 = row.createCell(6);
            // 想单元格写值
            cell1.setCellValue("序号");
            cell2.setCellValue("工单分类");
            cell3.setCellValue("工单号");
            cell4.setCellValue("工单标题");
            cell5.setCellValue("业务分类");
            cell6.setCellValue("交办时间");
            cell7.setCellValue("系统来源");
            // 保存到本地
            wk.write(new FileOutputStream(new File("D://测试/a.xls")));
            // 关闭工作薄
            wk.close();
        }
    }
    /*public static CellStyle getFontStyle(Workbook wb, int fontSize, short color) {
        CellStyle cellStyle = wb.createCellStyle();
        Font font = setFont(wb, fontSize, color);
        center(cellStyle);
        cellStyle.setFont(font);
        return cellStyle;
    }

    public static void center(CellStyle cellStyle) {
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
    }


    public static Font setFont(Workbook wb, int fontSize, short color) {
        //设置字体
        Font font = wb.createFont();
        font.setColor(color);
        font.setFontHeightInPoints((short) fontSize);
        return font;
    }

    public static Font setFont(Workbook wb, int fontSize) {
        //设置字体
        Font font = wb.createFont();
        font.setFontHeightInPoints((short) fontSize);
        return font;
    }*/

    @Test
    public void test5() {
        List<OverallSituation> list = new ArrayList<>();
        OverallSituation os1 = new OverallSituation();
        os1.setBustotal(1);
        os1.setRegion("一");
        OverallSituation os2 = new OverallSituation();
        os2.setBustotal(2);
        os2.setRegion("二");
        OverallSituation os3 = new OverallSituation();
        os3.setBustotal(3);
        os3.setRegion("三");
        list.add(os1);
        list.add(os3);
        list.add(os2);

        //升序
//        list.sort(Comparator.comparingInt(OverallSituation::getBustotal));

        //降序
        list.sort(Comparator.comparingInt(OverallSituation::getBustotal).reversed());

        System.out.println(ArrayUtils.toString(list));
    }
}
