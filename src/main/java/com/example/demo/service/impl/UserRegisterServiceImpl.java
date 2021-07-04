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
import com.example.demo.service.UserRegisterService;
import com.example.demo.utils.PublicMethod;
import com.example.demo.utils.model.OpenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


@Service("UserService")
public class UserRegisterServiceImpl implements UserRegisterService {
    @Autowired
    UserDao userDao;

    @Override
    public OpenResponse registerUser(UserRegisterDto userRegisterDto) {
        Integer gender = userRegisterDto.getGender();//.equals("男")  ? 1 : userRegisterDto.getGender().equals("女") ? 0 : 2;
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        Integer integer = userDao.selectCount(userQueryWrapper.select("phone")
                .eq("phone",userRegisterDto.getPhone()));
        if (
                userRegisterDto.getUsername().equals("") || userRegisterDto.getUsername() == null ||
                userRegisterDto.getGender() == null ||
                userRegisterDto.getEmail().equals("") || userRegisterDto.getEmail() == null ||
                userRegisterDto.getPassword().equals("") || userRegisterDto.getPassword() == null ||
                userRegisterDto.getPhone().equals("") || userRegisterDto.getPhone() == null ) {
            return OpenResponse.fail("请正确填写内容");
        } else if (integer ==1){
            return OpenResponse.fail("手机号已存在");
        } else if (integer == 0) {
            User user = new User();
            user.setPhone(userRegisterDto.getPhone());
            user.setUsername(userRegisterDto.getUsername());
            user.setGender(gender);
            user.setPassword(DigestUtils.md5DigestAsHex(userRegisterDto.getPassword().getBytes()));
            user.setEmail(userRegisterDto.getEmail());
            user.setCreateTime(PublicMethod.getNowTime());
            userDao.insert(user);
            return OpenResponse.ok("尊敬的"+userRegisterDto.getUsername()+",恭喜你注册成功");
        } else {
            return OpenResponse.fail("未知错误");
        }
    }
}
