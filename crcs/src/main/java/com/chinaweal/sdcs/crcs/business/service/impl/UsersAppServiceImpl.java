package com.chinaweal.sdcs.crcs.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.aicorg.model.AICUser;
import com.chinaweal.aicorg.services.OrgUM;
import com.chinaweal.sdcs.crcs.business.entity.Message;
import com.chinaweal.sdcs.crcs.business.entity.UsersApp;
import com.chinaweal.sdcs.crcs.business.mapper.MessageMapper;
import com.chinaweal.sdcs.crcs.business.mapper.TasklistMapper;
import com.chinaweal.sdcs.crcs.business.mapper.UsersAppMapper;
import com.chinaweal.sdcs.crcs.business.service.IUsersAppSerivce;
import com.chinaweal.sdcs.crcs.util.MessageUtil;
import com.chinaweal.sdcs.crcs.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @param
 * @author mo sheng xin
 * @since
 **/
@Service
public class UsersAppServiceImpl extends ServiceImpl<UsersAppMapper, UsersApp> implements IUsersAppSerivce {
    @Autowired
    private UsersAppMapper usersappMapperT;
    @Autowired
    private TasklistMapper tasklistMapper;
    @Autowired
    private OrgUM orgUM;
    @Autowired
    private MessageMapper messageMapper;

