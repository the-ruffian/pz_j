/*
 * @Description:UserUpdateServiceImpl
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-05 14:54
 * @LastEditTime: 2021-7-5 18:14:19
 * @LastEditors: the-ruffian
 */
package com.example.demo.service.impl;

import com.example.demo.mapper.UserDao;
import com.example.demo.service.UserUpdateService;
import com.example.demo.utils.model.OpenResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("UserUpdateService")
public class UserUpdateServiceImpl {

    @Autowired
    UserDao userDao;

    public OpenResponse update(@Param("userUpdateService")UserUpdateService userUpdateService){
        return null;
    }
}
