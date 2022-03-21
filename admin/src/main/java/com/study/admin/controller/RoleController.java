package com.study.admin.controller;

import com.study.admin.common.CommonResult;
import com.study.admin.entities.Role;
import com.study.admin.entities.Staff;
import com.study.admin.service.RoleMenuService;
import com.study.admin.service.RoleService;
import com.study.admin.utils.TokenUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/12 21:36
 */
@RestController
@RequestMapping(value = "/role")
public class RoleController {
    @Resource
    private RoleService roleService;
    @Resource
    private RoleMenuService roleMenuService;

    @GetMapping
    public CommonResult findAll(){
        return CommonResult.success(roleService.findAll());

    }
/**
 * 绑定角色和菜单的关系
 * @author 二爷
 * @E-mail 1299461580@qq.com
 * @date 2022/3/13 15:38
 * @param  roleId 角色id
 * @param  menuIds 菜单id数组
 * @return com.study.admin.common.CommonResult
 */
    @PostMapping(value = "/selectMenu/{roleId}")
    public CommonResult roleMenu(@PathVariable Integer roleId,@RequestBody List<Integer> menuIds){
        roleMenuService.setRoleMenu(roleId,menuIds);
        return CommonResult.success();
    }
    @GetMapping(value = "/getMenu/{roleId}")
    public CommonResult roleMenu(@PathVariable Integer roleId){
        List<Integer> roleMenu = roleMenuService.getRoleMenu(roleId);
        System.out.println(roleMenu);
        return CommonResult.success(roleMenu);
    }

    //    分页查询
    @GetMapping(value = "/page")
    public CommonResult findPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestParam(defaultValue = "") String name){
        pageNum = (pageNum - 1) * pageSize;
        List<Role> roles = roleService.selectPage(pageNum, pageSize,name);
//        name = '%' + name + '%';
        Integer total = roleService.selectTotal(name);
        Map<String,Object> res = new HashMap<>();
        res.put("roles",roles);
        res.put("total",total);
        TokenUtil.getCurrentAdminUser();
        if(total > 0){
            return CommonResult.success(res);
        }else{
            return CommonResult.error();
        }
    }
    //保存和更新
    @PostMapping(value = "/save")
    public CommonResult save(@RequestBody Role role){
        Integer status = 0;
        if(role.getId() ==null){
            status = roleService.addRole(role);

        }else {
            status = roleService.updateRole(role);
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
        Integer status = roleService.deleteById(id);
        if(status > 0){
            return CommonResult.success();
        }else{
            return CommonResult.error();
        }
    }
    //    删除多个角色
    @PostMapping(value = "/del/batch")
    public CommonResult deleteBatchRole(@RequestBody List<Integer> ids){
        Integer status = roleService.deleteBatchRole(ids);
        if(status > 0){
            return  CommonResult.success();
        }else{
            return CommonResult.error();
        }
    }
}
