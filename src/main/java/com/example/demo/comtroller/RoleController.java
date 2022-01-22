/*
 * @Description:角色模块
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-06-12 20:34:32
 * @LastEditTime: 2021-8-23 22:22:49
 * @LastEditors: the-ruffian
 */
package com.example.demo.comtroller;



import com.example.demo.mapper.RoleDao;
import com.example.demo.mapper.RolePermissionDao;
import com.example.demo.mapper.UserRoleDao;
import com.example.demo.model.dto.*;
import com.example.demo.service.RoleService;
import com.example.demo.utils.model.OpenResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@Api(tags = "Role",description = "角色")
public class RoleController {
    @Autowired
    RoleDao roleDao;
    @Autowired
    UserRoleDao userRoleDao;
    @Autowired
    RolePermissionDao rolePermissionDao;
    @Autowired
    RoleService roleService;


    @ApiOperation(value = "角色列表")
    @PostMapping(value = "/api/role/list", produces = "application/json; charset=UTF-8")
    public Object roleList(@RequestBody RoleListDto roleListDto) {
        return roleService.search(roleListDto);
    }

    @ApiOperation(value = "新增角色")
    @PostMapping(value = "/api/role/add", produces = "application/json; charset=UTF-8")
    public OpenResponse add(@RequestBody RoleAddDto roleAddDto){
        return roleService.add(roleAddDto);
    }

    @ApiOperation(value = "修改角色")
    @PutMapping(value = "/api/role/update", produces = "application/json; charset=UTF-8")
    public Object updateRole(@RequestBody RoleUpdateDto roleUpdateDto){
       return roleService.update(roleUpdateDto);
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping(value = "/api/role/delete",produces = "application/json; charset=UTF-8")
    public Object deleteRole(@RequestBody RoleDeleteDto roleDeleteDto) {
        return roleService.delete(roleDeleteDto);
    }

    @ApiModelProperty(value = "菜单资源")
    @PostMapping(value = "/api/role/permission",produces = "application/json; charset=UTF-8")
    public Object permission (){
        return roleService.permission();
    }

    @ApiModelProperty(value = "修改角色状态")
    @PostMapping(value = "/api/role/fixStatus",produces = "application/json; charset=UTF-8")
    public Object fixStatus(@RequestBody RoleStatusDto roleStatusDto) {
        return roleService.fixStatus(roleStatusDto);
    }
}
