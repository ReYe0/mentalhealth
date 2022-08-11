package com.study.admin.controller;

import com.study.admin.common.CommonResult;
import com.study.admin.common.Constants;
import com.study.admin.entities.Menu;
import com.study.admin.entities.Role;
import com.study.admin.service.DictService;
import com.study.admin.service.MenuService;
import com.study.admin.utils.TokenUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/12 22:24
 */
@RestController
@RequestMapping(value = "/menu")
public class MenuController {
    @Resource
    private MenuService menuService;
    @Resource
    private DictService dictService;
    @GetMapping(value = "/icons")
    public CommonResult getIcons(){
        return CommonResult.success(dictService.findByType(Constants.DICT_TYPE_ICON));
    }

    //找出所有菜单的数据
    @GetMapping()
    public CommonResult findAll(@RequestParam(defaultValue = "") String name){
        return CommonResult.success(menuService.findMenus(name));
    }

    //    分页查询
    @GetMapping(value = "/page")
    public CommonResult findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestParam(defaultValue = "") String name){
        pageNum = (pageNum - 1) * pageSize;
        List<Menu> menus = menuService.selectPage(pageNum, pageSize,name);
        Integer total = menuService.selectTotal(name);

        Map<String,Object> res = new HashMap<>();
        res.put("menus",menus);
        res.put("total",total);
        TokenUtil.getCurrentAdminUser();
        if(total > 0){
            return CommonResult.success(res);
        }else{
            return CommonResult.error();
        }
    }

    //保存和更新
    @PostMapping(value = "/save",produces = "application/json")
    public CommonResult save(@RequestBody Menu menu){
        Integer status = 0;
        System.out.println("111111111111111");
        if(menu.getId() == null){
            status = menuService.addMenu(menu);
        }else {
            status = menuService.updateMenu(menu);
        }
        if(status > 0){
            return CommonResult.success();
        }else{
            return CommonResult.error();
        }
    }
    //    删除
    @DeleteMapping(value = "/del/{id}")
    public CommonResult deleteRole(@PathVariable Integer id){
        Integer status = menuService.deleteById(id);
        if(status > 0){
            return CommonResult.success();
        }else{
            return CommonResult.error();
        }
    }
    //    删除多个角色
    @PostMapping(value = "/del/batch")
    public CommonResult deleteBatchRole(@RequestBody List<Integer> ids){
        Integer status = menuService.deleteBatchMenu(ids);
        if(status > 0){
            return  CommonResult.success();
        }else{
            return CommonResult.error();
        }
    }

}
