package com.chinaweal.sdcs.crcs.crawler.bmfw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.crawler.bmfw.entity.Complaintmodule;
import com.chinaweal.sdcs.crcs.crawler.bmfw.mapper.ComplaintmoduleMapper;
import com.chinaweal.sdcs.crcs.crawler.bmfw.service.IComplaintmoduleService;
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
public class ComplaintmoduleServiceImpl extends ServiceImpl<ComplaintmoduleMapper, Complaintmodule> implements IComplaintmoduleService {
    @Autowired
    private ComplaintmoduleMapper complaintmoduleMapper;

    @Override
    public List<Map> countBySubjectname() {
        return complaintmoduleMapper.selectBySubjectname();
    }


    @Override
    public List<Map> countBySubjectnameAndDate(Date begindate, Date enddate) {
        return complaintmoduleMapper.selectBySubjectnameAndDate(begindate,enddate);
    }
}
