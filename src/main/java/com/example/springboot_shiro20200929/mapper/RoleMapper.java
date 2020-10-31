package com.example.springboot_shiro20200929.mapper;

import com.example.springboot_shiro20200929.bean.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author 王飞
 * @since 2020-10-22
 */
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select * from role where role_id in (select role_id  from admin_role where admin_id=#{adminId})")
    List<Role> queryByAdminIdListRole(@Param("adminId") Long adminId);

}
