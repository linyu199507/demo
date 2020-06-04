package com.chinaweal.sdcs.crcs.system.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.system.entity.Basecodeform;
import com.chinaweal.sdcs.crcs.system.entity.ComplaintSituation;
import com.chinaweal.sdcs.crcs.system.entity.TimesVo;
import com.chinaweal.sdcs.crcs.system.mapper.BasecodeformMapper;
import com.chinaweal.sdcs.crcs.system.mapper.ComplaintSituationMapper;
import com.chinaweal.sdcs.crcs.system.service.IComplaintSituationService;
import com.chinaweal.sdcs.crcs.util.DateUtil;
import com.chinaweal.sdcs.crcs.util.TimeSelectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author chinaweal
 * @Name   Liny
 * @since 2020-02-06
 */
@Service
public class ComplaintSituationServiceImpl extends ServiceImpl<ComplaintSituationMapper, ComplaintSituation> implements IComplaintSituationService {

    @Resource
    private ComplaintSituationMapper complaintSituationMapper;
    @Resource
    private BasecodeformMapper basecodeformMapper;
    @Autowired
    private TimeSelectUtil timeSelectUtil;

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

    @Override
    public Map<String, Object> selectComplaintdata(Map<String, Object> requestMap) {

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

            Map<String,Object> thisYear = DateUtil.getThisYear();
            Map<String,Object> lastYear = DateUtil.getLastYear();

            String key = "";
            String value = "";
            for (Map<String, String> unit : areas) {
                for (Iterator it = unit.keySet().iterator(); it.hasNext(); ) {
                    key = (String) it.next();
                    value = unit.get(key);
                }
            if(requestMap.get("begindate")!=null || !"".equals(requestMap.get("begindate"))){
                requestMap.put("region", value);
                requestMap.put("begindate",requestMap.get("begindate"));
                requestMap.put("enddate",requestMap.get("enddate"));
            }else{
                requestMap.put("region", value);
                requestMap.put("begindate", thisYear.get("start"));
                requestMap.put("enddate", thisYear.get("end"));
            }
                ComplaintSituation dataOne = complaintSituationMapper.selectComplaintdataByMap(requestMap);
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
            }
            listOne.sort(Comparator.comparingInt(ComplaintSituation::getOveralltotal).reversed());
            listTwo = getSortList(listOne,listTwo);
            map.put("this",listOne);
            return map;
    }

    @Override
    public Map<String, Object> selectDataOfTwelve(Map<String, Object> requestMap) {

        Map<String,Object> map = new LinkedHashMap<>();
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
        List<TimesVo> times = DateUtil.getTwelveMonth();

        for(TimesVo time :times){
            listOne = new ArrayList<>();

            requestMap.put("begindate",time.getTimestart());
            requestMap.put("enddate",time.getTimeend());
            for (Map<String, String> unit : areas) {
                for (Iterator it = unit.keySet().iterator(); it.hasNext(); ) {
                    key = (String) it.next();
                    value = unit.get(key);
                }
                requestMap.put("region", value);
                ComplaintSituation dataOne = complaintSituationMapper.selectDataOfTwelveByMap(requestMap);
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
            }
            map.put(time.getTimestart(),listOne);
        }
        return map;
    }

    @Override
    public Map<String, Object> selectHotNature(Map<String, Object> requestMap) {
        Map<String,Object> map = new LinkedHashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "question12345").eq(Basecodeform::getOrglevel,"1");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = basecodeformMapper.selectList(queryWrapper);

