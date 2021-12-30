package com.example.demo.service.Article;

import com.example.demo.model.dto.article.ArticleAddDto;
import com.example.demo.model.dto.article.ArticleListDto;
import org.apache.ibatis.annotations.Param;

public interface ArticleService {
    Object newArticle(@Param("articleAddDto")ArticleAddDto articleAddDto);
    Object searchArticle(@Param("articleListDto")ArticleListDto articleListDto);
}
