package com.example.springboot_shiro20200929.bean;

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
 * @since 2020-09-30
 */
public class UserRole extends Model<UserRole> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_role_id", type = IdType.AUTO)
    private Integer userRoleId;

    private Integer roleId;

    private Integer userId;


    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    protected Serializable pkVal() {
        return this.userRoleId;
    }

    @Override
    public String toString() {
        return "UserRole{" +
        "userRoleId=" + userRoleId +
        ", roleId=" + roleId +
        ", userId=" + userId +
        "}";
    }
}
