package com.lzx.utils;
import com.lzx.domain.entity.User;
import com.lzx.domain.request.UserReq;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    //密钥
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long expiration;


    public String generateToken(User user) {
        Long userId = user.getUserId();
        String username = user.getUsername();
        String email = user.getEmail();
        Date now = new Date();
        Date expirationTime = new Date(System.currentTimeMillis() + expiration);

        return Jwts.builder()
                .setSubject(String.valueOf(userId)) // 用户ID
                .claim("username", username) // 用户名
                .claim("email", email) // 电子邮件
//                .claim("roles", roles) // 角色
                .setIssuedAt(now) // 签发时间
                .setExpiration(expirationTime) // 过期时间
                .signWith(SignatureAlgorithm.HS256,secretKey) // 签名密钥
                .compact();
    }

    /**
     * 验证JWT的有效期
     *
     * @param token JWT字符串
     * @return true 如果JWT在有效期内，否则返回false
     */
    public boolean isTokenExpired(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
            System.out.println("过期时间：" +  claims.getExpiration());
            System.out.println("当前时间：" + new Date());
            System.out.println("是否过期: " + claims.getExpiration().before(new Date()));
            return claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return true; // Token无效或解析错误
        }
    }

    /**
     * 验证JWT是否合法
     *
     * @param token JWT字符串
     * @return true 如果JWT合法，否则返回false
     */
    public boolean isTokenValid(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false; // Token无效或解析错误
        }
    }

    /**
     * 通过JWT获取用户ID
     *
     * @param token JWT字符串
     * @return 用户ID，如果JWT无效则返回null
     */
    public Long getUserIdFromToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return Long.parseLong(claims.getBody().getSubject());
        } catch (Exception e) {
            return null; // Token无效或解析错误
        }
    }
}
