package com.example.demo.service.Column;

import com.example.demo.model.dto.article.ColumnMenuDto;
import com.example.demo.utils.model.OpenResponse;
import org.apache.ibatis.annotations.Param;

public interface ColumnService {
    OpenResponse addColumn(@Param("columnMenuDto")ColumnMenuDto columnMenuDto);
}
