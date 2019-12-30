package com.example.demo.interceptor;

import com.example.demo.entity.User;
import com.example.demo.exception.BizException;
import com.example.demo.exception.LoginException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description 网页端拦截器
 * @Author wanxin
 * @Date 2019/7/21 11:26
 **/
public class XYPMinInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       User user = (User) request.getSession().getAttribute("userInfo");

       if (user==null){
           throw new LoginException("登陆失效");
       }
        return true;
    }
}
