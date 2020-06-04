package com.chinaweal.sdcs.crcs.util;
import com.chinaweal.aicorg.model.AICUser;
import com.chinaweal.sdcs.crcs.business.entity.*;
import com.chinaweal.sdcs.crcs.crawler.bmfw.entity.*;
import com.chinaweal.sdcs.crcs.crawler.ywcl.entity.*;
import com.chinaweal.sdcs.crcs.system.service.IBasecodeContrastService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Component
public class CreateNewassignUtil {

    private static CreateEntityInfoUtil createEntityInfoUtil;

    @Resource
    private IBasecodeContrastService iBasecodeContrastService;
    private Object CreateNewassignUtil;

    @PostConstruct
    public void init() {
        CreateNewassignUtil= this;
    }

    //msx  12345新派单列表获取，用于导出和对比
    public static Newassign createNewassign(Datagriddata datagriddata) {
        Newassign newassign = null;
        if (datagriddata != null) {
            newassign = new Newassign();
            newassign.setId(datagriddata.getId());
            newassign.setCaseguid(datagriddata.getCaseguid());
            newassign.setSerialnum(datagriddata.getSerialnum());
            newassign.setRqsttitle(datagriddata.getRqsttitle());
            newassign.setAssigndate(datagriddata.getCreatedate());
            newassign.setState("0");
            newassign.setSystemsource("02");//02 12345系统
            if("1".equals(datagriddata.getIscp())){
                newassign.setIscp("1");
            }else {
                newassign.setIscp("0");
            }
            if ("消费投诉表单".equals(datagriddata.getFormtype())) {
                newassign.setBustype("投诉类");
            } else if ("经济违法行为举报表单".equals(datagriddata.getFormtype())) {
                newassign.setBustype("举报类");
            } else {
                newassign.setBustype("咨询类");
            }
            newassign.setCreatedate(new Date());
        }
        return newassign;
    }

    //msx  12315新派单列表获取，用于导出和对比
    public static Newassign createNewassignByYwclshuntlist(Ywclshuntlist ywclshuntlist) {
        Newassign newassign = null;
        if (ywclshuntlist != null) {
            newassign = new Newassign();
            newassign.setId(ywclshuntlist.getId());
            newassign.setSerialnum(ywclshuntlist.getCaseid()); //登记编号
            newassign.setAssigndate(ywclshuntlist.getRegtime()); //登记时间
            newassign.setContent(ywclshuntlist.getApplidique()); //登记内容
            newassign.setSystemsource("03"); //03 12315系统
            newassign.setState("0");
            newassign.setBustype(ywclshuntlist.getInfotype());
            newassign.setNextnodeid("todispatch"); //增加待分流节点
            newassign.setCreatedate(new Date());
        }
        return newassign;
    }
}
