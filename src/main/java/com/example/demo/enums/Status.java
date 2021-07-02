/*
 * @Description:Status
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-07-02 15:40
 * @LastEditTime: 2021-07-02 15:40
 * @LastEditors: the-ruffian
 */
package com.example.demo.enums;



import lombok.Getter;



@Getter
public enum Status{
    SUCCESS(200,"成功"),
    FAIL(400,"失败");


    private int code;
    private String message;
    Status(int code, String message){
        this.code=code;
        this.message=message;
    }


}
