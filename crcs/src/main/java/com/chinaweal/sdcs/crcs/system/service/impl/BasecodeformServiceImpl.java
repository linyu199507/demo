package com.chinaweal.sdcs.crcs.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.aicorg.model.AICUser;
import com.chinaweal.sdcs.crcs.system.entity.Basecodeform;
import com.chinaweal.sdcs.crcs.system.entity.Loglist;
import com.chinaweal.sdcs.crcs.system.mapper.BasecodeformMapper;
import com.chinaweal.sdcs.crcs.system.mapper.LoglistMapper;
import com.chinaweal.sdcs.crcs.system.service.IBasecodeformService;
import com.chinaweal.sdcs.crcs.util.RequestUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author chinaweal
 * @since 2019-09-29
 */
@Service
public class BasecodeformServiceImpl extends ServiceImpl<BasecodeformMapper, Basecodeform> implements IBasecodeformService {

    @Autowired
    private BasecodeformMapper basecodeformMapper;
    @Autowired
    private LoglistMapper loglistMapper;

    @Override
    public void addData(Map<String, Object> requestMap) {

        Basecodeform basecodeform = (Basecodeform) RequestUtil.mapToEntity(Basecodeform.class, requestMap.get("basecodeform"));

        if (basecodeform.getParentid() != "" && basecodeform.getParentid() != null) {
            String orglevel = basecodeform.getOrglevel();
            int i = Integer.parseInt(orglevel) + 1;
            orglevel = Integer.toString(i);
            System.out.println(orglevel);
            basecodeform.setOrglevel(orglevel);
        }

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:ss");
        String updatedate = dateFormat.format(date);
        try {
            Date update = dateFormat.parse(updatedate);
            System.out.println(update);
            basecodeform.setUpdatedate(update);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(basecodeform);

        if (StringUtils.isBlank(basecodeform.getId())) {
            basecodeform.setId(UUID.randomUUID().toString());
        }
        System.out.println(basecodeform);
        basecodeformMapper.insert(basecodeform);

    }

    @Override
    public IPage<Basecodeform> findBasecodeformByMap(int currentPage, int size, Map<String, String> requestMap) {
        Page<Basecodeform> page = new Page();
        page.setCurrent(currentPage);
        page.setSize(size);
        return basecodeformMapper.findBasecodeformByMap(page, requestMap);
    }

    @Override
    public void deleteByIds(String[] ids, AICUser aicUser) {
        Loglist loglist = null;
        for (String id : ids) {
            Basecodeform basecodeform = basecodeformMapper.selectById(id);
            if (basecodeform != null) {
                basecodeformMapper.deleteById(basecodeform.getId());
                //保存删除日志
                loglist = new Loglist();
                loglist.setId(UUID.randomUUID().toString());
                loglist.setOperationid(aicUser.getPrimaryKey());
                loglist.setOperationname(aicUser.getName());
                loglist.setOperationinfo("删除codeId为：" + basecodeform.getCodeid() + " 的基础数据。");
                loglist.setOperationid("deleteBatchIds");
                loglist.setOperationtime(new Date());
                loglist.setUpdatedate(new Date());
                loglistMapper.insert(loglist);
            }
        }
    }

    @Override
    @Cacheable(value = "selectBaseCodeTreeCache")
    //设置清除机制
    @CacheEvict(value = "selectBaseCodeTreeCache", allEntries = true, condition = "#orgLevel=='clean'")
    public List<Basecodeform> listBaseCodeByTypeAndLevel(String type, String orgLevel) {
        QueryWrapper<Basecodeform> basecodeformWrapper = new QueryWrapper<>();
        basecodeformWrapper.lambda().eq(Basecodeform::getType, type).eq(Basecodeform::getOrglevel, orgLevel);
        return list(basecodeformWrapper);
    }

    @Override
    public List<String> listDistinctType() {
        return basecodeformMapper.findDistinctType();
    }

    @Override
    public Integer getMaxLevelByType(String type) {
        return basecodeformMapper.getMaxLevelByType(type);

    }

}
