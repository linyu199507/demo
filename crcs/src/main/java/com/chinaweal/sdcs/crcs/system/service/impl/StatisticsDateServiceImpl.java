package com.chinaweal.sdcs.crcs.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.system.entity.Basecodeform;
import com.chinaweal.sdcs.crcs.system.entity.StatisticsDate;
import com.chinaweal.sdcs.crcs.system.entity.TimesVo;
import com.chinaweal.sdcs.crcs.system.mapper.BasecodeformMapper;
import com.chinaweal.sdcs.crcs.system.mapper.StatisticsDataMapper;
import com.chinaweal.sdcs.crcs.system.service.IStatisticsDateService;
import com.chinaweal.sdcs.crcs.util.DateUtil;
import com.chinaweal.sdcs.crcs.util.TimeSelectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

/**
 * @author chinaweal
 * @Name   Liny
 * @since 2020-1-9
 *
 */
@Service
public class StatisticsDateServiceImpl extends ServiceImpl<StatisticsDataMapper, StatisticsDate> implements IStatisticsDateService {


    @Resource
    private StatisticsDataMapper statisticsDateMapper;
    @Resource
    private BasecodeformMapper basecodeformMapper;
    @Autowired
    private TimeSelectUtil timeSelectUtil;
    /**
     * @author chinaweal
     * @Name   Liny
     * @since 2020-1-12
     *
     */
    public class MyComparator implements Comparator<StatisticsDate>{
        @Override
        public int compare(StatisticsDate o1, StatisticsDate o2) {
            return o2.getRegiontotal()-o1.getRegiontotal();
        }
    }

    @Override
    public Map<String, Object> selectRegionalData(Map<String, Object> requestMap) {

        List<Map<String, String>> areas = new ArrayList<>();
        areas.add(new HashMap<String, String>(){{put("区局","440681");}});
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

        StatisticsDate statisticsDate;
        String key = "";
        String value = "";
        List<StatisticsDate> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        //各地区业务量
        for (Map<String, String> unit : areas) {
            for (Iterator it = unit.keySet().iterator(); it.hasNext(); ) {
                key = (String) it.next();
                value = unit.get(key);
            }
            requestMap.put("region", value);
            statisticsDate = statisticsDateMapper.getStatisticsDateByMap(requestMap);
            list.add(statisticsDate);
            map.put(key,statisticsDate.getRegiontotal());

        }
        StatisticsDate formTotals = statisticsDateMapper.getFormTotalsByMap(requestMap);
        map.put("咨询",formTotals.getCommom());
        map.put("投诉",formTotals.getComplaint());
        map.put("举报",formTotals.getEconomy());
        return map;
    }

    @Override
    public Map<String ,Object> selectComplaintRanking(Map<String, Object> requestMap) {

        Map<String ,Object> map = new HashMap<>();
        List<Object> data = statisticsDateMapper.getComplaintRankingByMap(requestMap);
        for (int i = 0; i < data.size(); i++) {
            StatisticsDate statisticsDate = (StatisticsDate) data.get(i);
            if("".equals(statisticsDate.getRegion()) || "无".equals(statisticsDate.getRegion())) {
                data.remove(i);
            }
        }
        data = data.subList(0,6);
        Collections.reverse(data);
        map.put("all",data);

        Map<String,Object> time = DateUtil.timeMounth();
        requestMap.put("begindate",time.get("start"));
        requestMap.put("enddate",time.get("end"));
        List<Object> dataOne = statisticsDateMapper.getComplaintRankingByMap(requestMap);
        if (dataOne.isEmpty()){

        }else{
            for (int i = 0; i < data.size(); i++) {
                StatisticsDate statisticsDateOne = (StatisticsDate) dataOne.get(i);
                if("".equals(statisticsDateOne.getRegion()) || "无".equals(statisticsDateOne.getRegion())) {
                    dataOne.remove(i);
                }
            }
            dataOne = dataOne.subList(0,6);
            Collections.reverse(dataOne);
        }
        map.put("month",dataOne);

        return map;
    }

