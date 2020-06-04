package com.chinaweal.sdcs.crcs.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chinaweal.sdcs.crcs.system.entity.BasecodeContrast;
import com.chinaweal.sdcs.crcs.system.entity.Basecodeform;
import com.chinaweal.sdcs.crcs.system.mapper.BasecodeContrastMapper;
import com.chinaweal.sdcs.crcs.system.service.IBasecodeContrastService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.system.service.IBasecodeformService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author chinaweal
 * @since 2019-11-28
 */
@Service
public class BasecodeContrastServiceImpl extends ServiceImpl<BasecodeContrastMapper, BasecodeContrast> implements IBasecodeContrastService {

    @Resource
    private IBasecodeformService iBasecodeformService;

    @Override
    public String getCountryCodeByFsCodeAndType(String fsCode, String type) {
        QueryWrapper<BasecodeContrast> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(BasecodeContrast::getFsCode, fsCode);
        queryWrapper.lambda().eq(BasecodeContrast::getType, type);
        BasecodeContrast basecodeContrast = getOne(queryWrapper,false);
        if (basecodeContrast != null) {
            return basecodeContrast.getCountryCode();
        }
        return fsCode;
    }

    @Override
    public String getCountryCodeByFsNameAndType(String fsName, String type) {
        //根据佛山的名称查询出它对应的codeid
        QueryWrapper<Basecodeform> baseCodeFormQuery = new QueryWrapper<>();
        baseCodeFormQuery.lambda().eq(Basecodeform::getComment, fsName).eq(Basecodeform::getType, type + "12345");
        Basecodeform basecodeForm = iBasecodeformService.getOne(baseCodeFormQuery, false);
        //查询
        QueryWrapper<BasecodeContrast> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(BasecodeContrast::getFsCode, basecodeForm.getCodeid());
        queryWrapper.lambda().eq(BasecodeContrast::getType, type);
        BasecodeContrast basecodeContrast = getOne(queryWrapper);
        if (basecodeContrast != null) {
            return basecodeContrast.getCountryCode();
        }
        return null;
    }

    @Override
    public String getFsCodeByCountryNameAndType(String countryName, String type) {
        //根据全国12315的名称查询出它对应的codeid
        QueryWrapper<Basecodeform> baseCodeFormQuery = new QueryWrapper<>();
        baseCodeFormQuery.lambda().eq(Basecodeform::getComment, countryName).eq(Basecodeform::getType, type);
        Basecodeform basecodeForm = iBasecodeformService.getOne(baseCodeFormQuery, false);
        if (basecodeForm != null) {
            //查询对应的佛山12345代码
            QueryWrapper<BasecodeContrast> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(BasecodeContrast::getCountryCode, basecodeForm.getCodeid());
            queryWrapper.lambda().eq(BasecodeContrast::getType, type);
            BasecodeContrast basecodeContrast = getOne(queryWrapper);
            if (basecodeContrast != null) {
                return basecodeContrast.getFsCode();
            }
        }
        return null;
    }

    @Override
    public String getFsCodeByCountryCodeAndType(String countryCode, String type) {
        //查询对应的佛山12345代码
        QueryWrapper<BasecodeContrast> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(BasecodeContrast::getCountryCode, countryCode);
        queryWrapper.lambda().eq(BasecodeContrast::getType, type);
        BasecodeContrast basecodeContrast = getOne(queryWrapper);
        if (basecodeContrast != null) {
            return basecodeContrast.getFsCode();
        }
        return null;
    }
}
