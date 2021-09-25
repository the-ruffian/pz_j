package com.example.demo.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author bugpz
 * @date 2021-09-25 23:10:21
 */
@Data
public class RoleUpdateDto {
    @ApiModelProperty(value = "旧角色名")
    private String oldName;
    @ApiModelProperty(value = "新角色名")
    private String newName;
    @ApiModelProperty(value = "备注")
    private String note;
}
