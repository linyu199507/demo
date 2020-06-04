package com.chinaweal.sdcs.crcs.business.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chinaweal.sdcs.crcs.business.entity.Zhistory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author chinaweal
 * @since 2020-03-17
 */
public interface IZhistoryService extends IService<Zhistory> {

    void manualConsumption();

    void manualEconomy();

    void selectEconomyHistory();

    void selectComplaintHistory();

}
