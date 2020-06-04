package com.chinaweal.sdcs.crcs.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.system.entity.Basecodeform;
import com.chinaweal.sdcs.crcs.system.entity.EconomySituation;
import com.chinaweal.sdcs.crcs.system.mapper.BasecodeformMapper;
import com.chinaweal.sdcs.crcs.system.mapper.EconomySituationMapper;
import com.chinaweal.sdcs.crcs.system.service.IEconomySituationService;
import com.chinaweal.sdcs.crcs.util.DateUtil;
import com.chinaweal.sdcs.crcs.util.TimeSelectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author chinaweal
 * @Name   Liny
 * @since 2020-02-05
 */
@Service
public class EconomySituationServiceImpl extends ServiceImpl<EconomySituationMapper, EconomySituation> implements IEconomySituationService {


    @Resource
    private EconomySituationMapper economySituationMapper;
    @Resource
    private BasecodeformMapper basecodeformMapper;
    @Autowired
    private TimeSelectUtil timeSelectUtil;

    @Override
    public Map<String,Object> selectEconomydata(Map<String, Object> requestMap) {

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

        Map<String,Object> thisYear = DateUtil.getThisYear();

        Map<String,Object> lastYear = DateUtil.getLastYear();
        if(requestMap.get("begindate")==null || requestMap.get("begindate")==""){
            String key = "";
            String value = "";

            for (Map<String, String> unit : areas) {
                for (Iterator it = unit.keySet().iterator(); it.hasNext(); ) {
                    key = (String) it.next();
                    value = unit.get(key);
                }
                requestMap.put("region",value);
                requestMap.put("begindate", thisYear.get("start"));
                requestMap.put("enddate", thisYear.get("end"));
                EconomySituation dataOne = economySituationMapper.selectEconomydataByMap(requestMap);
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

                requestMap.put("begindate", lastYear.get("start"));
                requestMap.put("enddate", lastYear.get("end"));

                EconomySituation dataTwo = economySituationMapper.selectEconomydataByMap(requestMap);
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
        }else{
            return timeSelectUtil.getEconomydata(requestMap);
        }
    }

    @Override
    public Map<String, Object> selectEconomyIllegal(Map<String, Object> requestMap) {

        //查询涉嫌违法行为分类内容
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "illegal12345").eq(Basecodeform::getOrglevel,"1");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = basecodeformMapper.selectList(queryWrapper);

        Map<String,Object> map = new HashMap<>();
        List<EconomySituation> listOne = new ArrayList<>();

        for (Basecodeform basecodeform : basecodeforms) {
            String codeid = basecodeform.getCodeid();
            requestMap.put("type", codeid);
            Map<String,Object> thisYear =  DateUtil.getThisYear();
//            requestMap.put("begindate", thisYear.get("start"));
//            requestMap.put("enddate", thisYear.get("end"));
//
            if(requestMap.get("firstdate")!=null || !"".equals(requestMap.get("firstdate"))) {
                requestMap.put("begindate", requestMap.get("firstdate"));
                requestMap.put("enddate", requestMap.get("lastdate"));
            }else{
                requestMap.put("begindate", thisYear.get("start"));
                requestMap.put("enddate", thisYear.get("end"));
            }
            EconomySituation dataOne = economySituationMapper.selectEconomyIllegalByMap(requestMap);
            if (dataOne != null){
                dataOne.setBustype(basecodeform.getComment());
            }else{
                dataOne = new EconomySituation();
                dataOne.setBustype(basecodeform.getComment());
                dataOne.setAccepttotal(0);
                dataOne.setEndtotal(0);
                dataOne.setRecordtotal(0);
            }
            listOne.add(dataOne);
        }
        listOne.sort(Comparator.comparingInt(EconomySituation::getRecordtotal));
        map.put("this",listOne);

        return map;
    }
}
