package com.example.springboot_shiro20200929.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 王飞
 * @since 2020-09-30
 */
public class RoleAuthority extends Model<RoleAuthority> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "role_authority_id", type = IdType.AUTO)
    private Integer roleAuthorityId;

    private Integer roleId;

    private Integer authorityId;


    public Integer getRoleAuthorityId() {
        return roleAuthorityId;
    }

    public void setRoleAuthorityId(Integer roleAuthorityId) {
        this.roleAuthorityId = roleAuthorityId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    @Override
    protected Serializable pkVal() {
        return this.roleAuthorityId;
    }

    @Override
    public String toString() {
        return "RoleAuthority{" +
        "roleAuthorityId=" + roleAuthorityId +
        ", roleId=" + roleId +
        ", authorityId=" + authorityId +
        "}";
    }
}
