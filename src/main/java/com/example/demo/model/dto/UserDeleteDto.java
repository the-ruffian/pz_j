package com.example.demo.model.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author bugpz
 */
@Data
public class UserDeleteDto {
    @ApiModelProperty(value = "手机号")
    private String phone;
}
