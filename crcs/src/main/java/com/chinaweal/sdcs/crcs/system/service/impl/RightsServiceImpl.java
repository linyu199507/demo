package com.chinaweal.sdcs.crcs.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.system.entity.Rights;
import com.chinaweal.sdcs.crcs.system.mapper.RightsMapper;
import com.chinaweal.sdcs.crcs.system.service.IRightsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-09-02
 */
@Service
public class RightsServiceImpl extends ServiceImpl<RightsMapper, Rights> implements IRightsService {

    @Resource
    private RightsMapper rightsMapper;

    @Override
    public List<List<String>> selectEnterprise(Map<String, Object> requestMap) {

        List<List<String>> data = new ArrayList<>();
        List<String> lists = rightsMapper.selectEnterpriseByRegion(requestMap);
        data.add(lists);

        return data;
    }
}
