/*
 * @Description:角色实现类
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-09 22:45:32
 * @LastEditTime: 2021-09-25 22:17:35
 * @LastEditors: the-ruffian
 */
package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Role;
import com.example.demo.entity.Role_permission;
import com.example.demo.entity.User_role;
import com.example.demo.enums.Status;
import com.example.demo.mapper.PermissionDao;
import com.example.demo.mapper.RoleDao;
import com.example.demo.mapper.RolePermissionDao;
import com.example.demo.mapper.UserRoleDao;
import com.example.demo.model.dto.*;
import com.example.demo.model.vo.AllPermissionVo;
import com.example.demo.model.vo.RoleListVo;
import com.example.demo.service.RoleService;
import com.example.demo.utils.PublicMethod;
import com.example.demo.utils.model.OpenResponse;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("RoleAddService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;
    @Autowired
    RolePermissionDao rolePermissionDao;
    @Autowired
    UserRoleDao userRoleDao;
    @Autowired
    PermissionDao permissionDao;

    /**
     *
     * @param roleAddDto
     * @return
     */
    @Override
    public OpenResponse add(RoleAddDto roleAddDto) {
        String roleName = roleAddDto.getRoleName();
        String note = roleAddDto.getNote();
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        if (roleDao.selectCount(roleQueryWrapper.eq("role_name", roleName)) == 0) {
            if (null != roleName && !"".equals(roleName)) {
                Role role = new Role();
                role.setRoleName(roleName);
                role.setNote(note);
                role.setCreateTime(PublicMethod.getNowTime());
                roleDao.insert(role);
                return OpenResponse.ok("新增角色(" + roleName + ")成功");
            } else {
                return OpenResponse.fail("角色名不能为空");
            }
        }
        return OpenResponse.fail("角色("+roleName+")已存在");
    }

    /**
     *
     * @param roleListDto
     * @return 分页查询所有角色
     */
    @Override
    public OpenResponse search(RoleListDto roleListDto){
        PageMethod.startPage(roleListDto.getPageNo(),roleListDto.getPageSize());
        List<RoleListVo> roleListVos = roleDao.search(roleListDto);
        return OpenResponse.ok(Status.SUCCESS.getMessage(),new PageInfo<>(roleListVos));
    }

    /**
     *
     * @param roleUpdateDto
     * @return 是否修改成功
     */
    @Override
    public OpenResponse update(RoleUpdateDto roleUpdateDto){
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        Integer roleCount=roleDao.selectCount(roleQueryWrapper.eq("role_name",roleUpdateDto.getOldName()));
        Role role = new Role();
        if (roleCount!=1){
            return OpenResponse.fail("没有该角色");
        }else {
            if (
                    roleUpdateDto.getNewName()!=null && !"".equals(roleUpdateDto.getNewName())
                    && roleUpdateDto.getNote()!=null && !"".equals(roleUpdateDto.getNote())
            ){
                role.setRoleName(roleUpdateDto.getNewName());
                role.setNote(roleUpdateDto.getNote());
                role.setUpdateTime(PublicMethod.getNowTime());
                roleDao.update(role,roleQueryWrapper.eq("role_name",roleUpdateDto.getOldName()));
                return OpenResponse.ok("修改成功");
            }
            if (roleUpdateDto.getNewName()!=null && !"".equals(roleUpdateDto.getNewName())){
                role.setRoleName(roleUpdateDto.getNewName());
                role.setUpdateTime(PublicMethod.getNowTime());
                roleDao.update(role,roleQueryWrapper.eq("role_name",roleUpdateDto.getOldName()));
                return OpenResponse.ok("修改成功");
            }
            if (roleUpdateDto.getNote()!=null && !"".equals(roleUpdateDto.getNote())){
                role.setNote(roleUpdateDto.getNote());
                role.setUpdateTime(PublicMethod.getNowTime());
                roleDao.update(role,roleQueryWrapper.eq("role_name",roleUpdateDto.getOldName()));
                return OpenResponse.ok("修改成功");
            }
            else {
                return OpenResponse.fail("角色名不能为空");
            }

        }
    }

    /**
     *
     * @param “角色ID”
     * @return 删除提示
     */
    @Override
    public OpenResponse delete(RoleDeleteDto roleDeleteDto){
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        QueryWrapper<Role_permission> rolePermissionQueryWrapper = new QueryWrapper<>();
        QueryWrapper<User_role> userRoleQueryWrapper = new QueryWrapper<>();
        Integer have = roleDao.selectCount(roleQueryWrapper.eq("role_id",roleDeleteDto.getRoleId()));

        if (have == 1){
            rolePermissionDao.deleteById(rolePermissionQueryWrapper.select("id").eq("role_id",roleDeleteDto.getRoleId()));
            userRoleDao.deleteById(userRoleQueryWrapper.select("id").eq("role_id",roleDeleteDto.getRoleId()));
            roleDao.deleteById(roleDeleteDto.getRoleId());
            return OpenResponse.ok("删除成功");
        }else {
            return OpenResponse.fail("没有此角色");
        }
    }

    /**
     *
     * @return 所有菜单
     */
    @Override
    public OpenResponse permission(){
        List<AllPermissionVo> permissions = permissionDao.allPermission();
        ArrayList<AllPermissionVo> permissionsList = new ArrayList<>();
        ArrayList<AllPermissionVo> permissionsListChild = new ArrayList<>();
        List<AllPermissionVo> permission = new ArrayList<>();
        permissions.forEach(i -> {
            if (i.getLevel()==1){
                for (AllPermissionVo child:permissions ){
                    if (child.getParentId().equals(i.getId())){
                        for (AllPermissionVo children:permissions){
                            if (children.getParentId().equals(child.getId())){
                                permissionsListChild.add(children);
                            }
                        }
                        child.setChildren(permissionsListChild);
                        permissionsList.add(child);
                    }
                }
                i.setChildren(permissionsList);
                permission.add(i);
            }
        });
        return OpenResponse.ok("菜单资源",permission);
    }

    @Override
    public OpenResponse fixStatus(RoleStatusDto roleStatusDto){
        if (roleStatusDto.getRoleName() !=null && !"".equals(roleStatusDto.getRoleName())){
            if (roleStatusDto.getStatus() !=null && (roleStatusDto.getStatus()==0 || roleStatusDto.getStatus()==1)){
                QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
                Integer role = roleDao.selectCount(roleQueryWrapper.eq("role_name",roleStatusDto.getRoleName()));
                if (role ==1){//有角色
                    Integer oldStatus = roleDao.selectOne(roleQueryWrapper.select("status").eq("role_name",roleStatusDto.getRoleName())).getStatus();
                    if (!oldStatus.equals(roleStatusDto.getStatus())){
                        roleDao.fixStatus(roleStatusDto);
                        return OpenResponse.ok("状态修改成功");
                    }else {
                        return OpenResponse.fail("无需修改");
                    }
                }
                return OpenResponse.fail("角色"+roleStatusDto.getRoleName()+"不存在");
            }else {
                return OpenResponse.fail("角色状态不能为空且只能是0或1");
            }
        }
        return OpenResponse.fail("角色名称不能为空");
    }
}

