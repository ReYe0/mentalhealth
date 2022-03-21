package com.study.front.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.study.front.dao.FilesDao;
import com.study.front.entities.Files;
import com.study.front.service.FilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/9 23:24
 */
@Service
public class FilesServiceImpl implements FilesService {
    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Resource
    private FilesDao filesDao;

    public Files upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();
        //        定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUID = uuid + StrUtil.DOT + type;


        File uploadFile = new File(fileUploadPath +fileUUID);
        //判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
        if(!uploadFile.getParentFile().exists()){
            uploadFile.getParentFile().mkdirs();
        }
        String url;
//        file.transferTo(uploadFile);
        //获取文件的md5
        String md5 = SecureUtil.md5(file.getInputStream());

        List<Files> dbFiles = filesDao.findByMd5(md5);
        //当文件存在的时候在获取文件的md5
        if(uploadFile.exists()){
            //获取文件的md5,通过对比md5 避免相同的文件上传
            md5 = SecureUtil.md5(uploadFile);

            //        获取文件的url
            if(dbFiles.size() != 0){
                Files firstFile = dbFiles.get(0);
                url = firstFile.getUrl();
            }else {
//        把获取到的文件存储到磁盘目录
//                file.transferTo(uploadFile);
                url =  "http://localhost:8002/file/" + fileUUID;
            }
        }else{
            //把获取到的文件存入到磁盘目录
            file.transferTo(uploadFile);
            md5 = SecureUtil.md5(uploadFile);
            url =  "http://localhost:8002/file/" + fileUUID;
        }



        //存储数据库
        Files saveFile = new Files();
        saveFile.setName(originalFilename);
        saveFile.setType(type);
        saveFile.setSize(size/1024);// 转为kb
        saveFile.setUrl(url);
        saveFile.setMd5(md5);
        filesDao.insert(saveFile);
        return saveFile;
    }

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
