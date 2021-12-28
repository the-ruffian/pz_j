package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 文章表映射
 */
@Data
@TableName(value = "article")
@ApiModel(value = "文章表")
public class Article {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    @ApiModelProperty(value = "修改时间")
    private Timestamp updateTime;

    @ApiModelProperty(value = "内容")
    private String article;

    @ApiModelProperty(value = "点击数")
    private Integer hits;

    @ApiModelProperty(value = "评论数")
    private Integer postNum;
}
