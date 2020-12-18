//package com.example.springboot_shiro20200929.shiro2;
//
//import com.lzqs.yuanzilian.newadmin2.bean.entity.AdminUser;
//import com.lzqs.yuanzilian.newadmin2.bean.vo.AuthTokenVo;
//import com.lzqs.yuanzilian.newadmin2.mapper.AdminRoleMapper;
//import com.lzqs.yuanzilian.newadmin2.mapper.AdminUserMapper;
//import com.lzqs.yuanzilian.newadmin2.mapper.PermMapper;
//import com.lzqs.yuanzilian.newadmin2.mapper.RolePermMapper;
//import com.lzqs.yuanzilian.newadmin2.utils.jwt.JwtUtil;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.apache.shiro.util.ByteSource;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.util.ObjectUtils;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//public class MyShiroRealm extends AuthorizingRealm {
//    private static Logger logger= LoggerFactory.getLogger(AuthorizingRealm.class);
//    @Resource
//    AdminUserMapper adminUserMapper;
//    @Resource
//    AdminRoleMapper adminRoleMapper;
//    @Resource
//    RolePermMapper rolePermMapper;
//    @Resource
//    PermMapper permMapper;
//    /**
//     * 重写，绕过身份令牌异常导致的shiro报错
//     * @param authenticationToken
//     * @return
//     */
//    @Override
//    public boolean supports(AuthenticationToken authenticationToken){
//        return authenticationToken instanceof AuthTokenVo;
//    }
//
//
//
//
//    /*
//    * @Description: 这个方法 主要用于授权
//    * @Param: [principalCollection]
//    * @return: org.apache.shiro.authz.AuthorizationInfo
//    * @Author: 王飞
//    * @Date: 2020/9/29 15:29
//    **/
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        logger.info("执行doGetAuthorizationInfo方法------授权");
//        //1  获取主体信息  2根据用户信息去查角色
//        //获取用户登录信息
//        AdminUser user = (AdminUser)principalCollection.getPrimaryPrincipal();
//        List<String>perms= permMapper.queryByUserIdListperm(user.getUserId());
////        List<Role> listRole=adminRoleMapper.queryByUserIdListRole(user.getUserId());
////        List<String> perms=new ArrayList<>();
////        for (Role role:listRole) {
////            List<Perm> listPerm= permMapper.getPermListByRoleId(role.getRoleId());
////            for (Perm perm:listPerm){
////                if (perm.getListPerm()!=null){
////                    for (Perm perm2:perm.getListPerm() ) {
////                        perms.add(perm2.getResource());
////                    }
////                }
////                perms.add(perm.getResource());
////            }
////        }
//
//        //添加角色和权限并与controller标注进行比对
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        authorizationInfo.addStringPermissions(perms);//权限
//        return authorizationInfo;
//
//    }
//
//
//    /*
//    * @Description: 这个方法  主要用于认证
//    * @Param: [authenticationToken]
//    * @return: org.apache.shiro.authc.AuthenticationInfo
//    * @Author: 王飞
//    * @Date: 2020/9/29 15:29
//    **/
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        logger.info("执行doGetAuthenticationInfo方法------认证");
//        //authenticationToken  主体传过来的信息
//        //获得token
//        String token = String.valueOf(authenticationToken.getCredentials());
//
//        Long userId = JwtUtil.getUserId(token);
//
//        AdminUser adminUser=adminUserMapper.selectById(userId);
//        if (ObjectUtils.isEmpty(adminUser))throw new AuthenticationException("没有找到此用户");
//        if (JwtUtil.isExpiration(token)){
//            throw new AuthenticationException("token过期");
//        }
//    ByteSource credentialsSalt = ByteSource.Util.bytes(adminUser.getSalt());
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//                adminUser,
//                adminUser.getPassword(),
//                credentialsSalt,
//                getName());
//        System.out.println(authenticationInfo);
//        return authenticationInfo;
//        //拿到信息去数据库 获取凭证
////        User user=getUserBypasswordoruserMobile(userMobile,password);
//
//    }
//
//
//    /*
//    * @Description: 去数据库中获取信息
//    * @Param: [userMobile, password]
//    * @return: com.example.springboot_shiro20200929.bean.User
//    * @Author: 王飞
//    * @Date: 2020/9/29 15:41
//    **/
////    private User getUserBypasswordoruserMobile(String userMobile,String password){
////        User user=new User();
////        return user;
////
////    }
//
//
//    @Override
//    public  boolean isPermitted(PrincipalCollection principals, String permission){
//        AdminUser user = (AdminUser)principals.getPrimaryPrincipal();
//        return user.getUserName().equals("admin")||super.isPermitted(principals,permission);
//    }
//    @Override
//    public boolean hasRole(PrincipalCollection principals, String roleIdentifier) {
//        AdminUser user = (AdminUser)principals.getPrimaryPrincipal();
//        return user.getUserName().equals("admin")||super.hasRole(principals,roleIdentifier);
//    }
//
////    protected UserService userService;
//
////    public JWTShiroRealm(UserService userService){
////        this.userService = userService;
////        //这里使用我们自定义的Matcher
////        this.setCredentialsMatcher(new JWTCredentialsMatcher());
////    }
//    /**
//     * 限定这个Realm只支持我们自定义的JWT Token
//     */
////    @Override
////    public boolean supports(AuthenticationToken token) {
////        return token instanceof AuthTokenVo;
////    }
//
//    /**
//     * 更controller登录一样，也是获取用户的salt值，给到shiro，由shiro来调用matcher来做认证
//     */
////    @Override
////    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
////        AuthTokenVo jwtToken = (AuthTokenVo) authcToken;
////        String token = jwtToken.getToken();
////
////        UserDto user = userService.getJwtTokenInfo(JwtUtils.getUsername(token));
////        if(user == null)
////            throw new AuthenticationException("token过期，请重新登录");
////
////        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getSalt(), "jwtRealm");
////
////        return authenticationInfo;
////    }
//
//
//
//}