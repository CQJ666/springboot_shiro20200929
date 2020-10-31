package com.example.springboot_shiro20200929.mapper;

import com.example.springboot_shiro20200929.bean.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 后台管理用户表 Mapper 接口
 * </p>
 *
 * @author 王飞
 * @since 2020-10-22
 */
public interface AdminMapper extends BaseMapper<Admin> {

    @Select("select * from admin where admin_name=#{adminName} and status=0")
    Admin queryByUserName (@Param("adminName") String adminName);



}
