package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import javax.validation.constraints.NotNull;


/**
 * @author bugpz
 * @date 2021年09月25日22:27:13
 */
@Data
@TableName(value = "token")
public class Token {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @NotNull
    private String token;


    private String phone;

    public Token(){

    }
}

