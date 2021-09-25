/*
 * @Description:Sys_code
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-08-14 17:25
 * @LastEditTime: 2021-09-25 22:17:48
 * @LastEditors: the-ruffian
 */
package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.sql.Timestamp;


/**
 * @author bugpz
 * @date 2021-09-25 22:26:13
 */
@Data
@TableName(value = "sys_code")
public class SysCode {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String code;

    private Integer used;

    private String email;

    private Timestamp createTime;

    private Timestamp updateTime;
}
