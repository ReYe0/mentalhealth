package com.study.admin.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.study.admin.common.CommonResult;
import com.study.admin.entities.Files;
import com.study.admin.entities.Staff;
import com.study.admin.service.FilesService;
import com.study.admin.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 文件上传相关接口
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/9 21:43
 */
@RestController
@RequestMapping("/file")
public class FilesController {
    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Resource
    private FilesService filesService;



    /**
     * 文件上传接口
     * @author 二爷
     * @E-mail 1299461580@qq.com
     * @date 2022/3/9 21:59
     * @param  file 前端传递过来的文件
     * @return java.lang.String
     */
    @PostMapping(value = "/upload")
    public CommonResult upload(@RequestParam MultipartFile file) throws IOException {
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
//        String url;
//        // 获取文件的md5
//        String md5 = SecureUtil.md5(file.getInputStream());
//        // 从数据库查询是否存在相同的记录
//        Files dbFiles = getFileByMd5(md5);
//        if (dbFiles != null) {
//            url = dbFiles.getUrl();
//        } else {
//            // 上传文件到磁盘
//            file.transferTo(uploadFile);
//            // 数据库若不存在重复文件，则不删除刚才上传的文件
//            url = "http://" + serverIp + ":9090/file/" + fileUUID;
//        }
        String url;
//        file.transferTo(uploadFile);
        //获取文件的md5
        String md5 = SecureUtil.md5(file.getInputStream());

        List<Files> dbFiles = filesService.findByMd5(md5);
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
                url =  "http://localhost:8001/file/" + fileUUID;
            }
        }else{
            //把获取到的文件存入到磁盘目录
            file.transferTo(uploadFile);
            md5 = SecureUtil.md5(uploadFile);
            url =  "http://localhost:8001/file/" + fileUUID;
        }



        //存储数据库
        Files saveFile = new Files();
        saveFile.setName(originalFilename);
        saveFile.setType(type);
        saveFile.setSize(size/1024);// 转为kb
        saveFile.setUrl(url);
        saveFile.setMd5(md5);
        filesService.insert(saveFile);
        return CommonResult.success(saveFile);
    }

    /**
     * 文件下载接口  http://localhost:8001/file/{fileUUID}
     * @author 二爷
     * @E-mail 1299461580@qq.com
     * @date 2022/3/9 23:54
     * @param  fileUUID
     * @param  response
     * @return void
     */
    @GetMapping("/{fileUUID}")
    public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
//        根据文件的唯一表示码获取文件
        File uploadFile = new File(fileUploadPath + fileUUID);
        ServletOutputStream os = response.getOutputStream();
//        设置输出流的格式
        response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(fileUUID,"UTF-8"));
        response.setContentType("application/octet-stream");
//读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();

    }
//    是否启用
    @PostMapping("/update/enable")
    public CommonResult changeEnable(@RequestBody Files files){
        Files originFile = filesService.findById(files.getId());
        if(originFile.getEnable() == true){
            return CommonResult.success(filesService.updateEnableById(false, files.getId()));
        }else{
            return CommonResult.success(filesService.updateEnableById(true, files.getId()));
        }
    }
    //    删除文件
    @DeleteMapping(value = "/del/{id}")
    public CommonResult deleteStaff(@PathVariable Integer id){
        Files files = filesService.findById(id);
        if(files != null){
            return CommonResult.success(filesService.updateIsDeleteById(true,id));
        }else{
            return CommonResult.error();
        }
    }
    //    删除多个文件
    @PostMapping(value = "/del/batch")
    public CommonResult deleteBatchStaff(@RequestBody List<Integer> ids){
        Integer status = filesService.deleteBatchFiles(ids);
        if(status > 0){
            return  CommonResult.success();
        }else{
            return CommonResult.error();
        }
    }
    //分页查询
    @GetMapping(value = "/page")
    public CommonResult findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam(defaultValue = "") String name){
        pageNum = (pageNum - 1) * pageSize;
        List<Files> files = filesService.selectPage(pageNum, pageSize,name);
//        name = '%' + name + '%';
        Integer total = filesService.selectTotal();
        Map<String,Object> res = new HashMap<>();
        res.put("fileList",files);
        res.put("total",total);
//        TokenUtil.getCurrentAdminUser();
        if( res.size() > 0){
            return CommonResult.success(res);
        }else{
            return CommonResult.error();
        }
    }

}
