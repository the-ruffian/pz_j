/*
 * @Description:忘记密码入参
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-24 20:52:51
 * @LastEditTime: 2021-07-24 20:55:11
 * @LastEditors: the-ruffian
 */
package com.example.demo.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserResetPasswordDto {

    @ApiModelProperty(value = "新密码")
    private String password;
    @ApiModelProperty(value = "确认密码")
    private String RePassword;
    @ApiModelProperty(value = "手机号")
    private String phone;
}
