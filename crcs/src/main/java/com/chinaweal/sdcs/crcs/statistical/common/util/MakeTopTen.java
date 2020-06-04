package com.chinaweal.sdcs.crcs.statistical.common.util;

import com.chinaweal.sdcs.crcs.statistical.common.entity.BusinessTopCount;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: NaJim
 * @Date: 2019/9/29
 */
public class MakeTopTen {

    public static List topTenList(List<BusinessTopCount> list){

        Map<String,Integer> map = new HashMap<>();
        for (int i=0;i<list.size();i++){
            BusinessTopCount businessTopCount = list.get(i);
            String subjectname = businessTopCount.getSubjectname();
            Integer count = businessTopCount.getCount();
            if (map.isEmpty()){
                map.put(subjectname,count);
            }else {
                if(map.get(subjectname)!=null){
                    map.put(subjectname,map.get(subjectname)+count);
                }else {
                    map.put(subjectname,count);
                }
            }
        }
        List<String> keys = MapSort.sortMapByValue(map);
        List<String> realTopTen = new ArrayList<>();
        for (int i = 0;i<keys.size();i++){
            if (StringUtils.isNotBlank(keys.get(i)) && realTopTen.size()<=10){
                realTopTen.add(keys.get(i));
            }
        }
        return realTopTen;
    }
}
