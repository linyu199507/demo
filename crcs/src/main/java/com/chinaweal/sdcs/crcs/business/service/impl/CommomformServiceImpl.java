package com.chinaweal.sdcs.crcs.business.service.impl;

import com.chinaweal.sdcs.crcs.business.entity.Commomform;
import com.chinaweal.sdcs.crcs.business.mapper.CommomformMapper;
import com.chinaweal.sdcs.crcs.business.service.ICommomformService;
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
public class CommomformServiceImpl extends ServiceImpl<CommomformMapper, Commomform> implements ICommomformService {

    @Autowired
    private CommomformMapper commomformMapper;

    @Override
    public void insertConsultationform(Commomform commomform) {

    }

    @Override
    public List<BusinessTopCount> countByEntname() {
        return commomformMapper.selectByEntname();
    }

    @Override
    public List<BusinessTopCount> countByEntnameAndDate(Date begin, Date end) {
        return commomformMapper.selectByEntnameAndDate(begin,end);
    }
}
