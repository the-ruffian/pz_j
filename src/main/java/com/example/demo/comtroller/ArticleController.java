package com.example.demo.comtroller;

import com.example.demo.model.dto.article.ArticleAddDto;
import com.example.demo.service.Article.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "文章")
public class ArticleController {
    @Autowired
    ArticleService articleService;


    @ApiOperation(value = "新增文章")
    @PostMapping(value = "/api/article/add", produces = "application/json;charset=UTF-8")
    public Object newArticle(@RequestBody ArticleAddDto articleAddDto){
        return articleService.newArticle(articleAddDto);
    }
}
