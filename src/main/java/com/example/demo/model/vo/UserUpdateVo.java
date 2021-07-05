package com.example.demo.model.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserUpdateVo {
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "邮箱")
    private String email;
}
