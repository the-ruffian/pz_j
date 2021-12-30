package com.example.demo.model.vo.article;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ArticleListVo {
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "作者")
    private String author;
    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;
    @ApiModelProperty(value = "修改时间")
    private Timestamp updateTime;
    @ApiModelProperty(value = "点击数")
    private Integer hits;
    @ApiModelProperty(value = "评论数")
    private Integer postNum;

}
