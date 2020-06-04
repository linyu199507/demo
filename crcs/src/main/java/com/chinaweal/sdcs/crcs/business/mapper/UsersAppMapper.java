package com.chinaweal.sdcs.crcs.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chinaweal.sdcs.crcs.business.entity.UsersApp;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @param
 * @author mo sheng xin
 * @since
 **/
public interface UsersAppMapper extends BaseMapper<UsersApp> {

    List<Map<String,Object>> selectAcceptMessage();

    String selectUserid(@Param("phone") String phone);

    String selectMobile(@Param("userid") String userid);


}