    @Override
    public Map<String,Object> selectTenBusiness(Map<String, Object> requestMap) {

        Map<String,Object> map = new HashMap<>();
        List<Object> dataAll = statisticsDateMapper.getTenBusinessByMap(requestMap);
        for (int i = 0; i < 30; i++) {
            StatisticsDate statisticsDate = (StatisticsDate) dataAll.get(i);
            if("".equals(statisticsDate.getRegion()) ||
                    "无".equals(statisticsDate.getRegion()) ||
                     "不详".equals(statisticsDate.getRegion())) {
                dataAll.remove(i);
            }
        }
        dataAll = dataAll.subList(0,10);
        map.put("all",dataAll);

        Map<String,Object> time = DateUtil.timeMounth();
        requestMap.put("begindate",time.get("start"));
        requestMap.put("enddate",time.get("end"));
        List<Object> dataMonth = statisticsDateMapper.getTenBusinessByMap(requestMap);
        if(dataMonth.isEmpty()){

        }else{
            for (int i = 0; i < 30; i++) {
                StatisticsDate statisticsDate = (StatisticsDate) dataMonth.get(i);
                if("".equals(statisticsDate.getRegion()) ||
                        "无".equals(statisticsDate.getRegion()) ||
                        "不详".equals(statisticsDate.getRegion())) {
                    dataMonth.remove(i);
                }
            }
            dataMonth = dataMonth.subList(0,10);
        }

        map.put("month",dataMonth);

        return map;
    }

    @Override
    public Map<String, Object> selectComplaintsArea(Map<String, Object> requestMap) {

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

        StatisticsDate statisticsDate;
        StatisticsDate statisticsDateNow;
        StatisticsDate statisticsDateMonth;
        String key = "";
        String value = "";
        List<StatisticsDate> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        //各地区业务量
        for (Map<String, String> unit : areas) {
            for (Iterator it = unit.keySet().iterator(); it.hasNext(); ) {
                key = (String) it.next();
                value = unit.get(key);
            }
            requestMap.put("region", key);
            requestMap.put("begindate",null);
            requestMap.put("enddate",null);
             statisticsDate = statisticsDateMapper.getComplaintsAreaByMap(requestMap);
             Map<String,Object> now = DateUtil.timeMounth();
            requestMap.put("begindate",now.get("start"));
            requestMap.put("enddate",now.get("end"));
            statisticsDateNow = statisticsDateMapper.getComplaintsAreaByMap(requestMap);

            Map<String,Object> month = DateUtil.lastMonth();
            requestMap.put("begindate",month.get("start"));
            requestMap.put("enddate",month.get("end"));
            statisticsDateMonth = statisticsDateMapper.getComplaintsAreaByMap(requestMap);
            statisticsDate.setMonthsData(statisticsDateMonth.getRegiontotal());
            if(statisticsDateNow == null){
                statisticsDate.setApplealtocase(0);
            }else{
                statisticsDate.setApplealtocase(statisticsDateNow.getRegiontotal());
            }
            statisticsDate.setRegion(key);
            list.add(statisticsDate);
        }
        map.put("all",list);

        return map;
    }

    @Override
    public List<StatisticsDate> selectRegionComplete(Map<String, Object> requestMap) {
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

        StatisticsDate statisticsDate;
        String key = "";
        String value = "";
        List<StatisticsDate> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        //各地区业务量
        for (Map<String, String> unit : areas) {
            for (Iterator it = unit.keySet().iterator(); it.hasNext(); ) {
                key = (String) it.next();
                value = unit.get(key);
            }
            requestMap.put("region", value);
            statisticsDate = statisticsDateMapper.getRegionCompleteByMap(requestMap);
            NumberFormat numberFormat = NumberFormat.getInstance();
            numberFormat.setMaximumFractionDigits(2);
            String transferred = numberFormat.format((float)statisticsDate.getComplaint()/(float)statisticsDate.getRegiontotal()*100);
            String mediation = numberFormat.format((float)statisticsDate.getEconomy()/(float)statisticsDate.getRegiontotal()*100);
            statisticsDate.setTransferred(transferred);
            statisticsDate.setMediation(mediation);
            statisticsDate.setRegion(key);
            list.add(statisticsDate);
        }
        return list;
    }

