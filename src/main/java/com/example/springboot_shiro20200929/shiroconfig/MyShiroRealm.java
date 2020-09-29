package com.example.springboot_shiro20200929.shiroconfig;

import com.example.springboot_shiro20200929.bean.AuthTokenVo;
import com.example.springboot_shiro20200929.bean.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

public class MyShiroRealm extends AuthorizingRealm {
    private static Logger logger= LoggerFactory.getLogger(AuthorizingRealm.class);



    /**
     * 重写，绕过身份令牌异常导致的shiro报错
     * @param authenticationToken
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken authenticationToken){
        return authenticationToken instanceof AuthTokenVo;
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


        principalCollection.getPrimaryPrincipal();


        return null;
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
        //authenticationToken  主体传过来的信息
        String password=(String) authenticationToken.getPrincipal();
        String userMobile=(String) authenticationToken.getPrincipal();
        logger.info(password+userMobile+"数据信息");
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                password,
                userMobile,
                getName());
        return authenticationInfo;
        //拿到信息去数据库 获取凭证
//        User user=getUserBypasswordoruserMobile(userMobile,password);

    }

    
    /*
    * @Description: 去数据库中获取信息
    * @Param: [userMobile, password]
    * @return: com.example.springboot_shiro20200929.bean.User
    * @Author: 王飞
    * @Date: 2020/9/29 15:41
    **/
//    private User getUserBypasswordoruserMobile(String userMobile,String password){
//        User user=new User();
//        return user;
//
//    }
}