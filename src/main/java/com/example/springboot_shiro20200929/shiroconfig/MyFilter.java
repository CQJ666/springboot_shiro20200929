package com.example.springboot_shiro20200929.shiroconfig;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

public class MyFilter extends BasicHttpAuthenticationFilter {
    private static Logger logger= LoggerFactory.getLogger(MyFilter.class);
//    private ReturnMessage responseResult;
//    @Override
//    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
//        logger.info("执行createToken方法------");
//        return null;
//    }
//
//    /**
//     * 解决权限不足302问题
//     */
//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        //onAccessDenied：是否是拒绝登录
//        //没有登录的情况下会走此方法
//        logger.info("执行onAccessDenied方法------");
//        Subject subject = getSubject(request, response);
//        if (subject.getPrincipal() == null) {
//            saveRequestAndRedirectToLogin(request, response);
//        } else {
//            WebUtils.toHttp(response).setContentType("application/json; charset=utf-8");
//        }
//        return false;
//    }
//    @Override
//    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
//        //isAccessAllowed：判断是否登录
//        //在登录的情况下会走此方法，此方法返回true直接访问控制器
//        logger.info("执行isAccessAllowed方法------");
//        String token= JWTUtil.getRequestToken((HttpServletRequest) request);
//        if (!StringUtils.isEmpty(token)){
//            try {
//                this.executeLogin(request, response);
//            } catch (Exception e) {
//                // 应用异常
//                logger.info(e.getMessage());
//                return false;
//            }
//        } else {
//            // cookie中未检查到token或token为空
//            responseResult=  ReturnMessage.failWithMsg("token为空");
//            return false;
//        }
//        return true;
//    }
//
//
//    /**
//     * 用户存在，执行登录认证
//     * @param request
//     * @param response
//     * @return
//     * @throws Exception
//     */
//    @Override
//    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
//        String token = JWTUtil.getRequestToken((HttpServletRequest)request);
//        AuthTokenVo jwtToken = new AuthTokenVo(token);
//        // 提交给MyShiroRealm进行登录认证
//        getSubject(request, response).login(jwtToken);
//        return true;
//    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

//            try {
        try {
            executeLogin(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
//            } catch (Exception e) {
//                if (e instanceof AuthorizationException) {
//                    throw new AuthorizationException("访问资源权限不足！");
////                } else {
////                    //token 异常 认证失败
////                    throw new AuthenticationException("token 异常 认证失败");
//                }
//            }

        return true;
    }

//    //这里通过判断请求的请求头中是否存在token，没有return false，直接进入控制器，校验成功返回token到前端，前端拿到token后，下次非登录请求就需要携带token
//    @Override
//    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
//        HttpServletRequest req = (HttpServletRequest) request;
//        //判断是否是登录请求
//        String authorization = req.getHeader("LOGIN_SIGN");
//        return authorization != null;
//    }
    //这个里边有个getSubject(request, response).login(token)方法，这个方法的执行就会到咱们自定的realm中，执行认证和授权
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        String header = req.getHeader("token");
        JWTToken token = new JWTToken(header);
        getSubject(request, response).login(token);
        return true;
    }

}
