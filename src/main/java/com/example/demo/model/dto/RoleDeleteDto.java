package com.example.demo.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author bugpz
 * @date 2021-09-25 23:09:16
 */
@Data
public class RoleDeleteDto {
    @ApiModelProperty(value = "角色名")
    private Integer roleId;
}
