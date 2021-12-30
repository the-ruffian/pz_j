package com.example.demo.model.dto.article;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class ArticleListDto {
    @ApiModelProperty(value = "页码")
    private Integer pageNo;

    @ApiModelProperty(value = "条数")
    private Integer pageSize;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "分类")
    private Integer sortId;
}
