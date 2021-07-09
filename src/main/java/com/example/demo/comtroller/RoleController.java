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
import com.example.demo.model.dto.RoleAddDto;
import com.example.demo.service.role.RoleAddService;
import com.example.demo.utils.PublicMethod;
import com.example.demo.utils.model.OpenResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@Api(tags = "角色")
public class RoleController {
    @Autowired
    RoleDao roleDao;
    @Autowired
    UserRoleDao userRoleDao;
    @Autowired
    RolePermissionDao rolePermissionDao;
    @Autowired
    RoleAddService roleAddService;


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
    public OpenResponse add(@RequestBody RoleAddDto roleAddDto){
        return roleAddService.add(roleAddDto);
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
