package com.example.demo.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserLogoutDto {
    @ApiModelProperty(value = "手机号")
    private String phone;
}
