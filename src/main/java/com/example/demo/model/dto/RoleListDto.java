package com.example.demo.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author bugpz
 * @date 2021-09-25 23:09:36
 */
@Data
public class RoleListDto {
    @ApiModelProperty(value = "页码")
    private Integer pageNo;
    @ApiModelProperty(value = "条数")
    private Integer pageSize;
    @ApiModelProperty(value = "角色名")
    private String roleName;
    @ApiModelProperty(value = "备注")
    private String note;
}
