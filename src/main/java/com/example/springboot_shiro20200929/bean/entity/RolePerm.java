package com.example.springboot_shiro20200929.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 角色权限表
 * </p>
 *
 * @author 王飞
 * @since 2020-10-22
 */
public class RolePerm extends Model<RolePerm> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "role_perm_id", type = IdType.AUTO)
    private Long rolePermId;

    private Long roleId;

    private Long permId;


    public Long getRolePermId() {
        return rolePermId;
    }

    public void setRolePermId(Long rolePermId) {
        this.rolePermId = rolePermId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermId() {
        return permId;
    }

    public void setPermId(Long permId) {
        this.permId = permId;
    }

    @Override
    protected Serializable pkVal() {
        return this.rolePermId;
    }

    @Override
    public String toString() {
        return "RolePerm{" +
        "rolePermId=" + rolePermId +
        ", roleId=" + roleId +
        ", permId=" + permId +
        "}";
    }
}
