package com.example.springboot_shiro20200929.service.impl;

import com.example.springboot_shiro20200929.bean.entity.Perm;
import com.example.springboot_shiro20200929.mapper.PermMapper;
import com.example.springboot_shiro20200929.service.PermService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author 王飞
 * @since 2020-10-22
 */
@Service
public class PermServiceImpl extends ServiceImpl<PermMapper, Perm> implements PermService {

}
