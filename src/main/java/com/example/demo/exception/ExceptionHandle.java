package com.example.demo.exception;


import com.example.demo.utils.ResponseUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

//全局异常处理
@ControllerAdvice
public class ExceptionHandle extends ResponseUtils {

    ByteArrayOutputStream buf = new java.io.ByteArrayOutputStream();


    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Object RuntimeExceptionHandler(RuntimeException exception,HttpServletRequest request,WebRequest webRequest) {
        if (exception instanceof BizException || exception instanceof LoginException){
            if (isAjaxRequest(webRequest)){
                return this.setResponse(this.Fail,exception.getMessage());
            }
            else {
                request.setAttribute("errormsg",exception.getMessage());
                return new ModelAndView("../static/error/404");
            }
        }else {
            exception.printStackTrace(new java.io.PrintWriter(buf, true));
            String expMessage = buf.toString();
            //打印异常信息
            System.out.println(expMessage);
            if (isAjaxRequest(webRequest)){
                return this.setResponse(this.Fail,"系统异常");
            }
            else {
                request.setAttribute("errormsg","系统异常");
                return new ModelAndView("../static/error/404");
            }
        }
    }
    @ExceptionHandler(IOException.class)
    @ResponseBody
    public Object IoHandler(IOException exception,HttpServletRequest request,WebRequest webRequest){
        System.out.println(exception.getMessage());
        exception.printStackTrace(new java.io.PrintWriter(buf, true));
        String expMessage = buf.toString();
        try {
            buf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //打印异常信息
        System.out.println(expMessage);
        if (isAjaxRequest(webRequest)){
            return this.setResponse(this.Fail,"文件系统异常");
        }
        else {
            request.setAttribute("errormsg","文件系统异常");
            return new ModelAndView("../static/error/404");
        }
    }
    /**
     * @Description 判断是否为Ajax请求
     * @Author wanxin
     * @Date 2019/6/23 13:12
     * @Param [webRequest]
     * @return boolean
     **/
    public static boolean isAjaxRequest(WebRequest webRequest) {
        String requestedWith = webRequest.getHeader("X-Requested-With");
        return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
    }
}
