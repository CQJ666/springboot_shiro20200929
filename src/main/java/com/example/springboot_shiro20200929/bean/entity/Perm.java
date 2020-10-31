package com.example.springboot_shiro20200929.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author 王飞
 * @since 2020-10-22
 */
public class Perm extends Model<Perm> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "perm_id", type = IdType.AUTO)
    private Long permId;

    /**
     * 权限名
     */
    private String permName;

    /**
     * 前端url
     */
    private String permValue;

    /**
     * 权限描述
     */
    private String permDesc;

    /**
     * 资源路径
     */
    private String resource;

    /**
     * 接口url
     */
    private String interfaceUrl;

    /**
     * 父模块id
     */
    private Long parentId;


    public Long getPermId() {
        return permId;
    }

    public void setPermId(Long permId) {
        this.permId = permId;
    }

    public String getPermName() {
        return permName;
    }

    public void setPermName(String permName) {
        this.permName = permName;
    }

    public String getPermValue() {
        return permValue;
    }

    public void setPermValue(String permValue) {
        this.permValue = permValue;
    }

    public String getPermDesc() {
        return permDesc;
    }

    public void setPermDesc(String permDesc) {
        this.permDesc = permDesc;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getInterfaceUrl() {
        return interfaceUrl;
    }

    public void setInterfaceUrl(String interfaceUrl) {
        this.interfaceUrl = interfaceUrl;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    protected Serializable pkVal() {
        return this.permId;
    }

    @Override
    public String toString() {
        return "Perm{" +
        "permId=" + permId +
        ", permName=" + permName +
        ", permValue=" + permValue +
        ", permDesc=" + permDesc +
        ", resource=" + resource +
        ", interfaceUrl=" + interfaceUrl +
        ", parentId=" + parentId +
        "}";
    }
}
