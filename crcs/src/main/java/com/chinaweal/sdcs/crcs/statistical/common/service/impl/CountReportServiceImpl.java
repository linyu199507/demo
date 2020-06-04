package com.chinaweal.sdcs.crcs.statistical.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chinaweal.sdcs.crcs.business.entity.*;
import com.chinaweal.sdcs.crcs.business.mapper.*;
import com.chinaweal.sdcs.crcs.business.service.ITasklistService;
import com.chinaweal.sdcs.crcs.statistical.common.entity.BusinessTopCount;
import com.chinaweal.sdcs.crcs.statistical.common.entity.RegionDateFinish;
import com.chinaweal.sdcs.crcs.statistical.common.service.ICountReportService;
import com.chinaweal.sdcs.crcs.statistical.common.util.TimeRelevantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: NaJim
 * @Date: 2019/9/29
 */
@Service
public class CountReportServiceImpl implements ICountReportService {

    @Autowired
    private CommomformMapper commomformMapper;

    @Autowired
    private EconomyformMapper economyformMapper;

    @Autowired
    private ComplaintformMapper complaintformMapper;

    @Autowired
    private ConsultationformMapper consultationformMapper;

    @Autowired
    private TasklistMapper tasklistMapper;

    @Qualifier("tasklistServiceImpl")
    @Autowired
    private ITasklistService iTasklistService;


    @Override
    public Map<String, Object> searchByRegion(String[] regions) {
        Map<String,Object> map = new HashMap<>();
        for(String region:regions){
            List<BusinessTopCount> list = new ArrayList<>();
            QueryWrapper<Commomform> wrappercomm = new QueryWrapper<>();
            wrappercomm.lambda().eq(Commomform::getRegion, region);
            Integer countcomm = commomformMapper.selectCount(wrappercomm);
            BusinessTopCount commomform = new BusinessTopCount("Commomform", countcomm);
            list.add(commomform);
            QueryWrapper<Economyform> wrappereco = new QueryWrapper<>();
            wrappereco.lambda().eq(Economyform::getRegion, region);
            Integer counteco = economyformMapper.selectCount(wrappereco);
            BusinessTopCount economyform = new BusinessTopCount("Economyform", counteco);
            list.add(economyform);
            QueryWrapper<Complaintform> wrappercom = new QueryWrapper<>();
            wrappercom.lambda().eq(Complaintform::getRegion, region);
            Integer countcom = complaintformMapper.selectCount(wrappercom);
            BusinessTopCount complaintform = new BusinessTopCount("Complaintform", countcom);
            list.add(complaintform);
            map.put(region,list);
        }
        return map;
    }

    @Override
    public Map<String, Object> searchByRegionAndDate(String[] regions, Date begindate, Date enddate) {
        Map<String,Object> map = new HashMap<>();
        for(String region:regions){
            List<BusinessTopCount> regionDateFinishList = new ArrayList<>();
            QueryWrapper<Commomform> wrappercomm = new QueryWrapper<>();
            wrappercomm.lambda().eq(Commomform::getRegion, region);
            wrappercomm.lambda().between(Commomform::getUpdatedate, begindate,enddate);
            Integer countcomm = commomformMapper.selectCount(wrappercomm);
            BusinessTopCount commomform = new BusinessTopCount("Commomform", countcomm);
            regionDateFinishList.add(commomform);
            QueryWrapper<Economyform> wrappereco = new QueryWrapper<>();
            wrappereco.lambda().eq(Economyform::getRegion, region);
            wrappereco.lambda().between(Economyform::getRecorddate, begindate,enddate);
            Integer counteco = economyformMapper.selectCount(wrappereco);
            BusinessTopCount economyform = new BusinessTopCount("economyform", counteco);
            regionDateFinishList.add(economyform);
            QueryWrapper<Complaintform> wrappercom = new QueryWrapper<>();
            wrappercom.lambda().eq(Complaintform::getRegion, region);
            wrappercom.lambda().between(Complaintform::getRecorddate, begindate,enddate);
            Integer countcom = complaintformMapper.selectCount(wrappercom);
            BusinessTopCount complaintform = new BusinessTopCount("complaintform", countcom);
            regionDateFinishList.add(complaintform);
            map.put(region,regionDateFinishList);
        }
        return map;
    }

