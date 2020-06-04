package com.chinaweal.sdcs.crcs.bigScreenDisplay.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.MapChangeVO;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.MapVO;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.mapper.DataMapMapper;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.service.IDataMapService;
import com.chinaweal.sdcs.crcs.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 大屏展示数据获取--顺德区地图
 */
@Service
public class DataMapServiceImpl extends ServiceImpl<DataMapMapper, MapVO> implements IDataMapService{


    @Resource
    private DataMapMapper dataMapMapper;

    @Override
    public List<MapChangeVO> searchDataMap(Map<String, Object> requestMap) {
        List<MapChangeVO> list = new ArrayList<>();

        if("year".equals(requestMap.get("type"))){
            Map<String,Object> thisYear = DateUtil.getThisYear();
            requestMap.put("begindate", thisYear.get("start"));
            requestMap.put("enddate", thisYear.get("end"));
        }else if("mounth".equals(requestMap.get("type"))){
            Map<String,Object> thisMounth = DateUtil.timeMounth();
            requestMap.put("begindate", thisMounth.get("start"));
            requestMap.put("enddate", thisMounth.get("end"));
        }else if("day".equals(requestMap.get("type"))){
            Map<String,Object> today= DateUtil.getDateOfToday();
            requestMap.put("begindate", today.get("begindate"));
            requestMap.put("enddate", today.get("enddate"));
        }



        List<Map<String, String>> areas = new ArrayList<>();
        /*
         *顺序固定:勒流-陈村-乐从-北滘-伦教-大良-容桂-杏坛-龙江-均安
         */
        areas.add(new HashMap<String, String>(){{put("勒流","440681007");}});
        areas.add(new HashMap<String, String>(){{put("陈村","440681005");}});
        areas.add(new HashMap<String, String>(){{put("乐从","440681006");}});
        areas.add(new HashMap<String, String>(){{put("北滘","440681004");}});
        areas.add(new HashMap<String, String>(){{put("伦教","440681003");}});
        areas.add(new HashMap<String, String>(){{put("大良","440681001");}});
        areas.add(new HashMap<String, String>(){{put("容桂","440681002");}});
        areas.add(new HashMap<String, String>(){{put("杏坛","440681009");}});
        areas.add(new HashMap<String, String>(){{put("龙江","440681008");}});
        areas.add(new HashMap<String, String>(){{put("均安","440681010");}});
        String key = "";
        String value = "";
        for (Map<String, String> unit : areas){
            for (Iterator it = unit.keySet().iterator(); it.hasNext(); ) {
                key = (String) it.next();
                value = unit.get(key);
            }
            requestMap.put("region", value);
            MapVO mapVo = dataMapMapper.searchDataMapByMap(requestMap);

            MapChangeVO data = new MapChangeVO();

            //勒流--1
            if("440681007".equals(requestMap.get("region")) && mapVo.getInfo() != null){
                data.setLng(113.205046);
                data.setLat(22.974268);
                data.setInfo(mapVo.getInfo().toString());
            }else if ("440681007".equals(requestMap.get("region")) && mapVo.equals(null)){
                data = new MapChangeVO();
                data.setLng(113.205046);
                data.setLat(22.974268);
                data.setInfo("0");
            }
            //陈村--2
            if("440681005".equals(requestMap.get("region")) && mapVo.getInfo() != null){
                data.setLng(113.109452);
                data.setLat(22.9288309);
                data.setInfo(mapVo.getInfo().toString());
            }else if ("440681005".equals(requestMap.get("region")) && mapVo.equals(null)){
                data = new MapChangeVO();
                data.setLng(113.109452);
                data.setLat(22.9288309);
                data.setInfo("0");
            }
            //乐从--3
            if("440681006".equals(requestMap.get("region")) && mapVo.getInfo() != null){
                data.setLng(113.222791);
                data.setLat(22.917672);
                data.setInfo(mapVo.getInfo().toString());
            }else if ("440681006".equals(requestMap.get("region")) && mapVo.equals(null)){
                data = new MapChangeVO();
                data.setLng(113.222791);
                data.setLat(22.917672);
                data.setInfo("0");
            }
            //北滘--4
            if("440681004".equals(requestMap.get("region")) && mapVo.getInfo() != null){
                data.setLng(113.075209);
                data.setLat(22.851551);
                data.setInfo(mapVo.getInfo().toString());
            }else if ("440681004".equals(requestMap.get("region")) && mapVo.equals(null)){
                data = new MapChangeVO();
                data.setLng(113.075209);
                data.setLat(22.851551);
                data.setInfo("0");
            }
            //伦教--5
            if("440681003".equals(requestMap.get("region")) && mapVo.getInfo() != null){
                data.setLng(113.171441);
                data.setLat(22.846092);
                data.setInfo(mapVo.getInfo().toString());
            }else if ("440681003".equals(requestMap.get("region")) && mapVo.equals(null)){
                data = new MapChangeVO();
                data.setLng(113.171441);
                data.setLat(22.846092);
                data.setInfo("0");
            }
            //大良--6
            if("440681001".equals(requestMap.get("region")) && mapVo.getInfo() != null){
                data.setLng(113.251721);
                data.setLat(22.866035);
                data.setInfo(mapVo.getInfo().toString());
            }else if ("440681001".equals(requestMap.get("region")) && mapVo.equals(null)){
                data = new MapChangeVO();
                data.setLng(113.251721);
                data.setLat(22.866035);
                data.setInfo("0");
            }
            //容桂--7
            if("440681002".equals(requestMap.get("region")) && mapVo.getInfo() != null){
                data.setLng(113.306984);
                data.setLat(22.812132);
                data.setInfo(mapVo.getInfo().toString());
            }else if ("440681002".equals(requestMap.get("region")) && mapVo.equals(null)){
                data = new MapChangeVO();
                data.setLng(113.306984);
                data.setLat(22.812132);
                data.setInfo("0");
            }
            //杏坛--8
            if("440681009".equals(requestMap.get("region")) && mapVo.getInfo() != null){
                data.setLng(113.142993);
                data.setLat(22.782828);
                data.setInfo(mapVo.getInfo().toString());
            }else if ("440681009".equals(requestMap.get("region")) && mapVo.equals(null)){
                data = new MapChangeVO();
                data.setLng(113.142993);
                data.setLat(22.782828);
                data.setInfo("0");
            }
            //龙江--9
            if("440681008".equals(requestMap.get("region")) && mapVo.getInfo() != null){
                data.setLng(113.251479);
                data.setLat(22.766709);
                data.setInfo(mapVo.getInfo().toString());
            }else if ("440681008".equals(requestMap.get("region")) && mapVo.equals(null)){
                data = new MapChangeVO();
                data.setLng(113.251479);
                data.setLat(22.766709);
                data.setInfo("0");
            }
            //均安--10
            if("440681010".equals(requestMap.get("region")) && mapVo.getInfo() != null){
                data.setLng(113.154679);
                data.setLat(22.716025);
                data.setInfo(mapVo.getInfo().toString());
            }else if ("440681010".equals(requestMap.get("region")) && mapVo.equals(null)){
                data = new MapChangeVO();
                data.setLng(113.154679);
                data.setLat(22.716025);
                data.setInfo("0");
            }
            list.add(data);
        }
        return list;
    }




}
