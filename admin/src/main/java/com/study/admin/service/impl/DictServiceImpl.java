package com.study.admin.service.impl;

import com.study.admin.dao.DictDao;
import com.study.admin.entities.Dict;
import com.study.admin.service.DictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/13 14:56
 */
@Service
public class DictServiceImpl implements DictService {
    @Resource
    private DictDao dictDao;

    @Override
    public List<Dict> findByType(String type) {
        return dictDao.findByType(type);
    }
}
