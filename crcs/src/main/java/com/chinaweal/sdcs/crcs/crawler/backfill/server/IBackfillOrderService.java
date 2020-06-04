package com.chinaweal.sdcs.crcs.crawler.backfill.server;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaweal.sdcs.crcs.crawler.backfill.entity.BackfillOrder;

import java.util.List;
import java.util.Map;

/**
 * @author liny
 * @since 2020-03-03
 */
public interface IBackfillOrderService extends IService<BackfillOrder> {


    Map<String,Object> searchBackOrder(Map<String,Object> requestMap);

    Integer searchBackOrderOfHome(Map<String,Object> requestMap);

    void deleteBackOrder(String[] ids);

    void changeBackOrder(String[] ids);

}
