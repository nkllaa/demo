package com.example.demo.interceptor;

import com.example.demo.exception.BizException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description 小程序端拦截器
 * @Author wanxin
 * @Date 2019/7/21 11:28
 **/
public class XYPWXInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if ("".equals(request.getParameter("session_3rd").trim())  || null == request.getParameter("session_3rd")) {
            throw new BizException("请求失败");
        }
        return true;
    }
}