package com.chinaweal.sdcs.crcs.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chinaweal.aicorg.model.AICUser;
import com.chinaweal.aicorg.services.OrgUM;
import com.chinaweal.sdcs.crcs.business.entity.Message;
import com.chinaweal.sdcs.crcs.business.entity.Tasklist;
import com.chinaweal.sdcs.crcs.business.mapper.MessageMapper;
import com.chinaweal.sdcs.crcs.business.mapper.TasklistMapper;
import com.chinaweal.sdcs.crcs.business.mapper.UsersAppMapper;
import com.chinaweal.sdcs.crcs.business.service.IUsersAppSerivce;
import com.chinaweal.sdcs.crcs.system.entity.TOrgunits;
import com.chinaweal.sdcs.crcs.system.entity.TUsers;
import com.chinaweal.sdcs.crcs.system.mapper.TOrgunitsMapper;
import com.chinaweal.sdcs.crcs.system.mapper.TUsersMapper;
import com.chinaweal.sdcs.crcs.system.service.ITOrgunitsService;
import com.chinaweal.sdcs.crcs.util.MessageUtil;
import com.chinaweal.sdcs.crcs.util.UuidUtil;
import com.chinaweal.youfool.framework.rest.RestResult;
import com.chinaweal.youfool.framework.rest.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author msx
 * @since 2020-04-21
 */
@Api(tags = "TOrgunitsController", description = "级联区局分局部门人员查询接口类")
@Controller
@RequestMapping("/t_orgunits")
public class TOrgunitsController {
    @Autowired
    private ITOrgunitsService iTOrgunitsService;
    @Autowired
    private OrgUM orgUM;
    @Autowired
    private ITOrgunitsService itOrgunitsService;
    @Autowired
    private TOrgunitsMapper tOrgunitsMapper;
    @Autowired
    private TasklistMapper tasklistMapper;
    @Autowired
    private UsersAppMapper UsersAppMapper;
    @Autowired
    private TUsersMapper tUsersMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private IUsersAppSerivce IUsersAppSerivce;


    @ApiOperation(
            tags = "TOrgunitsController",
            value = "分局部门人员级联查询接口")
    @PostMapping("orgunits")
    @ResponseBody
    public List<Map<String, Object>> getDate() {

        QueryWrapper<TOrgunits> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TOrgunits::getOrglevel, 5).eq(TOrgunits::getParentid, "6ba12fbe-0100-1000-e000-03fec0a85893");
        List<TOrgunits> quJu = iTOrgunitsService.list(queryWrapper);
        List<Map<String, Object>> options = new ArrayList<>();
        for (TOrgunits orgunits : quJu) {
            Map<String, Object> unit = new LinkedHashMap<>();

//            unit.put("value", orgunits.getParentid());
            unit.put("value", orgunits.getOrgunitid());
            unit.put("label", orgunits.getOrgunitname());

            //查询部门
            List<TOrgunits> buMens = iTOrgunitsService.list(Wrappers.<TOrgunits>lambdaQuery()
                    .eq(TOrgunits::getParentid, orgunits.getOrgunitid()));
            List<Map> childrenBm = new ArrayList<>();

            for (TOrgunits buMen : buMens) {
                Map<String, Object> itemBm = new LinkedHashMap<>();
                itemBm.put("value", buMen.getParentid());
                itemBm.put("label", buMen.getOrgunitname());
                //人
                List<Map> childrenR = new ArrayList<>();

                //查询有权限的人
                List<AICUser> tuser = orgUM.findNodeUsers(buMen.getOrgunitid(), "subordinate");//分派下级权限
                if (tuser != null&&!tuser.isEmpty()) {
                    for (AICUser tUsers : tuser) {
                        Map<String, Object> itemR = new LinkedHashMap<>();
                        itemR.put("value", tUsers.getPrimaryKey());
                        itemR.put("label", tUsers.getName());
                        childrenR.add(itemR);
                        childrenR= childrenR.stream().distinct().collect(Collectors.toList());//去重

                        itemBm.put("children", childrenR);
                        childrenBm.add(itemBm);//防止重复数据
                    }
                    childrenBm.add(itemBm);
                }else{

                }

//                childrenR= childrenR.stream().distinct().collect(Collectors.toList());//去重
//
//                itemBm.put("children", childrenR);
//                childrenBm.add(itemBm);
            }
            unit.put("children", childrenBm);
            //添加分局信息
            options.add(unit);
        }

