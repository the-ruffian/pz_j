package com.example.demo.comtroller;

import com.example.demo.model.dto.article.ColumnAddDto;
import com.example.demo.model.dto.article.ColumnListDto;
import com.example.demo.service.Column.ColumnService;
import com.example.demo.utils.model.OpenResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "栏目")
public class ColumnMenuController {
    @Autowired
    ColumnService columnService;

    /**
     * @Description 新增栏目
     * @param columnAddDto
     * @return
     */
    @ApiOperation(value = "新增栏目")
    @PostMapping(value = "/api/columnMenu/add",produces = "application/json;charset=UTF-8")
    public OpenResponse add(@RequestBody ColumnAddDto columnAddDto) {
        return columnService.addColumn(columnAddDto);
    }

    @ApiOperation(value = "查询栏目")
    @PostMapping(value = "/api/columnMenu/list",produces = "application/json;charset=UTF-8")
    public OpenResponse search(@RequestBody ColumnListDto columnListDto) {
        return columnService.searchColumn(columnListDto);
    }
}
