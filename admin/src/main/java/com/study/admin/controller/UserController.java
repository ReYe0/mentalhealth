package com.study.admin.controller;

import com.study.admin.controller.dto.UserRequest;
import com.study.admin.entities.User;
import com.study.admin.common.CommonResult;
import com.study.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/6 16:54
 */
@CrossOrigin
@RestController //@RestController 是@controller和@ResponseBody 的结合
//@Controller 将当前修饰的类注入SpringBoot IOC容器，使得从该类所在的项目跑起来的过程中，这个类就被实例化。
//@ResponseBody 它的作用简短截说就是指该类中所有的API接口返回的数据，甭管你对应的方法返回Map或是其他Object，它会以Json字符串的形式返回给客户端
@Slf4j //是用作日志输出的，一般会在项目每个类的开头加入该注解，如果不写下面这段代码，并且想用log 添加了该注释之后，就可以在代码中直接饮用log.info( ) 打印日志了
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;
//登陆
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public CommonResult Login(@RequestBody User user, UserRequest userRequest){
        UserRequest response = userService.login(user, userRequest);
        return CommonResult.success(response);
    }
//用户信息
    @GetMapping(value = "/get/{id}")
    public CommonResult getUserById(@PathVariable("id") Integer id){
        User user = userService.getUserById(id);
        log.info("***查询结果："+ user);
        if(user != null){
            return CommonResult.success(user);
        }else {
            return CommonResult.error();
        }
    }
    //保存用户信息
    @PostMapping(value = "/save")
    public CommonResult saveAdmin(@RequestBody User user){
        Integer status = userService.updateAdminById(user, user.getId());

        return status > 0 ? CommonResult.success(userService.getUserById(user.getId())) : CommonResult.error();

    }

    @PostMapping(value = "/create")
    public CommonResult create(User user){
        int result = userService.create(user);
        log.info("***插入结果："+result);
        if(result > 0){
            return CommonResult.success(result);
        }else {
            return CommonResult.error();
        }
    }
}
