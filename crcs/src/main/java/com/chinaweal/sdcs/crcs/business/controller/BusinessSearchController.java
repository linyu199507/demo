package com.chinaweal.sdcs.crcs.business.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.chinaweal.sdcs.crcs.business.entity.*;
import com.chinaweal.sdcs.crcs.business.service.*;
import com.chinaweal.sdcs.crcs.system.entity.Basecodeform;
import com.chinaweal.sdcs.crcs.system.service.IBasecodeformService;
import com.chinaweal.sdcs.crcs.util.BaseUtil;
import com.chinaweal.youfool.framework.rest.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author chents
 * @since 2019-08-19
 */
@Api(tags = "BusinessSearchController", description = "查询接口类")
@Controller
@RequestMapping("/businessSearch")
public class BusinessSearchController {


    @Qualifier("tasklistServiceImpl")
    @Autowired
    private ITasklistService iTasklistService;

    @Qualifier("customerinfoServiceImpl")
    @Autowired
    private ICustomerinfoService iCustomerinfoService;

    @Qualifier("commomformServiceImpl")
    @Autowired
    private ICommomformService iCommomformService;

    @Qualifier("economyformServiceImpl")
    @Autowired
    private IEconomyformService iEconomyformService;

    @Qualifier("complaintformServiceImpl")
    @Autowired
    private IComplaintformService iComplaintformService;

    @Qualifier("opinionServiceImpl")
    @Autowired
    private IOpinionService iOpinionService;

    @Qualifier("handledemandServiceImpl")
    @Autowired
    private IHandledemandService iHandledemandService;

    @Qualifier("consultationformServiceImpl")
    @Autowired
    private IConsultationformService iConsultationformService;

    @Qualifier("caseprocesformServiceImpl")
    @Autowired
    private ICaseprocesformService iCaseprocesformService;

    @Qualifier("exportComprehensiveImpl")
    @Autowired
    private IExcelService iexcelService;

    @Qualifier("materialinfoServiceImpl")
    @Autowired
    private IMaterialinfoService iMaterialinfoService;

    @Qualifier("businessSearchServiceImpl")
    @Autowired
    private IBusinessSearchService iBusinessSearchService;

    @Qualifier("business12315VOServiceImpl")
    @Autowired
    private IBusiness12315VOService iBusiness12315VOService;

    @Qualifier("businesslocalVOServiceImpl")
    @Autowired
    private IBusinesslocalVOService iBusinesslocalVOService;

    @Qualifier("business12345VOServiceImpl")
    @Autowired
    private IBusiness12345VOService iBusiness12345VOService;

    @Qualifier("basecodeformServiceImpl")
    @Autowired
    private IBasecodeformService iBasecodeformService;


