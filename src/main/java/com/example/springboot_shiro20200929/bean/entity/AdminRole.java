package com.example.springboot_shiro20200929.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 用户角色关系表
 * </p>
 *
 * @author 王飞
 * @since 2020-10-22
 */
public class AdminRole extends Model<AdminRole> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "admin_role_id", type = IdType.AUTO)
    private Long adminRoleId;

    private Long roleId;

    /**
     * 后台管理员表（admin_user）用户id
     */
    private Long adminId;


    public Long getAdminRoleId() {
        return adminRoleId;
    }

    public void setAdminRoleId(Long adminRoleId) {
        this.adminRoleId = adminRoleId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    @Override
    protected Serializable pkVal() {
        return this.adminRoleId;
    }

    @Override
    public String toString() {
        return "AdminRole{" +
        "adminRoleId=" + adminRoleId +
        ", roleId=" + roleId +
        ", adminId=" + adminId +
        "}";
    }
}
