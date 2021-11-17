package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.ColumnMenu;
import com.example.demo.model.dto.article.ColumnListDto;
import com.example.demo.model.vo.article.ColumnListVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ColumnMenuDao extends BaseMapper<ColumnMenu> {

    @Select("<script>\n" +
            "select\n" +
            "column_name\n" +
            "from column_menu\n" +
            "where 1=1\n" +
            "<if test=\"columnListDto != null and  columnListDto.columnName =! null and columnListDto.columnName != '' \"> and column_name like CONCAT('%',#{columnListDto.columnName},'%')</if> " +
            "</script>")
    List<ColumnListVo> search(@Param("columnListDto")ColumnListDto columnListDto);
}
