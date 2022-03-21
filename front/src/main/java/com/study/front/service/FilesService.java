package com.study.front.service;

import com.study.front.entities.Files;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/9 23:24
 */
public interface FilesService {
    //文件上传保存到本地
    public Files upload(MultipartFile file) throws IOException;

    //上传文件
    public Integer insert(Files files);
    //    查找数据库中是否存在该文件 根据md5
    public List<Files> findByMd5(String md5);
    //    根据id查找
    public Files findById(Integer id);
    //  删除一个文件  更新字段isdelete
    public Integer updateIsDeleteById(Boolean isDelete,Integer id);
    // 分页查询
    public List<Files> selectPage( Integer pageNum, Integer pageSize,String name);
    //删除多个文件 更新isdelete
    public Integer deleteBatchFiles(@Param("ids") List<Integer> ids);
    //    是否启用
    public Integer updateEnableById(Boolean enable,Integer id);
    //    查询总数
    public Integer selectTotal();
}
