package com.chinaweal.sdcs.crcs.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.system.entity.TOrgunits;
import com.chinaweal.sdcs.crcs.system.mapper.TOrgunitsMapper;
import com.chinaweal.sdcs.crcs.system.service.ITOrgunitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author mosx
 * @since 2020-04-21
 */
@Service
public class TOrgunitsServiceImpl extends ServiceImpl<TOrgunitsMapper, TOrgunits> implements ITOrgunitsService {

    @Autowired
    private ITOrgunitsService iTOrgunitsService;
    @Autowired
    private TOrgunitsMapper tOrgunitsMapper;

    @Override
    public List<Map> selectMessageCascadeData() {

        List<Map> arrayList = tOrgunitsMapper.selectMessageCascadeData();


        return arrayList;
    }
}
