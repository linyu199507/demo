package com.chinaweal.sdcs.crcs.crawler.bmfw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chinaweal.sdcs.crcs.crawler.bmfw.entity.Accessory;
import com.chinaweal.sdcs.crcs.crawler.bmfw.mapper.AccessoryMapper;
import com.chinaweal.sdcs.crcs.crawler.bmfw.service.IAccessoryService;
import org.springframework.stereotype.Service;

@Service
public class AccessoryServiceImpl extends ServiceImpl<AccessoryMapper, Accessory> implements IAccessoryService {
}
