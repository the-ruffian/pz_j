package com.example.demo.model.dto.article;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ArticleBodyDto {
    @ApiModelProperty(value = "文章id")
    private Integer id;
}
