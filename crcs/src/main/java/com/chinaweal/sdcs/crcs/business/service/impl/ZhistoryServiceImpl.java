package com.chinaweal.sdcs.crcs.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.business.entity.Zhistory;
import com.chinaweal.sdcs.crcs.business.mapper.ZhistoryMapper;
import com.chinaweal.sdcs.crcs.business.service.IZhistoryService;
import com.chinaweal.sdcs.crcs.util.UuidUtil;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author chinaweal
 * @since 2020-03-17
 */
@Service
public class ZhistoryServiceImpl extends ServiceImpl<ZhistoryMapper, Zhistory> implements IZhistoryService {

    @Autowired
    private ZhistoryMapper zhistorymapper;
    @Resource
    private BackfillServiceImpl backfillService;

    Logger logger = LoggerFactory.getLogger(ZhistoryServiceImpl.class);

    //todo 投诉表单
    @Override
    public void manualConsumption() {
        //请求10次停顿20秒
        int freq = 10;
        int i = 0;

        QueryWrapper<Zhistory> zhWrapper = new QueryWrapper<>();
        zhWrapper.lambda().eq(Zhistory::getRecord, "1");
        zhWrapper.lambda().eq(Zhistory::getZtype, "consumption");
        List<Zhistory> zhList = zhistorymapper.selectList(zhWrapper);
        if (zhList.size() > 0) {
            for (Zhistory zhistory : zhList) {
                backfillService.executeComplaintFormSwitch2(zhistory.getZjson().toString());


                //判断是否累加10次了
                i++;
                if (i % freq == 0) {
                    logger.info("手动调回填12345投诉，已累计调用{}次。进行等待20秒....！", i);
                    try {
                        Thread.sleep(180000);
                    } catch (InterruptedException e) {
                        logger.error("等待失败！", e);
                    }
                }
            }
            logger.info("完成手动调回填12345投诉，已累计调用{}次。", i);
        }
    }

    //todo 经济表单
    @Override
    public void manualEconomy() {
        //请求10次停顿20秒
        int freq = 10;
        int i = 0;

        QueryWrapper<Zhistory> zhWrapper = new QueryWrapper<>();
        zhWrapper.lambda().eq(Zhistory::getRecord, "1");
        zhWrapper.lambda().eq(Zhistory::getZtype, "economy");
        List<Zhistory> zhList = zhistorymapper.selectList(zhWrapper);


        if (zhList.size() > 0) {
            for (Zhistory zhistory : zhList) {
                backfillService.executeEconomyFormSwitch2(zhistory.getZjson().toString());

                //判断是否累加10次了
                i++;
                if (i % freq == 0) {
                    logger.info("手动调回填12345经济，已累计调用{}次。进行等待20秒....！", i);
                    try {
                        Thread.sleep(180000);
                    } catch (InterruptedException e) {
                        logger.error("等待失败！", e);
                    }
                }
            }
            logger.info("完成手动调回填12345经济，已累计调用{}次。", i);
        }
    }

