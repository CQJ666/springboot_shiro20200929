package com.example.springboot_shiro20200929.service.impl;

import com.example.springboot_shiro20200929.bean.constant.ReturnMessage;
import com.example.springboot_shiro20200929.bean.entity.Admin;
import com.example.springboot_shiro20200929.jwtutils.JwtUtils;
import com.example.springboot_shiro20200929.mapper.AdminMapper;
import com.example.springboot_shiro20200929.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * <p>
 * 后台管理用户表 服务实现类
 * </p>
 *
 * @author 王飞
 * @since 2020-10-22
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {


    @Resource
    AdminMapper adminMapper;


    @Override
    public ReturnMessage userlogin(String userName, String password) {
        Admin admin=adminMapper.queryByUserName(userName);
        if (ObjectUtils.isEmpty(admin)) return ReturnMessage.failWithMsg("用户名不存在");
        String salt=admin.getSalt();
        String cryptPassword= Md5Crypt.apr1Crypt(password, salt);
        if (cryptPassword.equals(admin.getPassword())){
//            String token = JWTUtil.sign(userName, cryptPassword);
            String token= JwtUtils.createJWT(String.valueOf(admin.getAdminId()));
            return ReturnMessage.success().add("token", token);
        }
        return ReturnMessage.failWithMsg("用户名或密码错误");
    }
    @Override
    public boolean queryByUsername(String userName) {
        Admin admin=adminMapper.queryByUserName(userName);
        if (ObjectUtils.isEmpty(admin)) return false;
        return true;
    }
}
