package com.chinaweal.sdcs.crcs.system.service.impl;

import com.chinaweal.sdcs.crcs.system.entity.RightsTransformRequest;
import com.chinaweal.sdcs.crcs.system.mapper.RightsTransformRequestMapper;
import com.chinaweal.sdcs.crcs.system.service.RightsTransformRequestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 维权转换请求记录 服务实现类
 * </p>
 *
 * @author <a href="https://blog.lroyia.top">lroyia</a>
 * @since 2020-06-02
 */
@Service
public class RightsTransformRequestServiceImpl extends ServiceImpl<RightsTransformRequestMapper, RightsTransformRequest> implements RightsTransformRequestService {

    @Override
    public RightsTransformRequest getLastOne(String rightId) {
        return baseMapper.selectLastOne(rightId);
    }
}
