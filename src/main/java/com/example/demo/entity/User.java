package com.example.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@ApiModel(value = "用户")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @ApiModelProperty(value = "用户名")
    @NotNull(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value = "用户密码")
    @NotNull(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "电子邮箱")
    @NotNull(message = "邮箱不能为空")
    private String email;

    @ApiModelProperty(value = "性别:中文")
    @NotNull(message = "性别不能为空")
    private String sex;

    @ApiModelProperty(value = "手机号", required = true)
    @Column(length = 12, nullable = false)
    @NotNull(message = "手机号不能为空")
    private String phone;

    public User() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
