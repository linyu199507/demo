package com.chinaweal.sdcs.crcs.business.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.business.entity.Newassign;
import com.chinaweal.sdcs.crcs.business.entity.Tasklist;
import com.chinaweal.sdcs.crcs.business.mapper.NewassignMapper;
import com.chinaweal.sdcs.crcs.business.mapper.TasklistMapper;
import com.chinaweal.sdcs.crcs.business.service.INewAssignService;
import com.chinaweal.sdcs.crcs.business.service.ITasklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @param
 * @author mo sheng xin
 * @since
 **/
@Service
public class NewassignServiceImpl extends ServiceImpl<NewassignMapper, Newassign> implements INewAssignService {
    @Autowired
    private NewassignMapper newassignMapper;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
//    @Scheduled(fixedDelay = 21600000) //6小时执行一次
    public void newAssign(){
        Date date=new Date();
        SimpleDateFormat sfd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sfd.format(date);
        List<Newassign> newassigns = newassignMapper.newAssign();
        for(Newassign newassign:newassigns){
            logger.info(format+"新派单"+newassign.getSerialnum()); //新派单信息保存到日志文件。
        }
    }
}
