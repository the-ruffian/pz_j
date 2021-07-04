/*
 * @Description:UserService
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-01 19:43
 * @LastEditTime: 2021-07-01 19:43
 * @LastEditors: the-ruffian
 */
package com.example.demo.service;



import com.example.demo.model.dto.UserRegisterDto;
import com.example.demo.utils.model.OpenResponse;
import org.apache.ibatis.annotations.Param;



public interface UserRegisterService {
    OpenResponse registerUser(@Param("userRegisterDto") UserRegisterDto userRegisterDto);
}
