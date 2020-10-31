package com.example.springboot_shiro20200929.service.impl;

import com.example.springboot_shiro20200929.bean.entity.AdminRole;
import com.example.springboot_shiro20200929.mapper.AdminRoleMapper;
import com.example.springboot_shiro20200929.service.AdminRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关系表 服务实现类
 * </p>
 *
 * @author 王飞
 * @since 2020-10-22
 */
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements AdminRoleService {

}
