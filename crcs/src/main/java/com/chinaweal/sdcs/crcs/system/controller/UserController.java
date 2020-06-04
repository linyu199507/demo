package com.chinaweal.sdcs.crcs.system.controller;

import com.chinaweal.aicorg.model.AICOrg;
import com.chinaweal.aicorg.model.AICUser;
import com.chinaweal.aicorg.services.OrgUM;
import com.chinaweal.youfool.file.ConfigPathUtil;
import com.chinaweal.youfool.framework.rest.RestResult;
import com.chinaweal.youfool.framework.rest.ResultCode;
import com.chinaweal.youfool.framework.shiro.JwtManager;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "UserController", description = "用户接口类")
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private OrgUM orgUM;
    @Autowired
    private JwtManager jwtManager;

    /**
     * 用户登录登陆
     */
    @ApiOperation(
            tags = "UserController",
            value = "用户登录登陆接口")
    @PostMapping("login")
    @ResponseBody
    public RestResult login(@RequestBody Map<String, String> requestMap, HttpServletRequest request) {
        String username = requestMap.get("username");
        if(username.indexOf("@") < 0  && !username.endsWith("@fsgs")){
            username += "@fsgs";
        }
        //登录
        AICUser aicUser = orgUM.login(username, requestMap.get("password"));
        if (aicUser != null) {
            JSONObject json = new JSONObject();
            json.put("userId", aicUser.getPrimaryKey());
            json.put("username", aicUser.getEname());
            json.put("host", request.getRemoteHost());
            String token = jwtManager.createJWT(json.toString());
            return RestResult.ok(aicUser);
        }
        return RestResult.error(ResultCode.USER_LOGIN_ERROR);
    }

    /**
     * 用户登录登陆
     */
    @ApiOperation(
            tags = "UserController",
            value = "用户登录登陆接口(返回token)")
    @PostMapping("loginPC")
    @ResponseBody
    public RestResult loginPC(@RequestBody Map<String, String> requestMap, HttpServletRequest request) {
        String username = requestMap.get("username");
        if(username.indexOf("@") < 0  && !username.endsWith("@fsgs")){
            username += "@fsgs";
        }
        //登录
        AICUser aicUser = orgUM.login(username, requestMap.get("password"));
        if (aicUser != null) {
            JSONObject json = new JSONObject();
            json.put("userId", aicUser.getPrimaryKey());
            json.put("username", aicUser.getEname());
            json.put("host", request.getRemoteHost());
            String token = jwtManager.createJWT(json.toString());
            return RestResult.ok(token);
        }
        return RestResult.error(ResultCode.USER_LOGIN_ERROR);
    }

    /**
     * 用户登录登陆
     */
    @ApiOperation(
            tags = "UserController",
            value = "用户登录登陆接口(返回token和用户信息)")
    @PostMapping("userLogin")
    @ResponseBody
    public RestResult userLogin(@RequestBody Map<String, String> requestMap, HttpServletRequest request) {
        String username = requestMap.get("username");
        if(username.indexOf("@") < 0  && !username.endsWith("@fsgs")){
            username += "@fsgs";
        }
        //登录
        AICUser aicUser = orgUM.login(username, requestMap.get("password"));
        Map<String, Object> map = new HashMap();
        map.put("user",aicUser);
        if (aicUser != null) {
            JSONObject json = new JSONObject();
            json.put("userId", aicUser.getPrimaryKey());
            json.put("username", aicUser.getEname());
            json.put("host", request.getRemoteHost());
            String token = jwtManager.createJWT(json.toString());
            map.put("token",token);
            return RestResult.ok(map);
        }
        return RestResult.error(ResultCode.USER_LOGIN_ERROR);
    }

    @ApiOperation(
            tags = "UserController",
            value = "用户查询接口")
    @GetMapping("searchUser")
    @ResponseBody
    public RestResult searchUser(HttpServletRequest request) {
        String token = request.getHeader("x-token");
        Claims claims = jwtManager.parseJWT(token);
        if (claims != null) {
            com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(claims.getSubject());
            String userId = (String) jsonObject.get("userId");
            return RestResult.ok(orgUM.findUserByPrimaryKey(userId));
        }
        return RestResult.error(ResultCode.TOKEN_ILLEGAL_ARGUMENT);
    }

    /**
     * 修改用户密码
     */
    @PostMapping("modifyPassword")
    @ResponseBody
    public RestResult modifyPassword(@RequestBody Map<String, String> requestMap) {
        String userid = requestMap.get("userid");
        String oldpassword = requestMap.get("oldpassword");
        String newpassword = requestMap.get("newpassword");
        boolean flag = orgUM.updateUserPassword(userid, newpassword, oldpassword);
        if (flag) {
            return RestResult.ok();
        }
        return RestResult.error(ResultCode.MYSTERIOUS_ERROR);
    }

    @ApiOperation(
            tags = "UserController",
            value = "根据权限查询用户列表")
    @GetMapping("findUsersByPermission")
    @ResponseBody
    public RestResult findUsersByPermission(@RequestParam String permission) {
        return RestResult.ok(orgUM.findUsersByPermission(permission));
    }

    @ApiOperation(
            tags = "UserController",
            value = "根据机构RegionId查询子机构列表")
    @GetMapping("findOrgUnitByRegionId")
    @ResponseBody
    public RestResult findOrgUnitByRegionId(@RequestParam String regionId) {
        List<AICOrg> aicOrgList = orgUM.findAllChildOrgByRegion(regionId);
        if (aicOrgList != null) {
            for (AICOrg aicOrg: aicOrgList) {
                if (regionId.equals(aicOrg.getRegion())) {
                    aicOrgList.remove(aicOrg);
                    break;
                }
            }
        }
        return RestResult.ok(aicOrgList);
    }

    @ApiOperation(
            tags = "UserController",
            value = "根据机构id查询机构部门列表")
    @GetMapping("findOrgDeptByOrgId")
    @ResponseBody
    public RestResult findOrgDeptByOrgId(@RequestParam String orgId) {
        return RestResult.ok(orgUM.findDeptByParent(orgId));
    }

    @ApiOperation(
            tags = "UserController",
            value = "根据权限和部门id查询用户列表")
    @GetMapping("findUsersByDeptIdAndPermission")
    @ResponseBody
    public RestResult findUsersByDeptIdAndPermission(@RequestParam String deptid, @RequestParam String permission) {
        return RestResult.ok(orgUM.findNodeUsers(deptid, permission));
    }


    @ApiOperation(
            tags = "UserController",
            value = "根据权限和单位id查询用户列表")
    @GetMapping("findUsersByOrgAndPermission")
    @ResponseBody
    public RestResult findUsersByOrgAndPermission(@RequestParam String orgid, @RequestParam String permission) {
        return RestResult.ok(orgUM.findUsersByOrgAndPermission(orgid, permission));
    }

    /**
     * 用户后台登陆
     */
    @ApiOperation(
            tags = "UserController",
            value = "用户登陆接口(返回token)")
    @PostMapping("loginByAdmin")
    @ResponseBody
    public RestResult loginByAdmin(@RequestBody Map<String, String> requestMap, HttpServletRequest request) {
        String username = requestMap.get("username");
        if(username.indexOf("@") < 0  && !username.endsWith("@fsgs")){
            username += "@fsgs";
        }
        //登录
        AICUser aicUser = orgUM.login(username);
        if (aicUser != null) {
            JSONObject json = new JSONObject();
            json.put("userId", aicUser.getPrimaryKey());
            json.put("username", aicUser.getEname());
            json.put("host", request.getRemoteHost());
            String token = jwtManager.createJWT(json.toString());
            return RestResult.ok(token);
        }
        return RestResult.error(ResultCode.USER_LOGIN_ERROR);
    }

}
