package com.chinaweal.sdcs.crcs.business.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.word.WordExportUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.business.entity.*;
import com.chinaweal.sdcs.crcs.business.mapper.TasklistMapper;
import com.chinaweal.sdcs.crcs.business.service.IExcelService;
import com.chinaweal.sdcs.crcs.business.service.IHandledemandService;
import com.chinaweal.sdcs.crcs.business.service.IProcessinfoService;
import com.chinaweal.sdcs.crcs.business.service.ITasklistService;
import com.chinaweal.sdcs.crcs.crawler.bmfw.entity.Inrequest;
import com.chinaweal.sdcs.crcs.crawler.bmfw.service.IInrequestService;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author ：Lin Yu
 * @version : 1.0
 * @since ： 2019/9/11
 */
@Service
class ExportComprehensiveImpl extends ServiceImpl<TasklistMapper, Tasklist> implements IExcelService {

    @Qualifier("handledemandServiceImpl")
    @Autowired
    private IHandledemandService iHandledemandService;
    @Qualifier("tasklistServiceImpl")
    @Autowired
    private ITasklistService iTasklistService;
    @Qualifier("processinfoServiceImpl")
    @Autowired
    private IProcessinfoService iprocessinfoService;
    @Qualifier("inrequestServiceImpl")
    @Autowired
    private IInrequestService iInrequestService;
    /*
     *    导出为Word文档
     * */
    @Override
    public void wordExportFile(Map<String, Object> stringObjectMap, HttpServletResponse response, String formType, String systemsource) throws IOException {
        //查询表单号和分派时间assigndate
        QueryWrapper<Tasklist> serialnumWrapper = new QueryWrapper<Tasklist>();
        serialnumWrapper.lambda().eq(Tasklist::getCaseguid, stringObjectMap.get("caseguid"));
        Tasklist tl = iTasklistService.getOne(serialnumWrapper, false);
        Map<String, Object> tasklistMap = BeanUtils.beanToMap(tl);
        String serialnum = (String) tasklistMap.get("serialnum");
        Map<String,Object> map=new HashMap<>();
        map.putAll(stringObjectMap);
        map.put("serialnum",serialnum);
            //查询初步意见应反馈时限、应受理时限、应立案时限
        if ("02".equals(systemsource)) {
            QueryWrapper<Inrequest> inrequestQueryWrapper = new QueryWrapper<Inrequest>();
            inrequestQueryWrapper.lambda().eq(Inrequest::getCaseguid, stringObjectMap.get("caseguid"));
            Inrequest inrequest = iInrequestService.getOne(inrequestQueryWrapper, false);
            Map<String, Object> inrequestMap = BeanUtils.beanToMap(inrequest);

            map.put("accepttime", inrequestMap.get("accepttime"));
            map.put("caseputtime", inrequestMap.get("caseputtime"));
            map.put("resulttime", inrequestMap.get("resulttime"));

        if (inrequestMap.get("accepttime")!=null && inrequestMap.get("accepttime")!=""){//应受理日期
            Date date= (Date) inrequestMap.get("accepttime");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String accepttime = sdf.format(date);
            map.put("accepttime",accepttime);
        }else{
            map.put("accepttime"," ");
        }
        if (inrequestMap.get("caseputtime")!=null && inrequestMap.get("caseputtime")!=""){//应立案日期
            Date date= (Date) inrequestMap.get("caseputtime");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String caseputtime = sdf.format(date);
            map.put("caseputtime",caseputtime);
        }else{
            map.put("caseputtime"," ");
        }
        if (inrequestMap.get("resulttime")!=null && inrequestMap.get("resulettime")!=""){//应反馈日期
            Date date= (Date) inrequestMap.get("resulttime");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String resulttime = sdf.format(date);
            map.put("resulttime",resulttime);
        }else{
            map.put("resulttime"," ");
        }
        }
        //12345导出assighdate
        if (tasklistMap.get("assigndate")!=null && tasklistMap.get("assigndate")!=""){//区局接收案件日期
            System.out.println(tasklistMap.get("assigndate"));
            Date date= (Date) tasklistMap.get("assigndate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String assigndate = sdf.format(date);
            map.put("assigndate",assigndate);
        }else{
            map.put("assigndate"," ");
        }

        if (map.get("disputedate")!=null && map.get("disputedate")!=" "){//争议时间
            Date date=(Date)map.get("disputedate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timeFormat = sdf.format(date);
            map.put("disputedateStr",timeFormat);
        }else{
            map.put("disputedate"," ");
        }
        if (map.get("recorddate")!=null && map.get("recorddate")!=" "){//登记时间
            Date date=(Date)map.get("recorddate");
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timeFormat = sdf.format(date);
//            map.put("recorddateStr",timeFormat);
            map.put("recorddate",timeFormat);
        }else{
            map.put("recorddate"," ");
        }
        if (map.get("expirationdate")!=null && map.get("expirationdate")!=" "){//登记时间
            Date date=(Date)map.get("expirationdate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss");
            String timeFormat = sdf.format(date);
            map.put("expirationdate",timeFormat);
        }else{
            map.put("expirationdate"," ");
        }
        if (map.get("updatedate")!=null && map.get("updatedate")!=" "){
            Date date=(Date)map.get("updatedate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timeFormat = sdf.format(date);
            map.put("updatedate",timeFormat);
        }else{
            map.put("updatedate"," ");
        }

//        if("02".equals(tl.getSystemsource())){
//            //接收时间acceptdate
//            if (map.get("acceptdate")!=null && map.get("acceptdate")!=" "){
//                Date date=(Date)map.get("acceptdate");
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                String timeFormat = sdf.format(date);
//                map.put("acceptdate",timeFormat);
//            }else{
//                map.put("acceptdate"," ");
//            }
//        }



        if (map.get("feedbackdate")!=null && map.get("feedbackdate")!=" "){
            Date date=(Date)map.get("feedbackdate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timeFormat = sdf.format(date);
            map.put("feedbackdate",timeFormat);
        }else{
            map.put("feedbackdate"," ");
        }

        //对身份证的证件号码进行加密处理
        if("身份证".equals(map.get("certificate"))){
            String idnumber = (String) map.get("idnumber");
            int begin = 6;//保留身份证前几位
            int end = 6;//保留身份证后几位
            String starStr = "";
            //剩余位置的身份证号码进行加星处理
            for (int i = 0; i < (idnumber.length() - begin - end); i++) {
                starStr = starStr + "*";
            }
            idnumber = idnumber.substring(0, begin) + starStr
                    + idnumber.substring(idnumber.length() - end, idnumber.length());
            map.put("idnumber",idnumber);
        }

        if (map.get("feedbacktime")!=null && map.get("feedbacktime")!=" "){//应立案日期
            Date date=(Date)map.get("feedbacktime");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String feedbacktime = sdf.format(date);
            map.put("feedbacktime",feedbacktime);
        }else{
            map.put("feedbacktime"," ");
        }

        if (map.get("todealwithtime")!=null && map.get("todealwithtime")!=" "){//最终反馈期限
            Date date=(Date)map.get("todealwithtime");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss");
            String todealwithtime = sdf.format(date);
            map.put("todealwithtime",todealwithtime);
        }else{
            map.put("todealwithtime"," ");
        }

        //处办要求
        Handledemand handledemand = null;
        QueryWrapper<Handledemand> handledemandWrapper = new QueryWrapper<Handledemand>();
        handledemandWrapper.lambda().eq(Handledemand::getCaseguid, map.get("caseguid"));
        List<Handledemand> handledemands = iHandledemandService.list(handledemandWrapper);
        if (handledemands != null && handledemands.size() > 0) {
            if (handledemands !=null){
                handledemand = handledemands.get(0);
                //初步意见应反馈时限: handledemand.opiniontimesurplus
                if(handledemand.getOpiniontimesurplus() != null){
                    Date date = handledemand.getOpiniontimesurplus();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss");
                    String opiniontimesurplus = sdf.format(date);
                    map.put("opiniontimesurplus",opiniontimesurplus);
                }else{
                    map.put("opiniontimesurplus"," ");
                }
                //应受理时限: handledemand.accepttimesurplus
                if(handledemand.getAccepttimesurplus() != null){
                    Date date = handledemand.getAccepttimesurplus();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss");
                    String accepttimesurplus = sdf.format(date);
                    map.put("accepttimesurplus",accepttimesurplus);
                }else{
                    map.put("accepttimesurplus"," ");
                }
                //应立案时限: handledemand.caseputtimeshould
                if(handledemand.getCaseputtimeshould() != null){
                    Date date = handledemand.getCaseputtimeshould();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss");
                    String caseputtimeshould = sdf.format(date);
                    map.put("caseputtimeshould",caseputtimeshould);
                }else{
                    map.put("caseputtimeshould"," ");
                }

            }else{
                map.put("handledemands","");
            }
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println("key:"+entry.getKey() +",value:"+ entry.getValue());
            if("".equals(entry.getValue()) || entry.getValue() == null){
                entry.setValue(" ");
            }
        }
//        System.out.println(handledemand);
        /*List<Economyform> list = new ArrayList<>();
        list.add(economyform);
        Map<String,Object> map = new HashMap<>();*/

        //获取经济违法实体类
//        Economyform economyform = (Economyform) RequestUtil.mapToEntity(Economyform.class, requestMap.get("comeconomicmodule"));
        //将实体类转换为Map
//        Map<String, Object> stringObjectMap = BeanUtils.beanToMap(economyform);
//        String num = (String)map.get("tserialnum").toString().substring((Integer) map.get("tserialnum").toString().length()-9,(Integer) map.get("tserialnum").toString().length()-1);
        String num = (String)map.get("tserialnum").toString();
        try {

            /*12345与12315与受理登记-->经济违法表*/
            if ("comeconomicmodule".equals(formType)) {
                if ("01".equals(systemsource)) {
                    XWPFDocument doc = WordExportUtil.exportWord07("word/economyform.docx",map);
                    response.setContentType("multipart/form-data");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=" + URLEncoder.encode("受理登记经济违法业务详情信息"+num, "UTF-8") + ".docx");
                    response.setCharacterEncoding("UTF-8");
                    doc.write(response.getOutputStream());
                }else if ("02".equals(systemsource)){
                    XWPFDocument doc = WordExportUtil.exportWord07("word/12345economyword.docx",map);
                    response.setContentType("multipart/form-data");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=" + URLEncoder.encode("12345经济违法表单详情信息_"+serialnum, "UTF-8") + ".docx");
                    response.setCharacterEncoding("UTF-8");
                    doc.write(response.getOutputStream());
                }else if ("03".equals(systemsource)){
                    XWPFDocument doc = WordExportUtil.exportWord07("word/12315economyword.docx",map);
                    response.setContentType("multipart/form-data");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=" + URLEncoder.encode("12315举报表单详情信息"+num, "UTF-8") + ".docx");
                    response.setCharacterEncoding("UTF-8");
                    doc.write(response.getOutputStream());
                }
            }
            /*12345与12315与受理登记-->消费投诉表*/
            if("complaintmodule".equals(formType)){
                if("01".equals(systemsource)){
                    XWPFDocument doc = WordExportUtil.exportWord07("word/complaintform.docx",map);
                    response.setContentType("multipart/form-data");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=" + URLEncoder.encode("受理登记消费投诉业务详情信息"+num, "UTF-8") + ".docx");
                    response.setCharacterEncoding("UTF-8");
                    doc.write(response.getOutputStream());
                }else if("02".equals(systemsource)){
                    XWPFDocument doc = WordExportUtil.exportWord07("word/12345complaintword.docx",map);
                    response.setContentType("multipart/form-data");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=" + URLEncoder.encode("12345消费投诉业务详情信息_"+serialnum, "UTF-8") + ".docx");
                    response.setCharacterEncoding("UTF-8");
                    doc.write(response.getOutputStream());
                }else if("03".equals(systemsource)) {
                    XWPFDocument doc = WordExportUtil.exportWord07("word/12315complaintword.docx",map);
                    response.setContentType("multipart/form-data");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=" + URLEncoder.encode("12315投诉业务详情信息"+num, "UTF-8") + ".docx");
                    response.setCharacterEncoding("UTF-8");
                    doc.write(response.getOutputStream());
                }
            }
            /*通用咨询*/
            if ("commommodule".equals(formType)){
                if("01".equals(systemsource)){
                    XWPFDocument doc = WordExportUtil.exportWord07("word/consultationform.docx",map);
                    response.setContentType("multipart/form-data");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=" + URLEncoder.encode("咨询登记业务详情信息"+num, "UTF-8") + ".docx");
                    response.setCharacterEncoding("UTF-8");
                    doc.write(response.getOutputStream());
                }if ("02".equals(systemsource)){
                    XWPFDocument doc = WordExportUtil.exportWord07("word/commomword.docx",map);
                    response.setContentType("multipart/form-data");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=" + URLEncoder.encode("12345咨询登记业务详情信息_"+serialnum, "UTF-8") + ".docx");
                    response.setCharacterEncoding("UTF-8");
                    doc.write(response.getOutputStream());
                }
            }

//            XWPFDocument doc = WordExportUtil.exportWord07("word/economyform.docx",map);
//            FileOutputStream fos = new FileOutputStream("D:/wrapExcel111.docx");
//            doc.write(fos);
//            fos.close();
//            response.setContentType("multipart/form-data");
//            // 下载文件的默认名称
//            response.setHeader("Content-Disposition",
//                    "attachment;filename=" + URLEncoder.encode("业务详情信息", "UTF-8") + ".docx");
//            //编码
//            response.setCharacterEncoding("UTF-8");
//            doc.write(response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void ExportBusinessSearchVOFile(List<BusinessSearchVO> list, HttpServletResponse response) throws IOException {
        Workbook workbook;
        ExportParams params = new ExportParams();
        workbook = ExcelExportUtil.exportExcel(params, BusinessSearchVO.class, list);
        response.setContentType("multipart/form-data");//application/octet-stream
        // 下载文件的默认名称
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode("即将到期工单", "UTF-8") + ".xls");
        //编码
        response.setCharacterEncoding("UTF-8");
        //写入response流，返回
        workbook.write(response.getOutputStream());
    }

    @Override
    public void ExportBusinessSearch12315VOFile(List<Business12315VO> list, HttpServletResponse response) throws IOException {
        Workbook workbook;
        ExportParams params = new ExportParams();
        workbook = ExcelExportUtil.exportExcel(params, Business12315VO.class, list);
        response.setContentType("multipart/form-data");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode("12315系统待办业务", "UTF-8") + ".xls");
        //编码
        response.setCharacterEncoding("UTF-8");
        //写入response流，返回
        workbook.write(response.getOutputStream());
    }

    @Override
    public void ExportBusinessSearchlocalVOFile(List<BusinesslocalVO> list, HttpServletResponse response) throws IOException {
        Workbook workbook;
        ExportParams params = new ExportParams();
        workbook = ExcelExportUtil.exportExcel(params, BusinesslocalVO.class, list);
        response.setContentType("multipart/form-data");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode("受理登记待办业务", "UTF-8") + ".xls");
        //编码
        response.setCharacterEncoding("UTF-8");
        //写入response流，返回
        workbook.write(response.getOutputStream());
    }

    @Override
    public void ExportBusinessSearch12345VOFile(List<Business12345VO> list, HttpServletResponse response) throws IOException {
        Workbook workbook;
        ExportParams params = new ExportParams();
        workbook = ExcelExportUtil.exportExcel(params, Business12345VO.class, list);
        response.setContentType("multipart/form-data");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode("12345系统待办业务", "UTF-8") + ".xls");
        //编码
        response.setCharacterEncoding("UTF-8");
        //写入response流，返回
        workbook.write(response.getOutputStream());
    }

}
