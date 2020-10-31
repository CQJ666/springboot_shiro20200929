package com.example.springboot_shiro20200929.controller;


import com.example.springboot_shiro20200929.bean.constant.ReturnMessage;
import com.example.springboot_shiro20200929.bean.entity.Admin;
import com.example.springboot_shiro20200929.bean.entity.AdminRole;
import com.example.springboot_shiro20200929.service.AdminRoleService;
import com.example.springboot_shiro20200929.service.AdminService;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 后台管理用户表 前端控制器
 * </p>
 *
 * @author 王飞
 * @since 2020-10-22
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    AdminService adminService;
    @Resource
    AdminRoleService adminRoleService;



    /*
     * @Description: 添加用户信息
     * @Param: [map]
     * @return: com.lzqs.yuanzilian.constant.ReturnMessage
     * @Author: 王飞
     * @Date: 2020/10/15 13:58
     **/
    @RequestMapping("/addAdminUser")
    @RequiresPermissions("admin")
    @ResponseBody
    @Transactional
    public  ReturnMessage addAdminUser(@RequestBody Map<String,Object> map) {
        String adminName= map.get("adminName").toString();//
        String password= map.get("password").toString();//
        List<Object> roleIds = map.get("roleIds") == null ? null : (List<Object>) map.get("roleIds");
        if (StringUtils.isEmpty(adminName) || StringUtils.isEmpty(password))return  ReturnMessage.failWithMsg("用户名或密码不能为空");
        if (roleIds==null)return ReturnMessage.failWithMsg("角色id不能为空");
        if(adminService.queryByUsername(adminName))return ReturnMessage.failWithMsg("用户已存在，不能重复添加");
        //生成随机盐
        String salt = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        salt = salt.substring(0, 10);
        String cryptPwd = Md5Crypt.apr1Crypt(password, salt);//通过盐加密后密码
        Admin admin=new Admin();
        admin.setPassword(cryptPwd);
        admin.setSalt(salt);
        admin.setAdminName(adminName);
        admin.setCtime(LocalDateTime.now());
        adminService.save(admin);
        for (Object id:roleIds) {
            AdminRole adminRole=new AdminRole();
            adminRole.setRoleId(Long.valueOf(id.toString()));
            adminRole.setAdminId(admin.getAdminId());
            adminRoleService.save(adminRole);
        }
        return ReturnMessage.success();
    }


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
        String adminName= map.get("adminName").toString();//职位
        String password= map.get("password").toString();//
        if (StringUtils.isEmpty(adminName) || StringUtils.isEmpty(password))return  ReturnMessage.failWithMsg("用户名或密码不能为空");
        return adminService.userlogin(adminName,password);
    }

}

