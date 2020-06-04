package com.chinaweal.sdcs.crcs.business.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.chinaweal.sdcs.crcs.business.entity.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * excel文件操作业务
 *
 * @author ：Lin yu
 * @version : 1.0
 * @since ： 2019/9/11
 */
public interface IExcelService extends IService<Tasklist> {

    /**
     * 导出待办查询结果详情页Word格式
     * @param map
     */
    void wordExportFile(Map<String,Object> map , HttpServletResponse response, String formType, String systemsource) throws IOException;

    /**
     * 导出综合查询报表&&12345系统待办导出
     * @param list
     */
    void ExportBusinessSearchVOFile(List<BusinessSearchVO> list, HttpServletResponse response) throws IOException;

    /**
     * 12315系统待办导出综合查询报表
     * @param list
     */
    void ExportBusinessSearch12315VOFile(List<Business12315VO> list, HttpServletResponse response) throws IOException;

    /**
     * 本地待办导出综合查询报表
     * @param list
     */
    void ExportBusinessSearchlocalVOFile(List<BusinesslocalVO> list, HttpServletResponse response) throws IOException;

    /**
     * 12345系统待办导出综合查询报表
     * @param list
     */
    void ExportBusinessSearch12345VOFile(List<Business12345VO> list, HttpServletResponse response) throws IOException;

}
