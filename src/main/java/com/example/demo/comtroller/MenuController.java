/*
 * @Description:MenuController
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-06-02 16:22
 * @LastEditTime: 2021-06-02 16:22
 * @LastEditors: the-ruffian
 */
package com.example.demo.comtroller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Permission;
import com.example.demo.mapper.UserRoleDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "菜单权限")
public class MenuController {
    @Autowired
    UserRoleDao userRoleDao;

    @ApiOperation(value = "获取菜单权限")
    @PostMapping(value = "/api/auth/menu",produces = "application/json; charset=UTF-8")
    public Object menuList(@RequestBody JSONObject jsonParam){
        String phone = jsonParam.getString("phone");
        List<Permission> permissions = userRoleDao.selectByPhone(phone);
        JSONObject obj = new JSONObject();
        obj.put("code",200);
        obj.put("result",permissions);
        return obj.toJSONString();
    }
}
