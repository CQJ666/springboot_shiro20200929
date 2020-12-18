package com.example.springboot_shiro20200929.jwtutils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.shiro.codec.Base64;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Date;

/**
 * @program: yuanzilian
 * @description: todo
 * @author: liuq
 * @Date: 2020/5/22 20:52
 * @Version: 1.0
 */
public class JwtUtils {

    // 代表这个JWT的接收对象,存入audience
    private static String clientId = "pOtBQ$Eh6EN!isDoaD5P70vglcpr%i";
    // 密钥, 经过Base64加密, 可自行替换
    private static String base64Secret = "p^64Y91rDgnvzcjpE3@d9a0MPtvS%6xMdMPoAXk1eNZDOBU6nJ!*&K$D2ngh";
    // JWT的签发主体，存入issuer
    private static String name = "dirxtxmngpt";
    // userId
    private static String userIdKey = "E0S9qHuQkBJyocL";
    // 过期时间，时间戳
    private static Long expiresSecond = new Long(30L * 24L * 60L * 60L * 1000L);
//    private static Long expiresSecond = new Long(2L * 60L * 60L * 1000L);

    /**
     * 构建jwt
     * @param userId
     * @return
     */
    public static String createJWT(String userId) {
//    public static String createJWT(String userId, String username, String role) {
        // 使用HS256加密算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Secret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //userId是重要信息，进行加密下
        String encryId = baseConvertStr(userId);

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
//                .claim("role", role)  // 可以将基本不重要的对象信息放到claims
                .claim(userIdKey, encryId)
//                .setSubject(username)           // 代表这个JWT的主体，即它的所有人
                .setIssuer(clientId)              // 代表这个JWT的签发主体；
                .setIssuedAt(new Date())        // 是一个时间戳，代表这个JWT的签发时间；
                .setAudience(name)          // 代表这个JWT的接收对象；
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        long TTLMillis = expiresSecond;
        if (TTLMillis >= 0) {
            long expMillis = nowMillis + TTLMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp)  // 是一个时间戳，代表这个JWT的过期时间；
                    .setNotBefore(now); // 是一个时间戳，代表这个JWT生效的开始时间，意味着在这个时间之前验证JWT是会失败的
        }
        //生成JWT
        return addTokenStr(builder.compact());
    }

    /**
     * Base64转码
     * @param str
     * @return
     */
    public static String baseConvertStr(String str) {
        Base64 base64 = new Base64();
        String base64Sign = null;
        try {
            base64Sign = base64.encodeToString(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return base64Sign;
    }

    /**
     * Base64转码
     * @param str
     * @return
     */
    public static String strConvertBase(String str) {
        Base64 base64 = new Base64();
        String base64Sign = null;
        try {
            byte[] decode = base64.decode(str.getBytes("UTF-8"));
            base64Sign = new String(decode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return base64Sign;
    }

    /**
     * 解析jwt
     * @param jsonWebToken
     * @return
     */
    public static Claims parseJWT(String jsonWebToken) {
        String jwToken = delTokenStr(jsonWebToken);
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(base64Secret))
                .parseClaimsJws(jwToken).getBody();
        return claims;
    }

    /**
     * 从token中获取用户名
     * @param token
     * @return
     */
    public static String getUsername(String token){
        return parseJWT(token).getSubject();
    }

    /**
     * 从token中获取用户ID
     * @param token
     * @return
     */
    public static Long getUserId(String token){
        String userId = parseJWT(token).get(userIdKey, String.class);
        return Long.valueOf(strConvertBase(userId));
    }

    /**
     * 是否已过期
     * @param token
     * @return
     */
    public static boolean isExpiration(String token) {
        try{
            return parseJWT(token).getExpiration().before(new Date());
        } catch (Exception e){
            return true;
        }
    }

    /**
     * 添加字符串
     * @param token
     * @return
     */
    public static String addTokenStr(String token){
        int tokenLength = token.length();
        if (tokenLength < 137){
            return token;
        }
        StringBuffer tokenBuffer = new StringBuffer(token);
        // 倒137 + bZGU2k0J4XFNtCFJq 17
        tokenBuffer.insert(tokenBuffer.length() - 137, "bZGU2k0J4XFNtCFJq");
        // 倒56 + 11
        tokenBuffer.insert(tokenBuffer.length() - 56, MyStringUtil.getRandomAllChar(11));
        // 倒21 + 5
        tokenBuffer.insert(tokenBuffer.length() - 21, MyStringUtil.getRandomAllChar(5));
        return tokenBuffer.toString();
    }

    /**
     * 去除字符串
     * @param token
     * @return
     */
    public static String delTokenStr(String token){
        int tokenLength = token.length();
        if (tokenLength < 170){ // 137   +   17   +   11   +   5
            return token;
        }
        StringBuffer tokenBuffer = new StringBuffer(token);
        // 倒21 + 5
        tokenBuffer.delete(tokenBuffer.length() - 26, tokenBuffer.length() - 21);
        // 倒56 + 11
        tokenBuffer.delete(tokenBuffer.length() - 67, tokenBuffer.length() - 56);
        // 倒137 + bZGU2k0J4XFNtCFJq 17
        tokenBuffer.delete(tokenBuffer.length() - 154, tokenBuffer.length() - 137);
        return tokenBuffer.toString();
    }

    public static void main(String[] args) {
        String jwt = createJWT("100123456789");
        System.out.println(jwt);
        System.out.println(parseJWT(jwt));
        System.out.println(getUserId("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJFMFM5cUh1UWtCSnlvY0wiOiJNZz09IiwiaXNzIjoicE90QlEkRWg2RU4haXNEb2FENVA3MHZnbGNwciVpIiwiabZGU2k0J4XFNtCFJqWF0IjoxNjA0NDcyMDE2LCJhdWQiOiJkaXJ4dHhtbmdwdCIsImV4cCI6MTYwNzA2NDAxNiwibmJmIjoxNjmZrjLXQJByRA0NDcyMDE2fQ.d59k63ynicYvEocQsFZ3VVsIm1fFSAACyGQdCjh1PD8KUx0k"));
    }
    public static String getRequestToken(HttpServletRequest request) {
        return request.getHeader("token");
    }
}
