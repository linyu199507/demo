package com.chinaweal.sdcs.crcs.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.aicorg.model.AICUser;
import com.chinaweal.sdcs.crcs.business.entity.Statisticsinfo;
import com.chinaweal.sdcs.crcs.business.mapper.StatisticsinfoMapper;
import com.chinaweal.sdcs.crcs.business.service.IStatisticsinfoService;
import com.chinaweal.sdcs.crcs.statistical.common.util.GetPropertiesValue;
import com.chinaweal.sdcs.crcs.system.entity.Loglist;
import com.chinaweal.sdcs.crcs.system.mapper.LoglistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author chinaweal
 * @since 2019-11-22
 */
@Service
public class StatisticsinfoServiceImpl extends ServiceImpl<StatisticsinfoMapper, Statisticsinfo> implements IStatisticsinfoService {

    @Autowired
    private StatisticsinfoMapper statisticsinfoMapper;
    @Value("${crcs.upload.path}")
    private String uploadPath;
    @Autowired
    private LoglistMapper loglistMapper;

    @Override
    public Map<String, Object> uploadMethod(MultipartFile file, HttpServletRequest request,Map aicUser) {
        Statisticsinfo statisticsinfo = new Statisticsinfo();
        String status = "1";
        Map<String, Object> map = new HashMap<>();
        String filesavepath = "";
        String filetype = "";
        String fileSaveName = "";
        if (file != null) {
            String filename = file.getOriginalFilename();
            //修改文件名为UUID以防重复
            String uuid = UUID.randomUUID().toString();
            String caseguid = UUID.randomUUID().toString();
            String substring = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            fileSaveName = uuid + substring;

            filetype = substring.substring(substring.lastIndexOf(".") + 1);
            File path = new File(uploadPath);
            if (!path.exists()) {
                path.mkdirs();
            }
            File savefile = new File(path, fileSaveName);
            filesavepath = "/" + fileSaveName;
            try {
                file.transferTo(savefile);
                status = "0";
            } catch (IOException e) {
                e.printStackTrace();
            }
            statisticsinfo.setId(uuid);
            statisticsinfo.setCaseguid(caseguid);
            statisticsinfo.setFiletype(filetype);
            statisticsinfo.setAmount(new BigDecimal(1));
            statisticsinfo.setFilesavepath(filesavepath);
            statisticsinfo.setStatus(status);
            statisticsinfo.setUploadtime(new Date());
            statisticsinfo.setUpdatedate(new Date());
            statisticsinfo.setFilename(filename);
            statisticsinfo.setHandler((String) aicUser.get("handler"));
            statisticsinfo.setHandleruserid((String) aicUser.get("handleruserid"));
            statisticsinfoMapper.insert(statisticsinfo);
        }
        map.put("status",status);
        return map;
    }

    @Override
    public IPage<Statisticsinfo> searchStatisticsinfos(int size, int current, Map<String, Object> requestMap) {
        Page<Statisticsinfo> page = new Page();
        page.setCurrent(current);
        page.setSize(size);
        return statisticsinfoMapper.searchStatisticsinfos(page, requestMap);
    }

    @Override
    public void deleteByIds(String[] ids, AICUser aicUser) {
        Loglist loglist = null;
        for (String id : ids) {
            Statisticsinfo statisticsinfo = statisticsinfoMapper.selectById(id);
            if (statisticsinfo != null) {
                statisticsinfo.setStatus("1");
                statisticsinfoMapper.updateById(statisticsinfo);
                //保存删除日志
                loglist = new Loglist();
                loglist.setId(UUID.randomUUID().toString());
                loglist.setOperationid(aicUser.getPrimaryKey());
                loglist.setOperationname(aicUser.getName());
                loglist.setOperationinfo("删除文件名为："+ statisticsinfo.getId() +" 的历史数据。");
                loglist.setOperationid("deleteBatchIds");
                loglist.setCaseguid(statisticsinfo.getCaseguid());
                loglist.setOperationtime(new Date());
                loglist.setUpdatedate(new Date());
                loglistMapper.insert(loglist);
            }
        }
    }
}
