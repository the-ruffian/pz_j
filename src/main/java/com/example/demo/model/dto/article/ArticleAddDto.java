package com.example.demo.model.dto.article;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class ArticleAddDto {
    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "内容")
    private String article;

}