    @Override
    public List<StatisticsDate> selectNatureAnalysis(Map<String, Object> requestMap) {

        //查询客体分类内容
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "object12345").eq(Basecodeform::getOrglevel,"2");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = basecodeformMapper.selectList(queryWrapper);
        StatisticsDate statisticsDate;
        Map<String,Object> map = new HashMap<>();
        List<StatisticsDate> list = new ArrayList<>();
        for (Basecodeform basecodeform : basecodeforms) {
            String codeid = basecodeform.getCodeid();
            requestMap.put("type",codeid);
            statisticsDate = statisticsDateMapper.getNatureAnalysis(requestMap);
            if(statisticsDate != null) {
                statisticsDate.setRegion(basecodeform.getComment());
                list.add(statisticsDate);
            }
        }
        MyComparator Comparator = new MyComparator();
        Collections.sort(list,Comparator);
        list = list.subList(0,6);
        return list;
    }

    @Override
    public Map<String, Object> selectIllegalBehavior(Map<String, Object> requestMap) {
        //查询涉嫌违法行为分类内容
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "illegal12345").eq(Basecodeform::getOrglevel,"1");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = basecodeformMapper.selectList(queryWrapper);
        StatisticsDate statisticsDateOne;
        StatisticsDate statisticsDateTwo;
        Map<String,Object> map = new HashMap<>();
        List<StatisticsDate> listOne = new ArrayList<>();
        List<StatisticsDate> listTwo = new ArrayList<>();
        for (Basecodeform basecodeform : basecodeforms) {
            String codeid = basecodeform.getCodeid();
            requestMap.put("type",codeid);
            statisticsDateOne = statisticsDateMapper.getIllegalBehaviorByMap(requestMap);
            if(statisticsDateOne != null) {
                statisticsDateOne.setRegion(basecodeform.getComment());
                listOne.add(statisticsDateOne);
            }
        }

        for (Basecodeform basecodeform : basecodeforms) {
            String codeid = basecodeform.getCodeid();
            requestMap.put("type",codeid);
            Map<String,Object> times =  DateUtil.timeMounth();
            requestMap.put("begindate",times.get("start"));
            requestMap.put("enddate",times.get("end"));
            statisticsDateTwo = statisticsDateMapper.getIllegalBehaviorByMap(requestMap);
            if(statisticsDateTwo != null) {
                statisticsDateTwo.setRegion(basecodeform.getComment());
                listTwo.add(statisticsDateTwo);
            }
        }
        MyComparator Comparator = new MyComparator();
        Collections.sort(listOne,Comparator);
        Collections.sort(listTwo,Comparator);
        listOne = listOne.subList(0,6);
        listTwo = listTwo.subList(0,6);
        map.put("all",listOne);
        map.put("month",listTwo);
        return map;
    }

    @Override
    public Map<String,Object> selectHotQuestion(Map<String, Object> requestMap) {
        //查询问题分类内容
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "question12345").eq(Basecodeform::getOrglevel,"1");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = basecodeformMapper.selectList(queryWrapper);
        StatisticsDate statisticsDateOne;
        StatisticsDate statisticsDateTwo;
        Map<String,Object> map = new HashMap<>();
        List<StatisticsDate> listOne = new ArrayList<>();
        List<StatisticsDate> listTwo = new ArrayList<>();
            for (Basecodeform basecodeform : basecodeforms) {
                String codeid = basecodeform.getCodeid();
                requestMap.put("type",codeid);
                statisticsDateOne = statisticsDateMapper.getHotQuestionByMap(requestMap);
                if(statisticsDateOne != null) {
                    statisticsDateOne.setRegion(basecodeform.getComment());
                    listOne.add(statisticsDateOne);
                }
            }
            for (Basecodeform basecodeform : basecodeforms) {
                String codeid = basecodeform.getCodeid();
                requestMap.put("type",codeid);
                Map<String,Object> times =  DateUtil.timeMounth();
                requestMap.put("begindate",times.get("start"));
                requestMap.put("enddate",times.get("end"));
                statisticsDateTwo = statisticsDateMapper.getHotQuestionByMap(requestMap);
                if(statisticsDateTwo != null) {
                    statisticsDateTwo.setRegion(basecodeform.getComment());
                    listTwo.add(statisticsDateTwo);
                }
            }
            MyComparator Comparator = new MyComparator();
            Collections.sort(listOne,Comparator);
            Collections.sort(listTwo,Comparator);
            listOne = listOne.subList(0,6);
            listTwo = listTwo.subList(0,6);
            map.put("all",listOne);
            map.put("month",listTwo);
            return map;
        }



    @Override
    public Map<String, Object> selectOverallBusiness(Map<String, Object> requestMap) {
        List<StatisticsDate> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> mapOne = new HashMap<>();
        Map<String,Object> mapTwo = new HashMap<>();

        mapOne =  DateUtil.timeMounth();
        requestMap.put("begindate",mapOne.get("start"));
        requestMap.put("enddate",mapOne.get("end"));
        StatisticsDate statisticsDateOne = statisticsDateMapper.getOverallBusinessByMap(requestMap);
        list.add(statisticsDateOne);
        map.put("dataList",list);
        mapTwo =  DateUtil.lastMonth();
        requestMap.put("begindate",mapTwo.get("start"));
        requestMap.put("enddate",mapTwo.get("end"));
        StatisticsDate statisticsDateTwo = statisticsDateMapper.getOverallBusinessByMap(requestMap);
//        list.add(statisticsDateTwo);
        System.out.println(list);

        BigDecimal regiontotal = DateUtil.toDivision(statisticsDateOne.getRegiontotal(),statisticsDateTwo.getRegiontotal());
        BigDecimal complaint = DateUtil.toDivision(statisticsDateOne.getComplaint(),statisticsDateTwo.getComplaint());
        BigDecimal economy = DateUtil.toDivision(statisticsDateOne.getEconomy(),statisticsDateTwo.getEconomy());
        BigDecimal commom = DateUtil.toDivision(statisticsDateOne.getCommom(),statisticsDateTwo.getCommom());
        BigDecimal applealtocase = DateUtil.toDivision(statisticsDateOne.getApplealtocase(),statisticsDateTwo.getApplealtocase());
        map.put("regiontotal",regiontotal+"%");
        map.put("complaint",complaint+"%");
        map.put("economy",economy+"%");
        map.put("commom",commom+"%");
        map.put("applealtocase",applealtocase+"%");
        System.out.println(map);

        return map;
    }


    @Override
    public  List<StatisticsDate>  selectHotQuestionChange(Map<String, Object> requestMap) {

        if ("wechat".equals(requestMap.get("type"))){
            QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().like(Basecodeform::getType, "object12345").eq(Basecodeform::getOrglevel,"2");
            queryWrapper.orderByAsc("codeid");
            List<Basecodeform> basecodeforms = basecodeformMapper.selectList(queryWrapper);
            System.out.println(basecodeforms);
            StatisticsDate statisticsDate;
            Map<String,Object> map = new HashMap<>();
            List<StatisticsDate> list = new ArrayList<>();
            List<List<StatisticsDate>> dateList = new ArrayList<>();
            for (Basecodeform basecodeform : basecodeforms) {
                String codeid = basecodeform.getCodeid();
                requestMap.put("type",codeid);
                Map<String,Object> time = DateUtil.timeMounth();
                requestMap.put("begindate",time.get("start"));
                requestMap.put("enddate",time.get("end"));
                statisticsDate = statisticsDateMapper.getNatureAnalysis(requestMap);
                if(statisticsDate != null) {
                    statisticsDate.setRegion(basecodeform.getComment());
                    statisticsDate.setMediation(basecodeform.getCodeid());
                    list.add(statisticsDate);
                }
            }
            MyComparator Comparator = new MyComparator();
            Collections.sort(list,Comparator);
            list = list.subList(0,6);
            return list ;

        }else {
            QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().like(Basecodeform::getType, "object12345").eq(Basecodeform::getOrglevel,"2");
            queryWrapper.orderByAsc("codeid");
            List<Basecodeform> basecodeforms = basecodeformMapper.selectList(queryWrapper);
            StatisticsDate statisticsDate;
            Map<String,Object> map = new HashMap<>();
            List<StatisticsDate> list = new ArrayList<>();
            List<List<StatisticsDate>> dateList = new ArrayList<>();
            for (Basecodeform basecodeform : basecodeforms) {
                String codeid = basecodeform.getCodeid();
                requestMap.put("type",codeid);
                statisticsDate = statisticsDateMapper.getNatureAnalysis(requestMap);
                if(statisticsDate != null) {
                    statisticsDate.setRegion(basecodeform.getComment());
                    statisticsDate.setMediation(basecodeform.getCodeid());
                    list.add(statisticsDate);
                }
            }
            MyComparator Comparator = new MyComparator();
            Collections.sort(list,Comparator);
            list = list.subList(0,6);
            List<TimesVo> times = DateUtil.getSixMonth();
            List<StatisticsDate> newlist = new ArrayList<>();

            for (StatisticsDate date : list ) {
                String name = date.getRegion();
                requestMap.put("type",date.getMediation());
                for ( TimesVo time : times){
                    requestMap.put("begindate",time.getTimestart());
                    requestMap.put("enddate",time.getTimeend());
                    statisticsDate = statisticsDateMapper.getNatureAnalysis(requestMap);
                    if(statisticsDate != null){
                        statisticsDate.setMonthstart(time.getTimestart().substring(0,7));
                        statisticsDate.setRegion(name);
                        newlist.add(statisticsDate);
                    }else {
                        statisticsDate = new StatisticsDate();
                        statisticsDate.setMonthstart(time.getTimestart().substring(0,7));
                        statisticsDate.setRegiontotal(0);
                        statisticsDate.setRegion(name);
                        newlist.add(statisticsDate);
                    }
                }
                dateList.add(newlist);
                System.out.println(dateList);
            }
            return newlist;
        }

    }


    @Override
    public List<StatisticsDate> selectBusinessTotal(Map<String, Object> requestMap) {
        List<StatisticsDate> list = new ArrayList<>();
        StatisticsDate statisticsDate;
        List<TimesVo> times = DateUtil.getSixMonth();

        for (TimesVo time : times){
            requestMap.put("begindate",time.getTimestart());
            requestMap.put("enddate",time.getTimeend());
            statisticsDate = statisticsDateMapper.getBusinessTotal(requestMap);
            statisticsDate.setMonthstart(time.getTimestart().substring(0,7));
            list.add(statisticsDate);
        }

        return list;
    }


}
