package com.chinaweal.sdcs.crcs.bigScreenDisplay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.GeneralVO;

import java.util.List;
import java.util.Map;


/**
 * @author liny
 * @since 2020-03-08
 */
public interface IGeneralService extends IService<GeneralVO> {


    void changeBusinessdept(Map<String, Object> requestMap);
}
