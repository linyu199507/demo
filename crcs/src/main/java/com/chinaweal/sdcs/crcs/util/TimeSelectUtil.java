package com.chinaweal.sdcs.crcs.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chinaweal.sdcs.crcs.system.entity.*;
import com.chinaweal.sdcs.crcs.system.mapper.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

/**
 * @param
 * @author mo sheng xin
 * @since
 **/
@Component
public class TimeSelectUtil {
    private static TimeSelectUtil timeSelectUtil;
    @Resource
    private ComplaintSituationMapper complaintSituationMapper;
    @Resource
    private BasecodeformMapper basecodeformMapper;
    @Resource
    private StatisticsDataMapper statisticsDataMapper;
    @Resource
    private OverallSituationMapper overallSituationMapper;
    @Resource
    private EconomySituationMapper economySituationMapper;
    @PostConstruct
    public void init(){
        timeSelectUtil=this;
    }

    public   List<ComplaintSituation> getSortList(List<ComplaintSituation> list1,List<ComplaintSituation> list2)  {
        List<ComplaintSituation> newList = new ArrayList<>();
        for (ComplaintSituation listOne : list1){
            for (ComplaintSituation listTwo : list2){
                if (listOne.getBustype().equals(listTwo.getBustype())){
                    newList.add(listTwo);
                    continue ;
                }
            }
        }
        return newList;
    }
    public   List<OverallSituation> getSortList1(List<OverallSituation> list1,List<OverallSituation> list2)  {
        List<OverallSituation> newList1 = new ArrayList<>();
        for (OverallSituation listOne : list1){
            for (OverallSituation listTwo : list2){
                if (listOne.getRegion().equals(listTwo.getRegion())){
                    newList1.add(listTwo);
                    continue ;
                }
            }
        }
        return newList1;
    }
    public class MyComparator implements Comparator<StatisticsDate>{
        @Override
        public int compare(StatisticsDate o1, StatisticsDate o2) {
            return o2.getRegiontotal()-o1.getRegiontotal();
        }
    }

