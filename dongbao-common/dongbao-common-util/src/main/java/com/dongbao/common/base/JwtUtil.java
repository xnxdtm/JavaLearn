package com.dongbao.common.base;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Auther zhangjq
 * @Date 2022/6/21
 * @Description com.dongbao.common.base
 * @Version 1.0
 */
public class JwtUtil {

    private static final String secret = "abcd";

    /*
    eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ6aGFuZ3NhbiJ9.iZ4AsYdUiE5watMgUb60uy3J6VV_nrG74wbOBV7vW2g
    eyJhbGciOiJIUzI1NiJ9: header(加密算法)
    eyJzdWIiOiJ6aGFuZ3NhbiJ9: payload(存储的信息)
    iZ4AsYdUiE5watMgUb60uy3J6VV_nrG74wbOBV7vW2g: sign(签名)
    header和payload => 使用base64编码得到的, sign => 使用header中的加密算法通过散列加密得到 (散列加密(payload, 盐(secret)))
     */
    public static String createToken(String subject) {
        return Jwts.builder().setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 30))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public static String parseToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public static void main(String[] args) throws InterruptedException {
        // 获取token和解析token
        String token = createToken("zhangsan");
        System.out.println(token);
        String src = JwtUtil.parseToken(token);
        System.out.println(src);

        // 监测过期时间
        TimeUnit.SECONDS.sleep(4);
        String src2 = JwtUtil.parseToken(token);
        System.out.println(src2);
    }
}
