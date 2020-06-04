package com.chinaweal.sdcs.crcs.crawler.bmfw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.business.mapper.ConsultationformMapper;
import com.chinaweal.sdcs.crcs.crawler.bmfw.mapper.CommommoduleMapper;
import com.chinaweal.sdcs.crcs.crawler.bmfw.entity.Commommodule;
import com.chinaweal.sdcs.crcs.crawler.bmfw.service.ICommommoduleService;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-08-18
 */
@Service
public class CommommoduleServiceImpl extends ServiceImpl<CommommoduleMapper, Commommodule> implements ICommommoduleService {
    @Autowired
    private CommommoduleMapper commommoduleMapper;

    @Override
    public List<Map> countByEntname() {
        return commommoduleMapper.selectByEntname();
    }

    @Override
    public List<Map> countByEntnameAndData(Date begindate, Date enddate) {
        return commommoduleMapper.selectByEntnameAndDate(begindate,enddate);
    }
}
