package com.example.demo.service.Article;

import com.example.demo.entity.Article;
import com.example.demo.mapper.ArticleDao;
import com.example.demo.model.dto.article.ArticleAddDto;
import com.example.demo.utils.PublicMethod;
import com.example.demo.utils.model.OpenResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author TR
 */
@Service("Article")
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    ArticleDao articleDao;

    /**
     *
     * @param articleAddDto: title,author,article,sortId
     * @return message
     */

    @Override
    public Object newArticle(@Param("articleAddDto") ArticleAddDto articleAddDto){

        if (articleAddDto == null){
            return OpenResponse.fail("参数不能为空");
        }else if (articleAddDto.getTitle() ==null || "".equals(articleAddDto.getTitle())){
            return OpenResponse.fail("标题不能为空");
        }else if (articleAddDto.getAuthor()==null || "".equals(articleAddDto.getAuthor())){
            return OpenResponse.fail("作者不能为空");
        }else if (articleAddDto.getArticle() ==null || "".equals(articleAddDto.getArticle())){
            return OpenResponse.fail("内容不能为空");
        }else if (articleAddDto.getSortId()==null){
            return OpenResponse.fail("栏目id不能为空");
        }else {
            Article article = new Article();
            article.setTitle(articleAddDto.getTitle());
            article.setAuthor(articleAddDto.getAuthor());
            article.setArticle(articleAddDto.getArticle());
            article.setCreateTime(PublicMethod.getNowTime());
            article.setUpdateTime(PublicMethod.getNowTime());
            article.setSortId(articleAddDto.getSortId());
            articleDao.insert(article);
            return OpenResponse.ok("新增文章<"+articleAddDto.getTitle()+">成功");
        }
    }
}
