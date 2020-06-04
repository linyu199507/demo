package com.chinaweal.sdcs.crcs.common.service.functions;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * 自定义参数包装function
 * @param <T> service对应实体
 * @author <a href="https://blog.lroyia.top">lroyia</a>
 * @since 2020/6/1 14:31
 */
@FunctionalInterface
public interface CustomWrapperPackingFunction<T extends Model> {
    /**
     * 参数打包方法
     * @param wrapper 参数包装
     */
    void packing(QueryWrapper<T> wrapper);
}
