package com.chinaweal.sdcs.crcs.statistical.common.util;

import java.util.*;

/**
 * @Author: NaJim
 * @Date: 2019/9/29
 */
public class MapSort {
    //降序
    public static List<String> sortMapByValue(Map<String,Integer> map){
        int size = map.size();
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(size);
        list.addAll(map.entrySet());
        ValueComparator vc = new ValueComparator();
        Collections.sort(list, vc);
        final List<String> keys = new ArrayList<String>(size);
        for (int i = 0; i < size; i++) {
            keys.add(i, list.get(i).getKey());
        }
        return keys;
    }
    private static class ValueComparator implements Comparator<Map.Entry<String, Integer>>
    {
        @Override
        public int compare(Map.Entry<String, Integer> mp1, Map.Entry<String, Integer> mp2)
        {
            return mp2.getValue() - mp1.getValue();
        }
    }
}
