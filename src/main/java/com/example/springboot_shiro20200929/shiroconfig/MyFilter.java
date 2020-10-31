package com.example.springboot_shiro20200929.shiroconfig;

import com.example.springboot_shiro20200929.bean.constant.AuthTokenVo;
import com.example.springboot_shiro20200929.bean.constant.ReturnMessage;
import com.example.springboot_shiro20200929.jwtutils.JWTUtil;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 王飞
 * @Date: 2020/09/29/13:54
 * @Description:
 */

public class MyFilter extends AuthenticatingFilter {
    private static Logger logger= LoggerFactory.getLogger(MyFilter.class);
    private ReturnMessage responseResult;
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        logger.info("执行createToken方法------");
        return null;
    }

    /**
     * 解决权限不足302问题
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //onAccessDenied：是否是拒绝登录
        //没有登录的情况下会走此方法
        logger.info("执行onAccessDenied方法------");
        Subject subject = getSubject(request, response);
        if (subject.getPrincipal() == null) {
            saveRequestAndRedirectToLogin(request, response);
        } else {
            WebUtils.toHttp(response).setContentType("application/json; charset=utf-8");
        }
        return false;
    }
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //isAccessAllowed：判断是否登录
        //在登录的情况下会走此方法，此方法返回true直接访问控制器
        logger.info("执行isAccessAllowed方法------");
        String token= JWTUtil.getRequestToken((HttpServletRequest) request);
        if (!StringUtils.isEmpty(token)){
            try {
                this.executeLogin(request, response);
            } catch (Exception e) {
                // 应用异常
                logger.info(e.getMessage());
                return false;
            }
        } else {
            // cookie中未检查到token或token为空
            responseResult=  ReturnMessage.failWithMsg("token为空");
            return false;
        }
        return true;
    }


    /**
     * 用户存在，执行登录认证
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        String token = JWTUtil.getRequestToken((HttpServletRequest)request);
        AuthTokenVo jwtToken = new AuthTokenVo(token);
        // 提交给MyShiroRealm进行登录认证
        getSubject(request, response).login(jwtToken);
        return true;
    }
}