    //12345、12315预警短信提醒
//    @Scheduled(fixedDelay = 7202000)
    public void warningMessage() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long nowtime = new Date().getTime();
        Map<String, String> messagemap = new HashMap<>();//保存短信参数
        String mType = "97";
        String mPassword = "v$HzWxpuiiNa6TmC";
        List<UsersApp> list = tasklistMapper.messageList();//2小时
        Message message = new Message();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        Calendar d = Calendar.getInstance();
        d.add(Calendar.DAY_OF_MONTH,1);
        d.set(Calendar.HOUR_OF_DAY, 6);
        d.set(Calendar.MINUTE, 0);
        d.set(Calendar.SECOND, 0);
        Date nighttime = c.getTime();
        Date morningtime = d.getTime();
        for (UsersApp uslist : list) {
            if(!"01".equals(uslist.getSystemsource())){
                AICUser userByPrimaryKey = orgUM.findUserByPrimaryKey(uslist.getUserId());
                //区局科室和伦教、北滘分局发短信
                if("440681".equals(userByPrimaryKey.getRegionID()) || "440681003".equals(userByPrimaryKey.getRegionID()) || "440681004".equals(userByPrimaryKey.getRegionID())) {
                    Date sdexpirationdate = new Date();
                    //时间在23~6点内
                    if(sdexpirationdate.compareTo(nighttime)==1 && sdexpirationdate.compareTo(morningtime)==-1){

                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                try {
                                    String warncontent = "%D4%A4%BE%AF%B5%A5%A3%BA%CB%B3%B5%C2%C7%F8%CA%D0%B3%A1%BC%E0%B6%BD%B9%DC%C0%ED%BE%D6%CF%FB%B7%D1%CE%AC%C8%A8%D2%B5%CE%F1%C8%DA%BA%CF%CF%B5%CD%B3%B7%D6%C5%C9%B9%A4%B5%A5" + uslist.getSerialnum() + "%B1%EA%CC%E2%A3%BA" + uslist.getRqsttitle() + "%C7%EB%B0%B4%B9%E6%B6%A8%D3%DA" + sdf.format(uslist.getSdexpirationdate()) + "%CA%B1%BC%E4%C7%B0%B4%F0%B8%B4%B0%EC%C0%ED%BD%E1%B9%FB%A3%AC%D2%D4%C3%E2%B0%EC%BD%E1%B3%AC%CA%B1%A1%A3";
                                    String relateDocUuid = uslist.getUserId();
                                    String phones = uslist.getMobile() + ";" + uslist.getGender() + ";" + uslist.getWorktype();
                                    //短信接口参数
                                    messagemap.put("mType", mType);
                                    messagemap.put("mPassword", mPassword);
                                    messagemap.put("phones", phones);
                                    messagemap.put("content", warncontent);
                                    messagemap.put("relateDocUuid", relateDocUuid);
                                    //记录短信
                                    message.setId(UuidUtil.getUuid());
                                    message.setCaseguid(uslist.getCaseguid());
                                    message.setUnitname(userByPrimaryKey.getUnitName());
                                    message.setUserid(uslist.getUserId());
                                    message.setFormtype(uslist.getBustype());
                                    message.setMessagetype("1");
                                    message.setMobile(uslist.getMobile());
                                    message.setReceivetime(new Date());
                                    message.setName(userByPrimaryKey.getName());
                                    message.setEname(userByPrimaryKey.getEname());
                                    message.setId(UuidUtil.getUuid());
                                    message.setCaseguid(uslist.getCaseguid());
                                    message.setSerialnum(uslist.getSerialnum());
                                    message.setSdexpirationdate(uslist.getSdexpirationdate());
                                    message.setContent(URLDecoder.decode(warncontent, "GBK"));
                                    message.setSystemsource(uslist.getSystemsource());
                                    String messageresult = MessageUtil.sendMessage("http://61.142.131.10:888/OaListenerServlet", messagemap);
                                    if("Http Request is accept.".equals(messageresult)){
                                        message.setStatus("0");
                                        messageMapper.insert(message);
                                    }else{
                                        message.setStatus("1");
                                        messageMapper.insert(message);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            //定义在第二天早上6点发短信
                        },morningtime);
                    }else{
                        //不在23到6点
                        String warncontent = "%D4%A4%BE%AF%B5%A5%A3%BA%CB%B3%B5%C2%C7%F8%CA%D0%B3%A1%BC%E0%B6%BD%B9%DC%C0%ED%BE%D6%CF%FB%B7%D1%CE%AC%C8%A8%D2%B5%CE%F1%C8%DA%BA%CF%CF%B5%CD%B3%B7%D6%C5%C9%B9%A4%B5%A5" + uslist.getSerialnum() + "%B1%EA%CC%E2%A3%BA" + uslist.getRqsttitle() + "%C7%EB%B0%B4%B9%E6%B6%A8%D3%DA" + sdf.format(uslist.getSdexpirationdate()) + "%CA%B1%BC%E4%C7%B0%B4%F0%B8%B4%B0%EC%C0%ED%BD%E1%B9%FB%A3%AC%D2%D4%C3%E2%B0%EC%BD%E1%B3%AC%CA%B1%A1%A3";
                        String relateDocUuid = uslist.getUserId();
                        String phones = uslist.getMobile() + ";" + uslist.getGender() + ";" + uslist.getWorktype();
                        messagemap.put("mType", mType);
                        messagemap.put("mPassword", mPassword);
                        messagemap.put("phones", phones);
                        messagemap.put("content", warncontent);
                        messagemap.put("relateDocUuid", relateDocUuid);

                        message.setId(UuidUtil.getUuid());
                        message.setCaseguid(uslist.getCaseguid());
                        message.setUnitname(userByPrimaryKey.getUnitName());
                        message.setUserid(uslist.getUserId());
                        message.setFormtype(uslist.getBustype());
                        message.setMessagetype("1");
                        message.setMobile(uslist.getMobile());
                        message.setReceivetime(new Date());
                        message.setName(userByPrimaryKey.getName());
                        message.setEname(userByPrimaryKey.getEname());
                        message.setId(UuidUtil.getUuid());
                        message.setCaseguid(uslist.getCaseguid());
                        message.setSerialnum(uslist.getSerialnum());
                        message.setSdexpirationdate(uslist.getSdexpirationdate());
                        message.setContent(URLDecoder.decode(warncontent, "GBK"));
                        message.setSystemsource(uslist.getSystemsource());
                        //调用短信接口
                        String messageresult = MessageUtil.sendMessage("http://61.142.131.10:888/OaListenerServlet", messagemap);
                        /*AICUser userByPrimaryKey = orgUM.findUserByPrimaryKey(uslist.getUserId());*/
                        if("Http Request is accept.".equals(messageresult)){
                            message.setStatus("0");
                            messageMapper.insert(message);
                        }else{
                            message.setStatus("1");
                            messageMapper.insert(message);
                        }

                    }
                }
            }
        }

        }

        //受理登记预警短信定时任务，两天内到期提醒
        @Scheduled(fixedDelay = 172802000)
        public void sendAcceptMessage() throws Exception {
            List<Map<String, Object>> amlist = usersappMapperT.selectAcceptMessage();
            Map<String, String> messagemap = new HashMap<>();//保存短信参数
            String mType = "97";
            String mPassword = "v$HzWxpuiiNa6TmC";
            Message message = new Message();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (amlist.size() != 0) {
            for (Map aml : amlist) {
                String warncontent = "你有受理登记预警工单号为" + aml.get("tserialnum").toString() + "的案件待处理，" + "到期时间为" + sdf.format(aml.get("sdexpirationdate"));
                String content = URLEncoder.encode(warncontent, "GBK");
                String relateDocUuid = aml.get("userid").toString();
                String phones = aml.get("mobile") + ";" + "W" + ";" + "1";
                messagemap.put("mType", mType);
                messagemap.put("mPassword", mPassword);
                messagemap.put("phones", phones);
                messagemap.put("content", content);
                messagemap.put("relateDocUuid", relateDocUuid);


                AICUser userByPrimaryKey = orgUM.findUserByPrimaryKey(aml.get("userid").toString());
                message.setId(UuidUtil.getUuid());
                message.setCaseguid(aml.get("caseguid").toString());
                message.setUnitname(userByPrimaryKey.getUnitName());
                message.setUserid(relateDocUuid);
                message.setFormtype(aml.get("bustype").toString());
                message.setMessagetype("1");
                message.setMobile(aml.get("mobile").toString());
                message.setReceivetime(new Date());
                message.setName(userByPrimaryKey.getName());
                message.setEname(userByPrimaryKey.getEname());
                message.setId(UuidUtil.getUuid());
                message.setSerialnum(aml.get("tserialnum").toString());
                message.setSdexpirationdate(sdf.parse(aml.get("sdexpirationdate").toString()));
                message.setContent(URLDecoder.decode(warncontent, "GBK"));
                message.setSystemsource("01");  //01为受理登记系统

                //调用短信接口
                String messageresult = MessageUtil.sendMessage("http://61.142.131.10:888/OaListenerServlet", messagemap);

                if ("Http Request is accept.".equals(messageresult)) {
                    message.setStatus("0");
                    messageMapper.insert(message);
                } else {
                    message.setStatus("1");
                    messageMapper.insert(message);
                }
            }
          }
        }

    }


