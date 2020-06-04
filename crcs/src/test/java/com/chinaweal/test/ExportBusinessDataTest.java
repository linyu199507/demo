package com.chinaweal.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chinaweal.entity.testLiny;
import com.chinaweal.mapper.testLinyMapper;
import com.chinaweal.sdcs.crcs.business.entity.Complaintform;
import com.chinaweal.sdcs.crcs.business.entity.Processinfo;
import com.chinaweal.sdcs.crcs.business.entity.Tasklist;
import com.chinaweal.sdcs.crcs.business.mapper.ComplaintformMapper;
import com.chinaweal.sdcs.crcs.business.mapper.ProcessinfoMapper;
import com.chinaweal.sdcs.crcs.business.mapper.TasklistMapper;
import com.chinaweal.sdcs.crcs.system.entity.BasecodeContrast;
import com.chinaweal.sdcs.crcs.system.entity.Basecodeform;
import com.chinaweal.sdcs.crcs.system.mapper.BasecodeformMapper;
import com.chinaweal.sdcs.crcs.system.service.IBasecodeContrastService;
import com.chinaweal.sdcs.crcs.system.service.IBasecodeformService;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-context.xml")
@WebAppConfiguration
public class ExportBusinessDataTest {


    @Resource
    private testLinyMapper testLinyMapper;
    @Resource
    private TasklistMapper tasklistMapper;
    @Resource
    private ComplaintformMapper complaintformMapper;
    @Resource
    private ProcessinfoMapper processinfoMapper;
    @Resource
    private BasecodeformMapper basecodeformMapper;



