package com.example.springboot_shiro20200929.service.impl;

import com.example.springboot_shiro20200929.bean.Authority;
import com.example.springboot_shiro20200929.mapper.AuthorityMapper;
import com.example.springboot_shiro20200929.service.AuthorityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author 王飞
 * @since 2020-09-30
 */
@Service
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, Authority> implements AuthorityService {

}