    @Override
    public void selectEconomyHistory() {
        //经济类历史工单
        List<Map<String, String>> list = zhistorymapper.selectEconomyJson();
        for (Map<String, String> economyMap : list) {
            if ("".equals(economyMap.get("commoditybrand")) || economyMap.get("commoditybrand").isEmpty()) {
                economyMap.put("commoditybrand", economyMap.get("subjectname"));
            }
            economyMap.put("operateUsername", economyMap.remove("operateusername"));
            if ("".equals(economyMap.get("rqsttitle")) || economyMap.get("rqsttitle") == null) {
                economyMap.put("rqsttitle", "总局单：" + economyMap.get("subjectname") + economyMap.get("caseguid"));
            } else {
                economyMap.put("rqsttitle", economyMap.get("rqsttitle").toString());
            }
            if ("".equals(economyMap.get("handleopinion")) || economyMap.get("handleopinion") == null) {
                economyMap.put("handleopinion", "无意见信息");
            } else {
                economyMap.put("handleopinion", economyMap.get("handleopinion").toString());
            }


            if ("".equals(economyMap.get("isweb")) || economyMap.get("isweb") == null) {
                economyMap.put("isweb", "否");
            }
            if ("".equals(economyMap.get("subjectzcadd")) || economyMap.get("subjectzcadd") == null) {
                economyMap.put("subjectzcadd", economyMap.get("subjectaddress"));
            }
            if (economyMap.get("providelb") == null || "".equals(economyMap.get("providelb"))) {
                economyMap.put("providelb", "私营企业");
            }
            if ("".equals(economyMap.get("accordtypecode")) || economyMap.get("accordtypecode") == null) {
                economyMap.put("accordtypecode", "价格投诉");
            }
            if ("".equals(economyMap.get("linknumber")) || economyMap.get("linknumber") == null) {
                economyMap.put("linknumber", "个人");
            }
            if (economyMap.get("objname").length() > 40) {
                economyMap.put("objname", economyMap.get("objname").toString().substring(0, 40));
            } else {
                economyMap.put("objname", economyMap.get("objname"));
            }
            if("".equals(economyMap.get("keyword")) || economyMap.get("keyword")==null) {
                economyMap.put("keyword","无");
            }else if (economyMap.get("keyword").length() > 40) {
                economyMap.put("keyword", economyMap.get("keyword").toString().substring(0, 40));
            } else {
                economyMap.put("keyword", economyMap.get("keyword"));
            }
            if("商品-其他商品-其他".equals(economyMap.get("objclassify"))){
                economyMap.put("objclassify","其他居民服务");
            }
            Set<String> set = economyMap.keySet();
            Iterator<String> it = set.iterator();
        }
        JSONArray jsonData = JSONArray.fromObject(list);
        Zhistory zhistory = new Zhistory();
        for (int i = 0; i < jsonData.size(); i++) {
            net.sf.json.JSONObject job = jsonData.getJSONObject(i);
            zhistory.setCaseid(job.get("caseguid").toString());
            zhistory.setId(UuidUtil.getUuid());
            zhistory.setZjson(job.toString());
            zhistory.setUpdatedate(new java.sql.Date(new java.util.Date().getTime()));
            zhistory.setZtype(job.get("formtype").toString());
            zhistory.setRecord("1");
            zhistorymapper.insert(zhistory);
        }

    }
    @Override
    public void selectComplaintHistory(){
        //投诉类历史工单
        List<Map<String, String>> complaintmap = zhistorymapper.selectComplaintJson();
        for (Map<String, String> complaintMap : complaintmap) {
            complaintMap.put("operateUsername", complaintMap.remove("operateusername"));
            if (complaintMap.get("rqsttitle") == null || "".equals(complaintMap.get("rqsttitle"))) {
                complaintMap.put("rqsttitle", "总局单：" + complaintMap.get("subjectname") + complaintMap.get("caseguid"));
            } else {
                complaintMap.put("rqsttitle", complaintMap.get("rqsttitle").toString());
            }

            if ("".equals(complaintMap.get("handleopinion")) || complaintMap.get("handleopinion") == null) {
                complaintMap.put("handleopinion", "无意见信息");
            } else {
                complaintMap.put("handleopinion", complaintMap.get("handleopinion").toString());
            }

            if (complaintMap.get("isweb") == null || "".equals(complaintMap.get("isweb"))) {
                complaintMap.put("isweb", "否");
            }
            if ("".equals(complaintMap.get("commoditybrand")) || complaintMap.get("commoditybrand") == null) {
                complaintMap.put("commoditybrand", complaintMap.get("subjectname"));
            }
            if ("".equals(complaintMap.get("providelb")) || complaintMap.get("providelb") == null) {
                complaintMap.put("providelb", "私营企业");
            }else if (complaintMap.get("providelb").contains("内资企业-公司") ||complaintMap.get("providelb").contains("股份有限公司") || complaintMap.get("providelb").contains("集团") || complaintMap.get("providelb").contains("有限责任公司") || complaintMap.get("providelb").contains("分公司") || complaintMap.get("providelb").contains("个人独资企业")) {
                complaintMap.put("providelb", "私营企业");
            }else{
                complaintMap.put("providelb", complaintMap.get("providelb"));
            }
            if ("".equals(complaintMap.get("subjectzcadd")) || complaintMap.get("subjectzcadd") == null) {
                complaintMap.put("subjectzcadd", complaintMap.get("subjectaddress"));
            }
            if ("".equals(complaintMap.get("linknumber")) || complaintMap.get("linknumber") == null) {
                complaintMap.put("linknumber", "个人");
            }
            if ("".equals(complaintMap.get("accordtypecode")) || complaintMap.get("accordtypecode") == null) {
                complaintMap.put("accordtypecode", "价格投诉");
            }
            if ("".equals(complaintMap.get("rqstsex")) || complaintMap.get("rqstsex") == null) {
                complaintMap.put("rqstsex", "男");
            }
            if(complaintMap.get("objname").length()>40){
                complaintMap.put("objname",complaintMap.get("objname").toString().substring(0,40));
            }else{
                complaintMap.put("objname",complaintMap.get("objname"));
            }

            if("".equals(complaintMap.get("keyword")) || complaintMap.get("keyword")==null) {
                complaintMap.put("keyword","无");
            }else if (complaintMap.get("keyword").length() > 40) {
                complaintMap.put("keyword", complaintMap.get("keyword").toString().substring(0, 40));
            } else {
                complaintMap.put("keyword", complaintMap.get("keyword"));
            }

            if("商品-其他商品-其他".equals(complaintMap.get("objclassify"))){
                complaintMap.put("objclassify","其他居民服务");
            }
            if("".equals(complaintMap.get("category"))||complaintMap.get("category")==null){
                complaintMap.put("category","其他");
            }
        }
        JSONArray complaintjsonData = JSONArray.fromObject(complaintmap);
        Zhistory zhistory=new Zhistory();
        for (int i = 0; i < complaintjsonData.size(); i++) {
            net.sf.json.JSONObject job = complaintjsonData.getJSONObject(i);
            zhistory.setCaseid(job.get("caseguid").toString());
            zhistory.setId(UuidUtil.getUuid());
            zhistory.setZjson(job.toString());
            zhistory.setUpdatedate(new java.sql.Date(new java.util.Date().getTime()));
            zhistory.setZtype(job.get("formtype").toString());
            zhistory.setRecord("1");
            zhistorymapper.insert(zhistory);
        }

    }
}
