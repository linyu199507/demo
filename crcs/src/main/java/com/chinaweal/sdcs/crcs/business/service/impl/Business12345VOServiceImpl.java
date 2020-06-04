package com.chinaweal.sdcs.crcs.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.business.entity.Business12315VO;
import com.chinaweal.sdcs.crcs.business.entity.Business12345VO;
import com.chinaweal.sdcs.crcs.business.mapper.Business12315VOMapper;
import com.chinaweal.sdcs.crcs.business.mapper.Business12345VOMapper;
import com.chinaweal.sdcs.crcs.business.service.IBusiness12315VOService;
import com.chinaweal.sdcs.crcs.business.service.IBusiness12345VOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class Business12345VOServiceImpl extends ServiceImpl<Business12345VOMapper, Business12345VO> implements IBusiness12345VOService {

    @Autowired
    private Business12345VOMapper business12345VOMapper;

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
    public Business12345VO searchBusiness12345Info(Map<String, String> requestMap) {
        return business12345VOMapper.searchBusiness12345Info(requestMap);
    }

    @Override
    public String[] searchBusiness12345InfoBysystemsource(Map<String, Object> requestMap) {
        return business12345VOMapper.searchBusiness12345InfoBysystemsource(requestMap);
    }

}
