/*
 * @Description:UserUpdateService
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-05 13:19
 * @LastEditTime: 2021-07-05 20:28:29
 * @LastEditors: the-ruffian
 */
package com.example.demo.service;

import com.example.demo.model.dto.UserUpdateDto;
import com.example.demo.utils.model.OpenResponse;
import org.apache.ibatis.annotations.Param;

public interface UserUpdateService {
    OpenResponse update(@Param("userUpdateDto") UserUpdateDto userUpdateDto);
}
