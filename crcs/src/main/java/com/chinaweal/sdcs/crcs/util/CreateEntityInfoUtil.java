package com.chinaweal.sdcs.crcs.util;

import com.chinaweal.aicorg.model.AICUser;
import com.chinaweal.sdcs.crcs.business.entity.*;
import com.chinaweal.sdcs.crcs.crawler.bmfw.entity.*;
import com.chinaweal.sdcs.crcs.crawler.ywcl.entity.*;
import com.chinaweal.sdcs.crcs.system.service.IBasecodeContrastService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Component
public class CreateEntityInfoUtil {

    private static CreateEntityInfoUtil createEntityInfoUtil;

    @Resource
    private IBasecodeContrastService iBasecodeContrastService;

    @PostConstruct
    public void init() {
        createEntityInfoUtil = this;
    }

    public static Tasklist createTasklist(Datagriddata datagriddata, AICUser aicUser) {
        Tasklist tasklist = null;
        if (datagriddata != null) {
            tasklist = new Tasklist();
            tasklist.setId(datagriddata.getId());
            tasklist.setCaseguid(datagriddata.getCaseguid());
            tasklist.setFocus(datagriddata.getFocus());
            tasklist.setSerialnum(datagriddata.getSerialnum());
            tasklist.setTserialnum(datagriddata.getTserialnum());
            tasklist.setRqsttitle(datagriddata.getRqsttitle());
            tasklist.setAccordtype(datagriddata.getAccordtype());
            tasklist.setLabelname(datagriddata.getLabelname());
            tasklist.setAssigndate(datagriddata.getCreatedate());
            tasklist.setResulttimemin(datagriddata.getResulttimemin());
            tasklist.setSystemsource("1");
            tasklist.setIssignin("0");
            tasklist.setSigninaccount(aicUser.getEname());
            tasklist.setSigninname(aicUser.getName());
            tasklist.setSigninuserid(aicUser.getPrimaryKey());
            tasklist.setSignindeptid(aicUser.getUnitID());
            tasklist.setState("0");
            tasklist.setSigndate(new Date());
            tasklist.setHandledeptname(aicUser.getOrgName());//当前处理机关
        }
        return tasklist;
    }

    public static Customerinfo createCustomerinfo(Seats seats) {
        Customerinfo customerinfo = null;
        if (seats != null) {
            customerinfo = new Customerinfo();
            customerinfo.setId(seats.getId());
            customerinfo.setCaseguid(seats.getCaseguid());
            customerinfo.setSeatsname(seats.getSeatsname());
            customerinfo.setSeatssex(seats.getSeatssex());
            customerinfo.setSeatslanguage(seats.getSeatslanguage());
            customerinfo.setSeatsregistrationtime(seats.getSeatsregistrationtime());
            customerinfo.setUpdatedate(new Date());
        }
        return customerinfo;
    }

    public static Commomform createCommomform(Commommodule commommodule) {
        Commomform commomform = null;
        if (commommodule != null) {
            commomform = new Commomform();
            commomform.setId(commommodule.getId());
            commomform.setCaseguid(commommodule.getCaseguid());
            commomform.setFormtype(commommodule.getFormtype());
            commomform.setFormmodule(commommodule.getFormmodule());
            commomform.setRqsttitle(commommodule.getRqsttitle());
            commomform.setName(commommodule.getName());
            commomform.setGender(commommodule.getGender());
            commomform.setRegion(commommodule.getRegion());
            commomform.setIsvalid(commommodule.getIsvalid());
            commomform.setRqstphonenumber(commommodule.getRqstnumber());
            commomform.setLinkphonenumber(commommodule.getLinknumber());
            commomform.setUsertype(commommodule.getUsertype());
            commomform.setAnswertype(commommodule.getAnswertype());
            commomform.setEntitytype(commommodule.getEntitytype());
            commomform.setEntname(commommodule.getEntname());
            commomform.setAicid(commommodule.getAicid());
            commomform.setAddress(commommodule.getAddress());
            commomform.setFocus(commommodule.getFocus());
            commomform.setFocusxjfl(commommodule.getFocusxjfl());
            if (StringUtils.isNotBlank(commommodule.getRqstcontent())) {
                commomform.setRqstcontent(commommodule.getRqstcontent().replace("</br>", "\r\n"));
            }
            commomform.setGovlabel(commommodule.getGovlabel());
        }
        return commomform;
    }

