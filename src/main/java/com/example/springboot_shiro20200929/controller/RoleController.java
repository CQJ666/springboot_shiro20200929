package com.example.springboot_shiro20200929.controller;


import com.example.springboot_shiro20200929.bean.ReturnMessage;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author 王飞
 * @since 2020-09-30
 */
@Controller
@RequestMapping("/role")

public class RoleController {

    @RequestMapping("/aaa")
    @RequiresPermissions("aaa")
    @ResponseBody
    public ReturnMessage aaa(){
        return ReturnMessage.success();
    }
}