    @Override
    public Map<String, Object> searchByRegionAndTimes(String[] regions) {
        Map<String,Object> map = new HashMap<>();
        QueryWrapper<Tasklist> wrapperformtype01 = new QueryWrapper<>();
        wrapperformtype01.lambda().eq(Tasklist::getFormtype, "01");
        Integer formtypecount01 = tasklistMapper.selectCount(wrapperformtype01);
        QueryWrapper<Tasklist> wrapperformtype02 = new QueryWrapper<>();
        wrapperformtype02.lambda().eq(Tasklist::getFormtype, "02");
        Integer formtypecount02 = tasklistMapper.selectCount(wrapperformtype02);
        QueryWrapper<Tasklist> wrapperformtype03 = new QueryWrapper<>();
        wrapperformtype03.lambda().eq(Tasklist::getFormtype, "03");
        Integer formtypecount03 = tasklistMapper.selectCount(wrapperformtype03);
        map.put("consultationformtotle",formtypecount01);
        map.put("complaintformtotle",formtypecount02);
        map.put("economyformtotle",formtypecount03);
        for (String regionstr:regions){
            List<RegionDateFinish> list = new ArrayList();
            for (int i=1;i<12;i++){
                Date begindate = TimeRelevantUtil.dateWrapper(2018, i);
                Date enddate = TimeRelevantUtil.dateWrapper(2018, (i + 1));
                Integer integer01 = consultationformMapper.selectcountbyfinishregion(regionstr,begindate,enddate);
                RegionDateFinish regionDateFinish01 = new RegionDateFinish("consultationform",i,"2018",integer01);
                list.add(regionDateFinish01);
                Integer integer02 = complaintformMapper.selectcountbyfinishregion(regionstr,begindate,enddate);
                RegionDateFinish regionDateFinish02 = new RegionDateFinish("complaintform",i,"2018",integer02);
                list.add(regionDateFinish02);
                Integer integer03 = economyformMapper.selectcountbyfinishregion(regionstr,begindate,enddate);
                RegionDateFinish regionDateFinish03 = new RegionDateFinish("economyform",i,"2018",integer03);
                list.add(regionDateFinish03);
            }
            Date begindate = TimeRelevantUtil.dateWrapper(2018, 12);
            Date enddate = TimeRelevantUtil.dateWrapper(2019, 1);
            Integer integer01 = consultationformMapper.selectcountbyfinishregion(regionstr,begindate,enddate);
            RegionDateFinish regionDateFinish01 = new RegionDateFinish("consultationform",12,"2018",integer01);
            list.add(regionDateFinish01);
            Integer integer02 = complaintformMapper.selectcountbyfinishregion(regionstr,begindate,enddate);
            RegionDateFinish regionDateFinish02 = new RegionDateFinish("complaintform",12,"2018",integer02);
            list.add(regionDateFinish02);
            Integer integer03 = economyformMapper.selectcountbyfinishregion(regionstr,begindate,enddate);
            RegionDateFinish regionDateFinish03 = new RegionDateFinish("economyform",12,"2018",integer03);
            list.add(regionDateFinish03);

            for (int i=1;i<12;i++){
                Date begindate19 = TimeRelevantUtil.dateWrapper(2019, i);
                Date enddate19 = TimeRelevantUtil.dateWrapper(2019, (i + 1));
                Integer integer019 = consultationformMapper.selectcountbyfinishregion(regionstr,begindate19,enddate19);
                RegionDateFinish regionDateFinish019 = new RegionDateFinish("consultationform",i,"2019",integer019);
                list.add(regionDateFinish019);
                Integer integer029 = complaintformMapper.selectcountbyfinishregion(regionstr,begindate19,enddate19);
                RegionDateFinish regionDateFinish029 = new RegionDateFinish("complaintform",i,"2019",integer029);
                list.add(regionDateFinish029);
                Integer integer039 = economyformMapper.selectcountbyfinishregion(regionstr,begindate19,enddate19);
                RegionDateFinish regionDateFinish039 = new RegionDateFinish("economyform",i,"2019",integer039);
                list.add(regionDateFinish039);
            }
            Date begindate19 = TimeRelevantUtil.dateWrapper(2019, 12);
            Date enddate19 = TimeRelevantUtil.dateWrapper(2020,1);
            Integer integer019 = consultationformMapper.selectcountbyfinishregion(regionstr,begindate19,enddate19);
            RegionDateFinish regionDateFinish019 = new RegionDateFinish("consultationform",12,"2019",integer019);
            list.add(regionDateFinish019);
            Integer integer029 = complaintformMapper.selectcountbyfinishregion(regionstr,begindate19,enddate19);
            RegionDateFinish regionDateFinish029 = new RegionDateFinish("complaintform",12,"2019",integer029);
            list.add(regionDateFinish029);
            Integer integer039 = economyformMapper.selectcountbyfinishregion(regionstr,begindate19,enddate19);
            RegionDateFinish regionDateFinish039 = new RegionDateFinish("economyform",12,"2019",integer039);
            list.add(regionDateFinish039);

            map.put(regionstr,list);
        }
        return map;
    }

