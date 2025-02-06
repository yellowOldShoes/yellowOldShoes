package com.huangsf.ums.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.huangsf.ums.common.ErrorCode;
import com.huangsf.ums.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author huangsf
 * @create 2024-12-09  13:45
 */
@Component
public class JwtUtils {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Value("${token.expire-time}")
    private Integer expireTime;

    //token中存放用户id对应的名字
    private static final String CLAIM_NAME_USERID = "CLAIM_NAME_USERID";
    //token中存放用户名对应的名字
    private static final String CLAIM_NAME_ACCOUNT = "CLAIM_NAME_ACCOUNT";
    //token中存放用户真实姓名对应的名字
    private static final String CLAIM_NAME_USERNAME = "CLAIM_NAME_USERNAME";

    //token中存放用户是否是超级管理员
    private static final String CLAIM_NAME_ISADMIN = "CLAIM_NAME_ISADMIN";

    private String sign(CurrentUser currentUser, String securityKey) {
        String token = JWT.create().withClaim(CLAIM_NAME_USERID, currentUser.getId())
                                   .withClaim(CLAIM_NAME_ACCOUNT, currentUser.getAccount())
                                   .withClaim(CLAIM_NAME_USERNAME, currentUser.getName())
                .withClaim(CLAIM_NAME_ISADMIN,currentUser.isAdmin()).withIssuedAt(new Date())//发行时间
                .withExpiresAt(new Date(System.currentTimeMillis() + expireTime * 1000))//有效时间
                .sign(Algorithm.HMAC256(securityKey));


        return token;
    }


    /**
     * 将当前用户信息以用户密码为密钥生成token的方法
     */
    public String loginSign(CurrentUser currentUser, String password) {
        //生成token
        String token = sign(currentUser, password);
        //将token保存到redis中,并设置token在redis中的过期时间
        stringRedisTemplate.opsForValue().set(token, token, expireTime * 2, TimeUnit.SECONDS);
        return token;
    }

    /**
     * 从客户端归还的token中获取用户信息的方法
     */
    public static CurrentUser getCurrentUser(String token) {
        if (StringUtils.isEmpty(token)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "令牌为空，请登录！");
        }
        //对token进行解码,获取解码后的token
        DecodedJWT decodedJWT = null;
        try {
            decodedJWT = JWT.decode(token);
        } catch (JWTDecodeException e) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "令牌格式错误，请登录！");
        }
        //从解码后的token中获取用户信息并封装到CurrentUser对象中返回
        Long userId = decodedJWT.getClaim(CLAIM_NAME_USERID).asLong();//用户账号id
        String ACCOUNT = decodedJWT.getClaim(CLAIM_NAME_ACCOUNT).asString();//用户昵称
        String userName = decodedJWT.getClaim(CLAIM_NAME_USERNAME).asString();//用户姓名
        Boolean isAdmin = decodedJWT.getClaim(CLAIM_NAME_ISADMIN).asBoolean();//用户姓名
        if (StringUtils.isEmpty(ACCOUNT) || StringUtils.isEmpty(userName)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "令牌缺失用户信息，请登录！");
        }
        return new CurrentUser(userId,userName,ACCOUNT,isAdmin);
    }

}
