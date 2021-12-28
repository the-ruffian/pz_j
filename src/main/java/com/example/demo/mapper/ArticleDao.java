package com.example.demo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Article;
import org.springframework.stereotype.Component;


@Component
public interface ArticleDao extends BaseMapper<Article> {
}
