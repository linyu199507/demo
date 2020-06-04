package com.chinaweal.sdcs.crcs.bigScreenDisplay.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.BrandVO;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.mapper.BrandVOMapper;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.service.IBrandVOService;
import com.chinaweal.sdcs.crcs.system.entity.Basecodeform;
import com.chinaweal.sdcs.crcs.system.entity.TimesVo;
import com.chinaweal.sdcs.crcs.system.mapper.BasecodeformMapper;
import com.chinaweal.sdcs.crcs.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 大屏展示数据获取--被投诉品牌TOP10
 */
@Service
public class BrandVOServiceImpl extends ServiceImpl<BrandVOMapper, BrandVO> implements IBrandVOService {


    @Resource
    private BrandVOMapper brandVOMapper;
    @Resource
    private BasecodeformMapper basecodeformMapper;



    @Override
    public List<BrandVO> searchBrand(Map<String, Object> requestMap) {
        List<BrandVO> listThree = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        List<BrandVO> listTwo = new ArrayList<>();
        Map<String,Object> thisyear = DateUtil.getThisYear();
//        Map<String,Object> lastyear = DateUtil.getLastYear();
        requestMap.put("begindate",thisyear.get("start"));
        requestMap.put("enddate",thisyear.get("end"));
        List<BrandVO> listOne = brandVOMapper.searchBrandByMap(requestMap);
        listOne = listOne.subList(0,20);
//        int i = 0;
//        for( BrandVO data : listOne ){
//            if(data.getX().contains("美的")){
//                listThree.add(data);
//                listOne.remove(data);
//                i++;
//            }
//        }
        Iterator<BrandVO> iterator = listOne.iterator();
        while(iterator.hasNext()) {
            BrandVO data = iterator.next();
            if(data.getX().contains("美的")){
                listThree.add(data);
                iterator.remove();
            }
        }

        Integer total = 0;
        for( BrandVO dataOne : listThree){
            total = total + dataOne.getY();
        }

        BrandVO newData = new BrandVO();
        newData.setX("美的");
        newData.setY(total);
        newData.setS(1);

        listOne.add(newData);

        listOne.sort(Comparator.comparingInt(BrandVO::getY).reversed());

        listOne = listOne.subList(0,10);

        return listOne;
    }

    @Override
    public List<BrandVO> searchIllegalTOP10(Map<String, Object> requestMap) {

        //查询涉嫌违法行为分类内容
        QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Basecodeform::getType, "illegal12345").eq(Basecodeform::getOrglevel,"1");
        queryWrapper.orderByAsc("codeid");
        List<Basecodeform> basecodeforms = basecodeformMapper.selectList(queryWrapper);

        Map<String,Object> map = new HashMap<>();
        List<BrandVO> listOne = new ArrayList<>();

        for (Basecodeform basecodeform : basecodeforms) {
            String codeid = basecodeform.getCodeid();
            requestMap.put("type", codeid);
            Map<String,Object> thisYear =  DateUtil.getThisYear();
            requestMap.put("begindate", thisYear.get("start"));
            requestMap.put("enddate", thisYear.get("end"));
            BrandVO dataOne = brandVOMapper.searchIllegalTOP10ByMap(requestMap);
            if (dataOne != null){
                dataOne.setX(basecodeform.getComment());
                dataOne.setS(1);
            }else{
                dataOne = new BrandVO();
                dataOne.setX(basecodeform.getComment());
                dataOne.setY(0);
                dataOne.setS(1);
            }
            listOne.add(dataOne);
        }
        listOne.sort(Comparator.comparingInt(BrandVO::getY).reversed());
        listOne = listOne.subList(0,10);


