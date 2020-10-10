package com.example.springboot_shiro20200929.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.example.springboot_shiro20200929.bean.ReturnMessage;
import com.example.springboot_shiro20200929.bean.User;
import com.example.springboot_shiro20200929.jwtutils.JWTUtil;
import com.example.springboot_shiro20200929.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 王飞
 * @since 2020-09-30
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    /*
    * @Description: 登录
    * @Param: []
    * @return: com.example.springboot_shiro20200929.bean.ReturnMessage
    * @Author: 王飞
    * @Date: 2020/10/9 10:09
    **/
    @RequestMapping("/login")
    @ResponseBody
    public ReturnMessage userLogin(@RequestBody Map<String,Object> map){
        String userMobile=map.get("userMobile").toString();
        String password=map.get("password").toString();

        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_mobile",userMobile);
        queryWrapper.eq("password",password);
        List<User> list= userService.list(queryWrapper);
        if(CollectionUtils.isNotEmpty(list)){
            String token=JWTUtil.sign(userMobile,password);
            return ReturnMessage.success().add("token",token);
        }
        return ReturnMessage.failWithMsg("用户名或密码错误");

    }

}

