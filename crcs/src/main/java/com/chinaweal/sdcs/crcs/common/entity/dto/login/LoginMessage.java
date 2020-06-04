package com.chinaweal.sdcs.crcs.common.entity.dto.login;

/**
 * 登录信息实体
 * @author <a href="https://blog.lroyia.top">lroyia</a>
 * @since 2020/6/1 16:34
 */
public class LoginMessage{
    private String userId;

    private String username;

    private String host;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
