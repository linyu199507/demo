package com.chinaweal.sdcs.crcs.common.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.common.entity.dto.page.PageRequestDto;
import com.chinaweal.sdcs.crcs.common.service.functions.CustomFilterFunction;
import com.chinaweal.sdcs.crcs.common.service.functions.CustomWrapperPackingFunction;
import org.apache.commons.collections.CollectionUtils;

/**
 * 我的IService实现
 * @param <M> mapper
 * @param <T> model 实体
 * @author <a href="https://blog.lroyia.top">lroyia</a>
 * @since 2020/6/1 14:31
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T extends Model> extends ServiceImpl<M, T> implements BaseService<T> {

    @Override
    public IPage<T> defaultPaging(PageRequestDto pageRequestDto, CustomWrapperPackingFunction<T> customWrapperPackingFunction, CustomFilterFunction<T> customFilterFunction) {
        // 参数校验
        pageRequestDto.checkDefault(1, 10, null);

        // 包装条件
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        pageRequestDto.packingParam(queryWrapper);
        if(customWrapperPackingFunction != null){
            customWrapperPackingFunction.packing(queryWrapper);
        }
        // 查询分页
        IPage<T> page = page(new Page<>(pageRequestDto.getCurPage(), pageRequestDto.getPageSize()), queryWrapper.orderBy(true, pageRequestDto.getOrderingRuleBoolean(), pageRequestDto.getOrderField()));

        // 自定义过滤
        if(customFilterFunction != null && CollectionUtils.isNotEmpty(page.getRecords())){
            for (T record : page.getRecords()) {
                customFilterFunction.filter(record);
            }
        }
        return page;
    }
}
