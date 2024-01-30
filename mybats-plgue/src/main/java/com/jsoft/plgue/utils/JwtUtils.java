package com.jsoft.plgue.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;


public class JwtUtils {


    public static String SECRET = "workbench-schedule:demo";


    public static void main(String[] args) {

        String jwt = createJWT("", "", "", 10000);

        parseJWT("eyJhbGciOiJSUzI1NiIsImtpZCI6IjFOY25YbiJ9.eyJqdGkiOiJ0WlBzS1hnWUJlR2tkc1RHLS1YUjZRIiwiYXVkIjoibkh4eWhKaGZGWmJkN3dlSWxjOGo0IiwiaWF0IjoxNzAzNzQyMjkxLCJuYmYiOjE3MDM3NDIyOTEsImV4cCI6MTc5MDE0MjI5MSwiY2xpIjoiN2d3TGNkbW9yYVNSNXV1TjdYNExqOSIsImlzcyI6Imh0dHBzOi8vaWQtb250ZXN0LmxpeGlhbmcuY29tL2FwaS9kaXNjb3YvN2d3TGNkbW9yYVNSNXV1TjdYNExqOSIsInNjb3BlcyI6WyJ3b3JrYmVuY2gtc2NoZWR1bGU6ZGVtbyJdfQ.eJN4cVSZ_Xf6SRIFulJkOZ8RyMujgnGcn474lzlnGEL3gLa4kUzudaSHcjHmGHLswo9ZTz5V5F-FJk-QH4LbsjXQxkdykUZTNoxW9tSDEqpgUXYAoFXjODNnpjC7VYI822h8ka2GeadLZF1YCRMmFJJChJUgBaNujcOnbfL2Xr4VQ3jEFiLQTml-cbBRMtwyJoM6efGjeNa0w5HsGypappX2i144whDQjcQcNB0ih7Hr5Jas6g644DcVR1q3_RPsnSP6QWw-IqBLhymXiW1zWj5uIcT4KJwVvZuOcZ9ZqImMF5GhtdwELQp7CYKr402pHKMLagQrjvqVNvnDyreneA");

    }

    //构建JWT的示例方法
    private static String createJWT(String id, String issuer, String subject, long ttlMillis) {

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
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);
        //builder.claim("name", "value"); //设置自定义的信息

        //如果已经指定，让我们添加到期日
        //过期时间
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //构建JWT并将其序列化为紧凑的URL安全字符串
        return builder.compact();
    }


    //验证和读取JWT的示例方法
    private static void parseJWT(String jwt) {

        //如果它不是签名的JWS（如预期的那样），则该行将抛出异常
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                .parseClaimsJws(jwt).getBody();
        System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Expiration: " + claims.getExpiration());
        //claims.get("name") //获取自定义的信息
    }


}
