package com.example.springboot_shiro20200929.service.impl;

import com.example.springboot_shiro20200929.bean.User;
import com.example.springboot_shiro20200929.mapper.UserMapper;
import com.example.springboot_shiro20200929.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王飞
 * @since 2020-09-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
