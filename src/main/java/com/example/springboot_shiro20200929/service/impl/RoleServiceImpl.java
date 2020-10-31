package com.example.springboot_shiro20200929.service.impl;

import com.example.springboot_shiro20200929.bean.entity.Role;
import com.example.springboot_shiro20200929.mapper.RoleMapper;
import com.example.springboot_shiro20200929.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author 王飞
 * @since 2020-10-22
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
