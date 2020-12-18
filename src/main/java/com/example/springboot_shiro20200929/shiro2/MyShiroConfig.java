package com.example.springboot_shiro20200929.shiro2;//package com.lzqs.yuanzilian.newadmin2.admin.shiro2;
//
//import com.lzqs.yuanzilian.newadmin2.admin.filter.MyFilter;
//import com.lzqs.yuanzilian.newadmin2.admin.shiro.MyShiroRealm;
//import org.apache.shiro.spring.LifecycleBeanPostProcessor;
//import org.apache.shiro.spring.config.ShiroConfiguration;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.servlet.Filter;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
///**
// * @ClassName MyShiro
// * @Description
// * @Date 2020/9/29 9:58
// * @Version 1.0
// **/
//@Configuration
//public class MyShiroConfig {
//
//    private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ShiroConfiguration.class);
//
//    //从配置文件里面读取是否需要启动登录认证的开关，默认true
//    //@Value("${jwt.auth}")
//    private boolean auth = true;
//
//    //配置拦截器
//    @Bean
//    public ShiroFilterFactoryBean shiroFilter(org.apache.shiro.mgt.SecurityManager securityManager) {
//
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        //设置securityManager
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        //启用认证
//        String openAuth = auth ? "auth" : "anon";
//
//        //自定义过滤器链
//        Map<String, Filter> filters = new HashMap<>();
//        //指定拦截器处理
//        filters.put("auth", new MyFilter());
//        shiroFilterFactoryBean.setFilters(filters);
//        Map<String, String> filterMap = new LinkedHashMap<>();
//
//        //登录请求不拦截
//        filterMap.put("/adminUser/login", "anon");
//        //登录页面需要用到的接口，不拦截
//        filterMap.put("/adminUser/logincode", "anon");//验证码登录
//        filterMap.put("/adminUser/sendms", "anon");//发送验证码
//        filterMap.put("/adminUser/codePic", "anon");//生成图片验证码
//        //拦截所有接口请求，做权限判断
//        filterMap.put("/**", openAuth);
//
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
//        logger.info("Shiro拦截器工厂类注入成功");
//        return shiroFilterFactoryBean;
//    }
//
//    // SecurityManager 安全管理器；Shiro的核心
//    @Bean
//    public DefaultWebSecurityManager securityManager() {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setRealm(userRealm());
//        return securityManager;
//    }
//    //自定义身份认证realm
//    @Bean
//    public MyShiroRealm userRealm() {
//        return new MyShiroRealm();
//    }
//
//
//
//    @Bean("lifecycleBeanPostProcessor")
//    //管理shiro生命周期
//    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }
//
//
//
////    //Shiro注解支持    开启aop注解支持
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(org.apache.shiro.mgt.SecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager((org.apache.shiro.mgt.SecurityManager) securityManager);
//        return authorizationAttributeSourceAdvisor;
//    }
//
//
//}
