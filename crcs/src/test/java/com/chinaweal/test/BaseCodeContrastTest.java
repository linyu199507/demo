package com.chinaweal.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chinaweal.sdcs.crcs.system.entity.BasecodeContrast;
import com.chinaweal.sdcs.crcs.system.entity.Basecodeform;
import com.chinaweal.sdcs.crcs.system.service.IBasecodeContrastService;
import com.chinaweal.sdcs.crcs.system.service.IBasecodeformService;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-context.xml")
@WebAppConfiguration
public class BaseCodeContrastTest {
    @Resource
    private IBasecodeContrastService iBasecodeContrastService;
    @Resource
    private IBasecodeformService iBasecodeformService;

    //    @Test
    public void createUUid() {
        List<BasecodeContrast> list = iBasecodeContrastService.list();
        for (BasecodeContrast basecodeContrast : list) {
            basecodeContrast.setId(UUID.randomUUID().toString());
            UpdateWrapper<BasecodeContrast> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("type", basecodeContrast.getType());
            updateWrapper.eq("country_code", basecodeContrast.getCountryCode());
            updateWrapper.eq("fs_code", basecodeContrast.getFsCode());
            updateWrapper.set("id", UUID.randomUUID().toString());
            iBasecodeContrastService.update(updateWrapper);
        }
    }

//    @Test
    public void test2() {
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", "tort12345");
        List<Basecodeform> list = iBasecodeformService.list(queryWrapper);
        for (Basecodeform basecodeform : list) {
            basecodeform.setId(UUID.randomUUID().toString());
            basecodeform.setType("tort12315");
            basecodeform.setUpdatedate(new Date());
            iBasecodeformService.save(basecodeform);
        }
    }
}
