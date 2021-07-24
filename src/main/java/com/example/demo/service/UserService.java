/*
 * @Description:UserService
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-01 19:43
 * @LastEditTime: 2021-7-13 18:06:21
 * @LastEditors: the-ruffian
 */
package com.example.demo.service;



import com.example.demo.model.dto.*;
import com.example.demo.utils.model.OpenResponse;
import org.apache.ibatis.annotations.Param;



public interface UserService {
    OpenResponse registerUser(@Param("userRegisterDto") UserRegisterDto userRegisterDto);
    OpenResponse search(@Param("userListDto")UserListDto userListDto);
    OpenResponse update(@Param("userUpdateDto") UserUpdateDto userUpdateDto);
    OpenResponse login(@Param("userLoginDto") UserLoginDto userLoginDto);
    OpenResponse delete(@Param("userDeleteDto") UserDeleteDto userDeleteDto);
    OpenResponse forgetPassword(@Param("userResetPasswordDto") UserResetPasswordDto userResetPasswordDto);
}
