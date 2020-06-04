package com.chinaweal.sdcs.crcs.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IDEA 2014.
 * Code: Lain
 * Date: 2018-08-13
 * Time: 09:09
 */
@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan("com.chinaweal.sdcs.crcs.**.controller")
public class SwaggerConfig {

    @Bean
    public Docket swaggerSpringMvcPlugin() {

        //添加head参数start
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> headers = new ArrayList<>();
        tokenPar.name("X-Token").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        headers.add(tokenPar.build());
        //添加head参数end

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
//                .host("www.chinaweal.com.cn")
                .enable(true)   //根据配置读取是否开启swagger文档，针对测试与生产环境采用不同的配置
                .select() //选择哪些路径和api会生成document
//                .apis(RequestHandlerSelectors.any())//对所有Api进行监控
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))//只生成被ApiOperation这个注解注解过的api接口
                .paths(PathSelectors.any()) //对所有路径进行扫描
                .build()
                .globalOperationParameters(headers);
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("顺德区市场监督管理局消费维权业务融合系统API接口平台")
                .description("提供顺德区市场监督管理局消费维权业务融合系统系统所有业务后台的接口")
//                .contact(new Contact("zhzhy", "http://www.chinaweal.com.cn", "zhzhy@chinaweal.com.cn"))
                .license("众望通科技")
                .licenseUrl("http://www.chinaweal.com.cn")
                .build();
    }
}