    //被投诉热点商圈TOP10
    public static Map<String,Object> getBusinesscircle(Map<String,Object> requestMap ){

        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        //获取时间选择器对应的同比时间
        Map<String, Object> begindate = DateUtil.getTbDate(requestMap.get("begindate").toString());
        Map<String, Object> enddate = DateUtil.getTbDate(requestMap.get("enddate").toString());

        requestMap.put("begindate", requestMap.get("begindate"));
        requestMap.put("enddate", requestMap.get("enddate"));
//        listOne = complaintSituationMapper.selectbusinesscircleByMap(requestMap);
        listOne=timeSelectUtil.complaintSituationMapper.selectbusinesscircleByMap(requestMap);
        if(listOne.size()>=10){
            listOne = listOne.subList(0,10);
        }

        for( ComplaintSituation data : listOne ){
            requestMap.put("begindate", begindate.get("lastdate"));
            requestMap.put("enddate", enddate.get("lastdate"));
            requestMap.put("type",data.getBustype());
            ComplaintSituation dataOne = timeSelectUtil.complaintSituationMapper.selectbusinesscircleOneByMap(requestMap);
            if( dataOne == null ){
                dataOne = new ComplaintSituation();
                dataOne.setBustype(data.getBustype());
                dataOne.setOveralltotal(0);
                listTwo.add(dataOne);
            }else{
                listTwo.add(dataOne);
            }
        }
        map.put("this",listOne);
        map.put("last",listTwo);
        return map;
    }
    //被投诉主体TOP10
    public static Map<String,Object> getSubjectTen(Map<String,Object> requestMap){
        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        //获取时间选择器对应的同比时间
        Map<String, Object> begindate = DateUtil.getTbDate(requestMap.get("begindate").toString());
        Map<String, Object> enddate = DateUtil.getTbDate(requestMap.get("enddate").toString());
        requestMap.put("begindate", requestMap.get("begindate"));
        requestMap.put("enddate", requestMap.get("enddate"));
        listOne = timeSelectUtil.complaintSituationMapper.selectsubjectTenByMap(requestMap);
        listOne = listOne.subList(0,10);

        for( ComplaintSituation data : listOne ){
            requestMap.put("begindate", begindate.get("lastdate"));
            requestMap.put("enddate", enddate.get("lastdate"));
            requestMap.put("type",data.getBustype());
            ComplaintSituation dataOne = timeSelectUtil.complaintSituationMapper.selectsubjectOneByMap(requestMap);
            if( dataOne == null ){
                dataOne = new ComplaintSituation();
                dataOne.setBustype(data.getBustype());
                dataOne.setOveralltotal(0);
                listTwo.add(dataOne);
            }else{
                listTwo.add(dataOne);
            }
        }

        map.put("this",listOne);
        map.put("last",listTwo);
        return map;
    }
    public static Map<String,Object> getAppliancesBrand(Map<String,Object> requestMap){
        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        //获取时间选择器对应的同比时间
        Map<String, Object> begindate = DateUtil.getTbDate(requestMap.get("begindate").toString());
        Map<String, Object> enddate = DateUtil.getTbDate(requestMap.get("enddate").toString());
        requestMap.put("begindate",requestMap.get("begindate"));
        requestMap.put("enddate",requestMap.get("enddate"));
        List<ComplaintSituation> listOne = timeSelectUtil.complaintSituationMapper.selectappliancesBrandByMap(requestMap);
        listOne = listOne.subList(0,10);

        for( ComplaintSituation data : listOne ){
            requestMap.put("begindate",begindate.get("lastdate"));
            requestMap.put("enddate",enddate.get("lastdate"));
            requestMap.put("type",data.getBustype());
            ComplaintSituation dataOne = timeSelectUtil.complaintSituationMapper.selectBrandByMap(requestMap);
            if( dataOne == null ){
                dataOne = new ComplaintSituation();
                dataOne.setBustype(data.getBustype());
                dataOne.setOveralltotal(0);
                listTwo.add(dataOne);
            }else{
                listTwo.add(dataOne);
            }
        }
        map.put("this",listOne);
        map.put("last",listTwo);
        return map;
    }
    public static Map<String,Object> getAppliancesNature(Map<String,Object> requestMap) {
        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        //获取时间选择器对应的同比时间
        Map<String, Object> begindate = DateUtil.getTbDate(requestMap.get("begindate").toString());
        Map<String, Object> enddate = DateUtil.getTbDate(requestMap.get("enddate").toString());
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "question12345").eq(Basecodeform::getOrglevel,"1");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms =timeSelectUtil.basecodeformMapper.selectList(queryWrapper);
        Map<String,Object> thisyear = DateUtil.getThisYear();
        Map<String,Object> lastyear = DateUtil.getLastYear();
        for ( Basecodeform basecodeform : basecodeforms) {
            String codeid = basecodeform.getCodeid();
            requestMap.put("type", codeid);
            requestMap.put("begindate",requestMap.get("begindate"));
            requestMap.put("enddate",requestMap.get("enddate"));
            ComplaintSituation dataOne =timeSelectUtil.complaintSituationMapper.selectappliancesNatureByMap(requestMap);
            if (dataOne != null){
                dataOne.setBustype(basecodeform.getComment());
            }else{
                dataOne = new ComplaintSituation();
                dataOne.setBustype(basecodeform.getComment());
                dataOne.setOveralltotal(0);
                dataOne.setEndtotal(0);
                dataOne.setToeconomytotal(0);
            }
            listOne.add(dataOne);
            listOne.sort(Comparator.comparingInt(ComplaintSituation::getOveralltotal).reversed());
            if(requestMap.get("begindate")!=null || !"".equals(requestMap.get("begindate"))){
                requestMap.put("begindate",begindate.get("lastdate"));
                requestMap.put("enddate",enddate.get("lastdate"));
            }else{
                requestMap.put("begindate", lastyear.get("start"));
                requestMap.put("enddate", lastyear.get("end"));
            }
            ComplaintSituation dataTwo = timeSelectUtil.complaintSituationMapper.selectappliancesNatureByMap(requestMap);
            if (dataTwo != null){
                dataTwo.setBustype(basecodeform.getComment());
            }else{
                dataTwo = new ComplaintSituation();
                dataTwo.setBustype(basecodeform.getComment());
                dataTwo.setOveralltotal(0);
                dataTwo.setEndtotal(0);
                dataTwo.setToeconomytotal(0);
            }
            listTwo.add(dataTwo);
            listTwo =timeSelectUtil.getSortList(listOne,listTwo);
        }
        map.put("this",listOne);
        map.put("last",listTwo);
        return map;
    }

    public static Map<String,Object> getDailyCategory(Map<String,Object> requestMap){
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> requestMapOne = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        //获取时间选择器对应的同比时间
        Map<String, Object> begindate = DateUtil.getTbDate(requestMap.get("begindate").toString());
        Map<String, Object> enddate = DateUtil.getTbDate(requestMap.get("enddate").toString());
        requestMap.put("begindate",requestMap.get("begindate"));
        requestMap.put("enddate",requestMap.get("enddate"));

        requestMapOne.put("begindate",begindate.get("lastdate"));
        requestMapOne.put("enddate",enddate.get("lastdate"));

        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "object12345").eq(Basecodeform::getOrglevel,"3").likeRight(Basecodeform::getCodeid,"0104%");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = timeSelectUtil.basecodeformMapper.selectList(queryWrapper);

        for ( Basecodeform basecodeform : basecodeforms) {
            String codeid = basecodeform.getCodeid();
            if ("家具".equals(basecodeform.getComment())){
                continue ;
            }
            requestMap.put("type", codeid);
            requestMapOne.put("type", codeid);
            ComplaintSituation dataOne = timeSelectUtil.complaintSituationMapper.selectdailyCategoryByMap(requestMap);
            ComplaintSituation dataTwo = timeSelectUtil.complaintSituationMapper.selectdailyCategoryByMap(requestMapOne);
            if( dataOne == null ){
                dataOne = new ComplaintSituation();
                dataOne.setBustype(basecodeform.getComment());
                dataOne.setOveralltotal(0);
                dataOne.setEndtotal(0);
                listOne.add(dataOne);
            }else{
                dataOne.setBustype(basecodeform.getComment());
                listOne.add(dataOne);
            }
            if( dataTwo == null ){
                dataTwo = new ComplaintSituation();
                dataTwo.setBustype(basecodeform.getComment());
                dataTwo.setOveralltotal(0);
                dataTwo.setEndtotal(0);
                listTwo.add(dataTwo);
            }else{
                dataTwo.setBustype(basecodeform.getComment());
                listTwo.add(dataTwo);
            }
        }

        QueryWrapper<Basecodeform> queryWrapperOne = new QueryWrapper<>();
        queryWrapperOne.lambda().like(Basecodeform::getType, "object12345").eq(Basecodeform::getOrglevel,"4").likeRight(Basecodeform::getCodeid,"010401%");
        queryWrapperOne.orderByAsc("codeid");
        List<Basecodeform> basecodeformsOne = timeSelectUtil.basecodeformMapper.selectList(queryWrapperOne);
        for ( Basecodeform BasecodeformOne : basecodeformsOne) {
            String codeid = BasecodeformOne.getCodeid();
            requestMap.put("type", codeid);
            requestMapOne.put("type", codeid);
            ComplaintSituation dataOne = timeSelectUtil.complaintSituationMapper.selectdailyCategoryByMap(requestMap);
            ComplaintSituation dataTwo = timeSelectUtil.complaintSituationMapper.selectdailyCategoryByMap(requestMapOne);
            if( dataOne == null ){
                dataOne = new ComplaintSituation();
                dataOne.setBustype(BasecodeformOne.getComment());
                dataOne.setOveralltotal(0);
                dataOne.setEndtotal(0);
                listOne.add(dataOne);
            }else{
                dataOne.setBustype(BasecodeformOne.getComment());
                listOne.add(dataOne);
            }
            if( dataTwo == null ){
                dataTwo = new ComplaintSituation();
                dataTwo.setBustype(BasecodeformOne.getComment());
                dataTwo.setOveralltotal(0);
                dataTwo.setEndtotal(0);
                listTwo.add(dataTwo);
            }else{
                dataTwo.setBustype(BasecodeformOne.getComment());
                listTwo.add(dataTwo);
            }
        }
