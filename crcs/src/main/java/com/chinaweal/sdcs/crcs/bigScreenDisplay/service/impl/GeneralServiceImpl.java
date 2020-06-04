package com.chinaweal.sdcs.crcs.bigScreenDisplay.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.GeneralVO;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.entity.ScreenDisplayVO;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.mapper.EachTypeVOMapper;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.mapper.GeneralMapper;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.mapper.ScreenDisplayMapper;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.service.IGeneralService;
import com.chinaweal.sdcs.crcs.bigScreenDisplay.service.IScreenDisplayService;
import com.chinaweal.sdcs.crcs.business.entity.Processinfo;
import com.chinaweal.sdcs.crcs.business.entity.Tasklist;
import com.chinaweal.sdcs.crcs.business.mapper.ProcessinfoMapper;
import com.chinaweal.sdcs.crcs.business.mapper.TasklistMapper;
import com.chinaweal.sdcs.crcs.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.channels.AcceptPendingException;
import java.util.*;

/**
 * 大屏展示数据获取
 */
@Service
public class GeneralServiceImpl extends ServiceImpl<GeneralMapper, GeneralVO> implements IGeneralService {


    @Resource
    private GeneralMapper generalMapper;
    @Autowired
    private TasklistMapper tasklistMapper;
    @Autowired
    private ProcessinfoMapper processinfoMapper;

    @Override
    public void changeBusinessdept(Map<String, Object> requestMap) {

        List<Tasklist> lists = tasklistMapper.selectBusinessdeptNull(requestMap);

//        Integer number = (Integer) requestMap.get("number");

//        if( number >0 && number > 1000 ){
            lists =  lists.subList(0,500);
//        }


        for(  Tasklist data : lists ){

            String caseguid = data.getCaseguid();
            System.out.println(caseguid);
            requestMap.put("caseguid",caseguid);
//            requestMap.put("nodeid","accept");
//            requestMap.put("nodeid","noAccept");
//            requestMap.get("type")
            List<Processinfo> processinfos = processinfoMapper.getProcessinfoByCaseguid(requestMap);
            if(processinfos.size()>0){
                Processinfo processinfo = processinfos.get(0);
                if( processinfo != null ){
                    String handledeptid = processinfo.getHandledeptid();
                    data.setHandledeptid(handledeptid);
                    String handledeptname = processinfo.getHandleorgname();
                    data.setHandledeptname(handledeptname);
                    if("佛山市顺德区市场监督管理局".equals(handledeptname) || "消费者权益保护科".equals(handledeptname) ){
                        data.setHandleregion("440681");
                    }else if(handledeptname.contains("乐从")){
                        data.setHandleregion("440681006");
                    }else if(handledeptname.contains("陈村")){
                        data.setHandleregion("440681005");
                    }else if(handledeptname.contains("勒流")){
                        data.setHandleregion("440681007");
                    }else if(handledeptname.contains("大良")){
                        data.setHandleregion("440681001");
                    }else if(handledeptname.contains("杏坛")){
                        data.setHandleregion("440681009");
                    }else if(handledeptname.contains("均安")){
                        data.setHandleregion("440681010");
                    }else if(handledeptname.contains("容桂")){
                        data.setHandleregion("440681002");
                    }else if(handledeptname.contains("龙江")){
                        data.setHandleregion("440681008");
                    }else if(handledeptname.contains("伦教")){
                        data.setHandleregion("440681003");
                    }else if(handledeptname.contains("北滘")){
                        data.setHandleregion("440681004");
                    }else{
                        data.setHandleregion("440681");
                    }
                }
            }


                //
            tasklistMapper.updateById(data);
//            return ;
        }



    }
}
