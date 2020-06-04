package com.chinaweal.sdcs.crcs.service.functions;


/**
 * 自定义过滤function
 * @param <T> service对应实体
 * @author <a href="https://blog.lroyia.top">lroyia</a>
 * @since 2020/6/1 14:31
 */
@FunctionalInterface
public interface CustomFilterFunction<T> {
    /**
     * 过滤字段
     * @param each 实体
     */
    void filter(T each);
}
