package com.example.demo.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Article;
import com.example.demo.model.dto.article.ArticleListDto;
import com.example.demo.model.vo.article.ArticleListVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface ArticleDao extends BaseMapper<Article> {
    @Select("<script>\n" +
            "select\n" +
            "a.title,a.author,a.create_time,a.update_time,a.article,a.hits,a.post_num,c.column_name\n" +
            "from article a,column_menu c where 1=1 and a.sort_id=c.id\n" +
            "<if test = \"articleListDto != null and articleListDto.title != null and articleListDto.title != ''\"> and a.title like CONCAT('%',#{articleListDto.title},'%')</if>\n" +
            "<if test = \"articleListDto != null and articleListDto.author != null and articleListDto.author != ''\"> and a.author like CONCAT('%',#{articleListDto.author},'%')</if>\n" +
            "<if test = \"articleListDto != null and articleListDto.sortId != null and articleListDto.sortId != ''\"> and c.id = #{articleListDto.sortId}</if>\n" +
            "</script>")
    List<ArticleListVo> search(@Param("articleListDto")ArticleListDto articleListDto);
}
