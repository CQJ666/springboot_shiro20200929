package com.example.springboot_shiro20200929.service;

import com.example.springboot_shiro20200929.bean.constant.ReturnMessage;
import com.example.springboot_shiro20200929.bean.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 后台管理用户表 服务类
 * </p>
 *
 * @author 王飞
 * @since 2020-10-22
 */
public interface AdminService extends IService<Admin> {

    ReturnMessage userlogin(String userName, String password);

    //根据username查询用户是否存在
    boolean  queryByUsername(String userName);
}
