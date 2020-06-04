package com.chinaweal.sdcs.crcs.statistical.crawler.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chinaweal.sdcs.crcs.business.entity.Tasklist;
import com.chinaweal.sdcs.crcs.business.service.ITasklistService;
import com.chinaweal.sdcs.crcs.statistical.crawler.service.IRediCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @Author: NaJim
 * @Date: 2019/10/8
 */
@Service
public class RediCrawlerServiceImpl implements IRediCrawlerService {

    @Qualifier("tasklistServiceImpl")
    @Autowired
    private ITasklistService iTasklistService;

    @Override
    public String searchSysByCid(String Cid) {
        QueryWrapper<Tasklist> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Tasklist::getCaseguid, Cid);
        Tasklist tasklist = iTasklistService.getOne(wrapper,false);
        return tasklist.getSystemsource();
    }
}
