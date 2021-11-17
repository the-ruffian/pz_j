package com.example.demo.model.dto.article;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ColumnAddDto {
    @ApiModelProperty(value = "栏目名")
    private String columnName;
}
