package com.example.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils {


    private static final String SING = "N/BVDrwzAwAJMjnb4HeVOw=="; //  签名 此处采用MD5(base64)加密某字段
    /*
    * 生成token header.payload.sing
    * */
    public static String getToken(Map<String,String> map){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 100); // 默认100秒过期

        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();

        //payload
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        String token = builder.withExpiresAt(instance.getTime()). //指定令牌过期时间
                sign(Algorithm.HMAC256(SING));
        return token;
    }
    
    /*
    * 验证token 合法性
    * */
    public static void  verify(String token){
        JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }
    
    /*
    * 获取token信息的方法
    * */
    public static DecodedJWT getTokenInfo(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
        return verify;
    }
}
