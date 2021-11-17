package com.example.demo.service.Column;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.ColumnMenu;
import com.example.demo.enums.Status;
import com.example.demo.mapper.ColumnMenuDao;
import com.example.demo.model.dto.article.ColumnAddDto;
import com.example.demo.model.dto.article.ColumnListDto;
import com.example.demo.model.vo.article.ColumnListVo;
import com.example.demo.utils.model.OpenResponse;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ColumnService")
public class ColumnServiceImpl implements ColumnService {
    @Autowired
    ColumnMenuDao columnMenuDao;

    @Override
    public OpenResponse addColumn(@Param("columnAddDto") ColumnAddDto columnAddDto) {
        QueryWrapper<ColumnMenu> columnMenuQueryWrapper = new QueryWrapper<>();
        Integer col = columnMenuDao.selectCount(columnMenuQueryWrapper
                .eq("column_name",columnAddDto.getColumnName()));
        if (col ==1){
            return OpenResponse.fail("栏目名已存在");
        }else {
            ColumnMenu columnMenu = new ColumnMenu();
            columnMenu.setColumnName(columnAddDto.getColumnName());
            columnMenuDao.insert(columnMenu);
            return OpenResponse.ok("新增栏目<"+columnAddDto.getColumnName()+">成功");
        }
    };

    @Override
    public OpenResponse searchColumn(ColumnListDto columnListDto){
        PageMethod.startPage(columnListDto.getPageNo(),20);
        List<ColumnListVo> columnListVos = columnMenuDao.search(columnListDto);
        return OpenResponse.ok(Status.SUCCESS.getMessage(), new PageInfo<>(columnListVos));
    };
}