    /**
     * 业务待办详细信息查询
     *
     * @return
     */
    @ApiOperation(
            tags = "BusinessSearchController",
            value = "业务待办详细信息查询")
    @PostMapping("searchBaseInfo")
    @ResponseBody
    public RestResult searchBaseInfo(@ApiParam(value = "查询相关参数", required = true)
                                     @RequestBody Map<String, String> requestMap) {
        Map searchMap = new HashMap();
        String id = requestMap.get("id");
        String caseguid = requestMap.get("caseguid");
        String systemsource = requestMap.get("systemsource"); //系统来源 01本系统 02 12345系统
        Customerinfo customerinfo = null;
        Commomform commomform = null;
        Economyform economyform = null;
        Complaintform complaintform = null;
        Consultationform consultationform = null;
        Handledemand handledemand = null;
        Caseprocesform caseprocesform = null;
        //待办列表
        Tasklist tasklist = iTasklistService.getById(id);
//        if ("01".equals(systemsource)) {
//            QueryWrapper<Consultationform> consultationformWrapper = new QueryWrapper<Consultationform>();
//            consultationformWrapper.lambda().eq(Consultationform::getCaseguid, caseguid);
//            List<Consultationform> consultationforms = iConsultationformService.list(consultationformWrapper);
//            if (consultationforms != null && consultationforms.size() > 0) {
//                consultationform = consultationforms.get(0);
//            }
//        } else {
            //话务信息
            QueryWrapper<Customerinfo> customerinfoWrapper = new QueryWrapper<Customerinfo>();
            customerinfoWrapper.lambda().eq(Customerinfo::getCaseguid, caseguid);
            List<Customerinfo> customerinfos = iCustomerinfoService.list(customerinfoWrapper);
            if (customerinfos != null && customerinfos.size() > 0) {
                customerinfo = customerinfos.get(0);
//            }

        }

        //通用表单
        QueryWrapper<Commomform> commomformWrapper = new QueryWrapper<Commomform>();
        commomformWrapper.lambda().eq(Commomform::getCaseguid, caseguid);
        List<Commomform> commomforms = iCommomformService.list(commomformWrapper);
        if (commomforms != null && commomforms.size() > 0) {
            commomform = commomforms.get(0);
        }

        //经济违法表单
        QueryWrapper<Economyform> economyformWrapper = new QueryWrapper<Economyform>();
        economyformWrapper.lambda().eq(Economyform::getCaseguid, caseguid);
        List<Economyform> economyforms = iEconomyformService.list(economyformWrapper);
        if (economyforms != null && economyforms.size() > 0) {
            economyform = economyforms.get(0);
        }
        //消费投诉表单
        QueryWrapper<Complaintform> complaintformWrapper = new QueryWrapper<Complaintform>();
        complaintformWrapper.lambda().eq(Complaintform::getCaseguid, caseguid);
        List<Complaintform> complaintforms = iComplaintformService.list(complaintformWrapper);
        if (complaintforms != null && complaintforms.size() > 0) {
            complaintform = complaintforms.get(0);
        }

        //处办要求
        QueryWrapper<Handledemand> handledemandWrapper = new QueryWrapper<Handledemand>();
        handledemandWrapper.lambda().eq(Handledemand::getCaseguid, caseguid);
        List<Handledemand> handledemands = iHandledemandService.list(handledemandWrapper);
        if (handledemands != null && handledemands.size() > 0) {
            handledemand = handledemands.get(0);
        }

        //处理意见
        QueryWrapper<Opinion> opinionWrapper = new QueryWrapper<Opinion>();
        opinionWrapper.lambda().eq(Opinion::getCaseguid, caseguid).orderByAsc(Opinion::getHandletime);
        List<Opinion> opinionList = iOpinionService.list(opinionWrapper);

        //经济违法立法信息
        QueryWrapper<Caseprocesform> caseprocesformWrapper = new QueryWrapper<Caseprocesform>();
        caseprocesformWrapper.lambda().eq(Caseprocesform::getCaseguid, caseguid);
        List<Caseprocesform> caseprocesforms = iCaseprocesformService.list(caseprocesformWrapper);
        if (caseprocesforms != null && caseprocesforms.size() > 0) {
            caseprocesform = caseprocesforms.get(0);
        }

        //附件
        QueryWrapper<Materialinfo> queryWrapper = new QueryWrapper<Materialinfo>();
        queryWrapper.lambda().eq(Materialinfo::getCaseguid, caseguid);
        List<Materialinfo> materialinfoList = iMaterialinfoService.list(queryWrapper);

        searchMap.put("tasklist", tasklist);
        searchMap.put("customerinfo", customerinfo);
        searchMap.put("commomform", commomform);
        searchMap.put("economyform", economyform);
        searchMap.put("complaintform", complaintform);
        searchMap.put("handledemand", handledemand);
        searchMap.put("consultationform", consultationform);
        searchMap.put("opinionList", opinionList);
        searchMap.put("caseprocesform", caseprocesform);
        searchMap.put("materialinfoList", materialinfoList);
        return RestResult.ok(searchMap);
    }

