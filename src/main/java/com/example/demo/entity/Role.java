package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.sql.Timestamp;

/**
 * @author bugpz
 * @date 2021-09-25 22:25:01
 */
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
