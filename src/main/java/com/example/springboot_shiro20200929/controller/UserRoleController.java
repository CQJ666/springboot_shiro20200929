package com.example.springboot_shiro20200929.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.example.springboot_shiro20200929.bean.ReturnMessage;
import com.example.springboot_shiro20200929.bean.Role;
import com.example.springboot_shiro20200929.bean.UserRole;
import com.example.springboot_shiro20200929.mapper.RoleMapper;
import com.example.springboot_shiro20200929.service.RoleService;
import com.example.springboot_shiro20200929.service.UserRoleService;
import net.bytebuddy.asm.Advice;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 用户角色关系表 前端控制器
 * </p>
 *
 * @author 王飞
 * @since 2020-09-30
 */
@Controller
@RequestMapping("/userRole")
public class UserRoleController {

    @Resource
    RoleService roleService;
    @Resource
    RoleMapper roleMapper;
    @Resource
    UserRoleService userRoleService;

    /*
    * @Description: 添加角色信息 仅管理员用户
    * @Param: [role]
    * @return: com.example.springboot_shiro20200929.bean.ReturnMessage
    * @Author: 王飞
    * @Date: 2020/9/30 17:17
    **/
    @RequestMapping("/addrole")
    @RequiresPermissions("addrole")
    public ReturnMessage insert(@RequestBody Map<String,Object> map ){
        String userId=map.get("userId").toString()==null ? null : map.get("userId").toString();//用户id
        String position=map.get("position").toString()==null ? null : map.get("position").toString();//角色名称
        String describe=map.get("describe").toString()==null ? null : map.get("describe").toString();//角色描述
        String authorityName=map.get("authorityName").toString()==null ? null : map.get("authorityName").toString();//权限名称
        String resourceName=map.get("resourceName").toString()==null ? null : map.get("resourceName").toString();//资源名称
        String resourceUrl=map.get("resourceUrl").toString()==null ? null : map.get("resourceUrl").toString();//资源路径

        if (StringUtils.isEmpty(position))return ReturnMessage.failWithMsg("角色名称不能为空");
        Role role =roleMapper.queryByName(position);//根据角色信息查询角色

        if (ObjectUtils.isEmpty(role)){//数据库没有该角色
                Role sqlrole=new Role();
                sqlrole.setDescribe(describe);
                sqlrole.setPosition(position);
                roleService.save(sqlrole);//保存新增角色信息

                UserRole  userRole =new UserRole();
                userRole.setRoleId(sqlrole.getRoleId());
                userRole.setUserId(Integer.valueOf(userId));
                userRoleService.save(userRole);//保存用户角色关系信息
        }else {//数据库中有该角色信息
            UserRole  userRole =new UserRole();
            userRole.setRoleId(role.getRoleId());
            userRole.setUserId(Integer.valueOf(userId));
            userRoleService.save(userRole);//保存用户角色关系信息
        }
        return ReturnMessage.failWithMsg("添加角色失败");
    }



}

