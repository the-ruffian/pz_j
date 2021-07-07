/*
 * @Description:UserDeleteDto
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-07 20:58
 * @LastEditTime: 2021-07-07 20:58
 * @LastEditors: the-ruffian
 */
package com.example.demo.model.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserDeleteDto {
    @ApiModelProperty(value = "手机号")
    private String phone;
}
