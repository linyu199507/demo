package com.chinaweal.sdcs.crcs.bigScreenDisplay.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.GeneralVO;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.ScreenDisplayVO;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.mapper.GeneralMapper;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.mapper.ScreenDisplayMapper;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.service.IScreenDisplayService;

import com.chinaweal.sdcs.crcs.util.DateUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;

/**
 * 大屏展示数据获取
 */
@Service
public class ScreenDisplayServiceImpl extends ServiceImpl<ScreenDisplayMapper, ScreenDisplayVO> implements IScreenDisplayService{


    @Resource
    private ScreenDisplayMapper screenDisplayMapper;
    @Resource
    private GeneralMapper generalMapper;


    @Override
    public List<ScreenDisplayVO> searchBusTOP10(Map<String, Object> requestMap) {
        Map<String,Object> thisYear = DateUtil.getThisYear();

            requestMap.put("begindate", thisYear.get("start"));
            requestMap.put("enddate", thisYear.get("end"));
        List<ScreenDisplayVO> listOne = screenDisplayMapper.searchBusTOP10ByMap(requestMap);
        listOne = listOne.subList(0,10);

        for(ScreenDisplayVO data : listOne){
            data.setContent(data.getContent()+"  "+data.getValue());
        }

        return listOne;
    }
}
