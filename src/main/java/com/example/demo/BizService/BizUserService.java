package com.example.demo.BizService;

import net.sf.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface BizUserService {
    /**
     * @Description 用户注册
     * @Author wanxin
     * @Date 2019/5/27 22:17
     * @Param [userName, phoneNumber, password]
     * @return net.sf.json.JSONObject
     **/
    JSONObject userRegister(String userName,String phoneNumber,String password) throws IOException;
    /**
     * @Description 用户登录
     * @Author wanxin
     * @Date 2019/6/1 14:09
     * @Param [phoneNumber, password, request]
     * @return net.sf.json.JSONObject
     **/
    JSONObject usetLogin(String phoneNumber, String password, HttpServletRequest request);

    /**
     * @Description 微信小程序登陆
     * @Author wanxin
     * @Date 2019/7/13 19:56
     * @Param [phoneNumber, password]
     * @return net.sf.json.JSONObject
     **/
    JSONObject wxUusetLogin(String phoneNumber,String password,String iv,String encryptedData,String code,String session_3rd,String sessionId);

    /**
     * @Description 获取用户信息
     * @Author wanxin
     * @Date 2020/1/2 17:12
     * @Param [userId]
     * @return net.sf.json.JSONObject
     **/
    JSONObject getUserInfo(long userId);
}
