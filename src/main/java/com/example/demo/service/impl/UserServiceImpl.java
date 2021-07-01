/*
 * @Description:UserServiceImpl
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-01 19:44
 * @LastEditTime: 2021-07-01 19:44
 * @LastEditors: the-ruffian
 */
package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserDao;
import com.example.demo.model.dto.UserRegisterDto;
import com.example.demo.service.UserService;
import com.example.demo.utils.PublicMethod;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public List<User> registerUser(UserRegisterDto userRegisterDto) {
        Integer gender = userRegisterDto.getGender();//.equals("男")  ? 1 : userRegisterDto.getGender().equals("女") ? 0 : 2;
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        Integer integer = userDao.selectCount(userQueryWrapper.select("phone")
                .eq("phone",userRegisterDto.getPhone()));
        if (integer == 0) {
            User user = new User();
            user.setPhone(userRegisterDto.getPhone());
            user.setUsername(userRegisterDto.getUsername());
            user.setGender(gender);
            user.setPassword(DigestUtils.md5DigestAsHex(userRegisterDto.getPassword().getBytes()));
            user.setEmail(userRegisterDto.getEmail());
            user.setCreateTime(PublicMethod.getNowTime());
            userDao.insert(user);
        }
        return null;
    }
}