    /**
     * 综合业务查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "BusinessSearchController",
            value = "综合业务查询")
    @PostMapping("searchBusinessInfo")
    @ResponseBody
    public RestResult searchBusinessInfo(@ApiParam(value = "查询相关参数", required = true)
                                         @RequestBody Map<String, Object> requestMap) {
        int size = requestMap.get("size") != null ? ((Integer) requestMap.get("size")).intValue() : 10;
        int current = requestMap.get("page") != null ? ((Integer) requestMap.get("page")).intValue() : 1;
        if (requestMap.get("objectcategory") != null && !"".equals(requestMap.get("objectcategory"))) {
            BaseUtil baseUtil = new BaseUtil();
            List<Basecodeform> basecodeformsObject = iBasecodeformService.listBaseCodeByTypeAndLevel("object12345", "1");
            List<String> codeidsObject = baseUtil.findBasecodeFormCodeids((String) requestMap.get("objectcategory"), basecodeformsObject);
            requestMap.put("codeidsObject", codeidsObject);
        }
        if (requestMap.get("problemtype") != null && !"".equals(requestMap.get("problemtype"))) {
            BaseUtil baseUtil = new BaseUtil();
            List<Basecodeform> basecodeformsQuestion = iBasecodeformService.listBaseCodeByTypeAndLevel("question12345", "1");
            List<String> codeidsQuestion = baseUtil.findBasecodeFormCodeids((String) requestMap.get("problemtype"), basecodeformsQuestion);
            requestMap.put("codeidsQuestion", codeidsQuestion);
        }
        IPage<BusinessSearchVO> businessSearchVOIPage = iBusinessSearchService.searchBusinessInfo(current, size, requestMap);
        return RestResult.ok(businessSearchVOIPage);
    }

    /**
     * 待办业务查询结果导出
     *
     * @return
     */
    @ApiOperation(
            tags = "BusinessSearchController",
            value = "业务待办信息查询结果导出")
    @GetMapping("excelexport")
    public void excelexport(
            HttpServletResponse response, @RequestParam("caseguid") String[] caseguids, @RequestParam("system") String system,
            @RequestParam(value = "ename", required = false) String ename,
            @RequestParam(value = "rqsttitle", required = false) String rqsttitle,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "tel", required = false) String tel,
            @RequestParam(value = "tserialnum", required = false) String tserialnum,
            @RequestParam(value = "serialnum", required = false) String serialnum,
            @RequestParam(value = "currentLink", required = false) String currentLink,
            @RequestParam(value = "formType", required = false) String formType,
            @RequestParam(value = "beComplainant", required = false) String beComplainant,
            @RequestParam(value = "complainant", required = false) String complainant,
            @RequestParam(value = "begindate", required = false) String begindate,
            @RequestParam(value = "enddate", required = false) String enddate,
            @RequestParam(value = "expectedBegin", required = false) String expectedBegin,
            @RequestParam(value = "expectedEnd", required = false) String expectedEnd,
            @RequestParam(value = "unitName", required = false) String unitName
            ) throws IOException {
        System.out.println(system);
        Map<String, Object> hashMap = new HashMap();

        try {
            if (StringUtils.isNotBlank(rqsttitle)) {
                hashMap.put("rqsttitle", rqsttitle);
            }
            if (StringUtils.isNotBlank(keyword)) {
                hashMap.put("keyword", keyword);
            }
            if (StringUtils.isNotBlank(tel)) {
                hashMap.put("tel", tel);
            }
            if (StringUtils.isNotBlank(serialnum)) {
                hashMap.put("serialnum", serialnum);
            }
            if (StringUtils.isNotBlank(tserialnum)) {
                hashMap.put("tserialnum", tserialnum);
            }
            if (StringUtils.isNotBlank(ename)) {
                hashMap.put("ename", ename);
            }
            if (StringUtils.isNotBlank(currentLink)) {
                hashMap.put("currentLink", currentLink);
            }
            if (StringUtils.isNotBlank(complainant)) {
                hashMap.put("complainant", complainant);
            }
            if (StringUtils.isNotBlank(beComplainant)) {
                hashMap.put("beComplainant", beComplainant);
            }
            if (StringUtils.isNotBlank(formType)) {
                hashMap.put("formType", formType);
            }
            if (StringUtils.isNotBlank(begindate)) {
                hashMap.put("begindate", begindate);
            }
            if (StringUtils.isNotBlank(enddate)) {
                if (begindate.equals(enddate)) {
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = sdf.parse(enddate);
                        Calendar c = Calendar.getInstance();
                        c.setTime(date);
                        c.add(Calendar.DAY_OF_MONTH, 1);
                        date = c.getTime();
                        String endDate = sdf.format(date);
                        hashMap.put("enddate", endDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    hashMap.put("enddate", enddate);
                }
            }
            if (StringUtils.isNotBlank(expectedBegin)) {
                hashMap.put("expectedBegin", expectedBegin);
            }
            if (StringUtils.isNotBlank(expectedEnd)) {
                hashMap.put("expectedEnd", expectedEnd);
            }
            if (StringUtils.isNotBlank(unitName)) {
                hashMap.put("unitName", unitName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //本地系统待办导出Excel
        if (system.equals("local")) {
            List<BusinesslocalVO> list1 = new ArrayList<>();
            hashMap.put("systemsource", "01");
            if (caseguids.length == 0) {
                caseguids = iBusinesslocalVOService.searchBusinesslocalInfoBysystemsource(hashMap);
            }
            for (String caseguid : caseguids) {
                Map<String, String> map = new HashMap<>();
                map.put("caseguid", caseguid);
                BusinesslocalVO businesslocalVO = iBusinesslocalVOService.searchBusinesslocalInfo(map);
                Integer i = list1.size();
                String index = "";
                index = Integer.toString(++i);
                businesslocalVO.setId(index);
                if ("01".equals(businesslocalVO.getFormtype())) {
                    businesslocalVO.setFormtype("咨询");
                } else if ("02".equals(businesslocalVO.getFormtype())) {
                    businesslocalVO.setFormtype("消费投诉");
                } else if ("03".equals(businesslocalVO.getFormtype())) {
                    businesslocalVO.setFormtype("经济违法举报");
                }
                list1.add(businesslocalVO);
            }
            iexcelService.ExportBusinessSearchlocalVOFile(list1, response);
        }
        //12315系统待办导出Excel
        if (system.equals("12315")) {
            List<Business12315VO> list1 = new ArrayList<>();
            hashMap.put("systemsource", "03");
            if (caseguids.length == 0) {
                caseguids = iBusiness12315VOService.searchBusiness12315InfoBysystemsource(hashMap);
            }
            for (String caseguid : caseguids) {
                Map<String, String> map = new HashMap<>();
                map.put("caseguid", caseguid);
                Business12315VO business12315VO = iBusiness12315VOService.searchBusiness12315Info(map);
                Integer i = list1.size();
                String index = "";
                index = Integer.toString(++i);
                business12315VO.setId(index);
                if ("01".equals(business12315VO.getSystemsource())) {
                    business12315VO.setSystemsource("本地系统");
                } else if ("02".equals(business12315VO.getSystemsource())) {
                    business12315VO.setSystemsource("12345系统");
                } else if ("03".equals(business12315VO.getSystemsource())) {
                    business12315VO.setSystemsource("12315系统");
                }
                list1.add(business12315VO);
            }
            iexcelService.ExportBusinessSearch12315VOFile(list1, response);
        }
        //12345系统待办导出Excel
        if (system.equals("12345")) {
            List<Business12345VO> list1 = new ArrayList<>();
            if (caseguids.length == 0) {
                hashMap.put("systemsource", "02");
                caseguids = iBusiness12345VOService.searchBusiness12345InfoBysystemsource(hashMap);
            }
            for (String caseguid : caseguids) {
                Map<String, String> map = new HashMap<>();
                map.put("caseguid", caseguid);
                Business12345VO business12345VO = iBusiness12345VOService.searchBusiness12345Info(map);
                Integer i = list1.size();
                String index = "";
                index = Integer.toString(++i);
                business12345VO.setId(index);
                if ("01".equals(business12345VO.getFormtype())) {
                    business12345VO.setFormtype("咨询");
                } else if ("02".equals(business12345VO.getFormtype())) {
                    business12345VO.setFormtype("消费投诉");
                } else if ("03".equals(business12345VO.getFormtype())) {
                    business12345VO.setFormtype("经济违法举报");
                }
                list1.add(business12345VO);
            }
            iexcelService.ExportBusinessSearch12345VOFile(list1, response);
        } else if (system.equals("0203")) {
            //预警工单Excel导出
            List<BusinessSearchVO> list1 = new ArrayList<>();
            if (caseguids.length == 0) {
                hashMap.put("systemsource", "0203");
                caseguids = iBusinessSearchService.searchBusinessExpectedInfoBysystemsource(hashMap);
            }
            for (String caseguid : caseguids) {
                Map<String, String> map = new HashMap<>();
                map.put("caseguid", caseguid);
                BusinessSearchVO businessSearchVO = iBusinessSearchService.searchBusinessInfo(map);
                Integer i = list1.size();
                String index = "";
                index = Integer.toString(++i);
                businessSearchVO.setId(index);
                if ("01".equals(businessSearchVO.getSystemsource())) {
                    businessSearchVO.setSystemsource("本地系统");
                } else if ("02".equals(businessSearchVO.getSystemsource())) {
                    businessSearchVO.setSystemsource("12345系统");
                } else if ("03".equals(businessSearchVO.getSystemsource())) {
                    businessSearchVO.setSystemsource("12315系统");
                }
                list1.add(businessSearchVO);
            }
            iexcelService.ExportBusinessSearchVOFile(list1, response);
        }
    }


    /**
     * 待办业务查询结果详情页Word导出
     *
     * @return
     */
    @ApiOperation(
            tags = "BusinessSearchController",
            value = "待办业务查询结果详情页Word导出")
    @GetMapping("wordexport")
    public void wordexport(@ApiParam(value = "查询相关参数", required = true)
                                   HttpServletResponse response, @RequestParam String caseguid, @RequestParam String formType,
                           String systemsource, String tserialnum) throws IOException {

        if ("comeconomicmodule".equals(formType)) {//经济
            //经济违法表单
            QueryWrapper<Economyform> economyformWrapper = new QueryWrapper<Economyform>();
            economyformWrapper.lambda().eq(Economyform::getCaseguid, caseguid);
            Economyform economyform = iEconomyformService.getOne(economyformWrapper, false);
            Map<String, Object> stringObjectMap = BeanUtils.beanToMap(economyform);
            stringObjectMap.put("tserialnum", tserialnum);
            //办理跟踪表
            QueryWrapper<Opinion> opinionWrapper = new QueryWrapper<Opinion>();
            opinionWrapper.lambda().eq(Opinion::getCaseguid, caseguid).orderByAsc(Opinion::getHandletime);
            List<Opinion> opinion = iOpinionService.list(opinionWrapper);
            //遍历办理跟踪表,代入size充当序号
            for (int i = 0; i < opinion.size(); i++) {
                opinion.get(i).setSize(i + 1);
            }
            stringObjectMap.put("opinion", opinion);
            iexcelService.wordExportFile(stringObjectMap, response, formType, systemsource);
        }
        if ("complaintmodule".equals(formType)) {//投诉
            //消费投诉表单
            QueryWrapper<Complaintform> complaintformWrapper = new QueryWrapper<Complaintform>();
            complaintformWrapper.lambda().eq(Complaintform::getCaseguid, caseguid);
            Complaintform complaintform = iComplaintformService.getOne(complaintformWrapper, false);
            Map<String, Object> stringObjectMap = BeanUtils.beanToMap(complaintform);
            stringObjectMap.put("tserialnum", tserialnum);
            //办理跟踪表
            QueryWrapper<Opinion> opinionWrapper = new QueryWrapper<Opinion>();
            opinionWrapper.lambda().eq(Opinion::getCaseguid, caseguid).orderByAsc(Opinion::getHandletime);
            List<Opinion> opinion = iOpinionService.list(opinionWrapper);
            //遍历办理跟踪表,代入size充当序号
            for (int i = 0; i < opinion.size(); i++) {
                opinion.get(i).setSize(i + 1);
            }
            stringObjectMap.put("opinion", opinion);
            System.out.println(stringObjectMap);
            iexcelService.wordExportFile(stringObjectMap, response, formType, systemsource);

        }
        if ("commommodule".equals(formType) || "consultationform".equals(formType)) {
            if ("01".equals(systemsource)) {//本地系统待办
                //咨询表单
//                QueryWrapper<Consultationform> consultationformWrapper = new QueryWrapper<Consultationform>();
//                consultationformWrapper.lambda().eq(Consultationform::getCaseguid, caseguid);
//                Consultationform consultationform = iConsultationformService.getOne(consultationformWrapper, false);
//                Map<String, Object> stringObjectMap = BeanUtils.beanToMap(consultationform);

                //更改咨询的表单类型为Commomform类。防止null异常
                QueryWrapper<Commomform> commomformQueryWrapper = new QueryWrapper<Commomform>();
                commomformQueryWrapper.lambda().eq(Commomform::getCaseguid, caseguid);
                Commomform commomform = iCommomformService.getOne(commomformQueryWrapper, false);
                Map<String, Object> stringObjectMap = BeanUtils.beanToMap(commomform);
                stringObjectMap.put("tserialnum", tserialnum);
                //办理跟踪表
                QueryWrapper<Opinion> opinionWrapper = new QueryWrapper<Opinion>();
                opinionWrapper.lambda().eq(Opinion::getCaseguid, caseguid).orderByAsc(Opinion::getHandletime);
                List<Opinion> opinion = iOpinionService.list(opinionWrapper);
                //遍历办理跟踪表,代入size充当序号
                for (int i = 0; i < opinion.size(); i++) {
                    opinion.get(i).setSize(i + 1);
                }
                stringObjectMap.put("opinion", opinion);
                iexcelService.wordExportFile(stringObjectMap, response, formType, systemsource);

            } else if ("02".equals(systemsource)) {//12345系统待办
                //通用表单
                QueryWrapper<Commomform> commomformWrapper = new QueryWrapper<Commomform>();
                commomformWrapper.lambda().eq(Commomform::getCaseguid, caseguid);
                Commomform commomform = iCommomformService.getOne(commomformWrapper, false);
                Map<String, Object> stringObjectMap = BeanUtils.beanToMap(commomform);
                stringObjectMap.put("tserialnum", tserialnum);
                //办理跟踪表
                QueryWrapper<Opinion> opinionWrapper = new QueryWrapper<Opinion>();
                opinionWrapper.lambda().eq(Opinion::getCaseguid, caseguid).orderByAsc(Opinion::getHandletime);
                List<Opinion> opinion = iOpinionService.list(opinionWrapper);
                //遍历办理跟踪表,代入size充当序号
                for (int i = 0; i < opinion.size(); i++) {
                    opinion.get(i).setSize(i + 1);
                }
                stringObjectMap.put("opinion", opinion);
                System.out.println(stringObjectMap);
                iexcelService.wordExportFile(stringObjectMap, response, formType, systemsource);

            }
        }
    }


    /**
     * 综合业务查询接口
     *
     * @return
     */
    @ApiOperation(
            tags = "BusinessSearchController",
            value = "综合业务查询结果导出")
    @RequestMapping(value = "searchBusinessList", method = RequestMethod.POST)
    @ResponseBody
    public void searchBusinessList(@ApiParam(value = "查询相关参数", required = true)
                                           HttpServletResponse response, @RequestBody Map<String, List<String>> requestMap) {
//        int size = requestMap.get("size") != null ? ((Integer) requestMap.get("size")).intValue() : 10;
//        int current = requestMap.get("page") != null ? ((Integer) requestMap.get("page")).intValue() : 1;
//        IPage<BusinessSearchVO> businessSearchVOIPage = iBusinessSearchService.searchBusinessInfo(current, size, requestMap);
//        return RestResult.ok(businessSearchVOIPage);

        //待办业务综合查询结果Excel导出

        List<BusinessSearchVO> list1 = new ArrayList<>();
        List<String> caseguids = requestMap.get("list");
        for (String caseguid : caseguids) {
            Map<String, String> map = new HashMap<>();
            map.put("caseguid", caseguid);
            BusinessSearchVO businessSearchVO = iBusinessSearchService.searchBusinessInfo(map);
            Integer i = list1.size();
            String index = "";
            index = Integer.toString(++i);
            businessSearchVO.setId(index);
            if ("01".equals(businessSearchVO.getSystemsource())) {
                businessSearchVO.setSystemsource("本地系统");
            } else if ("02".equals(businessSearchVO.getSystemsource())) {
                businessSearchVO.setSystemsource("12345系统");
            } else if ("03".equals(businessSearchVO.getSystemsource())) {
                businessSearchVO.setSystemsource("12315系统");
            }

            if("1".equals(businessSearchVO.getApplealtocase())){
                businessSearchVO.setApplealtocase("是");
            }else{
                businessSearchVO.setApplealtocase("否");
            }
            if(!businessSearchVO.getBustype().contains("投诉")){
                businessSearchVO.setResultsofmediation("无");
            }

            list1.add(businessSearchVO);
        }
        try {
            iexcelService.ExportBusinessSearchVOFile(list1, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
//            return RestResult.ok(list1);
    }

}