        return options;
    }

    @ApiOperation(
            tags = "TOrgunitsController",
            value = "区局部门人员级联查询接口")
    @PostMapping("deptData")
    @ResponseBody
    public List<Map<String, Object>> getDeptData() {
        QueryWrapper<TOrgunits> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TOrgunits::getOrglevel, 99).eq(TOrgunits::getParentid, "6ba12fbe-0100-1000-e000-03fec0a85893");
        //部门
        List<TOrgunits> quJu = iTOrgunitsService.list(queryWrapper);
        List<Map<String, Object>> options = new ArrayList<>();
        List<String> liststr = new ArrayList<>();

        for (TOrgunits orgunits : quJu) {
            Map<String, Object> unit = new HashMap<>();

            unit.put("value", orgunits.getOrgunitid());
            unit.put("label", orgunits.getOrgunitname());

            //查询有权限的人
            List<AICUser> buMens = orgUM.findNodeUsers(orgunits.getOrgunitid(), "dealdepartment");//分派其它部门权限

            List<Map> childrenBm = new ArrayList<>();

            for (AICUser buMen : buMens) {
                Map<String, Object> itemBm = new LinkedHashMap<>();

                    itemBm.put("label",buMen.getName());
                    itemBm.put("value",buMen.getPrimaryKey());
                    childrenBm.add(itemBm);
            }

            childrenBm=childrenBm.stream().distinct().collect(Collectors.toList());//去重
            unit.put("children", childrenBm);
            options.add(unit);
        }

        return options;
    }

    @ApiOperation(
            tags = "TOrgunitsController",
            value = "人员级联查询接口")
    @PostMapping("getAllData")
    @ResponseBody
    public List<Map<String, Object>> getAllData() {
        List<Map<String, Object>> list = new ArrayList<>();

        List<Map<String, Object>> listOne = getDate();
        List<Map<String, Object>> listTwo = getDeptData();

        Map<String,Object> mapOne = new HashMap<>();
        Map<String,Object> mapTwo = new HashMap<>();

        mapOne.put("label","区局");
        mapOne.put("value","区局");
        mapOne.put("children",listTwo);
        list.add(mapOne);

        mapTwo.put("label","分局");
        mapTwo.put("value","分局");
        mapTwo.put("children",listOne);
        list.add(mapTwo);

        return list;
    }

    @ApiOperation(
            tags = "TOrgunitsController",
            value = "受理登记短信发送对象级联数据查询接口")
    @PostMapping("getMessageCascadeData")
    @ResponseBody
    public List<Map<String, Object>> getMCdata() {
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String,Object> mapOne = new HashMap<>();
        Map<String,Object> mapTwo = new HashMap<>();

        QueryWrapper<TOrgunits> queryWrapperOne = new QueryWrapper<>();
        queryWrapperOne.lambda().eq(TOrgunits::getOrglevel, 5).eq(TOrgunits::getParentid, "6ba12fbe-0100-1000-e000-03fec0a85893");
        //查询10个分局
        List<TOrgunits> fenJu = iTOrgunitsService.list(queryWrapperOne);
        List<Map<String, Object>> optionsOne = new ArrayList<>();
        for (TOrgunits orgunits :fenJu) {
            Map<String, Object> unit = new LinkedHashMap<>();
            unit.put("value", orgunits.getOrgunitid());
            unit.put("label", orgunits.getOrgunitname());

            //查询部门
            List<TOrgunits> buMens = iTOrgunitsService.list(Wrappers.<TOrgunits>lambdaQuery()
                    .eq(TOrgunits::getParentid, orgunits.getOrgunitid()));
            List<Map> childrenBm = new ArrayList<>();

            for (TOrgunits buMen : buMens) {
                Map<String, Object> itemBm = new LinkedHashMap<>();
                itemBm.put("value", buMen.getParentid());
                itemBm.put("label", buMen.getOrgunitname());
                //人
                List<Map> childrenR = new ArrayList<>();

                //查询有SMS短信权限的人
//                List<Map> tuser = tOrgunitsMapper.selectSMSPerson(buMen.getOrgunitid());
                //查询有标记的人
                List<Map> tuser = tOrgunitsMapper.selectMarkPerson(buMen.getOrgunitid());

                if (tuser != null&&!tuser.isEmpty()) {
                    for (Map tUsers : tuser) {
                        Map<String, Object> itemR = new LinkedHashMap<>();
                        itemR.put("value", tUsers.get("mobile"));
                        itemR.put("label", tUsers.get("realname"));
                        childrenR.add(itemR);
                        //childrenR= childrenR.stream().distinct().collect(Collectors.toList());//去重

                        itemBm.put("children", childrenR);

//                        childrenBm.add(newitemBm);

                    }
                    childrenBm.add(itemBm);

                }else{

                }
            }
            unit.put("children", childrenBm);
            //添加分局信息
            optionsOne.add(unit);

        }


        //查询21个区局科室
        QueryWrapper<TOrgunits> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TOrgunits::getOrglevel, 99).eq(TOrgunits::getParentid, "6ba12fbe-0100-1000-e000-03fec0a85893");
        //部门
        List<TOrgunits> quJuTwo = iTOrgunitsService.list(queryWrapper);
        List<Map<String, Object>> optionsTwo = new ArrayList<>();
        List<String> liststr = new ArrayList<>();

        for (TOrgunits orgunits : quJuTwo) {
            Map<String, Object> unit = new HashMap<>();

            unit.put("value", orgunits.getOrgunitid());
            unit.put("label", orgunits.getOrgunitname());

            //查询有SMS短信权限的人
//            List<Map> buMens = tOrgunitsMapper.selectSMSPerson(orgunits.getOrgunitid());
//            //查询有标识的人
            List<Map> buMens = tOrgunitsMapper.selectMarkPerson(orgunits.getOrgunitid());
            List<Map> childrenBm = new ArrayList<>();

            for (Map buMen : buMens) {
                Map<String, Object> itemBm = new LinkedHashMap<>();

                itemBm.put("label",buMen.get("realname"));
                itemBm.put("value",buMen.get("mobile"));
                childrenBm.add(itemBm);
            }

            childrenBm=childrenBm.stream().distinct().collect(Collectors.toList());//去重
            unit.put("children", childrenBm);
            optionsTwo.add(unit);
        }

        mapOne.put("label","区局");
        mapOne.put("value","区局");
        mapOne.put("children",optionsTwo);
        list.add(mapOne);

        mapTwo.put("label","分局");
        mapTwo.put("value","分局");
        mapTwo.put("children",optionsOne);
        list.add(mapTwo);

        return list;
    }

    @ApiOperation(
            tags = "TOrgunitsController",
            value = "12345、12315短信发送对象级联数据查询接口")
    @PostMapping("getSMSPersonData")
    @ResponseBody
    public List<Map<String, Object>> getSMSPerson() {
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String,Object> mapOne = new HashMap<>();
        Map<String,Object> mapTwo = new HashMap<>();

        QueryWrapper<TOrgunits> queryWrapperOne = new QueryWrapper<>();
        queryWrapperOne.lambda().eq(TOrgunits::getOrglevel, 5).eq(TOrgunits::getParentid, "6ba12fbe-0100-1000-e000-03fec0a85893");
        //查询10个分局
        List<TOrgunits> fenJu = iTOrgunitsService.list(queryWrapperOne);
        List<Map<String, Object>> optionsOne = new ArrayList<>();
        for (TOrgunits orgunits :fenJu) {
            Map<String, Object> unit = new LinkedHashMap<>();
            unit.put("value", orgunits.getOrgunitid());
            unit.put("label", orgunits.getOrgunitname());

            //查询部门
            List<TOrgunits> buMens = iTOrgunitsService.list(Wrappers.<TOrgunits>lambdaQuery()
                    .eq(TOrgunits::getParentid, orgunits.getOrgunitid()));
            List<Map> childrenBm = new ArrayList<>();

            for (TOrgunits buMen : buMens) {
                Map<String, Object> itemBm = new LinkedHashMap<>();
                itemBm.put("value", buMen.getParentid());
                itemBm.put("label", buMen.getOrgunitname());
                //人
                List<Map> childrenR = new ArrayList<>();

                //查询有标记的人
                List<Map> tuser = tOrgunitsMapper.selectSMSPerson(buMen.getOrgunitid());//查询有SMS短信权限的人
//                List<Map> tuser = tOrgunitsMapper.selectMarkPerson(buMen.getOrgunitid());

                if (tuser != null&&!tuser.isEmpty()) {
                    for (Map tUsers : tuser) {
                        Map<String, Object> itemR = new LinkedHashMap<>();
                        itemR.put("value", tUsers.get("mobile"));
                        itemR.put("label", tUsers.get("realname"));
                        childrenR.add(itemR);
                        //childrenR= childrenR.stream().distinct().collect(Collectors.toList());//去重

                        itemBm.put("children", childrenR);

//                        childrenBm.add(newitemBm);

                    }
                    childrenBm.add(itemBm);

                }else{

                }
            }
            unit.put("children", childrenBm);
            //添加分局信息
            optionsOne.add(unit);

        }


        //查询21个区局科室
        QueryWrapper<TOrgunits> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TOrgunits::getOrglevel, 99).eq(TOrgunits::getParentid, "6ba12fbe-0100-1000-e000-03fec0a85893");
        //部门
        List<TOrgunits> quJuTwo = iTOrgunitsService.list(queryWrapper);
        List<Map<String, Object>> optionsTwo = new ArrayList<>();
        List<String> liststr = new ArrayList<>();

        for (TOrgunits orgunits : quJuTwo) {
            Map<String, Object> unit = new HashMap<>();

            unit.put("value", orgunits.getOrgunitid());
            unit.put("label", orgunits.getOrgunitname());

            //查询有权限的人
            List<Map> buMens = tOrgunitsMapper.selectSMSPerson(orgunits.getOrgunitid());//查询有SMS短信权限的人
//            //查询有标识的人
//            List<Map> buMens = tOrgunitsMapper.selectMarkPerson(orgunits.getOrgunitid());
            List<Map> childrenBm = new ArrayList<>();

            for (Map buMen : buMens) {
                Map<String, Object> itemBm = new LinkedHashMap<>();

                itemBm.put("label",buMen.get("realname"));
                itemBm.put("value",buMen.get("mobile"));
                childrenBm.add(itemBm);
            }

            childrenBm=childrenBm.stream().distinct().collect(Collectors.toList());//去重
            unit.put("children", childrenBm);
            optionsTwo.add(unit);
        }

        mapOne.put("label","区局");
        mapOne.put("value","区局");
        mapOne.put("children",optionsTwo);
        list.add(mapOne);

        mapTwo.put("label","分局");
        mapTwo.put("value","分局");
        mapTwo.put("children",optionsOne);
        list.add(mapTwo);

        return list;
    }


    /**
     *
     * 通过containsValue去重后放入新定义map
     *
     * @param map
     * @return
     *
     */
    private static Map<String, Object> deleteDuplicateValue(Map<String, Object> map) {

        if (map == null || map.size() == 0) {
            return new HashMap<String, Object>();
        }
        Map<String, Object> map2 = new HashMap<String, Object>();
        for (Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry<String, Object> entry = iterator.next();
            if (map2.containsValue(entry.getValue())) {
                continue;
            } else {
                map2.put(entry.getKey(), entry.getValue());
            }
        }
        System.out.println("去重");
        System.out.println(map2);
        return map2;
    }

    @ApiOperation(
            tags = "TOrgunitsController",
            value = "短信发送接口")
    @PostMapping("sendmessage")
    @ResponseBody
    public RestResult sendMessage(@RequestBody Map<String,Object> requestMap) throws Exception {


        try {
        QueryWrapper<Tasklist> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Tasklist::getCaseguid, requestMap.get("caseguid"));
        Tasklist tasklist = tasklistMapper.selectOne(queryWrapper);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Message message = new Message();
        Map<String, String> messagemap = new HashMap();
        Map<String, String> resultmap = new HashMap();

        String mType = "97";
        String mPassword = "v$HzWxpuiiNa6TmC";
        String newcontent = "你有受理登记新派工单号为" + requestMap.get("tserialnum") + "的案件待处理，" + "到期时间为" + sdf.format(tasklist.getSdexpirationdate());
        String content = URLEncoder.encode(newcontent, "GBK");
        String relateDocUuid = requestMap.get("caseguid").toString();
        ArrayList phonelist = (ArrayList) requestMap.get("phonelist");
        messagemap.put("mType", mType);
        messagemap.put("mPassword", mPassword);
        messagemap.put("content", content);
        messagemap.put("relateDocUuid", relateDocUuid);
        for (int i = 0; i < phonelist.size(); i++) {

            if (phonelist.get(i) != null) {

                String phones = phonelist.get(i) + ";" + "W" + ";" + "1";

                String userid = UsersAppMapper.selectUserid(phonelist.get(i).toString());

                QueryWrapper<TUsers> queryWrapper2 = new QueryWrapper<>();
                queryWrapper2.lambda().eq(TUsers::getUserid, userid).eq(TUsers::getLocked,'0').eq(TUsers::getDeleted,'0');
                TUsers tUsers = tUsersMapper.selectOne(queryWrapper2);
                AICUser userByPrimaryKey = orgUM.findUserByPrimaryKey(tUsers.getUserid());
                messagemap.put("phones", phones);
                message.setId(UuidUtil.getUuid());
                message.setCaseguid(relateDocUuid);
                message.setUnitname(userByPrimaryKey.getUnitName());
                message.setUserid(relateDocUuid);
                message.setFormtype(tasklist.getBustype());
                message.setMessagetype("0");
                message.setMobile(phonelist.get(i).toString());
                message.setReceivetime(new Date());
                message.setName(tUsers.getRealname());
                message.setEname(tUsers.getUsername());
                message.setId(UuidUtil.getUuid());
                message.setSerialnum(requestMap.get("tserialnum").toString());
                message.setSdexpirationdate(tasklist.getSdexpirationdate());
                message.setContent(newcontent);
                message.setSystemsource(tasklist.getSystemsource());

                String result = MessageUtil.sendMessage("http://61.142.131.10:888/OaListenerServlet", messagemap);
//                                String result="Http Request is accept.";
                if ("Http Request is accept.".equals(result)) {
                    message.setStatus("0");
                    messageMapper.insert(message);
                    //设置返回结果集
                    resultmap.put("state", "SendSuccessfully");
                    resultmap.put(tUsers.getRealname(), phonelist.get(i).toString());

                } else {
                    message.setStatus("1");
                    messageMapper.insert(message);
                    resultmap.put("state", "fail");
                }
            }
        }
            return RestResult.ok(resultmap);
    }catch(Exception e){
            return RestResult.error(ResultCode.SPECIFIED_QUESTIONED_USER_NOT_EXIST);
        }

    }
}
