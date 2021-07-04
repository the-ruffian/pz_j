/*
 * @Description:UserLoginService
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-04 17:04
 * @LastEditTime: 2021-07-04 17:04
 * @LastEditors: the-ruffian
 */
package com.example.demo.service;

import com.example.demo.model.dto.UserLoginDto;
import com.example.demo.utils.model.OpenResponse;
import org.apache.ibatis.annotations.Param;

public interface UserLoginService {
    OpenResponse login(@Param("userLoginDto")UserLoginDto userLoginDto);
}
