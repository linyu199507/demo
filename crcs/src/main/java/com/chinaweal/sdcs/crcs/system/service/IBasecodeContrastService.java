package com.chinaweal.sdcs.crcs.system.service;

import com.chinaweal.sdcs.crcs.system.entity.BasecodeContrast;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author chinaweal
 * @since 2019-11-28
 */
public interface IBasecodeContrastService extends IService<BasecodeContrast> {
    /**
     * 行业分类
     */
    String INDUSTRY = "industry";
    /**
     * 问题分类
     */
    String QUESTION = "question";
    /**
     * 侵权类型
     */
    String TORT = "tort";
    /**
     * 企业类型（市场主体）
     */
    String ENTERPRISE = "enterprise";
    /**
     * 客体分类
     */
    String Obj = "object";

    /**
     * 查询全国12315代码
     *
     * @param fsCode 佛山12345代码
     * @param type   根据基础类型
     */
    String getCountryCodeByFsCodeAndType(String fsCode, String type);

    /**
     * 查询全国12315代码
     *
     * @param fsName 佛山12345代码
     * @param type   根据基础类型
     */
    String getCountryCodeByFsNameAndType(String fsName, String type);

    /**
     * 查询全国12315代码
     *
     * @param countryName 全国12315名称
     * @param type   根据基础类型
     */
    String getFsCodeByCountryNameAndType(String countryName, String type);

    /**
     * 查询全国12315代码
     *
     * @param countryCode 全国12315名称
     * @param type   根据基础类型
     */
    String getFsCodeByCountryCodeAndType(String countryCode, String type);
}
