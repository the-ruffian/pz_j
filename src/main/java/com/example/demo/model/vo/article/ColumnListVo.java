package com.example.demo.model.vo.article;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ColumnListVo {
    @ApiModelProperty(value = "栏目名")
    private String columnName;
}
