package com.chinaweal.test;

import com.chinaweal.entity.testLiny;
import com.chinaweal.mapper.testLinyMapper;
import com.chinaweal.sdcs.crcs.business.entity.*;
import com.chinaweal.sdcs.crcs.business.mapper.*;
import com.chinaweal.sdcs.crcs.system.entity.Basecodeform;
import com.chinaweal.sdcs.crcs.system.mapper.BasecodeformMapper;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
public class ExportBusinessDataTestOf12345 {


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
           InputStream inputS=new FileInputStream("C:\\Users\\lin99\\Desktop\\12345系统历史数据.xlsx");
           XSSFWorkbook hssfWorkbook = new XSSFWorkbook(inputS);
           XSSFSheet sheet = hssfWorkbook.getSheet("历史数据");
//       List
//           for(int i=1;i<sheet.getLastRowNum();i++){
           for(int i=1;i<=sheet.getLastRowNum();i++){//i=1 为起始位置
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

             String problemcodeid = null;
             String objectcodeid = null;
             String illegcodeid = null;

             if(!sheet.getRow(i).getCell(8).getStringCellValue().isEmpty()){
                 List listOne = Arrays.asList(sheet.getRow(i).getCell(8).getStringCellValue().split("-"));
                 System.out.println(listOne);

                 if("其他".equals(listOne.get(0))){
                     problemcodeid = "14";
                 }else{
                     Map<String,Object> mapOne = new HashMap<>();
                     mapOne.put("type","question12345");
                     mapOne.put("comment",listOne.get(0));
                     List<Basecodeform> base = basecodeformMapper.getbasecodeformByComment(mapOne);
                     problemcodeid = base.get(0).getCodeid();
                 }

                 System.out.println(problemcodeid);
               }
               if(!sheet.getRow(i).getCell(9).getStringCellValue().isEmpty()){
                   List listOne = Arrays.asList(sheet.getRow(i).getCell(9).getStringCellValue().split("-"));
                   System.out.println(listOne);
                   Map<String,Object> mapOne = new HashMap<>();
                   mapOne.put("type","object12345");

                   if(listOne.size()>1){
                       mapOne.put("comment",listOne.get(1));
                   }else{
                       mapOne.put("comment",listOne.get(0));
                   }

                   List<Basecodeform> base = basecodeformMapper.getbasecodeformByComment(mapOne);
                   objectcodeid = base.get(0).getCodeid();
                   System.out.println(objectcodeid);
               }
               if(!sheet.getRow(i).getCell(10).getStringCellValue().isEmpty()){
                   List listOne = Arrays.asList(sheet.getRow(i).getCell(10).getStringCellValue().split("-"));
                   System.out.println(listOne);
                   Map<String,Object> mapOne = new HashMap<>();
                   mapOne.put("type","illegal12345");
                   mapOne.put("comment",listOne.get(0));
                   List<Basecodeform> base = basecodeformMapper.getbasecodeformByComment(mapOne);
                   illegcodeid = base.get(0).getCodeid();
                   System.out.println(illegcodeid);
               }



               Commomform con = new Commomform();
               con = null;
               Complaintform complaint = new Complaintform();
               complaint = null;
               Economyform economy = new Economyform();
               economy = null;
               UUID idCaseguid = UUID.randomUUID();
               String caseguid=idCaseguid.toString();//业务caseguid

               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

           if("咨询".equals(sheet.getRow(i).getCell(4).getStringCellValue()) || "通用".equals(sheet.getRow(i).getCell(4).getStringCellValue())){
               con = new Commomform();
               UUID Conid = UUID.randomUUID();
               String conid=Conid.toString();
               con.setId(conid);
               con.setCaseguid(caseguid);

               if("STRING".equals(sheet.getRow(i).getCell(1).getCellType().toString())){
                   con.setName(sheet.getRow(i).getCell(1).getStringCellValue());
               }else{
                   con.setName(new DecimalFormat().format(sheet.getRow(i).getCell(1).getNumericCellValue()));
               }

               if("STRING".equals(sheet.getRow(i).getCell(2).getCellType().toString())){
                   con.setRqstphonenumber(sheet.getRow(i).getCell(2).getStringCellValue());
                   con.setLinkphonenumber(sheet.getRow(i).getCell(2).getStringCellValue());
               }else {
                   con.setRqstphonenumber(new DecimalFormat().format(sheet.getRow(i).getCell(2).getNumericCellValue()).replace(",",""));
                   con.setLinkphonenumber(new DecimalFormat().format(sheet.getRow(i).getCell(2).getNumericCellValue()).replace(",",""));
               }

               con.setFormtype(sheet.getRow(i).getCell(4).getStringCellValue());
               con.setFormmodule(sheet.getRow(i).getCell(5).getStringCellValue());
               con.setSubjectname(sheet.getRow(i).getCell(11).getStringCellValue());
               con.setAddress(sheet.getRow(i).getCell(12).getStringCellValue());
               con.setRqsttitle(sheet.getRow(i).getCell(17).getStringCellValue());
               con.setRqstcontent(sheet.getRow(i).getCell(18).getStringCellValue());


               if("NUMERIC".equals(sheet.getRow(i).getCell(13).getCellType().toString())){
                   con.setAcceptdate(sheet.getRow(i).getCell(13).getDateCellValue());
               }else {
                   DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                   con.setAcceptdate(format.parse(sheet.getRow(i).getCell(13).getStringCellValue()));
               }

           }else if("消费投诉".equals(sheet.getRow(i).getCell(4).getStringCellValue())){
               complaint = new Complaintform();
               complaint.setId(UUID.randomUUID().toString());
               complaint.setCaseguid(caseguid);
//               complaint.setRqsttitle(sheet.getRow(i).getCell(0).getStringCellValue());

               if("STRING".equals(sheet.getRow(i).getCell(1).getCellType().toString())){
                   complaint.setRqstperson(sheet.getRow(i).getCell(1).getStringCellValue());
               }else{
                   complaint.setRqstperson(new DecimalFormat().format(sheet.getRow(i).getCell(1).getNumericCellValue()));
               }

               if("STRING".equals(sheet.getRow(i).getCell(2).getCellType().toString())){
                   complaint.setRqstnumber(sheet.getRow(i).getCell(2).getStringCellValue());
                   complaint.setLinknumber(sheet.getRow(i).getCell(2).getStringCellValue());
               }else {
                   complaint.setRqstnumber(new DecimalFormat().format(sheet.getRow(i).getCell(2).getNumericCellValue()).replace(",",""));
                   complaint.setLinknumber(new DecimalFormat().format(sheet.getRow(i).getCell(2).getNumericCellValue()).replace(",",""));
               }


               complaint.setFormtype(sheet.getRow(i).getCell(4).getStringCellValue());
               complaint.setFocus(sheet.getRow(i).getCell(5).getStringCellValue());
               complaint.setComplaintstype(sheet.getRow(i).getCell(8).getStringCellValue());
               complaint.setComplaintstypecodeid(problemcodeid);
               complaint.setObjclassify(sheet.getRow(i).getCell(9).getStringCellValue());
               complaint.setObjclassifycodeid(objectcodeid);
               complaint.setSubjectname(sheet.getRow(i).getCell(11).getStringCellValue());

               if("STRING".equals(sheet.getRow(i).getCell(12).getCellType().toString())){
                   complaint.setSubjectaddress(sheet.getRow(i).getCell(12).getStringCellValue());
               }else{
                   complaint.setSubjectaddress(new DecimalFormat().format(sheet.getRow(i).getCell(12).getNumericCellValue()).replace(",",""));
               }

               complaint.setRqsttitle(sheet.getRow(i).getCell(17).getStringCellValue());
               complaint.setProblemdescribe(sheet.getRow(i).getCell(18).getStringCellValue());
               complaint.setObjmoney(new BigDecimal(sheet.getRow(i).getCell(19).getNumericCellValue()));//new BigDecimal(sheet.getRow(i).getCell(19).getStringCellValue())
               complaint.setIsweb(sheet.getRow(i).getCell(20).getStringCellValue());

//               complaint.setAcceptdate(sheet.getRow(i).getCell(13).getDateCellValue());


//               if("STRING".equals(sheet.getRow(i).getCell(7).getCellType().toString())){
//                   complaint.setRqstnumber(sheet.getRow(i).getCell(7).getStringCellValue());
//               }else{
//                   complaint.setRqstnumber(new DecimalFormat().format(sheet.getRow(i).getCell(7).getNumericCellValue()).replace(",",""));
//
//               }




               if("STRING".equals(sheet.getRow(i).getCell(13).getCellType().toString())){
                   complaint.setAcceptdate(new SimpleDateFormat("yyyy-MM-dd").parse(sheet.getRow(i).getCell(13).getStringCellValue()));
//                   complaint.setRecorddate(new SimpleDateFormat("yyyy-MM-dd").parse(sheet.getRow(i).getCell(13).getStringCellValue()));
               }else{
                   complaint.setAcceptdate(sheet.getRow(i).getCell(13).getDateCellValue());
//                   complaint.setRecorddate(sheet.getRow(i).getCell(13).getDateCellValue());

               }


           }else if("经济违法举报".equals(sheet.getRow(i).getCell(4).getStringCellValue())){
               economy = new Economyform();
               economy.setId(UUID.randomUUID().toString());
               economy.setCaseguid(caseguid);
//               economy.setRqsttitle(sheet.getRow(i).getCell(0).getStringCellValue());
//               economy.setRqsttitle(sheet.getRow(i).getCell(1).getStringCellValue());
               if("STRING".equals(sheet.getRow(i).getCell(1).getCellType().toString())){
                   economy.setRqstperson(sheet.getRow(i).getCell(1).getStringCellValue());
               }else{
                   economy.setRqstperson(new DecimalFormat().format(sheet.getRow(i).getCell(1).getNumericCellValue()).replace(",",""));
               }


               if("STRING".equals(sheet.getRow(i).getCell(2).getCellType().toString())){
                   economy.setRqstnumber(sheet.getRow(i).getCell(2).getStringCellValue());
               }else{
                   economy.setRqstnumber(new DecimalFormat().format(sheet.getRow(i).getCell(2).getNumericCellValue()).replace(",",""));
               }

               economy.setFormtype(sheet.getRow(i).getCell(4).getStringCellValue());
               economy.setFocus(sheet.getRow(i).getCell(5).getStringCellValue());
               economy.setProblemtype(sheet.getRow(i).getCell(8).getStringCellValue());
               economy.setProblemtypecodeid(problemcodeid);
               economy.setObjectcategory(sheet.getRow(i).getCell(9).getStringCellValue());
               economy.setObjectcategorycodeid(objectcodeid);
               economy.setIllegalrules(sheet.getRow(i).getCell(10).getStringCellValue());
               economy.setIllegalrulescodeid(illegcodeid);

               if("STRING".equals(sheet.getRow(i).getCell(11).getCellType().toString())){
                   economy.setSubjectname(sheet.getRow(i).getCell(11).getStringCellValue());
               }else{
                   economy.setSubjectname(new DecimalFormat().format(sheet.getRow(i).getCell(11).getNumericCellValue()).replace(",",""));
               }


               economy.setSubjectaddress(sheet.getRow(i).getCell(12).getStringCellValue());
               economy.setRqsttitle(sheet.getRow(i).getCell(17).getStringCellValue());
               economy.setProblemdescribe(sheet.getRow(i).getCell(18).getStringCellValue());

               if("STRING".equals(sheet.getRow(i).getCell(19).getCellType().toString())){
                   economy.setObjmoney(null);
               }else{
                   economy.setObjmoney(new BigDecimal(sheet.getRow(i).getCell(19).getNumericCellValue()));//new BigDecimal(sheet.getRow(i).getCell(19).getStringCellValue())
               }

               economy.setIsweb(sheet.getRow(i).getCell(20).getStringCellValue());




               if("STRING".equals(sheet.getRow(i).getCell(13).getCellType().toString())){
                   economy.setAcceptdate(new SimpleDateFormat("yyyy-MM-dd").parse(sheet.getRow(i).getCell(13).getStringCellValue()));
                   economy.setRecorddate(new SimpleDateFormat("yyyy-MM-dd").parse(sheet.getRow(i).getCell(13).getStringCellValue()));
               }else{
                   economy.setAcceptdate(sheet.getRow(i).getCell(13).getDateCellValue());
                   economy.setRecorddate(sheet.getRow(i).getCell(13).getDateCellValue());

               }


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
               tasklist.setSerialnum(sheet.getRow(i).getCell(0).getStringCellValue());//登记编号

               if("STRING".equals(sheet.getRow(i).getCell(2).getCellType().toString())){
                   tasklist.setPhone(sheet.getRow(i).getCell(2).getStringCellValue());
               }else{
                   tasklist.setPhone(new DecimalFormat().format(sheet.getRow(i).getCell(2).getNumericCellValue()).replace(",",""));
               }


               if("STRING".equals(sheet.getRow(i).getCell(6).getCellType().toString())){
                   tasklist.setTserialnum(sheet.getRow(i).getCell(6).getStringCellValue());
               }else{
                   tasklist.setTserialnum(new DecimalFormat().format(sheet.getRow(i).getCell(6).getNumericCellValue()));
               }



               if("NUMERIC".equals(sheet.getRow(i).getCell(13).getCellType().toString())){
                   tasklist.setCreatedate(sheet.getRow(i).getCell(13).getDateCellValue());
                   tasklist.setAssigndate(sheet.getRow(i).getCell(13).getDateCellValue());

               }else {
                   DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                   tasklist.setCreatedate(format.parse(sheet.getRow(i).getCell(13).getStringCellValue()));
                   tasklist.setAssigndate(format.parse(sheet.getRow(i).getCell(13).getStringCellValue()));

               }

               tasklist.setHandledeptname(sheet.getRow(i).getCell(14).getStringCellValue());
               tasklist.setHandledeptid(sheet.getRow(i).getCell(15).getStringCellValue());
               tasklist.setContent(sheet.getRow(i).getCell(16).getStringCellValue());
               tasklist.setResults(sheet.getRow(i).getCell(16).getStringCellValue());
               tasklist.setRqsttitle(sheet.getRow(i).getCell(17).getStringCellValue());
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



//               tasklist.setBustype(sheet.getRow(i).getCell(0).getStringCellValue());

               if("咨询".equals(sheet.getRow(i).getCell(4).getStringCellValue()) || "通用".equals(sheet.getRow(i).getCell(4).getStringCellValue())){
                   tasklist.setFormtype("01");
                   tasklist.setBustype("咨询类");
               }else if("消费投诉".equals(sheet.getRow(i).getCell(4).getStringCellValue())){
                   tasklist.setFormtype("02");
                   tasklist.setBustype("投诉类");
               }else if("经济违法举报".equals(sheet.getRow(i).getCell(4).getStringCellValue())){
                   tasklist.setFormtype("03");
                   tasklist.setBustype("举报类");
               }

//               tasklist.setFormtype("03");
               tasklist.setSystemsource("02");

               if("办结".equals(sheet.getRow(i).getCell(7).getStringCellValue())){
                   tasklist.setIsaccept("1");
                   tasklist.setState("0");
                   tasklist.setIscbdf("1");
                   tasklist.setNextnodeid("end");
                   tasklist.setOperate("结束");
               }else{
                   tasklist.setIsaccept("0");
                   tasklist.setState("0");
                   tasklist.setIscbdf("1");
                   tasklist.setNextnodeid("noAccept");
                   tasklist.setOperate("不受理");
               }

               //插入处理部门id
               if(sheet.getRow(i).getCell(14).getStringCellValue().contains("乐从")){
                   tasklist.setHandleregion("440681006");
               }else if(sheet.getRow(i).getCell(14).getStringCellValue().contains("陈村")){
                   tasklist.setHandleregion("440681005");
               }else if(sheet.getRow(i).getCell(14).getStringCellValue().contains("勒流")){
                   tasklist.setHandleregion("440681007");
               }else if(sheet.getRow(i).getCell(14).getStringCellValue().contains("大良")){
                   tasklist.setHandleregion("440681001");
               }else if(sheet.getRow(i).getCell(14).getStringCellValue().contains("杏坛")){
                   tasklist.setHandleregion("440681009");
               }else if(sheet.getRow(i).getCell(14).getStringCellValue().contains("均安")){
                   tasklist.setHandleregion("440681010");
               }else if(sheet.getRow(i).getCell(14).getStringCellValue().contains("容桂")){
                   tasklist.setHandleregion("440681002");
               }else if(sheet.getRow(i).getCell(14).getStringCellValue().contains("龙江")){
                   tasklist.setHandleregion("440681008");
               }else if(sheet.getRow(i).getCell(14).getStringCellValue().contains("伦教")){
                   tasklist.setHandleregion("440681003");
               }else if(sheet.getRow(i).getCell(14).getStringCellValue().contains("北滘")){
                   tasklist.setHandleregion("440681004");
               }else{
                   tasklist.setHandleregion("440681");
               }



               Processinfo processinfo = new Processinfo();
               UUID idPro = UUID.randomUUID();
               String idProStr=idPro.toString();//processinfo
               processinfo.setId(idProStr);
               processinfo.setCaseguid(caseguid);
               processinfo.setIsmain("1");
               processinfo.setStatus("1");
               processinfo.setHandleruserid("991804e6-2595-474d-b345-e93aab3729c2");
               processinfo.setHandledept(sheet.getRow(i).getCell(14).getStringCellValue());
               processinfo.setHandledeptid("da00ef72-5b56-4f5b-88c2-4b038a7f41a0");

               if("NUMERIC".equals(sheet.getRow(i).getCell(13).getCellType().toString())){
                   processinfo.setStartdate(sheet.getRow(i).getCell(13).getDateCellValue());
               }else if("STRING".equals(sheet.getRow(i).getCell(13).getCellType().toString())){
                   DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                   processinfo.setStartdate(format.parse(sheet.getRow(i).getCell(13).getStringCellValue()));
               }
               if("办结".equals(sheet.getRow(i).getCell(7).getStringCellValue())){
                   processinfo.setNodeid("end");
                   processinfo.setNodename("结束");
               }else{
                   processinfo.setNodeid("noAccept");
                   processinfo.setNodename("退回");
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

//               if("STRING".equals(sheet.getRow(i).getCell(1).getCellType().toString())){
                   log.setSernumber(sheet.getRow(i).getCell(0).getStringCellValue());
//               }else{
//                   log.setSernumber(new DecimalFormat().format(sheet.getRow(i).getCell(1).getNumericCellValue()));
//               }


               log.setSystem("12345");
               log.setType(sheet.getRow(i).getCell(4).getStringCellValue());
               log.setUpdate(new Date());

               Opinion opin = new Opinion();
               opin.setId(UUID.randomUUID().toString());
               opin.setCaseguid(caseguid);
               if("办结".equals(sheet.getRow(i).getCell(7).getStringCellValue())){
                   opin.setNodename("结束");
                   opin.setOperate("结束");
               }else{
                   opin.setNodename("退回");
                   opin.setOperate("退回");
               }


               if(sheet.getRow(i).getCell(16).getStringCellValue().length()>=2000){
                   System.out.println(sheet.getRow(i).getCell(16).getStringCellValue().length());
                   opin.setHandleopinion(sheet.getRow(i).getCell(16).getStringCellValue().substring(0,1999));

               }else{
                   opin.setHandleopinion(sheet.getRow(i).getCell(16).getStringCellValue());

               }
               opin.setHandler("佛山市市场监督管理局");
               opin.setHandledept(sheet.getRow(i).getCell(14).getStringCellValue());
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
                   }else if(con != null){
                       commomformMapper.insert(con);
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
