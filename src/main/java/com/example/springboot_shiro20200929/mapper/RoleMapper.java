package com.example.springboot_shiro20200929.mapper;

import com.example.springboot_shiro20200929.bean.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author 王飞
 * @since 2020-09-30
 */

public interface RoleMapper extends BaseMapper<Role> {


    @Select("select * from role where position=${position}")
    Role queryByName(String position);
}
