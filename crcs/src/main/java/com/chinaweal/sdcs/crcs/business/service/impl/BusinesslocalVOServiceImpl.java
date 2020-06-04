package com.chinaweal.sdcs.crcs.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.business.entity.BusinesslocalVO;
import com.chinaweal.sdcs.crcs.business.mapper.BusinesslocalVOMapper;
import com.chinaweal.sdcs.crcs.business.service.IBusinesslocalVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BusinesslocalVOServiceImpl extends ServiceImpl<BusinesslocalVOMapper, BusinesslocalVO> implements IBusinesslocalVOService {

    @Autowired
    private BusinesslocalVOMapper businesslocalVOMapper;

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
    public BusinesslocalVO searchBusinesslocalInfo(Map<String, String> requestMap) {
        return businesslocalVOMapper.searchBusinesslocalInfo(requestMap);
    }

    @Override
    public String[] searchBusinesslocalInfoBysystemsource(Map<String, Object> requestMap) {
        return businesslocalVOMapper.searchBusinesslocalInfoBysystemsource(requestMap);
    }
}
