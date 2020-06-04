package com.chinaweal.sdcs.crcs.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.business.entity.Materialinfo;
import com.chinaweal.sdcs.crcs.business.entity.Tasklist;
import com.chinaweal.sdcs.crcs.business.entity.vo.MaterialinfoVo;
import com.chinaweal.sdcs.crcs.business.mapper.MaterialinfoMapper;
import com.chinaweal.sdcs.crcs.business.mapper.TasklistMapper;
import com.chinaweal.sdcs.crcs.business.service.IMaterialinfoService;
import com.chinaweal.sdcs.crcs.statistical.common.util.TimeRelevantUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author chinaweal
 * @since 2019-08-18
 */
@Service
public class MaterialinfoServiceImpl extends ServiceImpl<MaterialinfoMapper, Materialinfo> implements IMaterialinfoService {

    @Autowired
    private MaterialinfoMapper materialinfoMapper;
    @Value("${crcs.upload.path}")
    private String uploadPath;
    @Value("${crcs.backfill.path}")
    private String backfillPath;

    @Autowired
    private TasklistMapper tasklistMapper;

    @Override
    public Map<String, Object> uploadMethod(MultipartFile file, HttpServletRequest request) {
        Materialinfo materialinfo = new Materialinfo();
        String status = "0";
        String caseguid = request.getParameter("caseguid");
        Map<String, Object> map = new HashMap<>();
        String filesavepath = "";
        String materialtype = "";
        String fileSaveName = "";
        if (StringUtils.isNotBlank(caseguid)) {
            if (file != null) {
                String filename = file.getOriginalFilename();
                //修改文件名为UUID以防重复
                String uuid = UUID.randomUUID().toString();
                String substring = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                fileSaveName = uuid + substring;

                materialtype = substring.substring(substring.lastIndexOf(".") + 1);
                File path = new File(uploadPath);
                if (!path.exists()) {
                    path.mkdirs();
                }
                File savefile = new File(path, fileSaveName);
                filesavepath = "/" + fileSaveName;
                try {
                    file.transferTo(savefile);
                    status = "1";
                } catch (IOException e) {
                    e.printStackTrace();
                }
                materialinfo.setId(uuid);
                materialinfo.setCaseguid(caseguid);
                materialinfo.setMaterialtype(materialtype);
                materialinfo.setAmount(new BigDecimal(1));
                materialinfo.setFilesavepath(filesavepath);
                materialinfo.setStatus(status);
                materialinfo.setUploadtime(new Date());
                materialinfo.setUpdatedate(new Date());
                materialinfo.setAttachfilename(filename);
                materialinfo.setBackfillState("local");
                materialinfoMapper.insert(materialinfo);
            }
        }
        map.put("name", materialinfo.getAttachfilename());
        map.put("url", materialinfo.getFilesavepath());
        map.put("status", status);
        return map;
    }

    @Override
    public IPage<MaterialinfoVo> searchTasklistByData(int currentPage, int size, Map<String, Object> requestMap) {
        QueryWrapper<Tasklist> wrapper = new QueryWrapper<>();
        String rqsttitle = (String) requestMap.get("rqsttitle");
        String tserialnum = (String) requestMap.get("tserialnum");
        String assigndate = (String) requestMap.get("assigndate");
        List<String> list = new ArrayList<>();
        Page<Tasklist> page = new Page();
        page.setCurrent(currentPage);
        page.setSize(size);
        if (StringUtils.isNotBlank(rqsttitle)) {
            wrapper.lambda().like(Tasklist::getRqsttitle, rqsttitle);
        }
        if (StringUtils.isNotBlank(tserialnum)) {
            wrapper.lambda().eq(Tasklist::getTserialnum, tserialnum);
        }
        if (StringUtils.isNotBlank(assigndate)) {
            Date date = TimeRelevantUtil.strToDate(assigndate);
            wrapper.lambda().eq(Tasklist::getAssigndate, date);
        }
        List<Tasklist> tasklists = tasklistMapper.selectList(wrapper);
        for (Tasklist tasklist : tasklists) {
            String caseguid = tasklist.getCaseguid();
            list.add(caseguid);
        }
        System.out.println(tasklists);

        return materialinfoMapper.selectTasklistVo(page, list);
    }

    @Override
    public IPage<Tasklist> searchFilesInfo(int size, int current, Map<String, Object> requestMap) {
        Page<Tasklist> page = new Page();
        page.setCurrent(current);
        page.setSize(size);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (StringUtils.isNotBlank((String) requestMap.get("enddate"))) {
            try {
                Date sDate = sdf.parse((String) requestMap.get("enddate"));
                Calendar c = Calendar.getInstance();
                c.setTime(sDate);
                c.add(Calendar.DAY_OF_MONTH, 1);
                sDate = c.getTime();
                String endDate = sdf.format(sDate);
                requestMap.put("enddate", endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return materialinfoMapper.searchFilesInfo(page, requestMap);
    }

    @Override
    public List<String> listLocalPathsAndUpdateBackfillStateByCaseguid(String caseguid) {
        QueryWrapper<Materialinfo> materialinfoWrapper = new QueryWrapper<>();
        materialinfoWrapper.lambda().eq(Materialinfo::getCaseguid, caseguid).eq(Materialinfo::getBackfillState, "local");
        List<Materialinfo> list = list(materialinfoWrapper);
        List<String> paths = new ArrayList<>();
        for (Materialinfo materialinfo : list) {
            materialinfo.setBackfillState("localBack");
            paths.add(backfillPath + materialinfo.getFilesavepath());
        }
        if (!list.isEmpty()) {
            updateBatchById(list);
        }
        return paths;
    }

    @Override
    public void deleteByPathAndName(Map<String,String> requestMap){
        materialinfoMapper.deletematerialinfo(requestMap);
    }
}
