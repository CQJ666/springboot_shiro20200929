package com.example.springboot_shiro20200929.shiroconfig;

import com.example.springboot_shiro20200929.bean.AuthTokenVo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 王飞
 * @Date: 2020/09/29/13:54
 * @Description:
 */

public class CustomFilter extends AuthenticatingFilter {
    private static Logger logger= LoggerFactory.getLogger(CustomFilter.class);
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        logger.info("执行createToken方法------");
        return null;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        //onAccessDenied：是否是拒绝登录
        //没有登录的情况下会走此方法
        logger.info("执行onAccessDenied方法------");

        return false;
    }
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //isAccessAllowed：判断是否登录
        //在登录的情况下会走此方法，此方法返回true直接访问控制器
        logger.info("执行isAccessAllowed方法------");
        AuthTokenVo jwtToken = new AuthTokenVo("fgGDSFASDSSSSSSSSSSSSSSSSSSSSSSSSSSS");
        getSubject(request, response).login(jwtToken);
        return true;
    }
}
