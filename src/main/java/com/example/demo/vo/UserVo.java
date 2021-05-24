package com.example.demo.vo;

import com.example.demo.entity.User;

import java.util.List;

/*
* 包装类
* */
public class UserVo {
    private Integer current;
    private Integer size;
    private Long total;
    private List<User> userList;

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Integer getCurrent() {
        return current;
    }

    public Integer getSize() {
        return size;
    }

    public Long getTotal() {
        return total;
    }

    public List<User> getUserList() {
        return userList;
    }
}
