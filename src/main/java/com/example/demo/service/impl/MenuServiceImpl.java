//2021-07-18 16:46:24
package com.example.demo.service.impl;

import com.example.demo.mapper.PermissionDao;
import com.example.demo.model.dto.UserMenuListDto;
import com.example.demo.model.vo.UserMenuListVo;
import com.example.demo.service.MenuService;
import com.example.demo.utils.model.OpenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("MenuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    PermissionDao permissionDao;

    @Override
    public OpenResponse userMenu(UserMenuListDto userMenuListDto){
        List<UserMenuListVo> permissions = permissionDao.allMenu(userMenuListDto);
        ArrayList<UserMenuListVo> userMenuListVos = new ArrayList<>();
        ArrayList<UserMenuListVo> userMenuListVos1 = new ArrayList<>();
        List<UserMenuListVo> userMenuListVo2 = new ArrayList<>();
        permissions.forEach(i -> {
            if (i.getLevel()==1){
                for (UserMenuListVo child:permissions ){
                    if (child.getParentId().equals(i.getId())){
                        for (UserMenuListVo children:permissions){
                            if (children.getParentId().equals(child.getId())){
                                userMenuListVo2.add(children);
                            }
                        }
                        child.setChildren(userMenuListVo2);
                        userMenuListVos1.add(child);
                    }
                }
            i.setChildren(userMenuListVos1);
            userMenuListVos.add(i);
            }
        });
        return OpenResponse.ok("菜单权限",userMenuListVos);
    }
}
