package com.example.demo.service.Column;

import com.example.demo.model.dto.article.ColumnAddDto;
import com.example.demo.model.dto.article.ColumnListDto;
import com.example.demo.utils.model.OpenResponse;
import org.apache.ibatis.annotations.Param;

public interface ColumnService {
    OpenResponse addColumn(@Param("columnMenuDto") ColumnAddDto columnMenuDto);
    OpenResponse searchColumn(@Param("columnListDto")ColumnListDto columnListDto);
}