   @Test
    public void test9() throws IOException, ParseException {

//       for( int num = 1 ; num < 54 ; num++){

//        String index = String.valueOf(num);

//           InputStream inputS=new FileInputStream("C:\\Users\\lin99\\Desktop\\12315系统历史数据\\12315系统历史数据  ( 投诉类 ) Excel\\业务信息 ("+index+").xls");
           InputStream inputS=new FileInputStream("C:\\Users\\lin99\\Desktop\\12315系统历史数据\\12315系统历史数据  ( 投诉类 ) Excel\\业务信息.xls");
           HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputS);
           HSSFSheet sheet = hssfWorkbook.getSheet("查询结果");
//       List
           for(int i=1;i<sheet.getLastRowNum();i++){
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
//           System.out.println(sheet.getRow(i).getCell(7));
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
//           System.out.println(sheet.getRow(i).getCell(19));
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
//           System.out.println(sheet.getRow(i).getCell(33));
//           System.out.println(sheet.getRow(i).getCell(34));
//           System.out.println(sheet.getRow(i).getCell(35));
//           System.out.println(sheet.getRow(i).getCell(36));
//           System.out.println(sheet.getRow(i).getCell(37));
//           System.out.println(sheet.getRow(i).getCell(38));
//           System.out.println(sheet.getRow(i).getCell(39));

               //客体类别
               String objclassifyCodeid ;
               Map<String,Object> mapOne = new HashMap<>();
               mapOne.put("type","object12345");
               mapOne.put("comment",sheet.getRow(i).getCell(15).getStringCellValue());
               List<Basecodeform> listTwo = basecodeformMapper.getbasecodeformByComment(mapOne);
               if( !listTwo.isEmpty() ){
                   Basecodeform baseOne = listTwo.get(0);
                   objclassifyCodeid = baseOne.getCodeid();
               }else{
                   objclassifyCodeid = "012101";
               }


               //问题类别
               String complaintstypeCodeid =null;
               Map<String,Object> map = new HashMap<>();
               map.put("type","question12315");
               map.put("comment",sheet.getRow(i).getCell(22).getStringCellValue());
               List<Basecodeform> listOne = basecodeformMapper.getbasecodeformByComment(map);
               if( !listOne.isEmpty()){
                   Basecodeform baseOne = listOne.get(0);
                   complaintstypeCodeid = baseOne.getCodeid().substring(4,6);
                   if("02".equals(complaintstypeCodeid)){
                       complaintstypeCodeid = "03";
                   }else if("03".equals(complaintstypeCodeid)){
                       complaintstypeCodeid = "04";
                   }else if("01".equals(complaintstypeCodeid)){
                       complaintstypeCodeid = "02";
                   }else if("07".equals(complaintstypeCodeid)){
                       complaintstypeCodeid = "08";
                   }else if("09".equals(complaintstypeCodeid)){
                       complaintstypeCodeid = "10";
                   }else if("11".equals(complaintstypeCodeid)){
                       complaintstypeCodeid = "12";
                   }else if("12".equals(complaintstypeCodeid)){
                       complaintstypeCodeid = "13";
                   }else if("14".equals(complaintstypeCodeid)){
                       complaintstypeCodeid = "15";
                   }else if("20".equals(complaintstypeCodeid)){
                       complaintstypeCodeid = "19";
                   }else {
                       complaintstypeCodeid = "14";
                   }
               }

//               System.out.println(objclassifyCodeid);
//               System.out.println(ComplaintstypeCodeid);




               UUID idCom = UUID.randomUUID();
               String idComStr=idCom.toString();//类型表id

               UUID idTask = UUID.randomUUID();
               String idTaskStr=idTask.toString();//tasklist表id

               UUID idPro = UUID.randomUUID();
               String idProStr=idTask.toString();//processinfo

               UUID idCaseguid = UUID.randomUUID();
               String caseguid=idCaseguid.toString();//业务caseguid


               Processinfo processinfo = new Processinfo();
               processinfo.setId(idProStr);
               processinfo.setCaseguid(caseguid);
               processinfo.setIsmain("1");
               processinfo.setStatus("1");


               Complaintform complaint = new Complaintform();
               complaint.setId(idComStr);
               complaint.setCaseguid(caseguid);
               complaint.setFormtype(sheet.getRow(i).getCell(1).getStringCellValue());
               //String 转 date
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               Date time1 = sdf.parse(sheet.getRow(i).getCell(2).getStringCellValue());
               complaint.setUpdatedate(time1);

               complaint.setRqstperson(sheet.getRow(i).getCell(4).getStringCellValue());
               complaint.setGender(sheet.getRow(i).getCell(5).getStringCellValue());
               complaint.setLinknumber(sheet.getRow(i).getCell(6).getStringCellValue());
               complaint.setEmaiaddress(sheet.getRow(i).getCell(7).getStringCellValue());
               complaint.setRqstaddress(sheet.getRow(i).getCell(8).getStringCellValue());
               complaint.setSubjectname(sheet.getRow(i).getCell(9).getStringCellValue());
               complaint.setRegisterno(sheet.getRow(i).getCell(10).getStringCellValue());
               complaint.setSubjectphone(sheet.getRow(i).getCell(12).getStringCellValue());
               complaint.setSubjectaddress(sheet.getRow(i).getCell(13).getStringCellValue());
               complaint.setShoppingmode(sheet.getRow(i).getCell(14).getStringCellValue());
               complaint.setObjclassify(sheet.getRow(i).getCell(15).getStringCellValue());
               complaint.setObjclassifycodeid(objclassifyCodeid);
               complaint.setBrand(sheet.getRow(i).getCell(16).getStringCellValue());
               complaint.setObjname(sheet.getRow(i).getCell(17).getStringCellValue());
               complaint.setOrdernumber(sheet.getRow(i).getCell(18).getStringCellValue());

               //String 转 date
               if( !sheet.getRow(i).getCell(19).getStringCellValue().isEmpty()){
                   Date time2 = sdf.parse(sheet.getRow(i).getCell(19).getStringCellValue());
                   complaint.setDisputedate(time2);
               }

               String number1 = sheet.getRow(i).getCell(20).getStringCellValue();
               BigDecimal big1 =new BigDecimal(number1);
//                    System.out.println(big1);

               complaint.setObjmoney(big1);
               complaint.setReport(sheet.getRow(i).getCell(21).getStringCellValue());
               complaint.setComplaintstype(sheet.getRow(i).getCell(22).getStringCellValue());
               complaint.setComplaintstypecodeid(complaintstypeCodeid);
               complaint.setProblemdescribe(sheet.getRow(i).getCell(24).getStringCellValue());



               Tasklist tasklist = new Tasklist();
               tasklist.setId(idTaskStr);
               tasklist.setCaseguid(caseguid);
               tasklist.setSerialnum(sheet.getRow(i).getCell(0).getStringCellValue());//登记编号
               tasklist.setTserialnum(sheet.getRow(i).getCell(0).getStringCellValue());

               Date time3 = sdf.parse(sheet.getRow(i).getCell(2).getStringCellValue());
               tasklist.setCreatedate(time3);
               tasklist.setAssigndate(time3);

               tasklist.setRegistrant(sheet.getRow(i).getCell(25).getStringCellValue());
               tasklist.setRegdeptname(sheet.getRow(i).getCell(26).getStringCellValue());
               tasklist.setHandledeptname(sheet.getRow(i).getCell(30).getStringCellValue());

               SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
               Date time4 = sdf1.parse(sheet.getRow(i).getCell(32).getStringCellValue());
               tasklist.setBlqx(time4);

               Date time5 = sdf.parse(sheet.getRow(i).getCell(33).getStringCellValue());
               tasklist.setFirstblqx(time5);

               Date time6 = sdf.parse(sheet.getRow(i).getCell(34).getStringCellValue());
               tasklist.setTaskenddate(time6);

               if("已办结".equals(sheet.getRow(i).getCell(36).getStringCellValue())){
                   tasklist.setOperate("结束");
                   tasklist.setNextnodeid("end");
                   processinfo.setNodeid("end");
               }else {
                   tasklist.setOperate("不受理");
                   tasklist.setNextnodeid("noAccept");
                   processinfo.setNodeid("noAccept");
               }



               tasklist.setContent(sheet.getRow(i).getCell(37).getStringCellValue());
               tasklist.setBustype("投诉类");
               tasklist.setFormtype("02");
               tasklist.setSystemsource("03");
               tasklist.setIsaccept("1");
               tasklist.setState("0");
               tasklist.setIscbdf("1");
               System.out.println(complaint.getCaseguid());

               processinfo.setHandleruserid("991804e6-2595-474d-b345-e93aab3729c2");
               processinfo.setHandledept("消费者权益保护科");
               processinfo.setHandledeptid("da00ef72-5b56-4f5b-88c2-4b038a7f41a0");
               processinfo.setStartdate(time1);
               processinfo.setEnddate(time6);
               processinfo.setUpdatedate(new Date());
               processinfo.setHandleorgid("6ba12fbe-0100-1000-e000-03fec0a85893");
               processinfo.setHandleorgname("佛山市顺德区市场监督管理局");


               testLiny log = new testLiny();
               log.setId(UUID.randomUUID().toString());
               log.setCaseguid(caseguid);
               log.setSernumber(sheet.getRow(i).getCell(0).getStringCellValue());
               log.setSystem("12315系统");
               log.setType(sheet.getRow(i).getCell(1).getStringCellValue());
               log.setUpdate(new Date());

               complaintformMapper.insert(complaint);
               tasklistMapper.insert(tasklist);
               processinfoMapper.insert(processinfo);
               testLinyMapper.insert(log);

           }

       }





//    }
}
