package com.study.admin.service.impl;

import com.study.admin.dao.FilesDao;
import com.study.admin.entities.Files;
import com.study.admin.service.FilesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/9 23:24
 */
@Service
public class FilesServiceImpl implements FilesService {
    @Resource
    private FilesDao filesDao;

    @Override
    public Integer insert(Files files) {
        return filesDao.insert(files);
    }

    @Override
    public List<Files> findByMd5(String md5) {
        return filesDao.findByMd5(md5);
    }

    @Override
    public Files findById(Integer id) {
        return filesDao.findById(id);
    }

    @Override
    public Integer updateIsDeleteById(Boolean isDelete,Integer id) {
        return filesDao.updateIsDeleteById(isDelete,id);
    }

    @Override
    public List<Files> selectPage(Integer pageNum, Integer pageSize,String name) {
        return filesDao.selectPage(pageNum,pageSize, name);
    }

    @Override
    public Integer deleteBatchFiles(List<Integer> ids) {
        return filesDao.deleteBatchFiles(ids);
    }

    @Override
    public Integer updateEnableById(Boolean enable, Integer id) {
        return filesDao.updateEnableById(enable,id);
    }

    @Override
    public Integer selectTotal() {
        return filesDao.selectTotal();
    }
}
