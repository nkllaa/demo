package com.example.demo.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

@Component
public class TokenUtils {
    /**
     * 默认过期时长，单位：秒
     */
    public static final long DEFAULT_EXPIRE =7 * 60 * 60 * 24;
    @Autowired
    private RedisUtil redisUtil;
    /**
     * @Description 创建token
     * @Author wanxin
     * @Date 2019/12/28 13:40
     * @Param [account:账号, pass:密码]
     * @return java.lang.String
     **/
    public static String createToken(String account,String pass){

        long timestamp=System.currentTimeMillis();

        String str=account+pass+timestamp;
        String token= DigestUtils.md5DigestAsHex(DigestUtils.md5DigestAsHex(str.getBytes()).getBytes());

        return token;
    }
    /**
     * @Description 存储token
     * @Author wanxin
     * @Date 2019/12/28 13:42
     * @Param [key：键, value：值, time：存储时间]
     * @return boolean
     **/
    public boolean save(String key,Object value){
        return redisUtil.expire(key,value,DEFAULT_EXPIRE);
    }
}