    public static Economyform createEconomyform(Comeconomicmodule comeconomicmodule) {
        Economyform economyform = null;
        if (comeconomicmodule != null) {
            economyform = new Economyform();
            economyform.setId(comeconomicmodule.getId());
            economyform.setCaseguid(comeconomicmodule.getCaseguid());
            economyform.setFormtype(comeconomicmodule.getFormtype());
            economyform.setRqsttitle(comeconomicmodule.getRqsttitle());
            economyform.setAccordtype(comeconomicmodule.getAccordtype());
            economyform.setRqstperson(comeconomicmodule.getRqstperson());
            economyform.setGender(comeconomicmodule.getGender());
            economyform.setRqstnumber(comeconomicmodule.getRqstnumber());
            economyform.setLinknumber(comeconomicmodule.getLinknumber());
            economyform.setRqstaddress(comeconomicmodule.getRqstaddress());
            economyform.setUsertype(comeconomicmodule.getUsertype());
            economyform.setAnswertype(comeconomicmodule.getAnswertype());
            economyform.setFocus(comeconomicmodule.getFocus());
            economyform.setProvidelb(comeconomicmodule.getProvidelb());
            economyform.setAge(comeconomicmodule.getAge());
            economyform.setNation(comeconomicmodule.getNation());
            economyform.setContactaddress(comeconomicmodule.getContactaddress());
            economyform.setStatus(comeconomicmodule.getStatus());
            economyform.setComplaintpaper(comeconomicmodule.getComplaintpaper());
            economyform.setDisabilityidentification(comeconomicmodule.getDisabilityidentification());
            economyform.setCertificate(comeconomicmodule.getCertificate());
            economyform.setIdnumber(comeconomicmodule.getIdnumber());
            economyform.setCareer(comeconomicmodule.getCareer());
            economyform.setCompany(comeconomicmodule.getCompany());
            economyform.setSubjectname(comeconomicmodule.getSubjectname());
            economyform.setLinktype(comeconomicmodule.getLinktype());
            economyform.setSubjectphone(comeconomicmodule.getSubjectphone());
            economyform.setAicstationid(comeconomicmodule.getAicstationid());
            economyform.setPostcode(comeconomicmodule.getPostcode());
            economyform.setJyareacode(comeconomicmodule.getJyareacode());
            economyform.setSubjectaddress(comeconomicmodule.getSubjectaddress());
            economyform.setZcareacode(comeconomicmodule.getZcareacode());
            economyform.setSubjectzcadd(comeconomicmodule.getSubjectzcadd());
            economyform.setCksubjectname(comeconomicmodule.getCksubjectname());
            economyform.setCkjyareacode(comeconomicmodule.getCkjyareacode());
            economyform.setCksubjectaddress(comeconomicmodule.getCksubjectaddress());
            economyform.setObjname(comeconomicmodule.getObjname());
            economyform.setBrand(comeconomicmodule.getBrand());
            economyform.setObjclassify(comeconomicmodule.getObjclassify());
            economyform.setObjmoney(comeconomicmodule.getObjmoney());
            economyform.setProducecompany(comeconomicmodule.getProducecompany());
            economyform.setIsweb(comeconomicmodule.getIsweb());
            economyform.setImportmark(comeconomicmodule.getImportmark());
            economyform.setTypespecification(comeconomicmodule.getTypespecification());
            economyform.setQuantity(comeconomicmodule.getQuantity());
            economyform.setMeasureunit(comeconomicmodule.getMeasureunit());
            economyform.setPrice(comeconomicmodule.getPrice());
            economyform.setPurchasingdate(comeconomicmodule.getPurchasingdate());
            economyform.setProblemtype(comeconomicmodule.getProblemtype());
            economyform.setIllegalrules(comeconomicmodule.getIllegalrules());
            if (StringUtils.isNotBlank(comeconomicmodule.getProblemdescribe())) {
                economyform.setProblemdescribe(comeconomicmodule.getProblemdescribe().replace("</br>", "\r\n"));
            }
            economyform.setKeyword(comeconomicmodule.getKeyword());
            economyform.setEventlevel(comeconomicmodule.getEventlevel());
            economyform.setGovlabel(comeconomicmodule.getGovlabel());
            economyform.setDifferserv(comeconomicmodule.getDifferserv());
            economyform.setBriefing(comeconomicmodule.getBriefing());
            economyform.setAnestimate(comeconomicmodule.getAnestimate());
            economyform.setEconomicloss(comeconomicmodule.getEconomicloss());
            economyform.setTortclassify(comeconomicmodule.getTortclassify());
            economyform.setPersonalinjury(comeconomicmodule.getPersonalinjury());
            economyform.setInjurydegree(comeconomicmodule.getInjurydegree());
            economyform.setInjuries(comeconomicmodule.getInjuries());
            economyform.setConsumptionsafety(comeconomicmodule.getConsumptionsafety());
            economyform.setConsumptionsafetytype(comeconomicmodule.getConsumptionsafetytype());
            economyform.setEven(comeconomicmodule.getEven());
            economyform.setReduction(comeconomicmodule.getReduction());
            economyform.setReport(comeconomicmodule.getReport());
            economyform.setIsoutmall(comeconomicmodule.getIsoutmall());
            economyform.setIsfeedback(comeconomicmodule.getIsfeedback());
            economyform.setIsaward(comeconomicmodule.getIsaward());
            economyform.setAttachfilename(comeconomicmodule.getAttachfilename());
            economyform.setUpdatedate(new Date());
        }
        return economyform;
    }

