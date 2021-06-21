/*
 * @Description:NowTime
 * @CreatedBy:IntelliJ IDEA
 * @Author: the-ruffian
 * @Date: 2021-06-21 17:38
 * @LastEditTime: 2021-06-21 17:38
 * @LastEditors: the-ruffian
 */
package com.example.demo.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PublicMethod {
    public static Timestamp getNowTime() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return Timestamp.valueOf(df.format(date));
    }
}
