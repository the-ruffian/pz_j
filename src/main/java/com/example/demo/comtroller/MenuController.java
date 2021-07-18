/*
 * @Description:菜单
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-06-02 16:22
 * @LastEditTime: 2021-07-18 18:28:40
 * @LastEditors: the-ruffian
 */
package com.example.demo.comtroller;

import com.example.demo.mapper.UserRoleDao;
import com.example.demo.model.dto.UserMenuListDto;
import com.example.demo.service.MenuService;
import com.example.demo.utils.model.OpenResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




@RestController
@Api(tags = "菜单权限")
public class MenuController {
    @Autowired
    UserRoleDao userRoleDao;
    @Autowired
    MenuService menuService;

    @ApiOperation(value = "获取菜单权限")
    @PostMapping(value = "/api/auth/list",produces = "application/json;charset=UTF-8")
    public OpenResponse menu(@RequestBody UserMenuListDto userMenuListDto){
        return menuService.userMenu(userMenuListDto);
    }
}