        List<TimesVo> times = DateUtil.getTwelveMonth();
        for(TimesVo time :times){
            listOne = new ArrayList<>();
            requestMap.put("begindate",time.getTimestart());
            requestMap.put("enddate",time.getTimeend());
            for ( Basecodeform basecodeform : basecodeforms) {
                String codeid = basecodeform.getCodeid();
                requestMap.put("type", codeid);
                ComplaintSituation dataOne = complaintSituationMapper.selectHotNatureByMap(requestMap);
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
            }
            listOne.sort(Comparator.comparingInt(ComplaintSituation::getOveralltotal).reversed());
            listOne.subList(0,15);
            map.put(time.getTimestart(),listOne);
        }
        return map;
    }

    @Override
    public Map<String, Object> selectappliancesCategory(Map<String, Object> requestMap) {

        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "object12345").eq(Basecodeform::getOrglevel,"3").likeRight(Basecodeform::getCodeid,"0101%");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = basecodeformMapper.selectList(queryWrapper);
        Map<String,Object> thisyear = DateUtil.getThisYear();
        Map<String,Object> lastyear = DateUtil.getLastYear();
        if(requestMap.get("begindate")==null || requestMap.get("begindate")=="") {
            for (Basecodeform basecodeform : basecodeforms) {
                String codeid = basecodeform.getCodeid();
                requestMap.put("type", codeid);
                requestMap.put("begindate",thisyear.get("start"));
                requestMap.put("enddate",thisyear.get("end"));
                ComplaintSituation dataOne = complaintSituationMapper.selectappliancesCategoryByMap(requestMap);
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
                requestMap.put("begindate",lastyear.get("start"));
                requestMap.put("enddate",lastyear.get("end"));
                ComplaintSituation dataTwo = complaintSituationMapper.selectappliancesCategoryByMap(requestMap);
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
            }
        }else{
            return timeSelectUtil.getAppliancesCategory(requestMap);
        }
        listOne.sort(Comparator.comparingInt(ComplaintSituation::getOveralltotal).reversed());
        listTwo = getSortList(listOne,listTwo);
        map.put("this",listOne);
        map.put("last",listTwo);
        return map;
    }

    @Override
    public Map<String, Object> selectappliancesNature(Map<String, Object> requestMap) {

        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "question12345").eq(Basecodeform::getOrglevel,"1");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = basecodeformMapper.selectList(queryWrapper);
        Map<String,Object> thisyear = DateUtil.getThisYear();
        Map<String,Object> lastyear = DateUtil.getLastYear();
        if(requestMap.get("begindate")==null || requestMap.get("begindate")=="") {
            for ( Basecodeform basecodeform : basecodeforms) {
                String codeid = basecodeform.getCodeid();
                requestMap.put("type", codeid);

                requestMap.put("begindate",thisyear.get("start"));
                requestMap.put("enddate",thisyear.get("end"));

                ComplaintSituation dataOne = complaintSituationMapper.selectappliancesNatureByMap(requestMap);
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

                requestMap.put("begindate",lastyear.get("start"));
                requestMap.put("enddate",lastyear.get("end"));

                ComplaintSituation dataTwo = complaintSituationMapper.selectappliancesNatureByMap(requestMap);
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
        }else{
            return timeSelectUtil.getAppliancesNature(requestMap);
        }
        listOne.sort(Comparator.comparingInt(ComplaintSituation::getOveralltotal).reversed());
        listTwo = getSortList(listOne,listTwo);
        map.put("this",listOne);
        map.put("last",listTwo);
        return map;
    }

    @Override
    public Map<String, Object> selectappliancesBrand(Map<String, Object> requestMap) {
        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        Map<String,Object> thisyear = DateUtil.getThisYear();
        Map<String,Object> lastyear = DateUtil.getLastYear();
        if(requestMap.get("begindate")==null || requestMap.get("begindate")==""){
            requestMap.put("begindate", thisyear.get("start"));
            requestMap.put("enddate", thisyear.get("end"));
            List<ComplaintSituation> listOne = complaintSituationMapper.selectappliancesBrandByMap(requestMap);
            listOne = listOne.subList(0, 10);

            for (ComplaintSituation data : listOne) {
                requestMap.put("begindate", lastyear.get("start"));
                requestMap.put("enddate", lastyear.get("end"));
                requestMap.put("type", data.getBustype());
                ComplaintSituation dataOne = complaintSituationMapper.selectBrandByMap(requestMap);
                if (dataOne == null) {
                    dataOne = new ComplaintSituation();
                    dataOne.setBustype(data.getBustype());
                    dataOne.setOveralltotal(0);
                    listTwo.add(dataOne);
                } else {
                    listTwo.add(dataOne);
                }
            }
            map.put("this", listOne);
            map.put("last", listTwo);
            return map;
        }else {
            return timeSelectUtil.getAppliancesBrand(requestMap);
        }
    }

    @Override
    public Map<String, Object> selectsubjectTen(Map<String, Object> requestMap) {
        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        Map<String,Object> thisyear = DateUtil.getThisYear();
        Map<String,Object> lastyear = DateUtil.getLastYear();
        if(requestMap.get("begindate")==null || requestMap.get("begindate")==""){
            requestMap.put("begindate", thisyear.get("start"));
            requestMap.put("enddate", thisyear.get("end"));
            listOne = complaintSituationMapper.selectsubjectTenByMap(requestMap);
            listOne = listOne.subList(0,10);

            for( ComplaintSituation data : listOne ){
                requestMap.put("begindate", lastyear.get("start"));
                requestMap.put("enddate", lastyear.get("end"));
                requestMap.put("type",data.getBustype());
                ComplaintSituation dataOne = complaintSituationMapper.selectsubjectOneByMap(requestMap);
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
        }else{
            return timeSelectUtil.getSubjectTen(requestMap);
        }
    }

    @Override
    public Map<String, Object> selectbusinesscircle(Map<String, Object> requestMap) {
        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        Map<String,Object> thisyear = DateUtil.getThisYear();
        Map<String,Object> lastyear = DateUtil.getLastYear();
        if(requestMap.get("begindate")==null || requestMap.get("begindate")==""){
            requestMap.put("begindate", thisyear.get("start"));
            requestMap.put("enddate", thisyear.get("end"));
            listOne = complaintSituationMapper.selectbusinesscircleByMap(requestMap);
            if(listOne.size()>=10){
                listOne = listOne.subList(0,10);
            }
            for( ComplaintSituation data : listOne ){
                requestMap.put("begindate", lastyear.get("start"));
                requestMap.put("enddate", lastyear.get("end"));
                requestMap.put("type",data.getBustype());
                ComplaintSituation dataOne = complaintSituationMapper.selectbusinesscircleOneByMap(requestMap);
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
        }else{
            Map<String, Object> businesscircle = timeSelectUtil.getBusinesscircle(requestMap);
            return businesscircle;
        }
    }

    @Override
    public Map<String, Object> selectdailyCategory(Map<String, Object> requestMap) {

        Map<String,Object> map = new HashMap<>();
        Map<String,Object> requestMapOne = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
//        List<ComplaintSituation> listThree = new ArrayList<>();
        Map<String,Object> thisyear = DateUtil.getThisYear();
        Map<String,Object> lastyear = DateUtil.getLastYear();

        if(requestMap.get("begindate")==null || requestMap.get("begindate")==""){

            requestMap.put("begindate", thisyear.get("start"));
            requestMap.put("enddate", thisyear.get("end"));

            requestMapOne.put("begindate", lastyear.get("start"));
            requestMapOne.put("enddate", lastyear.get("end"));

            QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().like(Basecodeform::getType, "object12345").eq(Basecodeform::getOrglevel, "3").likeRight(Basecodeform::getCodeid, "0104%");
            queryWrapper.orderByAsc("codeid");
            List<Basecodeform> basecodeforms = basecodeformMapper.selectList(queryWrapper);

            for (Basecodeform basecodeform : basecodeforms) {
                String codeid = basecodeform.getCodeid();
                if ("家具".equals(basecodeform.getComment())) {
                    continue;
                }
                requestMap.put("type", codeid);
                requestMapOne.put("type", codeid);
                ComplaintSituation dataOne = complaintSituationMapper.selectdailyCategoryByMap(requestMap);
                ComplaintSituation dataTwo = complaintSituationMapper.selectdailyCategoryByMap(requestMapOne);
                if (dataOne == null) {
                    dataOne = new ComplaintSituation();
                    dataOne.setBustype(basecodeform.getComment());
                    dataOne.setOveralltotal(0);
                    dataOne.setEndtotal(0);
                    listOne.add(dataOne);
                } else {
                    dataOne.setBustype(basecodeform.getComment());
                    listOne.add(dataOne);
                }
                if (dataTwo == null) {
                    dataTwo = new ComplaintSituation();
                    dataTwo.setBustype(basecodeform.getComment());
                    dataTwo.setOveralltotal(0);
                    dataTwo.setEndtotal(0);
                    listTwo.add(dataTwo);
                } else {
                    dataTwo.setBustype(basecodeform.getComment());
                    listTwo.add(dataTwo);
                }
            }

            QueryWrapper<Basecodeform> queryWrapperOne = new QueryWrapper<>();
            queryWrapperOne.lambda().like(Basecodeform::getType, "object12345").eq(Basecodeform::getOrglevel, "4").likeRight(Basecodeform::getCodeid, "010401%");
            queryWrapperOne.orderByAsc("codeid");
            List<Basecodeform> basecodeformsOne = basecodeformMapper.selectList(queryWrapperOne);
            for (Basecodeform BasecodeformOne : basecodeformsOne) {
                String codeid = BasecodeformOne.getCodeid();
                requestMap.put("type", codeid);
                requestMapOne.put("type", codeid);
                ComplaintSituation dataOne = complaintSituationMapper.selectdailyCategoryByMap(requestMap);
                ComplaintSituation dataTwo = complaintSituationMapper.selectdailyCategoryByMap(requestMapOne);
                if (dataOne == null) {
                    dataOne = new ComplaintSituation();
                    dataOne.setBustype(BasecodeformOne.getComment());
                    dataOne.setOveralltotal(0);
                    dataOne.setEndtotal(0);
                    listOne.add(dataOne);
                } else {
                    dataOne.setBustype(BasecodeformOne.getComment());
                    listOne.add(dataOne);
                }
                if (dataTwo == null) {
                    dataTwo = new ComplaintSituation();
                    dataTwo.setBustype(BasecodeformOne.getComment());
                    dataTwo.setOveralltotal(0);
                    dataTwo.setEndtotal(0);
                    listTwo.add(dataTwo);
                } else {
                    dataTwo.setBustype(BasecodeformOne.getComment());
                    listTwo.add(dataTwo);
                }
            }
//        listOne.addAll(listTwo);
            listOne.sort(Comparator.comparingInt(ComplaintSituation::getOveralltotal).reversed());
            listTwo.sort(Comparator.comparingInt(ComplaintSituation::getOveralltotal).reversed());
            map.put("this", listOne);
            map.put("last", listTwo);
            return map;
        }else {
            return timeSelectUtil.getDailyCategory(requestMap);
        }
    }

    @Override
    public Map<String, Object> selectDailyNature(Map<String, Object> requestMap) {
        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "question12345").eq(Basecodeform::getOrglevel,"1");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = basecodeformMapper.selectList(queryWrapper);
        Map<String,Object> thisyear = DateUtil.getThisYear();
        Map<String,Object> lastyear = DateUtil.getLastYear();
        if(requestMap.get("begindate")==null || requestMap.get("begindate")==""){
            for (Basecodeform basecodeform : basecodeforms) {
                String codeid = basecodeform.getCodeid();
                requestMap.put("type", codeid);
                requestMap.put("begindate", thisyear.get("start"));
                requestMap.put("enddate", thisyear.get("end"));
                ComplaintSituation dataOne = complaintSituationMapper.selectDailyNatureByMap(requestMap);
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

                requestMap.put("begindate", lastyear.get("start"));
                requestMap.put("enddate", lastyear.get("end"));
                ComplaintSituation dataTwo = complaintSituationMapper.selectDailyNatureByMap(requestMap);
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
            }
            listOne.sort(Comparator.comparingInt(ComplaintSituation::getOveralltotal).reversed());
            listTwo = getSortList(listOne,listTwo);
            map.put("this",listOne);
            map.put("last",listTwo);
            return map;
        }
        else {
            return timeSelectUtil.getDailyNature(requestMap);
        }
    }

    @Override
    public Map<String, Object> selectDailyBrand(Map<String, Object> requestMap) {

        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        Map<String,Object> thisyear = DateUtil.getThisYear();
        Map<String,Object> lastyear = DateUtil.getLastYear();
        if(requestMap.get("begindate")==null || requestMap.get("begindate")==""){
            requestMap.put("begindate",thisyear.get("start"));
            requestMap.put("enddate",thisyear.get("end"));
//        List<ComplaintSituation> listOne = complaintSituationMapper.selectappliancesBrandByMap(requestMap);
            List<ComplaintSituation> listOne = complaintSituationMapper.selectDailyBrandByMap(requestMap);
            listOne = listOne.subList(0,10);

            for( ComplaintSituation data : listOne ){
                requestMap.put("begindate",lastyear.get("start"));
                requestMap.put("enddate",lastyear.get("end"));
                requestMap.put("type",data.getBustype());
//            ComplaintSituation dataOne = complaintSituationMapper.selectBrandByMap(requestMap);
                ComplaintSituation dataOne = complaintSituationMapper.selectDailyBrandOneByMap(requestMap);
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
        }else{
            return timeSelectUtil.getDailyBrand(requestMap);
        }
    }

    @Override
    public Map<String, Object> selectTrafficCategory(Map<String, Object> requestMap) {
        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "object12345").eq(Basecodeform::getOrglevel,"3").likeRight(Basecodeform::getCodeid,"0106%");
        queryWrapper.orderByAsc("codeid");
            List<Basecodeform> basecodeforms = basecodeformMapper.selectList(queryWrapper);
        Map<String,Object> thisyear = DateUtil.getThisYear();
        Map<String,Object> lastyear = DateUtil.getLastYear();
        if(requestMap.get("begindate")==null || requestMap.get("begindate")==""){
            for ( Basecodeform basecodeform : basecodeforms) {
                String codeid = basecodeform.getCodeid();
                requestMap.put("type", codeid);
                requestMap.put("begindate", thisyear.get("start"));
                requestMap.put("enddate", thisyear.get("end"));
                ComplaintSituation dataOne = complaintSituationMapper.selectTrafficCategoryByMap(requestMap);
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
                requestMap.put("begindate", lastyear.get("start"));
                requestMap.put("enddate", lastyear.get("end"));
                ComplaintSituation dataTwo = complaintSituationMapper.selectTrafficCategoryByMap(requestMap);
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
        }else{
            return timeSelectUtil.getTrafficCategory(requestMap);
        }
    }

    @Override
    public Map<String, Object> selectTrafficNature(Map<String, Object> requestMap) {
        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "question12345").eq(Basecodeform::getOrglevel,"1");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = basecodeformMapper.selectList(queryWrapper);
        Map<String,Object> thisyear = DateUtil.getThisYear();
        Map<String,Object> lastyear = DateUtil.getLastYear();
        if(requestMap.get("begindate")==null || requestMap.get("begindate")==""){
            for ( Basecodeform basecodeform : basecodeforms) {
                String codeid = basecodeform.getCodeid();
                requestMap.put("type", codeid);
                requestMap.put("begindate",thisyear.get("start"));
                requestMap.put("enddate",thisyear.get("end"));
                ComplaintSituation dataOne = complaintSituationMapper.selectTrafficNatureByMap(requestMap);
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

                requestMap.put("begindate",lastyear.get("start"));
                requestMap.put("enddate",lastyear.get("end"));
                ComplaintSituation dataTwo = complaintSituationMapper.selectTrafficNatureByMap(requestMap);
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
            listTwo = getSortList(listOne,listTwo);
            map.put("this",listOne);
            map.put("last",listTwo);
            return map;
        }else{
            return timeSelectUtil.getTrafficNature(requestMap);
        }
    }

    @Override
    public Map<String, Object> selectTrafficBrand(Map<String, Object> requestMap) {

        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        Map<String,Object> thisyear = DateUtil.getThisYear();
        Map<String,Object> lastyear = DateUtil.getLastYear();
        if(requestMap.get("begindate")==null || requestMap.get("begindate")==""){
            requestMap.put("begindate",thisyear.get("start"));
            requestMap.put("enddate",thisyear.get("end"));
            List<ComplaintSituation> listOne = complaintSituationMapper.selectTrafficBrandByMap(requestMap);
            listOne = listOne.subList(0,10);

            for( ComplaintSituation data : listOne ){
                requestMap.put("begindate",lastyear.get("start"));
                requestMap.put("enddate",lastyear.get("end"));
                requestMap.put("type",data.getBustype());
                ComplaintSituation dataOne = complaintSituationMapper.selectTrafficBrandOneByMap(requestMap);
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
        }else{
            return timeSelectUtil.getTrafficBrand(requestMap);
        }
    }

    @Override
    public Map<String, Object> selectdecoration(Map<String, Object> requestMap) {
        Map<String, Object> map = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "object12345").eq(Basecodeform::getOrglevel, "3").likeRight(Basecodeform::getCodeid, "0108%");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = basecodeformMapper.selectList(queryWrapper);
        Map<String, Object> thisyear = DateUtil.getThisYear();
        Map<String, Object> lastyear = DateUtil.getLastYear();
        if (requestMap.get("begindate")==null || requestMap.get("begindate")=="") {
            for (Basecodeform basecodeform : basecodeforms) {
                String codeid = basecodeform.getCodeid();
                requestMap.put("type", codeid);
                requestMap.put("begindate", thisyear.get("start"));
                requestMap.put("enddate", thisyear.get("end"));
                ComplaintSituation dataOne = complaintSituationMapper.selectdecorationByMap(requestMap);
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

            for (ComplaintSituation complaint : listOne) {
                requestMap.put("type", complaint.getRegion());
                requestMap.put("begindate", lastyear.get("start"));
                requestMap.put("enddate", lastyear.get("end"));
                ComplaintSituation dataTwo = complaintSituationMapper.selectdecorationByMap(requestMap);
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

            map.put("this", listOne);
            map.put("last", listTwo);
            return map;
        } else {
            return timeSelectUtil.getDecoration(requestMap);
         }
    }

    @Override
    public Map<String, Object> selecthousing(Map<String, Object> requestMap) {
        Map<String, Object> map = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "object12345").eq(Basecodeform::getOrglevel, "3").likeRight(Basecodeform::getCodeid, "0110%");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = basecodeformMapper.selectList(queryWrapper);
        Map<String, Object> thisyear = DateUtil.getThisYear();
        Map<String, Object> lastyear = DateUtil.getLastYear();
        if (requestMap.get("begindate")==null || requestMap.get("begindate")=="") {
            for (Basecodeform basecodeform : basecodeforms) {
                String codeid = basecodeform.getCodeid();
                requestMap.put("type", codeid);
                requestMap.put("begindate", thisyear.get("start"));
                requestMap.put("enddate", thisyear.get("end"));
                ComplaintSituation dataOne = complaintSituationMapper.selectdecorationByMap(requestMap);
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

            for (ComplaintSituation complaint : listOne) {
                requestMap.put("type", complaint.getRegion());
                requestMap.put("begindate", lastyear.get("start"));
                requestMap.put("enddate", lastyear.get("end"));
                ComplaintSituation dataTwo = complaintSituationMapper.selectdecorationByMap(requestMap);
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

            map.put("this", listOne);
            map.put("last", listTwo);
            return map;
        } else {
            return timeSelectUtil.getHousing(requestMap);
        }
    }

    @Override
    public Map<String, Object> selectfood(Map<String, Object> requestMap) {
        Map<String, Object> map = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "object12345").eq(Basecodeform::getOrglevel, "3").likeRight(Basecodeform::getCodeid, "0105%");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = basecodeformMapper.selectList(queryWrapper);
        Map<String, Object> thisyear = DateUtil.getThisYear();
        Map<String, Object> lastyear = DateUtil.getLastYear();
        if (requestMap.get("begindate")==null || requestMap.get("begindate")=="") {
            for (Basecodeform basecodeform : basecodeforms) {
                String codeid = basecodeform.getCodeid();
                requestMap.put("type", codeid);
                requestMap.put("begindate", thisyear.get("start"));
                requestMap.put("enddate", thisyear.get("end"));
                ComplaintSituation dataOne = complaintSituationMapper.selectdecorationByMap(requestMap);
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
            listOne = listOne.subList(0, 10);

            for (ComplaintSituation complaint : listOne) {
                requestMap.put("type", complaint.getRegion());
                requestMap.put("begindate", lastyear.get("start"));
                requestMap.put("enddate", lastyear.get("end"));
                ComplaintSituation dataTwo = complaintSituationMapper.selectdecorationByMap(requestMap);
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

            map.put("this", listOne);
            map.put("last", listTwo);
            return map;
        } else {
            return timeSelectUtil.getFood(requestMap);
        }
    }

    @Override
    public Map<String, Object> selectfoodLink(Map<String, Object> requestMap) {
        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "industry12345").eq(Basecodeform::getOrglevel,"1");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = basecodeformMapper.selectList(queryWrapper);
        Map<String,Object> thisyear = DateUtil.getThisYear();
        Map<String,Object> lastyear = DateUtil.getLastYear();
        if(requestMap.get("begindate")==null || requestMap.get("begindate")==""){
            for ( Basecodeform basecodeform : basecodeforms) {
                if ("01".equals(basecodeform.getCodeid()) || "02".equals(basecodeform.getCodeid()) || "03".equals(basecodeform.getCodeid()) || "05".equals(basecodeform.getCodeid())) {
                    String codeid = basecodeform.getCodeid();
                    requestMap.put("type", codeid);
                    requestMap.put("begindate", thisyear.get("start"));
                    requestMap.put("enddate", thisyear.get("end"));
                    ComplaintSituation dataOne = complaintSituationMapper.selectfoodLinkByMap(requestMap);
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
                requestMap.put("begindate", lastyear.get("start"));
                requestMap.put("enddate", lastyear.get("end"));
                ComplaintSituation dataTwo = complaintSituationMapper.selectfoodLinkByMap(requestMap);
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
        }else{
            return timeSelectUtil.getFoodLinkData(requestMap);
        }
    }

    @Override
    public Map<String, Object> selectmedicineLink(Map<String, Object> requestMap) {
        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "industry12345").eq(Basecodeform::getOrglevel,"1");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = basecodeformMapper.selectList(queryWrapper);
        Map<String,Object> thisyear = DateUtil.getThisYear();
        Map<String,Object> lastyear = DateUtil.getLastYear();
        if(requestMap.get("begindate")==null || requestMap.get("begindate")==""){
            for ( Basecodeform basecodeform : basecodeforms) {
                if ("01".equals(basecodeform.getCodeid()) || "02".equals(basecodeform.getCodeid()) || "03".equals(basecodeform.getCodeid()) || "05".equals(basecodeform.getCodeid())) {
                    String codeid = basecodeform.getCodeid();
                    requestMap.put("type", codeid);
                    requestMap.put("begindate", thisyear.get("start"));
                    requestMap.put("enddate", thisyear.get("end"));
                    ComplaintSituation dataOne = complaintSituationMapper.selectmedicineLinkByMap(requestMap);
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
                requestMap.put("begindate", lastyear.get("start"));
                requestMap.put("enddate", lastyear.get("end"));
                ComplaintSituation dataTwo = complaintSituationMapper.selectmedicineLinkByMap(requestMap);
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
        }else{
            return timeSelectUtil.getMedicineLink(requestMap);
        }
    }

    @Override
    public Map<String, Object> selecthealthfoodLink(Map<String, Object> requestMap) {
        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "industry12345").eq(Basecodeform::getOrglevel,"1");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = basecodeformMapper.selectList(queryWrapper);
        Map<String,Object> thisyear = DateUtil.getThisYear();
        Map<String,Object> lastyear = DateUtil.getLastYear();
        if(requestMap.get("begindate")==null || requestMap.get("begindate")==""){
            for ( Basecodeform basecodeform : basecodeforms) {
                if ("01".equals(basecodeform.getCodeid()) || "02".equals(basecodeform.getCodeid()) || "03".equals(basecodeform.getCodeid()) || "05".equals(basecodeform.getCodeid())) {
                    String codeid = basecodeform.getCodeid();
                    requestMap.put("type", codeid);
                    requestMap.put("begindate", thisyear.get("start"));
                    requestMap.put("enddate", thisyear.get("end"));
                    ComplaintSituation dataOne = complaintSituationMapper.selecthealthfoodLinkByMap(requestMap);
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
                requestMap.put("begindate", lastyear.get("start"));
                requestMap.put("enddate", lastyear.get("end"));
                ComplaintSituation dataTwo = complaintSituationMapper.selecthealthfoodLinkByMap(requestMap);
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
        }else{
            return timeSelectUtil.getHealthfoodLink(requestMap);
        }
    }

    @Override
    public Map<String, Object> selectCosmeticsLink(Map<String, Object> requestMap) {
        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "industry12345").eq(Basecodeform::getOrglevel,"1");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = basecodeformMapper.selectList(queryWrapper);
        Map<String,Object> thisyear = DateUtil.getThisYear();
        Map<String,Object> lastyear = DateUtil.getLastYear();
        if(requestMap.get("begindate")==null || requestMap.get("begindate")==""){
            for ( Basecodeform basecodeform : basecodeforms) {
                if ("01".equals(basecodeform.getCodeid()) || "02".equals(basecodeform.getCodeid()) || "03".equals(basecodeform.getCodeid()) || "05".equals(basecodeform.getCodeid())) {
                    String codeid = basecodeform.getCodeid();
                    requestMap.put("type", codeid);
                    requestMap.put("begindate", thisyear.get("start"));
                    requestMap.put("enddate", thisyear.get("end"));
                    ComplaintSituation dataOne = complaintSituationMapper.selectCosmeticsLinkByMap(requestMap);
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
                requestMap.put("begindate", lastyear.get("start"));
                requestMap.put("enddate", lastyear.get("end"));
                ComplaintSituation dataTwo = complaintSituationMapper.selectCosmeticsLinkByMap(requestMap);
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
        }else{
            return timeSelectUtil.getCosmeticsLink(requestMap);
        }
    }

    @Override
    public Map<String, Object> selectMedicalLink(Map<String, Object> requestMap) {
        Map<String,Object> map = new HashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        List<ComplaintSituation> listTwo = new ArrayList<>();
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "industry12345").eq(Basecodeform::getOrglevel,"1");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = basecodeformMapper.selectList(queryWrapper);
        Map<String,Object> thisyear = DateUtil.getThisYear();
        Map<String,Object> lastyear = DateUtil.getLastYear();
        if(requestMap.get("begindate")==null || requestMap.get("begindate")==""){
            for ( Basecodeform basecodeform : basecodeforms) {
                if ("01".equals(basecodeform.getCodeid()) || "02".equals(basecodeform.getCodeid()) || "03".equals(basecodeform.getCodeid()) || "05".equals(basecodeform.getCodeid())) {
                    //if ("02".equals(basecodeform.getCodeid())) {
                    String codeid = basecodeform.getCodeid();
                    requestMap.put("type", codeid);
                    requestMap.put("begindate", thisyear.get("start"));
                    requestMap.put("enddate", thisyear.get("end"));
                    ComplaintSituation dataOne = complaintSituationMapper.selectMedicalLinkByMap(requestMap);
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
                requestMap.put("begindate", lastyear.get("start"));
                requestMap.put("enddate", lastyear.get("end"));
                ComplaintSituation dataTwo = complaintSituationMapper.selectMedicalLinkByMap(requestMap);
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
        }else{
            return timeSelectUtil.getMedicalLink(requestMap);
        }
    }

    @Override
    public Map<String, Object> selectComplaintHotSpot(Map<String, Object> requestMap) {

        Map<String,Object> map = new LinkedHashMap<>();
        List<ComplaintSituation> listOne = new ArrayList<>();
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "object12345").eq(Basecodeform::getOrglevel,"2");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = basecodeformMapper.selectList(queryWrapper);
        List<TimesVo> thisYear = DateUtil.getTwelveMonth();
        Map<String,Object> lastYear = DateUtil.getLastYear();
        for(TimesVo timesVo:thisYear) {
            listOne = new ArrayList<>();
            requestMap.put("begindate", timesVo.getTimestart());
            requestMap.put("enddate", timesVo.getTimeend());
        for (Basecodeform unit : basecodeforms) {
                requestMap.put("type", unit.getCodeid());
                ComplaintSituation dataOne = complaintSituationMapper.selectComplaintHotSpotByMap(requestMap);
                if (dataOne != null) {
                    dataOne.setRegion(unit.getComment());
                } else {
                    dataOne = new ComplaintSituation();
                    dataOne.setRegion(unit.getComment());
                    dataOne.setOveralltotal(0);
                    dataOne.setEndtotal(0);
                    dataOne.setToeconomytotal(0);
                }
                listOne.add(dataOne);
                if(listOne.size()>10) {
                    listOne = listOne.subList(0, 10);
                }
            }
            listOne.sort(Comparator.comparingInt(ComplaintSituation::getOveralltotal).reversed());

            map.put(timesVo.getTimestart(),listOne);
        }
        return map;
    }
}
