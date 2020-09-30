package com.example.springboot_shiro20200929.bean;

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
 * @since 2020-09-30
 */
public class Authority extends Model<Authority> {

    private static final long serialVersionUID = 1L;

    /**
     * 权限表主键
     */
    @TableId(value = "authority_id", type = IdType.AUTO)
    private Integer authorityId;

    /**
     * 权限名称
     */
    private String authorityName;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 资源路径
     */
    private String resourceUrl;


    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    @Override
    protected Serializable pkVal() {
        return this.authorityId;
    }

    @Override
    public String toString() {
        return "Authority{" +
        "authorityId=" + authorityId +
        ", authorityName=" + authorityName +
        ", resourceName=" + resourceName +
        ", resourceUrl=" + resourceUrl +
        "}";
    }
}