//        listOne.addAll(listTwo);
        listOne.sort(Comparator.comparingInt(ComplaintSituation::getOveralltotal).reversed());
        listTwo.sort(Comparator.comparingInt(ComplaintSituation::getOveralltotal).reversed());
        map.put("this",listOne);
        map.put("last",listTwo);
        return map;
    }
    public static Map<String,Object> getDailyNature(Map<String,Object> requestMap){
        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "question12345").eq(Basecodeform::getOrglevel,"1");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = timeSelectUtil.basecodeformMapper.selectList(queryWrapper);
        //获取时间选择器对应的同比时间
        Map<String, Object> begindate = DateUtil.getTbDate(requestMap.get("begindate").toString());
        Map<String, Object> enddate = DateUtil.getTbDate(requestMap.get("enddate").toString());
        for ( Basecodeform basecodeform : basecodeforms) {
            String codeid = basecodeform.getCodeid();
            requestMap.put("type", codeid);
            requestMap.put("begindate",requestMap.get("begindate"));
            requestMap.put("enddate",requestMap.get("enddate"));
            ComplaintSituation dataOne = timeSelectUtil.complaintSituationMapper.selectDailyNatureByMap(requestMap);
            if (dataOne != null){
                dataOne.setBustype(basecodeform.getComment());
            }else{
                dataOne = new ComplaintSituation();
                dataOne.setBustype(basecodeform.getComment());
                dataOne.setOveralltotal(0);
                dataOne.setEndtotal(0);
                dataOne.setToeconomytotal(0);
            }
            listOne.add(dataOne);
            listOne.sort(Comparator.comparingInt(ComplaintSituation::getOveralltotal).reversed());
            requestMap.put("begindate",begindate.get("lastdate"));
            requestMap.put("enddate",enddate.get("lastdate"));
            ComplaintSituation dataTwo = timeSelectUtil.complaintSituationMapper.selectDailyNatureByMap(requestMap);
            if (dataTwo != null){
                dataTwo.setBustype(basecodeform.getComment());
            }else{
                dataTwo = new ComplaintSituation();
                dataTwo.setBustype(basecodeform.getComment());
                dataTwo.setOveralltotal(0);
                dataTwo.setEndtotal(0);
                dataTwo.setToeconomytotal(0);
            }
            listTwo.add(dataTwo);
            listTwo = timeSelectUtil.getSortList(listOne,listTwo);
            map.put("this",listOne);
            map.put("last",listTwo);

        }
        return map;
    }
    public static Map<String,Object> getDailyBrand(Map<String,Object> requestMap){
        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        //获取同比时间
        Map<String, Object> begindate = DateUtil.getTbDate(requestMap.get("begindate").toString());
        Map<String, Object> enddate = DateUtil.getTbDate(requestMap.get("enddate").toString());
        requestMap.put("begindate",requestMap.get("begindate"));
        requestMap.put("enddate",requestMap.get("enddatedate"));
        List<ComplaintSituation> listOne = timeSelectUtil.complaintSituationMapper.selectDailyBrandByMap(requestMap);
        listOne = listOne.subList(0,10);

        for( ComplaintSituation data : listOne ){
            requestMap.put("begindate",begindate.get("lastdate"));
            requestMap.put("enddate",enddate.get("lastdate"));
            requestMap.put("type",data.getBustype());
            ComplaintSituation dataOne = timeSelectUtil.complaintSituationMapper.selectDailyBrandOneByMap(requestMap);
            if( dataOne == null ){
                dataOne = new ComplaintSituation();
                dataOne.setBustype(data.getBustype());
                dataOne.setOveralltotal(0);
                listTwo.add(dataOne);
            }else{
                listTwo.add(dataOne);
            }
        }
        map.put("this",listOne);
        map.put("last",listTwo);
        return map;
    }
    public static Map<String,Object> getAppliancesCategory(Map<String,Object> requestMap){
        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        //获取时间选择器对应的同比时间
        Map<String, Object> begindate = DateUtil.getTbDate(requestMap.get("begindate").toString());
        Map<String, Object> enddate = DateUtil.getTbDate(requestMap.get("enddate").toString());
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "object12345").eq(Basecodeform::getOrglevel,"3").likeRight(Basecodeform::getCodeid,"0101%");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = timeSelectUtil.basecodeformMapper.selectList(queryWrapper);
        for (Basecodeform basecodeform : basecodeforms) {
            String codeid = basecodeform.getCodeid();
            requestMap.put("begindate", requestMap.get("begindate"));
            requestMap.put("enddate", requestMap.get("enddate"));
            requestMap.put("type", codeid);
            ComplaintSituation dataOne = timeSelectUtil.complaintSituationMapper.selectappliancesCategoryByMap(requestMap);
            if (dataOne != null) {
                dataOne.setBustype(basecodeform.getComment());
            } else {
                dataOne = new ComplaintSituation();
                dataOne.setBustype(basecodeform.getComment());
                dataOne.setOveralltotal(0);
                dataOne.setEndtotal(0);
                dataOne.setToeconomytotal(0);
            }
            listOne.add(dataOne);
            listOne.sort(Comparator.comparingInt(ComplaintSituation::getOveralltotal).reversed());
            requestMap.put("begindate", begindate.get("lastdate"));
            requestMap.put("enddate",enddate.get("lastdate"));
            ComplaintSituation dataTwo = timeSelectUtil.complaintSituationMapper.selectappliancesCategoryByMap(requestMap);
            if (dataTwo != null) {
                dataTwo.setBustype(basecodeform.getComment());
            } else {
                dataTwo = new ComplaintSituation();
                dataTwo.setBustype(basecodeform.getComment());
                dataTwo.setOveralltotal(0);
                dataTwo.setEndtotal(0);
                dataTwo.setToeconomytotal(0);
            }
            listTwo.add(dataTwo);
            listTwo = timeSelectUtil.getSortList(listOne,listTwo);
            map.put("this",listOne);
            map.put("last",listTwo);
        }
        return map;
    }
    public static Map<String,Object> getDecoration(Map<String,Object> requestMap){
        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "object12345").eq(Basecodeform::getOrglevel,"3").likeRight(Basecodeform::getCodeid,"0108%");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = timeSelectUtil.basecodeformMapper.selectList(queryWrapper);
        //获取时间选择器对应的同比时间
        Map<String, Object> begindate = DateUtil.getTbDate(requestMap.get("begindate").toString());
        Map<String, Object> enddate = DateUtil.getTbDate(requestMap.get("enddate").toString());
        for ( Basecodeform basecodeform : basecodeforms) {
            String codeid = basecodeform.getCodeid();
            requestMap.put("type", codeid);
            requestMap.put("begindate", requestMap.get("begindate"));
            requestMap.put("enddate", requestMap.get("enddate"));
            ComplaintSituation dataOne = timeSelectUtil.complaintSituationMapper.selectdecorationByMap(requestMap);
            if (dataOne == null) {
                dataOne = new ComplaintSituation();
                dataOne.setBustype(basecodeform.getComment());
                dataOne.setOveralltotal(0);
                dataOne.setEndtotal(0);
                dataOne.setRegion(basecodeform.getCodeid());
                listOne.add(dataOne);
            } else {
                dataOne.setBustype(basecodeform.getComment());
                dataOne.setRegion(basecodeform.getCodeid());
                listOne.add(dataOne);
            }
        }
        listOne.sort(Comparator.comparingInt(ComplaintSituation::getOveralltotal).reversed());

        for( ComplaintSituation complaint : listOne){
            requestMap.put("type", complaint.getRegion());
            requestMap.put("begindate", begindate.get("lastdate"));
            requestMap.put("enddate", enddate.get("lastdate"));
            ComplaintSituation dataTwo = timeSelectUtil.complaintSituationMapper.selectdecorationByMap(requestMap);
            if (dataTwo == null) {
                dataTwo = new ComplaintSituation();
                dataTwo.setBustype(complaint.getBustype());
                dataTwo.setOveralltotal(0);
                dataTwo.setEndtotal(0);
                dataTwo.setRegion(complaint.getRegion());
                listTwo.add(dataTwo);
            } else {
                dataTwo.setBustype(complaint.getBustype());
                dataTwo.setRegion(complaint.getRegion());
                listTwo.add(dataTwo);
            }
        }

        map.put("this",listOne);
        map.put("last",listTwo);
        return map;
    }
        public static Map<String,Object> getHousing(Map<String,Object> requestMap){
            Map<String,Object> map = new HashMap<>();
            List<ComplaintSituation> listOne = new ArrayList<>();
            List<ComplaintSituation> listTwo = new ArrayList<>();
            QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().like(Basecodeform::getType, "object12345").eq(Basecodeform::getOrglevel,"3").likeRight(Basecodeform::getCodeid,"0110%");
            queryWrapper.orderByAsc("codeid");
            List<Basecodeform> basecodeforms = timeSelectUtil.basecodeformMapper.selectList(queryWrapper);
            //获取时间选择器对应的同比时间
            Map<String, Object> begindate = DateUtil.getTbDate(requestMap.get("begindate").toString());
            Map<String, Object> enddate = DateUtil.getTbDate(requestMap.get("enddate").toString());
            for ( Basecodeform basecodeform : basecodeforms) {
                String codeid = basecodeform.getCodeid();
                requestMap.put("type", codeid);
                requestMap.put("begindate", requestMap.get("begindate"));
                requestMap.put("enddate", requestMap.get("enddate"));
                ComplaintSituation dataOne = timeSelectUtil.complaintSituationMapper.selectdecorationByMap(requestMap);
                if (dataOne == null) {
                    dataOne = new ComplaintSituation();
                    dataOne.setBustype(basecodeform.getComment());
                    dataOne.setOveralltotal(0);
                    dataOne.setEndtotal(0);
                    dataOne.setRegion(basecodeform.getCodeid());
                    listOne.add(dataOne);
                } else {
                    dataOne.setBustype(basecodeform.getComment());
                    dataOne.setRegion(basecodeform.getCodeid());
                    listOne.add(dataOne);
                }
            }
            listOne.sort(Comparator.comparingInt(ComplaintSituation::getOveralltotal).reversed());

            for( ComplaintSituation complaint : listOne){
                requestMap.put("type", complaint.getRegion());
                requestMap.put("begindate", begindate.get("lastdate"));
                requestMap.put("enddate", enddate.get("enddate"));
                ComplaintSituation dataTwo = timeSelectUtil.complaintSituationMapper.selectdecorationByMap(requestMap);
                if (dataTwo == null) {
                    dataTwo = new ComplaintSituation();
                    dataTwo.setBustype(complaint.getBustype());
                    dataTwo.setOveralltotal(0);
                    dataTwo.setEndtotal(0);
                    dataTwo.setRegion(complaint.getRegion());
                    listTwo.add(dataTwo);
                } else {
                    dataTwo.setBustype(complaint.getBustype());
                    dataTwo.setRegion(complaint.getRegion());
                    listTwo.add(dataTwo);
                }
            }

            map.put("this",listOne);
            map.put("last",listTwo);
            return map;
        }
    public static Map<String,Object> getFood(Map<String,Object> requestMap){
        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "object12345").eq(Basecodeform::getOrglevel,"3").likeRight(Basecodeform::getCodeid,"0105%");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = timeSelectUtil.basecodeformMapper.selectList(queryWrapper);
        //获取时间选择器对应的同比时间
        Map<String, Object> begindate = DateUtil.getTbDate(requestMap.get("begindate").toString());
        Map<String, Object> enddate = DateUtil.getTbDate(requestMap.get("enddate").toString());
        for ( Basecodeform basecodeform : basecodeforms) {
            String codeid = basecodeform.getCodeid();
            requestMap.put("type", codeid);
            requestMap.put("begindate", requestMap.get("begindate"));
            requestMap.put("enddate", requestMap.get("enddate"));
            ComplaintSituation dataOne = timeSelectUtil.complaintSituationMapper.selectdecorationByMap(requestMap);
            if (dataOne == null) {
                dataOne = new ComplaintSituation();
                dataOne.setBustype(basecodeform.getComment());
                dataOne.setOveralltotal(0);
                dataOne.setEndtotal(0);
                dataOne.setRegion(basecodeform.getCodeid());
                listOne.add(dataOne);
            } else {
                dataOne.setBustype(basecodeform.getComment());
                dataOne.setRegion(basecodeform.getCodeid());
                listOne.add(dataOne);
            }
        }
        listOne.sort(Comparator.comparingInt(ComplaintSituation::getOveralltotal).reversed());
        listOne = listOne.subList(0,10);

        for( ComplaintSituation complaint : listOne){
            requestMap.put("type", complaint.getRegion());
            requestMap.put("begindate", begindate.get("lastdate"));
            requestMap.put("enddate", enddate.get("lastdate"));
            ComplaintSituation dataTwo = timeSelectUtil.complaintSituationMapper.selectdecorationByMap(requestMap);
            if (dataTwo == null) {
                dataTwo = new ComplaintSituation();
                dataTwo.setBustype(complaint.getBustype());
                dataTwo.setOveralltotal(0);
                dataTwo.setEndtotal(0);
                dataTwo.setRegion(complaint.getRegion());
                listTwo.add(dataTwo);
            } else {
                dataTwo.setBustype(complaint.getBustype());
                dataTwo.setRegion(complaint.getRegion());
                listTwo.add(dataTwo);
            }
        }

        map.put("this",listOne);
        map.put("last",listTwo);
        return map;
    }
    public static Map<String,Object> getFoodLinkData(Map<String,Object> requestMap){
        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "industry12345").eq(Basecodeform::getOrglevel,"1");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = timeSelectUtil.basecodeformMapper.selectList(queryWrapper);
        //获取时间选择器对应的同比时间
        Map<String, Object> begindate = DateUtil.getTbDate(requestMap.get("begindate").toString());
        Map<String, Object> enddate = DateUtil.getTbDate(requestMap.get("enddate").toString());
        for ( Basecodeform basecodeform : basecodeforms) {
            if ("01".equals(basecodeform.getCodeid()) || "02".equals(basecodeform.getCodeid()) || "03".equals(basecodeform.getCodeid()) || "05".equals(basecodeform.getCodeid())) {
                String codeid = basecodeform.getCodeid();
                requestMap.put("type", codeid);
                requestMap.put("begindate", requestMap.get("begindate"));
                requestMap.put("enddate", requestMap.get("enddate"));
                ComplaintSituation dataOne = timeSelectUtil.complaintSituationMapper.selectfoodLinkByMap(requestMap);
                if (dataOne == null) {
                    dataOne = new ComplaintSituation();
                    dataOne.setBustype(basecodeform.getComment());
                    dataOne.setOveralltotal(0);
                    dataOne.setEndtotal(0);
                    dataOne.setRegion(basecodeform.getCodeid());
                    listOne.add(dataOne);
                } else {
                    dataOne.setBustype(basecodeform.getComment());
                    dataOne.setRegion(basecodeform.getCodeid());
                    listOne.add(dataOne);
                }
            }
        }
        listOne.sort(Comparator.comparingInt(ComplaintSituation::getOveralltotal).reversed());

        for( ComplaintSituation complaint : listOne){
            requestMap.put("type", complaint.getRegion());
            requestMap.put("begindate", begindate.get("lastdate"));
            requestMap.put("enddate", enddate.get("lastdate"));
            ComplaintSituation dataTwo = timeSelectUtil.complaintSituationMapper.selectfoodLinkByMap(requestMap);
            if (dataTwo == null) {
                dataTwo = new ComplaintSituation();
                dataTwo.setBustype(complaint.getBustype());
                dataTwo.setOveralltotal(0);
                dataTwo.setEndtotal(0);
                dataTwo.setRegion(complaint.getRegion());
                listTwo.add(dataTwo);
            } else {
                dataTwo.setBustype(complaint.getBustype());
                dataTwo.setRegion(complaint.getRegion());
                listTwo.add(dataTwo);
            }
        }

        map.put("this",listOne);
        map.put("last",listTwo);
        return map;
    }
    public static Map<String,Object> getMedicineLink(Map<String,Object> requestMap) {
        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "industry12345").eq(Basecodeform::getOrglevel,"1");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = timeSelectUtil.basecodeformMapper.selectList(queryWrapper);

        //获取时间选择器对应的同比时间
        Map<String, Object> begindate = DateUtil.getTbDate(requestMap.get("begindate").toString());
        Map<String, Object> enddate = DateUtil.getTbDate(requestMap.get("enddate").toString());
        for ( Basecodeform basecodeform : basecodeforms) {
            if ("01".equals(basecodeform.getCodeid()) || "02".equals(basecodeform.getCodeid()) || "03".equals(basecodeform.getCodeid()) || "05".equals(basecodeform.getCodeid())) {
                String codeid = basecodeform.getCodeid();
                requestMap.put("type", codeid);
                requestMap.put("begindate", requestMap.get("begindate"));
                requestMap.put("enddate", requestMap.get("enddate"));
                ComplaintSituation dataOne = timeSelectUtil.complaintSituationMapper.selectmedicineLinkByMap(requestMap);
                if (dataOne == null) {
                    dataOne = new ComplaintSituation();
                    dataOne.setBustype(basecodeform.getComment());
                    dataOne.setOveralltotal(0);
                    dataOne.setEndtotal(0);
                    dataOne.setRegion(basecodeform.getCodeid());
                    listOne.add(dataOne);
                } else {
                    dataOne.setBustype(basecodeform.getComment());
                    dataOne.setRegion(basecodeform.getCodeid());
                    listOne.add(dataOne);
                }
            }
        }
        listOne.sort(Comparator.comparingInt(ComplaintSituation::getOveralltotal).reversed());

        for( ComplaintSituation complaint : listOne){
            requestMap.put("type", complaint.getRegion());
            requestMap.put("begindate", begindate.get("lastdate"));
            requestMap.put("enddate", enddate.get("lastdate"));
            ComplaintSituation dataTwo = timeSelectUtil.complaintSituationMapper.selectmedicineLinkByMap(requestMap);
            if (dataTwo == null) {
                dataTwo = new ComplaintSituation();
                dataTwo.setBustype(complaint.getBustype());
                dataTwo.setOveralltotal(0);
                dataTwo.setEndtotal(0);
                dataTwo.setRegion(complaint.getRegion());
                listTwo.add(dataTwo);
            } else {
                dataTwo.setBustype(complaint.getBustype());
                dataTwo.setRegion(complaint.getRegion());
                listTwo.add(dataTwo);
            }
        }

        map.put("this",listOne);
        map.put("last",listTwo);
        return map;
    }
    public static Map<String,Object> getHealthfoodLink(Map<String,Object> requestMap){
        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "industry12345").eq(Basecodeform::getOrglevel,"1");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = timeSelectUtil.basecodeformMapper.selectList(queryWrapper);
        //获取时间选择器对应的同比时间
        Map<String, Object> begindate = DateUtil.getTbDate(requestMap.get("begindate").toString());
        Map<String, Object> enddate = DateUtil.getTbDate(requestMap.get("enddate").toString());
        for ( Basecodeform basecodeform : basecodeforms) {
            if ("01".equals(basecodeform.getCodeid()) || "02".equals(basecodeform.getCodeid()) || "03".equals(basecodeform.getCodeid()) || "05".equals(basecodeform.getCodeid())) {
                String codeid = basecodeform.getCodeid();
                requestMap.put("type", codeid);
                requestMap.put("begindate", requestMap.get("begindate"));
                requestMap.put("enddate", requestMap.get("enddate"));
                ComplaintSituation dataOne = timeSelectUtil.complaintSituationMapper.selecthealthfoodLinkByMap(requestMap);
                if (dataOne == null) {
                    dataOne = new ComplaintSituation();
                    dataOne.setBustype(basecodeform.getComment());
                    dataOne.setOveralltotal(0);
                    dataOne.setEndtotal(0);
                    dataOne.setRegion(basecodeform.getCodeid());
                    listOne.add(dataOne);
                } else {
                    dataOne.setBustype(basecodeform.getComment());
                    dataOne.setRegion(basecodeform.getCodeid());
                    listOne.add(dataOne);
                }
            }
        }
        listOne.sort(Comparator.comparingInt(ComplaintSituation::getOveralltotal).reversed());

        for( ComplaintSituation complaint : listOne){
            requestMap.put("type", complaint.getRegion());
            requestMap.put("begindate", begindate.get("lastdate"));
            requestMap.put("enddate", enddate.get("lastdate"));
            ComplaintSituation dataTwo = timeSelectUtil.complaintSituationMapper.selecthealthfoodLinkByMap(requestMap);
            if (dataTwo == null) {
                dataTwo = new ComplaintSituation();
                dataTwo.setBustype(complaint.getBustype());
                dataTwo.setOveralltotal(0);
                dataTwo.setEndtotal(0);
                dataTwo.setRegion(complaint.getRegion());
                listTwo.add(dataTwo);
            } else {
                dataTwo.setBustype(complaint.getBustype());
                dataTwo.setRegion(complaint.getRegion());
                listTwo.add(dataTwo);
            }
        }

        map.put("this",listOne);
        map.put("last",listTwo);
        return map;
    }
    public static Map<String,Object> getCosmeticsLink(Map<String,Object> requestMap){
        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "industry12345").eq(Basecodeform::getOrglevel,"1");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = timeSelectUtil.basecodeformMapper.selectList(queryWrapper);
        //获取时间选择器对应的同比时间
        Map<String, Object> begindate = DateUtil.getTbDate(requestMap.get("begindate").toString());
        Map<String, Object> enddate = DateUtil.getTbDate(requestMap.get("enddate").toString());
        for ( Basecodeform basecodeform : basecodeforms) {
            if ("01".equals(basecodeform.getCodeid()) || "02".equals(basecodeform.getCodeid()) || "03".equals(basecodeform.getCodeid()) || "05".equals(basecodeform.getCodeid())) {
                String codeid = basecodeform.getCodeid();
                requestMap.put("type", codeid);
                requestMap.put("begindate", requestMap.get("begindate"));
                requestMap.put("enddate", requestMap.get("enddate"));
                ComplaintSituation dataOne = timeSelectUtil.complaintSituationMapper.selectCosmeticsLinkByMap(requestMap);
                if (dataOne == null) {
                    dataOne = new ComplaintSituation();
                    dataOne.setBustype(basecodeform.getComment());
                    dataOne.setOveralltotal(0);
                    dataOne.setEndtotal(0);
                    dataOne.setRegion(basecodeform.getCodeid());
                    listOne.add(dataOne);
                } else {
                    dataOne.setBustype(basecodeform.getComment());
                    dataOne.setRegion(basecodeform.getCodeid());
                    listOne.add(dataOne);
                }
            }
        }
        listOne.sort(Comparator.comparingInt(ComplaintSituation::getOveralltotal).reversed());

        for( ComplaintSituation complaint : listOne){
            requestMap.put("type", complaint.getRegion());
            requestMap.put("begindate", begindate.get("lastdate"));
            requestMap.put("enddate", enddate.get("lastdate"));
            ComplaintSituation dataTwo = timeSelectUtil.complaintSituationMapper.selectCosmeticsLinkByMap(requestMap);
            if (dataTwo == null) {
                dataTwo = new ComplaintSituation();
                dataTwo.setBustype(complaint.getBustype());
                dataTwo.setOveralltotal(0);
                dataTwo.setEndtotal(0);
                dataTwo.setRegion(complaint.getRegion());
                listTwo.add(dataTwo);
            } else {
                dataTwo.setBustype(complaint.getBustype());
                dataTwo.setRegion(complaint.getRegion());
                listTwo.add(dataTwo);
            }
        }

        map.put("this",listOne);
        map.put("last",listTwo);
        return map;
    }
    public static Map<String,Object> getMedicalLink(Map<String,Object> requestMap){
        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "industry12345").eq(Basecodeform::getOrglevel,"1");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = timeSelectUtil.basecodeformMapper.selectList(queryWrapper);
        //获取时间选择器对应的同比时间
        Map<String, Object> begindate = DateUtil.getTbDate(requestMap.get("begindate").toString());
        Map<String, Object> enddate = DateUtil.getTbDate(requestMap.get("enddate").toString());
        for ( Basecodeform basecodeform : basecodeforms) {
            if ("01".equals(basecodeform.getCodeid()) || "02".equals(basecodeform.getCodeid()) || "03".equals(basecodeform.getCodeid()) || "05".equals(basecodeform.getCodeid())) {
                //if ("02".equals(basecodeform.getCodeid())) {
                String codeid = basecodeform.getCodeid();
                requestMap.put("type", codeid);
                requestMap.put("begindate", requestMap.get("begindate"));
                requestMap.put("enddate", requestMap.get("enddate"));
                ComplaintSituation dataOne = timeSelectUtil.complaintSituationMapper.selectMedicalLinkByMap(requestMap);
                if (dataOne == null) {
                    dataOne = new ComplaintSituation();
                    dataOne.setBustype(basecodeform.getComment());
                    dataOne.setOveralltotal(0);
                    dataOne.setEndtotal(0);
                    dataOne.setRegion(basecodeform.getCodeid());
                    listOne.add(dataOne);
                } else {
                    dataOne.setBustype(basecodeform.getComment());
                    dataOne.setRegion(basecodeform.getCodeid());
                    listOne.add(dataOne);
                }
            }
        }
        listOne.sort(Comparator.comparingInt(ComplaintSituation::getOveralltotal).reversed());

        for( ComplaintSituation complaint : listOne){
            requestMap.put("type", complaint.getRegion());
            requestMap.put("begindate", begindate.get("lastdate"));
            requestMap.put("enddate", enddate.get("lastdate"));
            ComplaintSituation dataTwo = timeSelectUtil.complaintSituationMapper.selectMedicalLinkByMap(requestMap);
            if (dataTwo == null) {
                dataTwo = new ComplaintSituation();
                dataTwo.setBustype(complaint.getBustype());
                dataTwo.setOveralltotal(0);
                dataTwo.setEndtotal(0);
                dataTwo.setRegion(complaint.getRegion());
                listTwo.add(dataTwo);
            } else {
                dataTwo.setBustype(complaint.getBustype());
                dataTwo.setRegion(complaint.getRegion());
                listTwo.add(dataTwo);
            }
        }

        map.put("this",listOne);
        map.put("last",listTwo);
        return map;
    }
    public static Map<String,Object> getTrafficCategory(Map<String,Object> requestMap){
        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "object12345").eq(Basecodeform::getOrglevel,"3").likeRight(Basecodeform::getCodeid,"0106%");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = timeSelectUtil.basecodeformMapper.selectList(queryWrapper);
        //获取时间选择器对应的同比时间
        Map<String, Object> begindate = DateUtil.getTbDate(requestMap.get("begindate").toString());
        Map<String, Object> enddate = DateUtil.getTbDate(requestMap.get("enddate").toString());
        for ( Basecodeform basecodeform : basecodeforms) {
            String codeid = basecodeform.getCodeid();
            requestMap.put("type", codeid);
            requestMap.put("begindate", requestMap.get("begindate"));
            requestMap.put("enddate", requestMap.get("enddate"));
            ComplaintSituation dataOne = timeSelectUtil.complaintSituationMapper.selectTrafficCategoryByMap(requestMap);
            if (dataOne == null) {
                dataOne = new ComplaintSituation();
                dataOne.setBustype(basecodeform.getComment());
                dataOne.setOveralltotal(0);
                dataOne.setEndtotal(0);
                dataOne.setRegion(basecodeform.getCodeid());
                listOne.add(dataOne);
            } else {
                dataOne.setBustype(basecodeform.getComment());
                dataOne.setRegion(basecodeform.getCodeid());
                listOne.add(dataOne);
            }
        }
        listOne.sort(Comparator.comparingInt(ComplaintSituation::getOveralltotal).reversed());

        for( ComplaintSituation complaint : listOne){
            requestMap.put("type", complaint.getRegion());
            requestMap.put("begindate", begindate.get("lastdate"));
            requestMap.put("enddate", enddate.get("lastdate"));
            ComplaintSituation dataTwo = timeSelectUtil.complaintSituationMapper.selectTrafficCategoryByMap(requestMap);
            if (dataTwo == null) {
                dataTwo = new ComplaintSituation();
                dataTwo.setBustype(complaint.getBustype());
                dataTwo.setOveralltotal(0);
                dataTwo.setEndtotal(0);
                dataTwo.setRegion(complaint.getRegion());
                listTwo.add(dataTwo);
            } else {
                dataTwo.setBustype(complaint.getBustype());
                dataTwo.setRegion(complaint.getRegion());
                listTwo.add(dataTwo);
            }
        }

        map.put("this",listOne);
        map.put("last",listTwo);
        return map;
    }
    public static Map<String,Object> getTrafficNature(Map<String,Object> requestMap){
        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "question12345").eq(Basecodeform::getOrglevel,"1");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = timeSelectUtil.basecodeformMapper.selectList(queryWrapper);
        //获取时间选择器对应的同比时间
        Map<String, Object> begindate = DateUtil.getTbDate(requestMap.get("begindate").toString());
        Map<String, Object> enddate = DateUtil.getTbDate(requestMap.get("enddate").toString());
        for ( Basecodeform basecodeform : basecodeforms) {
            String codeid = basecodeform.getCodeid();
            requestMap.put("type", codeid);
            requestMap.put("begindate",requestMap.get("begindate"));
            requestMap.put("enddate",requestMap.get("enddate"));
            ComplaintSituation dataOne = timeSelectUtil.complaintSituationMapper.selectTrafficNatureByMap(requestMap);
            if (dataOne != null){
                dataOne.setBustype(basecodeform.getComment());
            }else{
                dataOne = new ComplaintSituation();
                dataOne.setBustype(basecodeform.getComment());
                dataOne.setOveralltotal(0);
                dataOne.setEndtotal(0);
                dataOne.setToeconomytotal(0);
            }
            listOne.add(dataOne);

            requestMap.put("begindate",begindate.get("lastdate"));
            requestMap.put("enddate",enddate.get("lastdate"));
            ComplaintSituation dataTwo = timeSelectUtil.complaintSituationMapper.selectTrafficNatureByMap(requestMap);
            if (dataTwo != null){
                dataTwo.setBustype(basecodeform.getComment());
            }else{
                dataTwo = new ComplaintSituation();
                dataTwo.setBustype(basecodeform.getComment());
                dataTwo.setOveralltotal(0);
                dataTwo.setEndtotal(0);
                dataTwo.setToeconomytotal(0);
            }
            listTwo.add(dataTwo);
        }
        listOne.sort(Comparator.comparingInt(ComplaintSituation::getOveralltotal).reversed());
        listTwo = timeSelectUtil.getSortList(listOne,listTwo);
        map.put("this",listOne);
        map.put("last",listTwo);
        return map;
    }
    public static Map<String,Object> getTrafficBrand(Map<String,Object> requestMap){
        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        //获取时间选择器对应的同比时间
        Map<String, Object> begindate = DateUtil.getTbDate(requestMap.get("begindate").toString());
        Map<String, Object> enddate = DateUtil.getTbDate(requestMap.get("enddate").toString());
        requestMap.put("begindate",requestMap.get("begindate"));
        requestMap.put("enddate",requestMap.get("enddate"));
        List<ComplaintSituation> listOne = timeSelectUtil.complaintSituationMapper.selectTrafficBrandByMap(requestMap);
        listOne = listOne.subList(0,10);

        for( ComplaintSituation data : listOne ){
            requestMap.put("begindate",begindate.get("lastdate"));
            requestMap.put("enddate",enddate.get("lastdate"));
            requestMap.put("type",data.getBustype());
            ComplaintSituation dataOne = timeSelectUtil.complaintSituationMapper.selectTrafficBrandOneByMap(requestMap);
            if( dataOne == null ){
                dataOne = new ComplaintSituation();
                dataOne.setBustype(data.getBustype());
                dataOne.setOveralltotal(0);
                listTwo.add(dataOne);
            }else{
                listTwo.add(dataOne);
            }
        }
        map.put("this",listOne);
        map.put("last",listTwo);
        return map;
    }
    public static Map<String,Object> getAllArea(Map<String,Object> requestMap){
        Map<String,Object> map = new HashMap<>();
        List<OverallSituation> listOne = new ArrayList<>();
        List<OverallSituation> listTwo = new ArrayList<>();
        List<Map<String, String>> areas = new ArrayList<>();
        areas.add(new HashMap<String, String>(){{put("大良","440681001");}});
        areas.add(new HashMap<String, String>(){{put("容桂","440681002");}});
        areas.add(new HashMap<String, String>(){{put("伦教","440681003");}});
        areas.add(new HashMap<String, String>(){{put("北滘","440681004");}});
        areas.add(new HashMap<String, String>(){{put("勒流","440681007");}});
        areas.add(new HashMap<String, String>(){{put("陈村","440681005");}});
        areas.add(new HashMap<String, String>(){{put("乐从","440681006");}});
        areas.add(new HashMap<String, String>(){{put("龙江","440681008");}});
        areas.add(new HashMap<String, String>(){{put("杏坛","440681009");}});
        areas.add(new HashMap<String, String>(){{put("均安","440681010");}});

        //获取时间选择器对应的同比时间
        Map<String, Object> begindate = DateUtil.getTbDate(requestMap.get("begindate").toString());
        Map<String, Object> enddate = DateUtil.getTbDate(requestMap.get("enddate").toString());

        String key = "";
        String value = "";
        for (Map<String, String> unit : areas) {
            for (Iterator it = unit.keySet().iterator(); it.hasNext(); ) {
                key = (String) it.next();
                value = unit.get(key);
            }
                requestMap.put("region", value);
                requestMap.put("begindate", requestMap.get("begindate"));
                requestMap.put("enddate", requestMap.get("lastdate"));
            OverallSituation dataOne = timeSelectUtil.overallSituationMapper.selectAllAreaByMap(requestMap);
            if (dataOne != null){
                dataOne.setRegion(key);
            }else{
                dataOne.setRegion(key);
                dataOne.setBustotal(0);
                dataOne.setEndtotal(0);
                dataOne.setSuccessful(0);
            }
            listOne.add(dataOne);
                requestMap.put("begindate", begindate.get("lastdate"));
                requestMap.put("enddate", enddate.get("lastdate"));
            OverallSituation dataTwo = timeSelectUtil.overallSituationMapper.selectAllAreaByMap(requestMap);
            if (dataTwo != null){
                dataTwo.setRegion(key);
            }else{
                dataTwo.setRegion(key);
                dataTwo.setBustotal(0);
                dataTwo.setEndtotal(0);
                dataTwo.setSuccessful(0);
            }
            listTwo.add(dataTwo);
        }

        listOne.sort(Comparator.comparingInt(OverallSituation::getBustotal).reversed());
        listTwo = timeSelectUtil.getSortList1(listOne,listTwo);
        map.put("this",listOne);
        map.put("last",listTwo);
        return map;
    }
    public static Map<String,Object> getEconomydata(Map<String,Object> requestMap){
        Map<String,Object> map = new HashMap<>();
        List<EconomySituation> listOne = new ArrayList<>();
        List<EconomySituation> listTwo = new ArrayList<>();
        List<Map<String, String>> areas = new ArrayList<>();
        areas.add(new HashMap<String, String>(){{put("大良","440681001");}});
        areas.add(new HashMap<String, String>(){{put("容桂","440681002");}});
        areas.add(new HashMap<String, String>(){{put("伦教","440681003");}});
        areas.add(new HashMap<String, String>(){{put("北滘","440681004");}});
        areas.add(new HashMap<String, String>(){{put("勒流","440681007");}});
        areas.add(new HashMap<String, String>(){{put("陈村","440681005");}});
        areas.add(new HashMap<String, String>(){{put("乐从","440681006");}});
        areas.add(new HashMap<String, String>(){{put("龙江","440681008");}});
        areas.add(new HashMap<String, String>(){{put("杏坛","440681009");}});
        areas.add(new HashMap<String, String>(){{put("均安","440681010");}});

        //获取时间选择器对应的同比时间
        Map<String, Object> begindate = DateUtil.getTbDate(requestMap.get("begindate").toString());
        Map<String, Object> enddate = DateUtil.getTbDate(requestMap.get("enddate").toString());

        String key = "";
        String value = "";

        for (Map<String, String> unit : areas) {
            for (Iterator it = unit.keySet().iterator(); it.hasNext(); ) {
                key = (String) it.next();
                value = unit.get(key);
            }
                requestMap.put("region",value);
                requestMap.put("begindate", requestMap.get("begindate"));
                requestMap.put("enddate", requestMap.get("enddate"));

            EconomySituation dataOne = timeSelectUtil.economySituationMapper.selectEconomydataByMap(requestMap);
            if (dataOne != null){
                dataOne.setRegion(key);
            }else{
                dataOne = new EconomySituation();
                dataOne.setRegion(key);
                dataOne.setAccepttotal(0);
                dataOne.setEndtotal(0);
                dataOne.setRecordtotal(0);
            }
            listOne.add(dataOne);
                requestMap.put("begindate", begindate.get("lastdate"));
                requestMap.put("enddate", enddate.get("lastdate"));
            EconomySituation dataTwo = timeSelectUtil.economySituationMapper.selectEconomydataByMap(requestMap);
            if (dataTwo != null){
                dataTwo.setRegion(key);
            }else{
                dataTwo = new EconomySituation();
                dataTwo.setRegion(key);
                dataTwo.setAccepttotal(0);
                dataTwo.setEndtotal(0);
                dataTwo.setRecordtotal(0);
            }
            listTwo.add(dataTwo);
        }
        listOne.sort(Comparator.comparingInt(EconomySituation::getAccepttotal));
        listTwo.sort(Comparator.comparingInt(EconomySituation::getAccepttotal));

        map.put("this",listOne);
        map.put("last",listTwo);

        return map;
    }
    public static Map<String,Object> getComplaintdata(Map<String,Object> requestMap){
        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        List<Map<String, String>> areas = new ArrayList<>();
        areas.add(new HashMap<String, String>(){{put("大良","440681001");}});
        areas.add(new HashMap<String, String>(){{put("容桂","440681002");}});
        areas.add(new HashMap<String, String>(){{put("伦教","440681003");}});
        areas.add(new HashMap<String, String>(){{put("北滘","440681004");}});
        areas.add(new HashMap<String, String>(){{put("勒流","440681007");}});
        areas.add(new HashMap<String, String>(){{put("陈村","440681005");}});
        areas.add(new HashMap<String, String>(){{put("乐从","440681006");}});
        areas.add(new HashMap<String, String>(){{put("龙江","440681008");}});
        areas.add(new HashMap<String, String>(){{put("杏坛","440681009");}});
        areas.add(new HashMap<String, String>(){{put("均安","440681010");}});

        String key = "";
        String value = "";
        //获取时间选择器对应的同比时间
        Map<String, Object> begindate = DateUtil.getTbDate(requestMap.get("begindate").toString());
        Map<String, Object> enddate = DateUtil.getTbDate(requestMap.get("enddate").toString());
        for (Map<String, String> unit : areas) {
            for (Iterator it = unit.keySet().iterator(); it.hasNext(); ) {
                key = (String) it.next();
                value = unit.get(key);
            }
            requestMap.put("region", value);
            requestMap.put("begindate", requestMap.get("begindate"));
            requestMap.put("enddate", requestMap.get("enddate"));
            ComplaintSituation dataOne = timeSelectUtil.complaintSituationMapper.selectComplaintdataByMap(requestMap);
            if (dataOne != null){
                dataOne.setRegion(key);
            }else{
                dataOne = new ComplaintSituation();
                dataOne.setRegion(key);
                dataOne.setOveralltotal(0);
                dataOne.setEndtotal(0);
                dataOne.setToeconomytotal(0);
            }
            listOne.add(dataOne);
            requestMap.put("begindate",begindate.get("lastdate"));
            requestMap.put("enddate",enddate.get("lastdate"));
            ComplaintSituation dataTwo = timeSelectUtil.complaintSituationMapper.selectComplaintdataByMap(requestMap);
            if (dataTwo != null){
                dataTwo.setRegion(key);
            }else{
                dataTwo = new ComplaintSituation();
                dataTwo.setRegion(key);
                dataTwo.setOveralltotal(0);
                dataTwo.setEndtotal(0);
                dataTwo.setToeconomytotal(0);
            }
            listTwo.add(dataTwo);
        }
        listOne.sort(Comparator.comparingInt(ComplaintSituation::getOveralltotal).reversed());
        listTwo = timeSelectUtil.getSortList(listOne,listTwo);
        map.put("this",listOne);
        map.put("last",listTwo);
        return map;
    }
}

