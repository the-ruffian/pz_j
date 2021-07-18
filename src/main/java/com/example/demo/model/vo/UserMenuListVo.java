/*
 * @Description:用户菜单权限出参
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-18 16:39:35
 * @LastEditTime: 2021-06-02 09:51
 * @LastEditors: the-ruffian
 */
package com.example.demo.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserMenuListVo {
    @ApiModelProperty(value = "图标")
    private String icon;
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "层级")
    private Integer level;
    @ApiModelProperty(value = "父id")
    private Integer parentId;
    @ApiModelProperty(value = "菜单名")
    private String permissionName;
    @ApiModelProperty(value = "菜单备注")
    private String remark;
    @ApiModelProperty(value = "排序")
    private String sortNum;
    @ApiModelProperty(value = "类型")
    private Integer type;
    @ApiModelProperty(value = "路由")
    private String url;
    @ApiModelProperty("子菜单")
    @TableField(exist = false)
    private List<UserMenuListVo> children;

}
