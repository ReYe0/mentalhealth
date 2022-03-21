package com.study.front.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.study.front.common.CommonResult;
import com.study.front.dao.FilesDao;
import com.study.front.entities.Files;
import com.study.front.service.FilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
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
    @Resource
    private FilesDao filesDao;

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

        return CommonResult.success(filesService.upload(file));
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
    @DeleteMapping(value = "/deepDel/{id}")
    public CommonResult deleteStaff(@PathVariable Integer id){
        Files files = filesService.findById(id);
        if(files != null){
            return CommonResult.success(filesDao.deleteById(id));
        }else{
            return CommonResult.error();
        }
    }
    //    彻底删除文件
    @DeleteMapping(value = "/del/{id}")
    public CommonResult deleteFile(@PathVariable Integer id){
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
