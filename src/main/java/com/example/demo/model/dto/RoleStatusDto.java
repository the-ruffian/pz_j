package com.example.demo.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author bugpz
 * @date 2021-09-25 23:09:59
 */
@Data
public class RoleStatusDto {
    @ApiModelProperty(value = "角色状态")
    private Integer status;
    @ApiModelProperty(value = "角色名称")
    private String roleName;
}
