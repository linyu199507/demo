package com.chinaweal.sdcs.crcs.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinaweal.sdcs.crcs.business.entity.Consultationform;
import com.chinaweal.sdcs.crcs.business.entity.Customerinfo;
import com.chinaweal.sdcs.crcs.business.mapper.ConsultationformMapper;
import com.chinaweal.sdcs.crcs.business.mapper.CustomerinfoMapper;
import com.chinaweal.sdcs.crcs.business.service.ICustomerinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-08-18
 */
@Service
public class CustomerinfoServiceImpl extends ServiceImpl<CustomerinfoMapper, Customerinfo> implements ICustomerinfoService {

    @Autowired
    private ConsultationformMapper consultationformMapper;

    @Override
    public IPage<Consultationform> searchConsultationformList(int current, int size, Map<String, Object> requestMap) {
        Page<Consultationform> page = new Page();
        page.setCurrent(current);
        page.setSize(size);
        return consultationformMapper.searchConsultationformList(page, requestMap);
    }
}
