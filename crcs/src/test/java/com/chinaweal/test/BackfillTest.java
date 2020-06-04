package com.chinaweal.test;

import com.chinaweal.sdcs.crcs.business.service.impl.BackfillServiceImpl;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-context.xml")
@WebAppConfiguration
public class BackfillTest {
    @Resource
    BackfillServiceImpl backfillService;

}
