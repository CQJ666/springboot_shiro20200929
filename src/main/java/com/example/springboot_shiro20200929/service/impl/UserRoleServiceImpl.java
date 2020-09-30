package com.example.springboot_shiro20200929.service.impl;

import com.example.springboot_shiro20200929.bean.UserRole;
import com.example.springboot_shiro20200929.mapper.UserRoleMapper;
import com.example.springboot_shiro20200929.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关系表 服务实现类
 * </p>
 *
 * @author 王飞
 * @since 2020-09-30
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
