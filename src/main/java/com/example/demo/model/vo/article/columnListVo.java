package com.example.demo.model.vo.article;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class columnListVo {
    @ApiModelProperty(value = "栏目名")
    private String columnName;
}
