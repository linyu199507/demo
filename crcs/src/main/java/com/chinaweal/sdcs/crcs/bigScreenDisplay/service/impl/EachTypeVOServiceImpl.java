package com.chinaweal.sdcs.crcs.bigScreenDisplay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.AppliancesVO;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.EachTypeVO;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.mapper.AppliancesVOMapper;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.mapper.EachTypeVOMapper;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.service.IAppliancesVOService;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.service.IEachTypeVOService;
import com.chinaweal.sdcs.crcs.system.entity.Basecodeform;
import com.chinaweal.sdcs.crcs.system.mapper.BasecodeformMapper;
import com.chinaweal.sdcs.crcs.util.DateUtil;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 大屏展示数据获取--近一个月的业务量
 */
@Service
public class EachTypeVOServiceImpl extends ServiceImpl<EachTypeVOMapper, EachTypeVO> implements IEachTypeVOService {


    @Resource
    private EachTypeVOMapper eachTypeVOMapper;
    @Resource
    private BasecodeformMapper basecodeformMapper;


    @Override
    public List<EachTypeVO> searchTotalOfMounth(Map<String, Object> requestMap) {
        List<EachTypeVO> lists = new ArrayList<>();
        EachTypeVO data = new EachTypeVO();
        requestMap = DateUtil.getDateAfterOneMonth();

        List<String> formtypes = new ArrayList<>();
        formtypes.add("01");
        formtypes.add("02");
        formtypes.add("03");

        for( String formtype : formtypes ){
            data = new EachTypeVO();
            requestMap.put("formtype",formtype);
            EachTypeVO entity = eachTypeVOMapper.searchTotalOfMounth(requestMap);
            System.out.println(entity);
            data.setValue(entity.getValue());
            if( "01".equals(formtype) ){
                data.setType("咨询类");
            }else if( "02".equals(formtype) ){
                data.setType("投诉类");
            }else if( "03".equals(formtype) ){
                data.setType("举报类");
            }
            lists.add(data);
        }

        return lists;
    }

    @Override
    public List<EachTypeVO> searchTotalOfToday(Map<String, Object> requestMap) {

        List<EachTypeVO> lists = new ArrayList<>();
        EachTypeVO data = new EachTypeVO();
        requestMap = DateUtil.getDateOfToday();

        data = eachTypeVOMapper.searchTotalOfMounth(requestMap);
        lists.add(data);

        return lists;
    }

    @Override
    public List<EachTypeVO> searchRatioOfToday(Map<String, Object> requestMap) {
        List<EachTypeVO> list = new ArrayList<>();
        requestMap = DateUtil.getDateAfterOneMonth();
        EachTypeVO data = eachTypeVOMapper.searchTotalOfMounth(requestMap);

        Date now = new Date();
        Date date = DateUtils.addDays(now, -30);
        date = DateUtils.addYears(date,-1);
        now = DateUtils.addYears(now,-1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateOfEnd = simpleDateFormat.format(now);
        String dateOfStart = simpleDateFormat.format(date);
        requestMap.put("begindate",dateOfStart);
        requestMap.put("enddate",dateOfEnd);
        EachTypeVO dataOne = eachTypeVOMapper.searchTotalOfMounth(requestMap);
        data.setValue((data.getValue()-dataOne.getValue())*100/dataOne.getValue());
        list.add(data);
        return list;
    }

    @Override
    public List<EachTypeVO> searchRatioOfAll(Map<String, Object> requestMap) {
        List<EachTypeVO> list = new ArrayList<>();
        EachTypeVO data = new EachTypeVO();
        requestMap = DateUtil.getDateAfterOneMonth();
        EachTypeVO entity = eachTypeVOMapper.searchTotalOfMounth(requestMap);
        list.add(entity);
        return list;
    }
}