    public static Complaintform createComplaintform(Complaintmodule complaintmodule) {
        Complaintform complaintform = null;
        if (complaintmodule != null) {
            complaintform = new Complaintform();
            complaintform.setId(complaintmodule.getId());
            complaintform.setCaseguid(complaintmodule.getCaseguid());
            complaintform.setFormtype(complaintmodule.getFormtype());
            complaintform.setRqsttitle(complaintmodule.getRqsttitle());
            complaintform.setRqstperson(complaintmodule.getRqstperson());
            complaintform.setGender(complaintmodule.getGender());
            complaintform.setRqstnumber(complaintmodule.getRqstnumber());
            complaintform.setLinknumber(complaintmodule.getLinknumber());
            complaintform.setUsertype(complaintmodule.getUsertype());
            complaintform.setAnswertype(complaintmodule.getAnswertype());
            complaintform.setProvidetype(complaintmodule.getProvidetype());
            complaintform.setProvidelb(complaintmodule.getProvidelb());
            complaintform.setFocus(complaintmodule.getFocus());
            complaintform.setRegion(complaintmodule.getRegion());
            complaintform.setAge(complaintmodule.getAge());
            complaintform.setNation(complaintmodule.getNation());
            complaintform.setStatus(complaintmodule.getStatus());
            complaintform.setComplaintpaper(complaintmodule.getComplaintpaper());
            complaintform.setDisabilityidentification(complaintmodule.getDisabilityidentification());
            complaintform.setCertificate(complaintmodule.getCertificate());
            complaintform.setCodepostal(complaintmodule.getCodepostal());
            complaintform.setIdnumber(complaintmodule.getIdnumber());
            complaintform.setCareer(complaintmodule.getCareer());
            complaintform.setCompany(complaintmodule.getCompany());
            complaintform.setSubjectname(complaintmodule.getSubjectname());
            complaintform.setLinktype(complaintmodule.getLinktype());
            complaintform.setSubjectphone(complaintmodule.getSubjectphone());
            complaintform.setAicstationid(complaintmodule.getAicstationid());
            complaintform.setPostcode(complaintmodule.getPostcode());
            complaintform.setJyareacode(complaintmodule.getJyareacode());
            complaintform.setSubjectaddress(complaintmodule.getSubjectaddress());
            complaintform.setZcareacode(complaintmodule.getZcareacode());
            complaintform.setSubjectzcadd(complaintmodule.getSubjectzcadd());
            complaintform.setCksubjectname(complaintmodule.getCksubjectname());
            complaintform.setCkjyareacode(complaintmodule.getCkjyareacode());
            complaintform.setCksubjectaddress(complaintmodule.getCksubjectaddress());
            complaintform.setObjname(complaintmodule.getObjname());
            complaintform.setBrand(complaintmodule.getBrand());
            complaintform.setObjclassify(complaintmodule.getObjclassify());
            complaintform.setObjmoney(complaintmodule.getObjmoney());
            complaintform.setProducecompany(complaintmodule.getProducecompany());
            complaintform.setIsweb(complaintmodule.getIsweb());
            complaintform.setImportmark(complaintmodule.getImportmark());
            complaintform.setTypespecification(complaintmodule.getTypespecification());
            complaintform.setQuantity(complaintmodule.getQuantity());
            complaintform.setMeasureunit(complaintmodule.getMeasureunit());
            complaintform.setPrice(complaintmodule.getPrice());
            complaintform.setPurchasingdate(complaintmodule.getPurchasingdate());
            complaintform.setProblemtype(complaintmodule.getProblemtype());
            if (StringUtils.isNotBlank(complaintmodule.getProblemdescribe())) {
                complaintform.setProblemdescribe(complaintmodule.getProblemdescribe().replace("</br>", "\r\n"));
            }
            complaintform.setKeyword(complaintmodule.getKeyword());
            complaintform.setGovlabeloutput(complaintmodule.getGovlabeloutput());
            complaintform.setDifferserv(complaintmodule.getDifferserv());
            complaintform.setBriefing(complaintmodule.getBriefing());
            complaintform.setAnestimate(complaintmodule.getAnestimate());
            complaintform.setEconomicloss(complaintmodule.getEconomicloss());
            complaintform.setTortclassify(complaintmodule.getTortclassify());
            complaintform.setPersonalinjury(complaintmodule.getPersonalinjury());
            complaintform.setInjurydegree(complaintmodule.getInjurydegree());
            complaintform.setInjuries(complaintmodule.getInjuries());
            complaintform.setConsumptionsafety(complaintmodule.getConsumptionsafety());
            complaintform.setConsumptionsafetytype(complaintmodule.getConsumptionsafetytype());
            complaintform.setEven(complaintmodule.getEven());
            complaintform.setReduction(complaintmodule.getReduction());
            complaintform.setReport(complaintmodule.getReport());
            complaintform.setIsoutmall(complaintmodule.getIsoutmall());
            complaintform.setAttachfilename(complaintmodule.getAttachfilename());
            complaintform.setUpdatedate(new Date());
        }
        return complaintform;
    }

    public static Opinion createOpinion(Askdepthandle askdepthandle) {
        Opinion opinion = null;
        if (askdepthandle != null) {
            opinion = new Opinion();
            opinion.setId(askdepthandle.getId());
            opinion.setCaseguid(askdepthandle.getCaseguid());
            opinion.setNodename(askdepthandle.getActivitytype());
            opinion.setHandleopinion(askdepthandle.getHandleopinion());
            opinion.setHandler(askdepthandle.getOperateusername());
            opinion.setHandledept(askdepthandle.getOperatorouname());
            opinion.setOperate(askdepthandle.getOpeartionstatus());
            opinion.setHandletime(askdepthandle.getHandletime());
            opinion.setUpdatedate(new Date());
        }
        return opinion;
    }

