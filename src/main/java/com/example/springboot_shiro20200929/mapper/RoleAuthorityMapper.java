package com.example.springboot_shiro20200929.mapper;

import com.example.springboot_shiro20200929.bean.Authority;
import com.example.springboot_shiro20200929.bean.RoleAuthority;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 王飞
 * @since 2020-09-30
 */
public interface RoleAuthorityMapper extends BaseMapper<RoleAuthority> {

    @Select("select distinct resource_name from authority where authority_id IN (select authority_id from role_authority where role_id in(select role_id from user_role where user_id=#{userId}))")
    List<String> queryByUserIdListAuthority(Integer userId);

}
