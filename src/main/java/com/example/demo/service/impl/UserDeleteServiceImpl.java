/*
 * @Description:UserDeleteServiceImpl
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-07 21:02
 * @LastEditTime: 2021-07-07 21:02
 * @LastEditors: the-ruffian
 */
package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserDao;
import com.example.demo.model.dto.UserDeleteDto;
import com.example.demo.service.UserDeleteService;
import com.example.demo.utils.model.OpenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service("UserDeleteService")
public class UserDeleteServiceImpl implements UserDeleteService {
    @Autowired
    UserDao userDao;

    public OpenResponse delete(UserDeleteDto userDeleteDto){
        String phone=userDeleteDto.getPhone();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if (userDao.selectCount(userQueryWrapper.eq("phone",phone))==1){
            HashMap<String, Object> stringObjectHashMap = new HashMap<>();
            stringObjectHashMap.put("phone",phone);
            userDao.deleteByMap(stringObjectHashMap);
            return OpenResponse.ok("删除成功");
        }
        if (userDao.selectCount(userQueryWrapper.eq("phone",phone))==0){
            return OpenResponse.ok("用户不存在");
        }
        return OpenResponse.ok("参数错误");
    }
}