    public static Processinfo createProcessinfo(String caseguid, AICUser aicUser) {
        Processinfo processinfo = null;
        if (aicUser != null) {
            processinfo = new Processinfo();
            processinfo.setId(UUID.randomUUID().toString());
            processinfo.setCaseguid(caseguid);
            processinfo.setHandler(aicUser.getName());
            processinfo.setHandleruserid(aicUser.getPrimaryKey());
            processinfo.setHandledept(aicUser.getUnitName());
            processinfo.setHandledeptid(aicUser.getUnitID());
            processinfo.setStatus("0");
            processinfo.setIsmain("0");
            processinfo.setStartdate(new Date());
            processinfo.setEnddate(null);
            processinfo.setHandleorgid(aicUser.getOrgID());
            processinfo.setHandleorgname(aicUser.getOrgName());
        }
        return processinfo;
    }

    public static Handledemand createHandledemand(Inrequest inrequest) {
        Handledemand handledemand = null;
        if (inrequest != null) {
            handledemand = new Handledemand();
            handledemand.setId(inrequest.getId());
            handledemand.setCaseguid(inrequest.getCaseguid());
            handledemand.setOpiniontimeshould(inrequest.getOpiniontimeshould());
            handledemand.setOpiniontimesurplus(inrequest.getOpiniontimesurplus());
            handledemand.setOpiniontime(inrequest.getOpiniontime());
            handledemand.setAccepttimeshould(inrequest.getAccepttimeshould());
            handledemand.setAccepttimesurplus(inrequest.getAccepttimesurplus());
            handledemand.setAccepttime(inrequest.getAccepttime());
            handledemand.setCaseputtimeshould(inrequest.getCaseputtimeshould());
            handledemand.setCaseputtimesurplus(inrequest.getCaseputtimesurplus());
            handledemand.setCaseputtime(inrequest.getCaseputtime());
            handledemand.setResulttimeshould(inrequest.getResulttimeshould());
            handledemand.setResulttimesurplus(inrequest.getResulttimesurplus());
            handledemand.setResulttime(inrequest.getResulttime());
            handledemand.setOpiniontimeminstr(inrequest.getOpiniontimeminstr());
            handledemand.setAccepttimeminstr(inrequest.getAccepttimeminstr());
            handledemand.setCaseputtimeminstr(inrequest.getCaseputtimeminstr());
            handledemand.setResulttimeminstr(inrequest.getResulttimeminstr());
            handledemand.setUpdatedate(new Date());
        }
        return handledemand;
    }

    public static Tasklist createTasklist(AICUser aicUser) {
        Tasklist tasklist = new Tasklist();
        tasklist.setId(UUID.randomUUID().toString());
        tasklist.setAssigndate(new Date());
        tasklist.setResulttimemin(null);
        tasklist.setSystemsource("01"); //本系统
        tasklist.setIssignin("0");
        tasklist.setState("0");
        tasklist.setSigninaccount(aicUser.getEname());
        tasklist.setSigninname(aicUser.getName());
        tasklist.setSigninuserid(aicUser.getPrimaryKey());
        tasklist.setSignindeptid(aicUser.getUnitID());
        tasklist.setRegionid(aicUser.getRegionID());//TODO 相关机关
        tasklist.setSigndate(new Date());
        tasklist.setHandledeptname(aicUser.getOrgName());
        return tasklist;
    }

    /**
     * 根据开始日期 ，需要的工作日天数 ，计算工作截止日期，并返回截止日期
     *
     * @param startDate 开始日期
     * @param workDay   工作日天数(周一到周五)
     * @return Date类型
     * @createTime 2019-9-21
     */
    public static Date getWorkDay(Date startDate, int workDay, int hour, int second, int minute) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(startDate);
        for (int i = workDay; i >= 0; i--) {
            // 判断当天是否为周末，如果是周末加1
            if (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                workDay = workDay + 1;
                c1.set(Calendar.DATE, c1.get(Calendar.DATE) + 1);
                continue;
            }
            c1.set(Calendar.DATE, c1.get(Calendar.DATE) + 1);
            // 当天数加1 判断是否为周末 如果是周末加1
//            if (Calendar.SUNDAY == c1.get(Calendar.SUNDAY) || Calendar.SATURDAY == c1.get(Calendar.SATURDAY)) {
//                workDay = workDay + 1;
//                c1.set(Calendar.DATE, c1.get(Calendar.DATE) + 1);
//                continue;
//            }
        }
//        System.out.println(workDay);

