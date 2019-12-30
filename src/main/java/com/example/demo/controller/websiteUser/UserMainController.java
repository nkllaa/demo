package com.example.demo.controller.websiteUser;

import com.example.demo.BizService.BizUserService;
import com.example.demo.utils.ResponseUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;


/**
 * @Description 网站用户主
 * @Author wanxin
 * @Date 2019/6/15 18:04
 **/
@RestController
@RequestMapping("/userMain")
public class UserMainController extends ResponseUtils {
    @Autowired
    private BizUserService bizUserService;

    /**
     * @Description 用户登录
     * @Author wanxin
     * @Date 2019/6/23 14:56
     * @Param [phoneNumber, password, request]
     * @return java.lang.String
     **/
    @RequestMapping("/userLogin")
    public Map<String ,Object> UserLogin(String phoneNumber, String password, HttpServletRequest request){
        JSONObject jo=bizUserService.usetLogin(phoneNumber,password,request);
        return this.setResponse(this.SUCCESS,"登录成功",jo);
    }
    /**
     * @Description 退出登录
     * @Author wanxin
     * @Date 2019/6/23 10:31
     * @Param [request]
     * @return org.springframework.web.servlet.ModelAndView
     **/
    @RequestMapping("/Logout")
    public ModelAndView Logout(HttpServletRequest request){
        request.getSession().removeAttribute("userInfo");
        return new ModelAndView("../static/login");
    }
    /**
     * @Description 用户注册
     * @Author wanxin
     * @Date 2019/8/21 16:48
     * @Param [phoneNumber, password]
     * @return java.lang.String
     **/
    @RequestMapping( value = "/userReg",method = RequestMethod.POST)
    public Map<String ,Object> UserReg(String phoneNumber,String password,String nickname) throws IOException {
        bizUserService.userRegister(nickname,phoneNumber,password);
        return this.setResponse(this.SUCCESS,"注册成功");
    }
    /**
     * @Description 跳转到主页
     * @Author wanxin
     * @Date 2019/6/23 14:56
     * @Param [webRequest]
     * @return org.springframework.web.servlet.ModelAndView
     **/
    @RequestMapping("/main")
    public ModelAndView Main(WebRequest webRequest){
        return new ModelAndView("main");
    }
    /**
     * @Description 跳转到全部文件页
     * @Author wanxin
     * @Date 2019/6/23 14:54
     * @Param []
     * @return org.springframework.web.servlet.ModelAndView
     **/
    @RequestMapping("/MyFolder")
    public ModelAndView MyFolder(){
        return new ModelAndView("myFolder");
    }
    @RequestMapping("/Console")
    public ModelAndView Console(){
        return new ModelAndView("console");
    }

}
