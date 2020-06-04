package com.chinaweal.sdcs.crcs.bigScreenDisplay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.AppliancesVO;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.mapper.AppliancesVOMapper;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.service.IAppliancesVOService;
import com.chinaweal.sdcs.crcs.system.entity.Basecodeform;
import com.chinaweal.sdcs.crcs.system.mapper.BasecodeformMapper;
import com.chinaweal.sdcs.crcs.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 大屏展示数据获取--家用电器类投诉品类TOP10
 */
@Service
public class AppliancesVOServiceImpl extends ServiceImpl<AppliancesVOMapper, AppliancesVO> implements IAppliancesVOService {


    @Resource
    private AppliancesVOMapper appliancesVOMapper;
    @Resource
    private BasecodeformMapper basecodeformMapper;

    @Override
    public List<AppliancesVO> searchAppliancesTOP10(Map<String, Object> requestMap) {

        Map<String,Object> map = new HashMap<>();
        List<AppliancesVO> listOne = new ArrayList<>();
        List<AppliancesVO> listTwo = new ArrayList<>();
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "object12345").eq(Basecodeform::getOrglevel,"3").likeRight(Basecodeform::getCodeid,"0101%");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = basecodeformMapper.selectList(queryWrapper);
        Map<String,Object> thisyear = DateUtil.getThisYearOfNow();
        Map<String,Object> lastyear = DateUtil.getLastYearOfNow();
        for ( Basecodeform basecodeform : basecodeforms) {
            String codeid = basecodeform.getCodeid();
            requestMap.put("type", codeid);


            requestMap.put("begindate",lastyear.get("start"));
            requestMap.put("enddate",lastyear.get("end"));
            AppliancesVO dataLast = appliancesVOMapper.searchAppliancesTOP10ByMap(requestMap);
            if (dataLast != null){
                dataLast.setX(basecodeform.getComment());
            }else{
                dataLast = new AppliancesVO();
                dataLast.setX(basecodeform.getComment());
                dataLast.setY(0);
                dataLast.setZ(0);
            }


            requestMap.put("begindate",thisyear.get("start"));
            requestMap.put("enddate",thisyear.get("end"));
            AppliancesVO dataOne = appliancesVOMapper.searchAppliancesTOP10ByMap(requestMap);
            if (dataOne != null){
                dataOne.setX(basecodeform.getComment());
                if( dataLast.getY() > 0 ){
                    dataOne.setS((dataOne.getY()-dataLast.getY())*100/dataLast.getY());
                }else {
                    dataOne.setS(100);
                }

            }else{
                dataOne = new AppliancesVO();
                dataOne.setX(basecodeform.getComment());
                dataOne.setY(0);
                dataOne.setZ(0);
            }


            listOne.add(dataOne);
        }
//        listOne.sort(Comparator.comparingInt(ComplaintSituation::getOveralltotal).reversed());
//        listTwo = getSortList(listOne,listTwo);
//        map.put("this",listOne);
//        map.put("last",listTwo);
        listOne.sort(Comparator.comparingInt(AppliancesVO::getY).reversed());
        listOne = listOne.subList(0,10);
        List<AppliancesVO> dataList = new ArrayList<>();
        for( AppliancesVO data : listOne ){
            //柱-业务量
            AppliancesVO dataOne = new AppliancesVO();
            dataOne.setX(data.getX());
            dataOne.setY(data.getY());
            dataOne.setZ(0);
            dataOne.setS(1);
            dataList.add(dataOne);

            //柱-调解量
            AppliancesVO dataTwo = new AppliancesVO();
            dataTwo.setX(data.getX());
            dataTwo.setY(data.getZ());
            dataTwo.setZ(0);
            dataTwo.setS(2);
            dataList.add(dataTwo);

            //柱-增长率
//            QueryWrapper<Basecodeform> queryWrapperOne = new QueryWrapper<>();
//            queryWrapperOne.lambda().like(Basecodeform::getType, "object12345").eq(Basecodeform::getOrglevel,"3");
//            queryWrapperOne.lambda().eq(Basecodeform::getComment,data.getX()).likeRight(Basecodeform::getCodeid,"0101%");
//            Basecodeform basecodeform = basecodeformMapper.selectOne(queryWrapperOne);
//            requestMap.put("type", basecodeform.getCodeid());
//            requestMap.put("begindate",lastyear.get("start"));
//            requestMap.put("enddate",lastyear.get("end"));
//            AppliancesVO dataLast = appliancesVOMapper.searchAppliancesTOP10ByMap(requestMap);
//            System.out.println(dataLast);
            AppliancesVO dataThree = new AppliancesVO();
            dataThree.setX(data.getX());
            dataThree.setY(data.getY());
            dataThree.setZ(data.getS());
            dataThree.setS(3);
            dataList.add(dataThree);

            //柱-调解率
            AppliancesVO dataFour = new AppliancesVO();
            dataFour.setX(data.getX());
            dataFour.setY( data.getY());
            dataFour.setZ(data.getZ()*100/data.getY());
            dataFour.setS(4);
            dataList.add(dataFour);
        }




        return dataList;
    }
}
