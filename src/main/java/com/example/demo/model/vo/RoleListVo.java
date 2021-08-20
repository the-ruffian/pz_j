/*
 * @Description:角色列表响应
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-14 13:43
 * @LastEditTime: 2021-8-20 16:06:58
 * @LastEditors: the-ruffian
 */
package com.example.demo.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class RoleListVo {
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    @ApiModelProperty(value = "备注")
    private String note;
    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;
    @ApiModelProperty(value = "修改时间")
    private Timestamp updateTime;
    @ApiModelProperty(value = "角色状态")
    private String status;
}
