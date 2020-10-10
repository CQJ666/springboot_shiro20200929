package com.example.springboot_shiro20200929.mapper;

import com.example.springboot_shiro20200929.bean.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 王飞
 * @since 2020-09-30
 */
public interface UserMapper extends BaseMapper<User> {


    @Select("select * from user where user_mobile=#{userMobile}")
    User queryByUserMobile (String userMobile);
}
