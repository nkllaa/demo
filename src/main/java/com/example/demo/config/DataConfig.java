package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;

//@Component
//                                    //不能有大写字符
//@ConfigurationProperties(prefix = "wxdataconfig")
//JSR303校验
//@Validated
public class DataConfig {
    //@Email
    public static final String url="https://api.weixin.qq.com/sns/jscode2session";
    public static final String appId="wxeeb9111205eda9a7";
    public static final String AppSecret="cfac5d52abffef3d24c06b5ec11bd9d6";
    public static final String  grant_type="authorization_code";
}
