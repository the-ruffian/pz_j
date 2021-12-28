package com.example.demo.service.Article;

import com.example.demo.model.dto.article.ArticleAddDto;
import org.apache.ibatis.annotations.Param;

public interface ArticleService {
    Object newArticle(@Param("articleAddDto")ArticleAddDto articleAddDto);
}
