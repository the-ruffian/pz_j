package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


/**
 * @author bugpz
 * @date 2021-09-25 22:24:16
 */
@Data
@TableName(value = "permission")
@ApiModel(value = "资源")
public class Permission {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "类型")
    @NotNull(message = "类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "菜单层级")
    @NotNull(message = "层级不能为空")
    private Integer level;

    @ApiModelProperty(value = "路由地址")
    @NotNull(message = "路由地址不能为空")
    private String url;

    @ApiModelProperty(value = "菜单名")
    @NotNull(message = "不能为空")
    private String permissionName;

    @ApiModelProperty(value = "顺序")
    @NotNull(message = "不能为空")
    private Integer sortNum;

    @ApiModelProperty(value = "父级id")
    private Integer parentId;

    @ApiModelProperty(value = "权限控制")
    private String code;

    @ApiModelProperty(value = "remark")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    @ApiModelProperty(value = "修改时间")
    private Timestamp updateTime;

}
