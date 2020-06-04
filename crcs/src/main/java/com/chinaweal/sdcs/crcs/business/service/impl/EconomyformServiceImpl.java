package com.chinaweal.sdcs.crcs.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinaweal.sdcs.crcs.business.entity.Economyform;
import com.chinaweal.sdcs.crcs.business.mapper.EconomyformMapper;
import com.chinaweal.sdcs.crcs.business.service.IEconomyformService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.statistical.common.entity.BusinessTopCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-08-18
 */
@Service
public class EconomyformServiceImpl extends ServiceImpl<EconomyformMapper, Economyform> implements IEconomyformService {


    @Autowired
    private EconomyformMapper economyformMapper;

    @Override
    public List<BusinessTopCount> selectBySubjectname() {
        return economyformMapper.selectBySubjectname();
    }

    @Override
    public List<BusinessTopCount> selectBySubjectnameAndDate(Date begin, Date end) {
        return economyformMapper.selectByEntnameAndDate(begin,end);
    }

    @Override
    public IPage<Economyform> searchEconomyformList(int current, int size, Map<String, Object> requestMap) {
        Page<Economyform> page = new Page();
        page.setCurrent(current);
        page.setSize(size);
        return economyformMapper.searchEconomyformList(page, requestMap);
    }
}
