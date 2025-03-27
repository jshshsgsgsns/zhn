package org.example.zhn.Utils;

import io.jsonwebtoken.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Token {

    // 密钥（建议从配置文件读取）
    private static final String SECRET_KEY = "password1234567890wangcheng1223rwgfergerhtrhytj6j6jhr3wefs4egege5gddb6turr6y";
    // Token有效期 1周
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7;

    // 生成 JWT
    public static String generateToken(String username) {
        // 设置 JWT 的 Claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", username);  // 自定义 payload
        claims.put("role", "USER");
        claims.put("time", System.currentTimeMillis());
        claims.put("exp", System.currentTimeMillis() + EXPIRATION_TIME);
        // 可以根据需要设置用户角色等信息

        return Jwts.builder()
                .setClaims(claims) // 设置 Payload
                .setIssuedAt(new Date()) // 设置签发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 设置过期时间
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 使用 HMAC 算法和密钥进行签名
                .compact();
    }

    // 解析 JWT
    public static Claims parseToken(String token) {
        try {
            return Jwts.parser()  // 使用新的 parserBuilder
                    .setSigningKey(SECRET_KEY)  // 设置签名密钥
                    .build()
                    .parseClaimsJws(token)  // 解析Token
                    .getBody();
        } catch (JwtException e) {
            return null;  // Token无效或解析失败
        }
    }

    // 获取 JWT 中的用户名
    public static String getUsername(String token) {
        Claims claims = parseToken(token);
        return claims != null ? claims.get("sub", String.class) : null;
    }

    // 校验 JWT 是否有效
    public static boolean isTokenExpired(String token) {
        Claims claims = parseToken(token);
        return claims != null && claims.getExpiration().after(new Date());
    }

    // 校验 JWT 的有效性
    public static boolean validateToken(String token, String username) {
        String tokenUsername = getUsername(token);
        return tokenUsername != null && tokenUsername.equals(username) && isTokenExpired(token);
    }



}
