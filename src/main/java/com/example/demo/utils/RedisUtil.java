package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<String, Object>  redisTemplate;

    /**
     * @Description 存入缓存并 指定缓存失效时间
     * @Author wanxin
     * @Date 2019/12/27 15:12
     * @Param [key:键, time:时间]
     * @return boolean
     **/
    public boolean expire(String key,Object value,long time){
        redisTemplate.opsForValue().set(key,value);
        try {
            if(time>0){
                redisTemplate.expire(key,time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }
    public boolean delete(String key){
        return redisTemplate.delete(key);
    }


}