        c1.set(Calendar.HOUR_OF_DAY, hour);
        c1.set(Calendar.SECOND, second);
        c1.set(Calendar.MINUTE, minute);
        //liny--2020-3-3
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date newDate = addDate(startDate, workDay);
//        System.out.println(newDate);
        return newDate;
//        return c1.getTime();
    }

    //liny--2020-3-3
    public static Date addDate(Date date,long day) {
        long time = date.getTime(); // 得到指定日期的毫秒数
        day = day*24*60*60*1000; // 要加上的天数转换成毫秒数
        time+=day; // 相加得到新的毫秒数
        return new Date(time); // 将毫秒数转换成日期
    }

    /**
     * 根据日期，获取星期几
     *
     * @param dt
     * @return String类型
     * @createTime 2019-9-21
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) w = 0;
        return weekDays[w];
    }

    /**
     * 时间运算
     *
     * @param date1
     * @param date2
     * @throws Exception
     */
    public static long judgmentDate(String date1, String date2) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d HH:mm:ss");

        Date start = sdf.parse(date1);

        Date end = sdf.parse(date2);
        return start.getTime() - end.getTime();

    }

    public static Tasklist createTasklist(Datagriddata datagriddata) {
        Tasklist tasklist = null;
        if (datagriddata != null) {
            tasklist = new Tasklist();
            tasklist.setId(datagriddata.getId());
            tasklist.setCaseguid(datagriddata.getCaseguid());
            tasklist.setFocus(datagriddata.getFocus());
            tasklist.setSerialnum(datagriddata.getSerialnum());
            tasklist.setTserialnum(datagriddata.getTserialnum());
            tasklist.setRqsttitle(datagriddata.getRqsttitle());
            tasklist.setAccordtype(datagriddata.getAccordtype());
            tasklist.setLabelname(datagriddata.getLabelname());
            tasklist.setAssigndate(datagriddata.getCreatedate());
            tasklist.setResulttimemin(datagriddata.getResulttimemin());
            tasklist.setWorktype(datagriddata.getTeti());
            tasklist.setSystemsource("02"); //02 12345系统
            tasklist.setIssignin("0");
            tasklist.setState("0");
            tasklist.setFormtype(datagriddata.getFormtype());
            if("1".equals(datagriddata.getIscp())){
                tasklist.setIscp("1");
            }else {
                tasklist.setIscp("0");
            }
            if ("消费投诉表单".equals(datagriddata.getFormtype())) {
                tasklist.setBustype("投诉类");
            } else if ("经济违法行为举报表单".equals(datagriddata.getFormtype())) {
                tasklist.setBustype("举报类");
            } else {
                tasklist.setBustype("咨询类");
            }
            tasklist.setSigndate(new Date());
            tasklist.setCreatedate(new Date());
        }
        return tasklist;
    }

    public static Materialinfo createMaterialinfo(Accessory accessory) {
        Materialinfo materialinfo = null;
        if (accessory != null) {
            materialinfo = new Materialinfo();
            materialinfo.setId(accessory.getId());
            materialinfo.setCaseguid(accessory.getCaseguid());
            if (StringUtils.isNotBlank(accessory.getAttachfilename())) {
                materialinfo.setMaterialtype(accessory.getAttachfilename().substring(accessory.getAttachfilename().lastIndexOf(".") + 1));
            }
            materialinfo.setAmount(new BigDecimal(1));
            if(StringUtils.isNotBlank(accessory.getLocaladdress())) {
                String localAddress = accessory.getLocaladdress();
                int index = localAddress.indexOf("download");
                if (-1 != index) {
                    localAddress = localAddress.substring(index + 8);
                }
                materialinfo.setFilesavepath(localAddress);
            }
            materialinfo.setStatus("1");
            materialinfo.setUploadtime(new Date());
            materialinfo.setUpdatedate(new Date());
            materialinfo.setAttachfilename(accessory.getAttachfilename());
            materialinfo.setBackfillState("source");

        }
        return materialinfo;
    }

    /**
     * 将12315待办转换成Tasklist
     *
     * @param ywclshuntlist
     * @return
     */
    public static Tasklist createTasklistByYwclshuntlist(Ywclshuntlist ywclshuntlist) {
        Tasklist tasklist = null;
        if (ywclshuntlist != null) {
            tasklist = new Tasklist();
            tasklist.setId(ywclshuntlist.getId());
            tasklist.setSerialnum(ywclshuntlist.getCaseid()); //登记编号
            tasklist.setTserialnum(ywclshuntlist.getCaseid()); //登记编号
            tasklist.setAssigndate(ywclshuntlist.getRegtime()); //登记时间
            tasklist.setContent(ywclshuntlist.getApplidique()); //登记内容
            tasklist.setComplainant(ywclshuntlist.getProvidername()); //投诉人
            tasklist.setComplainanted(ywclshuntlist.getInvopt()); //涉及主体
            tasklist.setRegistrant(ywclshuntlist.getAccregpername()); //登记人
            tasklist.setHandledeptname(ywclshuntlist.getClbmname()); //处理部门
            tasklist.setRegdeptname(ywclshuntlist.getRegdeptname()); //登记部门
            tasklist.setCwcs(ywclshuntlist.getCwcs()); //催办次数
            tasklist.setDubcs(ywclshuntlist.getDubcs()); //督办次数
            tasklist.setFirstblqx(ywclshuntlist.getFirstblqx()); //初步查期限
            tasklist.setExpiredate(ywclshuntlist.getBlqx()); //到期时间
            tasklist.setBlqx(ywclshuntlist.getBlqx()); //办理期限
            tasklist.setSystemsource("03"); //03 12315系统
            tasklist.setIssignin("0");
            tasklist.setState("0");
            tasklist.setBustype(ywclshuntlist.getInfotype());
            if ("投诉".equals(ywclshuntlist.getInfotype())) {
                tasklist.setFormtype("02");
            } else if ("举报".equals(ywclshuntlist.getInfotype())) {
                tasklist.setFormtype("03");
            }
            tasklist.setSigndate(new Date());
            tasklist.setNextnodeid("todispatch"); //增加待分流节点
            tasklist.setCreatedate(new Date());
        }
        return tasklist;
    }

    /**
     * 12315详情转举报类
     *
     * @param ywcltapdetail
     * @return
     */
    public static Economyform createEconomyformByYwcltapdetails(Ywcltapdetails ywcltapdetail) {
        Economyform economyform = null;
        String providetypecodeid = ""; //市场主体类型codeid
        String industrynamecodeid = ""; //行业分类codeid
        String objectcategorycodeid = ""; //客体类别codeid
        String reportprovidelbcodeid = ""; //举报问题类别codeid
        if (ywcltapdetail != null) {
            economyform = new Economyform();
            economyform.setId(ywcltapdetail.getId());
            //系统信息
            economyform.setRegistrationnumber(ywcltapdetail.getCaseid()); //登记编号
            economyform.setFormtype(ywcltapdetail.getFormtype()); //记录类型
            economyform.setRecorddate(ywcltapdetail.getRecorddate()); //登记日期
            economyform.setReceivemode(ywcltapdetail.getReceivemode());//接收方式
            economyform.setRqstnumber(ywcltapdetail.getRqstnumber()); //来电号码

            //举报人信息
            economyform.setRqstperson(ywcltapdetail.getProvidername()); //姓名/单位
            economyform.setLinknumber(ywcltapdetail.getProvidertel()); //举报人联系电话
            economyform.setGender(ywcltapdetail.getProvidergender()); //性别
            economyform.setRqstaddress(ywcltapdetail.getProvideraddr()); //举报人地址
            economyform.setCertificate(ywcltapdetail.getCertype()); //证件类型
            economyform.setIdnumber(ywcltapdetail.getIdnumber()); //证件号码
            economyform.setUsertype(ywcltapdetail.getProvidertype()); //提供方类型
            economyform.setAge(ywcltapdetail.getPertypename()); //年龄段
            economyform.setInformerpostcode(ywcltapdetail.getEmail()); //电子邮箱
            economyform.setNationality(ywcltapdetail.getNationality()); //国籍
            economyform.setNation(ywcltapdetail.getNation()); //民族
            economyform.setStatus(ywcltapdetail.getPositionname()); //身份
            economyform.setPostcode(ywcltapdetail.getPostalcode()); //邮政编码
            economyform.setOthercontacts(ywcltapdetail.getOthercontactmethods()); //其他联系方式

            //被举报对象
            economyform.setSubjectname(ywcltapdetail.getSubjectname()); //被举报对象名称
            economyform.setSubjectaddress(ywcltapdetail.getAddr()); //被举报对象地址
            economyform.setProvidetype(ywcltapdetail.getEnttypename()); //市场主体类型
            //市场主体类型codeid 转换成12345对应的codeid
            providetypecodeid = createEntityInfoUtil.iBasecodeContrastService.getFsCodeByCountryCodeAndType(ywcltapdetail.getEnttype(), "enterprise");
            if (StringUtils.isBlank(providetypecodeid)) {
                providetypecodeid = ywcltapdetail.getEnttype();
            }
            economyform.setProvidetypecodeid(providetypecodeid); //类型编号
            economyform.setIndustryname(ywcltapdetail.getIndustrycategory()); //行业分类
            industrynamecodeid = createEntityInfoUtil.iBasecodeContrastService.getFsCodeByCountryNameAndType(ywcltapdetail.getIndustrycategory(), "industry");
            economyform.setIndustrynamecodeid(industrynamecodeid); //行业分类codeid
            economyform.setSubjectphone(ywcltapdetail.getLinknumber()); //联系电话
            economyform.setRegisterno(ywcltapdetail.getSocialcode()); //社会信用代码
            economyform.setReportedcontact(ywcltapdetail.getLinkman()); //联系人

            //业务信息
            economyform.setObjectcategory(ywcltapdetail.getObjtype()); //客体类别
            objectcategorycodeid = createEntityInfoUtil.iBasecodeContrastService.getFsCodeByCountryNameAndType(ywcltapdetail.getObjtype(), "object");
            economyform.setObjectcategorycodeid(objectcategorycodeid); //客体类别codeid
            economyform.setObjname(ywcltapdetail.getObjname()); //商品/服务名称
            economyform.setBrand(ywcltapdetail.getBrand()); //品牌
            economyform.setSellingformat(ywcltapdetail.getSalemode()); //销售方式
            economyform.setObjmoney(new BigDecimal(ywcltapdetail.getMonetary())); //涉及金额
            economyform.setOrdernumber(ywcltapdetail.getBidno()); //订单号
            economyform.setReportprovidelb(ywcltapdetail.getApplbasquename()); //举报问题类别
            reportprovidelbcodeid = createEntityInfoUtil.iBasecodeContrastService.getFsCodeByCountryNameAndType(ywcltapdetail.getApplbasquename(), "question");
            economyform.setReportprovidelbcodeid(reportprovidelbcodeid); //举报问题类别codeid
            economyform.setProductname(ywcltapdetail.getProductname()); //产品名称
            economyform.setApprovalnumber(ywcltapdetail.getApprovalnumber()); //批准文号
            economyform.setLotnumber(ywcltapdetail.getLotnumber()); //产品批号
            economyform.setTypespecification(ywcltapdetail.getProductstandard()); //产品规格
            economyform.setProducecompany(ywcltapdetail.getManufacturer()); //生产厂家
            economyform.setSalesenterprise(ywcltapdetail.getMarketingcompany()); //销售企业
            economyform.setExpirationdate(ywcltapdetail.getExpirationdate()); //产品有效期
            economyform.setKeyword(ywcltapdetail.getKeyword()); //关键字
            if (StringUtils.isNotBlank(ywcltapdetail.getReportdeviation())) {
                economyform.setProblemdescribe(ywcltapdetail.getReportdeviation().replace("</br>", "\r\n")); //举报内容
            }
            economyform.setUpdatedate(new Date()); //更新时间
            economyform.setAccsce(ywcltapdetail.getAccsce()); //事发地
            economyform.setFeedbacktime(ywcltapdetail.getFeedbacktime());
            economyform.setTodealwithtime(ywcltapdetail.getTodealwithtime());
        }
        return economyform;
    }

    /**
     * 12315详情转投诉类
     *
     * @param ywcltapdetail
     * @return
     */
    public static Complaintform createComplaintformByYwcltapdetails(Ywcltapdetails ywcltapdetail) {
        Complaintform complaintform = null;
        String entitytypecodeid = ""; //市场主体类型codeid
        String industrynamecodeid = ""; //行业分类codeid
        String objectcategorycodeid = ""; //客体类型codeid
        String complaintstypecodeid = ""; //投诉问题codeid
        if (ywcltapdetail != null) {
            complaintform = new Complaintform();
            complaintform.setId(ywcltapdetail.getId());
            //系统信息
            complaintform.setRegistrationnumber(ywcltapdetail.getCaseid()); //登记编号
            complaintform.setFormtype(ywcltapdetail.getFormtype()); //记录类型
            complaintform.setRecorddate(ywcltapdetail.getRecorddate()); //登记日期
            complaintform.setReceivemode(ywcltapdetail.getReceivemode());//接收方式
            complaintform.setRqstnumber(ywcltapdetail.getRqstnumber()); //来电号码

            //举报人信息
            complaintform.setRqstperson(ywcltapdetail.getProvidername()); //姓名/单位
            complaintform.setLinknumber(ywcltapdetail.getProvidertel()); //举报人联系电话
            complaintform.setGender(ywcltapdetail.getProvidergender()); //性别
            complaintform.setRqstaddress(ywcltapdetail.getProvideraddr()); //举报人地址
            complaintform.setCertificate(ywcltapdetail.getCertype()); //证件类型
            complaintform.setIdnumber(ywcltapdetail.getIdnumber()); //证件号码
            complaintform.setUsertype(ywcltapdetail.getProvidertype()); //提供方类型
            complaintform.setAge(ywcltapdetail.getPertypename()); //年龄段
            complaintform.setEmaiaddress(ywcltapdetail.getEmail()); //电子邮箱
            complaintform.setNationality(ywcltapdetail.getNationality()); //国籍
            complaintform.setNation(ywcltapdetail.getNation()); //民族
            complaintform.setStatus(ywcltapdetail.getPositionname()); //身份
            complaintform.setPostcode(ywcltapdetail.getPostalcode()); //邮政编码
            complaintform.setOthercontacts(ywcltapdetail.getOthercontactmethods()); //其他联系方式

            //被举报对象
            complaintform.setSubjectname(ywcltapdetail.getSubjectname()); //被举报对象名称
            complaintform.setSubjectaddress(ywcltapdetail.getAddr()); //被举报对象地址
            complaintform.setProvidetype(ywcltapdetail.getEnttypename()); //市场主体类型
            complaintform.setProvidetypecodeid(ywcltapdetail.getEnttype()); //类型编号
            complaintform.setEntitytype(ywcltapdetail.getEnttypename()); //市场主体类型
            //市场主体类型codeid 转换成12345对应的codeid
            entitytypecodeid = createEntityInfoUtil.iBasecodeContrastService.getFsCodeByCountryCodeAndType(ywcltapdetail.getEnttype(), "enterprise");
            if (StringUtils.isBlank(entitytypecodeid)) {
                entitytypecodeid = ywcltapdetail.getEnttype();
            }
            complaintform.setEntitytypecodeid(entitytypecodeid); //市场主体类型codeid
            complaintform.setIndustryname(ywcltapdetail.getIndustrycategory()); //行业分类
            industrynamecodeid = createEntityInfoUtil.iBasecodeContrastService.getFsCodeByCountryNameAndType(ywcltapdetail.getIndustrycategory(), "industry");
            complaintform.setIndustrynamecodeid(industrynamecodeid);
            complaintform.setSubjectphone(ywcltapdetail.getLinknumber()); //联系电话
            complaintform.setRegisterno(ywcltapdetail.getSocialcode()); //社会信用代码
            complaintform.setReportedcontact(ywcltapdetail.getLinkman()); //联系人

            //业务信息
            complaintform.setObjclassify(ywcltapdetail.getObjtype()); //客体类别
            objectcategorycodeid = createEntityInfoUtil.iBasecodeContrastService.getFsCodeByCountryNameAndType(ywcltapdetail.getObjtype(), "object");
            complaintform.setObjclassifycodeid(objectcategorycodeid); //客体类别codeid
            complaintform.setObjname(ywcltapdetail.getObjname()); //商品/服务名称
            complaintform.setBrand(ywcltapdetail.getBrand()); //品牌
            complaintform.setShoppingmode(ywcltapdetail.getSalemode()); //销售方式
            complaintform.setObjmoney(new BigDecimal(ywcltapdetail.getMonetary())); //涉及金额
            complaintform.setOrdernumber(ywcltapdetail.getBidno()); //订单号
            complaintform.setComplaintstype(ywcltapdetail.getApplbasquename()); //投诉问题类别
            complaintstypecodeid = createEntityInfoUtil.iBasecodeContrastService.getFsCodeByCountryNameAndType(ywcltapdetail.getApplbasquename(), "question");
            complaintform.setComplaintstypecodeid(complaintstypecodeid); // //投诉问题类别codeid
            complaintform.setProductname(ywcltapdetail.getProductname()); //产品名称
            complaintform.setApprovalnumber(ywcltapdetail.getApprovalnumber()); //批准文号
            complaintform.setLotnumber(ywcltapdetail.getLotnumber()); //产品批号
            complaintform.setTypespecification(ywcltapdetail.getProductstandard()); //产品规格
            complaintform.setProducecompany(ywcltapdetail.getManufacturer()); //生产厂家
            complaintform.setSalesenterprise(ywcltapdetail.getMarketingcompany()); //销售企业
            complaintform.setExpirationdate(ywcltapdetail.getExpirationdate()); //产品有效期
            complaintform.setKeyword(ywcltapdetail.getKeyword()); //关键字
            if (StringUtils.isNotBlank(ywcltapdetail.getReportdeviation())) {
                complaintform.setProblemdescribe(ywcltapdetail.getReportdeviation().replace("</br>", "\r\n")); //举报内容
            }
            complaintform.setUpdatedate(new Date()); //更新时间
            complaintform.setDisputedate(ywcltapdetail.getControversytime());//争议发生时间
            complaintform.setReport(ywcltapdetail.getAppealcontent()); //诉求内容
            complaintform.setAccsce(ywcltapdetail.getAccsce()); //事发地

            complaintform.setFeedbacktime(ywcltapdetail.getFeedbacktime());
            complaintform.setTodealwithtime(ywcltapdetail.getTodealwithtime());
        }
        return complaintform;
    }

    /**
     * 12315 附件转本地附件
     *
     * @param accessory
     * @return
     */
    public static Materialinfo createMaterialinfoByYwclaccessory(Ywclaccessory accessory) {
        Materialinfo materialinfo = null;
        if (accessory != null) {
            materialinfo = new Materialinfo();
            materialinfo.setId(accessory.getId());
            materialinfo.setMaterialtype(accessory.getBiztype());
            materialinfo.setAmount(new BigDecimal(1));
            String localAddress = accessory.getLocaladdress();
            int index = localAddress.indexOf("download");
            if (-1 != index) {
                localAddress = localAddress.substring(index + 8);
            }
            materialinfo.setFilesavepath(localAddress);
            materialinfo.setStatus("1");
            materialinfo.setUpdatedate(accessory.getUpdatedate());
            materialinfo.setAttachfilename(accessory.getFname());
            materialinfo.setUploadtime(accessory.getTimestr());
            materialinfo.setBackfillState("source");
        }
        return materialinfo;
    }

    public static Ywclfeedbackform createYwclfeedbackformByYwclfeedback(Ywclfeedback ywclfeedback) {
        Ywclfeedbackform ywclfeedbackform = null;
        if (ywclfeedback != null) {
            ywclfeedbackform = new Ywclfeedbackform();
            ywclfeedbackform.setId(ywclfeedback.getId());
            ywclfeedbackform.setCaseid(ywclfeedback.getCaseid());
            ywclfeedbackform.setBusiness(ywclfeedback.getBusiness());
            ywclfeedbackform.setContent(ywclfeedback.getContent());
            ywclfeedbackform.setFeedbacktime(ywclfeedback.getFeedbacktime());
            ywclfeedbackform.setOperationchoice(ywclfeedback.getOperationchoice());
            ywclfeedbackform.setOperationstaff(ywclfeedback.getOperationstaff());
            ywclfeedbackform.setRecord(ywclfeedback.getRecord());
            ywclfeedbackform.setUpdatedate(ywclfeedback.getUpdatedate());
        }
        return ywclfeedbackform;
    }

    public static Ywclflowform createYwclflowformByYwclflow(Ywclflow ywclflow) {
        Ywclflowform ywclflowform = null;
        if (ywclflow != null) {
            ywclflowform = new Ywclflowform();
            ywclflowform.setId(ywclflow.getId());
            ywclflowform.setCaseid(ywclflow.getCaseid());
            ywclflowform.setKeyid(ywclflow.getKeyid());
            ywclflowform.setParentid(ywclflow.getParentid());
            ywclflowform.setFldjr(ywclflow.getFldjr());
            ywclflowform.setFldjrname(ywclflow.getFldjrname());
            ywclflowform.setZcsj(ywclflow.getZcsj());
            ywclflowform.setFllxname(ywclflow.getFllxname());
            ywclflowform.setZcfy(ywclflow.getZcfy());
            ywclflowform.setZxunitname(ywclflow.getZxunitname());
            ywclflowform.setZxbmname(ywclflow.getZxbmname());
            ywclflowform.setJsbmname(ywclflow.getJsbmname());
            ywclflowform.setJsunitname(ywclflow.getJsunitname());
            ywclflowform.setRecord(ywclflow.getRecord());
            ywclflowform.setUpdatedate(ywclflow.getUpdatedate());
        }
        return ywclflowform;
    }
}
