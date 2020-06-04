package com.chinaweal.sdcs.crcs.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.system.entity.OverallSituation;
import com.chinaweal.sdcs.crcs.system.mapper.OverallSituationMapper;
import com.chinaweal.sdcs.crcs.system.service.IOverallSituationService;
import com.chinaweal.sdcs.crcs.util.DateUtil;
import com.chinaweal.sdcs.crcs.util.TimeSelectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author chinaweal
 * @Name   Liny
 * @since 2020-02-03
 */
@Service
public class OverallSituationServiceImpl extends ServiceImpl<OverallSituationMapper, OverallSituation> implements IOverallSituationService {


    @Resource
    private OverallSituationMapper overallSituationMapper;
    @Autowired
    private TimeSelectUtil timeSelectUtil;

    @Override
    public List<OverallSituation> selectBustype(Map<String, Object> requestMap) {
        if(requestMap.get("start")==null || requestMap.get("start")==""){
            List<OverallSituation> data = overallSituationMapper.selectBustypeByMap(requestMap);
            return data;
        }else{
            requestMap.put("start",requestMap.get("start"));
            requestMap.put("end",requestMap.get("end"));
            List<OverallSituation> data = overallSituationMapper.selectBustypeByMap(requestMap);
            return data;
        }
    }

    public   List<OverallSituation> getSortList(List<OverallSituation> list1,List<OverallSituation> list2)  {
        List<OverallSituation> newList = new ArrayList<>();
        for (OverallSituation listOne : list1){
            for (OverallSituation listTwo : list2){
                if (listOne.getRegion().equals(listTwo.getRegion())){
                    newList.add(listTwo);
                    continue ;
                }
            }
        }
        return newList;
    }

    @Override
    public Map<String,Object> selectAllArea(Map<String, Object> requestMap) {

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

        Map<String,Object> thisYear = DateUtil.getThisYear();

        Map<String,Object> lastYear = DateUtil.getLastYear();
        System.out.println("fffff");
        System.out.println(requestMap.get("begindate"));
        if(requestMap.get("begindate")==null || requestMap.get("begindate")==""){
            String key = "";
            String value = "";
            for (Map<String, String> unit : areas) {
                for (Iterator it = unit.keySet().iterator(); it.hasNext(); ) {
                    key = (String) it.next();
                    value = unit.get(key);
                }
                requestMap.put("region", value);
                requestMap.put("begindate", thisYear.get("start"));
                requestMap.put("enddate", thisYear.get("end"));
                OverallSituation dataOne = overallSituationMapper.selectAllAreaByMap(requestMap);
                if (dataOne != null){
                    dataOne.setRegion(key);
                }else{
                    dataOne.setRegion(key);
                    dataOne.setBustotal(0);
                    dataOne.setEndtotal(0);
                    dataOne.setSuccessful(0);
                }
                listOne.add(dataOne);
                requestMap.put("begindate", lastYear.get("start"));
                requestMap.put("enddate", lastYear.get("end"));


                OverallSituation dataTwo = overallSituationMapper.selectAllAreaByMap(requestMap);
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
            listTwo = getSortList(listOne,listTwo);
            map.put("this",listOne);
            map.put("last",listTwo);
            return map;
        }else{
            return timeSelectUtil.getAllArea(requestMap);
        }
    }
}
