package com.study.front.dao;

import com.study.front.entities.Files;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/9 23:23
 */
@Mapper
public interface FilesDao {
//    彻底删除文件
    public Integer deleteById(@Param("id")Integer id);
//上传文件
    public Integer insert(Files files);
//    查找数据库中是否存在该文件 根据md5
    public List<Files> findByMd5(String md5);
//    根据id查找
    public Files findById(Integer id);
//   删除一个文件  更新字段isdelete
    public Integer updateIsDeleteById(@Param("isDelete") Boolean isDelete,@Param("id") Integer id);
    //分页查询
    public List<Files> selectPage(@Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize,@Param("name") String name);
//    查询总数
    public Integer selectTotal();
    //删除多个文件 更新isdelete
    public Integer deleteBatchFiles(@Param("ids") List<Integer> ids);
//    是否启用
    public Integer updateEnableById(@Param("enable") Boolean enable,@Param("id")Integer id);
}
