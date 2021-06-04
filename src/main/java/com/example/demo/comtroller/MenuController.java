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
import com.example.demo.vos.PermissionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "菜单权限")
public class MenuController {
    @Autowired
    UserRoleDao userRoleDao;

    @ApiOperation(value = "获取菜单权限")
    @PostMapping(value = "/api/auth/list",produces = "application/json;charset=UTF-8")
    public Object menu(@RequestBody JSONObject jsonParam){
        String phone = jsonParam.getString("phone");
        List<Permission> allMenu = userRoleDao.selectByPhone(phone);
        List<PermissionVo> permissionVoList = new ArrayList<>();
        List<PermissionVo> permissionThree = new ArrayList<>();
        List<PermissionVo> permissionTwo = new ArrayList<>();
        JSONObject obj = new JSONObject();
        allMenu.forEach(i -> {
            if (i.getLevel().equals("1")) {
                for (Permission child: allMenu){
                    if (child.getLevel().equals("2") && child.getParentId().equals(i.getId())){
                        PermissionVo permissionVo = new PermissionVo();
                        for (Permission children:allMenu){
                            if (children.getLevel().equals("3") && children.getParentId().equals(child.getId())) {
                                PermissionVo permissionVo1 = new PermissionVo();
                                BeanUtils.copyProperties(children, permissionVo1);
                                permissionThree.add(permissionVo1);
                            }
                            permissionVo.setChild(permissionThree);
                        }
                        BeanUtils.copyProperties(child, permissionVo);
                        permissionTwo.add(permissionVo);
                    }
                }

                PermissionVo permissionVo = new PermissionVo();
                permissionVo.setChild(permissionTwo);
                BeanUtils.copyProperties(i, permissionVo);
                permissionVoList.add(permissionVo);
            }
        });
        obj.put("result",permissionVoList);
        return obj.toJSONString();
    }
}