        return listOne;
    }

    @Override
    public List<BrandVO> searchProblemOfComplaint(Map<String, Object> requestMap) {

        List<BrandVO> list = new ArrayList<>();
        List<TimesVo> times = DateUtil.getTwelveMonth();

        for(TimesVo time :times){

            requestMap.put("begindate",time.getTimestart());
            requestMap.put("enddate",time.getTimeend());
//            QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
//            queryWrapper.lambda().like(Basecodeform::getType, "question12345").eq(Basecodeform::getOrglevel,"1");
//            queryWrapper.orderByAsc("codeid");
//            List<Basecodeform> basecodeforms = basecodeformMapper.selectList(queryWrapper);
            Integer i = 0 ;
            //大屏展示数据写定 : 1.售后服务 2.无照经营 3.价格 4.质量  5.广告
            String[] codeids = {"13","06","11","02","03"} ;
            String a = time.getTimestart();
            String date = a.substring(2,4)+"/"+a.substring(5,7);

            for ( String codeid : codeids) {
                i++;
                requestMap.put("type", codeid);
                BrandVO dataOne = brandVOMapper.searchProblemOfComplaintByMap(requestMap);
                if (dataOne != null){
                    dataOne.setX(date);
                    dataOne.setS(i);
                }else{
                    dataOne = new BrandVO();
                    dataOne.setX(date);
                    dataOne.setY(0);
                    dataOne.setS(i);
                }
                list.add(dataOne);
            }
        }
        return list;
    }

    @Override
    public List<BrandVO> searchProblemOfEconomy(Map<String, Object> requestMap) {
        List<BrandVO> list = new ArrayList<>();
        List<TimesVo> times = DateUtil.getTwelveMonth();

        for(TimesVo time :times){

            requestMap.put("begindate",time.getTimestart());
            requestMap.put("enddate",time.getTimeend());
//            QueryWrapper<Basecodeform> queryWrapper = new QueryWrapper<>();
//            queryWrapper.lambda().like(Basecodeform::getType, "question12345").eq(Basecodeform::getOrglevel,"1");
//            queryWrapper.orderByAsc("codeid");
//            List<Basecodeform> basecodeforms = basecodeformMapper.selectList(queryWrapper);
            Integer i = 0 ;
            //大屏展示数据写定 : 1.广告 2.质量 3.无照经营 4.合同  5.价格
            String[] codeids = {"03","02","06","08","11"} ;
            String a = time.getTimestart();
            String date = a.substring(2,4)+"/"+a.substring(5,7);

            for ( String codeid : codeids) {
                i++;
                requestMap.put("type", codeid);
                BrandVO dataOne = brandVOMapper.searchProblemOfEconomyByMap(requestMap);
                if (dataOne != null){
                    dataOne.setX(date);
                    dataOne.setS(i);
                }else{
                    dataOne = new BrandVO();
                    dataOne.setX(date);
                    dataOne.setY(0);
                    dataOne.setS(i);
                }
                list.add(dataOne);
            }
        }
        return list;
    }

    @Override
    public List<BrandVO> searchObjectOfComplaint(Map<String, Object> requestMap) {

        List<BrandVO> list = new ArrayList<>();
        List<TimesVo> times = DateUtil.getTwelveMonth();

        for(TimesVo time :times){

            requestMap.put("begindate",time.getTimestart());
            requestMap.put("enddate",time.getTimeend());

            Integer i = 0 ;
            //大屏展示数据写定 : 1.家用电器(商品) 2.日用百货(商品) 3.交通工具(商品) 4.互联网(服务)  5.邮政业(服务) 6.居民服务(服务)
            String[] codeids = {"0101","0104","0106","0213","0210","0203"} ;
            String a = time.getTimestart();
            String date = a.substring(2,4)+"/"+a.substring(5,7);

            for ( String codeid : codeids) {
                i++;
                requestMap.put("type", codeid);
                BrandVO dataOne = brandVOMapper.searchObjectOfComplaint(requestMap);
                if (dataOne != null){
                    dataOne.setX(date);
                    dataOne.setS(i);
                }else{
                    dataOne = new BrandVO();
                    dataOne.setX(date);
                    dataOne.setY(0);
                    dataOne.setS(i);
                }
                list.add(dataOne);
            }
        }
        return list;
    }
}
