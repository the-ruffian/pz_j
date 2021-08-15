/*
 * @Description:忘记密码入参
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-24 20:52:51
 * @LastEditTime: 2021-08-15 15:56:08
 * @LastEditors: the-ruffian
 */
package com.example.demo.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserResetPasswordDto {

    @ApiModelProperty(value = "验证码")
    private String code;
    @ApiModelProperty(value = "新密码")
    private String password;
    @ApiModelProperty(value = "确认密码")
    private String RePassword;
    @ApiModelProperty(value = "邮箱")
    private String email;
}
