/*
 * @Description:Status
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-02 15:40
 * @LastEditTime: 2021-09-25 22:17:42
 * @LastEditors: the-ruffian
 */
package com.example.demo.enums;



import lombok.Getter;



@Getter
/**
 * @author the-ruffian
 */
public enum Status{
    /**
     * SUCCESS 成功
     */
    SUCCESS(200,"成功"),
    /**
     * FAIL 失败
     */
    FAIL(400,"失败");


    private int code;
    private String message;
    Status(int code, String message){
        this.code=code;
        this.message=message;
    }


}
