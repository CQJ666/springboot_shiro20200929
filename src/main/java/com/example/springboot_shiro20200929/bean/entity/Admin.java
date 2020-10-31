package com.example.springboot_shiro20200929.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 后台管理用户表
 * </p>
 *
 * @author 王飞
 * @since 2020-10-22
 */
public class Admin extends Model<Admin> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "admin_id", type = IdType.AUTO)
    private Long adminId;

    private String adminName;

    private String password;

    /**
     * 0正常 1删除
     */
    private Integer status;

    /**
     * 盐值
     */
    private String salt;

    private LocalDateTime ctime;

    private LocalDateTime utime;


    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public LocalDateTime getCtime() {
        return ctime;
    }

    public void setCtime(LocalDateTime ctime) {
        this.ctime = ctime;
    }

    public LocalDateTime getUtime() {
        return utime;
    }

    public void setUtime(LocalDateTime utime) {
        this.utime = utime;
    }

    @Override
    protected Serializable pkVal() {
        return this.adminId;
    }

    @Override
    public String toString() {
        return "Admin{" +
        "adminId=" + adminId +
        ", adminName=" + adminName +
        ", password=" + password +
        ", status=" + status +
        ", salt=" + salt +
        ", ctime=" + ctime +
        ", utime=" + utime +
        "}";
    }
}
