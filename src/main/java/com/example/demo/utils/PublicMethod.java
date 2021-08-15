/*
 * @Description:公共方法
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-06-21 17:38
 * @LastEditTime: 2021-08-15 17:58:44
 * @LastEditors: the-ruffian
 */
package com.example.demo.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class PublicMethod {
    public static Timestamp getNowTime() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return Timestamp.valueOf(df.format(date));
    }
    public static Integer code() {
        int max=9999;
        int min=1000;
        Random random = new Random();
        //生成验证码
        return random.nextInt(max)%(max-min+1) + min;
    }
}
