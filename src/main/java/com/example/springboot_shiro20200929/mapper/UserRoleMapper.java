package com.example.springboot_shiro20200929.mapper;

import com.example.springboot_shiro20200929.bean.Role;
import com.example.springboot_shiro20200929.bean.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户角色关系表 Mapper 接口
 * </p>
 *
 * @author 王飞
 * @since 2020-09-30
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {


    @Select("select * from role where role_id in (select role_id  from user_role where user_id=#{userId})")
    List<Role> queryByUserIdListRole(Integer userId);





}
