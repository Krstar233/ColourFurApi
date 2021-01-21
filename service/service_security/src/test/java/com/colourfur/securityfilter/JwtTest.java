package com.colourfur.securityfilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.UUID;

public class JwtTest {
    /**
     * 过期时间（毫秒单位）
     */
    private final static long TOKEN_EXPIRE_MILLIS = 1000 * 5;

    @Test
    public void testjjwt() throws InterruptedException {
        String private_key = "123321651231352151546152156416511";
        SecretKey secretKey = new SecretKeySpec(private_key.getBytes(), SignatureAlgorithm.HS256.getJcaName());

        long currentTimeMillis = System.currentTimeMillis();

        String token = Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date(currentTimeMillis))    // 设置签发时间
                .setExpiration(new Date(currentTimeMillis + TOKEN_EXPIRE_MILLIS))   // 设置过期时间
                .setSubject("JSON Web Token")
                .signWith(secretKey)
                .compact();
        System.out.println(token);

        Thread.sleep(TOKEN_EXPIRE_MILLIS-2000);
        // 2. 验证token，如果验证token失败则会抛出异常
        try {
            // 解析
            Claims body = Jwts.parserBuilder()  // 创建解析对象
                    .setSigningKey(secretKey)   // 设置安全密钥（生成签名所需的密钥和算法）
                    .build()
                    .parseClaimsJws(token)      // 解析token
                    .getBody();                 // 获取 payload 部分内容
            System.out.println("验证成功！");
            //OK, we can trust this JWT

            System.out.println(body);

        } catch (JwtException e) {
            System.out.println("验证失败！");
            //don't trust the JWT!
        }

    }
}
