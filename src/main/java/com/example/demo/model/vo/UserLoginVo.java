/*
 * @Description:UserLoginVo
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-04 17:42
 * @LastEditTime: 2021-07-04 17:42
 * @LastEditors: the-ruffian
 */
package com.example.demo.model.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserLoginVo {
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "token")
    private String token;
}
