/*
 * @Description:验证码Dao
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-08-15 16:10
 * @LastEditTime: 2021-08-15 16:15:12
 * @LastEditors: the-ruffian
 */
package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Sys_code;
import org.springframework.stereotype.Component;

@Component
public interface SysCodeDao extends BaseMapper<Sys_code> {
}
