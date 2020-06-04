package com.chinaweal.sdcs.crcs.util;

import org.apache.commons.lang3.CharSequenceUtils;

/**
 * 字符串工具类（扩展）
 * @author <a href="https://blog.lroyia.top">lroyia</a>
 * @since 2020/6/1 17:02
 **/
public abstract class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * obj转字符串（空针转空字符串）
     * @param obj obj
     * @author lroyia
     * @return String
     */
    public static String tranObject(Object obj){
        if(obj == null){
            return org.apache.commons.lang3.StringUtils.EMPTY;
        }
        return obj.toString();
    }
}
