package com.example.demo.comtroller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Role;
import com.example.demo.entity.Role_permission;
import com.example.demo.entity.User_role;
import com.example.demo.mapper.RoleDao;
import com.example.demo.mapper.RolePermissionDao;
import com.example.demo.mapper.UserRoleDao;
import com.example.demo.utils.PublicMethod;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


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
    public Object roleList(@RequestBody JSONObject jsonParam) {
        JSONObject search;
        search = jsonParam.getJSONObject("search");
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        Page<Role> rolePage = new Page<>(jsonParam.getInteger("pageNo"), jsonParam.getInteger("pageSize"));
        IPage<Role> roleIPage = roleDao.selectPage(rolePage, roleQueryWrapper
                .select("role_name","note","create_time","update_time")
                .like(null != search.get("roleName") && "" != search.get("roleName"),"role_name",search.get("roleName"))
                .like(null != search.get("note") && "" != search.get("note"),"note",search.get("note")));
        JSONObject obj = new JSONObject();
        obj.put("result",roleIPage);
        obj.put("code",200);
        return obj.toJSONString();
    }

    @ApiOperation(value = "新增角色")
    @PostMapping(value = "/api/role/add", produces = "application/json; charset=UTF-8")
    public Object addRole(@RequestBody JSONObject jsonParam){
        String roleName = jsonParam.getString("roleName");
        String note = jsonParam.getString("note");
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        JSONObject obj = new JSONObject();
        if (roleDao.selectCount(roleQueryWrapper.eq("role_name",roleName)) == 0){
            if (null != roleName && !roleName.equals("")){
                Role role = new Role();
                role.setRoleName(roleName);
                role.setNote(note);
                role.setCreateTime(PublicMethod.getNowTime());
                roleDao.insert(role);
                obj.put("msg","角色"+ roleName+"创建成功");
                obj.put("code",200);
            }
            else {
                obj.put("msg","角色名不能为空");
                obj.put("code",1002);
            }

        }
        else {
            obj.put("code",1001);
            obj.put("msg","角色名"+roleName+"已存在");
        }
        return obj.toJSONString();
    }

    @ApiOperation(value = "修改角色")
    @PutMapping(value = "/api/role/update", produces = "application/json; charset=UTF-8")
    public Object updateRole(@RequestBody JSONObject jsonParam){
        String oldName = jsonParam.getString("oldName");
        String roleName = jsonParam.getString("roleName");
        String note = jsonParam.getString("note");
        Role role = new Role();
        JSONObject obj = new JSONObject();
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        if (roleName != null && !roleName.equals("")
        && note != null && !note.equals("")){
            role.setNote(note);
            role.setRoleName(roleName);
            role.setUpdateTime(PublicMethod.getNowTime());
            roleDao.update(role,roleQueryWrapper.eq("role_name",oldName));
            obj.put("msg","修改成功");
            obj.put("code",200);
        }
        else if (roleName != null && !roleName.equals("")){
            role.setRoleName(roleName);
            role.setUpdateTime(PublicMethod.getNowTime());
            roleDao.update(role,roleQueryWrapper.eq("role_name",oldName));
            obj.put("msg","修改成功");
            obj.put("code",200);
        }
        else if (note != null && !note.equals("")){
            role.setNote(note);
            role.setUpdateTime(PublicMethod.getNowTime());
            roleDao.update(role,roleQueryWrapper.eq("role_name",oldName));
            obj.put("msg","修改成功");
            obj.put("code",200);
        }
        else {
            obj.put("msg","字段不能为空");
            obj.put("code",1002);
        }
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
        obj.put("msg", "角色"+roleName+"删除成功");
        obj.put("code",200);
        return obj.toJSONString();
    }
}
