package com.example.demo.service.role.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Role;
import com.example.demo.mapper.RoleDao;
import com.example.demo.model.dto.RoleAddDto;
import com.example.demo.service.role.RoleAddService;
import com.example.demo.utils.PublicMethod;
import com.example.demo.utils.model.OpenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("RoleAddService")
public class RoleAddServiceImpl implements RoleAddService {

    @Autowired
    RoleDao roleDao;

    public OpenResponse add(RoleAddDto roleAddDto) {
        String roleName = roleAddDto.getRoleName();
        String note = roleAddDto.getNote();
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        if (roleDao.selectCount(roleQueryWrapper.eq("role_name", roleName)) == 0) {
            if (null != roleName && !roleName.equals("")) {
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
}

