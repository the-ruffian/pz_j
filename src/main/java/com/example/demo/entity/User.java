package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.sql.Timestamp;


/**
 * @author bugpz
 * @date 2021-09-25 22:29:15
 */

@Data
@TableName(value = "user")
@ApiModel(value = "用户表")
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "用户密码")
    private String password;
    @ApiModelProperty(value = "用户手机号")
    private String phone;
    private Integer gender;
    private String remark;
    private String idNumber;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Timestamp loginTime;
    private String username;
    private String email;


}
