package com.chinaweal.sdcs.crcs.dev;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.chinaweal.youfool.file.ConfigPathUtil;
import com.chinaweal.youfool.file.PropertiesReader;
import com.chinaweal.youfool.framework.mybatis.plus.CodeGenerator;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created with IDEA 2017.
 * User: Lain
 * Date: 2019-01-25
 * Time: 10:13
 * business
 * zhistory
 */
public class CodeGen {

    public static void main(String[] args) {
        String jdbc = "/src/main/resources/properties/codeGenerator.properties";
        new CodeGenerator(jdbc).genCode();
    }

}
