package com.example.springboot_shiro20200929.mapper;

import com.example.springboot_shiro20200929.bean.entity.Perm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author 王飞
 * @since 2020-10-22
 */
public interface PermMapper extends BaseMapper<Perm> {


    @Select("select distinct resource from perm where perm_id IN (select perm_id from role_perm where role_id in(select role_id from admin_role where admin_id=#{adminId}))")
    List<String> queryByUserIdListperm(@Param("adminId") Long adminId);

    List<Perm> getPermList();

    @Select("select * from perm where perm_id in  (select perm_id from role_perm where role_id=#{roleId})")
    List<Perm> queryByRoleIdListperm(@Param("roleId") Long roleId);

}
