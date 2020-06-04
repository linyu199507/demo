package com.chinaweal.sdcs.crcs.bigScreenDisplay.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.MapOneVO;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.RegionOrderVO;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.mapper.DataMapOneMapper;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.mapper.RegionOrderVOMapper;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.service.IDataMapOneService;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.service.IRegionOrderVOService;
import com.chinaweal.sdcs.crcs.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 大屏展示数据获取--各地区投诉业务量
 */
@Service
public class RegionOrderVOServiceImpl extends ServiceImpl<RegionOrderVOMapper, RegionOrderVO> implements IRegionOrderVOService {


    @Resource
    private RegionOrderVOMapper regionOrderVOMapper;


    @Override
    public List<RegionOrderVO> searchRegionOrder(Map<String, Object> requestMap) {
        List<RegionOrderVO> list = new ArrayList<>();
        Map<String,Object> thisYear = DateUtil.getThisYear();
        requestMap.put("begindate", thisYear.get("start"));
        requestMap.put("enddate", thisYear.get("end"));

        List<Map<String, String>> areas = new ArrayList<>();
        /*
         *顺序固定:勒流-陈村-乐从-北滘-伦教-大良-容桂-杏坛-龙江-均安
         */
        areas.add(new HashMap<String, String>(){{put("勒流","440681007");}});
        areas.add(new HashMap<String, String>(){{put("陈村","440681005");}});
        areas.add(new HashMap<String, String>(){{put("乐从","440681006");}});
        areas.add(new HashMap<String, String>(){{put("北滘","440681004");}});
        areas.add(new HashMap<String, String>(){{put("伦教","440681003");}});
        areas.add(new HashMap<String, String>(){{put("大良","440681001");}});
        areas.add(new HashMap<String, String>(){{put("容桂","440681002");}});
        areas.add(new HashMap<String, String>(){{put("杏坛","440681009");}});
        areas.add(new HashMap<String, String>(){{put("龙江","440681008");}});
        areas.add(new HashMap<String, String>(){{put("均安","440681010");}});
        String key = "";
        String value = "";
        for (Map<String, String> unit : areas) {
            for (Iterator it = unit.keySet().iterator(); it.hasNext(); ) {
                key = (String) it.next();
                value = unit.get(key);
            }
            requestMap.put("region", value);
            RegionOrderVO data = regionOrderVOMapper.searchRegionOrderByMap(requestMap);
            data.setPr(key);
//            data.setSeries1("https://img.alicdn.com/tfs/TB141hPsSzqK1RjSZPcXXbTepXa-200-200.png");
            data.setSeries2(String.valueOf((Integer.valueOf(data.getSeries2())*100/data.getAttribute()))+"%");
            list.add(data);
        }
        list.sort(Comparator.comparingInt(RegionOrderVO::getAttribute).reversed());

        int i = 1 ;
        for ( RegionOrderVO data: list ){
                data.setId(i);
                i++;
        }

        return list;
    }
}
