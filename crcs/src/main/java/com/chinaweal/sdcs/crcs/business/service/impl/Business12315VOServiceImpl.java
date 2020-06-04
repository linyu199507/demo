package com.chinaweal.sdcs.crcs.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.business.entity.Business12315VO;
import com.chinaweal.sdcs.crcs.business.entity.BusinessSearchVO;
import com.chinaweal.sdcs.crcs.business.entity.BusinesslocalVO;
import com.chinaweal.sdcs.crcs.business.mapper.Business12315VOMapper;
import com.chinaweal.sdcs.crcs.business.mapper.BusinessSearchMapper;
import com.chinaweal.sdcs.crcs.business.mapper.BusinesslocalVOMapper;
import com.chinaweal.sdcs.crcs.business.service.IBusiness12315VOService;
import com.chinaweal.sdcs.crcs.business.service.IBusinessSearchService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Service
public class Business12315VOServiceImpl extends ServiceImpl<Business12315VOMapper, Business12315VO> implements IBusiness12315VOService {

    @Autowired
    private Business12315VOMapper business12315VOMapper;

//    @Override
//    public IPage<BusinessSearchVO> searchBusinessInfo(int current, int size, Map<String, Object> requestMap) {
//        Page<BusinessSearchVO> page = new Page();
//        page.setCurrent(current);
//        page.setSize(size);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            if (StringUtils.isNotBlank((String)requestMap.get("duetime"))) {
//                Date sDate = sdf.parse((String)requestMap.get("duetime"));
//                Calendar c = Calendar.getInstance();
//                c.setTime(sDate);
//                c.add(Calendar.DAY_OF_MONTH, 1);
//                sDate = c.getTime();
//                String endDate = sdf.format(sDate);
//                requestMap.put("duetime", endDate);
//            }
//            if (StringUtils.isNotBlank((String)requestMap.get("enddate"))) {
//                Date sDate = sdf.parse((String)requestMap.get("enddate"));
//                Calendar c = Calendar.getInstance();
//                c.setTime(sDate);
//                c.add(Calendar.DAY_OF_MONTH, 1);
//                sDate = c.getTime();
//                String endDate = sdf.format(sDate);
//                requestMap.put("enddate", endDate);
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return businessSearchMapper.searchBusinessInfo(page, requestMap);
//    }
//
//    @Override
//    public BusinessSearchVO searchBusinessInfo(Map<String, String> requestMap) {
//        return businessSearchMapper.searchBusinessInfo(requestMap);
//    }

    @Override
    public Business12315VO searchBusiness12315Info(Map<String, String> requestMap) {
        return business12315VOMapper.searchBusiness12315Info(requestMap);
    }

    @Override
    public String[] searchBusiness12315InfoBysystemsource(Map<String, Object> requestMap) {
        return business12315VOMapper.searchBusiness12315InfoBysystemsource(requestMap);
    }

}
