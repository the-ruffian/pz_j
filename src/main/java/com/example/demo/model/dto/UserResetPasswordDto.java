/*
 * @Description:忘记密码入参
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-24 20:52:51
 * @LastEditTime: 2021-09-25 22:17:55
 * @LastEditors: the-ruffian
 */
package com.example.demo.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserResetPasswordDto {

    @ApiModelProperty(value = "验证码")
    private String code;
    @ApiModelProperty(value = "新密码")
    private String password;
    @ApiModelProperty(value = "确认密码")
    private String rePassword;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "更新时间")
    private Timestamp updateTime;
}
