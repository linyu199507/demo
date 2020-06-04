package com.chinaweal.sdcs.crcs.system.mapper;

import com.chinaweal.sdcs.crcs.system.entity.RightsTransformRequest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 维权转换请求记录 Mapper 接口
 * </p>
 *
 * @author <a href="https://blog.lroyia.top">lroyia</a>
 * @since 2020-06-02
 */
public interface RightsTransformRequestMapper extends BaseMapper<RightsTransformRequest> {

    /**
     * 查询最新一个
     * @param rightId 维权信息id
     * @author lroyia
     * @return 获取最新一个转换申请
     */
    RightsTransformRequest selectLastOne(@Param("rightId") String rightId);
}
