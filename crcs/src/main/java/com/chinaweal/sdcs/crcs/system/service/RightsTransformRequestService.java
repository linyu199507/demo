package com.chinaweal.sdcs.crcs.system.service;

import com.chinaweal.sdcs.crcs.system.entity.RightsTransformRequest;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 维权转换请求记录 服务类
 * </p>
 *
 * @author <a href="https://blog.lroyia.top">lroyia</a>
 * @since 2020-06-02
 */
public interface RightsTransformRequestService extends IService<RightsTransformRequest> {

    /**
     * 获取最新一个
     * @param rightId 维权信息id
     * @author lroyia
     * @return 获取最新一个转换申请
     */
    RightsTransformRequest getLastOne(String rightId);
}
