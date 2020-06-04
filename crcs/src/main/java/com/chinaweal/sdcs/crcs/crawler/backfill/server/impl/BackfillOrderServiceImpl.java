package com.chinaweal.sdcs.crcs.crawler.backfill.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.crawler.backfill.entity.BackfillOrder;
import com.chinaweal.sdcs.crcs.crawler.backfill.mapper.BackfillOrderMapper;
import com.chinaweal.sdcs.crcs.crawler.backfill.server.IBackfillOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BackfillOrderServiceImpl extends ServiceImpl<BackfillOrderMapper, BackfillOrder> implements IBackfillOrderService {


    @Resource
    private BackfillOrderMapper backfillOrderMapper;

    @Override
    public  Map<String,Object> searchBackOrder(Map<String, Object> requestMap) {
        Map<String,Object> map = new HashMap<>();
        List<BackfillOrder> lists = new ArrayList<>();
        //前溯7天
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        Date endTime = c.getTime();
        String endDay = sdf.format(endTime);
        c.add(Calendar.DATE, -14);
        Date startTime = c.getTime();
        String startDay = sdf.format(startTime);
        requestMap.put("begindate", startDay);
        requestMap.put("enddate", endDay);
        requestMap.put("state", "1");
        List<BackfillOrder> orders = backfillOrderMapper.searchBackOrderByMap(requestMap);
//        System.out.println(orders);

        for( BackfillOrder order : orders){
            BackfillOrder data ;
            System.out.println(order.getStep());
            if(order.getRequest_address().contains("12345") && !"acceptanceRegistration".equals(order.getStep())){
                requestMap.put("uuid",order.getUuid());
                data = backfillOrderMapper.searchBackOrder12345(requestMap);
                data.setSystem("12345系统");
                lists.add(data);
            }else if (order.getRequest_address().contains("12315") && !"acceptanceRegistration".equals(order.getStep())){
                order.setSystem("12315系统");
                lists.add(order);
            }else{
                order.setSystem("12315转12345系统");
                lists.add(order);
            }
        }
        Page<BackfillOrder> page = new Page();
//        Map<String,Object> page = new HashMap<>();
        int size = requestMap.get("size") != null ? ((Integer) requestMap.get("size")).intValue() : 10;
        int current = requestMap.get("page") != null ? ((Integer) requestMap.get("page")).intValue() : 1;
        int total = lists.size();
        page.setSize(size);
        page.setCurrent(current);
        page.setTotal(total);

        if ( total < size*current){
//            System.out.println(size*current-size);
//            System.out.println(total);
            lists = lists.subList(size*current-size,total);
        }else {
            lists = lists.subList(size*current-size,size*current);
        }
        map.put("page",page);
        map.put("list",lists);
        return map;
    }

    @Override
    public Integer searchBackOrderOfHome(Map<String, Object> requestMap) {
        Integer total ;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        Date endTime = c.getTime();
        String endDay = sdf.format(endTime);
        c.add(Calendar.DATE, -14);
        Date startTime = c.getTime();
        String startDay = sdf.format(startTime);
        requestMap.put("begindate", startDay);
        requestMap.put("enddate", endDay);
        requestMap.put("state", "1");
        BackfillOrder data = backfillOrderMapper.searchBackOrderOfHomeByMap(requestMap);
        if ( data != null){
                total = data.getTotal();
        }else {
                total = 0 ;
        }
        return total;
    }

    @Override
    public void deleteBackOrder(String[] ids) {

        for ( String uuid : ids){
            BackfillOrder data = backfillOrderMapper.selectByUuid(uuid);
            System.out.println(data);
            data.setFailed_marks("0");
            data.setTotal_changeTime(new Date());
            data.setConfirm("1");
            backfillOrderMapper.updateByUuid(data);
        }

    }

    @Override
    public void changeBackOrder(String[] ids) {
        for ( String uuid : ids){
            BackfillOrder data = backfillOrderMapper.selectByUuid(uuid);
            data.setConfirm("0");
            backfillOrderMapper.updateByUuid(data);
        }
    }


}
