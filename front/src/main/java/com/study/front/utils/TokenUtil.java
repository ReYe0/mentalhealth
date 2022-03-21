package com.study.front.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.study.front.entities.User;
import com.study.front.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/9 18:55
 */
@Component
public class TokenUtil {
    private static UserService staticUserService;
    @Resource
    private UserService userService;
    @PostConstruct
    public void setUserService(){
        staticUserService = userService;
    }
    /**
     * 生成token
     * @author 二爷
     * @E-mail 1299461580@qq.com
     * @date 2022/3/9 20:08
     * @param  adminId
     * @param  sign
     * @return java.lang.String
     */
    public static String genToken(String adminId,String sign){
       return JWT.create().withAudience(adminId) //将admin id 保存到 token 里面 作为载荷
        .withExpiresAt(DateUtil.offsetHour(new Date(),2)) //2小时候token过期
        .sign(Algorithm.HMAC256(sign));// 以password 作为 token 的秘钥
    }

    /**
     * 获取当前登陆的用户信息
     * @author 二爷
     * @E-mail 1299461580@qq.com
     * @date 2022/3/9 21:33 
     * @param  
     * @return com.study.admin.entities.User
     */
    public static User getCurrentUser(){
        try{
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if(StrUtil.isNotBlank(token)) {
                String userId = JWT.decode(token).getAudience().get(0);
                return staticUserService.getUserById(Integer.valueOf(userId));
            }
            }catch (Exception e){
                return  null;
            }
        return null;
    }

}
