package com.chinaweal.test;

import com.chinaweal.entity.testLiny;
import com.chinaweal.mapper.testLinyMapper;
import com.chinaweal.sdcs.crcs.business.entity.*;
import com.chinaweal.sdcs.crcs.business.mapper.*;
import com.chinaweal.sdcs.crcs.system.entity.Rights;
import com.chinaweal.sdcs.crcs.system.mapper.BasecodeformMapper;
import com.chinaweal.sdcs.crcs.system.mapper.RightsMapper;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-context.xml")
@WebAppConfiguration
public class ExportRights {

    @Resource
    private RightsMapper rightsMapper;

   @Test
    public void test9() throws IOException, ParseException {

       for( int num = 1 ; num < 2 ; num++){

        String index = String.valueOf(num);

//           InputStream inputS=new FileInputStream("C:\\Users\\lin99\\Desktop\\12315系统历史数据\\12315系统历史数据（举报类）Excel\\业务信息 ("+index+").xls");
           InputStream inputS=new FileInputStream("C:\\Users\\lin99\\Desktop\\0e0aee66b318ea54db051eafebf9aa1f.xls");
           HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputS);
           HSSFSheet sheet = hssfWorkbook.getSheet("Sheet1");
//       List
//           for(int i=1;i<sheet.getLastRowNum();i++){
           for(int i=2;i<142;i++){
               System.out.println(sheet.getLastRowNum());
               System.out.println(sheet.getRow(i).getCell(1));
               System.out.println("第"+i+"行");
//           String One = sheet.getRow(i).getCell(1).getStringCellValue();
//           String Two = sheet.getRow(i).getCell(2).getStringCellValue();
//           System.out.println(sheet.getRow(i).getCell(0));
//           System.out.println(sheet.getRow(i).getCell(1));
//           System.out.println(sheet.getRow(i).getCell(2));
//           System.out.println(sheet.getRow(i).getCell(3));
//           System.out.println(sheet.getRow(i).getCell(4));
//           System.out.println(sheet.getRow(i).getCell(5));
//           System.out.println(sheet.getRow(i).getCell(6));
//           System.out.println(sheet.getRow(i).getCell(7).getNumericCellValue());
//           System.out.println(sheet.getRow(i).getCell(8));
//           System.out.println(sheet.getRow(i).getCell(9));
//           System.out.println(sheet.getRow(i).getCell(10));
//           System.out.println(sheet.getRow(i).getCell(11));
//           System.out.println(sheet.getRow(i).getCell(12));
//           System.out.println(sheet.getRow(i).getCell(13));
//           System.out.println(sheet.getRow(i).getCell(14));
//           System.out.println(sheet.getRow(i).getCell(15));
//           System.out.println(sheet.getRow(i).getCell(16));
//           System.out.println(sheet.getRow(i).getCell(17));
//           System.out.println(sheet.getRow(i).getCell(18));
//           System.out.println(sheet.getRow(i).getCell(19).getDateCellValue());
//           System.out.println(sheet.getRow(i).getCell(20));
//           System.out.println(sheet.getRow(i).getCell(21));
//           System.out.println(sheet.getRow(i).getCell(22));
//           System.out.println(sheet.getRow(i).getCell(23));
//           System.out.println(sheet.getRow(i).getCell(24));
//           System.out.println(sheet.getRow(i).getCell(25));
//           System.out.println(sheet.getRow(i).getCell(26));
//           System.out.println(sheet.getRow(i).getCell(27));
//           System.out.println(sheet.getRow(i).getCell(28));
//           System.out.println(sheet.getRow(i).getCell(29));
//           System.out.println(sheet.getRow(i).getCell(30));
//           System.out.println(sheet.getRow(i).getCell(31));
//           System.out.println(sheet.getRow(i).getCell(32));


              Rights right = new Rights();

               UUID id = UUID.randomUUID();
               String caseguid=id.toString();//主键
               right.setId(caseguid);
               right.setEnterprise(sheet.getRow(i).getCell(0).getStringCellValue());
               right.setType(sheet.getRow(i).getCell(1).getStringCellValue());
               right.setAddress(sheet.getRow(i).getCell(2).getStringCellValue());
               right.setRepresentative(sheet.getRow(i).getCell(3).getStringCellValue());
               System.out.println(sheet.getRow(i).getCell(4).getCellType());
               if("STRING".equals(sheet.getRow(i).getCell(4).getCellType().toString())){
                   right.setPhone(sheet.getRow(i).getCell(4).getStringCellValue());
               }else{
                   right.setPhone(new DecimalFormat().format(sheet.getRow(i).getCell(4).getNumericCellValue()).replace(",",""));
               }


               right.setRegion(sheet.getRow(i).getCell(5).getStringCellValue());

               try {
                   rightsMapper.insert(right);
               } catch (Exception e) {
                   e.printStackTrace();
               }

           }

       }





    }
}
