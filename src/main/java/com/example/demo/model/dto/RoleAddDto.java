package com.example.demo.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author bugpz
 * @date 2021-09-25 23:08:26
 */
@Data
public class RoleAddDto {
    @ApiModelProperty(value = "角色名")
    private String roleName;
    @ApiModelProperty(value = "备注")
    private String note;
}
