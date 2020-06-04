package com.chinaweal.sdcs.crcs.common.controller;

import com.alibaba.fastjson.JSON;
import com.chinaweal.sdcs.crcs.common.entity.dto.login.LoginMessage;
import com.chinaweal.youfool.framework.rest.RestResult;
import com.chinaweal.youfool.framework.rest.ResultCode;
import com.chinaweal.youfool.framework.shiro.JwtManager;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * BaseController用于存储一些简单的controller层通用方法
 * @author <a href="https://blog.lroyia.top">lroyia</a>
 * @since 2020/6/1 16:29
 **/
public abstract class BaseController {

    public static final RestResult OK = RestResult.ok();

    @Autowired
    public JwtManager jwtManager;

    /**
     * x-token的cookie-key
     */
    private static final String X_TOKEN = "x-token";
    /**
     * 获取登录信息
     * @param request 前端请求
     * @return 获取结果
     */
    public LoginMessage getLoginMessage(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies == null) return null;
        String token = null;
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals(X_TOKEN)){
                token = cookie.getValue();
            }
        }
        if(StringUtils.isBlank(token)) return null;
        Claims claims = jwtManager.parseJWT(token);
        String loginMsg = claims.getSubject();
        if(StringUtils.isBlank(loginMsg)) return null;
        return JSON.toJavaObject(JSON.parseObject(loginMsg), LoginMessage.class);
    }


}
