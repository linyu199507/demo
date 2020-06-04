package com.chinaweal.sdcs.crcs.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinaweal.sdcs.crcs.business.entity.Complaintform;
import com.chinaweal.sdcs.crcs.business.mapper.ComplaintformMapper;
import com.chinaweal.sdcs.crcs.business.service.IComplaintformService;
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
public class ComplaintformServiceImpl extends ServiceImpl<ComplaintformMapper, Complaintform> implements IComplaintformService {

    @Autowired
    private ComplaintformMapper complaintformMapper;

    @Override
    public List<BusinessTopCount> selectBySubjectname() {
        return complaintformMapper.selectBySubjectname();
    }

    @Override
    public List<BusinessTopCount> selectBySubjectnameAndDate(Date begin, Date end) {
        return complaintformMapper.selectByEntnameAndDate(begin,end);
    }

    @Override
    public IPage<Complaintform> searchComplaintformList(int current, int size, Map<String, Object> requestMap) {
        Page<Complaintform> page = new Page();
        page.setCurrent(current);
        page.setSize(size);
        return complaintformMapper.searchComplaintformList(page, requestMap);
    }
}
