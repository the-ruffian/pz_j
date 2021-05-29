package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
@TableName(value = "permission")
@ApiModel(value = "资源")
public class Permission {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "类型")
    @NotNull(message = "类型不能为空")
    private String type;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "菜单层级")
    @NotNull(message = "层级不能为空")
    private String level;

    @ApiModelProperty(value = "路由地址")
    @NotNull(message = "路由地址不能为空")
    private String url;

    @ApiModelProperty(value = "菜单名")
    @NotNull(message = "不能为空")
    private String permission_name;

    @ApiModelProperty(value = "顺序")
    @NotNull(message = "不能为空")
    private Integer sort_num;

    @ApiModelProperty(value = "父级id")
    private Integer parent_id;

    @ApiModelProperty(value = "权限控制")
    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission_name() {
        return permission_name;
    }

    public void setPermission_name(String permission_name) {
        this.permission_name = permission_name;
    }

    public Integer getSort_num() {
        return sort_num;
    }

    public void setSort_num(Integer sort_num) {
        this.sort_num = sort_num;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}