package com.chinaweal.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chinaweal.sdcs.crcs.crawler.bmfw.entity.Inrequest;
import com.chinaweal.sdcs.crcs.crawler.bmfw.service.IInrequestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-context.xml")
@WebAppConfiguration
public class InrequestTest {
    @Resource
    private IInrequestService iInrequestService;

    /**
     * 查询一条
     */
    @Test
    public void getOne() {
        QueryWrapper<Inrequest> inrequestWrapper = new QueryWrapper<Inrequest>();
        inrequestWrapper.lambda().eq(Inrequest::getCaseguid, "4c59cb6e-77ab-49c7-a33e-a8f6f696f717");
        Inrequest inrequest = iInrequestService.getOne(inrequestWrapper, false);
        System.out.println(inrequest.toString());
    }
}
