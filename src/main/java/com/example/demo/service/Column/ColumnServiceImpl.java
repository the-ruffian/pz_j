package com.example.demo.service.Column;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.ColumnMenu;
import com.example.demo.mapper.ColumnMenuDao;
import com.example.demo.model.dto.article.ColumnAddDto;
import com.example.demo.utils.model.OpenResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ColumnService")
public class ColumnServiceImpl implements ColumnService {
    @Autowired
    ColumnMenuDao columnMenuDao;

    @Override
    public OpenResponse addColumn(@Param("columnMenuDto") ColumnAddDto columnMenuDto) {
        QueryWrapper<ColumnMenu> columnMenuQueryWrapper = new QueryWrapper<>();
        Integer col = columnMenuDao.selectCount(columnMenuQueryWrapper
                .eq("column_name",columnMenuDto.getColumnName()));
        if (col ==1){
            return OpenResponse.fail("栏目名已存在");
        }else {
            ColumnMenu columnMenu = new ColumnMenu();
            columnMenu.setColumnName(columnMenuDto.getColumnName());
            columnMenuDao.insert(columnMenu);
            return OpenResponse.ok("新增栏目<"+columnMenuDto.getColumnName()+">成功");
        }
    };
}
