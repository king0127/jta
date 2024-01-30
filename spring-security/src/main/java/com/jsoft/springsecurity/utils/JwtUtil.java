package com.jsoft.springsecurity.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;


@Slf4j
public class JwtUtil {


    public static final String SECRET = "workbench-schedule:demo";

    private static final Long TTL_MILLIS = 60*60*1000*24L;

    private static final String ISSUER = "https://www.baidu.com";

    //构建JWT的示例方法
    public static String createJWT(String id, String subject) {

        //我们将用于签署令牌的JWT签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //创建时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //我们将使用我们的Api Key秘密签署您的JWT
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //让我们设置JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(ISSUER)
                .signWith(signatureAlgorithm, signingKey);
        //如果已经指定，让我们添加到期日
        //过期时间
        if (TTL_MILLIS >= 0) {
            long expMillis = nowMillis + TTL_MILLIS;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //构建JWT并将其序列化为紧凑的URL安全字符串
        return builder.compact();
    }


    //验证和读取JWT的示例方法
    public static String parseJWT(String jwt) {

        //如果它不是签名的JWS（如预期的那样），则该行将抛出异常
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                    .parseClaimsJws(jwt).getBody();
            System.out.println("ID: " + claims.getId());
            System.out.println("Subject: " + claims.getSubject());
            System.out.println("Issuer: " + claims.getIssuer());
            System.out.println("Expiration: " + claims.getExpiration());
            return claims.getSubject();
        } catch (Exception e) {
            log.error(" 解析JWT失败:{} ", e.getMessage());
        }
        return null;
    }


}
