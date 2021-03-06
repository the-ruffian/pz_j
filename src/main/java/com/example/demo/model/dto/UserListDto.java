package com.example.demo.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author bugpz
 * @date 2021-09-25 23:11:58
 */
@Data
public class UserListDto {
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "性别")
    private Integer gender;
    @ApiModelProperty(value = "当前页")
    private Integer pageNo;
    @ApiModelProperty(value = "一页多少行")
    private Integer pageSize;
}
