package com.example.demo.model.dto.article;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ColumnListDto {
    @ApiModelProperty(value = "页码")
    private Integer pageNo;
    @ApiModelProperty(value = "栏目名称")
    private String columnName;
}
