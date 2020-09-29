package com.example.springboot_shiro20200929.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 王飞
 * @Date: 2020/09/29/17:10
 * @Description:
 */
@RestController
public class UserController {



    @RequestMapping("aaa")
    @RequiresPermissions("shop")
    public  String aaa(){
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        return "成功";
    }
}
