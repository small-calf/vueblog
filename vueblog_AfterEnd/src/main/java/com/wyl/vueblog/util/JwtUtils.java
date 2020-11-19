package com.wyl.vueblog.util;/**
 * @Auther:小王
 * @Date:2020/11/8
 * @Description:vueblog
 * @version:1.0
 */

import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 **/
@Data
@Component
@ConfigurationProperties(prefix = "calf.jwt")
public class JwtUtils {
    private String secret;
    private long expire;
    private String header;
    /**
     * 生成jwt token
     */
    public String generateToken(long userId) {
        System.out.println(secret);
        System.out.println(expire);
        System.out.println(header);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,7);//默认7天过期
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime()+expire * 1000);
        System.out.println(nowDate.getTime());
        System.out.println(nowDate.getTime()+expire);
        System.out.println(nowDate.getTime()+expire * 1000);
        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setSubject(userId+"")
                .setIssuedAt(nowDate)
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    // 获取jwt的信息
    public Claims getClaimByToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            System.out.println("解jwts");
            return null;
        }
    }

    /**
     * token是否过期
     * @return  true：过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }
}

