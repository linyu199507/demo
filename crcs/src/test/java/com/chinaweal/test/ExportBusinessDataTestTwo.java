package com.chinaweal.test;

import com.chinaweal.entity.testLiny;
import com.chinaweal.mapper.testLinyMapper;
import com.chinaweal.sdcs.crcs.business.entity.*;
import com.chinaweal.sdcs.crcs.business.mapper.*;
import com.chinaweal.sdcs.crcs.system.entity.Basecodeform;
import com.chinaweal.sdcs.crcs.system.mapper.BasecodeformMapper;
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
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-context.xml")
@WebAppConfiguration
public class ExportBusinessDataTestTwo {


    @Resource
    private testLinyMapper testLinyMapper;
    @Resource
    private TasklistMapper tasklistMapper;
    @Resource
    private EconomyformMapper economyformMapper;
    @Resource
    private ProcessinfoMapper processinfoMapper;
    @Resource
    private BasecodeformMapper basecodeformMapper;
    @Resource
    private ComplaintformMapper complaintformMapper;
    @Resource
    private ConsultationformMapper consultationformMapper;
    @Resource
    private OpinionMapper opinionMapper;
    @Resource
    private CommomformMapper commomformMapper;



   @Test
    public void test9() throws IOException, ParseException {

       for( int num = 1 ; num < 2 ; num++){

        String index = String.valueOf(num);

//           InputStream inputS=new FileInputStream("C:\\Users\\lin99\\Desktop\\12315系统历史数据\\12315系统历史数据（举报类）Excel\\业务信息 ("+index+").xls");
           InputStream inputS=new FileInputStream("C:\\Users\\lin99\\Desktop\\手工录入渠道汇总.xls");
           HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputS);
           HSSFSheet sheet = hssfWorkbook.getSheet("咨询");
//       List
//           for(int i=1;i<sheet.getLastRowNum();i++){
           for(int i=1;i<211;i++){
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

               Consultationform con = new Consultationform();
               con = null;
               Commomform com = new Commomform();
               com = null;
               Complaintform complaint = new Complaintform();
               complaint = null;
               Economyform economy = new Economyform();
               economy = null;
               UUID idCaseguid = UUID.randomUUID();
               String caseguid=idCaseguid.toString();//业务caseguid

               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

           if("咨询".equals(sheet.getRow(i).getCell(0).getStringCellValue())){
               com = new Commomform();
               UUID Conid = UUID.randomUUID();
               String conid=Conid.toString();
               com.setId(conid);
               com.setCaseguid(caseguid);
               com.setRqsttitle(sheet.getRow(i).getCell(1).getStringCellValue()+sheet.getRow(i).getCell(3).getStringCellValue());
               com.setAppealsource(sheet.getRow(i).getCell(2).getStringCellValue());
               com.setTransfernumber(sheet.getRow(i).getCell(3).getStringCellValue());
               com.setIncomingcommpany(sheet.getRow(i).getCell(4).getStringCellValue());
               com.setName(sheet.getRow(i).getCell(6).getStringCellValue());
               System.out.println(sheet.getRow(i).getCell(7));
               if(sheet.getRow(i).getCell(7).equals("")){
                   com.setRqstphonenumber(new BigDecimal(sheet.getRow(i).getCell(7).getStringCellValue()).toString());

               }
               com.setGender(sheet.getRow(i).getCell(8).getStringCellValue());
               com.setIsprofessional(sheet.getRow(i).getCell(9).getStringCellValue());
               com.setConsultantaddress(sheet.getRow(i).getCell(10).getStringCellValue());
               com.setEntname(sheet.getRow(i).getCell(11).getStringCellValue());
               com.setLinkphonenumber(sheet.getRow(i).getCell(12).getStringCellValue());
               com.setAddress(sheet.getRow(i).getCell(13).getStringCellValue());
               com.setComplaintstype(sheet.getRow(i).getCell(14).getStringCellValue());
               System.out.println(sheet.getRow(i).getCell(19).getCellType());


               if("NUMERIC".equals(sheet.getRow(i).getCell(19).getCellType())){
                   com.setAcceptdate(sheet.getRow(i).getCell(19).getDateCellValue());
               }else if("STRING".equals(sheet.getRow(i).getCell(19).getCellType())){
                   DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                   com.setAcceptdate(format.parse(sheet.getRow(i).getCell(19).getStringCellValue()));
               }else{

               }

               com.setKeyword(sheet.getRow(i).getCell(23).getStringCellValue());
               com.setRqstcontent(sheet.getRow(i).getCell(24).getStringCellValue());
           }else if("投诉".equals(sheet.getRow(i).getCell(0).getStringCellValue())){
               complaint = new Complaintform();
               complaint.setId(UUID.randomUUID().toString());
               complaint.setCaseguid(caseguid);
               complaint.setRqsttitle(sheet.getRow(i).getCell(1).getStringCellValue());
               complaint.setAppealsource(sheet.getRow(i).getCell(2).getStringCellValue());
               complaint.setTransfernumber(sheet.getRow(i).getCell(3).getStringCellValue());
               complaint.setDetailaddress(sheet.getRow(i).getCell(4).getStringCellValue());
               complaint.setSubordinateplate(sheet.getRow(i).getCell(5).getStringCellValue());
               complaint.setRqstperson(sheet.getRow(i).getCell(6).getStringCellValue());
//               complaint.setRqstnumber(new BigDecimal(sheet.getRow(i).getCell(7).getNumericCellValue()).toString());
               if("STRING".equals(sheet.getRow(i).getCell(7).getCellType().toString())){
                   complaint.setRqstnumber(sheet.getRow(i).getCell(7).getStringCellValue());
               }else{
                   complaint.setRqstnumber(new DecimalFormat().format(sheet.getRow(i).getCell(7).getNumericCellValue()).replace(",",""));

               }

               complaint.setGender(sheet.getRow(i).getCell(8).getStringCellValue());
               complaint.setIsprofessional(sheet.getRow(i).getCell(9).getStringCellValue());
               complaint.setRqstaddress(sheet.getRow(i).getCell(10).getStringCellValue());
               complaint.setSubjectname(sheet.getRow(i).getCell(11).getStringCellValue());
               complaint.setLinknumber(sheet.getRow(i).getCell(12).getStringCellValue());
               complaint.setSubjectaddress(sheet.getRow(i).getCell(13).getStringCellValue());
               complaint.setComplaintstype(sheet.getRow(i).getCell(14).getStringCellValue());
               complaint.setServicetype(sheet.getRow(i).getCell(15).getStringCellValue());
               complaint.setObjname(sheet.getRow(i).getCell(16).getStringCellValue());
               complaint.setBrand(sheet.getRow(i).getCell(17).getStringCellValue());
               complaint.setIsweb(sheet.getRow(i).getCell(18).getStringCellValue());

               System.out.println(sheet.getRow(i).getCell(19).getCellType());
               if("STRING".equals(sheet.getRow(i).getCell(19).getCellType().toString())){
                   complaint.setAcceptdate(new SimpleDateFormat("yyyy-MM-dd").parse(sheet.getRow(i).getCell(19).getStringCellValue()));
                   complaint.setRecorddate(new SimpleDateFormat("yyyy-MM-dd").parse(sheet.getRow(i).getCell(19).getStringCellValue()));
               }else{
                   complaint.setAcceptdate(sheet.getRow(i).getCell(19).getDateCellValue());
                   complaint.setRecorddate(sheet.getRow(i).getCell(19).getDateCellValue());

               }

               complaint.setAcceptancenumber(sheet.getRow(i).getCell(21).getStringCellValue());
               complaint.setTransferbooknumber(sheet.getRow(i).getCell(22).getStringCellValue());
               complaint.setKeyword(sheet.getRow(i).getCell(23).getStringCellValue());
               complaint.setProblemdescribe(sheet.getRow(i).getCell(24).getStringCellValue());

           }else if("举报".equals(sheet.getRow(i).getCell(0).getStringCellValue())){
               System.out.println("正确--举报类");
               economy = new Economyform();
               economy.setId(UUID.randomUUID().toString());
               economy.setCaseguid(caseguid);
               economy.setRqsttitle(sheet.getRow(i).getCell(1).getStringCellValue());
               economy.setAppealsource(sheet.getRow(i).getCell(2).getStringCellValue());
               economy.setTransfernumber(sheet.getRow(i).getCell(3).getStringCellValue());
               economy.setDetailaddress(sheet.getRow(i).getCell(4).getStringCellValue());
               economy.setSubordinateplate(sheet.getRow(i).getCell(5).getStringCellValue());
               economy.setRqstperson(sheet.getRow(i).getCell(6).getStringCellValue());
               System.out.println(sheet.getRow(i).getCell(7).getCellType());
               if("STRING".equals(sheet.getRow(i).getCell(7).getCellType().toString())){
//                   economy.setRqstnumber(sheet.getRow(i).getCell(7).getStringCellValue());
               }else{
//                   System.out.println(new DecimalFormat().format(sheet.getRow(i).getCell(7).getNumericCellValue()).replace(",",""));
                   economy.setRqstnumber(new DecimalFormat().format(sheet.getRow(i).getCell(7).getNumericCellValue()).replace(",",""));

               }

               economy.setGender(sheet.getRow(i).getCell(8).getStringCellValue());
               economy.setIsprofessional(sheet.getRow(i).getCell(9).getStringCellValue());
               economy.setRqstaddress(sheet.getRow(i).getCell(10).getStringCellValue());
               economy.setSubjectname(sheet.getRow(i).getCell(11).getStringCellValue());
               economy.setLinknumber(sheet.getRow(i).getCell(12).getStringCellValue());
               economy.setSubjectaddress(sheet.getRow(i).getCell(13).getStringCellValue());
               economy.setReportprovidelb(sheet.getRow(i).getCell(14).getStringCellValue());
               economy.setObjectcategory(sheet.getRow(i).getCell(15).getStringCellValue());
               economy.setObjname(sheet.getRow(i).getCell(16).getStringCellValue());
               economy.setBrand(sheet.getRow(i).getCell(17).getStringCellValue());
               economy.setIsweb(sheet.getRow(i).getCell(18).getStringCellValue());

               System.out.println(sheet.getRow(i).getCell(19).getCellType());
               if("STRING".equals(sheet.getRow(i).getCell(19).getCellType().toString())){
                   economy.setAcceptdate(new SimpleDateFormat("yyyy-MM-dd").parse(sheet.getRow(i).getCell(19).getStringCellValue()));
                   economy.setRecorddate(new SimpleDateFormat("yyyy-MM-dd").parse(sheet.getRow(i).getCell(19).getStringCellValue()));
               }else{
                   economy.setAcceptdate(sheet.getRow(i).getCell(19).getDateCellValue());
                   economy.setRecorddate(sheet.getRow(i).getCell(19).getDateCellValue());

               }

               economy.setAcceptancenumber(sheet.getRow(i).getCell(21).getStringCellValue());
               economy.setTransferbooknumber(sheet.getRow(i).getCell(22).getStringCellValue());
               economy.setKeyword(sheet.getRow(i).getCell(23).getStringCellValue());
               economy.setProblemdescribe(sheet.getRow(i).getCell(24).getStringCellValue());

           }


               //客体类别
//               String objclassifyCodeid ;
//               Map<String,Object> mapOne = new HashMap<>();
//               mapOne.put("type","object12345");
//               mapOne.put("comment",sheet.getRow(i).getCell(15).getStringCellValue());
//               List<Basecodeform> listTwo = basecodeformMapper.getbasecodeformByComment(mapOne);
//               if( !listTwo.isEmpty() ){
//                   Basecodeform baseOne = listTwo.get(0);
//                   objclassifyCodeid = baseOne.getCodeid();
//               }else{
//                   objclassifyCodeid = "012101";
//               }


               //问题类别
//               String complaintstypeCodeid =null;
//               Map<String,Object> map = new HashMap<>();
//               map.put("type","question12315");
//               map.put("comment",sheet.getRow(i).getCell(22).getStringCellValue());
//               List<Basecodeform> listOne = basecodeformMapper.getbasecodeformByComment(map);
//               if( !listOne.isEmpty()){
//                   Basecodeform baseOne = listOne.get(0);
//                   complaintstypeCodeid = baseOne.getCodeid().substring(4,6);
//                   if("02".equals(complaintstypeCodeid)){
//                       complaintstypeCodeid = "03";
//                   }else if("03".equals(complaintstypeCodeid)){
//                       complaintstypeCodeid = "04";
//                   }else if("01".equals(complaintstypeCodeid)){
//                       complaintstypeCodeid = "02";
//                   }else if("07".equals(complaintstypeCodeid)){
//                       complaintstypeCodeid = "08";
//                   }else if("09".equals(complaintstypeCodeid)){
//                       complaintstypeCodeid = "10";
//                   }else if("11".equals(complaintstypeCodeid)){
//                       complaintstypeCodeid = "12";
//                   }else if("12".equals(complaintstypeCodeid)){
//                       complaintstypeCodeid = "13";
//                   }else if("14".equals(complaintstypeCodeid)){
//                       complaintstypeCodeid = "15";
//                   }else if("20".equals(complaintstypeCodeid)){
//                       complaintstypeCodeid = "19";
//                   }else {
//                       complaintstypeCodeid = "14";
//                   }
//               }

//               System.out.println(objclassifyCodeid);
//               System.out.println(ComplaintstypeCodeid);




//               UUID idCom = UUID.randomUUID();
//               String idComStr=idCom.toString();//类型表id
//
//               UUID idTask = UUID.randomUUID();
//               String idTaskStr=idTask.toString();//tasklist表id
//
//               UUID idPro = UUID.randomUUID();
//               String idProStr=idTask.toString();//processinfo
//
//               UUID idCaseguid = UUID.randomUUID();
//               String caseguid=idCaseguid.toString();//业务caseguid
//
//
//               Processinfo processinfo = new Processinfo();
//               processinfo.setId(idProStr);
//               processinfo.setCaseguid(caseguid);
//               processinfo.setIsmain("1");
//               processinfo.setStatus("1");
//
//
//               Economyform economy = new Economyform();
//               economy.setId(idComStr);
//               economy.setCaseguid(caseguid);
//               economy.setFormtype(sheet.getRow(i).getCell(1).getStringCellValue());
//               //String 转 date
//               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//               Date time1 = sdf.parse(sheet.getRow(i).getCell(2).getStringCellValue());
//               economy.setUpdatedate(time1);
//
//               economy.setRqstperson(sheet.getRow(i).getCell(4).getStringCellValue());
//               economy.setGender(sheet.getRow(i).getCell(5).getStringCellValue());
//               economy.setLinknumber(sheet.getRow(i).getCell(6).getStringCellValue());
//               economy.setInformerpostcode(sheet.getRow(i).getCell(7).getStringCellValue());
//               economy.setRqstaddress(sheet.getRow(i).getCell(8).getStringCellValue());
//               economy.setSubjectname(sheet.getRow(i).getCell(9).getStringCellValue());
//               economy.setRegisterno(sheet.getRow(i).getCell(10).getStringCellValue());
//               economy.setSubjectphone(sheet.getRow(i).getCell(12).getStringCellValue());
//               economy.setSubjectaddress(sheet.getRow(i).getCell(13).getStringCellValue());
//               economy.setSellingformat(sheet.getRow(i).getCell(14).getStringCellValue());
//               economy.setObjectcategory(sheet.getRow(i).getCell(15).getStringCellValue());
//               economy.setObjectcategorycodeid(objclassifyCodeid);
//               economy.setBrand(sheet.getRow(i).getCell(16).getStringCellValue());
//               economy.setObjname(sheet.getRow(i).getCell(17).getStringCellValue());
//               economy.setOrdernumber(sheet.getRow(i).getCell(18).getStringCellValue());
//
//               //String 转 date
////               if( !sheet.getRow(i).getCell(19).getStringCellValue().isEmpty()){
////                   Date time2 = sdf.parse(sheet.getRow(i).getCell(19).getStringCellValue());
////                   economy.setDisputedate(time2);
////               }
//
//               String number1 = sheet.getRow(i).getCell(20).getStringCellValue();
//               BigDecimal big1 =new BigDecimal(number1);
////                    System.out.println(big1);
//
//               economy.setObjmoney(big1);
//               economy.setReport(sheet.getRow(i).getCell(21).getStringCellValue());
//               economy.setReportprovidelb(sheet.getRow(i).getCell(22).getStringCellValue());
//               economy.setReportprovidelbcodeid(complaintstypeCodeid);
//               economy.setProblemdescribe(sheet.getRow(i).getCell(24).getStringCellValue());
//
//
//
               Tasklist tasklist = new Tasklist();
               UUID idTask = UUID.randomUUID();
               String idTaskStr=idTask.toString();//tasklist表id
               tasklist.setId(idTaskStr);
               tasklist.setCaseguid(caseguid);
               tasklist.setRqsttitle(sheet.getRow(i).getCell(1).getStringCellValue());

               if("8180平台".equals(sheet.getRow(i).getCell(2).getStringCellValue()) ||
                       "纪检监察行政投诉平台".equals(sheet.getRow(i).getCell(2).getStringCellValue())){
                   tasklist.setSerialnum(sheet.getRow(i).getCell(3).getStringCellValue());//登记编号
                   tasklist.setTserialnum(sheet.getRow(i).getCell(3).getStringCellValue());
               }



//               Date time3 = sdf.parse(sheet.getRow(i).getCell(19).getStringCellValue());
               System.out.println("这里");
               System.out.println(sheet.getRow(i).getCell(19));
               if("NUMERIC".equals(sheet.getRow(i).getCell(19).getCellType().toString())){
                   tasklist.setCreatedate(sheet.getRow(i).getCell(19).getDateCellValue());
                   tasklist.setAssigndate(sheet.getRow(i).getCell(19).getDateCellValue());

//               }else if("STRING".equals(sheet.getRow(i).getCell(19).getCellType())){
               }else {
                   DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                   tasklist.setCreatedate(format.parse(sheet.getRow(i).getCell(19).getStringCellValue()));
                   tasklist.setAssigndate(format.parse(sheet.getRow(i).getCell(19).getStringCellValue()));

               }
//               tasklist.setCreatedate(sheet.getRow(i).getCell(19).getDateCellValue());
//               tasklist.setAssigndate(sheet.getRow(i).getCell(19).getDateCellValue());
//
//               tasklist.setRegistrant(sheet.getRow(i).getCell(25).getStringCellValue());
//               tasklist.setRegdeptname(sheet.getRow(i).getCell(26).getStringCellValue());
               tasklist.setHandledeptname(sheet.getRow(i).getCell(27).getStringCellValue());
//
//               SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
//               Date time4 = sdf1.parse(sheet.getRow(i).getCell(32).getStringCellValue());
//               tasklist.setBlqx(time4);
//
//               Date time5 = sdf.parse(sheet.getRow(i).getCell(33).getStringCellValue());
//               tasklist.setFirstblqx(time5);
//
//               Date time6 = sdf.parse(sheet.getRow(i).getCell(34).getStringCellValue());
//               tasklist.setTaskenddate(time6);
//
//               if("立案".equals(sheet.getRow(i).getCell(37).getStringCellValue())){
//                   tasklist.setIsregistercase("是");
//               }else {
//                   tasklist.setIsregistercase("否");
//               }
//
//               if("已办结".equals(sheet.getRow(i).getCell(36).getStringCellValue())){
//                   tasklist.setOperate("结束");
//                   tasklist.setNextnodeid("end");
//                   processinfo.setNodeid("end");
//               }else {
//                   tasklist.setOperate("不受理");
//                   tasklist.setNextnodeid("noAccept");
//                   processinfo.setNodeid("noAccept");
//               }
//
//               tasklist.setContent(sheet.getRow(i).getCell(32).getStringCellValue());

               if(sheet.getRow(i).getCell(32).getStringCellValue().length()>=2000){
                   System.out.println(sheet.getRow(i).getCell(32).getStringCellValue().length());
                   tasklist.setContent(sheet.getRow(i).getCell(32).getStringCellValue().substring(0,1999));

               }else{
                   tasklist.setContent(sheet.getRow(i).getCell(32).getStringCellValue());

               }

               tasklist.setBustype(sheet.getRow(i).getCell(0).getStringCellValue());

               if("咨询".equals(sheet.getRow(i).getCell(0).getStringCellValue())){
                   tasklist.setFormtype("01");
               }else if("投诉".equals(sheet.getRow(i).getCell(0).getStringCellValue())){
                   tasklist.setFormtype("02");
               }else if("举报".equals(sheet.getRow(i).getCell(0).getStringCellValue())){
                   tasklist.setFormtype("03");
               }

//               tasklist.setFormtype("03");
               tasklist.setSystemsource("01");
               tasklist.setIsaccept("1");
               tasklist.setState("0");
               tasklist.setIscbdf("1");
               tasklist.setNextnodeid("end");
               tasklist.setOperate("结束");


               Processinfo processinfo = new Processinfo();
               UUID idPro = UUID.randomUUID();
               String idProStr=idPro.toString();//processinfo
               processinfo.setId(idProStr);
               processinfo.setCaseguid(caseguid);
               processinfo.setIsmain("1");
               processinfo.setStatus("1");
               processinfo.setHandleruserid("991804e6-2595-474d-b345-e93aab3729c2");
               processinfo.setHandledept("消费者权益保护科");
               processinfo.setHandledeptid("da00ef72-5b56-4f5b-88c2-4b038a7f41a0");

               if("NUMERIC".equals(sheet.getRow(i).getCell(19).getCellType())){
                   processinfo.setStartdate(sheet.getRow(i).getCell(19).getDateCellValue());
               }else if("STRING".equals(sheet.getRow(i).getCell(19).getCellType())){
                   DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                   processinfo.setStartdate(format.parse(sheet.getRow(i).getCell(19).getStringCellValue()));
               }

//               processinfo.setStartdate(sheet.getRow(i).getCell(19).getDateCellValue());
//               processinfo.setEnddate(time6);
               processinfo.setUpdatedate(new Date());
               processinfo.setHandleorgid("6ba12fbe-0100-1000-e000-03fec0a85893");
               processinfo.setHandleorgname("佛山市顺德区市场监督管理局");
//
//
               testLiny log = new testLiny();
               log.setId(UUID.randomUUID().toString());
               log.setCaseguid(caseguid);
               log.setSernumber(sheet.getRow(i).getCell(0).getStringCellValue());
               log.setSystem("手工录入");
               log.setType(sheet.getRow(i).getCell(3).getStringCellValue());
               log.setUpdate(new Date());

               Opinion opin = new Opinion();
               opin.setId(UUID.randomUUID().toString());
               opin.setCaseguid(caseguid);
               opin.setNodename("结束");

               if(sheet.getRow(i).getCell(32).getStringCellValue().length()>=2000){
                   System.out.println(sheet.getRow(i).getCell(32).getStringCellValue().length());
                   opin.setHandleopinion(sheet.getRow(i).getCell(32).getStringCellValue().substring(0,1999));

               }else{
                   opin.setHandleopinion(sheet.getRow(i).getCell(32).getStringCellValue());

               }
               opin.setHandler("顺德区市场监督管理局");
               opin.setHandledept("佛山市市场监督管理局");
               opin.setOperate("结束");
               opin.setUpdatedate(new Date());
//
//               try {
//                   economyformMapper.insert(economy);
//               } catch (Exception e) {
//                   System.out.println(economy);
//                   e.printStackTrace();
//               }

//               economyformMapper.insert(economy);
               try {
                   if(complaint != null){
                       complaintformMapper.insert(complaint);
                   }else if(com != null){
                       commomformMapper.insert(com);
                   }else if(economy != null){
                       economyformMapper.insert(economy);
                   }
               } catch (Exception e) {
                   e.printStackTrace();
               }


               try {
                   tasklistMapper.insert(tasklist);
                   processinfoMapper.insert(processinfo);
                   opinionMapper.insert(opin);
                   testLinyMapper.insert(log);
               } catch (Exception e) {
                   e.printStackTrace();
               }

           }

       }





    }
}
