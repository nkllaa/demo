package com.example.demo.service;

import com.example.demo.entity.User;
import net.sf.json.JSONObject;


public interface UserService {


    User create(String userName,String phoneNumber,String password);
    /**
     * @Description 用户登陆
     * @Author wanxin
     * @Date 2019/5/26 17:49
     * @Param [phoneNumber, password]
     * @return com.example.demo.entity.User
     **/
    User usetLogin(String phoneNumber, String password);
    /**
     * @Description 根据用户手机好查询
     * @Author wanxin
     * @Date 2019/5/26 18:01
     * @Param [phoneNumber][phoneNumber]
     * @return com.example.demo.entity.User
     **/
    User getByPhoneNumber(String phoneNumber);
    /**
     * @Description 根据用户名查询
     * @Author wanxin
     * @Date 2020/1/2 18:04
     * @Param [userName]
     * @return com.example.demo.entity.User
     **/
    User getByUserName(String userName);
    /**
     * @Description 更新用户信息
     * @Author wanxin
     * @Date 2019/6/16 10:52
     * @Param [user]
     * @return void
     **/
    void updata(User user);
    /**
     * @Description 获取用户信息
     * @Author wanxin
     * @Date 2020/1/2 17:21
     * @Param [userId]
     * @return com.example.demo.entity.User
     **/
    User getById(long userId);
}
