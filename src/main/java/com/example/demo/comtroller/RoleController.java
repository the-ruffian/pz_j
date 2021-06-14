package com.example.demo.comtroller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Role;
import com.example.demo.entity.Role_permission;
import com.example.demo.entity.User_role;
import com.example.demo.mapper.RoleDao;
import com.example.demo.mapper.RolePermissionDao;
import com.example.demo.mapper.UserRoleDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@Api(tags = "角色")
public class RoleController {
    @Autowired
    RoleDao roleDao;
    @Autowired
    UserRoleDao userRoleDao;
    @Autowired
    RolePermissionDao rolePermissionDao;
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

    @ApiOperation(value = "新增角色")
    @PostMapping(value = "/api/role/add", produces = "application/json; charset=UTF-8")
    public Object addRole(@RequestBody JSONObject jsonParam){
        String roleName = jsonParam.getString("roleName");
        String note = jsonParam.getString("note");
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //df.format(day)
        JSONObject obj = new JSONObject();
        if (roleDao.selectCount(roleQueryWrapper.eq("role_name",roleName)) == 0){
            Role role = new Role();
            role.setRoleName(roleName);
            role.setNote(note);
            role.setCreateTime(Timestamp.valueOf(df.format(date)));
            roleDao.insert(role);
            obj.put("result","角色"+ roleName+"创建成功");
        }
        else {
            obj.put("result","角色名"+roleName+"已存在");
        }
        return obj.toJSONString();
    }

    @ApiOperation(value = "修改角色")
    @PutMapping(value = "/api/role/update", produces = "application/json; charset=UTF-8")
    public Object updateRole(@RequestBody JSONObject jsonParam){
        String oldName = jsonParam.getString("oldName");
        String roleName = jsonParam.getString("roleName");
        String note = jsonParam.getString("note");
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Role role = new Role();
        JSONObject obj = new JSONObject();
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        if (roleName != null && roleName.length() >0
        && note != null && note.length() > 0){
            role.setNote(note);
            role.setRoleName(roleName);
            role.setUpdateTime(Timestamp.valueOf(df.format(date)));
            roleDao.update(role,roleQueryWrapper.eq("role_name",oldName));
            obj.put("result","修改成功");
        }
        else if (roleName != null && roleName.length() >0){
            role.setRoleName(roleName);
            role.setUpdateTime(Timestamp.valueOf(df.format(date)));
            roleDao.update(role,roleQueryWrapper.eq("role_name",oldName));
            obj.put("result","修改成功");
        }
        else if (note != null && note.length() > 0){
            role.setNote(note);
            role.setUpdateTime(Timestamp.valueOf(df.format(date)));
            roleDao.update(role,roleQueryWrapper.eq("role_name",oldName));
            obj.put("result","修改成功");
        }
        else {
            obj.put("result","未作任何修改或字段为空");
        }
        obj.put("code",200);
        return obj.toJSONString();
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping(value = "/api/role/delete/{roleName}")
    public Object deleteRole(@PathVariable("roleName") String roleName){
        JSONObject obj = new JSONObject();
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        Integer roleID = roleDao.selectByRoleNameInteger(roleName);
        QueryWrapper<User_role> user_roleQueryWrapper = new QueryWrapper<>();
        QueryWrapper<Role_permission> role_permissionQueryWrapper = new QueryWrapper<>();
        Integer userRole = userRoleDao.selectCount(user_roleQueryWrapper.eq("role_id",roleID));
        Integer rolePermission = rolePermissionDao.selectCount(role_permissionQueryWrapper.eq("role_id",roleID));
        if (userRole > 0){
            userRoleDao.delete(user_roleQueryWrapper.eq("role_id",roleID));
        }
        if (rolePermission >0){
            rolePermissionDao.delete(role_permissionQueryWrapper.eq("role_id",roleID));
        }
        roleDao.delete(roleQueryWrapper.eq("role_name", roleName));
        obj.put("result", "角色"+roleName+"删除成功");
        obj.put("code",200);
        return obj.toJSONString();
    }
}
