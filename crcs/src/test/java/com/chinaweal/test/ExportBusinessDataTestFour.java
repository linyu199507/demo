package com.chinaweal.test;

import com.chinaweal.entity.testLiny;
import com.chinaweal.mapper.testLinyMapper;
import com.chinaweal.sdcs.crcs.business.entity.*;
import com.chinaweal.sdcs.crcs.business.mapper.*;
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
import java.util.Date;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-context.xml")
@WebAppConfiguration
public class ExportBusinessDataTestFour {


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



   @Test
    public void test9() throws IOException, ParseException {

       for( int num = 1 ; num < 2 ; num++){

        String index = String.valueOf(num);

//           InputStream inputS=new FileInputStream("C:\\Users\\lin99\\Desktop\\12315系统历史数据\\12315系统历史数据（举报类）Excel\\业务信息 ("+index+").xls");
           InputStream inputS=new FileInputStream("C:\\Users\\lin99\\Desktop\\手工录入渠道汇总 - 信访.xls");
           HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputS);
           HSSFSheet sheet = hssfWorkbook.getSheet("经济违法举报");
//       List
//           for(int i=1;i<sheet.getLastRowNum();i++){
           for(int i=1;i<831;i++){
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

               String data = null;
               if(sheet.getRow(i).getCell(4) != null){
                   data = "来信人数: " + sheet.getRow(i).getCell(4).getStringCellValue();
               }
               if(sheet.getRow(i).getCell(5) != null){
                   data = data + " , 涉访人数: " +sheet.getRow(i).getCell(5).getStringCellValue();
               }
               if(sheet.getRow(i).getCell(10) != null){
                   data = data + " , 办理方式: " +sheet.getRow(i).getCell(10).getStringCellValue();
               }
               if(sheet.getRow(i).getCell(11) != null){
                   data = data + " , 问题性质: " +sheet.getRow(i).getCell(11).getStringCellValue();
               }
               if(sheet.getRow(i).getCell(13) != null){
                   data = data + " , 经办人: " +sheet.getRow(i).getCell(13).getStringCellValue();
               }
               if(sheet.getRow(i).getCell(25) != null){
                   data = data + " , 建议办理途径: " +sheet.getRow(i).getCell(25).getStringCellValue();
               }
               if(sheet.getRow(i).getCell(9) != null){
                   data = data + " , 处理单位: " +sheet.getRow(i).getCell(9).getStringCellValue();
               }

               Consultationform con = new Consultationform();
               con = null;
               Complaintform complaint = new Complaintform();
               complaint = null;
               Economyform economy = new Economyform();
               economy = null;
               UUID idCaseguid = UUID.randomUUID();
               String caseguid=idCaseguid.toString();//业务caseguid

               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

           if("咨询".equals(sheet.getRow(i).getCell(0).getStringCellValue())){
               con = new Consultationform();
               UUID Conid = UUID.randomUUID();
               String conid=Conid.toString();
               con.setId(conid);
               con.setCaseguid(caseguid);
               con.setRqsttitle(sheet.getRow(i).getCell(1).getStringCellValue()+sheet.getRow(i).getCell(3).getStringCellValue());
               con.setAppealsource(sheet.getRow(i).getCell(2).getStringCellValue());
               con.setTransfernumber(sheet.getRow(i).getCell(3).getStringCellValue());
               con.setIncomingcommpany(sheet.getRow(i).getCell(4).getStringCellValue());
               con.setName(sheet.getRow(i).getCell(6).getStringCellValue());
               System.out.println(sheet.getRow(i).getCell(7));
               if(sheet.getRow(i).getCell(7).equals("")){
                   con.setRqstnumber(new BigDecimal(sheet.getRow(i).getCell(7).getStringCellValue()).toString());

               }
               con.setGender(sheet.getRow(i).getCell(8).getStringCellValue());
               con.setIsprofessional(sheet.getRow(i).getCell(9).getStringCellValue());
               con.setConsultantaddress(sheet.getRow(i).getCell(10).getStringCellValue());
               con.setEntname(sheet.getRow(i).getCell(11).getStringCellValue());
               con.setLinknumber(sheet.getRow(i).getCell(12).getStringCellValue());
               con.setAddress(sheet.getRow(i).getCell(13).getStringCellValue());
               con.setComplaintstype(sheet.getRow(i).getCell(14).getStringCellValue());
               System.out.println(sheet.getRow(i).getCell(19).getCellType());


               if("NUMERIC".equals(sheet.getRow(i).getCell(19).getCellType())){
                   con.setAcceptdate(sheet.getRow(i).getCell(19).getDateCellValue());
               }else if("STRING".equals(sheet.getRow(i).getCell(19).getCellType())){
                   DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                   con.setAcceptdate(format.parse(sheet.getRow(i).getCell(19).getStringCellValue()));
               }else{

               }

               con.setKeyword(sheet.getRow(i).getCell(23).getStringCellValue());
               con.setRqstcontent(sheet.getRow(i).getCell(24).getStringCellValue());
           }else if("投诉".equals(sheet.getRow(i).getCell(26).getStringCellValue())){
               complaint = new Complaintform();
               complaint.setId(UUID.randomUUID().toString());
               complaint.setCaseguid(caseguid);

               complaint.setTransfernumber(sheet.getRow(i).getCell(0).getStringCellValue());
               if(sheet.getRow(i).getCell(2).getStringCellValue().length()>=199){
                   complaint.setRqsttitle(sheet.getRow(i).getCell(2).getStringCellValue().substring(1,199));
               }else {
                   complaint.setRqsttitle(sheet.getRow(i).getCell(2).getStringCellValue());
               }
               complaint.setAppealsource("信访系统");
               if (sheet.getRow(i).getCell(3) != null){
                   complaint.setRqstperson(sheet.getRow(i).getCell(3).getStringCellValue());
               }

               if("STRING".equals(sheet.getRow(i).getCell(6).getCellType().toString())){
                   complaint.setAcceptdate(new SimpleDateFormat("yyyy-MM-dd").parse(sheet.getRow(i).getCell(6).getStringCellValue()));
                   complaint.setRecorddate(new SimpleDateFormat("yyyy-MM-dd").parse(sheet.getRow(i).getCell(6).getStringCellValue()));
               }else{
                   complaint.setAcceptdate(sheet.getRow(i).getCell(6).getDateCellValue());
                   complaint.setRecorddate(sheet.getRow(i).getCell(6).getDateCellValue());
               }
               if(sheet.getRow(i).getCell(8) != null ){
                   if("STRING".equals(sheet.getRow(i).getCell(8).getCellType().toString())){
                       complaint.setAcceptdate(new SimpleDateFormat("yyyy-MM-dd").parse(sheet.getRow(i).getCell(8).getStringCellValue()));
                       complaint.setRecorddate(new SimpleDateFormat("yyyy-MM-dd").parse(sheet.getRow(i).getCell(8).getStringCellValue()));
                   }else{
                       complaint.setAcceptdate(sheet.getRow(i).getCell(8).getDateCellValue());
                       complaint.setRecorddate(sheet.getRow(i).getCell(8).getDateCellValue());
                   }
               }


               if (sheet.getRow(i).getCell(9) != null){
                   if(sheet.getRow(i).getCell(9).getStringCellValue().length()>50){
                       complaint.setUndertake(sheet.getRow(i).getCell(9).getStringCellValue().substring(0,49));
                   }else {
                       complaint.setUndertake(sheet.getRow(i).getCell(9).getStringCellValue());
                   }
               }
               if (sheet.getRow(i).getCell(14) != null){
                   complaint.setDetailaddress(sheet.getRow(i).getCell(14).getStringCellValue());
               }
               if (sheet.getRow(i).getCell(15) != null){
                   complaint.setComplaintstype(sheet.getRow(i).getCell(15).getStringCellValue());
               }
               if (sheet.getRow(i).getCell(19) != null){
                   complaint.setSubjectaddress(sheet.getRow(i).getCell(19).getStringCellValue());
               }
               if (sheet.getRow(i).getCell(20) != null){
                   complaint.setNationality(sheet.getRow(i).getCell(20).getStringCellValue());
               }
               if (sheet.getRow(i).getCell(21) != null){
                   complaint.setRqstaddress(sheet.getRow(i).getCell(21).getStringCellValue());
               }
               if (sheet.getRow(i).getCell(22) != null){
                   complaint.setIdnumber(sheet.getRow(i).getCell(22).getStringCellValue());
               }
               if (sheet.getRow(i).getCell(23) != null){
                   complaint.setRqstnumber(sheet.getRow(i).getCell(23).getStringCellValue());
               }
               if (sheet.getRow(i).getCell(24) != null){
                   complaint.setProblemdescribe(sheet.getRow(i).getCell(24).getStringCellValue());
               }
               complaint.setUpdatedate(new Date());






//               complaint.setRqsttitle(sheet.getRow(i).getCell(1).getStringCellValue());
//               complaint.setAppealsource(sheet.getRow(i).getCell(2).getStringCellValue());
//               complaint.setTransfernumber(sheet.getRow(i).getCell(3).getStringCellValue());
//               complaint.setDetailaddress(sheet.getRow(i).getCell(4).getStringCellValue());
//               complaint.setSubordinateplate(sheet.getRow(i).getCell(5).getStringCellValue());
//               complaint.setRqstperson(sheet.getRow(i).getCell(6).getStringCellValue());
////               complaint.setRqstnumber(new BigDecimal(sheet.getRow(i).getCell(7).getNumericCellValue()).toString());
//               if("STRING".equals(sheet.getRow(i).getCell(7).getCellType().toString())){
//                   complaint.setRqstnumber(sheet.getRow(i).getCell(7).getStringCellValue());
//               }else{
//                   complaint.setRqstnumber(new DecimalFormat().format(sheet.getRow(i).getCell(7).getNumericCellValue()).replace(",",""));
//
//               }
//
//               complaint.setGender(sheet.getRow(i).getCell(8).getStringCellValue());
//               complaint.setIsprofessional(sheet.getRow(i).getCell(9).getStringCellValue());
//               complaint.setRqstaddress(sheet.getRow(i).getCell(10).getStringCellValue());
//               complaint.setSubjectname(sheet.getRow(i).getCell(11).getStringCellValue());
//               complaint.setLinknumber(sheet.getRow(i).getCell(12).getStringCellValue());
//               complaint.setSubjectaddress(sheet.getRow(i).getCell(13).getStringCellValue());
//               complaint.setComplaintstype(sheet.getRow(i).getCell(14).getStringCellValue());
//               complaint.setServicetype(sheet.getRow(i).getCell(15).getStringCellValue());
//               complaint.setObjname(sheet.getRow(i).getCell(16).getStringCellValue());
//               complaint.setBrand(sheet.getRow(i).getCell(17).getStringCellValue());
//               complaint.setIsweb(sheet.getRow(i).getCell(18).getStringCellValue());

//               System.out.println(sheet.getRow(i).getCell(19).getCellType());
//               if("STRING".equals(sheet.getRow(i).getCell(19).getCellType().toString())){
//                   complaint.setAcceptdate(new SimpleDateFormat("yyyy-MM-dd").parse(sheet.getRow(i).getCell(19).getStringCellValue()));
//                   complaint.setRecorddate(new SimpleDateFormat("yyyy-MM-dd").parse(sheet.getRow(i).getCell(19).getStringCellValue()));
//               }else{
//                   complaint.setAcceptdate(sheet.getRow(i).getCell(19).getDateCellValue());
//                   complaint.setRecorddate(sheet.getRow(i).getCell(19).getDateCellValue());
//
//               }
//
//               complaint.setAcceptancenumber(sheet.getRow(i).getCell(21).getStringCellValue());
//               complaint.setTransferbooknumber(sheet.getRow(i).getCell(22).getStringCellValue());
//               complaint.setKeyword(sheet.getRow(i).getCell(23).getStringCellValue());
//               complaint.setProblemdescribe(sheet.getRow(i).getCell(24).getStringCellValue());

           }else if("举报".equals(sheet.getRow(i).getCell(26).getStringCellValue())){
               System.out.println("正确--举报类");
               economy = new Economyform();
               economy.setId(UUID.randomUUID().toString());
               economy.setCaseguid(caseguid);

               economy.setTransfernumber(sheet.getRow(i).getCell(0).getStringCellValue());
               if(sheet.getRow(i).getCell(2).getStringCellValue().length()>=199){
                   economy.setRqsttitle(sheet.getRow(i).getCell(2).getStringCellValue().substring(1,199));
               }else {
                   economy.setRqsttitle(sheet.getRow(i).getCell(2).getStringCellValue());
               }
               economy.setAppealsource("信访系统");

               if (sheet.getRow(i).getCell(3) != null){
                   economy.setRqstperson(sheet.getRow(i).getCell(3).getStringCellValue());
               }

               if("STRING".equals(sheet.getRow(i).getCell(6).getCellType().toString())){
                   economy.setAcceptdate(new SimpleDateFormat("yyyy-MM-dd").parse(sheet.getRow(i).getCell(6).getStringCellValue()));
                   economy.setRecorddate(new SimpleDateFormat("yyyy-MM-dd").parse(sheet.getRow(i).getCell(6).getStringCellValue()));
               }else{
                   economy.setAcceptdate(sheet.getRow(i).getCell(6).getDateCellValue());
                   economy.setRecorddate(sheet.getRow(i).getCell(6).getDateCellValue());
               }

               if(sheet.getRow(i).getCell(8) != null ){
                   if("STRING".equals(sheet.getRow(i).getCell(8).getCellType().toString())){
                       economy.setAcceptdate(new SimpleDateFormat("yyyy-MM-dd").parse(sheet.getRow(i).getCell(8).getStringCellValue()));
                       economy.setRecorddate(new SimpleDateFormat("yyyy-MM-dd").parse(sheet.getRow(i).getCell(8).getStringCellValue()));
                   }else{
                       economy.setAcceptdate(sheet.getRow(i).getCell(8).getDateCellValue());
                       economy.setRecorddate(sheet.getRow(i).getCell(8).getDateCellValue());
                   }
               }


               if (sheet.getRow(i).getCell(9) != null){
                   if(sheet.getRow(i).getCell(9).getStringCellValue().length()>50){
                       economy.setUndertake(sheet.getRow(i).getCell(9).getStringCellValue().substring(0,49));
                   }else {
                       economy.setUndertake(sheet.getRow(i).getCell(9).getStringCellValue());
                   }
               }
               if (sheet.getRow(i).getCell(14) != null){
                   economy.setDetailaddress(sheet.getRow(i).getCell(14).getStringCellValue());
               }
               if (sheet.getRow(i).getCell(15) != null){
                   economy.setReportprovidelb(sheet.getRow(i).getCell(15).getStringCellValue());
               }
               if (sheet.getRow(i).getCell(19) != null){
                   economy.setSubjectaddress(sheet.getRow(i).getCell(19).getStringCellValue());
               }
               if (sheet.getRow(i).getCell(20) != null){
                   economy.setNationality(sheet.getRow(i).getCell(20).getStringCellValue());
               }
               if (sheet.getRow(i).getCell(21) != null){
                   economy.setRqstaddress(sheet.getRow(i).getCell(21).getStringCellValue());
               }
               if (sheet.getRow(i).getCell(22) != null){
                   economy.setIdnumber(sheet.getRow(i).getCell(22).getStringCellValue());
               }
               if (sheet.getRow(i).getCell(23) != null){
                   economy.setRqstnumber(sheet.getRow(i).getCell(23).getStringCellValue());
               }
               if (sheet.getRow(i).getCell(24) != null){
                   economy.setProblemdescribe(sheet.getRow(i).getCell(24).getStringCellValue());
               }
               economy.setUpdatedate(new Date());

           }



               Tasklist tasklist = new Tasklist();
               UUID idTask = UUID.randomUUID();
               String idTaskStr=idTask.toString();//tasklist表id
               tasklist.setId(idTaskStr);
               tasklist.setCaseguid(caseguid);

//               tasklist.setRqsttitle(sheet.getRow(i).getCell(2).getStringCellValue());

               if(sheet.getRow(i).getCell(2).getStringCellValue().length()>=99){
                   tasklist.setRqsttitle(sheet.getRow(i).getCell(2).getStringCellValue().substring(1,99));
               }else {
                   tasklist.setRqsttitle(sheet.getRow(i).getCell(2).getStringCellValue());
               }


               if(sheet.getRow(i).getCell(1) != null){
                   tasklist.setSerialnum(sheet.getRow(i).getCell(1).getStringCellValue());//登记编号
                   tasklist.setTserialnum(sheet.getRow(i).getCell(1).getStringCellValue());
               }




               if("NUMERIC".equals(sheet.getRow(i).getCell(6).getCellType().toString())){
                   tasklist.setCreatedate(sheet.getRow(i).getCell(6).getDateCellValue());
                   tasklist.setAssigndate(sheet.getRow(i).getCell(6).getDateCellValue());
               }else {
                   DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                   tasklist.setCreatedate(format.parse(sheet.getRow(i).getCell(6).getStringCellValue()));
                   tasklist.setAssigndate(format.parse(sheet.getRow(i).getCell(6).getStringCellValue()));
               }

               if(sheet.getRow(i).getCell(9) != null){
                   if(sheet.getRow(i).getCell(9).getStringCellValue().length()>50){
                       tasklist.setHandledeptname(sheet.getRow(i).getCell(9).getStringCellValue().substring(0,49));
                   }else{
                       tasklist.setHandledeptname(sheet.getRow(i).getCell(9).getStringCellValue());
                   }
               }

               if (sheet.getRow(i).getCell(12) != null) {
//                       tasklist.setIsregistercase("是");
                       tasklist.setNextnodeid("end");
//                       tasklist.setOperate(sheet.getRow(i).getCell(12).getStringCellValue());
                       tasklist.setOperate("归档");
               }

               if(!data.isEmpty()){
                   tasklist.setContent(data);
               }


               tasklist.setBustype(sheet.getRow(i).getCell(26).getStringCellValue());

               if("咨询".equals(sheet.getRow(i).getCell(26).getStringCellValue())){
                   tasklist.setFormtype("01");
               }else if("投诉".equals(sheet.getRow(i).getCell(26).getStringCellValue())){
                   tasklist.setFormtype("02");
               }else if("举报".equals(sheet.getRow(i).getCell(26).getStringCellValue())){
                   tasklist.setFormtype("03");
               }

//               tasklist.setFormtype("03");
               tasklist.setSystemsource("01");
               tasklist.setIsaccept("1");
               tasklist.setState("0");
               tasklist.setIscbdf("1");
               tasklist.setUpdatedate(new Date());



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

               if("NUMERIC".equals(sheet.getRow(i).getCell(6).getCellType())){
                   processinfo.setStartdate(sheet.getRow(i).getCell(6).getDateCellValue());
               }else if("STRING".equals(sheet.getRow(i).getCell(6).getCellType())){
                   DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                   processinfo.setStartdate(format.parse(sheet.getRow(i).getCell(6).getStringCellValue()));
               }


               processinfo.setUpdatedate(new Date());
               processinfo.setHandleorgid("6ba12fbe-0100-1000-e000-03fec0a85893");
               processinfo.setHandleorgname("佛山市顺德区市场监督管理局");

               testLiny log = new testLiny();
               log.setId(UUID.randomUUID().toString());
               log.setCaseguid(caseguid);
               log.setSernumber(sheet.getRow(i).getCell(1).getStringCellValue());
               log.setSystem("手工录入");

               if( sheet.getRow(i).getCell(3) != null ){
                   log.setType(sheet.getRow(i).getCell(3).getStringCellValue());

               }

               log.setUpdate(new Date());

               Opinion opin = new Opinion();
               opin.setId(UUID.randomUUID().toString());
               opin.setCaseguid(caseguid);
//               opin.setNodename(sheet.getRow(i).getCell(12).toString());
               opin.setNodename("归档");

               if(!data.isEmpty()){
                   opin.setHandleopinion(data);
               }

               opin.setHandler("顺德区市场监督管理局");
               opin.setHandledept("佛山市市场监督管理局");

               if (sheet.getRow(i).getCell(12) != null) {
//                       opin.setOperate(sheet.getRow(i).getCell(12).getStringCellValue());
                       opin.setOperate("归档");
               }

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
                       consultationformMapper.insert(con);
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
