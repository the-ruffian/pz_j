/*
 * @Description:Role
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-06-02 10:09
 * @LastEditTime: 2021-8-23 21:21:54
 * @LastEditors: the-ruffian
 */
package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.sql.Timestamp;


@Data
@TableName(value = "role")
public class Role {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String roleName;

    private Integer status;

    private String note;

    private Timestamp createTime;

    private Timestamp updateTime;

}
