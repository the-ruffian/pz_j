/*
 * @Description:UserListVo
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-13 14:10
 * @LastEditTime: 2021-07-13 14:10
 * @LastEditors: the-ruffian
 */
package com.example.demo.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserListVo {
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "性别")
    private Integer gender;
    @ApiModelProperty(value = "状态")
    private Integer status;
    @ApiModelProperty(value = "最后登录时间")
    private Timestamp loginTime;
    @ApiModelProperty(value = "角色名")
    private String roleName;
}
