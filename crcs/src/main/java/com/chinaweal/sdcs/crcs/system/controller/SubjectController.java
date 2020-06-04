package com.chinaweal.sdcs.crcs.system.controller;

import com.chinaweal.youfool.framework.rest.RestResult;
import com.chinaweal.youfool.framework.rest.ResultCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 涉及主体
 *
 * @author HuangXiao
 * @since 2019-10-24
 */


@RestController
@RequestMapping("/subject")
public class SubjectController {


    @GetMapping("/list")
    public RestResult loginPC(String heckName) {
        return RestResult.error(ResultCode.USER_LOGIN_ERROR);
    }
}
