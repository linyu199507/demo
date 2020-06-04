package com.chinaweal.sdcs.crcs.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chinaweal.sdcs.crcs.business.entity.Materialinfo;
import com.chinaweal.sdcs.crcs.business.entity.Tasklist;
import com.chinaweal.sdcs.crcs.business.entity.vo.MaterialinfoVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author chinaweal
 * @since 2019-08-18
 */
public interface IMaterialinfoService extends IService<Materialinfo> {

    Map<String, Object> uploadMethod(MultipartFile file, HttpServletRequest request);

    IPage<MaterialinfoVo> searchTasklistByData(int currentPage, int size, Map<String, Object> requestMap);

    IPage<Tasklist> searchFilesInfo(int size, int current, Map<String, Object> requestMap);

    /**
     * 获取没有回填文件，并修改为已回填状态
     *
     * @param caseguid
     * @return
     */
    List<String> listLocalPathsAndUpdateBackfillStateByCaseguid(String caseguid);

    void deleteByPathAndName(Map<String,String> requestMap);
}