    @Override
    public Map<String, Object> searchCountByDate(List<String> betweenDate) {
        int weekNum01 = 0;int weekNum02 = 0;int weekNum03 = 0;int weekNum04 = 0;
        int weekNum05 = 0;int weekNum06 = 0;int weekNum07 = 0;
        Map<String, Object> map = new HashMap<>();
        for (int i = 0;i<betweenDate.size();i++){
            String date = betweenDate.get(i);
            Date realDate = TimeRelevantUtil.strToDate(date);
            QueryWrapper<Tasklist> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(Tasklist::getAssigndate, realDate);
            int count = iTasklistService.count(wrapper);
            String weeknum = TimeRelevantUtil.dateToWeek(date);
            switch (weeknum){
                case "sunday":
                    weekNum07 = weekNum07 + count;break;
                case "monday":
                    weekNum01 = weekNum01 + count;break;
                case "tuesday":
                    weekNum02 = weekNum02 + count;break;
                case "wednesday":
                    weekNum03 = weekNum03 + count;break;
                case "thursday":
                    weekNum04 = weekNum04 + count;break;
                case "friday":
                    weekNum05 = weekNum05 + count;break;
                case "saturday":
                    weekNum06 = weekNum06 + count;break;
            }
        }
       map.put("sunday",weekNum07);
       map.put("monday",weekNum01);
       map.put("tuesday",weekNum02);
       map.put("wednesday",weekNum03);
       map.put("thursday",weekNum04);
       map.put("friday",weekNum05);
       map.put("saturday",weekNum06);
       return map;
    }

    @Override
    public Map<String, Object> searchRegionCountByDate(String[] regions, Date begindate, Date enddate) {
        Map<String,Object> map = new HashMap<>();
        for(String region:regions){
            List<BusinessTopCount> list = new ArrayList<>();
            QueryWrapper<Commomform> wrappercomm = new QueryWrapper<>();
            wrappercomm.lambda().eq(Commomform::getRegion, region);
            wrappercomm.lambda().between(Commomform::getUpdatedate, begindate,enddate);
            Integer countcomm = commomformMapper.selectCount(wrappercomm);
            QueryWrapper<Economyform> wrappereco = new QueryWrapper<>();
            wrappereco.lambda().eq(Economyform::getRegion, region);
            wrappereco.lambda().between(Economyform::getRecorddate, begindate,enddate);
            Integer counteco = economyformMapper.selectCount(wrappereco);
            QueryWrapper<Complaintform> wrappercom = new QueryWrapper<>();
            wrappercom.lambda().eq(Complaintform::getRegion, region);
            wrappercom.lambda().between(Complaintform::getRecorddate, begindate,enddate);
            Integer countcom = complaintformMapper.selectCount(wrappercom);
            Integer countAll = countcom+countcomm+counteco;
            map.put(region,countAll);
        }
        return map;
    }


}
