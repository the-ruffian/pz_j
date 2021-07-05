/*
 * @Description:UserUpdateService
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-05 13:19
 * @LastEditTime: 2021-07-05 13:19
 * @LastEditors: the-ruffian
 */
package com.example.demo.service;

import com.example.demo.utils.model.OpenResponse;
import org.apache.ibatis.annotations.Param;

public interface UserUpdateService {
    OpenResponse update(@Param("userUpdateService") UserUpdateService userUpdateService);
}
