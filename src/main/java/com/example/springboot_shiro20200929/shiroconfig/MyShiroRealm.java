package com.example.springboot_shiro20200929.shiroconfig;

import com.example.springboot_shiro20200929.bean.entity.Admin;

import com.example.springboot_shiro20200929.jwtutils.JwtUtils;
import com.example.springboot_shiro20200929.mapper.AdminMapper;
import com.example.springboot_shiro20200929.mapper.PermMapper;
import com.example.springboot_shiro20200929.mapper.RoleMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {
    private static Logger logger= LoggerFactory.getLogger(AuthorizingRealm.class);
    @Resource
    AdminMapper adminMapper;
    @Resource
    RoleMapper roleMapper;
    @Resource
    PermMapper permMapper;

    /**
     * 重写，绕过身份令牌异常导致的shiro报错
     * @param authenticationToken
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken authenticationToken){
        return authenticationToken instanceof JWTToken;
    }




    /*
    * @Description: 这个方法 主要用于授权
    * @Param: [principalCollection]
    * @return: org.apache.shiro.authz.AuthorizationInfo
    * @Author: 王飞
    * @Date: 2020/9/29 15:29
    **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("执行doGetAuthorizationInfo方法------授权");
        //1  获取主体信息  2根据用户信息去查角色
        //获取用户登录信息
        Long  adminId = JwtUtils.getUserId(principalCollection.toString());
//        Admin admin=adminMapper.selectById(userId);
//        List<Role> listRole=roleMapper.queryByAdminIdListRole(admin.getAdminId());//根据用户id去查询角色

        List<String> listperm= permMapper.queryByUserIdListperm(adminId);
        //添加角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(listperm);//权限
        return authorizationInfo;

    }


    /*
    * @Description: 这个方法  主要用于认证
    * @Param: [authenticationToken]
    * @return: org.apache.shiro.authc.AuthenticationInfo
    * @Author: 王飞
    * @Date: 2020/9/29 15:29
    **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("执行doGetAuthenticationInfo方法------认证");
        String token = (String) authenticationToken.getCredentials();
        //从token中获取用户名
        Long userId = JwtUtils.getUserId(token);
        //获取数据库中存取的用户，密码是加密后的
        Admin admin = adminMapper.selectById(userId);
        if (admin != null) {
            //
            if (JwtUtils.isExpiration(token)) {
                throw new IncorrectCredentialsException();
            }
            return new SimpleAuthenticationInfo(token, token, getName());
        } else {
            throw new UnknownAccountException();
        }
    }

    @Override
    public  boolean isPermitted(PrincipalCollection principals, String permission){
        logger.info("判断权限-----------------------------------------");
        Long  adminId = JwtUtils.getUserId(principals.toString());
        Admin admin = adminMapper.selectById(adminId);
        if (ObjectUtils.isEmpty(admin))return false;
        return admin.getAdminName().equals("admin")||super.isPermitted(principals,permission);
    }

}