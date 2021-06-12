package com.example.demo.comtroller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Role;
import com.example.demo.mapper.RoleDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Api(tags = "角色")
public class RoleController {
    @Autowired
    RoleDao roleDao;
    @ApiOperation(value = "获取所有角色")
    @PostMapping(value = "/api/role/list", produces = "application/json; charset=UTF-8")
    public Object roleList() {
        JSONObject obj = new JSONObject();
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        List<Role> roleList = roleDao.selectList(roleQueryWrapper.select("role_name"));
        obj.put("result",roleList);
        obj.put("code",200);
        return obj.toJSONString();
    }
}
