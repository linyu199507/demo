package com.chinaweal.sdcs.crcs.bigScreenDisplay.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.MapOneVO;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.MapVO;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.mapper.DataMapMapper;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.mapper.DataMapOneMapper;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.service.IDataMapOneService;
import com.chinaweal.sdcs.crcs.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 大屏展示数据获取--顺德区地图
 */
@Service
public class DataMapOneServiceImpl extends ServiceImpl<DataMapOneMapper, MapOneVO> implements IDataMapOneService {


    @Resource
    private DataMapOneMapper dataMapOneMapper;


    @Override
    public List<MapOneVO> searchAllDataMap(Map<String, Object> requestMap) {

        Map<String,Object> thisYear = DateUtil.getThisYear();
        requestMap.put("begindate", thisYear.get("start"));
        requestMap.put("enddate", thisYear.get("end"));

        List<MapOneVO> datas = dataMapOneMapper.searchAllDataMapByMap(requestMap);

        for ( MapOneVO data : datas){
            data.setName("");
        }

        return datas;
    }

    @Override
    public List<MapOneVO> searchRatioDataMap(Map<String, Object> requestMap) {

        Map<String,Object> thisMounth = DateUtil.timeMounth();
        requestMap.put("begindate", thisMounth.get("start"));
        requestMap.put("enddate", thisMounth.get("end"));
        MapOneVO dataOne = dataMapOneMapper.searchAllDataMapByMapOne(requestMap);

        Map<String,Object> lastMounth = DateUtil.lastMonth();
        requestMap.put("begindate", lastMounth.get("start"));
        requestMap.put("enddate", lastMounth.get("end"));
        MapOneVO dataTwo = dataMapOneMapper.searchAllDataMapByMapOne(requestMap);

        List<MapOneVO> list = new ArrayList<>();
        MapOneVO data = new MapOneVO();
        data.setName("");
        data.setValue((dataOne.getValue()-dataTwo.getValue())*100/dataTwo.getValue());
        list.add(data);

        return list;
    }

    @Override
    public List<MapOneVO> searchSequential(Map<String, Object> requestMap) {

        List<MapOneVO> list = new ArrayList<>();
        Map<String,Object> thisMounth = DateUtil.timeMounth();
        requestMap.put("begindate", thisMounth.get("start"));
        requestMap.put("enddate", thisMounth.get("end"));
        MapOneVO dataThis = dataMapOneMapper.searchSequentialByMap(requestMap);

        Map<String,Object> lastMonth = DateUtil.lastMonth();
        requestMap.put("begindate", lastMonth.get("start"));
        requestMap.put("enddate", lastMonth.get("end"));
        MapOneVO dataLast = dataMapOneMapper.searchSequentialByMap(requestMap);

        MapOneVO data = new MapOneVO();
        data.setValue( (dataThis.getValue()-dataLast.getValue())*100/dataLast.getValue() );
        data.setName("");
        list.add(data);

        return list;
    }

    @Override
    public List<MapOneVO> searchTotalComplaints(Map<String, Object> requestMap) {

        List<MapOneVO> list = new ArrayList<>();
        Map<String,Object> thisMounth = DateUtil.getThisYearOfNow();
        requestMap.put("begindate", thisMounth.get("start"));
        requestMap.put("enddate", thisMounth.get("end"));
        MapOneVO dataThis = dataMapOneMapper.searchSequentialByMap(requestMap);
        if(dataThis == null){
            dataThis = new MapOneVO();
            dataThis.setName("");
            dataThis.setValue(0);
        }else {
            dataThis.setName("");
        }
        list.add(dataThis);
        return list;
    }

    @Override
    public List<MapOneVO> searchSettlementAmount(Map<String, Object> requestMap) {
        List<MapOneVO> list = new ArrayList<>();
        Map<String,Object> thisMounth = DateUtil.getThisYearOfNow();
        requestMap.put("begindate", thisMounth.get("start"));
        requestMap.put("enddate", thisMounth.get("end"));
        MapOneVO dataThis = dataMapOneMapper.searchSettlementAmountByMap(requestMap);
        if(dataThis == null){
            dataThis = new MapOneVO();
            dataThis.setName("");
            dataThis.setValue(0);
        }else {
            dataThis.setName("");
        }
        list.add(dataThis);
        return list;
    }

    @Override
    public List<MapOneVO> searchTotalEconomy(Map<String, Object> requestMap) {

        List<MapOneVO> list = new ArrayList<>();
        Map<String,Object> thisMounth = DateUtil.getThisYearOfNow();
        requestMap.put("begindate", thisMounth.get("start"));
        requestMap.put("enddate", thisMounth.get("end"));
        MapOneVO dataThis = dataMapOneMapper.searchTotalEconomyByMap(requestMap);
        if(dataThis == null){
            dataThis = new MapOneVO();
            dataThis.setName("");
            dataThis.setValue(0);
        }else {
            dataThis.setName("");
        }
        list.add(dataThis);
        return list;
    }

    @Override
    public List<MapOneVO> searchSettlementAmountOfEconomy(Map<String, Object> requestMap) {
        List<MapOneVO> list = new ArrayList<>();
        Map<String,Object> thisMounth = DateUtil.getThisYearOfNow();
        requestMap.put("begindate", thisMounth.get("start"));
        requestMap.put("enddate", thisMounth.get("end"));
        MapOneVO dataThis = dataMapOneMapper.searchSettlementAmountOfEconomyByMap(requestMap);
        if(dataThis == null){
            dataThis = new MapOneVO();
            dataThis.setName("");
            dataThis.setValue(0);
        }else {
            dataThis.setName("");
        }
        list.add(dataThis);
        return list;
    }


}
