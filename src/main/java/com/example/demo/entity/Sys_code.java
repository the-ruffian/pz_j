/*
 * @Description:Sys_code
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-08-14 17:25
 * @LastEditTime: 2021-08-15 17:36:04
 * @LastEditors: the-ruffian
 */
package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName(value = "sys_code")
public class Sys_code {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String code;

    private Integer used;

    private String email;

    private Timestamp createTime;

    private Timestamp UpdateTime;
}
