/*
 * @Description:UserDeleteService
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-07 20:55
 * @LastEditTime: 2021-07-07 20:55
 * @LastEditors: the-ruffian
 */
package com.example.demo.service;

import com.example.demo.model.dto.UserDeleteDto;
import com.example.demo.utils.model.OpenResponse;
import org.apache.ibatis.annotations.Param;

public interface UserDeleteService {
    OpenResponse delete(@Param("userDeleteDto")UserDeleteDto userDeleteDto);
}
