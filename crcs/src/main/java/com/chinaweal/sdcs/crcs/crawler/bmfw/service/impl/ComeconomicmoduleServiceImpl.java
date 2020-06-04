package com.chinaweal.sdcs.crcs.crawler.bmfw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.crawler.bmfw.mapper.ComeconomicmoduleMapper;
import com.chinaweal.sdcs.crcs.crawler.bmfw.service.IComeconomicmoduleService;
import com.chinaweal.sdcs.crcs.crawler.bmfw.entity.Comeconomicmodule;
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
public class ComeconomicmoduleServiceImpl extends ServiceImpl<ComeconomicmoduleMapper, Comeconomicmodule> implements IComeconomicmoduleService {
    @Autowired
    private ComeconomicmoduleMapper comeconomicmoduleMapper;

    @Override
    public List<Map> countBySubjectname() {
        return comeconomicmoduleMapper.selectBySubjectname();
    }

    @Override
    public List<Map> countBySubjectnameAndDate(Date begindate, Date enddate) {
        return comeconomicmoduleMapper.selectBySubjectnameAndDate(begindate,enddate);
    }
}
