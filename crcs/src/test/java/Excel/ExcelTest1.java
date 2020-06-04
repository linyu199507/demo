package Excel;

import jdk.nashorn.internal.runtime.arrays.ArrayData;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelTest1 {

    /*private static final ArrayData.UntouchedArrayData FileUtil = ;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        //用已有的模板复制出来，没有模板自己创建file
        File file = FileUtil.copy(excelUrl.getPath(), dirPath, true);
//07版本，兼容03，不要用03HSSFWorkbook
//1、先创建workbook窗口
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
//2、再创建sheet工作表
        int indexSheet;
        XSSFSheet sheet = wb.getSheetAt(indexSheet);
//3、再创建row行，一定要判断，不然百分百空指针
        int rowIndex;
        XSSFRow row = sheet.getRow(rowIndex);
        if (null == row) {
            row = sheet.createRow(rowIndex);
        }
//4、再创建cell单元格，一定要判断，不然百分百空指针。
//cellnum是第几行
        XSSFCell cell = row.getCell(cellnum);
        if (null == cell) {
            cell = row.createCell(cellnum);
        }
//5、给单元格赋值数据，用list可以赋值一行
//dlFxRemoteXqVo 是List集合的名字，自定义
        cell.setCellValue(dlFxRemoteXqVo.getDlmc());

//6、附加：插入将图片转换为Base64编码，即插入图片可参考，再最后

//7、最后的收尾，这边只需要生成，没有下载，下载还需要通过流
//输出的位置，可定义新的路径
        OutputStream out = new FileOutputStream(file);
        wb.write(out);
        wb.close();
        out.close();

//8、输出到客户端，下载如下，上面不要关闭流
        fileName = new String(fileName.getBytes(),"ISO8859-1");
        response.setContentType("application/octet-stream;charset=ISO8859-1");
        response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
        response.addHeader("Pargam", "no-cache");
        response.addHeader("Cache-Control", "no-cache")
        OutputStream os = response.getOutputStream();
        wb.write(os);
        os.flush();
        os.close();

//6、添加图片
        byte[] b = new BASE64Decoder().decodeBuffer(imgStr);
        int pictureIdx = wb.addPicture(b, XSSFWorkbook.PICTURE_TYPE_PNG);
        XSSFDrawing drawing = sheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 0, 0, (short) col1, row1, (short) col1 + col2,
                row1 + row2);
        XSSFPicture pict = drawing.createPicture(anchor, pictureIdx);
        }
    */
}
